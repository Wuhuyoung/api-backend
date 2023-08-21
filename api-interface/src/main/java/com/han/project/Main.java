package com.han.project;


import com.han.apiclientsdk.client.NameApiClient;
import com.han.apiclientsdk.model.User;

public class Main {
    public static void main(String[] args) {
        String accessKey = "han";
        String secretKey = "asdjkl";
        NameApiClient nameApiClint = new NameApiClient(accessKey, secretKey);
        String result1 = nameApiClint.getNameByGet("Jack");
        String result2 = nameApiClint.getNameByPost("Cool");
        User user = new User();
        user.setName("Mike");
        String result3 = nameApiClint.getUserName(user);
        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);
    }


}
