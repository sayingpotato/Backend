package iampotato.iampotato.domain.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class TokenResponse {

    private String grantType;
    private String accessToken;

    //    private String refreshToken;
}
