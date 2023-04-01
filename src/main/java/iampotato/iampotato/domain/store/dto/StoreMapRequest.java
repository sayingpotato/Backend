package iampotato.iampotato.domain.store.dto;

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
