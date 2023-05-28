package iampotato.iampotato.domain.customer.dto;

import lombok.Data;

@Data
public class SignUpResponse {   //회원 가입시 ResponseBody
    private String id;

    public SignUpResponse(String id) {
        this.id = id;
    }
}
