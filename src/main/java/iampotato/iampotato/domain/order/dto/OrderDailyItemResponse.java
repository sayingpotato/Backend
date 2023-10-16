package iampotato.iampotato.domain.order.dto;

import lombok.Data;

@Data
public class OrderDailyItemResponse {

    String day;

    Long itemId;

    String itemName;

    long itemCount;

    public OrderDailyItemResponse(String day, Long itemId, String itemName, double itemCount) {
        this.day = day;
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemCount = Math.round(itemCount);
    }
}
