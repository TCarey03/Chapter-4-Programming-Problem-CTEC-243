public class Message {
    private String messageId;
    private String payload;
    private int retryCount;

    public Message(String messageId, String payload) {
        this.messageId = messageId;
        this.payload = payload;
        this.retryCount = 0;
    }

    public String getMessageId() {
        return messageId;
    }

    public String getPayload() {
        return payload;
    }

    public int getRetryCount() {
        return retryCount;
    }

    public void incrementRetryCount() {
        retryCount++;
    }

    @Override
    public String toString() {
        return "Message ID: " + messageId +
                ", Payload: " + payload +
                ", Retry Count: " + retryCount;
    }
}