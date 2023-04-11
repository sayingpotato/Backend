package iampotato.iampotato.domain.store.api;

import iampotato.iampotato.domain.store.application.StoreService;
import iampotato.iampotato.domain.store.domain.Location;
import iampotato.iampotato.domain.store.domain.Store;
import iampotato.iampotato.domain.store.dto.*;
import iampotato.iampotato.global.util.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    public Result<List<StoreMapResponse>> findStoresByLocation(StoreMapRequest storeMapRequest) {

        Location location = new Location(storeMapRequest.getLatitude(), storeMapRequest.getLongitude());
        List<Store> stores = storeService.findStoresByLocation(location);

        List<StoreMapResponse> storeMapResponses = stores.stream()
                .map(StoreMapResponse::new)
                .collect(Collectors.toList());

        return new Result<>(Result.CODE_SUCCESS, Result.MESSAGE_OK, storeMapResponses);
    }

    /**
     * 위치정보를 받으면 해당 위치에서 특정반경 이내에 있는 가게정보를 반환합니다.
     * 리스트보기 클릭시 띄워주는 상세정보를 반환합니다.
     */
    @GetMapping("api/v1/stores/nearby/list")
    public Result<List<StoreMapListResponse>> findStoresByLocationWithList(StoreMapListRequest storeMapListRequest,
                                                                           @RequestParam(value = "offset", defaultValue = "0") int offset,
                                                                           @RequestParam(value = "limit", defaultValue = "100") int limit) {

        Location location = new Location(storeMapListRequest.getLatitude(), storeMapListRequest.getLongitude());
        List<Store> stores = storeService.findStoresListByLocation(location, offset, limit);

        List<StoreMapListResponse> storeMapListResponse = stores.stream()
                .map(StoreMapListResponse::new)
                .collect(Collectors.toList());

        return new Result<>(Result.CODE_SUCCESS, Result.MESSAGE_OK, storeMapListResponse);
    }

}
