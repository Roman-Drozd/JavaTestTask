package com.playtika.automation.school.test.framework.client;

import com.playtika.automation.school.test.framework.pojo.RegistrationRequest;
import com.playtika.automation.school.test.framework.pojo.responses.RegistrationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "registration-client",
        url = "${test.service.host}",
        path = "/v1/accounts",
        configuration = FeignConfiguration.class
)
public interface RegistrationFeignClient {

    @PostMapping(consumes = "application/json")
    RegistrationResponse createUser(@RequestBody RegistrationRequest request);
}
