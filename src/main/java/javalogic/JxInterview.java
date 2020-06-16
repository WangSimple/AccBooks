package javalogic;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.lang.reflect.Proxy;
import java.util.ArrayList;

public class JxInterview {



    public void t1(){
        String s="abcabc";
        s.concat("abc");
        System.out.println(s);
    }

    public void t2(){
        Float f1=new Float(0.1f);
        Float f2=new Float(0.1f);
        Double d1=new Double(0.1);
        System.out.println(f1==f2);
        System.out.println(f1.equals(f2));
        System.out.println(d1.equals(f1));
        System.out.println(f1.equals(d1));
    }

    public void t3(){
        ArrayList<String> list=new ArrayList<String>();
        //编译出错
        //list.add(true);
        //list.add(123);
        list.add("abc");
    }

    public void t4(){
        short i=0;
        System.out.println(i);
        System.out.println(i++);
        System.out.println(i);
        System.out.println(++i);
        System.out.println(i);
    }

    public void t5(){
        int[] arr={1,2,3,4,5};
        int temp=arr[0];
        for (int i=1;i<5;i++){
            if(arr[i]<temp){
                temp=arr[i];
            }
        }
        System.out.println(temp);
    }

    public void t6()  {
        String str="abcdefg";
        System.out.println(str.substring(2,3));
        StringBuilder sb=new StringBuilder("abcdefg");
        System.out.println(sb.substring(2,3));
        System.out.println(sb);
        //String charset=new String(str.getBytes(),"utf-8");
    }

    public void t7(){
        String str1="a";
        String str2="a";
        System.out.println(str1==str2);
        System.out.println("str"=="str");
        System.out.println("str".equals(new String("str")));
        System.out.println("str"==new String("str"));
    }

    public static void main(String []args){
       // new JxInterview().t7();
        ArrayList list=new ArrayList();
        list.add(1);
        print(list);
        System.out.println(list.get(1));
    }

    public static void print(ArrayList list){
        list.add(2);
        list=new ArrayList();
        list.add(3);
        list.add(4);
    }
}
