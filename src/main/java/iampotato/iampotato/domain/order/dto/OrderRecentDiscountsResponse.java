package iampotato.iampotato.domain.order.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderRecentDiscountsResponse {

    private int discountPrice;

    private String storeName;

    private LocalDateTime time;

    public OrderRecentDiscountsResponse(int discountPrice, String storeName, LocalDateTime time) {
        this.discountPrice = discountPrice;
        this.storeName = storeName;
        this.time = time;
    }
}
