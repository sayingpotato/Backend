package iampotato.iampotato.domain.customer.domain;

/*
* 회원 가입 상태를 저장하기 위한 enum
* COMPLETE: 학생증 인증까지 완료되어 정상적으로 회원가입이 마쳐진 상태
* UNAUTHORIZED: 학생증 인증이 되지 않아 인증 대기중인 상태
*/
public enum CustomerStatus {
    COMPLETE, UNAUTHORIZED
}
