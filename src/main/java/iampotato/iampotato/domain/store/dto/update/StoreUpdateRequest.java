package iampotato.iampotato.domain.store.dto.update;

import iampotato.iampotato.domain.store.domain.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class StoreUpdateRequest {

    private Long id;

    private StoreSalesType salesType;

    private StoreStatus storeStatus;

    private StoreDiscountInfo discountInfo;

    private String description;

    private String tableImg;

    private List<OperationHour> storeOperationHours;

    private StoreDay closedDay;

    private StoreMapThumbnail storeMapThumbnail;

    private String storeTodayDiscountThumbnail;

    private int outletNum;

    @Data
    public static class OperationHour {

        private StoreDay startDay;

        private StoreDay endDay;

        private String startTime;

        private String endTime;
    }
}
