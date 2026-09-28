public class Main {
    public static void main(String[] args) {
        QueueInterface<Message> queue = new LinkedQueue<>();

        Message message1 = new Message("001", "Order received");
        Message message2 = new Message("002", "Payment received");
        Message message3 = new Message("003", "Order shipped");

        try {
            queue.enqueue(message1);
            queue.enqueue(message2);
            queue.enqueue(message3);

            System.out.println("Dequeuing messages:");

            while (!queue.isEmpty()) {
                Message message = queue.dequeue();
                System.out.println(message);
            }

        } catch (QueueOverflowException | QueueUnderflowException e) {
            System.out.println("Queue error: " + e.getMessage());
        }
    }
}