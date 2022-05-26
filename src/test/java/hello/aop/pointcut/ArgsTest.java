package hello.aop.pointcut;

import hello.aop.member.MemberServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;

public class ArgsTest {
    AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();

    Method helloMethod;

    @BeforeEach
    public void init() throws NoSuchMethodException {
        helloMethod = MemberServiceImpl.class.getMethod("hello", String.class);
    }

    /*
        execution: 파라미터 타입이 정확하게 매칭되어야 한다. 클래스에 선언된 정보를 기반으로 판단
                   정적으로 판단
        args: 부모 타입을 허용, 실제 넘어온 파라미터 객체 인스턴스를 보고 판단한다.
              동적으로 판단
     */
    @Test
    void withinTest() {
        pointcut.setExpression("args(String)");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void withinTest_object_true() {
        //상위타입 허용 O
        pointcut.setExpression("args(Object)");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void executionTest_object_false() {
        //상위타입 허용 X
        pointcut.setExpression("execution(* *(Object))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isFalse();
    }

}
