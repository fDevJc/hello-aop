package hello.aop.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CallServiceV1 {
    private CallServiceV1 callServiceV1;

    /*
        생성자로 자기자신을 주입받으면 순환참조 에러가 발생한다.
        인스턴스가 생성되지도 않았는데 주입을 받으려고 하니 에러 발생
        세터는 인스턴스가 다 생성되고 주입이 이루어지기 때문에 가능
        스프링은 생성단계 -> 세터주입단계로 나뉘어 짐

        참고
        스프링 부트 2.6 릴리즈 노트를 확인해보니 순환 참조를 기본적으로 금지하도록 변경되었습니다.
        순환 참조를 허용하도록 해결하려면
        application.properties 파일에 다음을 추가해야합니다.
        spring.main.allow-circular-references=true
    */
//    @Autowired
//    public void setCallServiceV1(CallServiceV1 callServiceV1) {
//        log.info("etCallServiceV1 callServiceV1.getClass={}", callServiceV1.getClass());
//        this.callServiceV1 = callServiceV1;
//    }


    public void external() {
        log.info("call external");
        callServiceV1.internal();
    }

    public void internal() {
        log.info("call internal");
    }
}
