package iampotato.iampotato.domain.storeoperationhour.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StoreOperationHours {

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<StoreOperationHour> storeOperationHours = new ArrayList<>();

    public StoreOperationHours(StoreOperationHour... storeOperationHours) {
        this.storeOperationHours = List.of(storeOperationHours);
    }
}
