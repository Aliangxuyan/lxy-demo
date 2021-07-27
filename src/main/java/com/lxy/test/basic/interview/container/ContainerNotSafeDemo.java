package com.lxy.test.basic.interview.container;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author lxy
 * @date 2019/4/20
 * <p>
 * 集合类不安全的问题
 */
public class ContainerNotSafeDemo {
    public static void main(String[] args) {
        mapNotSafe();

    }

    public static void mapNotSafe(){
        Map<String,String > map;
        map = new HashMap<>();
//        map = Collections.synchronizedMap(new HashMap<>());
//        map = new ConcurrentHashMap<>();
        for (int i = 1; i < 30; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0, 8));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
    }


    public static void setNotSafe(){
 //        Set<String> set = new HashSet<>();
//        Set<String> set = Collections.synchronizedSet(new HashSet<>());
        Set<String> set = new CopyOnWriteArraySet<>();
        for (int i = 1; i < 30; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }

    /**
     * list 不安全类
     */
    public static void listNotSafe(){
//        0.    List<String> list = new Vector<>();
//        1.    List<String> list = new Vector<>();

//        2.    List<String> list = Collections.synchronizedList(new ArrayList<>());

    CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
    for (int i = 1; i < 30; i++) {
        new Thread(() -> {
            list.add(UUID.randomUUID().toString().substring(0, 8));
            System.out.println(list);
        }, String.valueOf(i)).start();
    }
}
}

/**
 * 1 故障现象
 *      java.util.ConcurrentModificationException
 *
 * 2 导致原因
 *      并发争抢修改导致，参考我们的花名册签名情况。
 *      一个人正在写，另外一个人争抢，导致数据不一样异常，即并发修改异常（java.util.ConcurrentModificationException）
 *
 * 3 解决方案
 *      3.1 List<String> list = new Vector<>();
 *      3.2 Collections.synchronizedList(new ArrayList<>());
 *      3.3 new CopyOnWriteArrayList<>();
 * 4 优化建议（同样的错误不犯第二次）
 *
 *
 * 笔记：
 * 写时复制
 * copyOnWrite 容器写时复制的容器，往一个容器添加元素的时候，不直接往当前容器Object[] 添加，
 * 而是先将当前容器 object[]进行 copy,复制出一个新的容器 Object[] newElements,然后新的容器
 * object[] newElements 里添加元素，添加完之后，再将原容器的引用指向新的容器 setArray(newElements);
 * 这样做的好处是可以对 CopyOnWrite 容器进行并发的读，而不需要加锁，因为当前容器不会添加任何元素，
 * 所以 CopyOnWrite 容器也是一种读写分离的思想，读和写不同的容器
    /*public boolean add(E e) {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            Object[] elements = getArray();
            int len = elements.length;
            Object[] newElements = Arrays.copyOf(elements, len + 1);
            newElements[len] = e;
            setArray(newElements);
            return true;
        } finally {
            lock.unlock();
        }
    }*/
