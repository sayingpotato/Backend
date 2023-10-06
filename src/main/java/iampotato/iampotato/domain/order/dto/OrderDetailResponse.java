package iampotato.iampotato.domain.order.dto;

import iampotato.iampotato.domain.order.domain.Order;
import iampotato.iampotato.domain.order.domain.OrderStatus;
import iampotato.iampotato.domain.orderitem.domain.OrderItem;
import iampotato.iampotato.domain.review.domain.Review;
import iampotato.iampotato.domain.review.domain.ReviewDetail;
import iampotato.iampotato.domain.review.domain.ReviewStatus;
import iampotato.iampotato.domain.store.domain.Store;
import iampotato.iampotato.domain.store.domain.StoreCategory;
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

    private StoreInfo storeInfo;

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

        private String greatCoffeeContent;

        private int greatBeverage;

        private String greatBeverageContent;

        private int greatFood;

        private String greatFoodContent;

        private int manyOutlet;

        private String manyOutletContent;

        public OrderDetailReview(Review review) {
            this.reviewStatus = review.getReviewStatus();
            this.greatCoffee = review.getReviewDetailCount(ReviewDetail.GREAT_COFFEE);
            this.greatCoffeeContent = review.getReviewDetailContent(ReviewDetail.GREAT_COFFEE);
            this.greatBeverage = review.getReviewDetailCount(ReviewDetail.GREAT_BEVERAGE);
            this.greatBeverageContent = review.getReviewDetailContent(ReviewDetail.GREAT_BEVERAGE);
            this.greatFood = review.getReviewDetailCount(ReviewDetail.GREAT_FOOD);
            this.greatFoodContent = review.getReviewDetailContent(ReviewDetail.GREAT_FOOD);
            this.manyOutlet = review.getReviewDetailCount(ReviewDetail.MANY_OUTLET);
            this.manyOutletContent = review.getReviewDetailContent(ReviewDetail.MANY_OUTLET);
        }
    }

    @Data
    public class StoreInfo {

        private StoreCategory category;

        private String thumbnail;

        public StoreInfo(Store store) {
            this.category = store.getCategory();
            this.thumbnail = store.getStoreTodayDiscountThumbnail();
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
        this.storeInfo = new StoreInfo(order.getReview().getStore());
    }
}
