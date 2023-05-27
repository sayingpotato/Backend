package iampotato.iampotato.domain.storeimage.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StoreImages {

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<StoreImage> storeImages = new ArrayList<>();

    public StoreImages(StoreImage... storeImages) {
        this.storeImages = List.of(storeImages);
    }
}
