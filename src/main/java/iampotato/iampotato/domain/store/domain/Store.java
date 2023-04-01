package iampotato.iampotato.domain.store.domain;

import iampotato.iampotato.domain.discount.domain.Discount;
import iampotato.iampotato.domain.item.domain.Item;
import iampotato.iampotato.domain.review.domain.Review;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Store <-> Item = 1 : N
 * Store <-> Discount = 1 : N
 * Store -> Review = 1 : N
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Store {

    @Id @GeneratedValue
    @Column(name = "store_id")
    private Long id;

    // Store 을 삭제하면 해당 가게의 상품과 상품옵션도 같이 지워지도록 CascadeType.ALL 로 설정
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Item> items = new ArrayList<>();

    @OneToMany(mappedBy = "store")
    private List<Discount> discounts = new ArrayList<>();

    @OneToMany
    private List<Review> reviews = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private StoreCategory category;

    private String name;

    @Enumerated(EnumType.STRING)
    private StorePaymentType paymentType; // [PREPAID, POSTPAID]

    @Column(columnDefinition = "GEOMETRY")
    private Point location;

    @Embedded
    private Address address;

    private String storeImg;

    private String description;

    private String tableImg;

    private String operationHour;

    private String closedDay;

    private String phone;

    // 여기서 부터는 가게 세부정보 입니다.
    // ===============================
    private int outletNum;


    // ===============================
    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    @Enumerated(EnumType.STRING)
    private StoreSalesType salesType;

    @Enumerated(EnumType.STRING)
    private StoreStatus storeStatus;

    @Enumerated(EnumType.STRING)
    private StoreDiscountInfo discountInfo;

    public static Store createStoreWithRequiredValue(String name,
                                                     StoreCategory category,
                                                     StorePaymentType paymentType,
                                                     StoreSalesType storeSalesType,
                                                     Address address,
                                                     String phone,
                                                     StoreStatus storeStatus,
                                                     StoreDiscountInfo discountInfo) {

        Store store = new Store();
        store.name = name;
        store.category = category;
        store.paymentType = paymentType;
        store.salesType = storeSalesType;
        store.address = address;
        store.phone = phone;

        // 가게 추가 필드
        // =============
        store.outletNum = 0;

        // =============
        store.storeStatus = storeStatus;
        store.discountInfo = discountInfo;

        return store;
    }

    public static Store createStoreWithRequiredValueWithLocation(String name,
                                                     StoreCategory category,
                                                     StorePaymentType paymentType,
                                                     StoreSalesType storeSalesType,
                                                     Point location,
                                                     Address address,
                                                     String phone,
                                                     StoreStatus storeStatus,
                                                     StoreDiscountInfo discountInfo) {

        Store store = new Store();
        store.name = name;
        store.category = category;
        store.paymentType = paymentType;
        store.salesType = storeSalesType;
        store.location = location;
        store.address = address;
        store.phone = phone;

        // 가게 추가 필드
        // =============
        store.outletNum = 0;

        // =============
        store.storeStatus = storeStatus;
        store.discountInfo = discountInfo;

        return store;
    }
}
