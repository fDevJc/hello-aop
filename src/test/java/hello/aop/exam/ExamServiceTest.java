package hello.aop.exam;

import hello.aop.exam.annotation.Authority;
import hello.aop.exam.aop.AuthorityAspect;
import hello.aop.exam.aop.RetryAspect;
import hello.aop.exam.aop.TraceAspect;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@Import({TraceAspect.class, RetryAspect.class, AuthorityAspect.class})
@SpringBootTest
class ExamServiceTest {
    @Autowired
    ExamService examService;

    @Test
    void test() {
        for (int i = 0; i < 5; i++) {
            log.info("request i = {}", i);
            examService.request("data" + i);
        }
    }

    @Test
    void test1() {
        examService.test();
    }
}