package iampotato.iampotato.domain.store.dto.map;

import lombok.Data;

@Data
public class StoreMapRequest {

    private double latitude;
    private double longitude;

    public StoreMapRequest(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

}
