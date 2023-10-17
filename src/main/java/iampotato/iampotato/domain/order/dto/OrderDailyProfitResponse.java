package iampotato.iampotato.domain.order.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class OrderDailyProfitResponse {

    String time;

    long profit;

    long count;

    public OrderDailyProfitResponse(Date time, long profit, long count) {
        this.time = time.toString();
        this.profit = profit;
        this.count = count;
    }
}
