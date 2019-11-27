package cn.faceland.springbootalgorithm;

import cn.faceland.springbootalgorithm.leetcode.*;
import org.junit.jupiter.api.Test;

//@SpringBootTest
class DoTest {
    /**
     * leetcode上第1号问题：Two Sum II
     */
    @Test
    public void twoSum(){
        AbstractHandler.doCal(TwoSum.class);
    }

    /**
     * leetcode上第283号问题：Move Zeros
     */
    @Test
    public void MoveZeros(){
        AbstractHandler.doCal(MoveZeros.class);
    }

    /**
     * leetcode上第2号
     */
    @Test
    public void MoveZeros2(){
        AbstractHandler.doCal(TwoSum2.class);
    }
    /**
     * leetcode上第3号
     */
    @Test
    public void FindRepetStr(){
        AbstractHandler.doCal(FindRepetStr.class);
    }
}
