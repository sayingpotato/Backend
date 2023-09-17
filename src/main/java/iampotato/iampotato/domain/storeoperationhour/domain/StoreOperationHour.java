package iampotato.iampotato.domain.storeoperationhour.domain;

import iampotato.iampotato.domain.store.domain.Store;
import iampotato.iampotato.domain.store.domain.StoreDay;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StoreOperationHour {

    @Id
    @GeneratedValue
    @Column(name = "store_operation_hour_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @Enumerated(EnumType.STRING)
    private StoreDay startDay;
    @Enumerated(EnumType.STRING)
    private StoreDay endDay;
    private String startTime;
    private String endTime;

    public static StoreOperationHour createStoreOperationHour(Store store,
                                                              StoreDay startDay,
                                                              StoreDay endDay,
                                                              String startTime,
                                                              String endTime) {

        return StoreOperationHour.builder()
                .store(store)
                .startDay(startDay)
                .endDay(endDay)
                .startTime(startTime)
                .endTime(endTime)
                .build();
    }
}
