package com.playtika.automation.school.test.framework.action;

import com.playtika.automation.school.test.framework.client.AuthFeignClient;
import com.playtika.automation.school.test.framework.pojo.responses.AuthResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AuthActions {

    private final AuthFeignClient authFeignClient;
    private final String authorization;
    private final String grandType;
    private final String scope;

    public AuthResponse authUser(String mail, String password) {
        return authFeignClient.authorizeUser(authorization, grandType, scope, mail, password);
    }
}
