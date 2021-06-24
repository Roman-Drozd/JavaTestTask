package com.playtika.automation.school.test.framework.pojo.responses;

import lombok.Value;

@Value
public class AuthResponse {

    String access_token;
    String token_type;
    Integer expires_in;
    String scope;
}
