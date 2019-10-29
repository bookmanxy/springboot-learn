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

    /** fake some userInfos */
    private List<UserInfo> userInfos;

    @Autowired
    private IUserGeoService userGeoService;

    @Before
    public void init() {
        userInfos = new ArrayList<>();
        userInfos.add(new UserInfo("吕布-晓城天地", 120.337869, 30.316137));
        userInfos.add(new UserInfo("董卓-泰美国际", 120.323397, 30.315669));
        userInfos.add(new UserInfo("貂蝉-天街", 120.332937, 30.315653));
        userInfos.add(new UserInfo("孙权-四季风景苑", 120.354384, 30.308709));
        userInfos.add(new UserInfo("刘备-金毅", 120.378548, 30.310267));
        userInfos.add(new UserInfo("张飞-世茂江滨花园", 120.387873, 30.288627));
        userInfos.add(new UserInfo("关羽-东港花苑", 120.279479, 30.438134));
    }

    /**
     * <h2>测试 saveUserInfoToRedis 方法</h2>
     * 保存多个人的位置信息
     * */
    @Test
    public void testSaveUserInfoToRedis() {

        System.out.println(userGeoService.saveUserInfoToRedis(userInfos));
    }

    /**
     * <h2>测试 getUserPos 方法</h2>
     * 如果传递的 User 在 Redis 中没有记录, 会返回什么呢 ? 例如, 这里传递的 xxx
     * 获取多个人的位置信息
     * */
    @Test
    public void testGetUserPos() {

        System.out.println(JSON.toJSONString(userGeoService.getUserPos(
                Arrays.asList("吕布-晓城天地", "董卓-泰美国际", "xxx").toArray(new String[3])
        )));
    }

    /**
     * <h2>测试 getTwoUserDistance 方法</h2>
     * 获取两个人
     * */
    @Test
    public void testGetTwoUserDistance() {

        System.out.println(userGeoService.getTwoUserDistance("吕布-晓城天地", "貂蝉-天街", null).getValue() + " 米");
        System.out.println(userGeoService.getTwoUserDistance("吕布-晓城天地", "貂蝉-天街", Metrics.KILOMETERS).getValue() + " 公里");
    }

    /**
     * <h2>测试 getPointRadius 方法</h2>
     * 某个人附近的人
     * */
    @Test
    public void testGetPointRadius() {
        //吕布附近的人
        Point center = new Point(userInfos.get(0).getLongitude(), userInfos.get(0).getLatitude());
        //2公里范围内
        Distance radius = new Distance(2, Metrics.KILOMETERS);
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

        System.out.println(JSON.toJSONString(userGeoService.getMemberRadius("吕布-晓城天地", radius, null)));

        // order by 距离 limit 2, 同时返回距离中心点的距离
        RedisGeoCommands.GeoRadiusCommandArgs args =
                RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs().includeDistance().limit(2).sortAscending();
        System.out.println(JSON.toJSONString(userGeoService.getMemberRadius("吕布-晓城天地", radius, args)));
    }

    /**
     * <h2>测试 getUserGeoHash 方法</h2>
     * 获取某个地理位置的 geohash 值
     * */
    @Test
    public void testGetUserGeoHash() {

        System.out.println(JSON.toJSONString(userGeoService.getUserGeoHash(
                Arrays.asList("吕布-晓城天地", "suzhou", "xxx").toArray(new String[3])
        )));
    }
    /**
     * <h2>测试 testRemoveUserGeo 方法</h2>
     * 根据姓名移除某个人的地理信息
     * */
    @Test
    public void testRemoveUserGeo() {
        System.out.println(JSON.toJSONString(userGeoService.getUserPos(
                Arrays.asList("吕布-晓城天地", "董卓-泰美国际", "xxx").toArray(new String[3])
        )));

        System.out.println(userGeoService.removeUserGeo("董卓-泰美国际"));

        System.out.println(JSON.toJSONString(userGeoService.getUserPos(
                Arrays.asList("吕布-晓城天地", "董卓-泰美国际", "xxx").toArray(new String[3])
        )));
    }
}

