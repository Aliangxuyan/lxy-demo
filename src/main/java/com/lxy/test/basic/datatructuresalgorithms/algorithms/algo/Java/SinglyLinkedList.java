package com.lxy.test.basic.datatructuresalgorithms.algorithms.algo.Java;

/**
 * @author lxy
 * @date 2020-03-17
 * <p>
 * * 1）单链表的插入、删除、查找操作；
 * * 2）链表中存储的是int类型的数据；
 * <p>
 * 双链表反转
 */
public class SinglyLinkedList {
    private Node head = null;

    public static void main(String[] args) {
        SinglyLinkedList link = new SinglyLinkedList();
        //int data[] = {1};
        //int data[] = {1,2};
        //int data[] = {1,2,3,1};
        //int data[] = {1,2,5};
        //int data[] = {1,2,2,1};
        // int data[] = {1,2,5,2,1};
        int data[] = {1, 2, 5, 3, 1};

        for (int i = 0; i < data.length; i++) {
            //link.insertToHead(data[i]);
            link.insertTail(data[i]);
        }
        link.printAll();
        Node p = link.reverseList(link.head);
        while (p != null) {
            System.out.println("aa:" + p.value);
            p = p.next;
        }
    }

    public void printAll() {
        Node p = head;
        while (p != null) {
            System.out.print(p.value + " ");
            p = p.next;
        }
        System.out.println();
    }


    //顺序插入
    //链表尾部插入
    public void insertTail(int value) {

        Node newNode = new Node(value, null);
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

    //    双链表反转
    public Node reverseList(Node head) {

        Node pre = null;
        Node curr = head;
        while (curr != null) {
            Node tmp = curr.next;
            curr.next = pre;
            pre = curr;
            curr = tmp;
        }
        return pre;
    }

    public static class Node {
        int value;
        Node next;

        public Node(int data, Node next) {
            this.value = data;
            this.next = next;
        }
    }
}

