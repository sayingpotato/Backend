package iampotato.iampotato.domain.store.domain;

import iampotato.iampotato.domain.discount.domain.Discounts;
import iampotato.iampotato.domain.item.domain.Items;
import iampotato.iampotato.domain.review.domain.Reviews;
import iampotato.iampotato.domain.storeimage.domain.StoreImages;
import iampotato.iampotato.domain.storeoperationhour.domain.StoreOperationHours;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.locationtech.jts.geom.Point;

import javax.persistence.*;
import java.time.LocalDateTime;


/**
 * Store <-> Item = 1 : N
 * Store <-> Discount = 1 : N
 * Store <-> Review = 1 : N
 * Store <-> StoreImage = 1 : N
 * Store <-> StoreOperationHour = 1 : N
 */
@Entity
@Getter
@Builder
@DynamicInsert
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Store {

    @Id @GeneratedValue
    @Column(name = "store_id")
    private Long id;

    // Store 을 삭제하면 해당 가게의 상품과 상품옵션도 같이 지워지도록 CascadeType.ALL 로 설정
    @Embedded
    private Items items;

    @Embedded
    private Discounts discounts;

    @Embedded
    private Reviews reviews;

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

    private String storeTodayDiscountThumbnail;

    @Embedded
    private StoreTopReview storeTopReview;

    @Embedded
    private StoreTopItem storeTopItem;

    @Embedded
    private StoreImages storeImages;

    private String description;

    private String tableImg;

    @Embedded
    private StoreOperationHours storeOperationHours;

    @Enumerated(EnumType.STRING)
    private StoreDay closedDay;

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


    // 해당 메서드는 실제 사용할 비즈니스 로직이 아닙니다. InitDb 에 사용할 메소드로 임시로 만들어둔것입니다.
    public void updateCollection(StoreOperationHours storeOperationHours, StoreImages storeImages, Reviews reviews, Discounts discounts, Items items) {
        this.items = items;
        this.discounts = discounts;
        this.storeOperationHours = storeOperationHours;
        this.storeImages = storeImages;
        this.reviews = reviews;
    }
}
