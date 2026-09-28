import java.util.Random;

public class Broker {

    private static final int MAX_RETRIES = 3;

    private QueueInterface<Message> queue;
    private QueueInterface<Message> deadLetterQueue;
    private Random random;

    public Broker() {
        queue = new LinkedQueue<>();
        deadLetterQueue = new LinkedQueue<>();
        random = new Random();
    }

    public void enqueue(Message message) throws QueueOverflowException {
        queue.enqueue(message);
    }

    public void processBatch()
            throws QueueUnderflowException, QueueOverflowException {

        while (!queue.isEmpty()) {

            Message message = queue.dequeue();

            int randomNumber = random.nextInt(100);

            if (randomNumber < message.getSuccessChance()) {

                System.out.println("SUCCESS: " + message);

            } else {

                message.incrementRetryCount();

                if (message.getRetryCount() >= MAX_RETRIES) {

                    deadLetterQueue.enqueue(message);

                    System.out.println(
                            "FAILED - Moved to DLQ: " + message
                    );

                } else {

                    queue.enqueue(message);

                    System.out.println(
                            "FAILED - Re-enqueued: " + message
                    );
                }
            }
        }
    }
    public void displayAndClearDLQ()
            throws QueueUnderflowException {

        System.out.println("\n=== Dead-Letter Queue ===");

        if (deadLetterQueue.isEmpty()) {
            System.out.println("DLQ is empty.");
            return;
        }

        while (!deadLetterQueue.isEmpty()) {

            Message message = deadLetterQueue.dequeue();

            System.out.println(message);
        }

        System.out.println("DLQ has been cleared.");
    }
}
