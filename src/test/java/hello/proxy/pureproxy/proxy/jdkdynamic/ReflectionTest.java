package hello.proxy.pureproxy.proxy.jdkdynamic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Slf4j
public class ReflectionTest {

    @Test
    void reflection0() {
        Hello target = new Hello();

        log.info("start");

        String result = target.callA();
        log.info("result={}", result);


        log.info("start");

        String resultB = target.callB();
        log.info("result={}", resultB);
    }

    @Test
    void reflection1() throws Exception {
        Class<?> classHello = Class.forName("hello.proxy.pureproxy.proxy.jdkdynamic.ReflectionTest$Hello"); // 클래스 메타정보 휙득
        
        Hello target = new Hello();
        
        // callA 의 메서드 정보
        Method callA = classHello.getMethod("callA"); // 해당 클래스의 메타정보를 획득
        Object result1 = callA.invoke(target);

        log.info("result={}", result1);

        // callA 의 메서드 정보
        Method callB = classHello.getMethod("callB");
        Object result2 = callB.invoke(target);

        log.info("result={}", result2);
    }

    @Test
    void reflection2() throws Exception{

        Class<?> classHello = Class.forName("hello.proxy.pureproxy.proxy.jdkdynamic.ReflectionTest$Hello"); // 클래스 메타정보 휙득

        Hello target = new Hello();

        // callA 의 메서드 정보
        Method callA = classHello.getMethod("callA"); // 해당 클래스의 메타정보를 획득
        dynamicCall(callA, target);

        // callA 의 메서드 정보
        Method callB = classHello.getMethod("callB");
        dynamicCall(callB, target);

    }

    private void dynamicCall(Method method, Object target) throws Exception{
        // callA 의 메서드 정보
        log.info("start");
        Object result1 = method.invoke(target);
        log.info("result={}", result1);
    }

    @Slf4j
    static class Hello {
        public String callA() {
            log.info("calla");
            return "A";
        }

        public String callB() {
            log.info("callb");
            return "B";
        }
    }
}
