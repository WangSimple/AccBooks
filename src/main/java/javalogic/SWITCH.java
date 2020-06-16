package javalogic;

public class SWITCH {

    public static void main(String[] args){
        String str="中文";
        String str2="a";
        switch (str){
            case "a":
                System.out.println("a");
            case "b":
                System.out.println("b");
            case "c":
                System.out.println("c");
                default:
                    System.out.println("default");
            case "d":
                System.out.println("d");
        }
        switch (str2){
            case "a":
                System.out.println("a");
            case "b":
                System.out.println("b");
            case "c":
                System.out.println("c");
            default:
                System.out.println("default");
            case "d":
                System.out.println("d");
        }

    }
}
