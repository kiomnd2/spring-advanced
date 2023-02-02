package hello.advanced.aop.pointcut;


import hello.advanced.aop.member.MemberService;
import hello.advanced.aop.member.annotation.ClassApp;
import hello.advanced.aop.member.annotation.MethodAop;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class ParameterTest {

    @Autowired
    MemberService memberService;

    @Test
    void success() {
        log.info("memberService Proxy={}", memberService.getClass());
        memberService.hello("helloA");
    }

    @Aspect
    @Slf4j
    static class ParameterAspect {

        @Pointcut("execution(* hello.advanced.aop.member..*.*(..))")
        private void allMember() {}

        @Around("allMember()")
        public Object logArgs(ProceedingJoinPoint joinPoint) throws Throwable {
            Object arg1 = joinPoint.getArgs()[0]; // 매개변수
            log.info("[logArgs1]{}, arg={}", joinPoint.getSignature(), arg1);
            return joinPoint.proceed();
        }

        @Around("allMember() && args(arg,..)")
        public Object logArgs2(ProceedingJoinPoint joinPoint, Object arg) throws Throwable {
            log.info("[logArgs1]{}, arg={}", joinPoint.getSignature(), arg);
            return joinPoint.proceed();
        }

        @Before("allMember() && args(arg,..)")
        public void logArgs3(String arg) {
            log.info("[logArgs3] arg={}", arg);
        }

        // this -> 프록시 객체를 전달 받는다
        @Before("allMember() && this(obj)")
        public void thisArgs(JoinPoint joinPoint, MemberService obj) {
            log.info("[this]{}, obj={}", joinPoint.getSignature(), obj.getClass());
        }

        // 실제 대상 객체를 전달 받는다
        @Before("allMember() && target(obj)")
        public void targetArgs(JoinPoint joinPoint, MemberService obj) {
            log.info("[target]{}, obj={}", joinPoint.getSignature(), obj.getClass());
        }

        // 타입의 애노테이션을 받습니다
        @Before("allMember() && @target(annotation)")
        public void atTarget(JoinPoint joinPoint, ClassApp annotation) {
            log.info("[@target]{}, obj={}", joinPoint.getSignature(),
                    annotation);
        }

        // 타입의 애노테이션을 받음
        @Before("allMember() && @within(annotation)")
        public void atWithin(JoinPoint joinPoint, ClassApp annotation) {
            log.info("[@within]{}, obj={}", joinPoint.getSignature(),
                    annotation);
        }

        // 메서드의 애노테이션을 받음
        @Before("allMember() && @annotation(annotation)")
        public void atAnnotation(JoinPoint joinPoint, MethodAop annotation) {
            log.info("[@annotation]{}, annotationValue={}",
                    joinPoint.getSignature(), annotation.value());
        }

    }

}
