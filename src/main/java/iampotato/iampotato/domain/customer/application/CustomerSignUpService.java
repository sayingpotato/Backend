package iampotato.iampotato.domain.customer.application;

import iampotato.iampotato.domain.customer.dao.CustomerRepository;
import iampotato.iampotato.domain.customer.domain.Customer;
import iampotato.iampotato.domain.customer.exception.CustomerException;
import iampotato.iampotato.domain.customer.exception.CustomerExceptionGroup;
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
    public String signUp(Customer customer) throws Exception{
        validateDuplicatedCustomerByLoginId(customer);
        validateDuplicatedCustomerByNickname(customer);
        customerRepository.save(customer);
        return customer.getId();
    }

    private void validateDuplicatedCustomerByLoginId(Customer customer) {
        List<Customer> findCustomersByLoginId = customerRepository.findByLoginId(customer.getLoginId());
        if (!findCustomersByLoginId.isEmpty()) {
            throw new CustomerException(CustomerExceptionGroup.CUSTOMER_DUPLICATED_ID);
        }
    }

    private void validateDuplicatedCustomerByNickname(Customer customer) {
        List<Customer> findCustomersByNickname = customerRepository.findByNickname(customer.getNickname());

        if (!findCustomersByNickname.isEmpty()) {
            throw new CustomerException(CustomerExceptionGroup.CUSTOMER_DUPLICATED_NICKNAME);
        }
    }

    //손님 전체 조회
    public List<Customer> findCustomers() {
        return customerRepository.findAll();
    }

    //id를 사용한 손님 단건 조회
    public Customer findOne(String customerId) {
        return customerRepository.findOne(customerId);
    }



}
