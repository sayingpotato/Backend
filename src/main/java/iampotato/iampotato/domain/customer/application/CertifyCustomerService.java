package iampotato.iampotato.domain.customer.application;

import iampotato.iampotato.domain.customer.dao.CustomerRepository;
import iampotato.iampotato.domain.customer.domain.Customer;
import iampotato.iampotato.domain.customer.domain.CustomerStatus;
import iampotato.iampotato.domain.customer.exception.CustomerException;
import iampotato.iampotato.domain.customer.exception.CustomerExceptionGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CertifyCustomerService {

    private final CustomerRepository customerRepository;

    @Transactional
    public void certifyCustomer(String id) {
        Optional<Customer> findCustomersById = customerRepository.findById(id);
        Customer customer = findCustomersById.orElseThrow(() -> new CustomerException(CustomerExceptionGroup.CUSTOMER_ID_NULL));
        customer.updateCustomerStatus(CustomerStatus.COMPLETE);
    }
}