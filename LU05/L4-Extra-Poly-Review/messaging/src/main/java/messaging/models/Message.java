package messaging.models;

public abstract class Message {
    private final String sender;
    private final String recipient;

    protected Message(String sender, String recipient) {
        this.sender = sender;
        this.recipient = recipient;
    }

    public String getSender() {
        return sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public abstract String getType();

    public abstract String send();
}
