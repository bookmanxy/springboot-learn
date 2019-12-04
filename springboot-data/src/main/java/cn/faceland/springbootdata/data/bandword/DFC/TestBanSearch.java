package cn.faceland.springbootdata.data.bandword.DFC;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author watermelon
 * @Date 2019-12-02
 * @Description  词库来源：https://github.com/chason777777/mgck
 */
public class TestBanSearch {
    public static void main(String[] args) {
        //1:加载违禁词
        List<String> list = new ArrayList<>();
        list.add("E:\\文档\\banword\\暴恐词库.txt");
        list.add("E:\\文档\\banword\\反动词库.txt");
        list.add("E:\\文档\\banword\\民生词库.txt");
        list.add("E:\\文档\\banword\\其他词库.txt");
        list.add("E:\\文档\\banword\\色情词库.txt");
        list.add("E:\\文档\\banword\\贪腐词库.txt");
        SensitivewordFilter sensitivewordFilter = new SensitivewordFilter(list);

        //2：加载文章
        String content = "太多的伤感情怀也许只局限于饲养基地 荧幕中的情节，主人公尝试着去用某种方式渐渐的很潇洒地释自杀指南怀那些自己经历的伤感。"
                + "然后法轮功 我们的扮演的角色就是跟随着主人公的喜红客联盟 怒哀乐而过于牵强的把自己的情感也附加于银幕情节中，然后感动就流泪，"
                + "难过就躺在某一个人的怀里尽情的阐述心扉或者手机卡复制器一个人一杯红酒一部电影在夜三级片 深人静的晚上，关上电话静静的发呆着。";

        content = "5大发胖食物，还敢 贪污吃吗？<br/><p>　　大家都知道病从口入，其实胖也是从口入的。想减肥就要坚持忌口，很多容易发胖的食物还是最好别吃，虽然食物美味，但是为了减肥，为了美丽，还是先戒掉这些高热量食物吧，吃一口能胖好几斤呢!</p><p><br></p><p><b>　　1.发胖食物之糖果</b></p><p style=\"text-align: center;\"><b><img alt=\"untitled.jpg\" src=\"https://cdn3.bianla.cn/FlS7ftEsNDKcQ746LpncHyYDjXb3\" height=\"340\" width=\"480\"><br></b></p><p>　　糖果可谓是减肥的杀手，糖果的含糖量特别高，经常食用非常容易变胖，实乃女生的天敌。</p><p>　　怎么办：爱美MM们一定要杜绝吃糖，当你想吃糖果的时候，不妨吃一个新鲜的水果，这样既能满足嘴馋又能促进身体排出毒素，维持纤瘦身材哦。</p><p><br></p><p><b>　　2.发胖食物之巧克力</b></p><p style=\"text-align: center;\"><img alt=\"chocolate-183543_960_720.jpg\" src=\"https://cdn3.bianla.cn/FlwjFZ9oUbq2V_a9XvyP_KmO8f05\" height=\"320\" width=\"480\"><br></p><p>　　几乎每一个女生都喜欢吃巧克力，但是巧克力的热量很高，可以说是甜食中女生减肥的第一大天敌。经常食用巧克力最容易导致身材走样。不过，这种吃巧克力导致肥胖主要原因不在于巧克力本身，而是因为吃的过量和缺乏运动。</p><p>　　怎么办：喜爱吃巧克力的人只要注意吃的适量，还有通过增加适当的运动来维持能量平衡就不会引起肥胖。</p><p><br></p><p><b>　　3.发胖食物之碳酸饮料</b></p><p style=\"text-align: center;\"><b><img alt=\"child-643438_960_720.jpg\" src=\"https://cdn3.bianla.cn/FiMkCFKHAuM22TyXaSZTgHwvD0XI\" height=\"319\" width=\"480\"><br></b></p><p>　　比如汽水、可乐、沙士等热量很高，因此，经常饮用会使人体摄入过多的热量，体重增加是早晚的事情。</p><p>　　怎么办：想要瘦的MM应马上放弃汽水，可改为喝茶，喝茶既能燃烧脂肪，还能清理肠胃，最适合减肥哦。</p><p><br></p><p><b>　　4.发胖食物之方便面</b></p><p style=\"text-align: center;\"><b><img alt=\"if-the-203517_960_720.jpg\" src=\"https://cdn3.bianla.cn/Fj0nU59UVNeEzcdGR44D757WL5Ku\" height=\"360\" width=\"480\"><br></b></p><p>　　如今很多人懒于做法，因此，简洁方便的方便面几乎成了第一选择。可是，殊不知方便面是纯热量食品，一包方便面的热量是470卡(100克)，几乎相当于两碗饭。而且，方面便又没有任何营养，盐分过高，含损肝的防腐剂、香精。</p><p>　　怎么办：方便面不仅容易发胖，因此一定要少吃。就算实在嘴馋，适当尝一下就行，切莫多吃。</p><p><br></p><p><b>　　5.发胖食物之罐装果汁</b></p><p style=\"text-align: center;\"><b><img alt=\"beverage-cans-1058702_960_720.jpg\" src=\"https://cdn3.bianla.cn/FqVgiuNTviiCX9Ae94GIDeNwDK8X\" height=\"318\" width=\"480\"><br></b></p><p>　　别以为灌装果汁热量就会很低，灌装果汁虽然没有多少碳水化合物，但是含糖量却比较高，如果长期饮用还是会变胖的。</p><p>　　怎么办：如果实在喜欢喝果汁，不妨买水果回家自己榨取吧，自己鲜榨的果汁既美味又健康瘦身哦。</p>";
        //3：违禁词匹配校验
        System.out.println("文章是否包含敏感词：" + sensitivewordFilter.isContaintSensitiveWord(content,1));

        long beginTime = System.currentTimeMillis();
        Set<String> set = sensitivewordFilter.getSensitiveWord(content, 2);
        long endTime = System.currentTimeMillis();

        System.out.println("语句中包含敏感词的个数为：" + set.size() + "。包含：" + set);
//        System.out.println("文章包含的所有敏感词：" );
//        for(String str : set){
//            System.out.println("             " + str);
//        }

        System.out.println("总共消耗时间为：" + (endTime - beginTime));
    }
}
