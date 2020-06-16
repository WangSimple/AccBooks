package designPatterns.factory;

public interface IMessageFactory {
    IMyMessage createMessage(MessageType messageType);
}
