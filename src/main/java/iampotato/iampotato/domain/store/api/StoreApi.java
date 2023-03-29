package iampotato.iampotato.domain.store.api;

import iampotato.iampotato.domain.store.application.StoreService;
import iampotato.iampotato.domain.store.domain.Store;
import iampotato.iampotato.domain.store.dto.StoreRegistrationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class StoreApi {

    private final StoreService storeService;


    /**
     * 가게 정보 등록 POST API
     * 가게 정보를 등록할려면 admin Page 에서 데이터를 받아야 하는데
     * 아직 갈길이 머므로 껍데기만 만들었습니다.
     */
//    @PostMapping("api/v1/stores")
//    public StoreRegistrationResponse registerStore(@RequestBody StoreRegistrationResponse storeRegistrationResponse) {
//
//        Store store = Store.createStoreWithRequiredValue();
//        Long id = storeService.registerStore(store);
//
//        return new StoreRegistrationResponse(id);
//    }

}
