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
        validateDuplicatedCustomerByLoginId(customer);
        validateDuplicatedCustomerByNickname(customer);
        customerRepository.save(customer);
        return customer.getId();
    }

    private void validateDuplicatedCustomerByLoginId(Customer customer) {
        List<Customer> findCustomersByLoginId = customerRepository.findByLoginId(customer.getLoginId());
        if (!findCustomersByLoginId.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 아이디입니다.");
        }
    }

    private void validateDuplicatedCustomerByNickname(Customer customer) {
        List<Customer> findCustomersByNickname = customerRepository.findByNickname(customer.getNickname());

        if (!findCustomersByNickname.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 닉네임입니다.");
        }
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
