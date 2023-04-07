package iampotato.iampotato.domain.customer.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CustomerImage {

    private String customerOriginalImage;

    private String customerStoredImage;

    private Long fileSize;

    protected CustomerImage(String customerOriginalImage, String customerStoredImage, Long fileSize) {
        this.customerOriginalImage = customerOriginalImage;
        this.customerStoredImage = customerStoredImage;
        this.fileSize = fileSize;
    }
}
