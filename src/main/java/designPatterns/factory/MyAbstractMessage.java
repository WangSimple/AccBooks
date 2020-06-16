package designPatterns.factory;

import java.util.Map;

public abstract class MyAbstractMessage implements IMyMessage {
    private Map<String, Object> myMessageParam;
    @Override
    public Map<String, Object> getMessageParam() {
        return myMessageParam;
    }
    @Override
    public void setMessageParam(Map<String, Object> messageParam) {
        myMessageParam=messageParam;
    }
}
