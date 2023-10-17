package iampotato.iampotato.domain.order.dto;

import lombok.Data;

import java.util.Date;

@Data
public class OrderItemByWeekResponse {

    String time;

    Long id;

    String name;

    long count;

    public OrderItemByWeekResponse(Date time, Long id, String name, long count) {
        this.time = time.toString();
        this.id = id;
        this.name = name;
        this.count = count;
    }
}