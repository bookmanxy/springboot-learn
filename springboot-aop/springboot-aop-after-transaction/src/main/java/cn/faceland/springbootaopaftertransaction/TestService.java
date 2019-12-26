package cn.faceland.springbootaopaftertransaction;

import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author watermelon on 2019/1/9 21:04
 * @description
 */
@Transactional(rollbackFor = Exception.class)
public interface TestService {

    /**
     *測試方法
     */
    @Async("taskExecutor")
    void test(String param);
}
