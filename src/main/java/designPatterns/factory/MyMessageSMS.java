package designPatterns.factory;

import java.util.Map;

public class MyMessageSMS extends MyAbstractMessage{

    @Override
    public void sendMessage() throws Exception {
        System.out.println("SMS："+getMessageParam().get("phonenum"));
    }
}
