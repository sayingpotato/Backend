package iampotato.iampotato.domain.owner.dto;

import lombok.Data;

@Data
public class OwnerSignUpRequest {

    private String loginId;

    private String password;

    private String nickname;

    private String ownerBusinessNumber;
}
