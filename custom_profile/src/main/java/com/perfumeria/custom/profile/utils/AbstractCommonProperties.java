package com.perfumeria.custom.profile.utils;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.TimeZone;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.gson.Gson;



public abstract class AbstractCommonProperties {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    public static final String DEBUG_CONSTANT = "debug";
    public static final String ERROR_CONSTANT = "error";
    public static final String INFO_CONSTANT = "info";

    public CustomErrorType getError(String code, String description) {
        CustomErrorType err = new CustomErrorType();
        switch (code) {
            case "400" -> {
                err.setErrorCode(400);
                err.setErrorType("MSJ");
                err.setCode("001");
                err.setDescription("Bad request");
            }
            case "401" -> {
                err.setErrorCode(401);
                err.setErrorType("SEG");
                err.setCode("001");
                err.setDescription("Invalid authorization code");
            }
            case "403" -> {
                err.setErrorCode(403);
                err.setErrorType("NEG");
                err.setCode("003");
                err.setDescription("Request cannot be completed.");
            }
            case "404" -> {
                err.setErrorCode(404);
                err.setErrorType("MSJ");
                err.setCode("002");
                err.setDescription("Trying to access a resource that do not exists.");
            }
            case "429" -> {
                err.setErrorCode(429);
                err.setDescription("Limit is exceeded.");
            }
            case "500" -> {
                err.setErrorCode(500);
                err.setErrorType("DES");
                err.setCode("003");
                err.setDescription("Internal server error");
            }
            case "503" -> {
                err.setErrorCode(503);
                err.setErrorType("COM");
                err.setCode("003");
                err.setDescription("There is no communication with external service");
            }
            case "504" -> {
                err.setErrorCode(504);
                err.setErrorType("COM");
                err.setCode("003");
                err.setDescription("Timeout.");
            }
            default -> {
                err.setCode(code);
                err.setDescription("Unknown error");
            }
        }
        if (description.length() == 0) {
            return err;
        } else {
            err.setDescription(description);
            return err;
        }
    }

    private void log(String evento, Long initialTime, String origen, String destino,String exeption, Object response,  String type)
            throws IOException {
        GenericLog jsonLog = new GenericLog();
        jsonLog.setEvento(evento);
        jsonLog.setOrigen(origen);
        jsonLog.setDestino(destino);
        jsonLog.setExeption(exeption);
        jsonLog.setResponse(response);

        if (type.equalsIgnoreCase(DEBUG_CONSTANT)) {
            jsonLog.setLevel(10);
        } else if (type.equalsIgnoreCase("info")) {
            jsonLog.setLevel(20);
        } else if (type.equalsIgnoreCase("warning")) {
            jsonLog.setLevel(30);
        } else if (type.equalsIgnoreCase(ERROR_CONSTANT)) {
            jsonLog.setLevel(40);
        } else if (type.equalsIgnoreCase("critical")) {
            jsonLog.setLevel(50);
        } else {
            jsonLog.setLevel(0);
        }

        jsonLog.setTime(this.formatoFecha(new Date()));

        switch (type) {
            case DEBUG_CONSTANT ->
                // deepcode ignore LogLevelCheck: <please specify a reason of ignoring this>
                logger.debug(new Gson().toJson(jsonLog));
            case INFO_CONSTANT ->
                logger.info(new Gson().toJson(jsonLog));
            case ERROR_CONSTANT ->
                logger.error(new Gson().toJson(jsonLog));
            default ->
                logger.error(new Gson().toJson(jsonLog));
            
        }
    }

    public String formatoFecha(Date fecha) {
        DateFormat formato = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        formato.setTimeZone(TimeZone.getTimeZone("GMT"));
        return formato.format(fecha);
    }

    public String getFecha(Date fecha) {
        DateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        formato.setTimeZone(TimeZone.getTimeZone("GMT"));
        return formato.format(fecha);
    }

    public Timestamp getDateString(String fechaStr) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyyHHmm");

            // Parsear la cadena a LocalDateTime
            LocalDateTime localDateTime = LocalDateTime.parse(fechaStr, formatter);

            // Convertir LocalDateTime a Timestamp directamente
            return Timestamp.valueOf(localDateTime);

        } catch (Exception e) {
            return null;
        }
    }

    public void setLog(String evento, Long initialTime, String origen,String destino, String exeption, Object response, String type) {
        try {
            log(evento, initialTime, origen,destino, exeption, response, type);
        } catch (IOException e) {
            logger.debug("Error al guardar logs");
        }
    }

    public Date getDateYesterday() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -1); // Ir a ayer
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59); // Usar 59 segundos para incluir correos justo antes de la medianoche
        return calendar.getTime();
    }

    public HttpHeaders getHeaders(String token) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + token);
        return headers;
    }

    public String validarIdentidad(Integer id){
        
        Map<Integer, String> map = new HashMap<>();

        map.put(8, "DNI");
        map.put(11,"RUC");
        map.put(12, "Pasaporte");
        
        return map.get(id);        
    }

    public String validaSexo(int id){

        Map<Integer, String> map = new HashMap<>();

        map.put(4, "Mujer");
        map.put(7,"Sin Especificar"); 
        map.put(1, "Hombre"); 
        
        return map.get(id);        
    }

    public String getCategoria(String id){

        Map<String, String> map = new HashMap<>();

        map.put("118", "Perfumes");
        map.put("127","Maquillaje"); 
        map.put("130", "Tratamiento"); 
        map.put("133", "Accesorios"); 
        map.put("895", "Capilar Profesional"); 
        
        return map.get(id);        
    }
    
    public String getSubcategoria(String id){
        Map<String, String> map = new HashMap<>();

        map.put("121", "Perfumes de Mujer");
        map.put("136", "Perfumes de Hombre");
        map.put("145", "Perfumes Unisex");
        map.put("148", "Estuchería");
        map.put("943", "Perfumes para Bebés");
        map.put("994", "Perfumes para Niños");
        map.put("202", "Labios");
        map.put("259", "Ojos");
        map.put("223", "Rostro");
        map.put("280", "Uñas");
        map.put("286", "Cejas");
        map.put("316", "Accesorios");
        map.put("352", "Estuchería");
        map.put("1902", "Piernas");
        map.put("376", "Cuerpo");
        map.put("367", "Ojos");
        map.put("385", "Labios");
        map.put("358", "Rostro");
        map.put("394", "Cejas");
        map.put("403", "Estuchería");
        map.put("766", "Manos");
        map.put("511", "Complementos");
        map.put("514", "Escritorio");
        map.put("517", "Carteras");
        map.put("520", "Joyas");
        map.put("523", "Relojes");
        map.put("862", "Set");
        map.put("865", "Accesorios");
        map.put("868", "Decoración");
        map.put("898", "Capilar");
        map.put("1541", "Necesidad");
        map.put("1544", "Tipo de Producto");

        return map.get(id);        
    }

    public String getCategoriaFromSub(String id){
        Map<String, String> map = new HashMap<>();
        
        map.put("Perfumes de Mujer", "Perfumes");
        map.put("Perfumes de Hombre", "Perfumes");
        map.put("Perfumes Unisex", "Perfumes");
        map.put("Estuchería", "Perfumes");
        map.put("Perfumes para Bebés", "Perfumes");
        map.put("Perfumes para Niños", "Perfumes");
        map.put("Labios", "Maquillaje");
        map.put("Ojos", "Maquillaje");
        map.put("Rostro", "Maquillaje");
        map.put("Uñas", "Maquillaje");
        map.put("Cejas", "Maquillaje");
        map.put("Accesorios", "Maquillaje");
        map.put("Estuchería", "Maquillaje");
        map.put("Piernas", "Maquillaje");
        map.put("Cuerpo", "Tratamiento");
        map.put("Ojos", "Tratamiento");
        map.put("Labios", "Tratamiento");
        map.put("Rostro", "Tratamiento");
        map.put("Cejas", "Tratamiento");
        map.put("Estuchería", "Tratamiento");
        map.put("Manos", "Tratamiento"); 
        map.put("Complementos", "Accesorios");
        map.put("Escritorio", "Accesorios");
        map.put("Carteras", "Accesorios");
        map.put("Joyas", "Accesorios");
        map.put("Relojes", "Accesorios");
        map.put("Set", "Accesorios");
        map.put("Accesorios", "Accesorios");
        map.put("Decoración", "Accesorios");
        map.put("Capilar", "Capilar Profesional");
        map.put("Necesidad", "Capilar Profesional");
        map.put("Tipo de Producto", "Capilar Profesional");

        return map.get(id);
    }

    public String limpiar_colony(String value){

        if(value == null){
            return "";
        }   
        return value.replace("\n", "").replace("colony", "");
    }

    public String validaIdentidad(String existe_att) {

        Map<String, String> map = new HashMap<>();

        map.put("8", "DNI");
        map.put("11", "RUC");
        map.put("12", "Pasaporte");

        return map.get(existe_att);
    }

    public String limpiarDni(String dni) {

        if(dni == null){
            return "";
        }   
        return dni.replace("\n", "").replace("dni", "");
    }


   
}
