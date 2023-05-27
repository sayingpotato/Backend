package iampotato.iampotato.domain.store.dto.detail;

import iampotato.iampotato.domain.storeimage.domain.StoreImage;
import lombok.Data;

@Data
public class StoreDetailStoreImage {

    private String storeImg;

    public StoreDetailStoreImage(StoreImage storeImage) {
        this.storeImg = storeImage.getStoreImg();
    }
}
