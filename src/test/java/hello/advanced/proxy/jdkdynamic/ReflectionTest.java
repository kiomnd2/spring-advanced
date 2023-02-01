package hello.advanced.proxy.jdkdynamic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

@Slf4j
public class ReflectionTest {


    @Test
    void reflection0() {

        Hello target = new Hello();


        // 공통 로직 1 시작
        log.info("start");
        String result1 = target.callA(); // 호출하는 메서드만 다름

        log.info("result={}", result1);


        // 공통 로직 2 시작
        log.info("start");

        String result2 = target.callB();
        log.info("result={}", result2);

    }

    @Test
    void reflection1() throws Exception{

        // 클래스 메타정보 획득
        Class classHello = Class.forName("hello.advanced.proxy.jdkdynamic.ReflectionTest$Hello");

        Hello target = new Hello();

        Method methodCallA = classHello.getMethod("callA");
        dynamicCall(methodCallA, target);

        Method methodCallB = classHello.getMethod("callB");
        dynamicCall(methodCallB, target);
    }

    @Test
    void reflection2() throws Exception{
        // 클래스 메타정보 획득
        Class classHello = Class.forName("hello.advanced.proxy.jdkdynamic.ReflectionTest$Hello");

        Hello target = new Hello();

        Method methodCallA = classHello.getMethod("callA");
        Object result1 = methodCallA.invoke(target);
        log.info("result1={}", result1);

        Method methodCallB = classHello.getMethod("callB");
        Object result2 = methodCallB.invoke(target);
        log.info("result2={}", result2);
    }
    private void dynamicCall(Method method ,Object target) throws Exception {
        log.info("start");
        Object result1 = method.invoke(target);
        log.info("result1={}", result1);
    }

    @Slf4j
    static class Hello {
        public String callA() {
            log.info("callA");
            return "A";
        }

        public String callB() {
            log.info("callB");
            return "B";
        }
    }
}
