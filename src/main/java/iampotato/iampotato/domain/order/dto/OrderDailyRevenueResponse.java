package iampotato.iampotato.domain.order.dto;

import lombok.Data;

@Data
public class OrderDailyRevenueResponse {

    String day;

    String time;

    long orderCount;

    long revenue;

    public OrderDailyRevenueResponse(String day, String time, long orderCount, long revenue) {
        this.day = day;
        this.time = time;
        this.orderCount = orderCount;
        this.revenue = revenue;
    }
}
