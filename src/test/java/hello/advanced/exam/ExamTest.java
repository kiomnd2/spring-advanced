package hello.advanced.exam;

import hello.advanced.exam.aop.RetryAspect;
import hello.advanced.exam.aop.TraceAspect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import({TraceAspect.class, RetryAspect.class})
@SpringBootTest
public class ExamTest {


    @Autowired
    ExamService examService;

    @Test
    void test() {
        for (int i=0 ; i<5 ; i++) {
            examService.request("data" + i);
        }
    }
}
