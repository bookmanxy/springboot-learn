package cn.faceland.springbootidempotent.service;

/**
 * @author watermelon
 * @Date 2020-01-03
 * @Description
 */
import cn.faceland.springbootidempotent.util.JedisUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.text.StrBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Service
public class TokenServiceImpl implements TokenService {

    private static final String TOKEN_NAME = "token";

    @Autowired
    private JedisUtil jedisUtil;

    @Override
    public String createToken() {
        String str =  UUID.randomUUID().toString();
        StrBuilder token = new StrBuilder();
        String TOKEN_PREFIX = "token_";
        token.append(TOKEN_PREFIX).append(str);

        jedisUtil.set(token.toString(), token.toString(), 60);

        return token.toString();
    }

    @Override
    public void checkToken(HttpServletRequest request) {
        String token = request.getHeader(TOKEN_NAME);
        if (StringUtils.isBlank(token)) {// header中不存在token
            token = request.getParameter(TOKEN_NAME);
            if (StringUtils.isBlank(token)) {// parameter中也不存在token
//                throw new Exception();
                //抛异常
            }
        }

        if (!jedisUtil.exists(token)) {
            //抛异常说，不存在，或者不允许非幂等
//            throw new ServiceException(ResponseCode.REPETITIVE_OPERATION.getMsg());
        }

        Long del = jedisUtil.del(token);
        if (del <= 0) {
            //抛异常说，不存在，或者不允许非幂等
//            throw new ServiceException(ResponseCode.REPETITIVE_OPERATION.getMsg());
        }
    }

}