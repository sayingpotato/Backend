package iampotato.iampotato.domain.customer.dto;

import lombok.Data;

@Data
public class SignUpRequest {    //회원 가입시 RequestBody
    private String loginId;
    private String password;
    private String nickname;
    private String customerNumber;
    private String customerDept;
    private String customerCollege;
}