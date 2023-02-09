package hello.aop.exam.aop;

import org.aspectj.lang.annotation.Before;

import hello.aop.exam.annotation.Authority;

public class AuthorityAspect {
	//
	@Before(value = "@annotation(hello.aop.exam.annotation.Authority)")
	public void checkAccountType(Authority authority) {
		System.out.println(authority.value());
	}
}
