package hello.advanced.aop.internalcall;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(CallLogAspect.class)
@SpringBootTest
public class CallServiceV0Test {

    @Autowired
    CallServiceV0 callServiceV0;

    // 내부 메소드에 대한 advice 가 발생하지 않습니다.
    // this 를 참조하기 때문
    // aspect 사용시 이런분제 발생 안함
    @Test
    void external() {
        callServiceV0.external();
    }

    @Test
    void internal() {
        callServiceV0.internal();
    }
}
