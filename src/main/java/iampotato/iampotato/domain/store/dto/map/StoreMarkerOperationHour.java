package iampotato.iampotato.domain.store.dto.map;

import iampotato.iampotato.domain.store.domain.StoreDay;
import iampotato.iampotato.domain.storeoperationhour.domain.StoreOperationHour;
import lombok.Data;

@Data
public class StoreMarkerOperationHour {

    private StoreDay startDay;
    private StoreDay endDay;
    private String startTime;
    private String endTime;

    public StoreMarkerOperationHour(StoreOperationHour storeOperationHour) {
        this.startDay = storeOperationHour.getStartDay();
        this.endDay = storeOperationHour.getEndDay();
        this.startTime = storeOperationHour.getStartTime();
        this.endTime = storeOperationHour.getEndTime();
    }
}
