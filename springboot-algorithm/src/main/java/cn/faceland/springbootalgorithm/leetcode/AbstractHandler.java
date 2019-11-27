package cn.faceland.springbootalgorithm.leetcode;

public abstract class  AbstractHandler {
    /**
     * 执行运算
     */
    abstract void doCalculations();

    public static <T extends  AbstractHandler> void  doCal(Class<T> c){
        try {
            c.newInstance().doCalculations();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
