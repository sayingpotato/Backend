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

        private Long id;

        private int greatCoffee;

        private String greatCoffeeContent;

        private int greatBeverage;

        private String greatBeverageContent;

        private int greatFood;

        private String greatFoodContent;

        private int manyOutlet;

        private String manyOutletContent;

        private int goodMood;

        private String goodMoodContent;

        private int goodPrice;

        private String goodPriceContent;

        private int goodWifi;

        private String goodWifiContent;

        private int goodDesert;

        private String goodDesertContent;

        private int quietMood;

        private String quietMoodContent;

        private int cleanToilet;

        private String cleanToiletContent;

        private int goodService;

        private String goodServiceContent;

        private int goodKind;

        private String goodKingContent;

        public OrderDetailReview(Review review) {
            this.reviewStatus = review.getReviewStatus();
            this.id = review.getId();
            this.greatCoffee = review.getReviewDetailCount(ReviewDetail.GREAT_COFFEE);
            this.greatCoffeeContent = review.getReviewDetailContent(ReviewDetail.GREAT_COFFEE);
            this.greatBeverage = review.getReviewDetailCount(ReviewDetail.GREAT_BEVERAGE);
            this.greatBeverageContent = review.getReviewDetailContent(ReviewDetail.GREAT_BEVERAGE);
            this.greatFood = review.getReviewDetailCount(ReviewDetail.GREAT_FOOD);
            this.greatFoodContent = review.getReviewDetailContent(ReviewDetail.GREAT_FOOD);
            this.manyOutlet = review.getReviewDetailCount(ReviewDetail.MANY_OUTLET);
            this.manyOutletContent = review.getReviewDetailContent(ReviewDetail.MANY_OUTLET);
            this.goodMood = review.getReviewDetailCount(ReviewDetail.GOOD_MOOD);
            this.goodMoodContent = review.getReviewDetailContent(ReviewDetail.GOOD_MOOD);
            this.goodPrice = review.getReviewDetailCount(ReviewDetail.GOOD_PRICE);
            this.goodPriceContent = review.getReviewDetailContent(ReviewDetail.GOOD_PRICE);
            this.goodWifi = review.getReviewDetailCount(ReviewDetail.GOOD_WIFI);
            this.goodWifiContent = review.getReviewDetailContent(ReviewDetail.GOOD_WIFI);
            this.goodDesert = review.getReviewDetailCount(ReviewDetail.GOOD_DESERT);
            this.goodDesertContent = review.getReviewDetailContent(ReviewDetail.GOOD_DESERT);
            this.quietMood = review.getReviewDetailCount(ReviewDetail.QUIET_MOOD);
            this.quietMoodContent = review.getReviewDetailContent(ReviewDetail.QUIET_MOOD);
            this.cleanToilet = review.getReviewDetailCount(ReviewDetail.CLEAN_TOILET);
            this.cleanToiletContent = review.getReviewDetailContent(ReviewDetail.CLEAN_TOILET);
            this.goodService = review.getReviewDetailCount(ReviewDetail.GOOD_SERVICE);
            this.goodServiceContent = review.getReviewDetailContent(ReviewDetail.GOOD_SERVICE);
            this.goodKind = review.getReviewDetailCount(ReviewDetail.GOOD_KIND);
            this.goodKingContent = review.getReviewDetailContent(ReviewDetail.GOOD_KIND);
        }
    }

    @Data
    public static class StoreInfo {

        private StoreCategory category;

        private String thumbnail;

        private String storeName;

        public StoreInfo(Store store) {
            this.category = store.getCategory();
            this.thumbnail = store.getStoreTodayDiscountThumbnail();
            this.storeName = store.getName();
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
