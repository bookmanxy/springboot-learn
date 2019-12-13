package cn.faceland.springbootjson.jsondeal;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author watermelon
 * @Date 2019-12-04
 * @Description
 */
public class JsonUtil {

    public static void main(String[] args) {
        String test = "{\n" +
                "    \"code\": 1,\n" +
                "    \"success\": true,\n" +
                "    \"alertMsg\": \"\",\n" +
                "    \"data\": {\n" +
                "        \"name\":\"ss\"\n" +
                "\t\t}\n" +
                "}";

        test = "{\n" +
                "    \"code\": 1,\n" +
                "    \"success\": true,\n" +
                "    \"alertMsg\": \"\",\n" +
                "    \"data\": {\n" +
                "\t\n" +
                "        \"pageResBean\":[{\"name\":\"2\"},{\"name\":\"4\"}]\n" +
                "\t\t}\n" +
                "}";
        test = "{\n" +
                "    \"code\": 1,\n" +
                "    \"success\": true,\n" +
                "    \"alertMsg\": \"\",\n" +
                "    \"data\": {\n" +
                "        \"pageResBean\": {\n" +
                "            \"total\": 8,\n" +
                "            \"totalPage\": 1,\n" +
                "            \"pageNo\": 1,\n" +
                "            \"page\": 1,\n" +
                "            \"pageSize\": 15,\n" +
                "            \"list\": [\n" +
                "                {\n" +
                "                    \"id\": 874608,\n" +
                "                    \"title\": \"\",\n" +
                "                    \"userId\": 1426696,\n" +
                "                    \"content\": \"每天早上看到自己，心情都是美美哒\",\n" +
                "                    \"position\": \"河南省焦作市山阳区\",\n" +
                "                    \"hasImage\": true,\n" +
                "                    \"isMixed\": 0,\n" +
                "                    \"shareIcon\": \"https://pic.bianla.cn/sharepic/20180815/d06e815058034c96acd665e7d004d5cc.jpg\",\n" +
                "                    \"auditStatus\": 1,\n" +
                "                    \"oldAuditStatus\": null,\n" +
                "                    \"latestReplyTime\": \"2018-08-15 06:56:45\",\n" +
                "                    \"permission\": 0,\n" +
                "                    \"externalUrl\": \"\",\n" +
                "                    \"isRecommend\": false,\n" +
                "                    \"isStickTop\": 0,\n" +
                "                    \"activityType\": 0,\n" +
                "                    \"topicType\": 0,\n" +
                "                    \"oldTopicType\": null,\n" +
                "                    \"visualization\": null,\n" +
                "                    \"popularizeType\": 0,\n" +
                "                    \"isConfirmPass\": null,\n" +
                "                    \"themeIds\": [],\n" +
                "                    \"mergePicture\": null,\n" +
                "                    \"firstWeight\": null,\n" +
                "                    \"lastWeight\": null,\n" +
                "                    \"totalFatReduce\": null,\n" +
                "                    \"totalWeightReduce\": null,\n" +
                "                    \"totalReduceDay\": null,\n" +
                "                    \"versionNumber\": null,\n" +
                "                    \"physiclalStatus\": \"\",\n" +
                "                    \"isConfirm\": null,\n" +
                "                    \"physiclalDetails\": [],\n" +
                "                    \"physiclalRemark\": \"\",\n" +
                "                    \"canEdit\": false,\n" +
                "                    \"ketoneImgUrl\": \"\",\n" +
                "                    \"physiclalPrompt\": [],\n" +
                "                    \"deleteImgIds\": [],\n" +
                "                    \"showInStarGym\": 0,\n" +
                "                    \"starGymRelation\": null,\n" +
                "                    \"reduceDetail\": {},\n" +
                "                    \"reduceDay\": null,\n" +
                "                    \"reduceWeight\": null,\n" +
                "                    \"reduceFat\": null,\n" +
                "                    \"ketoneTime\": null,\n" +
                "                    \"ketoneValue\": 0,\n" +
                "                    \"diaryTopic\": null,\n" +
                "                    \"extraData\": {},\n" +
                "                    \"question\": \"\",\n" +
                "                    \"description\": \"\",\n" +
                "                    \"showType\": null,\n" +
                "                    \"isStopReduceFat\": null,\n" +
                "                    \"isHorizontal\": false,\n" +
                "                    \"topicTag\": \"0\",\n" +
                "                    \"userTag\": \"0\",\n" +
                "                    \"customTag\": \"0\",\n" +
                "                    \"topicTagsLevel1\": \"\",\n" +
                "                    \"userTagsLevel1\": \"\",\n" +
                "                    \"baikeItems\": \"\",\n" +
                "                    \"baikeItemsLevel1\": \"\",\n" +
                "                    \"latestReplyTimeStr\": \"2018-08-15\",\n" +
                "                    \"imgList\": [\n" +
                "                        {\n" +
                "                            \"originUrl\": \"https://pic.bianla.cn/topic/20180815/88c97021197143999e6bf7ff26f05b02.jpg\",\n" +
                "                            \"thumbUrl\": \"https://pic.bianla.cn/thumbnail/20180815/946d2b8a9a094c25843c45b2f861c290.jpg\",\n" +
                "                            \"thumbWidth\": 440,\n" +
                "                            \"thumbHeight\": 440\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"shareCount\": 1,\n" +
                "                    \"likeCount\": 5,\n" +
                "                    \"replyCount\": 6,\n" +
                "                    \"collectCount\": 2,\n" +
                "                    \"clickCount\": 76,\n" +
                "                    \"isMine\": false,\n" +
                "                    \"isFollowed\": false,\n" +
                "                    \"isCollected\": false,\n" +
                "                    \"isPraised\": false,\n" +
                "                    \"user\": null,\n" +
                "                    \"video\": null,\n" +
                "                    \"videoUrl\": \"\",\n" +
                "                    \"shareSign\": 0,\n" +
                "                    \"bestComment\": \"\",\n" +
                "                    \"deleteUser\": \"\",\n" +
                "                    \"userTagStr\": \"\",\n" +
                "                    \"topicTagStr\": \"\",\n" +
                "                    \"customTagStr\": \"\",\n" +
                "                    \"isSpecialRecommend\": false\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 606855,\n" +
                "                    \"title\": \"\",\n" +
                "                    \"userId\": 1426696,\n" +
                "                    \"content\": \"犹记得去年天还没冷的时候第一次接触变啦，那时的我还是个胖子。现在，我更爱自己了。\",\n" +
                "                    \"position\": \"河南省焦作市山阳区\",\n" +
                "                    \"hasImage\": true,\n" +
                "                    \"isMixed\": 0,\n" +
                "                    \"shareIcon\": \"https://pic.bianla.cn/sharepic/20180515/874ab7b43ed441a6981fb2c7abcd6aef.jpg\",\n" +
                "                    \"auditStatus\": 1,\n" +
                "                    \"oldAuditStatus\": null,\n" +
                "                    \"latestReplyTime\": \"2018-05-15 00:03:29\",\n" +
                "                    \"permission\": 0,\n" +
                "                    \"externalUrl\": \"\",\n" +
                "                    \"isRecommend\": false,\n" +
                "                    \"isStickTop\": 0,\n" +
                "                    \"activityType\": 0,\n" +
                "                    \"topicType\": 0,\n" +
                "                    \"oldTopicType\": null,\n" +
                "                    \"visualization\": null,\n" +
                "                    \"popularizeType\": 0,\n" +
                "                    \"isConfirmPass\": null,\n" +
                "                    \"themeIds\": [],\n" +
                "                    \"mergePicture\": null,\n" +
                "                    \"firstWeight\": null,\n" +
                "                    \"lastWeight\": null,\n" +
                "                    \"totalFatReduce\": null,\n" +
                "                    \"totalWeightReduce\": null,\n" +
                "                    \"totalReduceDay\": null,\n" +
                "                    \"versionNumber\": null,\n" +
                "                    \"physiclalStatus\": \"\",\n" +
                "                    \"isConfirm\": null,\n" +
                "                    \"physiclalDetails\": [],\n" +
                "                    \"physiclalRemark\": \"\",\n" +
                "                    \"canEdit\": false,\n" +
                "                    \"ketoneImgUrl\": \"\",\n" +
                "                    \"physiclalPrompt\": [],\n" +
                "                    \"deleteImgIds\": [],\n" +
                "                    \"showInStarGym\": 0,\n" +
                "                    \"starGymRelation\": null,\n" +
                "                    \"reduceDetail\": {},\n" +
                "                    \"reduceDay\": null,\n" +
                "                    \"reduceWeight\": null,\n" +
                "                    \"reduceFat\": null,\n" +
                "                    \"ketoneTime\": null,\n" +
                "                    \"ketoneValue\": 0,\n" +
                "                    \"diaryTopic\": null,\n" +
                "                    \"extraData\": {},\n" +
                "                    \"question\": \"\",\n" +
                "                    \"description\": \"\",\n" +
                "                    \"showType\": null,\n" +
                "                    \"isStopReduceFat\": null,\n" +
                "                    \"isHorizontal\": false,\n" +
                "                    \"topicTag\": \"0\",\n" +
                "                    \"userTag\": \"0\",\n" +
                "                    \"customTag\": \"0\",\n" +
                "                    \"topicTagsLevel1\": \"\",\n" +
                "                    \"userTagsLevel1\": \"\",\n" +
                "                    \"baikeItems\": \"\",\n" +
                "                    \"baikeItemsLevel1\": \"\",\n" +
                "                    \"latestReplyTimeStr\": \"2018-05-15\",\n" +
                "                    \"imgList\": [\n" +
                "                        {\n" +
                "                            \"originUrl\": \"https://pic.bianla.cn/topic/20180515/ae116b75e20b41fdb39c3a5e01b2f06c.jpg\",\n" +
                "                            \"thumbUrl\": \"https://pic.bianla.cn/thumbnail/20180515/7ff486f8441a487fbb1a84a10e7e4ac9.jpg\",\n" +
                "                            \"thumbWidth\": null,\n" +
                "                            \"thumbHeight\": null\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"originUrl\": \"https://pic.bianla.cn/topic/20180515/be66e21663404545997342624d48ca7d.jpg\",\n" +
                "                            \"thumbUrl\": \"https://pic.bianla.cn/thumbnail/20180515/e6968f7e37c94fb4a5afab972c87a7d1.jpg\",\n" +
                "                            \"thumbWidth\": null,\n" +
                "                            \"thumbHeight\": null\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"shareCount\": 5,\n" +
                "                    \"likeCount\": 21,\n" +
                "                    \"replyCount\": 5,\n" +
                "                    \"collectCount\": 3,\n" +
                "                    \"clickCount\": 302,\n" +
                "                    \"isMine\": false,\n" +
                "                    \"isFollowed\": false,\n" +
                "                    \"isCollected\": false,\n" +
                "                    \"isPraised\": false,\n" +
                "                    \"user\": null,\n" +
                "                    \"video\": null,\n" +
                "                    \"videoUrl\": \"\",\n" +
                "                    \"shareSign\": 0,\n" +
                "                    \"bestComment\": \"\",\n" +
                "                    \"deleteUser\": \"\",\n" +
                "                    \"userTagStr\": \"\",\n" +
                "                    \"topicTagStr\": \"\",\n" +
                "                    \"customTagStr\": \"\",\n" +
                "                    \"isSpecialRecommend\": false\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 548189,\n" +
                "                    \"title\": \"\",\n" +
                "                    \"userId\": 1426696,\n" +
                "                    \"content\": \"早，美好的一天开始了\",\n" +
                "                    \"position\": \"河南省焦作市山阳区\",\n" +
                "                    \"hasImage\": true,\n" +
                "                    \"isMixed\": 0,\n" +
                "                    \"shareIcon\": \"https://pic.bianla.cn/sharepic/20180430/4c69537880fe418686d36d576afc1a7f.jpg\",\n" +
                "                    \"auditStatus\": 1,\n" +
                "                    \"oldAuditStatus\": null,\n" +
                "                    \"latestReplyTime\": \"2018-04-30 06:15:22\",\n" +
                "                    \"permission\": 0,\n" +
                "                    \"externalUrl\": \"\",\n" +
                "                    \"isRecommend\": false,\n" +
                "                    \"isStickTop\": 0,\n" +
                "                    \"activityType\": 0,\n" +
                "                    \"topicType\": 0,\n" +
                "                    \"oldTopicType\": null,\n" +
                "                    \"visualization\": null,\n" +
                "                    \"popularizeType\": 0,\n" +
                "                    \"isConfirmPass\": null,\n" +
                "                    \"themeIds\": [],\n" +
                "                    \"mergePicture\": null,\n" +
                "                    \"firstWeight\": null,\n" +
                "                    \"lastWeight\": null,\n" +
                "                    \"totalFatReduce\": null,\n" +
                "                    \"totalWeightReduce\": null,\n" +
                "                    \"totalReduceDay\": null,\n" +
                "                    \"versionNumber\": null,\n" +
                "                    \"physiclalStatus\": \"\",\n" +
                "                    \"isConfirm\": null,\n" +
                "                    \"physiclalDetails\": [],\n" +
                "                    \"physiclalRemark\": \"\",\n" +
                "                    \"canEdit\": false,\n" +
                "                    \"ketoneImgUrl\": \"\",\n" +
                "                    \"physiclalPrompt\": [],\n" +
                "                    \"deleteImgIds\": [],\n" +
                "                    \"showInStarGym\": 0,\n" +
                "                    \"starGymRelation\": null,\n" +
                "                    \"reduceDetail\": {},\n" +
                "                    \"reduceDay\": null,\n" +
                "                    \"reduceWeight\": null,\n" +
                "                    \"reduceFat\": null,\n" +
                "                    \"ketoneTime\": null,\n" +
                "                    \"ketoneValue\": 0,\n" +
                "                    \"diaryTopic\": null,\n" +
                "                    \"extraData\": {},\n" +
                "                    \"question\": \"\",\n" +
                "                    \"description\": \"\",\n" +
                "                    \"showType\": null,\n" +
                "                    \"isStopReduceFat\": null,\n" +
                "                    \"isHorizontal\": false,\n" +
                "                    \"topicTag\": \"0\",\n" +
                "                    \"userTag\": \"0\",\n" +
                "                    \"customTag\": \"0\",\n" +
                "                    \"topicTagsLevel1\": \"\",\n" +
                "                    \"userTagsLevel1\": \"\",\n" +
                "                    \"baikeItems\": \"\",\n" +
                "                    \"baikeItemsLevel1\": \"\",\n" +
                "                    \"latestReplyTimeStr\": \"2018-04-30\",\n" +
                "                    \"imgList\": [\n" +
                "                        {\n" +
                "                            \"originUrl\": \"https://pic.bianla.cn/topic/20180430/2d82255e4e3b4ce489641b9a14b04f22.jpg\",\n" +
                "                            \"thumbUrl\": \"https://pic.bianla.cn/thumbnail/20180430/53e3ec76e3864fbf9db7ce4e0fac7de7.jpg\",\n" +
                "                            \"thumbWidth\": 258,\n" +
                "                            \"thumbHeight\": 460\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"shareCount\": 0,\n" +
                "                    \"likeCount\": 6,\n" +
                "                    \"replyCount\": 1,\n" +
                "                    \"collectCount\": 2,\n" +
                "                    \"clickCount\": 29,\n" +
                "                    \"isMine\": false,\n" +
                "                    \"isFollowed\": false,\n" +
                "                    \"isCollected\": false,\n" +
                "                    \"isPraised\": false,\n" +
                "                    \"user\": null,\n" +
                "                    \"video\": null,\n" +
                "                    \"videoUrl\": \"\",\n" +
                "                    \"shareSign\": 0,\n" +
                "                    \"bestComment\": \"\",\n" +
                "                    \"deleteUser\": \"\",\n" +
                "                    \"userTagStr\": \"\",\n" +
                "                    \"topicTagStr\": \"\",\n" +
                "                    \"customTagStr\": \"\",\n" +
                "                    \"isSpecialRecommend\": false\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 546708,\n" +
                "                    \"title\": \"\",\n" +
                "                    \"userId\": 1426696,\n" +
                "                    \"content\": \"最开心的事情莫过于自己努力后的收获吧！\",\n" +
                "                    \"position\": \"河南省焦作市山阳区\",\n" +
                "                    \"hasImage\": true,\n" +
                "                    \"isMixed\": 0,\n" +
                "                    \"shareIcon\": \"https://pic.bianla.cn/sharepic/20180429/b410b32164594119a8d8c850708ba9f5.jpg\",\n" +
                "                    \"auditStatus\": 1,\n" +
                "                    \"oldAuditStatus\": null,\n" +
                "                    \"latestReplyTime\": \"2018-04-29 12:19:25\",\n" +
                "                    \"permission\": 0,\n" +
                "                    \"externalUrl\": \"\",\n" +
                "                    \"isRecommend\": false,\n" +
                "                    \"isStickTop\": 0,\n" +
                "                    \"activityType\": 0,\n" +
                "                    \"topicType\": 0,\n" +
                "                    \"oldTopicType\": null,\n" +
                "                    \"visualization\": null,\n" +
                "                    \"popularizeType\": 0,\n" +
                "                    \"isConfirmPass\": null,\n" +
                "                    \"themeIds\": [],\n" +
                "                    \"mergePicture\": null,\n" +
                "                    \"firstWeight\": null,\n" +
                "                    \"lastWeight\": null,\n" +
                "                    \"totalFatReduce\": null,\n" +
                "                    \"totalWeightReduce\": null,\n" +
                "                    \"totalReduceDay\": null,\n" +
                "                    \"versionNumber\": null,\n" +
                "                    \"physiclalStatus\": \"\",\n" +
                "                    \"isConfirm\": null,\n" +
                "                    \"physiclalDetails\": [],\n" +
                "                    \"physiclalRemark\": \"\",\n" +
                "                    \"canEdit\": false,\n" +
                "                    \"ketoneImgUrl\": \"\",\n" +
                "                    \"physiclalPrompt\": [],\n" +
                "                    \"deleteImgIds\": [],\n" +
                "                    \"showInStarGym\": 0,\n" +
                "                    \"starGymRelation\": null,\n" +
                "                    \"reduceDetail\": {},\n" +
                "                    \"reduceDay\": null,\n" +
                "                    \"reduceWeight\": null,\n" +
                "                    \"reduceFat\": null,\n" +
                "                    \"ketoneTime\": null,\n" +
                "                    \"ketoneValue\": 0,\n" +
                "                    \"diaryTopic\": null,\n" +
                "                    \"extraData\": {},\n" +
                "                    \"question\": \"\",\n" +
                "                    \"description\": \"\",\n" +
                "                    \"showType\": null,\n" +
                "                    \"isStopReduceFat\": null,\n" +
                "                    \"isHorizontal\": false,\n" +
                "                    \"topicTag\": \"0\",\n" +
                "                    \"userTag\": \"0\",\n" +
                "                    \"customTag\": \"0\",\n" +
                "                    \"topicTagsLevel1\": \"\",\n" +
                "                    \"userTagsLevel1\": \"\",\n" +
                "                    \"baikeItems\": \"\",\n" +
                "                    \"baikeItemsLevel1\": \"\",\n" +
                "                    \"latestReplyTimeStr\": \"2018-04-29\",\n" +
                "                    \"imgList\": [\n" +
                "                        {\n" +
                "                            \"originUrl\": \"https://pic.bianla.cn/topic/20180429/f69afd7ca3a7419cbfd15dddea6cef24.jpg\",\n" +
                "                            \"thumbUrl\": \"https://pic.bianla.cn/thumbnail/20180429/d3e2ea324c6c446b900d362e1032ec06.jpg\",\n" +
                "                            \"thumbWidth\": 460,\n" +
                "                            \"thumbHeight\": 458\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"shareCount\": 5,\n" +
                "                    \"likeCount\": 8,\n" +
                "                    \"replyCount\": 4,\n" +
                "                    \"collectCount\": 2,\n" +
                "                    \"clickCount\": 59,\n" +
                "                    \"isMine\": false,\n" +
                "                    \"isFollowed\": false,\n" +
                "                    \"isCollected\": false,\n" +
                "                    \"isPraised\": false,\n" +
                "                    \"user\": null,\n" +
                "                    \"video\": null,\n" +
                "                    \"videoUrl\": \"\",\n" +
                "                    \"shareSign\": 0,\n" +
                "                    \"bestComment\": \"\",\n" +
                "                    \"deleteUser\": \"\",\n" +
                "                    \"userTagStr\": \"\",\n" +
                "                    \"topicTagStr\": \"\",\n" +
                "                    \"customTagStr\": \"\",\n" +
                "                    \"isSpecialRecommend\": false\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 523297,\n" +
                "                    \"title\": \"\",\n" +
                "                    \"userId\": 1426696,\n" +
                "                    \"content\": \"从XXXL到S的轻松路程，唯有变啦，遇见更美的自己\",\n" +
                "                    \"position\": \"河南省焦作市山阳区\",\n" +
                "                    \"hasImage\": true,\n" +
                "                    \"isMixed\": 0,\n" +
                "                    \"shareIcon\": \"https://pic.bianla.cn/sharepic/20180424/26977ab8a2724700b8a25a831c0e9235.jpg\",\n" +
                "                    \"auditStatus\": 1,\n" +
                "                    \"oldAuditStatus\": null,\n" +
                "                    \"latestReplyTime\": \"2018-04-24 01:06:04\",\n" +
                "                    \"permission\": 0,\n" +
                "                    \"externalUrl\": \"\",\n" +
                "                    \"isRecommend\": false,\n" +
                "                    \"isStickTop\": 0,\n" +
                "                    \"activityType\": 0,\n" +
                "                    \"topicType\": 0,\n" +
                "                    \"oldTopicType\": null,\n" +
                "                    \"visualization\": null,\n" +
                "                    \"popularizeType\": 0,\n" +
                "                    \"isConfirmPass\": null,\n" +
                "                    \"themeIds\": [],\n" +
                "                    \"mergePicture\": null,\n" +
                "                    \"firstWeight\": null,\n" +
                "                    \"lastWeight\": null,\n" +
                "                    \"totalFatReduce\": null,\n" +
                "                    \"totalWeightReduce\": null,\n" +
                "                    \"totalReduceDay\": null,\n" +
                "                    \"versionNumber\": null,\n" +
                "                    \"physiclalStatus\": \"\",\n" +
                "                    \"isConfirm\": null,\n" +
                "                    \"physiclalDetails\": [],\n" +
                "                    \"physiclalRemark\": \"\",\n" +
                "                    \"canEdit\": false,\n" +
                "                    \"ketoneImgUrl\": \"\",\n" +
                "                    \"physiclalPrompt\": [],\n" +
                "                    \"deleteImgIds\": [],\n" +
                "                    \"showInStarGym\": 0,\n" +
                "                    \"starGymRelation\": null,\n" +
                "                    \"reduceDetail\": {},\n" +
                "                    \"reduceDay\": null,\n" +
                "                    \"reduceWeight\": null,\n" +
                "                    \"reduceFat\": null,\n" +
                "                    \"ketoneTime\": null,\n" +
                "                    \"ketoneValue\": 0,\n" +
                "                    \"diaryTopic\": null,\n" +
                "                    \"extraData\": {},\n" +
                "                    \"question\": \"\",\n" +
                "                    \"description\": \"\",\n" +
                "                    \"showType\": null,\n" +
                "                    \"isStopReduceFat\": null,\n" +
                "                    \"isHorizontal\": false,\n" +
                "                    \"topicTag\": \"0\",\n" +
                "                    \"userTag\": \"0\",\n" +
                "                    \"customTag\": \"0\",\n" +
                "                    \"topicTagsLevel1\": \"\",\n" +
                "                    \"userTagsLevel1\": \"\",\n" +
                "                    \"baikeItems\": \"\",\n" +
                "                    \"baikeItemsLevel1\": \"\",\n" +
                "                    \"latestReplyTimeStr\": \"2018-04-24\",\n" +
                "                    \"imgList\": [\n" +
                "                        {\n" +
                "                            \"originUrl\": \"https://pic.bianla.cn/topic/20180424/31b90770d4bf44e28f1cbbd410d11625.jpg\",\n" +
                "                            \"thumbUrl\": \"https://pic.bianla.cn/thumbnail/20180424/909c6aa4a4984dee98d7e301065a6d90.jpg\",\n" +
                "                            \"thumbWidth\": null,\n" +
                "                            \"thumbHeight\": null\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"originUrl\": \"https://pic.bianla.cn/topic/20180424/df5cb94c276d4dda9350d5b737b52c11.jpg\",\n" +
                "                            \"thumbUrl\": \"https://pic.bianla.cn/thumbnail/20180424/2a7ed361f3864192a9cccdbcc8406643.jpg\",\n" +
                "                            \"thumbWidth\": null,\n" +
                "                            \"thumbHeight\": null\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"shareCount\": 1,\n" +
                "                    \"likeCount\": 22,\n" +
                "                    \"replyCount\": 1,\n" +
                "                    \"collectCount\": 1,\n" +
                "                    \"clickCount\": 247,\n" +
                "                    \"isMine\": false,\n" +
                "                    \"isFollowed\": false,\n" +
                "                    \"isCollected\": false,\n" +
                "                    \"isPraised\": false,\n" +
                "                    \"user\": null,\n" +
                "                    \"video\": null,\n" +
                "                    \"videoUrl\": \"\",\n" +
                "                    \"shareSign\": 0,\n" +
                "                    \"bestComment\": \"\",\n" +
                "                    \"deleteUser\": \"\",\n" +
                "                    \"userTagStr\": \"\",\n" +
                "                    \"topicTagStr\": \"\",\n" +
                "                    \"customTagStr\": \"\",\n" +
                "                    \"isSpecialRecommend\": false\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 493002,\n" +
                "                    \"title\": \"\",\n" +
                "                    \"userId\": 1426696,\n" +
                "                    \"content\": \"去年的我和今年的我\",\n" +
                "                    \"position\": \"浙江省金华市义乌市\",\n" +
                "                    \"hasImage\": true,\n" +
                "                    \"isMixed\": 0,\n" +
                "                    \"shareIcon\": \"https://pic.bianla.cn/sharepic/20180416/0799a7f8d624487eba2ca9b5500e1247.jpg\",\n" +
                "                    \"auditStatus\": 1,\n" +
                "                    \"oldAuditStatus\": null,\n" +
                "                    \"latestReplyTime\": \"2018-04-16 16:39:01\",\n" +
                "                    \"permission\": 0,\n" +
                "                    \"externalUrl\": \"\",\n" +
                "                    \"isRecommend\": false,\n" +
                "                    \"isStickTop\": 0,\n" +
                "                    \"activityType\": 0,\n" +
                "                    \"topicType\": 0,\n" +
                "                    \"oldTopicType\": null,\n" +
                "                    \"visualization\": null,\n" +
                "                    \"popularizeType\": 0,\n" +
                "                    \"isConfirmPass\": null,\n" +
                "                    \"themeIds\": [],\n" +
                "                    \"mergePicture\": null,\n" +
                "                    \"firstWeight\": null,\n" +
                "                    \"lastWeight\": null,\n" +
                "                    \"totalFatReduce\": null,\n" +
                "                    \"totalWeightReduce\": null,\n" +
                "                    \"totalReduceDay\": null,\n" +
                "                    \"versionNumber\": null,\n" +
                "                    \"physiclalStatus\": \"\",\n" +
                "                    \"isConfirm\": null,\n" +
                "                    \"physiclalDetails\": [],\n" +
                "                    \"physiclalRemark\": \"\",\n" +
                "                    \"canEdit\": false,\n" +
                "                    \"ketoneImgUrl\": \"\",\n" +
                "                    \"physiclalPrompt\": [],\n" +
                "                    \"deleteImgIds\": [],\n" +
                "                    \"showInStarGym\": 0,\n" +
                "                    \"starGymRelation\": null,\n" +
                "                    \"reduceDetail\": {},\n" +
                "                    \"reduceDay\": null,\n" +
                "                    \"reduceWeight\": null,\n" +
                "                    \"reduceFat\": null,\n" +
                "                    \"ketoneTime\": null,\n" +
                "                    \"ketoneValue\": 0,\n" +
                "                    \"diaryTopic\": null,\n" +
                "                    \"extraData\": {},\n" +
                "                    \"question\": \"\",\n" +
                "                    \"description\": \"\",\n" +
                "                    \"showType\": null,\n" +
                "                    \"isStopReduceFat\": null,\n" +
                "                    \"isHorizontal\": false,\n" +
                "                    \"topicTag\": \"0\",\n" +
                "                    \"userTag\": \"0\",\n" +
                "                    \"customTag\": \"0\",\n" +
                "                    \"topicTagsLevel1\": \"\",\n" +
                "                    \"userTagsLevel1\": \"\",\n" +
                "                    \"baikeItems\": \"\",\n" +
                "                    \"baikeItemsLevel1\": \"\",\n" +
                "                    \"latestReplyTimeStr\": \"2018-04-16\",\n" +
                "                    \"imgList\": [\n" +
                "                        {\n" +
                "                            \"originUrl\": \"https://pic.bianla.cn/topic/20180416/7dcd2cbab90b439ca8e70da477025858.jpg\",\n" +
                "                            \"thumbUrl\": \"https://pic.bianla.cn/thumbnail/20180416/b7329b423b5b438ab71db9af71ad3668.jpg\",\n" +
                "                            \"thumbWidth\": null,\n" +
                "                            \"thumbHeight\": null\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"originUrl\": \"https://pic.bianla.cn/topic/20180416/61313565821f45c197a030dac491fd32.jpg\",\n" +
                "                            \"thumbUrl\": \"https://pic.bianla.cn/thumbnail/20180416/0d174a7c966048899f1788d88b0371fa.jpg\",\n" +
                "                            \"thumbWidth\": null,\n" +
                "                            \"thumbHeight\": null\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"shareCount\": 3,\n" +
                "                    \"likeCount\": 4,\n" +
                "                    \"replyCount\": 2,\n" +
                "                    \"collectCount\": 1,\n" +
                "                    \"clickCount\": 37,\n" +
                "                    \"isMine\": false,\n" +
                "                    \"isFollowed\": false,\n" +
                "                    \"isCollected\": false,\n" +
                "                    \"isPraised\": false,\n" +
                "                    \"user\": null,\n" +
                "                    \"video\": null,\n" +
                "                    \"videoUrl\": \"\",\n" +
                "                    \"shareSign\": 0,\n" +
                "                    \"bestComment\": \"\",\n" +
                "                    \"deleteUser\": \"\",\n" +
                "                    \"userTagStr\": \"\",\n" +
                "                    \"topicTagStr\": \"\",\n" +
                "                    \"customTagStr\": \"\",\n" +
                "                    \"isSpecialRecommend\": false\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 488222,\n" +
                "                    \"title\": \"\",\n" +
                "                    \"userId\": 1426696,\n" +
                "                    \"content\": \"减完肥后，我老公说他危机感爆棚了…\",\n" +
                "                    \"position\": \"浙江省金华市义乌市\",\n" +
                "                    \"hasImage\": true,\n" +
                "                    \"isMixed\": 0,\n" +
                "                    \"shareIcon\": \"https://pic.bianla.cn/sharepic/20180415/5d30b282910a436ba55f2d28ba044bad.jpg\",\n" +
                "                    \"auditStatus\": 1,\n" +
                "                    \"oldAuditStatus\": null,\n" +
                "                    \"latestReplyTime\": \"2018-04-15 12:38:26\",\n" +
                "                    \"permission\": 0,\n" +
                "                    \"externalUrl\": \"\",\n" +
                "                    \"isRecommend\": false,\n" +
                "                    \"isStickTop\": 0,\n" +
                "                    \"activityType\": 0,\n" +
                "                    \"topicType\": 0,\n" +
                "                    \"oldTopicType\": null,\n" +
                "                    \"visualization\": null,\n" +
                "                    \"popularizeType\": 0,\n" +
                "                    \"isConfirmPass\": null,\n" +
                "                    \"themeIds\": [],\n" +
                "                    \"mergePicture\": null,\n" +
                "                    \"firstWeight\": null,\n" +
                "                    \"lastWeight\": null,\n" +
                "                    \"totalFatReduce\": null,\n" +
                "                    \"totalWeightReduce\": null,\n" +
                "                    \"totalReduceDay\": null,\n" +
                "                    \"versionNumber\": null,\n" +
                "                    \"physiclalStatus\": \"\",\n" +
                "                    \"isConfirm\": null,\n" +
                "                    \"physiclalDetails\": [],\n" +
                "                    \"physiclalRemark\": \"\",\n" +
                "                    \"canEdit\": false,\n" +
                "                    \"ketoneImgUrl\": \"\",\n" +
                "                    \"physiclalPrompt\": [],\n" +
                "                    \"deleteImgIds\": [],\n" +
                "                    \"showInStarGym\": 0,\n" +
                "                    \"starGymRelation\": null,\n" +
                "                    \"reduceDetail\": {},\n" +
                "                    \"reduceDay\": null,\n" +
                "                    \"reduceWeight\": null,\n" +
                "                    \"reduceFat\": null,\n" +
                "                    \"ketoneTime\": null,\n" +
                "                    \"ketoneValue\": 0,\n" +
                "                    \"diaryTopic\": null,\n" +
                "                    \"extraData\": {},\n" +
                "                    \"question\": \"\",\n" +
                "                    \"description\": \"\",\n" +
                "                    \"showType\": null,\n" +
                "                    \"isStopReduceFat\": null,\n" +
                "                    \"isHorizontal\": false,\n" +
                "                    \"topicTag\": \"0\",\n" +
                "                    \"userTag\": \"0\",\n" +
                "                    \"customTag\": \"0\",\n" +
                "                    \"topicTagsLevel1\": \"\",\n" +
                "                    \"userTagsLevel1\": \"\",\n" +
                "                    \"baikeItems\": \"\",\n" +
                "                    \"baikeItemsLevel1\": \"\",\n" +
                "                    \"latestReplyTimeStr\": \"2018-04-15\",\n" +
                "                    \"imgList\": [\n" +
                "                        {\n" +
                "                            \"originUrl\": \"https://pic.bianla.cn/topic/20180415/54068a6c35524dc5b75d2222b47dd982.jpg\",\n" +
                "                            \"thumbUrl\": \"https://pic.bianla.cn/thumbnail/20180415/9c005f421cbe44dca4c4964a158c36a0.jpg\",\n" +
                "                            \"thumbWidth\": null,\n" +
                "                            \"thumbHeight\": null\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"originUrl\": \"https://pic.bianla.cn/topic/20180415/fddf51cb695c46d9a0382c0a16ad223f.jpg\",\n" +
                "                            \"thumbUrl\": \"https://pic.bianla.cn/thumbnail/20180415/cb06306856f44f718d848058d06c2e4c.jpg\",\n" +
                "                            \"thumbWidth\": null,\n" +
                "                            \"thumbHeight\": null\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"originUrl\": \"https://pic.bianla.cn/topic/20180415/2f41e066960549768ad0ae79d4637cb2.jpg\",\n" +
                "                            \"thumbUrl\": \"https://pic.bianla.cn/thumbnail/20180415/00d0041f483c4f4195cc135fccd2b035.jpg\",\n" +
                "                            \"thumbWidth\": null,\n" +
                "                            \"thumbHeight\": null\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"originUrl\": \"https://pic.bianla.cn/topic/20180415/cbad244c710d4940a94f2455a9cf6ac7.jpg\",\n" +
                "                            \"thumbUrl\": \"https://pic.bianla.cn/thumbnail/20180415/ef5af75da3ba4b8ba85bc68dc8bb3cd2.jpg\",\n" +
                "                            \"thumbWidth\": null,\n" +
                "                            \"thumbHeight\": null\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"originUrl\": \"https://pic.bianla.cn/topic/20180415/f964569eb5cf4f7d93b8117c01e2e4ea.jpg\",\n" +
                "                            \"thumbUrl\": \"https://pic.bianla.cn/thumbnail/20180415/27793e1a13704c1a933604de30a9045f.jpg\",\n" +
                "                            \"thumbWidth\": null,\n" +
                "                            \"thumbHeight\": null\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"originUrl\": \"https://pic.bianla.cn/topic/20180415/700b88f58b9c4c608eabdb9fa1c8f3ab.jpg\",\n" +
                "                            \"thumbUrl\": \"https://pic.bianla.cn/thumbnail/20180415/555d2823d8ed4782a83c90b1a2df040c.jpg\",\n" +
                "                            \"thumbWidth\": null,\n" +
                "                            \"thumbHeight\": null\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"originUrl\": \"https://pic.bianla.cn/topic/20180415/908d5c675543436ebd4bfef0470ec174.jpg\",\n" +
                "                            \"thumbUrl\": \"https://pic.bianla.cn/thumbnail/20180415/e00aa7d2257f41caa2ae1346f018022d.jpg\",\n" +
                "                            \"thumbWidth\": null,\n" +
                "                            \"thumbHeight\": null\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"originUrl\": \"https://pic.bianla.cn/topic/20180415/519742f19f094b4e88fafc28c940ab6a.jpg\",\n" +
                "                            \"thumbUrl\": \"https://pic.bianla.cn/thumbnail/20180415/a69924957d614e5f98c25d157795a90b.jpg\",\n" +
                "                            \"thumbWidth\": null,\n" +
                "                            \"thumbHeight\": null\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"originUrl\": \"https://pic.bianla.cn/topic/20180415/139e8350fb2b454aa6c9f3e05fbf1ab9.jpg\",\n" +
                "                            \"thumbUrl\": \"https://pic.bianla.cn/thumbnail/20180415/bf15857a01e5496aa6c9066b8af156b7.jpg\",\n" +
                "                            \"thumbWidth\": null,\n" +
                "                            \"thumbHeight\": null\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"shareCount\": 0,\n" +
                "                    \"likeCount\": 6,\n" +
                "                    \"replyCount\": 2,\n" +
                "                    \"collectCount\": 0,\n" +
                "                    \"clickCount\": 36,\n" +
                "                    \"isMine\": false,\n" +
                "                    \"isFollowed\": false,\n" +
                "                    \"isCollected\": false,\n" +
                "                    \"isPraised\": false,\n" +
                "                    \"user\": null,\n" +
                "                    \"video\": null,\n" +
                "                    \"videoUrl\": \"\",\n" +
                "                    \"shareSign\": 0,\n" +
                "                    \"bestComment\": \"\",\n" +
                "                    \"deleteUser\": \"\",\n" +
                "                    \"userTagStr\": \"\",\n" +
                "                    \"topicTagStr\": \"\",\n" +
                "                    \"customTagStr\": \"\",\n" +
                "                    \"isSpecialRecommend\": false\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 460517,\n" +
                "                    \"title\": \"\",\n" +
                "                    \"userId\": 1426696,\n" +
                "                    \"content\": \"在最好的时光里活出自己最好的姿态来\",\n" +
                "                    \"position\": \"河南省焦作市山阳区\",\n" +
                "                    \"hasImage\": true,\n" +
                "                    \"isMixed\": 0,\n" +
                "                    \"shareIcon\": \"https://pic.bianla.cn/sharepic/20180409/517b908c67a24c9b95dbeeceab8c6450.jpg\",\n" +
                "                    \"auditStatus\": 1,\n" +
                "                    \"oldAuditStatus\": null,\n" +
                "                    \"latestReplyTime\": \"2018-04-09 09:03:17\",\n" +
                "                    \"permission\": 0,\n" +
                "                    \"externalUrl\": \"\",\n" +
                "                    \"isRecommend\": false,\n" +
                "                    \"isStickTop\": 0,\n" +
                "                    \"activityType\": 0,\n" +
                "                    \"topicType\": 0,\n" +
                "                    \"oldTopicType\": null,\n" +
                "                    \"visualization\": null,\n" +
                "                    \"popularizeType\": 0,\n" +
                "                    \"isConfirmPass\": null,\n" +
                "                    \"themeIds\": [],\n" +
                "                    \"mergePicture\": null,\n" +
                "                    \"firstWeight\": null,\n" +
                "                    \"lastWeight\": null,\n" +
                "                    \"totalFatReduce\": null,\n" +
                "                    \"totalWeightReduce\": null,\n" +
                "                    \"totalReduceDay\": null,\n" +
                "                    \"versionNumber\": null,\n" +
                "                    \"physiclalStatus\": \"\",\n" +
                "                    \"isConfirm\": null,\n" +
                "                    \"physiclalDetails\": [],\n" +
                "                    \"physiclalRemark\": \"\",\n" +
                "                    \"canEdit\": false,\n" +
                "                    \"ketoneImgUrl\": \"\",\n" +
                "                    \"physiclalPrompt\": [],\n" +
                "                    \"deleteImgIds\": [],\n" +
                "                    \"showInStarGym\": 0,\n" +
                "                    \"starGymRelation\": null,\n" +
                "                    \"reduceDetail\": {},\n" +
                "                    \"reduceDay\": null,\n" +
                "                    \"reduceWeight\": null,\n" +
                "                    \"reduceFat\": null,\n" +
                "                    \"ketoneTime\": null,\n" +
                "                    \"ketoneValue\": 0,\n" +
                "                    \"diaryTopic\": null,\n" +
                "                    \"extraData\": {},\n" +
                "                    \"question\": \"\",\n" +
                "                    \"description\": \"\",\n" +
                "                    \"showType\": null,\n" +
                "                    \"isStopReduceFat\": null,\n" +
                "                    \"isHorizontal\": false,\n" +
                "                    \"topicTag\": \"0\",\n" +
                "                    \"userTag\": \"0\",\n" +
                "                    \"customTag\": \"0\",\n" +
                "                    \"topicTagsLevel1\": \"\",\n" +
                "                    \"userTagsLevel1\": \"\",\n" +
                "                    \"baikeItems\": \"\",\n" +
                "                    \"baikeItemsLevel1\": \"\",\n" +
                "                    \"latestReplyTimeStr\": \"2018-04-09\",\n" +
                "                    \"imgList\": [\n" +
                "                        {\n" +
                "                            \"originUrl\": \"https://pic.bianla.cn/topic/20180409/caa291cae2954f2995eaa631467d3452.jpg\",\n" +
                "                            \"thumbUrl\": \"https://pic.bianla.cn/thumbnail/20180409/6d3110c1b10949bb8b21809f35e7b067.jpg\",\n" +
                "                            \"thumbWidth\": 258,\n" +
                "                            \"thumbHeight\": 460\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"shareCount\": 0,\n" +
                "                    \"likeCount\": 5,\n" +
                "                    \"replyCount\": 3,\n" +
                "                    \"collectCount\": 0,\n" +
                "                    \"clickCount\": 40,\n" +
                "                    \"isMine\": false,\n" +
                "                    \"isFollowed\": false,\n" +
                "                    \"isCollected\": false,\n" +
                "                    \"isPraised\": false,\n" +
                "                    \"user\": null,\n" +
                "                    \"video\": null,\n" +
                "                    \"videoUrl\": \"\",\n" +
                "                    \"shareSign\": 0,\n" +
                "                    \"bestComment\": \"\",\n" +
                "                    \"deleteUser\": \"\",\n" +
                "                    \"userTagStr\": \"\",\n" +
                "                    \"topicTagStr\": \"\",\n" +
                "                    \"customTagStr\": \"\",\n" +
                "                    \"isSpecialRecommend\": false\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        \"user\": {\n" +
                "            \"userId\": 1426696,\n" +
                "            \"userType\": 1,\n" +
                "            \"nickName\": \"无双\",\n" +
                "            \"imageUrl\": \"https://pic.bianla.cn/bianla_image/bianla_imageThumbnail/20180726/1532595479239.jpg\",\n" +
                "            \"gender\": \"f\",\n" +
                "            \"isV\": 0\n" +
                "        }\n" +
                "    }\n" +
                "}";
//        JSONObject obj = new JSONObject();
        JSONObject obj = JSONObject.parseObject(test);

        String struct = "data.pageResBean.list.imgList.originUrl";
        JsonUtil jsonUtil = new JsonUtil();
        jsonUtil.core(struct,obj);
    }

    private List<Object> core(String struct,JSONObject obj){
        List<Object> list = new LinkedList<>();
        String[] structs = struct.split("\\.");
        if(structs.length > 0){
            this.deal(list,structs[0], obj, structs);

            for(Object o : list){
                System.out.println(o);
            }
        }

        return list;
    }
    private void deal(List<Object> list,String key, JSONObject obj,String[] structs){
        String nextKey = getNextKey(structs,key);
        boolean hadNext = StringUtils.isNotBlank(nextKey);
        Object o = obj.get(key);
        JSONArray array;
        if(o instanceof JSONObject){
            obj = (JSONObject)o;

            if(hadNext){
                deal(list,nextKey,obj,structs);
            }
        }else if(o instanceof JSONArray){
            array = (JSONArray)o;
            if(hadNext){
                for(int p = 0 ; p < array.size() ; p ++){
                    deal(list,nextKey,(JSONObject) array.get(p),structs);
                }
            }
        }else {
            list.add(o);
        }
    }

    private String getNextKey(String[] structs,String key){
        for(int i = 0 ; i < structs.length ; i++){
            if(key.equals(structs[i]) && i != structs.length-1 ){
                return structs[i + 1];
            }
        }
        return "";
    }
}
