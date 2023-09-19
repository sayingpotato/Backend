package iampotato.iampotato.domain.store.dto.maplist;

import iampotato.iampotato.domain.store.domain.*;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 할인지도 페이지에서 리스트보기 클릭시 반환 가게정보 DTO
 */
@Data
public class StoreMapListResponse {

    private Long id;

    private String name;

    private StoreMapThumbnail storeMapThumbnail;

    private Location location;

    private List<StoreMapListDiscount> discounts;

    private StoreTopItem storeTopItem;

    private StoreTopReview storeTopReview;

    private int maxDiscountRatio;

    private int minDiscountRatio;

    public StoreMapListResponse(Store store) {

        this.id = store.getId();
        this.name = store.getName();
        this.storeMapThumbnail = store.getStoreMapThumbnail();
        this.location = new Location(store.getLocation().getY(), store.getLocation().getX());
        this.maxDiscountRatio = store.maxDiscountRatio().getDiscountRatio();
        this.minDiscountRatio = store.minDiscountRatio().getDiscountRatio();

        this.discounts = store.getDiscounts().getDiscounts().stream()
                .map(StoreMapListDiscount::new)
                .collect(Collectors.toList());

        this.storeTopItem = store.getStoreTopItem();
        this.storeTopReview = store.getStoreTopReview();
    }
}
