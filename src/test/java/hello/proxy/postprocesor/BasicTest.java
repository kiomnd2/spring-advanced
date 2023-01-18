package hello.proxy.postprocesor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


public class BasicTest {

    @Test
    void basicConfig() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BasicConfig.class);

        // A 빈으로 등록됩니다
        A a = applicationContext.getBean("beanA", A.class);
        a.helloA();

        // B는 빈으로 등록되지 않음
        Assertions.assertThrows(NoSuchBeanDefinitionException.class, ()-> applicationContext.getBean(B.class));
    }


    @Configuration
    @Slf4j
    static class BasicConfig {
        @Bean("beanA")
        public A a() {
            return new A();
        }
    }

    @Slf4j
    static class A {
        public void helloA() {
            log.info("hello A");
        }
    }

    @Slf4j
    static class B {
        public void helloB() {
            log.info("hello B");
        }
    }
}
