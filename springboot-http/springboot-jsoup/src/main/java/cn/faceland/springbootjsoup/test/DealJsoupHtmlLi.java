package cn.faceland.springbootjsoup.test;

import lombok.Data;
import lombok.experimental.Accessors;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * @author watermelon
 * @Date 2019-12-21
 * @Description 使用jsoup处理li嵌套的解析
 */
public class DealJsoupHtmlLi {
    public static void main(String[] args) {
//        String html = "<li>多线程<ul><li id=\"859\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1032\" target=\"_blank\">什么时候会执行拒绝策略</a><ul><li id=\"876\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1035\" target=\"_blank\">线程类的构造方法、静态块是被哪个线程调用的</a><ul><li id=\"860\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1032\" target=\"_blank\">线程池状态对拒绝策略的影响</a><ul><li id=\"866\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1034\" target=\"_blank\">线程池的核心参数和基本原理</a></li><li id=\"858\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1032\" target=\"_blank\">任务执行过程中发生异常怎么处理</a></li></ul></li><li id=\"872\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1034\" target=\"_blank\">什么是线程池</a></li></ul></li><li id=\"864\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1034\" target=\"_blank\">Java线程池缺陷</a></li><li id=\"865\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1034\" target=\"_blank\">线程池的调优策略</a></li><li id=\"862\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1033\" target=\"_blank\">聊聊Java中的并发队列中 有界队列和无界队列的区别</a></li></ul></li><li id=\"873\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1034\" target=\"_blank\">线程池的优点</a></li><li id=\"874\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1034\" target=\"_blank\">Executors类是什么</a><ul><li id=\"857\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1032\" target=\"_blank\">Executors.newFixedThreadPool(…) 和 Executors.newCachedThreadPool() 构造出来的线程池有什么差别</a></li></ul></li><li id=\"875\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1034\" target=\"_blank\">Executors五种线程池的创建</a></li><li id=\"856\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1032\" target=\"_blank\">说说线程池中的线程创建时机</a></li><li id=\"855\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1032\" target=\"_blank\">java 线程池有哪些关键属性</a></li><li id=\"854\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1032\" target=\"_blank\">线程池基础汇总</a></li><li id=\"278\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1237\" target=\"_blank\">多线程实现方式（4种）</a></li><li id=\"160\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1237\" target=\"_blank\">什么是cas方法</a></li><li id=\"161\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1237\" target=\"_blank\">cas有什么缺点</a></li><li id=\"244\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1237\" target=\"_blank\">并行和并发的区别是什么</a></li><li id=\"265\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1237\" target=\"_blank\">什么是AQS，结构怎么样的</a></li><li id=\"266\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1237\" target=\"_blank\">什么是cas</a></li><li id=\"267\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1237\" target=\"_blank\">cas的好处是什么</a></li><li id=\"268\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1237\" target=\"_blank\">cas的缺点是什么</a></li><li id=\"269\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1237\" target=\"_blank\">什么是ABA问题</a></li><li id=\"270\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1237\" target=\"_blank\">ABA问题处理办法是什么</a></li><li id=\"264\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1237\" target=\"_blank\">AQS定义两种资源共享方式是哪两种</a></li></ul></li>";
        String html = "<ul><li id=\"859\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1032\" target=\"_blank\">什么时候会执行拒绝策略</a><ul><li id=\"876\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1035\" target=\"_blank\">线程类的构造方法、静态块是被哪个线程调用的</a><ul><li id=\"860\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1032\" target=\"_blank\">线程池状态对拒绝策略的影响</a><ul><li id=\"866\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1034\" target=\"_blank\">线程池的核心参数和基本原理</a></li><li id=\"858\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1032\" target=\"_blank\">任务执行过程中发生异常怎么处理</a></li></ul></li><li id=\"872\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1034\" target=\"_blank\">什么是线程池</a></li></ul></li><li id=\"864\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1034\" target=\"_blank\">Java线程池缺陷</a></li><li id=\"865\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1034\" target=\"_blank\">线程池的调优策略</a></li><li id=\"862\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1033\" target=\"_blank\">聊聊Java中的并发队列中 有界队列和无界队列的区别</a></li></ul></li><li id=\"873\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1034\" target=\"_blank\">线程池的优点</a></li><li id=\"874\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1034\" target=\"_blank\">Executors类是什么</a><ul><li id=\"857\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1032\" target=\"_blank\">Executors.newFixedThreadPool(…) 和 Executors.newCachedThreadPool() 构造出来的线程池有什么差别</a></li></ul></li><li id=\"875\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1034\" target=\"_blank\">Executors五种线程池的创建</a></li><li id=\"856\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1032\" target=\"_blank\">说说线程池中的线程创建时机</a></li><li id=\"855\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1032\" target=\"_blank\">java 线程池有哪些关键属性</a></li><li id=\"854\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1032\" target=\"_blank\">线程池基础汇总</a></li><li id=\"278\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1237\" target=\"_blank\">多线程实现方式（4种）</a></li><li id=\"160\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1237\" target=\"_blank\">什么是cas方法</a></li><li id=\"161\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1237\" target=\"_blank\">cas有什么缺点</a></li><li id=\"244\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1237\" target=\"_blank\">并行和并发的区别是什么</a></li><li id=\"265\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1237\" target=\"_blank\">什么是AQS，结构怎么样的</a></li><li id=\"266\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1237\" target=\"_blank\">什么是cas</a></li><li id=\"267\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1237\" target=\"_blank\">cas的好处是什么</a></li><li id=\"268\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1237\" target=\"_blank\">cas的缺点是什么</a></li><li id=\"269\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1237\" target=\"_blank\">什么是ABA问题</a></li><li id=\"270\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1237\" target=\"_blank\">ABA问题处理办法是什么</a></li><li id=\"264\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1237\" target=\"_blank\">AQS定义两种资源共享方式是哪两种</a></li></ul>";
//        String html = "<a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1237\" target=\"_blank\">AQS定义两种资源共享方式是哪两种</a>";
//        String html = "<a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1032\" target=\"_blank\">什么时候会执行拒绝策略</a>\n" +
//                "<ul>\n" +
//                " <li id=\"876\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1035\" target=\"_blank\">线程类的构造方法、静态块是被哪个线程调用的</a>\n" +
//                "  <ul>\n" +
//                "   <li id=\"860\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1032\" target=\"_blank\">线程池状态对拒绝策略的影响</a>\n" +
//                "    <ul>\n" +
//                "     <li id=\"866\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1034\" target=\"_blank\">线程池的核心参数和基本原理</a></li>\n" +
//                "     <li id=\"858\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1032\" target=\"_blank\">任务执行过程中发生异常怎么处理</a></li>\n" +
//                "    </ul></li>\n" +
//                "   <li id=\"872\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1034\" target=\"_blank\">什么是线程池</a></li>\n" +
//                "  </ul></li>\n" +
//                " <li id=\"864\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1034\" target=\"_blank\">Java线程池缺陷</a></li>\n" +
//                " <li id=\"865\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1034\" target=\"_blank\">线程池的调优策略</a></li>\n" +
//                " <li id=\"862\"><a href=\"http://123.206.129.53:8080/page/blogs/admin/tools/story/index.html?id=1033\" target=\"_blank\">聊聊Java中的并发队列中 有界队列和无界队列的区别</a></li>\n" +
//                "</ul>";
        Document doc = Jsoup.parse(html);
        Element ul;
        Elements uls = doc.getElementsByTag("ul");
        if(uls.size() > 0){
            ul = uls.get(0);
        }else{
            System.out.println("i am son");
            return;
        }

        Elements lis = ul.getElementsByTag("li");
        for(Element element : lis){

            System.out.println(element.html());
            boolean isSingleSon = isSingleSon(element.html());
            if(isSingleSon){
                //进入一次迭代，传入nodeId，parentId
                System.out.println("i am son");
            }
//            System.out.println(element.text());
            System.out.println(element.attr("id"));
            System.out.println("================");
        }
    }

    private static boolean isSingleSon(String html){
        Document doc = Jsoup.parse(html);
        Elements uls = doc.getElementsByTag("ul");
        return uls.size() <1;
    }
    public List<NodeMind> dealList(Integer nodeId,Integer parentQuestionId,String html,List<NodeMind> list){
//        List<NodeMind> list = new ArrayList<>();
        //既是子节点，又是下面的父节点
        boolean isSonWithParent = false;

        //仅仅作为子节点
        boolean isSingleSon = false;

        Document doc = Jsoup.parse(html);
        Element ul = null;
        Elements uls = doc.getElementsByTag("ul");
        if(uls.size() > 0){
            isSonWithParent = true;
        }else{
            isSingleSon = true;
        }

        if(isSingleSon){
            NodeMind mind = new NodeMind();
            mind.setNodeId(nodeId);
//            mind.setQuestionId(currentQuestionId);
//            mind.setPreQuestionId(preQuestionid);
            mind.setParentQuestionId(parentQuestionId);

            list.add(mind);
            return list;
        }

        if(isSonWithParent){
            ul = uls.get(0);

            NodeMind mind = new NodeMind();
            mind.setNodeId(nodeId);

            Elements lis = ul.getElementsByTag("li");
            for(Element element : lis){
                System.out.println(element.html());
//            System.out.println(element.text());
                System.out.println(element.attr("id"));
                mind.setQuestionId(Integer.parseInt(element.attr("id")));

                System.out.println("================");
            }
        }
        return list;
    }

    @Data
    @Accessors(chain = true)
    public class NodeMind{
        private Integer nodeId;
        private Integer parentQuestionId;
        private Integer questionId;
    }
}
