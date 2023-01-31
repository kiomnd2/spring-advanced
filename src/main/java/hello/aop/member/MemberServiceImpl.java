package hello.aop.member;

import hello.aop.member.annotation.ClassApp;
import hello.aop.member.annotation.MethodAop;
import org.springframework.stereotype.Component;

@ClassApp
@Component
public class MemberServiceImpl implements MemberService {

    @MethodAop("test value")
    @Override
    public String hello(String param) {
        return "ok";
    }

    public String internal(String param) {
        return "ok";
    }
}
