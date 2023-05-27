package iampotato.iampotato.domain.store.dto.maplist;

import lombok.Data;

@Data
public class StoreMapListRequest {

    private double latitude;
    private double longitude;

    public StoreMapListRequest(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
