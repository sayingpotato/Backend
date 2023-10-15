package iampotato.iampotato.domain.order.dto;

import lombok.Data;

@Data
public class OrderRejectRequest {

    private Long orderId;

    public OrderRejectRequest(Long orderId) {
        this.orderId = orderId;
    }
}
