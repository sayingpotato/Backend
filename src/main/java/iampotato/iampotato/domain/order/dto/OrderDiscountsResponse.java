package iampotato.iampotato.domain.order.dto;

import lombok.Data;

@Data
public class OrderDiscountsResponse {

    Long totalDiscountPrice;

    public OrderDiscountsResponse(Long totalDiscountPrice) {
        this.totalDiscountPrice = totalDiscountPrice;
    }
}
