package hello.aop.order.aop.order.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Slf4j
@Aspect
public class AspectV6Advice {

//    @Around("hello.aop.order.aop.order.aspect.Pointcuts.allOrder()")
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[log] {}", joinPoint.getSignature());
        return joinPoint.proceed();
    }

//    @Around("hello.aop.order.aop.order.aspect.Pointcuts.orderAndService()") //&& 로 붙여서 사용가능
    public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            //@Before
            log.info("[transaction start] {}", joinPoint.getSignature());
            Object result = joinPoint.proceed();
            //@AfterReturning
            log.info("[transaction commit] {}", joinPoint.getSignature());
            return result;
        } catch (Exception e) {
            //@AfterThrowing
            log.info("[transaction rollback] {}", joinPoint.getSignature());
            throw e;
        } finally {
            //@After
            log.info("[resource release] {}", joinPoint.getSignature());
        }
    }

    @Before("hello.aop.order.aop.order.aspect.Pointcuts.orderAndService()")
    public void doBefore(JoinPoint joinPoint) {
        log.info("@Before [transaction start] {}", joinPoint.getSignature());
    }

    @AfterReturning(value = "hello.aop.order.aop.order.aspect.Pointcuts.orderAndService()", returning = "result")
    public void doReturn(JoinPoint joinPoint, Object result) {
        log.info("@AfterReturning [transaction commit] {}, result={}", joinPoint.getSignature(), result);
    }

    @AfterThrowing(value = "hello.aop.order.aop.order.aspect.Pointcuts.orderAndService()", throwing = "ex")
    public void doThrowing(JoinPoint joinPoint, Exception ex) {
        log.info("@AfterThrowing [transaction rollback] {}, ex={}", joinPoint.getSignature(), ex);
    }

    @After("hello.aop.order.aop.order.aspect.Pointcuts.orderAndService()")
    public void doAfter(JoinPoint joinPoint) {
        log.info("@After [transaction release] {}", joinPoint.getSignature());
    }
}
