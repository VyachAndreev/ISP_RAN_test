package org.example;

import java.util.ArrayDeque;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        ArrayDeque<String> a1 = new ArrayDeque<>();
        a1.add("1");
        a1.add("2");
        ArrayDeque<String> a2 = new ArrayDeque<>();
        a2.add("3");
        a2.add("4");
        a2.add("5");
        ArrayDeque<String> a3 = new ArrayDeque<>();
        a3.add("6");
        ArrayDeque<String> a4 = new ArrayDeque<>();
        a4.add("7");
        a4.add("8");
        a4.add("9");
        a4.add("0");
        ArrayDeque<String>[] res = new ArrayDeque[4];
        res[0] = a1;
        res[1] = a2;
        res[2] = a3;
        res[3] = a4;
        CollectionIterator2<String> collectionIterator = new CollectionIterator2<>(4, res);
        while (true) {
            System.out.println(collectionIterator.next());
        }
    }
}