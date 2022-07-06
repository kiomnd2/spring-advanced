package hello.proxy.pureproxy.concreteproxy;

import hello.proxy.pureproxy.concreteproxy.code.ConcreateClient;
import hello.proxy.pureproxy.concreteproxy.code.ConcreateLogic;
import org.junit.jupiter.api.Test;

public class ConcreateProxyTest {

    @Test
    void noProxy() {
        ConcreateLogic concreateLogic = new ConcreateLogic();
        ConcreateClient client = new ConcreateClient(concreateLogic);
        client.execute();
    }
}
