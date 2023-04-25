package iampotato.iampotato.domain.store.dto;

import iampotato.iampotato.domain.store.domain.Store;
import lombok.Data;

@Data
public class StoreTodayDiscountResponse {

    private String name;

    private String storeImg;

    public StoreTodayDiscountResponse(Store store) {
        this.name = store.getName();
        this.storeImg = store.getStoreImg();
    }
}
