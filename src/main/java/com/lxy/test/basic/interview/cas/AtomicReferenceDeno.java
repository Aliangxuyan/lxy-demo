package com.lxy.test.basic.interview.cas;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author lxy
 * @date 2019/4/20
 */
@Getter
@ToString
@AllArgsConstructor
class User {
    String username;
    int age;

    @Override
    public String toString() {
        return super.toString();
    }
}

/**
 * 原子引用解决ABA 问题
 */
public class AtomicReferenceDeno {

    public static void main(String[] args) {
        AtomicReference<User> atomicReference = new AtomicReference<>();
        User z3 = new User("z3", 12);
        User li4 = new User("li4", 34);

        atomicReference.set(z3);

        System.out.println(atomicReference.compareAndSet(z3, li4) + "\t" + atomicReference.get().toString());
        System.out.println(atomicReference.compareAndSet(li4, z3) + "\t" + atomicReference.get().toString());
        System.out.println(atomicReference.compareAndSet(z3, li4) + "\t" + atomicReference.get().toString());

    }
}
