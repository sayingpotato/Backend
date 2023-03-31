package iampotato.iampotato.domain.store.application;

import lombok.Getter;

/**
 * StoreMapDistance 가 GeometryUtil 을 사용할때 필요한 상수입니다.
 */
@Getter
public enum Direction {
    NORTH(0.0),
    WEST(270.0),
    SOUTH(180.0),
    EAST(90.0),
    NORTHWEST(315.0),
    SOUTHWEST(225.0),
    SOUTHEAST(135.0),
    NORTHEAST(45.0);

    private final Double bearing;

    Direction(Double bearing) {
        this.bearing = bearing;
    }
}