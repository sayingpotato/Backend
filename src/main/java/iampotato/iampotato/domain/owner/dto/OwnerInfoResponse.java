package iampotato.iampotato.domain.owner.dto;

import iampotato.iampotato.domain.owner.domain.Owner;
import iampotato.iampotato.domain.owner.domain.OwnerStatus;
import lombok.Data;

@Data
public class OwnerInfoResponse {

    private String loginId;

    private String nickname;

    private OwnerStatus ownerStatus;

    private String ownerBusinessNumber;

    public OwnerInfoResponse(Owner owner) {
        this.loginId = owner.getLoginId();
        this.nickname = owner.getNickname();
        this.ownerStatus = owner.getOwnerStatus();
        this.ownerBusinessNumber = owner.getOwnerBusinessNumber();
    }
}
