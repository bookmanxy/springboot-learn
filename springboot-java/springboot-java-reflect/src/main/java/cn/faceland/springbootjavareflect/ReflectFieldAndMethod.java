package cn.faceland.springbootjavareflect;

import cn.faceland.springbootjavareflect.entity.SportHealthstep48Hour;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

/**
 * @author watermelon on 2019/10/31 11:18
 * @description 反射类字段 和 方法
 */
public class ReflectFieldAndMethod {
    /**
     * step48Vo实体类有个数组，数组长度48，分别对应SportHealthstep48Hour的48个字段
     * @return
     */
    public SportHealthstep48Hour saveByVo(Integer[] steps) {
        SportHealthstep48Hour step48 = new SportHealthstep48Hour();
        if(steps.length == 48){
            Field[] f = SportHealthstep48Hour.class.getDeclaredFields();
            for(int i=0;i<f.length;i++) {
                //获取属相名
                String attributeName = f[i].getName();
                if (attributeName.contains("stepNumber")) {
                    String methodName = attributeName.substring(0, 1).toUpperCase() + attributeName.substring(1);
                    Integer index = Integer.parseInt(methodName.substring(methodName.length() - 1));
                    System.out.println("当前数组id：" + index);

                    try {
                        //获取Test类当前属性的setXXX方法（私有和公有方法）
                        /*Method setMethod=Test.class.getDeclaredMethod("set"+methodName);*/
                        //获取Test类当前属性的setXXX方法（只能获取公有方法）
                        Method setMethod = SportHealthstep48Hour.class.getMethod("set" + methodName, Integer.class);
                        //执行该set方法
                        try {
                            setMethod.invoke(step48, steps[i - 1]);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        System.out.println(step48);
        return null;
    }

    public static void main(String[] args) {
        Integer[] steps = new Integer[48];
        for(int i = 0 ; i < 48 ; i ++){
            steps[i] = i * 100;
        }
        ReflectFieldAndMethod reflectFieldAndMethod = new ReflectFieldAndMethod();
        reflectFieldAndMethod.saveByVo(steps);

    }
}
