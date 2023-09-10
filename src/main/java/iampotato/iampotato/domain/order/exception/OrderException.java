package iampotato.iampotato.domain.order.exception;

import lombok.Getter;

@Getter
public class OrderException extends RuntimeException{

    private final OrderExceptionGroup orderExceptionGroup;

    public OrderException(OrderExceptionGroup orderExceptionGroup) {
        this.orderExceptionGroup = orderExceptionGroup;
    }
}
