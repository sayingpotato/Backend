package iampotato.iampotato.domain.order.dto;


import lombok.Data;

import java.util.List;

@Data
public class OrderPostRequest {

    private Long storeId;

    private int totalPrice;

    private int totalPeople;

    private int discountPrice;

    private List<Long> itemIds;

    private List<Long> itemOptionIds;

    public OrderPostRequest(Long storeId, int totalPrice, int totalPeople, int discountPrice, List<Long> itemIds, List<Long> itemOptionIds) {
        this.storeId = storeId;
        this.totalPrice = totalPrice;
        this.totalPeople = totalPeople;
        this.discountPrice = discountPrice;
        this.itemIds = itemIds;
        this.itemOptionIds = itemOptionIds;
    }
}

