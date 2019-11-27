package cn.faceland.springbootalgorithm.leetcode;

import java.util.HashMap;

/**
 * @author watermelon
 * @Date 2019-11-25
 */
public class FindRepetStr extends AbstractHandler{

    /**
     给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。

     示例 1:

     输入: "abcabcbb"
     输出: 3
     解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     示例 2:

     输入: "bbbbb"
     输出: 1
     解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
     示例 3:

     输入: "pwwkew"
     输出: 3
     解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
          请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。

     来源：力扣（LeetCode）
     链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
     著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */



    @Override
    void doCalculations() {
//        String s = "12342013";
        String s = "jbpnbwwd";  // 4
//        String s = "aabaab!bb";   //3
        System.out.println(lengthOfLongestSubstring2(s));
    }

    public int lengthOfLongestSubstring(String s) {
        HashMap<Character,Integer> map = new HashMap<>();
        int length = 0;
        char[] array = s.toCharArray();
        Integer value;
        for(int i = 0 ; i < array.length ; i ++){
            value = map.get(array[i]);
            if(value != null){
                if(length < map.size()){
                    length = map.size();
                }
                i = value;
                map = new HashMap<>();
            }else{
                map.put(array[i],i);
            }
        }
        if(length < map.size()){
            length = map.size();
        }
        return length;
    }

    public int lengthOfLongestSubstring2(String s) {
        HashMap<Character,Integer> map = new HashMap<>();
        int length = 0;
        char[] array = s.toCharArray();
        Integer value;
        for(int i = 0 ; i < array.length ; i ++){
            if(map.containsKey(array[i])){
                if(length < map.size()){
                    length = map.size();
                }
                i = map.get(array[i]);
                map = new HashMap<>();
            }else{
                map.put(array[i],i);
            }
        }
        if(length < map.size()){
            length = map.size();
        }
        return length;
    }

    public static void main(String[] args) {
        AbstractHandler.doCal(FindRepetStr.class);
    }
}
