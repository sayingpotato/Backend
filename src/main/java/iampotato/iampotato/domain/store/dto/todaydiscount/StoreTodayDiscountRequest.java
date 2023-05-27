package iampotato.iampotato.domain.store.dto.todaydiscount;

import iampotato.iampotato.domain.discount.domain.DiscountDay;
import lombok.Data;

@Data
public class StoreTodayDiscountRequest {

    DiscountDay day;

    public StoreTodayDiscountRequest(DiscountDay day) {
        this.day = day;
    }
}
