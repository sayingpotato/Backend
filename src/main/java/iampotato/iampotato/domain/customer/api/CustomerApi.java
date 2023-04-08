package iampotato.iampotato.domain.customer.api;

import iampotato.iampotato.domain.customer.application.CustomerImageService;
import iampotato.iampotato.domain.customer.application.CustomerSignUpService;
import iampotato.iampotato.domain.customer.domain.Customer;
import iampotato.iampotato.domain.customer.dto.SignUpRequest;
import iampotato.iampotato.domain.customer.dto.SignUpResponse;
import iampotato.iampotato.domain.customer.dto.UploadImageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class CustomerApi {

    private final CustomerSignUpService customerSignUpService;
    private final CustomerImageService customerImageService;

    @PostMapping("/api/v1/customers")
    public SignUpResponse signUp(@RequestBody SignUpRequest signUpRequest) {    //회원 가입하는 POST API
        //Spring security로 Password Hash 암호화 로직 추가하기
        Customer customer = Customer.builder()
                .loginId(signUpRequest.getLoginId())
                .password(signUpRequest.getPassword())
                .nickname(signUpRequest.getNickname())
                .build();
        Long id = customerSignUpService.signUp(customer);
        return new SignUpResponse(id);
    }

    @PutMapping("/api/v1/customers/{id}/image")
    public UploadImageResponse uploadImage(@PathVariable("id") Long customerId, MultipartFile multipartFile) throws Exception {
        Customer customer = customerImageService.uploadImage(customerId, multipartFile);
        return new UploadImageResponse(customerId, customer.getCustomerImage());
    }
}
