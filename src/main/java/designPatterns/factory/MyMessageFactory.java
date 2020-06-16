package designPatterns.factory;

import java.util.HashMap;
import java.util.Map;


public class MyMessageFactory implements IMessageFactory {

    @Override
    public IMyMessage createMessage(MessageType messageType) {
        IMyMessage message=null;
        Map<String,Object> messageParam=new HashMap<String,Object>();
        switch (messageType) {
            case MESSAGE_TYPE_SMS:
                messageParam.put("phonenum", "123456789");
                message = new MyMessageSMS();
                break;
            case MESSAGE_TYPE_EMAIL:
                messageParam.put("email", "test@test.com");
                message = new MyMessageEmail();
                break;
            default:
                //throw new Exception("出错了");
        }
        message.setMessageParam(messageParam);
        return message;
    }
}
