package javalogic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListRemove {


    public static void main(String[] args) {
        List<Integer> list= new ArrayList<Integer>();
        for(int i=0;i<10;i++){
            list.add(i+1);
        }
        for(int i=0;i<list.size();i++){
            if (list.get(i)%2==0){
                list.remove(list.get(i));
                i--;
            }

        }
        System.out.println(list);
    }
}
