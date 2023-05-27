package iampotato.iampotato.domain.store.dto.map;

import iampotato.iampotato.domain.store.domain.*;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 프론트엔드에서 마커에 이미지를 표시할려면 하나의 객체로 넘겨줘야 한다는 요청이 있어서 만들었습니다
 */
@Data
public class StoreMarkerInfo {

    private StoreCategory category; // 지도에서 마커이미지 카페와 음식점에 따라 다르게 하기위함

    private Address address;

    private List<StoreMarkerOperationHour> operationHour;

    private StoreDay closedDay;

    private String phone;

    private StoreStatus status;

    private StoreDiscountInfo discountInfo; // 마커에 할인일때만 색칠해주기위해서

    public StoreMarkerInfo(Store store) {

        this.category = store.getCategory();
        this.address = store.getAddress();
        this.operationHour = store.getStoreOperationHours().getStoreOperationHours().stream()
                .map(StoreMarkerOperationHour::new)
                .collect(Collectors.toList());
        this.closedDay = store.getClosedDay();
        this.phone = store.getPhone();
        this.status = store.getStoreStatus();
        this.discountInfo = store.getDiscountInfo();
    }
}
