package hello.proxy.jdkdynamic;

import hello.proxy.jdkdynamic.code.AInterface;
import hello.proxy.jdkdynamic.code.AInterfaceImpl;
import hello.proxy.jdkdynamic.code.TimeInvocationHandler;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

@Slf4j
public class JdkDynamicProxyTest {

    @Test
    void dynamicA() {
        AInterface target = new AInterfaceImpl();
        TimeInvocationHandler invocationHandler = new TimeInvocationHandler(target);

        AInterface proxyInstance = (AInterface)Proxy.newProxyInstance(AInterface.class.getClassLoader(), new Class[]{AInterface.class}, invocationHandler);

        proxyInstance.call();

        log.info("target Classes={}", target.getClass());
        log.info("proxy Classes={}", proxyInstance.getClass());
    }
}
