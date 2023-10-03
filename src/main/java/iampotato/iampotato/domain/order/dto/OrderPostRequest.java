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
}

