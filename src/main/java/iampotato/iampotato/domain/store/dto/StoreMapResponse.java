package iampotato.iampotato.domain.store.dto;

import iampotato.iampotato.domain.store.domain.*;
import lombok.Data;

/**
 * 할인 지도 페이지에서 마커 정보 및 마커 클릭시  DTO
 */
@Data
public class StoreMapResponse {

    private Long id;

    private StoreCategory storeCategory; // 지도에서 마커이미지 카페와 음식점에 따라 다르게 하기위함

    private Location location;

    private Address address;

    private String operationHour;

    private String closedDay;

    private String phone;

    private StoreStatus storeStatus;

    private StoreDiscountInfo discountInfo; // 마커에 할인일때만 색칠해주기위해서

    public StoreMapResponse(Store store) {
        this.id = store.getId();
        this.storeCategory = store.getCategory();

        // Point 자료형은 json 인식이 불가능하기때문에 Location 자료형으로 변환해주어야 합니다.
        this.location = new Location(store.getLocation().getX(), store.getLocation().getY());

        this.address = store.getAddress();
        this.operationHour = store.getOperationHour();
        this.closedDay = store.getClosedDay();
        this.phone = store.getPhone();
        this.storeStatus = store.getStoreStatus();
        this.discountInfo = store.getDiscountInfo();
    }
}
