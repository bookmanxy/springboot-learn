package cn.faceland.springbootidempotent.service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author watermelon
 * @Date 2020-01-03
 * @Description
 */
public interface TokenService {
    public String createToken();

    public void checkToken(HttpServletRequest request);
}
