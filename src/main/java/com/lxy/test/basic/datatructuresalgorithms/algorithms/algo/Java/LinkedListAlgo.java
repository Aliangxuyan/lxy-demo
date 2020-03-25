package com.lxy.test.basic.datatructuresalgorithms.algorithms.algo.Java;

/**
 * @author lxy
 * @date 2020-03-17
 * <p>
 * 1) 单链表反转
 * 2) 链表中环的检测
 * 3) 两个有序的链表合并
 * 4) 删除链表倒数第n个结点
 * 5) 求链表的中间结点
 */
public class LinkedListAlgo {
    public Node head = null;

    public static void main(String[] args) {
        LinkedListAlgo link = new LinkedListAlgo();

        int data[] = {1, 2, 5, 3, 1};

        for (int i = 0; i < data.length; i++) {
            link.insertTail(data[i]);
        }
        link.printAll();


        // 3)  有序链表合并 Leetcode 21
        int n1[] = {1, 2, 2, 5, 6};
        int n2[] = {1, 2, 4, 8, 9};
        LinkedListAlgo l1 = new LinkedListAlgo();
        LinkedListAlgo l2 = new LinkedListAlgo();

        for (int i : n1) {
            l1.insertTail(i);
        }
        for (int i : n2) {
            l2.insertTail(i);
        }
        Node result3 = link.mergeTwoLists(l1.head, l2.head);
        while (result3 != null) {
            System.out.println("result3:" + result3.data);
            result3 = result3.next;
        }


        // 2) 链表中环的检测
        boolean result2 = link.checkCircle(link.head);
        System.out.println("链表是否存在环：" + result2);


        // 1) 单链表反转
        Node p = link.reverse(link.head);
        while (p != null) {
            System.out.println("result2:" + p.data);
            p = p.next;
        }

    }

    // 5) 求链表的中间结点 让快指针一次前进两步，慢指针一次前进一步，当快指针到达链表尽头时，慢指针就处于链表的中间位置

    /**
     * 1 2 3 4 5
     *
     * @param list
     * @return
     */
    public Node middleNode(Node list) {
        Node fast = list;
        Node slow = list;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }


    /**
     * 3 4 5 2 4 6 8
     *
     * @param list
     * @param k
     * @return 删除倒数第二个元素
     */

    // 4)  删除链表倒数第n个结点
    public static Node deleteLastKth(Node list, int k) {
        Node fast = list;
        int i = 1;
        while (fast != null && i < k) {
            fast = fast.next;
            ++i;
        }

        if (fast == null) return list;

        Node slow = list;
        Node prev = null;
        while (fast.next != null) {
            fast = fast.next;
            prev = slow;
            slow = slow.next;
        }

        if (prev == null) {
            list = list.next;
        } else {
            prev.next = prev.next.next;
        }
        return list;
    }

    // 方法二（比较简洁）： 快指针先走n 步,再走慢指针，快指针走到null 的时候慢指针的下一个就是要删除的数据
    public Node removeNthFromEnd(Node head, int n) {
        Node node = new Node(0); // 哨兵
        node = head;
        //快慢指针
        Node slow = head;
        Node fast = head;

        for (int i = 0; i < n; i++)
            fast = fast.next;
        //n等于链表长度时,正好是删除第一个节点
        if (fast == null)
            return head.next;
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        //因为前面slow是指针操作,所以这里对slow进行操作就能操作head
        slow.next = slow.next.next;//删除结点,其实是“跳过”了这个结点;没有释放内存！
        return head;
    }


    // 3)  有序链表合并 Leetcode 21
    public Node mergeTwoLists(Node l1, Node l2) {
        Node soldier = new Node(0); //利用哨兵结点简化实现难度
        Node p = soldier;
        while (l1 != null && l2 != null) {
            if (l1.data < l2.data) {
                p.next = l1;
                l1 = l1.next;
            } else {
                p.next = l2;
                l2 = l2.next;
            }
            p = p.next;
        }
        if (l1 != null) {
            p.next = l1;
        }
        if (l2 != null) {
            p.next = l2;
        }
        return soldier.next;
    }


    // 2) 链表中环的检测
    // —— 快慢指针，
    // —— 还可以是标记等其他方式 https://www.cnblogs.com/chanshuyi/p/5314216.html
    // —— set 可以构造一个set集合或者散列表，每次获取到上层推荐人就去散列表里先查，没有查到的话就加入，如果存在则表示存在环了
    // 算法的思想是设定两个指针p, q，其中p每次向前移动一步，q每次向前移动两步。那么如果单链表存在环，则p和q相遇；否则q将首先遇到null
    public boolean checkCircle(Node list) {
        if (list == null) return false;
        Node fast = list.next;
        Node slow = list;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) return true;
        }
        return false;
    }


    // 1) 单链表反转
    public Node reverse(Node head) {
        Node pre = null;
        Node curr = head;
        Node tempNext;
        while (curr != null) {
            tempNext = curr.next;
            curr.next = pre;
            pre = curr;
            curr = tempNext;
        }
        return pre;
    }

    //********************************************************************************************

    public void printAll() {
        Node p = head;
        while (p != null) {
            System.out.print(p.data + " ");
            p = p.next;
        }
        System.out.println();
    }


    //顺序插入
    //链表尾部插入
    public void insertTail(int value) {

        Node newNode = new LinkedListAlgo.Node(value, null);
        //空链表，可以插入新节点作为head，也可以不操作
        if (head == null) {
            head = newNode;

        } else {
            Node q = head;
            while (q.next != null) {
                q = q.next;
            }
            newNode.next = q.next;
            q.next = newNode;
        }
    }

    public static class Node {
        int data;
        Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

        public Node(int data) {
            data = data;
        }

        public int getNode() {
            return data;
        }
    }
}
