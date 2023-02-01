package hello.advanced.aop.pointcut;

import hello.advanced.app.v0.OrderServiceV0;
import hello.advanced.app.v3.OrderServiceV3;
import hello.advanced.app.v5.OrderServiceV5;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@Import(BeanTest.BeanAspect.class)
@SpringBootTest
public class BeanTest {

    @Autowired
    OrderServiceV0 orderServiceV0;

    @Test
    void success() {
        orderServiceV0.orderItem("itemA");
    }

    @Slf4j
    @Aspect
    static class BeanAspect {

        @Around("bean(orderServiceV0) || bean(*Repository*)")
        public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[bean] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }
    }
}
