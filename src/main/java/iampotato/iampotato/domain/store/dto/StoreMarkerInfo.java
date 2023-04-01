package iampotato.iampotato.domain.store.dto;

import iampotato.iampotato.domain.store.domain.Address;
import iampotato.iampotato.domain.store.domain.StoreCategory;
import iampotato.iampotato.domain.store.domain.StoreDiscountInfo;
import iampotato.iampotato.domain.store.domain.StoreStatus;
import lombok.Data;

/**
 * 프론트엔드에서 마커에 이미지를 표시할려면 하나의 객체로 넘겨줘야 한다는 요청이 있어서 만들었습니다
 */
@Data
public class StoreMarkerInfo {

    private StoreCategory category; // 지도에서 마커이미지 카페와 음식점에 따라 다르게 하기위함

    private Address address;

    private String operationHour;

    private String closedDay;

    private String phone;

    private StoreStatus status;

    private StoreDiscountInfo discountInfo; // 마커에 할인일때만 색칠해주기위해서

    public StoreMarkerInfo(StoreCategory storeCategory,
                           Address address,
                           String operationHour,
                           String closedDay,
                           String phone,
                           StoreStatus status,
                           StoreDiscountInfo discountInfo) {

        this.category = storeCategory;
        this.address = address;
        this.operationHour = operationHour;
        this.closedDay = closedDay;
        this.phone = phone;
        this.status = status;
        this.discountInfo = discountInfo;
    }
}
