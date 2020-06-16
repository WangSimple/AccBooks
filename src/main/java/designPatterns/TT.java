package designPatterns;

import java.util.*;

public class TT {

    public static void main (String[] args){
        Map<String,Integer> map=new HashMap<String,Integer>();
        List<Integer> list=new LinkedList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);
        list.add(0);
       for(Integer i:list){
            System.out.println(i);
            if (i==5){
                list.remove(i);
            }
        }
        for(int i=0;i<list.size();i++){
            if (list.get(i)==5){
                list.remove(i);
            }
        }
        for(Integer i:list){
            System.out.println(i);
        }
    }
}
