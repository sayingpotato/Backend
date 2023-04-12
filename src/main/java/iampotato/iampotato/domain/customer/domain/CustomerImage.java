package iampotato.iampotato.domain.customer.domain;

import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor()
public class CustomerImage {

    private String customerOriginalImage;

    private String customerStoredImage;

    private Long fileSize;
}
