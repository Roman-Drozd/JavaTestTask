package com.playtika.automation.school.test.framework.configuration;

import com.playtika.automation.school.test.framework.action.RegistrationActions;
import com.playtika.automation.school.test.framework.client.RegistrationFeignClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(clients = RegistrationFeignClient.class)
public class RegistrationConfiguration {

    @Bean
    public RegistrationActions registrationActions(RegistrationFeignClient registrationFeignClient) {
        return new RegistrationActions(registrationFeignClient);
    }
}
