package designPatterns.factory;

import java.util.Map;

public class MyMessageEmail extends MyAbstractMessage {

    @Override
    public void sendMessage() throws Exception {
        System.out.println("Email:"+getMessageParam().get("email"));
    }
}
