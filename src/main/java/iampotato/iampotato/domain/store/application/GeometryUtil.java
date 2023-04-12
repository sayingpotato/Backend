package iampotato.iampotato.domain.store.application;

import iampotato.iampotato.domain.store.domain.Location;

/**
 * 위도, 경도, 거리, 방향이 주어지면
 * 주어진 위도,경도 위치에서 최대거리가 되는 방향의 좌표를 얻을 수 있습니다.
 */
public class GeometryUtil {

    private static final double EARTH_RADIUS = 6371.01;

    public static Location calculateByDirection(double baseLatitude, double baseLongitude,
                                                double distance, double bearing) {
        double radianLatitude = toRadian(baseLatitude);
        double radianLongitude = toRadian(baseLongitude);
        double radianAngle = toRadian(bearing);
        double distanceRadius = distance / EARTH_RADIUS;

        double latitude = Math.asin(Math.sin(radianLatitude) * Math.cos(distanceRadius) +
                Math.cos(radianLatitude) * Math.sin(distanceRadius) * Math.cos(radianAngle));
        double longitude = radianLongitude + Math.atan2(Math.sin(radianAngle) * Math.sin(distanceRadius) *
                Math.cos(radianLatitude), Math.cos(distanceRadius) - Math.sin(radianLatitude) * Math.sin(latitude));

        longitude = normalizeLongitude(longitude);
        return new Location(toDegree(latitude), toDegree(longitude));
    }

    public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        double dLat = toRadian(lat2 - lat1);
        double dLon = toRadian(lon2 - lon1);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(toRadian(lat1)) * Math.cos(toRadian(lat2)) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double b = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return EARTH_RADIUS * b * 1000; //m
    }

    private static double toRadian(double coordinate) {
        return coordinate * Math.PI / 180.0;
    }

    private static double toDegree(double coordinate) {
        return coordinate * 180.0 / Math.PI;
    }

    private static double normalizeLongitude(double longitude) {
        return (longitude + 540) % 360 - 180;
    }
}