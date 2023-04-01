package iampotato.iampotato.domain.store.application;

import iampotato.iampotato.domain.store.dao.StoreRepository;
import iampotato.iampotato.domain.store.domain.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class) // junit5 사용시 선언
@SpringBootTest
@Transactional
class StoreServiceTest {

    @Autowired
    StoreService storeService;

    @Autowired
    StoreRepository storeRepository;

    @Test
    void 가게_등록_정상_작동() {

        Store store = Store.createStoreWithRequiredValue("좋은원두",
                StoreCategory.CAFE,
                StorePaymentType.PREPAID,
                StoreSalesType.HALL,
                new Address("주소1", "주소2", 2, 5),
                "01000000000",
                StoreStatus.CLOSED,
                StoreDiscountInfo.TODAY_DISCOUNT);

        Long id = storeService.registerStore(store);
        Store findStore = storeRepository.findById(id);


        assertThat(store).isEqualTo(findStore);
    }

    @Test
    void 주변_가게_정보_정상_작동() throws ParseException {

        double latitude = 36.625169;
        double longitude = 127.455409;
        String pointWKT = String.format("POINT(%s %s)", longitude, latitude);

        // 가게 store1 ~ store3 저장, store1~store2 만 범위에 들어오게 설정
        Point point = (Point) new WKTReader().read(pointWKT);
        Store store1 = Store.createStoreWithRequiredValueWithLocation("좋은원두",
                StoreCategory.CAFE,
                StorePaymentType.PREPAID,
                StoreSalesType.HALL,
                point,
                new Address("주소1", "주소2", 2, 5),
                "01000000000",
                StoreStatus.CLOSED,
                StoreDiscountInfo.TODAY_DISCOUNT);

        latitude = 36.625172;
        longitude = 127.455410;
        pointWKT = String.format("POINT(%s %s)", longitude, latitude);
        point = (Point) new WKTReader().read(pointWKT);
        Store store2 = Store.createStoreWithRequiredValueWithLocation("나쁜원두",
                StoreCategory.CAFE,
                StorePaymentType.PREPAID,
                StoreSalesType.HALL,
                point,
                new Address("주소3", "주소4", 2, 5),
                "01000000001",
                StoreStatus.CLOSED,
                StoreDiscountInfo.TODAY_DISCOUNT);

        latitude = 32.124012;
        longitude = 127.122001;
        pointWKT = String.format("POINT(%s %s)", longitude, latitude);
        point = (Point) new WKTReader().read(pointWKT);
        Store store3 = Store.createStoreWithRequiredValueWithLocation("착한원두",
                StoreCategory.CAFE,
                StorePaymentType.PREPAID,
                StoreSalesType.HALL,
                point,
                new Address("주소5", "주소6", 2, 5),
                "01000000300",
                StoreStatus.CLOSED,
                StoreDiscountInfo.TODAY_DISCOUNT);

        storeService.registerStore(store1);
        storeService.registerStore(store2);
        storeService.registerStore(store3);
        // =========================================

        // 0.00001 당 대략 11m 정도 거리차이가 있습니다.
        // 그래서 아래 기준에서 store1, store2 가게만 범위에 들어갑니다.
        double baseLatitude = 36.625169;
        double baseLongitude = 127.455409;

        List<Store> result = storeService.findStoresByLocation(new Location(baseLatitude, baseLongitude));
        assertThat(result).contains(store1, store2)
                .doesNotContain(store3);
    }
}