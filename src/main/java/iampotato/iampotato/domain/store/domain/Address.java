package iampotato.iampotato.domain.store.domain;

import lombok.Getter;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
@Getter
public class Address {

    private String roadAddr;
    private String jibunAddr;
    private int zipNum;
    private int roadNum;

    protected Address(){}

    public Address(String roadAddr, String jibunAddr, int zipNum, int roadNum) {
        this.roadAddr = roadAddr;
        this.jibunAddr = jibunAddr;
        this.zipNum = zipNum;
        this.roadNum = roadNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return zipNum == address.zipNum && roadNum == address.roadNum && Objects.equals(roadAddr, address.roadAddr) && Objects.equals(jibunAddr, address.jibunAddr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roadAddr, jibunAddr, zipNum, roadNum);
    }
}
