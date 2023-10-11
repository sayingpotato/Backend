package iampotato.iampotato.domain.order.dto;

import iampotato.iampotato.domain.order.domain.Order;
import iampotato.iampotato.domain.order.domain.OrderStatus;
import iampotato.iampotato.domain.orderitem.domain.OrderItem;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class OrderOwnerResponse {

    private Long orderId;

    private int totalPrice;

    private int totalPeople;

    private LocalDateTime orderTime;

    private OrderStatus orderStatus;

    private List<OrderOwnerItem> orderOwnerItems;

    @Data
    public static class OrderOwnerItem {

        private String itemName;

        private String itemOptionName;

        private int price;

        public OrderOwnerItem(OrderItem orderItem) {
            this.itemName = orderItem.getItem().getName();
            if (orderItem.getItemOption() != null) {
                this.itemOptionName = orderItem.getItemOption().getName();
            }
            this.price = orderItem.getTotalPrice();
        }
    }

    public OrderOwnerResponse(Order order) {

        this.orderId = order.getId();
        this.totalPrice = order.getTotalPrice();
        this.totalPeople = order.getTotalPeople();
        this.orderTime = order.getCreatedDate();
        this.orderStatus = order.getOrderStatus();

        this.orderOwnerItems = order.getOrderItems().stream()
                .map(OrderOwnerItem::new)
                .collect(Collectors.toList());
    }
}
