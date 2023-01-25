package hello.advanced.aop.order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class OrderRepositoryAop {


    public String save(String item) {
        log.info("[orderRepository] 실행");
        if (item.equals("ex")) {
            throw new IllegalStateException("예외 발생");
        }
        return "ok";
    }
}
