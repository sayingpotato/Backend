package iampotato.iampotato.domain.store.dto;

import lombok.Data;

@Data
public class StoreTodayDiscountRequest {

    String day;

    public StoreTodayDiscountRequest(String day) {
        this.day = day;
    }
}
