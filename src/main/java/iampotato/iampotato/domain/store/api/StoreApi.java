package iampotato.iampotato.domain.store.api;

import iampotato.iampotato.domain.store.application.StoreService;
import iampotato.iampotato.domain.store.domain.Location;
import iampotato.iampotato.domain.store.domain.Store;
import iampotato.iampotato.domain.store.dto.StoreMapRequest;
import iampotato.iampotato.domain.store.dto.StoreMapResponse;
import iampotato.iampotato.domain.store.dto.StoreRegistrationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


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

    /**
     * 위치정보를 받으면 해당 위치에서 특정반경 이내에 있는 가게정보를 반환합니다.
     * 마커클릭시 띄워주는 info 창 정보까지만 반환합니다.
     */
    @GetMapping("api/v1/stores/nearby")
    public List<StoreMapResponse> findStoresByLocation(StoreMapRequest storeMapRequest) {

        Location location = new Location(storeMapRequest.getLatitude(), storeMapRequest.getLongitude());
        List<Store> stores = storeService.findStoresByLocation(location);

        return stores.stream()
                .map(StoreMapResponse::new)
                .collect(Collectors.toList());
    }

}
