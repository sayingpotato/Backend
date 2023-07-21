package iampotato.iampotato.domain.store.dto.search;

import iampotato.iampotato.domain.store.domain.Store;
import lombok.Data;

@Data
public class StoreSearchResponse {

    private String name;

    public StoreSearchResponse(Store store) {
        this.name = store.getName();
    }
}
