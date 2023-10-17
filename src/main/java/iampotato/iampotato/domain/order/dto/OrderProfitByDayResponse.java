package iampotato.iampotato.domain.order.dto;

import lombok.Data;

@Data
public class OrderProfitByDayResponse {

    long count;

    long sum;

    public OrderProfitByDayResponse(long count, long sum) {
        this.count = count;
        this.sum = sum;
    }
}
