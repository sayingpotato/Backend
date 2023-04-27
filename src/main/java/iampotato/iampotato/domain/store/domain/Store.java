package iampotato.iampotato.domain.store.domain;

import iampotato.iampotato.domain.discount.domain.Discount;
import iampotato.iampotato.domain.item.domain.Item;
import iampotato.iampotato.domain.review.domain.Review;
import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.locationtech.jts.geom.Point;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Store <-> Item = 1 : N
 * Store <-> Discount = 1 : N
 * Store <-> Review = 1 : N
 */
@Entity
@Getter
@Builder
@DynamicInsert
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Store {

    private static final String DEFAULT_STORE_URL = "'aa'";

    @Id @GeneratedValue
    @Column(name = "store_id")
    private Long id;

    // Store 을 삭제하면 해당 가게의 상품과 상품옵션도 같이 지워지도록 CascadeType.ALL 로 설정
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Item> items = new ArrayList<>();

    @BatchSize(size = 100)
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Discount> discounts = new ArrayList<>();

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
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

    @Embedded
    private StoreMapThumbnail storeMapThumbnail;

    @Embedded
    private StoreTopReview storeTopReview;

    @Embedded
    private StoreTopItem storeTopItem;

    @ColumnDefault(DEFAULT_STORE_URL)
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

}
