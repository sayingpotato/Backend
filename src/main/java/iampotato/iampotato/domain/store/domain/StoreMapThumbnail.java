package iampotato.iampotato.domain.store.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Embeddable;

/**
 * 가게의 할인지도페이지 리스트에 들어갈 사진 리소스를 저장합니다.
 */
@Embeddable
@Getter
@EqualsAndHashCode
public class StoreMapThumbnail {

    /**
     * DEFAULT_THUMBNAIL_URL 는 저장된 이미지 없을시 띄워주는 사진 URL 입니다.
     */
    private static final String DEFAULT_THUMBNAIL_URL = "'aa'";
    protected StoreMapThumbnail(){}

    @ColumnDefault(DEFAULT_THUMBNAIL_URL)
    private String mainImg;

    @ColumnDefault(DEFAULT_THUMBNAIL_URL)
    private String subImg1;

    @ColumnDefault(DEFAULT_THUMBNAIL_URL)
    private String subImg2;

    @ColumnDefault(DEFAULT_THUMBNAIL_URL)
    private String subImg3;


}
