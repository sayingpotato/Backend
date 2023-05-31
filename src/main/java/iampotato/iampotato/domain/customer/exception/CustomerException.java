package iampotato.iampotato.domain.customer.exception;

import lombok.Getter;

@Getter
public class CustomerException extends RuntimeException{

    private final CustomerExceptionGroup customerExceptionGroup;

    public CustomerException(CustomerExceptionGroup customerExceptionGroup) {
        this.customerExceptionGroup = customerExceptionGroup;
    }

}
