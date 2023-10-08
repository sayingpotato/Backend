package iampotato.iampotato.domain.store.application;

import iampotato.iampotato.domain.discount.domain.DiscountDay;
import iampotato.iampotato.domain.owner.dao.OwnerRepository;
import iampotato.iampotato.domain.owner.domain.Owner;
import iampotato.iampotato.domain.ownerstore.application.OwnerStoreService;
import iampotato.iampotato.domain.store.dao.StoreRepository;
import iampotato.iampotato.domain.store.domain.Location;
import iampotato.iampotato.domain.store.domain.Store;
import iampotato.iampotato.domain.store.dto.register.StoreRegistrationRequest;
import iampotato.iampotato.domain.store.dto.search.StoreSearchResponse;
import iampotato.iampotato.domain.store.dto.update.StoreUpdateRequest;
import iampotato.iampotato.domain.store.exception.StoreException;
import iampotato.iampotato.domain.store.exception.StoreExceptionGroup;
import iampotato.iampotato.domain.storeoperationhour.domain.StoreOperationHour;
import iampotato.iampotato.domain.storeoperationhour.domain.StoreOperationHours;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;

    private final OwnerStoreService ownerStoreService;

    private final OwnerRepository ownerRepository;

    @Transactional
    public Long registerStore(Store store) {
        storeRepository.save(store);
        return store.getId();
    }

    @Transactional
    public Store registerStore(String userId, StoreRegistrationRequest request) {

        Store store = Store.builder()
                .name(request.getName())
                .phone(request.getPhone())
                .address(request.getAddress())
                .category(request.getCategory())
                .createdDate(LocalDateTime.now())
                .build();

        Owner owner = ownerRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 점주"));

        ownerStoreService.registerOwnerStore(store, owner);

        storeRepository.save(store);

        return store;
    }

    @Transactional
    public Store updateStore(StoreUpdateRequest request) {

        Store store = storeRepository.findById(request.getId());


        List<StoreOperationHour> hours = request.getStoreOperationHours().stream()
                .map(o -> StoreOperationHour.createStoreOperationHour(store,
                        o.getStartDay(),
                        o.getEndDay(),
                        o.getStartTime(),
                        o.getEndTime()
                )).collect(Collectors.toList());

        store.updateStore(request.getSalesType(),
                request.getStoreStatus(),
                request.getDiscountInfo(),
                request.getDescription(),
                request.getTableImg(),
                new StoreOperationHours(hours),
                request.getClosedDay(),
                request.getStoreMapThumbnail(),
                request.getStoreTodayDiscountThumbnail(),
                request.getOutletNum());

        return store;
    }

    public Store findById(Long storeId) {
        return storeRepository.findById(storeId);
    }

    public Store findByIdV2(Long storeId, int offset, int limit) {

        Optional<Store> OptionalStore = storeRepository.findByIdV2(storeId, offset, limit);

        return OptionalStore.orElseThrow(() -> new StoreException(StoreExceptionGroup.STORE_NULL));
    }


    /**
     * 애플리케이션에서 사용자 위치기준 북동쪽좌표와 남서쪽 좌표를 구해 해당 좌표를
     * DB 에 넘겨서 DB 에서 해당 범위에 들어가는 가게들을 가져옵니다.
     * 현재 코드에서 정확히 원반경으로 범위를 구할려면 쿼리를 한 번 더 날려야 됩니다. 아니면 DB 에서 연산을 추가로 해야합니다.
     * 하지만 사용자입장에서 원반경으로 정확한 범위로 가게정보를 제공하든 사각형 범위로 가게정보를 제공하든 큰 불편함은 없다고 생각합니다.
     */
    public List<Store> findStoresByLocation(Location location) {

        Location northeast = StoreMapDistance.aroundCustomerNortheastDot(location);
        Location southwest = StoreMapDistance.aroundCustomerSouthwestDot(location);

        return storeRepository.findStoresByLocation(northeast, southwest);
    }

    public List<Store> findStoresListByLocation(Location location, int offset, int limit) {

        Location northeast = StoreMapDistance.aroundCustomerNortheastDot(location);
        Location southwest = StoreMapDistance.aroundCustomerSouthwestDot(location);

        List<Store> stores = storeRepository.findStoresListByLocation(northeast, southwest, offset, limit);
        sortStoresByDistance(stores, location);

        return stores;
    }

    /**
     * 할인지도 페이지에서 리스트로 보여줄때, 가장 가까운순으로 정렬합니다.
     * 정렬 기준이 추가된다면 strategy 패턴으로 만들려고 합니다.
     */
    private void sortStoresByDistance(List<Store> stores, Location location) {

        stores.sort((o1, o2) -> (int) (StoreMapDistance.calculateDistance(location, o1) - StoreMapDistance.calculateDistance(location, o2)));
    }


    public List<Store> findStoresByDiscountDay(DiscountDay discountDay) {

        List<Store> todayDiscountStores = storeRepository.findStoresByDiscountDay(discountDay);
        sortStoresByName(todayDiscountStores);

        return todayDiscountStores;
    }

    private void sortStoresByName(List<Store> stores) {

        stores.sort(Comparator.comparing(Store::getName));
    }

    public List<StoreSearchResponse> searchStoresBy(String name) {

        List<Store> storesByStoreName = storeRepository.findStoresByStoreName(name);
        List<Store> storesByItemName = storeRepository.findStoresByItemName(name);

        if (storesByStoreName.size() == 0 && storesByItemName.size() == 0) {
            throw new StoreException(StoreExceptionGroup.STORE_NULL);
        }

        Set<Long> checkDistinct = new HashSet<>();

        List<StoreSearchResponse> responses = storesByItemName.stream()
                .map(s -> {
                    checkDistinct.add(s.getId());
                    return new StoreSearchResponse(s, false, s.getItems().findItemsByName(name));
                })
                .collect(Collectors.toList());

        List<StoreSearchResponse> responsesByStoreName = storesByStoreName.stream()
                .filter(s -> !checkDistinct.contains(s.getId()))
                .map(s -> new StoreSearchResponse(s, true, null))
                .collect(Collectors.toList());

        responses.addAll(responsesByStoreName);

        return responses;
    }

    public List<Store> searchStoresByStoreName(String name) {
        List<Store> stores = storeRepository.findStoresByStoreName(name);

        if (stores.size() == 0) {
            throw new StoreException(StoreExceptionGroup.STORE_NULL);
        }

        sortStoresByName(stores);
        return stores;
    }

    public List<Store> searchStoresByItemName(String name) {
        List<Store> stores = storeRepository.findStoresByItemName(name);

        if (stores.size() == 0) {
            throw new StoreException(StoreExceptionGroup.STORE_NULL);
        }

        sortStoresByName(stores);
        return stores;
    }
}
