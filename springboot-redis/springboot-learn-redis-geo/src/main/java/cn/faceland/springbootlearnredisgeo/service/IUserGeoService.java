package cn.faceland.springbootlearnredisgeo.service;

import cn.faceland.springbootlearnredisgeo.entity.UserInfo;
import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;

import java.util.Collection;
import java.util.List;

/**
 * <h1>Geo 服务接口定义</h1>
 * Created by watermelon.
 */
public interface IUserGeoService {

    /**
     * <h2>把用户信息保存到 Redis 中</h2>
     * @param userInfos {@link UserInfo}
     * @return 成功保存的个数
     * */
    Long saveCityInfoToRedis(Collection<UserInfo> userInfos);

    /**
     * <h2>获取给定用户的坐标</h2>
     * @param cities 给定用户 key
     * @return {@link Point}s
     * */
    List<Point> getCityPos(String[] cities);

    /**
     * <h2>获取两个用户之间的距离</h2>
     * @param city1 第一个用户
     * @param city2 第二个用户
     * @param metric {@link Metric} 单位信息, 可以是 null
     * @return {@link Distance}
     * */
    Distance getTwoCityDistance(String city1, String city2, Metric metric);

    /**
     * <h2>根据给定地理位置坐标获取指定范围内的地理位置集合</h2>
     * @param within {@link Circle} 中心点和距离
     * @param args {@link RedisGeoCommands.GeoRadiusCommandArgs} 限制返回的个数和排序方式, 可以是 null
     * @return {@link RedisGeoCommands.GeoLocation}
     * */
    GeoResults<RedisGeoCommands.GeoLocation<String>> getPointRadius(
            Circle within, RedisGeoCommands.GeoRadiusCommandArgs args);

    /**
     * <h2>根据给定地理位置获取指定范围内的地理位置集合</h2>
     * */
    GeoResults<RedisGeoCommands.GeoLocation<String>> getMemberRadius(
            String member, Distance distance, RedisGeoCommands.GeoRadiusCommandArgs args);

    /**
     * <h2>获取某个地理位置的 geohash 值</h2>
     * @param cities 给定用户 key
     * @return city geohashs
     * */
    List<String> getCityGeoHash(String[] cities);
}

