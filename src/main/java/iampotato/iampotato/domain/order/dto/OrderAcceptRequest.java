package iampotato.iampotato.domain.order.dto;

import lombok.Data;

@Data
public class OrderAcceptRequest {

    private Long orderId;

    public OrderAcceptRequest(Long orderId) {
        this.orderId = orderId;
    }
}
