package hello.proxy.config.concreate_proxy;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;
import hello.proxy.app.v2.OrderRepositoryV2;
import lombok.RequiredArgsConstructor;


public class OrderRepositoryContreteProxy extends OrderRepositoryV2 {

    private final OrderRepositoryV2 target;

    private final LogTrace logTrace;

    public OrderRepositoryContreteProxy(OrderRepositoryV2 target, LogTrace logTrace) {
        this.target = target;
        this.logTrace = logTrace;
    }

    @Override
    public void save(String itemId) {
        TraceStatus status = null;
        try {
            status = logTrace.begin("OrderRepository.request()");
            target.save(itemId);
            logTrace.end(status);
            // target 호출
        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e;
        }
    }
}
