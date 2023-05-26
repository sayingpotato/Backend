package iampotato.iampotato.domain.customer.application;

import iampotato.iampotato.domain.customer.dao.CustomerRepository;
import iampotato.iampotato.domain.customer.domain.Customer;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)    //spring이랑 완전히 integration해서 테스트 하기 위해
@SpringBootTest //spring이랑 완전히 integration해서 테스트 하기 위함
@Transactional  //테스트 메소드가 끝난 뒤 롤백을 하기 위해
//정확히는 EntityManager가 flush를 하지 못하게 하여 DB에 커밋이 안되는 것
class CustomerImageServiceTest {

    @Autowired
    CustomerImageService customerImageService;

    @Autowired
    CustomerSignUpService customerSignUpService;

    @Autowired
    CustomerRepository customerRepository;

    @Test
    public void 학생증_이미지_업로드() throws Exception {
        //given
        MockMultipartFile uploadFile = new MockMultipartFile("image", "test.png", "image/png", new byte[1]);
        Customer customer = Customer.builder()
                .loginId("testImage")
                .password("1q2w3e4r1!")
                .nickname("loganImage")
                .build();
        Long customerId = customerSignUpService.signUp(customer);
        //when
        customerImageService.uploadImage(customerId, uploadFile);

        //then
        assertEquals("test.png", customerRepository.findOne(customerId).getCustomerImage().getCustomerOriginalImage());
    }
}