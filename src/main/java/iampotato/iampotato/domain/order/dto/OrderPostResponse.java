package iampotato.iampotato.domain.order.dto;

import iampotato.iampotato.domain.order.domain.Order;
import lombok.Data;

@Data
public class OrderPostResponse {

    private Long id;

    public OrderPostResponse(Order order) {
        this.id = order.getId();
    }
}
