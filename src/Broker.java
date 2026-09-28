import java.util.Random;

public class Broker {

    private QueueInterface<Message> queue;
    private Random random;

    public Broker() {
        queue = new LinkedQueue<>();
        random = new Random();
    }

    public void enqueue(Message message) throws QueueOverflowException {
        queue.enqueue(message);
    }

    public void processBatch() throws QueueUnderflowException, QueueOverflowException {

        // Remember how many messages are currently in the queue
        int batchSize = queue.size();

        // Only process the messages that were originally in the queue
        for (int i = 0; i < batchSize; i++) {

            Message message = queue.dequeue();

            int randomNumber = random.nextInt(100);

            if (randomNumber < message.getSuccessChance()) {

                System.out.println("SUCCESS: " + message);

            } else {

                message.incrementRetryCount();

                queue.enqueue(message);

                System.out.println("FAILED - Re-enqueued: " + message);
            }
        }
    }
}