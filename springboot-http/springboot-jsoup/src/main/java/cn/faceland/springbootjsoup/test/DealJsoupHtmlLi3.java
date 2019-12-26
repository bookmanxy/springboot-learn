package cn.faceland.springbootjsoup.test;

import lombok.Data;
import lombok.experimental.Accessors;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;

/**
 * @author watermelon
 * @Date 2019-12-21
 * @Description 使用jsoup处理li嵌套的解析
 */
public class DealJsoupHtmlLi3 {
    public static void main(String[] args) {
                String html = "<ul id='demo1'><li id=\"859\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1032\" target=\"_blank\">什么时候会执行拒绝策略</a><ul><li id=\"876\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1035\" target=\"_blank\">线程类的构造方法、静态块是被哪个线程调用的</a><ul><li id=\"860\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1032\" target=\"_blank\">线程池状态对拒绝策略的影响</a><ul><li id=\"866\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1034\" target=\"_blank\">线程池的核心参数和基本原理</a></li><li id=\"858\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1032\" target=\"_blank\">任务执行过程中发生异常怎么处理</a></li></ul></li><li id=\"872\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1034\" target=\"_blank\">什么是线程池</a></li></ul></li><li id=\"864\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1034\" target=\"_blank\">Java线程池缺陷</a></li><li id=\"865\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1034\" target=\"_blank\">线程池的调优策略</a></li><li id=\"862\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1033\" target=\"_blank\">聊聊Java中的并发队列中 有界队列和无界队列的区别</a></li></ul></li><li id=\"873\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1034\" target=\"_blank\">线程池的优点</a></li><li id=\"874\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1034\" target=\"_blank\">Executors类是什么</a><ul><li id=\"857\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1032\" target=\"_blank\">Executors.newFixedThreadPool(…) 和 Executors.newCachedThreadPool() 构造出来的线程池有什么差别</a></li></ul></li><li id=\"875\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1034\" target=\"_blank\">Executors五种线程池的创建</a></li><li id=\"856\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1032\" target=\"_blank\">说说线程池中的线程创建时机</a></li><li id=\"855\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1032\" target=\"_blank\">java 线程池有哪些关键属性</a></li><li id=\"854\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1032\" target=\"_blank\">线程池基础汇总</a></li><li id=\"278\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1237\" target=\"_blank\">多线程实现方式（4种）</a></li><li id=\"160\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1237\" target=\"_blank\">什么是cas方法</a></li><li id=\"161\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1237\" target=\"_blank\">cas有什么缺点</a></li><li id=\"244\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1237\" target=\"_blank\">并行和并发的区别是什么</a></li><li id=\"265\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1237\" target=\"_blank\">什么是AQS，结构怎么样的</a></li><li id=\"266\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1237\" target=\"_blank\">什么是cas</a></li><li id=\"267\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1237\" target=\"_blank\">cas的好处是什么</a></li><li id=\"268\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1237\" target=\"_blank\">cas的缺点是什么</a></li><li id=\"269\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1237\" target=\"_blank\">什么是ABA问题</a></li><li id=\"270\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1237\" target=\"_blank\">ABA问题处理办法是什么</a></li><li id=\"264\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1237\" target=\"_blank\">AQS定义两种资源共享方式是哪两种</a></li></ul>";

//        Integer nodeId = 0;
//        Integer parentQuestionId = nodeId;
//        List<NodeMind> list = new ArrayList<>();
//
//        dealList(nodeId,parentQuestionId,html,list);
//
//
//
//        Map<String ,Integer> map = new HashMap<>();
//        Iterator<NodeMind> it = list.iterator();
//        NodeMind item;
//        String key = "";
//        while (it.hasNext()){
//            item = it.next();
//            if(item.getQuestionId().equals(item.getParentQuestionId())){
//                it.remove();
//                continue;
//            }
//            key = item.getNodeId() + "" + item.getQuestionId();
//            if(map.containsKey(key)){
//                it.remove();
//            }else{
//                map.put(key,item.getParentQuestionId());
//            }
//        }
//
//        for(NodeMind nodeMind : list){
//            System.out.println(nodeMind);
//        }

//        for(Element element : lis){
//
//            System.out.println(element.html());
//            boolean isSingleSon = isSingleSon(element.html());
//            if(isSingleSon){
//                //进入一次迭代，传入nodeId，parentId
//                System.out.println("i am son");
//            }
////            System.out.println(element.text());
//            System.out.println(element.attr("id"));
//            System.out.println("================");
//        }
//        ul>li
        Document doc = Jsoup.parse(html);
        Elements lis = doc.getElementsByTag("#demo1 >li");
        for(Element element : lis){
            System.out.println("li element === " + element.html());
        }
    }

    private static boolean isSingleSon(String html){
        Document doc = Jsoup.parse(html);
        Elements uls = doc.getElementsByTag("ul");
        return uls.size() <1;
    }

    public static List<NodeMind> dealList(Integer nodeId,Integer parentQuestionId,String html,List<NodeMind> list){
//        System.out.println(nodeId + "::" + parentQuestionId + ";html:==" + html);
        Integer currentQuestionId = 0 ;
        Document doc = Jsoup.parse(html);
        Elements lis = doc.getElementsByTag("li");
        for(Element element : lis){
//            System.out.println(parentQuestionId + "li element === " + element.html());

            currentQuestionId = Integer.parseInt(element.attr("id"));
            boolean isSingleSon = isSingleSon(element.html());
            if(isSingleSon){
                //进入一次迭代，传入nodeId，parentId
                NodeMind nodeMind = new NodeMind();
                nodeMind.setNodeId(nodeId);
                nodeMind.setParentQuestionId(parentQuestionId);
                nodeMind.setQuestionId(currentQuestionId);
                list.add(nodeMind);
//                return list;
            }else{
//                System.out.println(parentQuestionId + "li element === " + element.html());

                NodeMind nodeMind = new NodeMind();
                nodeMind.setNodeId(nodeId);
                nodeMind.setParentQuestionId(parentQuestionId);
                nodeMind.setQuestionId(currentQuestionId);
                list.add(nodeMind);

                //把我第一个html给递归下去
                Elements uls = doc.getElementsByTag("ul");
                if(uls.size() > 0){
                    html = uls.get(0).html();
                    dealList(nodeId,currentQuestionId,html,list);
                }
            }
        }
        return list;
    }

    @Data
    @Accessors(chain = true)
    public static class NodeMind{
        private Integer nodeId;
        private Integer parentQuestionId;
        private Integer questionId;
    }
}
