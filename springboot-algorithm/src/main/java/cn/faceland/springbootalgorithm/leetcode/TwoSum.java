package cn.faceland.springbootalgorithm.leetcode;

/**
 * @author watermelon
 * @Date 2019-11-12
 * @Description 石杉的架构笔记：https://mp.weixin.qq.com/s/3FyxT7mvxCwI3oesS3ymOg
 * 乐扣传送门： https://leetcode-cn.com/problems/two-sum/
 */
public class TwoSum extends AbstractHandler{

    /**
     * 给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。
     * 函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。
     * 说明:
     * 返回的下标值（index1 和 index2）不是从零开始的。
     * 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
     * 示例:
     * 输入: numbers = [2, 7, 11, 15], target = 9
     * 输出: [1,2]
     * 解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
     */

    @Override
    void doCalculations() {
        int[] nums = {2, 7, 11, 15};
        int target = 9;

//        int[] result = twoSum(nums, target);
        int[] result = twoSum2(nums, target);
        for(int i : result){
            System.out.println(i);
        }
    }

    /**
     * 我自己的原始方法实现的
     */
    public int[] twoSum(int[] nums, int target) {
        for(int i = 0 ; i < nums.length -1 ; i ++){
            for(int b = i +1  ; b < nums.length ; b ++){
                if(nums[i] + nums[b] == target){
                    return new int[]{i,b};
                }
            }
        }
        return new int[]{};
    }

    /**
     * 指针移动法
     */
    public int[] twoSum2(int[] nums, int target) {
        int l = 0 , f = nums.length -1;
        while (l < f){
            if(nums[l] + nums[f] == target){
                return new int[]{l,f};
            }else if(nums[l] + nums[f] > target){
                f --;
            }else if(nums[l] + nums[f] < target){
                l ++;
            }
        }

        return new int[]{};
    }
}
