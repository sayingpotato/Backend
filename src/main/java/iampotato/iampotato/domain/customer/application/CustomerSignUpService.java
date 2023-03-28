package iampotato.iampotato.domain.customer.application;

import iampotato.iampotato.domain.customer.dao.CustomerRepository;
import iampotato.iampotato.domain.customer.domain.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CustomerSignUpService {

    private final CustomerRepository customerRepository;

    @Transactional
    public Long signUp(Customer customer) {
        //여기에 중복 회원 검증 추가할 것
        customerRepository.save(customer);
        return customer.getId();
    }

    //손님 전체 조회
    public List<Customer> findCustomers() {
        return customerRepository.findAll();
    }

    //id를 사용한 손님 단건 조회
    public Customer findOne(Long customerId) {
        return customerRepository.findOne(customerId);
    }

}
