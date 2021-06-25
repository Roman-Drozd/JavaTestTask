package com.playtika.automation.school.test.framework.pojo.responses;

import lombok.AllArgsConstructor;
import lombok.Value;


@Value
public class RegistrationResponse {

    Integer id;
    String email;
    String registeredAt;
}
