package hello.advanced.aop.order;

import hello.advanced.aop.AspectV1;
import hello.advanced.aop.AspectV2;
import hello.advanced.aop.AspectV3;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(AspectV3.class) // 추가
@Slf4j
@SpringBootTest
class AopTest {

    @Autowired
    OrderServiceAop orderServiceAop;

    @Autowired
    OrderRepositoryAop orderRepositoryAop;

    @Test
    void aopInfo() {
        log.info("isAopProxy, orderService={}", AopUtils.isAopProxy(orderServiceAop));
        log.info("isAopProxy, orderReposotiry={}", AopUtils.isAopProxy(orderRepositoryAop));
    }

    @Test
    void success() {
        orderServiceAop.orderItem("itemA");
    }

    @Test
    void exception() {
        Assertions.assertThrows(IllegalStateException.class, ()-> {
            orderServiceAop.orderItem("ex");
        });
    }
}
