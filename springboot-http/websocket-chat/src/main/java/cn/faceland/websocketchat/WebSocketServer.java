package cn.faceland.websocketchat;


import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author watermelon
 * @Date 2020-01-08
 * @Description 参考：博客园  https://www.cnblogs.com/yangxiaohui227/p/11077172.html
 */
@ServerEndpoint("/websocket/{name}")
@RestController
public class WebSocketServer {
//    public static Map<Integer, String> CoinTypeItemMaps = new HashMap<Integer, String>(){{
//        put(FOREVER_COIN, CoinItem.FOREVER_COIN);
//        put(FOREVER_NOT_RETURN_COIN, CoinItem.FOREVER_NOT_RETURN_COIN);
//        put(FOREVER_ATY_COIN, CoinItem.FOREVER_ATY_COIN);
//        put(ATY_COIN, CoinItem.ATY_COIN);
//    }};

    private static List<String> headLists = new ArrayList<String>(){{
        add("https://wx2.sinaimg.cn/mw1024/005xB1vLly1ga931tg3ehj31hc0u0gx5.jpg");
        add("https://wx2.sinaimg.cn/mw1024/005xB1vLly1ga92zmyuykj31hc0u0ti6.jpg");
        add("https://wx2.sinaimg.cn/mw1024/005xB1vLly1ga9311mtpwj31hc0u0dpx.jpg");
        add("https://wx2.sinaimg.cn/mw1024/005xB1vLly1ga930bhxb3j31hc0u0tji.jpg");
        add("https://wx2.sinaimg.cn/mw1024/005xB1vLly1ga931ter3mj31fc0u07fy.jpg");
        add("https://wx2.sinaimg.cn/mw1024/005xB1vLly1ga930bdeojj31hc0u04ab.jpg");
    }};

    private static Map<String, String> headMap = new HashMap<>();

    public String getHead(String name){
        if(headMap.containsKey(name)){
            return headMap.get(name);
        }else{
            //从 headLists 中随机分配一个
             Random random = new Random();
            int n = random.nextInt(headLists.size());

            String myHead = headLists.get(n);
            headMap.put(name, myHead);
            return myHead;
        }
    }

    private String getHeadImgHtml(String headUrl){
        return "<img src='" + headUrl + "' style='width:20px;height:20px'>";
    }

    private String changeMyHead(String name){
        String oldHead = "";
        if(headMap.containsKey(name)){
            oldHead = headMap.get(name);

            headMap.remove(name);
        }

        //从 headLists 中随机分配一个
        String newHead = "";
        while (oldHead.equals(newHead) || StringUtils.isBlank(newHead) ){
            Random random = new Random();
            int n = random.nextInt(headLists.size());

            newHead = headLists.get(n);
        }

        headMap.put(name, newHead);
        System.out.println("最新的头像是：" + newHead);
        return newHead;
    }
    //存储客户端的连接对象,每个客户端连接都会产生一个连接对象
    private static ConcurrentHashMap<String,WebSocketServer> map=new ConcurrentHashMap();
    //每个连接都会有自己的会话
    private Session session;
    private String name;

    @OnOpen
    public void open(@PathParam("name") String name, Session session){
        map.put(name,this);
        System.out.println(name+"连接服务器成功");
        System.out.println("客户端连接个数:"+getConnetNum());

        this.session=session;
        this.name=name;
    }
    @OnClose
    public void close(){
        map.remove(name);
        System.out.println(name+"断开了服务器连接");
    }
    @OnError
    public void error(Throwable error){
        error.printStackTrace();
        System.out.println(name+"出现了异常");
    }
    @OnMessage
    public void getMessage(String message) throws IOException {
        System.out.println("收到"+name+":"+message);
        System.out.println("客户端连接个数:"+getConnetNum());

        if("换头像".equals(message)){
            changeMyHead(name);

            map.get(name).send("头像更新成功，您的新头像是：" + getHeadImgHtml(getHead(name)));
        }else{
            //普通消息推送
            Set<Map.Entry<String, WebSocketServer>> entries = map.entrySet();
            for (Map.Entry<String, WebSocketServer> entry : entries) {
                if(!entry.getKey().equals(name)){//将消息转发到其他非自身客户端
                    entry.getValue().send(getHeadImgHtml(getHead(name)) + " " + name + ": " +message);
                }else{
                    entry.getValue().send(getHeadImgHtml(getHead(name)) + " " + "我: " +message);
                }
            }
        }
    }

    public void send(String message) throws IOException {
        if(session.isOpen()){
            session.getBasicRemote().sendText(message);
        }
    }

    public int  getConnetNum(){
        return map.size();
    }
}