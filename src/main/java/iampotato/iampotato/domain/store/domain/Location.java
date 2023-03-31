package iampotato.iampotato.domain.store.domain;

import lombok.Getter;


@Getter
public class Location {

    // 위도 경도를 String 으로 저장한이유는 부동소수점 자료형으로 저장시 오차가 있을 수 있어서입니다.
    private double latitude;
    private double longitude;

    public Location(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
