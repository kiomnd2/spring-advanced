package hello.advanced.aop.order;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
public class Pointcuts {


    //hello.springaop.app 패키지와 하위 패키지
    @Pointcut("execution(* hello.advanced.aop.order..*(..))")
    public void allOrder(){}
    //타입 패턴이 *Service
    @Pointcut("execution(* *..*Service.*(..))")
    public void allService(){}
    //allOrder && allService
    @Pointcut("allOrder() && allService()")
    public void orderAndService(){}

}
