package iampotato.iampotato.domain.store.dto.search;

import iampotato.iampotato.domain.store.domain.Store;
import lombok.Data;

@Data
public class StoreSearchResponse {

    private Long id;

    private String name;

    public StoreSearchResponse(Store store) {
        this.id = store.getId();
        this.name = store.getName();
    }
}
