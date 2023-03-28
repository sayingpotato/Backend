package iampotato.iampotato.domain.customer.dto;

import lombok.Data;

@Data
public class SignUpResponse {   //회원 가입시 ResponseBody
    private Long id;

    public SignUpResponse(Long id) {
        this.id = id;
    }
}
