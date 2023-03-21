package iampotato.iampotato.domain.order.domain;

/**
 * 주문 상태를 저장하기 위한 ENUM
 * ORDER : 주문넣을시 (사용자가 주문하고 점주가 수락하기 전)
 * WAITQR : 후불 결제시에 사용자가 맨처음 QR을 찍으면 변경되는 상태
 * FINISH : 후불 결제시에 사용자가 두번째 QR을 찍거나, 선불 결제시에 사용자가 QR 을 찍으면 변경되는 상태
 */
public enum OrderStatus {
    ORDER, WAITQR, FINISH
}
