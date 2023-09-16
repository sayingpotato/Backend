package iampotato.iampotato.domain.store.dto.detail;

import iampotato.iampotato.domain.review.domain.ReviewResultOfCafe;
import iampotato.iampotato.domain.store.domain.*;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class StoreDetailResponse {

    Long id;
    String name;
    Address address;
    String tableImg;
    List<StoreDetailStoreOperationHour> storeOperationHours;

    List<StoreDetailStoreImage> storeDetailStoreImages;

    List<StoreDetailItems> storeDetailItems;

    ReviewResultOfCafe reviewResultOfCafe;

    List<StoreDetailDiscount> discounts;

    public StoreDetailResponse(Store store) {
        this.id = store.getId();
        this.address = store.getAddress();
        this.name = store.getName();
        this.tableImg = store.getTableImg();

        this.storeOperationHours = store.getStoreOperationHours().getStoreOperationHours().stream()
                .map(StoreDetailStoreOperationHour::new)
                .collect(Collectors.toList());

        this.storeDetailStoreImages = store.getStoreImages().getStoreImages().stream()
                .map(StoreDetailStoreImage::new)
                .collect(Collectors.toList());

        this.storeDetailItems = store.getItems().getItems().stream()
                .map(StoreDetailItems::new)
                .collect(Collectors.toList());

        this.reviewResultOfCafe = store.getReviews().getReviewResultOfCafe();

        this.discounts = store.getDiscounts().getDiscounts().stream()
                .map(StoreDetailDiscount::new)
                .collect(Collectors.toList());

    }
}
