package hello.advanced.aop.internalcall;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CallServiceV2 {

    // 프로바이더 객체 이용
    private final ObjectProvider<CallServiceV2> callServiceProvider;


    // 지연 로딩 이용
    public void external() {
        log.info("call external");
        CallServiceV2 callServiceV2 = callServiceProvider.getObject();
        callServiceV2.internal();
    }

    public void internal() {
        log.info("call internal");
    }
}
