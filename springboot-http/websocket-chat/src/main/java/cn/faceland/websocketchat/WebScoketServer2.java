package cn.faceland.websocketchat;


import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * @author watermelon
 * @Date 2020-01-08
 * @Description
 */
@ServerEndpoint("/websocket2")
public class WebScoketServer2 {

    private static Integer onlineNum = 0; //当前在线人数，线程必须设计成安全的
    private static CopyOnWriteArraySet<WebScoketServer2> arraySet = new CopyOnWriteArraySet<WebScoketServer2>(); //存放每一个客户的的WebScoketServer对象，线程安全
    private Session session;

    /**
     * 连接成功
     *
     * @param session 会话信息
     */
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        arraySet.add(this);
        this.addOnlineNum();
        System.out.println("有一个新连接加入，当前在线 " + this.getOnLineNum() + " 人");
    }

    /**
     * 连接关闭
     */
    @OnClose
    public void onClose() {
        arraySet.remove(this);
        this.subOnlineNum();
        System.out.println("有一个连接断开，当前在线 " + this.getOnLineNum() + " 人");
    }

    /**
     * 连接错误
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.err.println("发生错误！");
        error.printStackTrace();
    }

    /**
     * 发送消息,不加注解，自己选择实现
     *
     * @param msg
     * @throws IOException
     */
    public void onSend(String msg) throws IOException {
        this.session.getBasicRemote().sendText(msg);
    }

    /**
     * 收到客户端消息回调方法
     *
     * @param session
     * @param msg
     */
    @OnMessage
    public void onMessage(Session session, String msg) {
        System.out.println("消息监控：" + msg);
        for (WebScoketServer2 webScoketServer2 : arraySet) {
            try {
                webScoketServer2.onSend(msg);
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
        }
    }

    /**
     * 增加一个在线人数
     */
    private synchronized void addOnlineNum() {
        onlineNum++;
    }

    /**
     * 减少一个在线人数
     */
    private synchronized void subOnlineNum() {
        onlineNum--;
    }

    private Integer getOnLineNum() {
        return onlineNum;
    }
}