public class Message {
    private String messageId;
    private String payload;
    private int retryCount;
    private int successChance;

    public Message(String messageId, String payload, int successChance) {
        this.messageId = messageId;
        this.payload = payload;
        this.successChance = successChance;
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

    public int getSuccessChance() {
        return successChance;
    }

    public void incrementRetryCount() {
        retryCount++;
    }

    @Override
    public String toString() {
        return "Message ID: " + messageId +
                ", Payload: " + payload +
                ", Retry Count: " + retryCount +
                ", Success Chance: " + successChance + "%";
    }
}
