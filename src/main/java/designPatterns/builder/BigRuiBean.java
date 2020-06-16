package designPatterns.builder;

/**
 * Builder pattern 可以创建不可变的对象
 */
public class BigRuiBean {
    private final String param1;
    private final String param2;
    private final String param3;
    private final String param4;
    public String toString(){
        return param1+param2+param3+param4;
    }
    private BigRuiBean(Builder builder){
        this.param1=builder.param1;
        this.param2=builder.param2;
        this.param3=builder.param3;
        this.param4=builder.param4;
    }
    public static class Builder{
        private String param1;
        private String param2;
        private String param3;
        private String param4;

        public Builder setParam1(String param1) {
            this.param1 = param1;
            return this;
        }
        public Builder setParam2(String param2) {
            this.param2 = param2;
            return this;
        }
        public Builder setParam3(String param3) {
            this.param3 = param3;
            return this;
        }
        public Builder setParam4(String param4) {
            this.param4 = param4;
            return this;
        }
        public BigRuiBean build(){
            return new BigRuiBean(this);
        }
    }
}


class TestMain{
    public static void main(String [] args){
        BigRuiBean brb=new BigRuiBean.Builder().setParam1("1").setParam2("2").setParam3("3").setParam4("4").build();
        System.out.println(brb);
    }
}