package hello.aop.proxyvs;

import hello.aop.member.MemberService;
import hello.aop.member.MemberServiceImpl;
import hello.aop.proxyvs.code.ProxyDIAspect;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@Import(ProxyDIAspect.class)
@SpringBootTest(properties = {"spring.aop.proxy-target-class=true"})   //cglib프록시
//@SpringBootTest(properties = {"spring.aop.proxy-target-class=false"})   //jdk동적프록시
public class ProxyDITest {
    @Autowired
    MemberService memberService;

    //jdk동적프록시 인경우 캐스팅이 되지않기 때문에 에러
    @Autowired
    MemberServiceImpl memberServiceImpl;

    @Test
    void test() {
        log.info("memberService class={}", memberService.getClass());
        log.info("memberServiceImpl class={}", memberServiceImpl.getClass());
    }
}
