package com.perfumeria.custom.profile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.perfumeria.custom.profile.models.request.CustomProfile;
//import com.perfumeria.custom.profile.service.ICustomProfileService;
import com.perfumeria.custom.profile.utils.CustomProfileException;





@RestController
@RequestMapping("/custom")
public class CustomProfileController {

  //  @Autowired
  //  private ICustomProfileService customProfileService;

    @GetMapping("/health")
    public String getMethodName() {
        return "TODO OK";
    }

    @GetMapping("/profile/{id}")
    public CustomProfile getCustomerProfile(@PathVariable Long id) {
               
        CustomProfile customP = new CustomProfile();

        customP.setClientid(id);
        customP.setEmail("falex.orellana@gmail.com");
        customP.setGender("Masculino");
        customP.setHobbies(new String[]{"Escribir","Leer","Escuchar"});   
        customP.setProductInterest(new String[]{"Perfumes","Maquillaje","Tratamiento"});
        customP.setFavoriteProducts(new String[]{"Perfumes de Mujer","Perfumes de Hombre","Perfumes Unisex","Estuchería","Perfumes para Bebés","Perfumes para Niños","Labios","Ojos","Rostro","Uñas","Cejas","Accesorios","Estuchería","Piernas","Cuerpo","Ojos","Labios","Rostro","Cejas","Estuchería","Manos","Complementos","Escritorio","Carteras","Joyas","Relojes","Set","Accesorios","Decoración","Capilar","Necesidad","Tipo de Producto"});
        //customP.setSkinType(new String[]{"Perfumes de Mujer","Perfumes de Hombre","Perfumes Unisex","Estuchería","Perfumes para Bebés","Perfumes para Niños","Labios","Ojos","Rostro","Uñas","Cejas","Accesorios","Estuchería","Piernas","Cuerpo","Ojos","Labios","Rostro","Cejas","Estuchería","Manos","Complementos","Escritorio","Carteras","Joyas","Relojes","Set","Accesorios","Decoración","Capilar","Necesidad","Tipo de Producto"});        
        customP.setImproveYourSkin(new String[]{"Perfumes de Mujer","Perfumes de Hombre","Perfumes Unisex","Estuchería","Perfumes para Bebés","Perfumes para Niños","Labios","Ojos","Rostro","Uñas","Cejas","Accesorios","Estuchería","Piernas","Cuerpo","Ojos","Labios","Rostro","Cejas","Estuchería","Manos","Complementos","Escritorio","Carteras","Joyas","Relojes","Set","Accesorios","Decoración","Capilar","Necesidad","Tipo de Producto"}); 
        //customP.setHairType(new String[]{"Perfumes de Mujer","Perfumes de Hombre","Perfumes Unisex","Estuchería","Perfumes para Bebés","Perfumes para Niños","Labios","Ojos","Rostro","Uñas","Cejas","Accesorios","Estuchería","Piernas","Cuerpo","Ojos","Labios","Rostro","Cejas","Estuchería","Manos","Complementos","Escritorio","Carteras","Joyas","Relojes","Set","Accesorios","Decoración","Capilar","Necesidad","Tipo de Producto"});
        customP.setHairNeed(new String[]{"Perfumes de Mujer","Perfumes de Hombre","Perfumes Unisex","Estuchería","Perfumes para Bebés","Perfumes para Niños","Labios","Ojos","Rostro","Uñas","Cejas","Accesorios","Estuchería","Piernas","Cuerpo","Ojos","Labios","Rostro","Cejas","Estuchería","Manos","Complementos","Escritorio","Carteras","Joyas","Relojes","Set","Accesorios","Decoración","Capilar","Necesidad","Tipo de Producto"});        
        
    
        return customP;
        
    }
    

    @PostMapping("/profile")
    public String customProfile(@RequestBody CustomProfile customProfile) {
        //TODO: process POST request

       /*  com.perfumeria.custom.profile.models.sqlserver.CustomProfile customP = new com.perfumeria.custom.profile.models.sqlserver.CustomProfile();


        customP.setClientid(customProfile.getClientid());
        customP.setEmail(customProfile.getEmail());
        customP.setGender(customProfile.getGender());
        customP.setHobbies(String.join(",", customProfile.getHobbies()));   
        customP.setProductInterest(String.join(",", customProfile.getProductInterest()));
        customP.setFavoriteProducts(String.join(",", customProfile.getFavoriteProducts()));
        customP.setSkinType(String.join(",", customProfile.getSkinType()));
        customP.setImproveYourSkin(String.join(",", customProfile.getImproveYourSkin()));
        customP.setHairType(String.join(",", customProfile.getHairType()));
        customP.setHairNeed(String.join(",", customProfile.getHairNeed()));
        customP.setScentType(String.join(",", customProfile.getScentType()));     */ 
    

        /*try {
            customProfileService.saveCustomProfile(customP);
            
        } catch (CustomProfileException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/

       // System.out.println("SEXO :" + customProfile.getGender());
        
        return "entity";
    }
    
}
