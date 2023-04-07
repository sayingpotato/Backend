package iampotato.iampotato.global.utility;

//==이미지 파일 관련 유틸리티 클래스==//
/*
유틸리티 클래스는 OOP보다는 절차 지향 프로그래밍에 가까워서 사용을 지양하는 것이 좋다.
하지만 현재 우리 서비스는 Image가 매우 다양한 클래스(store, item, review, customer, owner 등)에서
매우 동일한 로직으로 사용될 예정이므로 유연하게 유틸리티 클래스를 활용하기로 했다.
 */
public final class ImageUtility {
    //ImageUtility를 활용하는 각 클래스 별로 이미지 저장 path를 다르게 하기 위해 아래와 같이 설정
    private static final String CUSTOMER = "customer";
    private static final String OWNER = "owner";
    private static final String ITEM = "item";
    private static final String STORE = "store";

    private ImageUtility() {    //외부에서 ImageUtility 클래스를 인스턴스화시키지 못하도록 private으로 만들고 내부에서 인스턴스화를 시도하여도 Error 발생
        throw new UnsupportedOperationException();
    }

}
