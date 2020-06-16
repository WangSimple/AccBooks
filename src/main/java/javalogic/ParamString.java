package javalogic;

public class ParamString {
    public static void main(String[] args) {
        ParamString test=new ParamString();
        String str="abc";
        test.t1(str);
        System.out.println(str);
    }
    public void t1(String str){
        str.replace("a","b");
        str.toUpperCase();
    }
}
