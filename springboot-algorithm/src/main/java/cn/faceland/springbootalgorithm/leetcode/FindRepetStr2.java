package cn.faceland.springbootalgorithm.leetcode;

import java.util.HashMap;

/**
 * @author watermelon
 * @Date 2019-11-25
 */
public class FindRepetStr2 extends AbstractHandler{

    /**
     给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。

     示例 1：

     输入: "babad"
     输出: "bab"
     注意: "aba" 也是一个有效答案。
     示例 2：

     输入: "cbbd"
     输出: "bb"

     来源：力扣（LeetCode）
     链接：https://leetcode-cn.com/problems/longest-palindromic-substring
     著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */



    @Override
    void doCalculations() {
//        String s = "12342013";
//        String s = "babad";  // 4
//        String s = "cbbd";   //3
//        String s = "";   //3
//        String s = "ccc";   //3
//        String s = "abcda";  //3
        String s = "babad";  //3
        System.out.println(lengthOfLongestSubstring2(s));
    }

    public String lengthOfLongestSubstring2(String s) {
        String result ;
        char[] array = s.toCharArray();
        if(array.length <= 0){
            return s;
        }

        int length = 0;
        Character bestChar = null;
        Integer bestIndex1 = 0,bestIndex2=0;

        for(int i = 0 ; i < array.length ; i ++){
            for(int j = array.length-1; j > i ; j --){
                if((j - i) <= length){
                    break;
                }

                if(array[j] == array[i]){
                    if(huiwen(s.substring(i, j) + array[i])){
                        if((j - i ) > length){
                            bestChar = array[i];
                            bestIndex1 = i;
                            bestIndex2 = j;
                            length = j - i ;
                            break;
                        }
                    }
                }
            }
        }
        if(bestChar != null){
            result = s.substring(bestIndex1, bestIndex2) + bestChar;
        }else{
            result = array.length > 0 ?array[0] +"": s;
        }
        return result;
    }

    boolean huiwen(String s){
        System.out.println("===="+s);
        char[] array = s.toCharArray();
        for(int i = 0 ; i <= array.length/2; i ++){
            if(array[i] != array[array.length-1-i]){
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        AbstractHandler.doCal(FindRepetStr2.class);
    }
}
