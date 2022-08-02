package hello.proxy.config.concreate_proxy;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;
import hello.proxy.app.v2.OrderRepositoryV2;
import hello.proxy.app.v2.OrderServiceV2;

public class OrderServiceConcreateProxy extends OrderServiceV2 {

    private final OrderServiceV2 target;

    private final LogTrace logTrace;

    public OrderServiceConcreateProxy(OrderServiceV2 target, LogTrace logTrace) {
        super(null); // 클래스 기반 프록시는 부모의 기능을 사용하지 않으므로 super로 기본적으로 사용하되 null을 전달합니다
        this.target = target;
        this.logTrace = logTrace;
    }


    @Override
    public void orderItem(String itemId) {
        TraceStatus status = null;
        try {
            status = logTrace.begin("OrderService.orderItem()");
            target.orderItem(itemId);
            logTrace.end(status);
            // target 호출
        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e;
        }
    }
}
