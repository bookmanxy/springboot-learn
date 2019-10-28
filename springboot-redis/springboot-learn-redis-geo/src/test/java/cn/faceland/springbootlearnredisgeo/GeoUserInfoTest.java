package cn.faceland.springbootlearnredisgeo;

import cn.faceland.springbootlearnredisgeo.entity.UserInfo;
import cn.faceland.springbootlearnredisgeo.service.IUserGeoService;
import com.alibaba.fastjson.JSON;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <h1>GeoService 测试用例</h1>
 * Created by Qinyi. 参考：http://www.imooc.com/article/280622?block_id=tuijian_wz
 */
@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {BlogTestApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.NONE)
@SpringBootTest
public class GeoUserInfoTest {

    /** fake some cityInfos */
    private List<UserInfo> cityInfos;

    @Autowired
    private IUserGeoService userGeoService;

    @Before
    public void init() {

        cityInfos = new ArrayList<>();

        cityInfos.add(new UserInfo("吕布", 117.17, 31.52));
        cityInfos.add(new UserInfo("董卓", 117.02, 30.31));
        cityInfos.add(new UserInfo("貂蝉", 116.47, 33.57));
        cityInfos.add(new UserInfo("孙权", 116.58, 33.38));
        cityInfos.add(new UserInfo("刘备", 115.48, 32.54));
        cityInfos.add(new UserInfo("张飞", 117.21, 32.56));
        cityInfos.add(new UserInfo("关羽", 118.18, 29.43));
    }

    /**
     * <h2>测试 saveCityInfoToRedis 方法</h2>
     * */
    @Test
    public void testSaveCityInfoToRedis() {

        System.out.println(userGeoService.saveCityInfoToRedis(cityInfos));
    }

    /**
     * <h2>测试 getCityPos 方法</h2>
     * 如果传递的 city 在 Redis 中没有记录, 会返回什么呢 ? 例如, 这里传递的 xxx
     * */
    @Test
    public void testGetCityPos() {

        System.out.println(JSON.toJSONString(userGeoService.getCityPos(
                Arrays.asList("anqing", "suzhou", "xxx").toArray(new String[3])
        )));
    }

    /**
     * <h2>测试 getTwoCityDistance 方法</h2>
     * */
    @Test
    public void testGetTwoCityDistance() {

        System.out.println(userGeoService.getTwoCityDistance("anqing", "suzhou", null).getValue());
        System.out.println(userGeoService.getTwoCityDistance("anqing", "suzhou", Metrics.KILOMETERS).getValue());
    }

    /**
     * <h2>测试 getPointRadius 方法</h2>
     * */
    @Test
    public void testGetPointRadius() {

        Point center = new Point(cityInfos.get(0).getLongitude(), cityInfos.get(0).getLatitude());
        Distance radius = new Distance(200, Metrics.KILOMETERS);
        Circle within = new Circle(center, radius);

        System.out.println(JSON.toJSONString(userGeoService.getPointRadius(within, null)));

        // order by 距离 limit 2, 同时返回距离中心点的距离
        RedisGeoCommands.GeoRadiusCommandArgs args =
                RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs().includeDistance().limit(2).sortAscending();
        System.out.println(JSON.toJSONString(userGeoService.getPointRadius(within, args)));
    }

    /**
     * <h2>测试 getMemberRadius 方法</h2>
     * */
    @Test
    public void testGetMemberRadius() {

        Distance radius = new Distance(200, Metrics.KILOMETERS);

        System.out.println(JSON.toJSONString(userGeoService.getMemberRadius("suzhou", radius, null)));

        // order by 距离 limit 2, 同时返回距离中心点的距离
        RedisGeoCommands.GeoRadiusCommandArgs args =
                RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs().includeDistance().limit(2).sortAscending();
        System.out.println(JSON.toJSONString(userGeoService.getMemberRadius("suzhou", radius, args)));
    }

    /**
     * <h2>测试 getCityGeoHash 方法</h2>
     * */
    @Test
    public void testGetCityGeoHash() {

        System.out.println(JSON.toJSONString(userGeoService.getCityGeoHash(
                Arrays.asList("anqing", "suzhou", "xxx").toArray(new String[3])
        )));
    }
}

