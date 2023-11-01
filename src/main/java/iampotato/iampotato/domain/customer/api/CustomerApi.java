package iampotato.iampotato.domain.customer.api;

import iampotato.iampotato.domain.customer.application.*;
import iampotato.iampotato.domain.customer.dao.CustomerRepository;
import iampotato.iampotato.domain.customer.domain.Customer;
import iampotato.iampotato.domain.customer.dto.*;
import iampotato.iampotato.global.util.Result;
import iampotato.iampotato.global.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class CustomerApi {
    private final CustomerSignInService customerSignInService;
    private final CustomerSignUpService customerSignUpService;
    private final CustomerImageService customerImageService;
    private final CertifyCustomerService certifyCustomerService;
    private final GetUnauthorizedCustomersService getUnauthorizedCustomersService;
    private final CustomerRepository customerRepository;

    @PostMapping("/api/v1/customers/signUp")
    public Result<SignUpResponse> signUp(@RequestBody SignUpRequest signUpRequest) throws Exception {    //회원 가입하는 POST API
        //Spring security로 Password Hash 암호화 로직 추가하기
        Customer customer = Customer.builder()
                .loginId(signUpRequest.getLoginId())
                .password(signUpRequest.getPassword())
                .nickname(signUpRequest.getNickname())
                .customerNumber(signUpRequest.getCustomerNumber())
                .customerDept(signUpRequest.getCustomerDept())
                .customerCollege(signUpRequest.getCustomerCollege())
                .build();
        String id = customerSignUpService.signUp(customer);
        return new Result<>(Result.CODE_SUCCESS, Result.MESSAGE_OK, new SignUpResponse(id));
    }

    @PostMapping("/api/v1/customers/signIn")
    public Result<TokenResponse> signIn(@RequestBody SignInRequest signInRequest) throws Exception {
        TokenResponse tokenResponse = customerSignInService.signIn(signInRequest.getLoginId(), signInRequest.getPassword());
        return new Result<>(Result.CODE_SUCCESS, Result.MESSAGE_OK, customerSignInService.addCustomerStatus(tokenResponse, signInRequest.getLoginId()));
    }

    @PutMapping("/api/v1/customers/image") //MultipartFile을 처리하기 위해서 @RequestParam을 사용했다. 따라서 {image:이미지파일} 꼴로 넘겨 받아야한다.
    public Result<UploadImageResponse> uploadImage(@RequestParam(value = "image", required = false) MultipartFile multipartFile) throws Exception {
//        System.out.println(SecurityUtil.getCurrentUserId());
        String customerId = SecurityUtil.getCurrentUserId();
        Customer customer = customerImageService.uploadImage(customerId, multipartFile);
        return new Result<>(Result.CODE_SUCCESS, Result.MESSAGE_OK, new UploadImageResponse(customerId, customer.getCustomerImage()));
    }

    @PostMapping("/api/v1/customers/certify")
    public Result<String> certifyCustomer(@RequestBody CertifyCustomerRequest certifyCustomerRequest) {
        certifyCustomerService.certifyCustomer(certifyCustomerRequest.getId());
        return new Result<>(Result.CODE_SUCCESS, Result.MESSAGE_OK, null);
    }

    @GetMapping("/api/v1/customers/unauthorizedCustomers")
    public Result<List<UnauthorizedCustomer>> getUnauthorizedCustomers() throws Exception {

        List<Customer> unauthorizedCustomers = getUnauthorizedCustomersService.getUnauthorizedCustomers();
        List<UnauthorizedCustomer> responses = unauthorizedCustomers.stream()
                .map(unauthorizedCustomer -> new UnauthorizedCustomer(
                        unauthorizedCustomer.getId(),
                        unauthorizedCustomer.getCustomerNumber(),
                        unauthorizedCustomer.getCustomerDept(),
                        unauthorizedCustomer.getCustomerCollege(),
                        unauthorizedCustomer.getCustomerImage()
                ))
                .collect(Collectors.toList());

        return new Result<>(Result.CODE_SUCCESS, Result.MESSAGE_OK, responses);
    }

    @GetMapping("/api/v1/mypage")
    public Result<MyPageResponse> getUserInfo() {

        Customer customer = customerRepository.findOne(SecurityUtil.getCurrentUserId());

        MyPageResponse response = new MyPageResponse(customer);

        return new Result<>(Result.CODE_SUCCESS, Result.MESSAGE_OK, response);
    }

    @PutMapping("/api/v1/profile")
    public Result<Boolean> updateProfile(@RequestBody CustomerUpdateProfileRequest request) {

        Customer customer = customerRepository.findOne(SecurityUtil.getCurrentUserId());

        customer.updateProfile(request.getNickname());

        return new Result<>(Result.CODE_SUCCESS, Result.MESSAGE_OK, true);
    }


    @GetMapping(value = "/image/view", produces = {"image/jpeg", "image/png", "image/gif"})
    public byte[] getImage(@RequestParam("customerStoredImage") String customerStoredImage) throws IOException {
        FileInputStream fis = null; //파일로부터 바이트로 입력받기
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            fis = new FileInputStream(customerStoredImage); //화면에 출력하고자 하는 이미지 파일을 fis에 넣어주기
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int readCount = 0;
        byte[] buffer = new byte[1024];
        byte[] fileArray = null;
        try {
            while ((readCount = fis.read(buffer)) != -1) {  //fis가 파일 내용을 한번에 모두 읽어서 buffer에 저장
                baos.write(buffer, 0, readCount);   //baos에 offset인 0부터 readCount만큼 한번에 buffer 바이트 배열에서 데이터를 읽어 쓴다
            }
            fileArray = baos.toByteArray(); //baos를 바이트 배열로 변환
            fis.close();
            baos.close();
        } catch (IOException e) {
            throw new RuntimeException("File Error");
        }
        return fileArray;   //바이트 배열로 리턴
    }
}
