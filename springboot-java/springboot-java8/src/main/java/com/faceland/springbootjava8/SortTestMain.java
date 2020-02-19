package com.faceland.springbootjava8;

/**
 * @author watermelon
 * @Date 2020-02-19
 * @Description
 */

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tan
 * @version V1.0
 * @description: GroupTestMain
 * @date 2019/9/27
 */
public class SortTestMain {

    public static void main(String[] args) {
        List<Person> persons = new ArrayList<>();//列表

        persons.add(new Person("aaa", 12,5.22));
        persons.add(new Person("ccc", 20,16.33));
        persons.add(new Person("bbb", 8,4.11));


        //no 1. Collections排序

//        System.out.println("Age排序前：");
//        persons.forEach((person)->System.out.println(person));
//
//        //Collections排序升序
//        Collections.sort(persons, new Comparator<Person>() {
//            @Override
//            public int compare(Person o1, Person o2) {
//                return o1.getAge().compareTo(o2.getAge());
//            }
//        });
//
//        System.out.println("Age升序排序后：");
//        persons.forEach((person)->System.out.println(person));
//
//        //Collections排序降序
//        Collections.sort(persons, new Comparator<Person>() {
//            @Override
//            public int compare(Person o1, Person o2) {
//                return o2.getAge().compareTo(o1.getAge());
//            }
//        });
//
//        //循环打印
//        System.out.println("Age降序排序后：");
//        persons.forEach((person)->System.out.println(person));
//
//        System.out.println("***************************************************************");

        // no 2.Lambda写法，JAVA8的写法
        // persons.sort((Developer o1, Developer o2)->o1.getAge().compareTo(o2.getAge())); //可以更简洁，如下：

        //升序排序
//        persons.sort((a, b) -> a.getName().compareTo(b.getName()));
//        System.out.println("Name升序排序后：");
//        persons.forEach((person)->System.out.println(person));


        //降序排序, a,b倒转即可
        persons.sort((a, b) -> b.getWeight().compareTo(a.getWeight()));
        System.out.println("Name降序排序后：");
        persons.forEach((person)-> System.out.println(person));

//        //升序排序
//        persons.sort(Comparator.comparing(Person::getAge));
//        System.out.println("Age升序排序：");
//        persons.forEach((person)->System.out.println(person));
//
//        //降序排序, 加上 .reversed() 即可
//        persons.sort(Comparator.comparing(Person::getAge).reversed());
//        System.out.println("Age降序排序：");
//        persons.forEach((person)->System.out.println(person));
//
//        //先配置再排序
//        Comparator<Person> ageComparator = (o1, o2)->o1.getName().compareTo(o2.getName());
//        persons.sort(ageComparator);                //按上面配置的顺序取值
//        System.out.println("Name升序排序后：");
//        persons.forEach((person)->System.out.println(person));
//
//        System.out.println("Name降序排序后：");
//        persons.sort(ageComparator.reversed());     //按上面配置的顺序反向取值
//        persons.forEach((person)->System.out.println(person));
//
//
//        // 多条件排序第二个写法，先按Age排序，再根据name排序
//        persons.sort(Comparator.comparing(Person::getAge).thenComparing(Person::getName));
//        System.out.println("多条件排序后：");
//        persons.sort(ageComparator.reversed());     //按上面配置的顺序反向取值
//        persons.forEach((person)->System.out.println(person));
//
//        // 中文排序
//        Collections.sort(persons, (Persono1, Persono2) -> Collator.getInstance(Locale.CHINESE).compare(Persono1.getName(), Persono2.getName()));

    }

}


