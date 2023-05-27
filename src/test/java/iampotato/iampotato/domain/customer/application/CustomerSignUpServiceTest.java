package iampotato.iampotato.domain.customer.application;

import iampotato.iampotato.domain.customer.dao.CustomerRepository;
import iampotato.iampotato.domain.customer.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)    //spring이랑 완전히 integration해서 테스트 하기 위해
@SpringBootTest //spring이랑 완전히 integration해서 테스트 하기 위함
@Transactional  //테스트 메소드가 끝난 뒤 롤백을 하기 위해
//정확히는 EntityManager가 flush를 하지 못하게 하여 DB에 커밋이 안되는 것
public class CustomerSignUpServiceTest {

    @Autowired
    CustomerSignUpService customerSignUpService;

    @Autowired
    CustomerRepository customerRepository;

    @Test
    public void 회원가입() throws Exception {
        //given
        Customer customer = Customer.builder()
                .loginId("test1")
                .password("1q2w3e4r1!")
                .nickname("로건")
                .build();

        //when
        String saveId = customerSignUpService.signUp(customer);

        //then
        assertEquals(customer, customerRepository.findOne(saveId));
    }

    @Test
    public void 중복_아이디_예외() throws Exception {
        //given
        Customer customer1 = Customer.builder()
                .loginId("test1")
                .password("123")
                .nickname("로건")
                .build();

        Customer customer2 = Customer.builder()
                .loginId("test1")
                .password("123")
                .nickname("루루")
                .build();

        //when
        customerSignUpService.signUp(customer1);
        assertThatThrownBy(() -> customerSignUpService.signUp(customer2))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("아이디");

        //then
    }


    @Test
    public void 중복_닉네임_예외() throws Exception {
        //given
        Customer customer1 = Customer.builder()
                .loginId("test1")
                .password("123")
                .nickname("로건")
                .build();

        Customer customer2 = Customer.builder()
                .loginId("test2")
                .password("123")
                .nickname("로건")
                .build();

        //when
        customerSignUpService.signUp(customer1);
        assertThatThrownBy(() -> customerSignUpService.signUp(customer2))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("닉네임");

        //then
    }

}