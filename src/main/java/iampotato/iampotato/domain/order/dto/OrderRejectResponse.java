package iampotato.iampotato.domain.order.dto;

import iampotato.iampotato.domain.order.domain.Order;
import lombok.Data;

@Data
public class OrderRejectResponse {

    private Long id;

    public OrderRejectResponse(Order order) {
        this.id = order.getId();
    }
}
