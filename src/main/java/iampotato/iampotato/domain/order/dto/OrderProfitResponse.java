package iampotato.iampotato.domain.order.dto;

import lombok.Data;

@Data
public class OrderProfitResponse {

    long totalCount;

    long averageCount;

    long totalPrice;

    long averagePrice;

    public OrderProfitResponse(long totalCount, double averageCount, long totalPrice, double averagePrice) {
        this.totalCount = totalCount;
        this.averageCount = Math.round(averageCount);
        this.totalPrice = totalPrice;
        this.averagePrice = Math.round(averagePrice);
    }
}
