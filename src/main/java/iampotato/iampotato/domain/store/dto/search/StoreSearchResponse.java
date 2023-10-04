package iampotato.iampotato.domain.store.dto.search;

import iampotato.iampotato.domain.store.domain.Store;
import lombok.Data;

import java.util.List;

@Data
public class StoreSearchResponse {

    private Long id;

    private String name;

    private String thumbnail;

    boolean isFindByStore;

    private List<String> itemNames;

    public StoreSearchResponse(Store store, boolean isFindByStore, List<String> itemNames) {
        this.id = store.getId();
        this.name = store.getName();
        this.thumbnail = store.getStoreTodayDiscountThumbnail();
        this.isFindByStore = isFindByStore;
        this.itemNames = itemNames;
    }
}
