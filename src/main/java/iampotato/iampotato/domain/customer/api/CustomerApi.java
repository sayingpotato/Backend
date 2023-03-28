package iampotato.iampotato.domain.customer.api;

import iampotato.iampotato.domain.customer.application.CustomerSignUpService;
import iampotato.iampotato.domain.customer.dto.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CustomerApi {

    private final CustomerSignUpService customerSignUpService;

    @PostMapping("/api/v1/members")
    public Long signUp(SignUpRequest signUpRequest) {
        return customerSignUpService.signUp();
    }
}
