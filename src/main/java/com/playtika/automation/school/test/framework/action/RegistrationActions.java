package com.playtika.automation.school.test.framework.action;

import com.playtika.automation.school.test.framework.client.RegistrationFeignClient;
import com.playtika.automation.school.test.framework.pojo.RegistrationRequest;
import com.playtika.automation.school.test.framework.pojo.responses.RegistrationResponse;
import lombok.AllArgsConstructor;

import java.util.Random;

@AllArgsConstructor
public class RegistrationActions {

    private final RegistrationFeignClient registrationFeignClient;

    private static final String MAIL = "mail_";
    private static final String MAILPOSTFIX = "@yandex.ru";
    private static final String PASSWORD = "Password_";
    private static final int RANDOMLOW = 1;
    private static final int RANDOMHIGH = 100000;

    public RegistrationResponse getRegistrationData(String mail, String password) {
        RegistrationRequest request = RegistrationRequest.builder()
                .email(mail)
                .password(password)
                .build();
        return registrationFeignClient.createUser(request);
    };

    private Integer generateRandomInt() {

        Random r = new Random();

        int result = r.nextInt(RANDOMHIGH-RANDOMLOW) + RANDOMLOW;
        return result;
    }

    public String generateRandomMail() {

        String randomMail = MAIL + generateRandomInt().toString() + MAILPOSTFIX;
        return randomMail;
    }

    public String generateRandomPassword() {

        String randomPassword =  PASSWORD + generateRandomInt().toString();
        return randomPassword;
    }
}
