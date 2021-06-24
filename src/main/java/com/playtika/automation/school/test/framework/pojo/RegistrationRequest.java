package com.playtika.automation.school.test.framework.pojo;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RegistrationRequest {

    String email;
    String password;
}
