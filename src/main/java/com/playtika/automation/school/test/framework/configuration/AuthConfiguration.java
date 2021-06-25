package com.playtika.automation.school.test.framework.configuration;

import com.playtika.automation.school.test.framework.action.AuthActions;
import com.playtika.automation.school.test.framework.client.AuthFeignClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(clients = AuthFeignClient.class)
public class AuthConfiguration {

    @Bean
    public AuthActions authActions(AuthFeignClient authFeignClient,
                                   @Value("${auth.authorization}") String authorization,
                                   @Value("${auth.grant.type}") String grantType,
                                   @Value("${auth.scope}") String scope) {
        return new AuthActions(authFeignClient, authorization, grantType, scope);
    }
}
