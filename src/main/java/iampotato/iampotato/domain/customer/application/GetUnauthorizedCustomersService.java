package iampotato.iampotato.domain.customer.application;


import iampotato.iampotato.domain.customer.dao.CustomerRepository;
import iampotato.iampotato.domain.customer.domain.Customer;
import iampotato.iampotato.domain.customer.dto.UnauthorizedCustomer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GetUnauthorizedCustomersService {
    private final CustomerRepository customerRepository;

    public List<Customer> getUnauthorizedCustomers() {
        List<Customer> unauthorizedCustomers = customerRepository.findUnauthorizedCustomers();
        return unauthorizedCustomers;
    }
}
