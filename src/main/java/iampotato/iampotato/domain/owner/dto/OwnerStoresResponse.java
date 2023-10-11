package iampotato.iampotato.domain.owner.dto;

import iampotato.iampotato.domain.owner.domain.Owner;
import iampotato.iampotato.domain.store.domain.Store;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class OwnerStoresResponse {

    private String ownerId;

    private List<StoreInfo> storeInfos;

    public OwnerStoresResponse(Owner owner) {
        this.ownerId = owner.getId();
        this.storeInfos = owner.getOwnerStores().stream()
                .map(os -> new StoreInfo(os.getStore()))
                .collect(Collectors.toList());
    }

    @Data
    static class StoreInfo {

        private String name;

        private String description;

        private String thumbnail;

        private Long storeId;

        public StoreInfo(Store store) {
            this.name = store.getName();
            this.description = store.getDescription();
            this.thumbnail = store.getStoreTodayDiscountThumbnail();
            this.storeId = store.getId();
        }
    }
}
