package hello.advanced.exam;

import hello.advanced.exam.annotation.Retry;
import hello.advanced.exam.annotation.Trace;
import org.springframework.stereotype.Repository;

@Repository
public class ExamRepository {


    private static int seq = 0;

    @Retry(value = 4)
    @Trace
    public String save(String itemId) {
        seq++;
        if (seq % 5 == 0) {
            throw new IllegalStateException("예외 발생");
        }
        return "ok";
    }

}
