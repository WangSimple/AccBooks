package designPatterns.factory;

public class TMain {
    public static void main(String[] args){
        IMessageFactory factory=new MyMessageFactory();
        try {
            factory.createMessage(MessageType.MESSAGE_TYPE_SMS).sendMessage();
            factory.createMessage(MessageType.MESSAGE_TYPE_EMAIL).sendMessage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
