package cn.faceland.springbootjwt.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTokenUtil {
    private static final String SECRET = "#&%!&#%!@#*<FACELAND sdfkjsdrowBIANLA%^&*^(#@#>?N<:{HDSU(@#"+"8#qdCNbz5BgtR#PQ3Hqp2z8!DySKj8KV";

    public static class JwtMessage{
        public DecodedJWT jwt;
        public boolean result;
        public String errorMessage;

        public DecodedJWT getJwt() {
            return jwt;
        }

        public void setJwt(DecodedJWT jwt) {
            this.jwt = jwt;
        }

        public boolean isResult() {
            return result;
        }

        public void setResult(boolean result) {
            this.result = result;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

        public void setErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
        }
    }

    //Header
    private static Map<String, Object> generateHeader(String jwt){
        Map<String, Object> header = new HashMap<>();
        header.put("typ","JWT");
        switch(jwt){
            case "HS256":
                header.put("alg","HMAC256");
                break;
            case "HS384":
                header.put("alg","HMAC256");
                break;
            case "HS512":break;
            case "RS256":break;
            case "RS384":break;
            case "RS512":break;
            case "ES256":break;
            case "ES384":break;
            case "ES512":break;
            default:
                header.put("alg","HMAC256");
                break;
        }
        return header;
    }

    public static DecodedJWT decodedJWT(String token){
        try {
            DecodedJWT jwt = JWT.decode(token);
            return  jwt;
        } catch (JWTDecodeException exception){
            //Invalid token
        }
        return null;
    }

    public static String createToken(String from, String userId) {
        //过期时间(秒)
        int expiredMinutes = 1000*60*30;
        return createToken(from, userId, expiredMinutes);
    }

    public static String createToken(String from, String userId,long expireTime) {
        //过期时间(秒)
        long now = System.currentTimeMillis();
        try {
            String token = JWT.create()
                    //header
                    .withHeader(generateHeader("HS256"))
                    //payload
                    .withIssuer("bianla")//JWT的签发主体
                    .withIssuedAt(new Date(now))//JWT的签发时间
                    .withSubject("bianla.cn")//JWT的主体，即它的所有人
                    .withAudience(from)//JWT的接收对象
                    .withExpiresAt(new Date(now + expireTime)) //JWT的过期时间 30分钟
                    .withNotBefore(new Date(now))//JWT生效的开始时间
                    .withClaim("user_id", userId)
                    //signature
                    .sign(Algorithm.HMAC256(SECRET));
            System.out.println("token:"+token);
            return token;
        }  catch (JWTCreationException exception){
            //Invalid Signing configuration / Couldn't convert Claims.
        }

        return null;

    }
/*
iss(Issuser)：代表这个JWT的签发主体；
sub(Subject)：代表这个JWT的主体，即它的所有人；
aud(Audience)：代表这个JWT的接收对象；
exp(Expiration time)：是一个时间戳，代表这个JWT的过期时间；
nbf(Not Before)：是一个时间戳，代表这个JWT生效的开始时间，意味着在这个时间之前验证JWT是会失败的；
iat(Issued at)：是一个时间戳，代表这个JWT的签发时间；
jti(JWT ID)：是JWT的唯一标识。
 */

    //验证
    public static JwtMessage verify(String token, String userId){
        JwtMessage jwtMessage = new JwtMessage();
        jwtMessage.setResult(false);
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("bianla")
                    .build(); //Reusable verifier instance
            JWT.decode(token);
            DecodedJWT jwt = verifier.verify(token);
            Map<String, Claim> claims = jwt.getClaims();

            if(null!=claims.get("user_id")){
                System.out.println("token userId:"+claims.get("user_id").asString());
                System.out.println("post userId:"+userId);

                if(claims.get("user_id").asString().equals(userId)){
                    jwtMessage.setJwt(jwt);
                    jwtMessage.setResult(true);
                    return jwtMessage;
                }
            }

        } catch (JWTVerificationException exception){
            //Invalid signature/claims
            System.out.println(exception.getMessage());
            if(exception.getMessage().indexOf("expired")>0){
                jwtMessage.setErrorMessage("token-expired");
            }

        }

        return jwtMessage;
    }

    //每次请求验证token
    public static boolean checkToken(HttpServletRequest request, HttpServletResponse response, String userId){
        String token = request.getHeader("token");
        System.out.println("headerToken :" + token);

        if ((token != null) && (token.length() > 7)) {

                JwtMessage jwtMessage = verify(token,userId);
                if(jwtMessage.isResult() ){
                    DecodedJWT jwt = jwtMessage.getJwt();
                    System.out.println("jwt:"+(jwt == null));
                    if (jwt != null) {
                        System.out.println("get new jwt ");
                        //当旧Token快要过期时 ，发放新的token
                        String newToken = updateToken(jwt);
                        response.setHeader("token",newToken);
                        return true;
                    }
                }
        }
        return false;
    }

    public static boolean createAndSetToken(HttpServletRequest request, HttpServletResponse response, Integer userId) {
        String token = createToken("iamfront", userId + "");
        response.setHeader("token",token);
        return true;

    }

    public static String updateToken(DecodedJWT jwt) {
        //StaticLog.info("from {},accountId:{}",jwt.getClaim("from").asString(),jwt.getClaim("accountId").asString());
        Date expireTime = jwt.getExpiresAt();
        long expireMillis = transDateToMillsecond(expireTime);
        Date signTime = jwt.getIssuedAt();
        long signMillis = transDateToMillsecond(signTime);
        long now = System.currentTimeMillis();
//        double a = (expireMillis - signMillis) *1.0 / (expireMillis - now);
        double a = (now - signMillis) *1.0 / (expireMillis - signMillis);
        if(a>=0.5){
            return createToken(jwt.getClaim("from").asString(),jwt.getClaim("user_id").asString());
        }
        return jwt.getToken();
    }

    public static void main(String[] args) {
        String token1 = createToken("d","65");
        String token2 = createToken("d","66");

        System.out.println(token1);
        System.out.println(token2);
        System.out.println(verify(token1,"65"));

        String tt = "token has expired";
        System.out.println(tt.indexOf("expired"));

    }

    public static long transDateToMillsecond(Date date){
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date);
        return calendar1.getTimeInMillis() ;
    }
}
