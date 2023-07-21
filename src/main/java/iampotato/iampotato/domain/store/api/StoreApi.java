package iampotato.iampotato.domain.store.api;

import iampotato.iampotato.domain.store.application.StoreService;
import iampotato.iampotato.domain.store.domain.Location;
import iampotato.iampotato.domain.store.domain.Store;
import iampotato.iampotato.domain.store.dto.detail.StoreDetailRequest;
import iampotato.iampotato.domain.store.dto.detail.StoreDetailResponse;
import iampotato.iampotato.domain.store.dto.map.StoreMapRequest;
import iampotato.iampotato.domain.store.dto.map.StoreMapResponse;
import iampotato.iampotato.domain.store.dto.maplist.StoreMapListRequest;
import iampotato.iampotato.domain.store.dto.maplist.StoreMapListResponse;
import iampotato.iampotato.domain.store.dto.search.StoreSearchRequest;
import iampotato.iampotato.domain.store.dto.search.StoreSearchResponse;
import iampotato.iampotato.domain.store.dto.todaydiscount.StoreTodayDiscountRequest;
import iampotato.iampotato.domain.store.dto.todaydiscount.StoreTodayDiscountResponse;
import iampotato.iampotato.global.util.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
    @Tag(name = "할인 지도 페이지")
    @Operation(summary = "할인 지도", description = "맨처음 할인 지도 페이지에 사용자 거리 기준 일정 거리안에 있는 띄워줄 가게 정보를 반환합니다.")
    @GetMapping("api/v1/stores/nearby")
    public Result<List<StoreMapResponse>> findStoresByLocationForDiscountMap(StoreMapRequest storeMapRequest) {

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
    @Tag(name = "할인 지도 페이지")
    @Operation(summary = "할인 지도 리스트", description = "할인지도에서 리스트버튼 클릭시 보여주는 리스트에서의 가게 정보를 반환합니다.")
    @GetMapping("api/v1/stores/nearby/list")
    public Result<List<StoreMapListResponse>> findStoresByLocationForDiscountMapList(StoreMapListRequest storeMapListRequest,
                                                                                     @RequestParam(value = "offset", defaultValue = "0") int offset,
                                                                                     @RequestParam(value = "limit", defaultValue = "100") int limit) {

        Location location = new Location(storeMapListRequest.getLatitude(), storeMapListRequest.getLongitude());
        List<Store> stores = storeService.findStoresListByLocation(location, offset, limit);

        List<StoreMapListResponse> storeMapListResponse = stores.stream()
                .map(StoreMapListResponse::new)
                .collect(Collectors.toList());

        return new Result<>(Result.CODE_SUCCESS, Result.MESSAGE_OK, storeMapListResponse);
    }


    /**
     * 요일을 받으면 해당 요일에 할인하는 가게정보를 가져옵니다.
     */
    @Tag(name = "오늘의 할인 페이지")
    @Operation(summary = "할인되는가게", description = "가게 할인되는가게의 정보를 가져옵니다.")
    @GetMapping("api/v1/stores/discount")
    public Result<List<StoreTodayDiscountResponse>> findStoresByDiscountDayForTodayDiscount(StoreTodayDiscountRequest storeTodayDiscountRequest) {

        List<Store> todayDiscountStores = storeService.findStoresByDiscountDay(storeTodayDiscountRequest.getDay());

        List<StoreTodayDiscountResponse> storeTodayDiscountResponses = todayDiscountStores.stream()
                .map(StoreTodayDiscountResponse::new)
                .collect(Collectors.toList());

        return new Result<>(Result.CODE_SUCCESS, Result.MESSAGE_OK, storeTodayDiscountResponses);
    }

    @Tag(name = "가게세부정보")
    @Operation(summary = "가게세부정보", description = "가게 전체 정보를 가져오는 api 입니다.")
    @GetMapping("api/v1/stores/detail")
    public Result<StoreDetailResponse> findStoreDetail(StoreDetailRequest storeDetailRequest,
                                                                        @RequestParam(value = "offset", defaultValue = "0") int offset,
                                                                        @RequestParam(value = "limit", defaultValue = "100") int limit) {

        Store store = storeService.findByIdV2(storeDetailRequest.getId(), offset, limit);
        StoreDetailResponse storeDetailResponse = new StoreDetailResponse(store);

        return new Result<>(Result.CODE_SUCCESS, Result.MESSAGE_OK, storeDetailResponse);
    }

    @Tag(name = "가게검색")
    @Operation(summary = "가게 및 아이템 검색", description = "검색어 기준 가게이름과 아이템이름에 매칭되는것을 반환하는 api 입니다.")
    @GetMapping("api/v1/stores/name")
    public Result<List<StoreSearchResponse>> searchStoreBy(StoreSearchRequest storeSearchRequest,
                                                     @RequestParam(value = "offset", defaultValue = "0") int offset,
                                                     @RequestParam(value = "limit", defaultValue = "100") int limit) {

        List<Store> stores = storeService.searchStoresBy(storeSearchRequest.getName());
        List<StoreSearchResponse> storeSearchResponses = stores.stream()
                .map(StoreSearchResponse::new)
                .collect(Collectors.toList());

        return new Result<>(Result.CODE_SUCCESS, Result.MESSAGE_OK, storeSearchResponses);
    }
}
