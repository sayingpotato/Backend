package iampotato.iampotato.domain.store.dto.update;

import iampotato.iampotato.domain.store.domain.Store;
import lombok.Data;

@Data
public class StoreUpdateResponse {

    private Long id;

    public StoreUpdateResponse(Store store) {
        this.id = store.getId();
    }
}
