package com.perfumeria.custom.profile.models.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomProfile {

    private String email;
    private Long clientid;
    private String gender;
    private String [] hobbies;
    private String [] productInterest;
    private String [] favoriteProducts;
    private String  skinType;
    private String [] improveYourSkin;
    private String  hairType;
    private String [] hairNeed;
    private String [] scentType;

}
