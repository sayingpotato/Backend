package iampotato.iampotato.domain.store.application;

import iampotato.iampotato.domain.store.dao.StoreRepository;
import iampotato.iampotato.domain.store.domain.Location;
import iampotato.iampotato.domain.store.domain.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;

    @Transactional
    public Long registerStore(Store store) {
        storeRepository.save(store);
        return store.getId();
    }

    public Store findById(Long storeId) {
        return storeRepository.findById(storeId);
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

        return storeRepository.findByLocation(northeast, southwest);
    }

    public List<Store> findStoresListByLocation(Location location, int offset, int limit) {

        Location northeast = StoreMapDistance.aroundCustomerNortheastDot(location);
        Location southwest = StoreMapDistance.aroundCustomerSouthwestDot(location);

        List<Store> stores = storeRepository.findByLocationWithList(northeast, southwest, offset, limit);
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
}
