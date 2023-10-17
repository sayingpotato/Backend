package iampotato.iampotato.domain.order.dto;

import lombok.Data;

@Data
public class OrderMonthlyProfitResponse {

    String time;

    long profit;

    public OrderMonthlyProfitResponse(String time, long profit) {
        this.time = time;
        this.profit = profit;
    }
}
