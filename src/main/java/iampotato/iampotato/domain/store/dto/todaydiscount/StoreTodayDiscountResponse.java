package iampotato.iampotato.domain.store.dto.todaydiscount;

import iampotato.iampotato.domain.store.domain.Store;
import iampotato.iampotato.domain.store.domain.StoreCategory;
import lombok.Data;

@Data
public class StoreTodayDiscountResponse {

    private Long id;

    private String name;

    private String storeTodayDiscountThumbnail;

    private StoreCategory category;

    public StoreTodayDiscountResponse(Store store) {
        this.id = store.getId();
        this.name = store.getName();
        this.storeTodayDiscountThumbnail = store.getStoreTodayDiscountThumbnail();
        this.category = store.getCategory();
    }
}
