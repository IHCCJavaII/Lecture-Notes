package messaging.models;

public class ImageMessage extends Message {

    private final String imageFileName;

    public ImageMessage(String sender, String recipient, String imageFileName) {
        super(sender, recipient);
        this.imageFileName = imageFileName;
    }

    @Override
    public String getType() {
        return "Image";
    }

    @Override
    public String send() {
        return "[IMAGE] " + getSender() + " -> " + getRecipient() + ": sent file " + imageFileName;
    }
}
