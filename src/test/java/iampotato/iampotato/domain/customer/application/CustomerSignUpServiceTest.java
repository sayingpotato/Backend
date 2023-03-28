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
        Customer customer = Customer.createCustomer("test1", "1q2w3e4r1!", "로건");

        //when
        Long saveId = customerSignUpService.signUp(customer);

        //then
        assertEquals(customer, customerRepository.findOne(saveId));
    }

    @Test(expected = IllegalStateException.class)
    public void 중복_아이디_예외() throws Exception {
        //given
        Customer customer1 = Customer.createCustomer("test1", "123", "로건");
        Customer customer2 = Customer.createCustomer("test1", "123", "루루");

        //when
        customerSignUpService.signUp(customer1);
        customerSignUpService.signUp(customer2);

        //then
        fail("예외가 발생해야 한다.");
    }



}