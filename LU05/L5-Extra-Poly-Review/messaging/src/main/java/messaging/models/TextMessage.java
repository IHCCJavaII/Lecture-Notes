package messaging.models;

public class TextMessage extends Message {
    private final String text;

    public TextMessage(String sender, String recipient, String text) {
        super(sender, recipient);
        this.text = text;
    }

    @Override
    public String getType() {
        return ;
    }

    @Override
    public String send() {
        return "[TEXT] " + getSender() + " -> " + getRecipient() + ": " + text;
    }
}
