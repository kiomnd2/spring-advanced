package hello.advanced.proxy.pureproxy.decorator.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DecoratorPatternClient {

    public Component component;


    public DecoratorPatternClient(Component component) {
        this.component = component;
    }

    public void execute() {
        String result = this.component.operation();
        log.info("result={}", result);
    }

}
