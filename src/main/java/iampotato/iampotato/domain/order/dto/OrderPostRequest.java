package iampotato.iampotato.domain.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class OrderPostRequest {

    private Long storeId;

    private int totalPrice;

    private int totalPeople;

    private int discountPrice;

    private List<Long> itemIds;

    private List<Long> itemOptionIds;
}

