public class Main {

    public static void main(String[] args) {

        try {
            Broker broker = new Broker();

            Message message1 =
                    new Message("001", "Order received", 100);

            Message message2 =
                    new Message("002", "Payment processing", 50);

            Message message3 =
                    new Message("003", "Shipping request", 25);

            Message message4 =
                    new Message("004", "Customer notification", 75);

            broker.enqueue(message1);
            broker.enqueue(message2);
            broker.enqueue(message3);
            broker.enqueue(message4);

            System.out.println("=== Processing Batch ===");

            broker.processBatch();

        } catch (QueueOverflowException | QueueUnderflowException e) {
            System.out.println("Queue error: " + e.getMessage());
        }
    }
}
