Phase 1 Journal

Queue vs. Stack

A Queue is appropriate for a message broker because messages should normally be processed in the order they are received. A Queue uses FIFO (First In, First Out), so the first message added is the first message processed.

A Stack uses LIFO (Last In, First Out). If I used a Stack instead of a Queue, the newest message would be processed first. This could cause older messages to wait while newer messages continue to be processed.

For a message broker, using a Queue helps maintain the order in which messages were received.
