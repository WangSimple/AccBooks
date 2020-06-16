package designPatterns.factory;

import java.util.Map;

public interface IMyMessage {
    Map<String,Object> getMessageParam();
    void setMessageParam(Map<String,Object> messageParam);
    void sendMessage()throws Exception;

}
