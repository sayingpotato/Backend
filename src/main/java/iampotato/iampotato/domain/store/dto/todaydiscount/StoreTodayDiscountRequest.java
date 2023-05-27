package iampotato.iampotato.domain.store.dto.todaydiscount;

import lombok.Data;

@Data
public class StoreTodayDiscountRequest {

    String day;

    public StoreTodayDiscountRequest(String day) {
        this.day = day;
    }
}
