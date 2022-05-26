package hello.aop;

import org.junit.jupiter.api.Test;

public class BasicTest {
    @Test
    void foo() {
        String str = "fo";
        foo1(str);
        System.out.println("str = " + str);
    }

    public void foo1(String str) {
        str = "foooooooooooo";
    }
}
