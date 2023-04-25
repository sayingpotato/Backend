package iampotato.iampotato.domain.store.application;

import iampotato.iampotato.domain.discount.application.DiscountService;
import iampotato.iampotato.domain.discount.domain.Discount;
import iampotato.iampotato.domain.discount.domain.DiscountDay;
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

import java.util.ArrayList;
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

    @Autowired
    DiscountService discountService;

    @Test
    void 가게_등록_정상_작동() {

        Store store = Store.builder()
                .name("좋은원두")
                .category(StoreCategory.CAFE)
                .paymentType(StorePaymentType.PREPAID)
                .salesType(StoreSalesType.HALL)
                .address(new Address("주소1", "주소2", "2", "5"))
                .phone("01000000000")
                .storeStatus(StoreStatus.OPEN)
                .discountInfo(StoreDiscountInfo.TODAY_DISCOUNT)
                .build();


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
        Store store1 = Store.builder()
                .name("좋은원두")
                .category(StoreCategory.CAFE)
                .paymentType(StorePaymentType.PREPAID)
                .salesType(StoreSalesType.HALL)
                .location(point)
                .address(new Address("주소1", "주소2", "2", "5"))
                .phone("01000000000")
                .storeStatus(StoreStatus.OPEN)
                .discountInfo(StoreDiscountInfo.TODAY_DISCOUNT)
                .outletNum(0)
                .build();

        latitude = 36.625172;
        longitude = 127.455410;
        pointWKT = String.format("POINT(%s %s)", longitude, latitude);
        point = (Point) new WKTReader().read(pointWKT);
        Store store2 = Store.builder()
                .name("나쁜원두")
                .category(StoreCategory.CAFE)
                .paymentType(StorePaymentType.PREPAID)
                .salesType(StoreSalesType.HALL)
                .location(point)
                .address(new Address("주소3", "주소4", "2", "5"))
                .phone("01000000020")
                .storeStatus(StoreStatus.OPEN)
                .discountInfo(StoreDiscountInfo.TODAY_DISCOUNT)
                .outletNum(0)
                .build();

        latitude = 32.124012;
        longitude = 127.122001;
        pointWKT = String.format("POINT(%s %s)", longitude, latitude);
        point = (Point) new WKTReader().read(pointWKT);
        Store store3 = Store.builder()
                .name("착한원두")
                .category(StoreCategory.CAFE)
                .paymentType(StorePaymentType.PREPAID)
                .salesType(StoreSalesType.HALL)
                .location(point)
                .address(new Address("주소5", "주소6", "2", "5"))
                .phone("01000000030")
                .storeStatus(StoreStatus.OPEN)
                .discountInfo(StoreDiscountInfo.TODAY_DISCOUNT)
                .outletNum(0)
                .build();

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

    @Test
    void 오늘의_할인페이지_이름순_정렬() {

        Store store1 = Store.builder()
                .name("좋은원두")
                .storeStatus(StoreStatus.OPEN)
                .discountInfo(StoreDiscountInfo.TODAY_DISCOUNT)
                .build();

        Store store2 = Store.builder()
                .name("아잉원두2")
                .storeStatus(StoreStatus.OPEN)
                .discountInfo(StoreDiscountInfo.TODAY_DISCOUNT)
                .build();


        Store store3 = Store.builder()
                .name("감자원두")
                .storeStatus(StoreStatus.OPEN)
                .discountInfo(StoreDiscountInfo.TODAY_DISCOUNT)
                .build();

        storeService.registerStore(store1);
        storeService.registerStore(store2);
        storeService.registerStore(store3);


        Discount discount1 = Discount.builder()
                .discountDay(DiscountDay.MON)
                .store(store1)
                .build();

        Discount discount2 = Discount.builder()
                .discountDay(DiscountDay.MON)
                .store(store2)
                .build();

        Discount discount3 = Discount.builder()
                .discountDay(DiscountDay.MON)
                .store(store3)
                .build();

        discountService.registerDiscount(discount1);
        discountService.registerDiscount(discount2);
        discountService.registerDiscount(discount3);

        List<Store> result = storeService.findTodayDiscountStores(DiscountDay.MON);
        assertThat(result).containsExactly(store3, store2, store1);
    }
}