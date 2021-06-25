package com.playtika.automation.school.test.framework.client;

import com.playtika.automation.school.test.framework.pojo.responses.AuthResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "auth-client",
        url = "${test.service.host}",
        path = "/oauth/token",
        configuration = FeignConfiguration.class
)
public interface AuthFeignClient {

    @PostMapping(consumes = "application/json")
    AuthResponse authorizeUser(@RequestHeader("authorization") String authorization,
                               @RequestParam("grant_type") String grandType,
                               @RequestParam("scope") String scope,
                               @RequestParam("username") String mail,
                               @RequestParam("password") String password);
}
