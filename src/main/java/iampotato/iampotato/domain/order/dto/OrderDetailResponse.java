package iampotato.iampotato.domain.order.dto;

import iampotato.iampotato.domain.order.domain.Order;
import iampotato.iampotato.domain.order.domain.OrderStatus;
import iampotato.iampotato.domain.orderitem.domain.OrderItem;
import iampotato.iampotato.domain.review.domain.Review;
import iampotato.iampotato.domain.review.domain.ReviewStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class OrderDetailResponse {

    private int totalPrice;

    private int totalPeople;

    private LocalDateTime orderTime;

    private OrderStatus orderStatus;

    private List<OrderDetailOrderItem> orderDetailOrderItems;

    private OrderDetailReview orderDetailReview;

    @Data
    public static class OrderDetailOrderItem {

        private String itemName;

        private String itemOptionName;

        private int price;

        public OrderDetailOrderItem(OrderItem orderItem) {
            this.itemName = orderItem.getItem().getName();
            if (orderItem.getItemOption() != null) {
                this.itemOptionName = orderItem.getItemOption().getName();
            }
            this.price = orderItem.getTotalPrice();
        }
    }

    @Data
    public static class OrderDetailReview {

        private ReviewStatus reviewStatus;

        private int greatCoffee;

        private int greatBeverage;

        private int greatFood;

        private int manyOutlet;

        public OrderDetailReview(Review review) {
            this.reviewStatus = review.getReviewStatus();
            this.greatCoffee = review.getGreatCoffee();
            this.greatBeverage = review.getGreatBeverage();
            this.greatFood = review.getGreatFood();
            this.manyOutlet = review.getManyOutlet();
        }
    }

    public OrderDetailResponse(Order order) {

        this.totalPrice = order.getTotalPrice();
        this.totalPeople = order.getTotalPeople();
        this.orderTime = order.getCreatedDate();
        this.orderStatus = order.getOrderStatus();

        this.orderDetailOrderItems = order.getOrderItems().stream()
                .map(OrderDetailOrderItem::new)
                .collect(Collectors.toList());

        this.orderDetailReview = new OrderDetailReview(order.getReview());
    }
}
