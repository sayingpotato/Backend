package iampotato.iampotato.domain.store.domain;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {

    private String roadAddr;
    private String jibunAddr;

    private String zipNum;
    private String roadNum;

    public Address(String roadAddr, String jibunAddr, String zipNum, String roadNum) {
        this.roadAddr = roadAddr;
        this.jibunAddr = jibunAddr;
        this.zipNum = zipNum;
        this.roadNum = roadNum;
    }

}
