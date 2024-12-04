# KAFKA:
- With kafka, we publish messages/events to topics, and they get persisted.
    Messages are removed when consumers receive them. 
    This supports to replay messages and more importantly, it allows a multiple consumers to process logic on the same messages/events.
- We can add different types of consumers that execute different logic based on the same event/message. Becauses Kafka works on publish/subscribe architecture.
- Kafka helps such scenarios as the messages are retained for longer time and there is facility of consumer groups.

# AWS SQS:
    - Max message size 256KB.
    - Pull based
    - Message order preserved only for FIFO queues.
    - Data Retention 30 seconds to 14 Days.
    - same message will not be deliver to multiple consumers.

# Kafka:
    - Max message size 1MB and configurable.
    - Publish subscribe based.
    - Message Order always preserved
    - Data retention is configurable based on need. Default 7 Days.
    - same message will be deliver to multiple consumers or consumers group.