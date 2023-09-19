package iampotato.iampotato.domain.store.dto.register;


import iampotato.iampotato.domain.store.domain.Store;

public class StoreRegistrationResponse {

    Long id;

    public StoreRegistrationResponse(Store store) {
        this.id = store.getId();
    }
}
