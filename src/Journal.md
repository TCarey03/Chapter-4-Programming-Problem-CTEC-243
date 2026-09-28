Phase 1 Journal
Queue vs. Stack

A Queue is appropriate for a message broker because messages should normally be processed in the order they are received. A Queue uses FIFO (First In, First Out), so the first message added is the first message processed.

A Stack uses LIFO (Last In, First Out). If I used a Stack instead of a Queue, the newest message would be processed first. This could cause older messages to wait while newer messages continue to be processed.

For a message broker, using a Queue helps maintain the order in which messages were received.

-------------------------

Phase 2 Journal
Retry Logic and Fairness

When a message fails, I increment its retry count and put it back at the rear of the queue. This makes the system fairer because a failed message does not immediately get processed over and over while other messages are waiting.

For example, if messages A, B, and C are in the queue and B fails, B is moved to the back. The queue will then continue processing C before trying B again.

This gives the other messages a chance to be processed instead of allowing one failing message to block the rest of the queue.

I also used the queue size at the beginning of processBatch() so that the method only processes the original batch of messages. This prevents a message that keeps failing from causing an infinite loop.

--------------------------

Phase 3 Journal
Dead-Letter Queue

A poison message starts in the normal message queue. When the Broker processes it, the message has a chance to succeed. If it fails, its retry count is increased and it is placed back at the end of the main queue.

If the message continues to fail and its retry count reaches the maximum of 3 retries, it is removed from the main queue and placed into the Dead-Letter Queue (DLQ).

The message's retry count keeps increasing each time it fails. Its position also changes because failed messages are placed at the rear of the main queue. This allows other messages to be processed before the failed message is tried again.

Once the message reaches the maximum retry limit, it is moved permanently to the DLQ instead of being re-enqueued. This prevents a poison message from blocking the system forever.

The DLQ can be viewed through the menu. Viewing the DLQ also removes the messages from it because the messages are dequeued while they are displayed.
