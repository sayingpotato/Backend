package iampotato.iampotato.domain.customer.api;

import iampotato.iampotato.domain.customer.application.CustomerSignUpService;
import iampotato.iampotato.domain.customer.domain.Customer;
import iampotato.iampotato.domain.customer.dto.SignUpRequest;
import iampotato.iampotato.domain.customer.dto.SignUpResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CustomerApi {

    private final CustomerSignUpService customerSignUpService;

    @PostMapping("/api/v1/members")
    public SignUpResponse signUp(@RequestBody SignUpRequest signUpRequest) {    //회원 가입하는 POST API
        //Spring security로 Password Hash 암호화 로직 추가하기
        Customer customer = Customer.createCustomer(signUpRequest.getLoginId(), signUpRequest.getPassword(), signUpRequest.getNickname());
        Long id = customerSignUpService.signUp(customer);
        return new SignUpResponse(id);
    }
}
