package cn.faceland.springbootalgorithm.leetcode;

import java.util.HashMap;

/**
 * @author watermelon
 * @Date 2019-11-25
 */
public class ReverseNum extends AbstractHandler{

    /**
     给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。

     示例 1:

     输入: 123
     输出: 321
      示例 2:

     输入: -123
     输出: -321
     示例 3:

     输入: 120
     输出: 21
     注意:

     假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231,  231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。

     来源：力扣（LeetCode）
     链接：https://leetcode-cn.com/problems/reverse-integer
     著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */



    @Override
    void doCalculations() {
//        int x = 123;
//        int x = -123;
//        int x = 120;
        int x = -10;
//        int x = 901000;
//        int x = 1534236469;
        System.out.println(reverse(x));
    }


    public int reverse(int x) {
        char[] cha = String.valueOf(x).toCharArray();
        int times = cha.length % 2 == 0 ? cha.length / 2 - 1 :cha.length / 2;

        int offset = 0;
        if(cha.length > 0 && cha[0] == '-'){
            offset = 1;
        }
        char temp;
        for(int i = offset ; i <= times ; i ++){
            temp = cha[i];
            cha[i] = cha[cha.length-1 - i + offset];
            cha[cha.length-1 - i  + offset] = temp;
        }

        try {
            String str = new String(cha);
            return Integer.parseInt(str);
        }catch (NumberFormatException e){
            return 0;
        }
    }

    public static void main(String[] args) {
        AbstractHandler.doCal(ReverseNum.class);
    }
}
