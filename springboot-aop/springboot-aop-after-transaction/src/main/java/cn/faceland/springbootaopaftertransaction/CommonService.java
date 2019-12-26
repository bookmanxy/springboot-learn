package cn.faceland.springbootaopaftertransaction;

import com.alibaba.fastjson.JSONObject;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author watermelon on 2019/1/9 21:04
 * @description
 */
@Transactional(rollbackFor = Exception.class)
public interface CommonService {

    JSONObject testCommon(String param);
}
