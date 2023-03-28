package iampotato.iampotato.domain.customer.dto;

import lombok.Data;

@Data
public class SignUpRequest {
    private Long id;

    private String loginId;

    private String password;

    private String nickname;
}
