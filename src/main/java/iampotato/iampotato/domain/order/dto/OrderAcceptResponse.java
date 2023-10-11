package iampotato.iampotato.domain.order.dto;

import iampotato.iampotato.domain.order.domain.Order;
import lombok.Data;

@Data
public class OrderAcceptResponse {

    private Long orderId;

    public OrderAcceptResponse(Order order) {
        this.orderId = order.getId();
    }
}
