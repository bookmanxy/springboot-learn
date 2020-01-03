package cn.faceland.springbootwordstransvoice;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

/**
 * 文字转语音测试 jdk bin文件中需要导入jacob-1.17-M2-x64.dll
 *
 * @author zk
 * @date: 2019年6月25日 上午10:05:21
 */
public class jacobtest {

    /**
     * 语音转文字并播放
     *
     * @param txt
     */
    public static void textToSpeech(String text) {
        ActiveXComponent ax = null;
        try {
            ax = new ActiveXComponent("Sapi.SpVoice");

            // 运行时输出语音内容
            Dispatch spVoice = ax.getObject();
            // 音量 0-100
            ax.setProperty("Volume", new Variant(100));
            // 语音朗读速度 -10 到 +10
            ax.setProperty("Rate", new Variant(-2));
            // 执行朗读
            Dispatch.call(spVoice, "Speak", new Variant(text));

            // 下面是构建文件流把生成语音文件

            ax = new ActiveXComponent("Sapi.SpFileStream");
            Dispatch spFileStream = ax.getObject();

            ax = new ActiveXComponent("Sapi.SpAudioFormat");
            Dispatch spAudioFormat = ax.getObject();

            // 设置音频流格式
            Dispatch.put(spAudioFormat, "Type", new Variant(22));
            // 设置文件输出流格式
            Dispatch.putRef(spFileStream, "Format", spAudioFormat);
            // 调用输出 文件流打开方法，创建一个.wav文件
            Dispatch.call(spFileStream, "Open", new Variant("./text.wav"), new Variant(3), new Variant(true));
            // 设置声音对象的音频输出流为输出文件对象
            Dispatch.putRef(spVoice, "AudioOutputStream", spFileStream);
            // 设置音量 0到100
            Dispatch.put(spVoice, "Volume", new Variant(100));
            // 设置朗读速度
            Dispatch.put(spVoice, "Rate", new Variant(-2));
            // 开始朗读
            Dispatch.call(spVoice, "Speak", new Variant(text));

            // 关闭输出文件
            Dispatch.call(spFileStream, "Close");
            Dispatch.putRef(spVoice, "AudioOutputStream", null);

            spAudioFormat.safeRelease();
            spFileStream.safeRelease();
            spVoice.safeRelease();
            ax.safeRelease();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String str = "double计算保留两位小数MySQL两种引擎Myisam,innodb单表大小的限制jquery操作表单数组提交为什么MySQL的索引要使用B+树而不是其它树形结构?比如B树怎么得到InnoDB主键索引B+树的高度mysql中通常一棵B+树可以存放多少行数据mysql主键不自增，在插入数据会有什么影响mysql没有主键，存储引擎会怎么处理磁盘扇区、文件系统、InnoDB存储引擎都有各自的最小存储单元山东舰和辽宁舰的区别对比mysql的查询优化器的优化过程mysql中为什么InnoDB只有一个聚簇索引，而不将所有索引都使用聚簇索引mysql中联合索引的最左前缀匹配原则mysql中的非聚簇索引mysql中的聚簇索引mysql中的B+树结构mysql中对多页目录概念的优化mysql中的多页模式概念mysql中的页目录概念mysql中的单页模式概念";
        textToSpeech(str);
    }
}