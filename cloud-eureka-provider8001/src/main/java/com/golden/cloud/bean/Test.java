package com.golden.cloud.bean;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.SystemUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

@Component
public class Test {
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Test test = (Test) o;
        return Objects.equals(name, test.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
    static  LongAdder longAdder = new LongAdder();
    public static void increment() {
        longAdder.increment();
    }
    static AtomicInteger atomicInteger = new AtomicInteger();
    public static void increment2() {
        atomicInteger.getAndIncrement();
    }
    public static void main(String[] args) throws InterruptedException {
//        String str = "a,b,c,,";
//        String[] ary = str.split(",");
//        // 预期大于 3，结果是 3
//        System.out.println(ary.length);
//        List<String> list2 = new ArrayList<>();
//        list2.add("a");
//        list2.add("b");
//        list2.add("c");
//        List<String> strings = list2.subList(0, 3);
//        String[] strings1 = list2.toArray(new String[0]);
//        for (String s : strings1) {
//            System.out.println(s);
//        }
//
//        List<String> list = new ArrayList<String>();
//        list.add("1");
//        list.add("2");
//        ListIterator<String> stringListIterator = list.listIterator();
//        while (stringListIterator.hasNext()) {
//            String v = stringListIterator.next();
//            if (v .equals("2")) {
//                stringListIterator.remove();
//            }
//        }
//
//        for (String s : list) {
//            System.out.println(s);
//        }
//
//
//        Map<String,String> map = new HashMap<>();
//        map.put("k1","v1");
//        map.put("k2","v2");
//        map.put("k3","v3");
//        for (Map.Entry<String,String>entry:map.entrySet()) {
//        }
//        Instant instant = Instant.now();
//        System.out.println(instant);
        long start1 = System.currentTimeMillis();
        Thread a = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                increment();
            }
        }, "A");
        Thread b = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                increment();
            }
        }, "B");
        a.start();
        b.start();
        a.join();
        b.join();
        long end1 = System.currentTimeMillis();
        System.err.println(longAdder.intValue());
        System.out.println(end1-start1+"(ms)");

        long start2 = System.currentTimeMillis();
        Thread c = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                increment2();
            }
        }, "C");
        Thread d = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                increment2();
            }
        }, "D");
        //TimeUnit.SECONDS.sleep(5);
        c.start();
        d.start();
        c.join();
        d.join();
        long end2 = System.currentTimeMillis();
        System.err.println(atomicInteger.intValue());
        System.out.println(end2-start2+"(ms)");

        // 低竞争下 atomicInteger性能优于longAdder
        // 高竞争下 longAdder性能优于atomicInteger
    }


}

