package cn.faceland.springbootlearnredisgeo.service;

import cn.faceland.springbootlearnredisgeo.entity.UserInfo;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.GeoOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <h1>Geo 服务接口实现</h1>
 * Created by watermelon.
 */
@Slf4j
@Service
public class UserGeoServiceImpl implements IUserGeoService {
    private final String GEO_KEY = "ah-user";

    /** redis geo operation */
    private final GeoOperations geoOperations;

    @Autowired
    public UserGeoServiceImpl(StringRedisTemplate redisTemplate) {
        this.geoOperations = redisTemplate.opsForGeo();
    }

    @Override
    public Long saveUserInfoToRedis(Collection<UserInfo> users) {
        log.info("start to save user info: {}.", JSON.toJSONString(users));

        Set<RedisGeoCommands.GeoLocation<String>> locations = new HashSet<>();
        users.forEach(item ->
                locations.add(new RedisGeoCommands.GeoLocation<String>(item.getName(),new Point(item.getLongitude(),item.getLatitude())))
        );

        log.info("done to save user info.");

        return geoOperations.add(GEO_KEY, locations);
    }

    @Override
    public List<Point> getUserPos(String[] users) {
        log.info("start to save user info: {}.",JSON.toJSON(users));
        return geoOperations.position(GEO_KEY, users);
    }

    @Override
    public Distance getTwoUserDistance(String user1, String user2, Metric metric) {
        log.info("start to getTwoUserDistance info: user1:{};user2:{};metric:{}",user1,user2,JSON.toJSON(metric));
        return metric == null ?
                geoOperations.distance(GEO_KEY, user1, user2) : geoOperations.distance(GEO_KEY, user1, user2, metric);
    }

    @Override
    public GeoResults<RedisGeoCommands.GeoLocation<String>> getPointRadius(Circle within, RedisGeoCommands.GeoRadiusCommandArgs args) {
        log.info("start to getPointRadius info: within:{};args:{}",JSON.toJSON(within), JSON.toJSON(args));
        return args == null ?
                geoOperations.radius(GEO_KEY, within) : geoOperations.radius(GEO_KEY, within, args);
    }

    @Override
    public GeoResults<RedisGeoCommands.GeoLocation<String>> getMemberRadius(String member, Distance distance, RedisGeoCommands.GeoRadiusCommandArgs args) {
        log.info("start to getMemberRadius info: member:{};distance:{};args:{}",JSON.toJSON(member), JSON.toJSON(distance), JSON.toJSON(args));
        return args == null ?
                geoOperations.radius(GEO_KEY,member,distance) : geoOperations.radius(GEO_KEY,member,distance,args);
    }

    @Override
    public List<String> getUserGeoHash(String[] users) {
        log.info("start to getUserGeoHash info: users:{}",JSON.toJSON(users));

        return geoOperations.hash(GEO_KEY, users);
    }

    @Override
    public Long removeUserGeo(String user) {
        log.info("start to removeUserGeo info: user:{}",user);
        return  geoOperations.remove(GEO_KEY,user);
    }
}
