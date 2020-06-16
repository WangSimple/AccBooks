package javalogic;

/**
 * jvm加载顺序
 */
public class JVMLoaderBean extends SuperBean{
    public JVMLoaderBean(){
        System.out.println("constructor");
    }
    {
        System.out.println("Block 1");
    }
    static{
        System.out.println("static 1");
    }
    public static JVMLoaderBean bean=new JVMLoaderBean();

    static{
        System.out.println("static 2");
    }
    {
        System.out.println("Block 2");
    }
    public static void main(String[] args){
        System.out.println("main 1");
        JVMLoaderBean bean2=new JVMLoaderBean();

    }


    /**
     * 运行结果
     * Super static
     static 1
     Super block
     Super constructor
     Block 1
     Block 2
     constructor
     static 2
     main 1
     Super block
     Super constructor
     Block 1
     Block 2
     constructor
     */
}
