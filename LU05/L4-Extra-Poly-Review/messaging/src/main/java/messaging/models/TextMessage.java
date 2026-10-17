package messaging.models;

public class TextMessage extends Message {
    private final String text;

    public TextMessage(String sender, String recipient, String text) {
        super(sender, recipient);
        this.text = text;
    }

    @Override
    public String getType() {
        // Return type of message as a string
        return this.getType().toString();
    }

    @Override
    public String send() {
        return "[" + getType() + "] " 
            + getSender() + " to " + getRecipient() 
            + ": " + text;
    }
}
