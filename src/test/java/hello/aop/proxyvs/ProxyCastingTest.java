package hello.aop.proxyvs;

import hello.aop.member.MemberService;
import hello.aop.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;

import static org.assertj.core.api.Assertions.*;

public class ProxyCastingTest {
    @Test
    void jdkProxy() {
        MemberServiceImpl target = new MemberServiceImpl();
        ProxyFactory factory = new ProxyFactory(target);
        factory.setProxyTargetClass(false); //jdk 동적 프록시

        //프록시를 인터페이스로 캐스팅
        MemberService memberServiceProxy = (MemberService) factory.getProxy();

        //구현클래스로 캐스팅 X
        assertThatThrownBy(() -> {
            MemberServiceImpl castingMemberServiceImpl = (MemberServiceImpl) memberServiceProxy;
        }).isInstanceOf(ClassCastException.class);
    }

    @Test
    void cglibProxy() {
        MemberServiceImpl target = new MemberServiceImpl();
        ProxyFactory factory = new ProxyFactory(target);
        factory.setProxyTargetClass(true); //cglib

        //프록시를 인터페이스로 캐스팅
        MemberService memberServiceProxy = (MemberService) factory.getProxy();

        //구현클래스로 캐스팅 O
        MemberServiceImpl castingMemberServiceImpl = (MemberServiceImpl) memberServiceProxy;
    }
}
