package hello.advanced.aop.order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderServiceAop {

    private final OrderRepositoryAop orderRepositoryAop;

    public OrderServiceAop(OrderRepositoryAop orderRepositoryAop) {
        this.orderRepositoryAop = orderRepositoryAop;
    }

    public void orderItem(String itemId) {
        log.info("[orderService] 실행");
        orderRepositoryAop.save(itemId);
    }
}
