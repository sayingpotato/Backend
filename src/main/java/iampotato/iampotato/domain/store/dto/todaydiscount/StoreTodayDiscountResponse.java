package iampotato.iampotato.domain.store.dto.todaydiscount;

import iampotato.iampotato.domain.store.domain.Store;
import lombok.Data;

@Data
public class StoreTodayDiscountResponse {

    private String name;

    private String storeTodayDiscountThumbnail;

    public StoreTodayDiscountResponse(Store store) {
        this.name = store.getName();
        this.storeTodayDiscountThumbnail = store.getStoreTodayDiscountThumbnail();
    }
}
