package iampotato.iampotato.domain.store.application;

import iampotato.iampotato.domain.store.domain.Location;
import iampotato.iampotato.domain.store.domain.Store;


/**
 * 위도, 경도를 이용한 거리 계산을 위한 클래스
 */
public class StoreMapDistance {

    // 탐색 거리 (km 단위)
    private static final double SEARCH_DISTANCE = 1.0;

    /**
     * 기준 지점에서 최대 반경이 되는 북동쪽 좌표를 반환합니다.
     */
    public static Location aroundCustomerNortheastDot(Location customerLocation) {

        double nowLatitude = customerLocation.getLatitude(); // 현재 위도 = y 좌표
        double nowLongitude = customerLocation.getLongitude(); // 현재 경도 = x 좌표

        return GeometryUtil.calculateByDirection(nowLatitude, nowLongitude, SEARCH_DISTANCE, Direction.NORTHEAST.getBearing());
    }

    /**
     * 기준 지점에서 최대 거리가 되는 남서쪽 좌표를 반환합니다.
     */
    public static Location aroundCustomerSouthwestDot(Location customerLocation) {

        double nowLatitude = customerLocation.getLatitude(); // 현재 위도 = y 좌표
        double nowLongitude = customerLocation.getLongitude(); // 현재 경도 = x 좌표

        return GeometryUtil.calculateByDirection(nowLatitude, nowLongitude, SEARCH_DISTANCE, Direction.SOUTHWEST.getBearing());
    }

    public static double calculateDistance(Location customerLocation, Store store) {

        double nowLatitude = customerLocation.getLatitude(); // 현재 위도 = y 좌표
        double nowLongitude = customerLocation.getLongitude(); // 현재 경도 = x 좌표

        double storeLatitude = store.getLocation().getY(); // 현재 위도 = y 좌표
        double storeLongitude = store.getLocation().getX(); // 현재 경도 = x 좌표

        return GeometryUtil.calculateDistance(nowLatitude, nowLongitude, storeLatitude, storeLongitude);
    }
}