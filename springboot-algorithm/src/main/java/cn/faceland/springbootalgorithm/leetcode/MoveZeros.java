package cn.faceland.springbootalgorithm.leetcode;

/**
 * @author watermelon
 * @Date 2019-11-12
 * @Description 石杉的架构笔记：https://mp.weixin.qq.com/s/ikxTnt9kpCtxHmosCjxcuw
 * 乐扣传送门：https://leetcode-cn.com/problems/move-zeroes/
 */
public class MoveZeros extends AbstractHandler {
    /**
     * 给定一个数组nums，写一个函数，将数组中所有的0挪到数组的末尾，⽽维持其他所有非0元素的相对位置。
     * 举例: nums = [0, 1, 0, 3, 12]，函数运⾏后结果为[1, 3, 12, 0, 0]
     */

    @Override
    void doCalculations() {
        int[] nums = {0,1,0,3,12};
        int target = 0;
        nums = move1(nums, target);
        for(int i  : nums){
            System.out.println(i);
        }
    }

    public int[] move1(int[] nums, int target){
        int l = 0 ;
        for(int i = 0 ; i < nums.length ; i ++){
            if(nums[i] != 0){
                nums[l] = nums[i];

                if(i != l){
                    nums[i] = 0;
                }

                l ++;
            }

        }

//        for(;l < nums.length; l ++){
//            nums[l] = 0;
//        }
        return nums;
    }
}
