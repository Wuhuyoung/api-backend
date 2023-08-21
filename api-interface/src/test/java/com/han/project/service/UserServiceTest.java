package com.han.project.service;

import com.han.apiclientsdk.client.NameApiClient;
import com.han.apiclientsdk.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;


@SpringBootTest
class UserServiceTest {

    @Resource
    NameApiClient nameApiClient;

    @Test
    void testApiClient() {
        String result1 = nameApiClient.getNameByGet("mike");
        User user = new User();
        user.setName("Jack");
        String result2 = nameApiClient.getUserName(user);
        System.out.println();
        System.out.println(result1);
        System.out.println(result2);
    }
}