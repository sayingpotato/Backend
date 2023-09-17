package iampotato.iampotato.domain.store.dto.map;

import iampotato.iampotato.domain.store.domain.*;
import lombok.Data;

/**
 * 할인 지도 페이지에서 마커 정보 및 마커 클릭시  DTO
 */
@Data
public class StoreMapResponse {

    private Long id;

    private Location location;

    private String name;

    private StoreMarkerInfo markerInfo;

    public StoreMapResponse(Store store) {
        this.id = store.getId();

        // Point 자료형은 json 인식이 불가능하기때문에 Location 자료형으로 변환해주어야 합니다.
        this.location = new Location(store.getLocation().getY(), store.getLocation().getX());

        this.name = store.getName();

        markerInfo = new StoreMarkerInfo(store);
    }
}
