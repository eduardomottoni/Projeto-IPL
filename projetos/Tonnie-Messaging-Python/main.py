from pika import BlockingConnection, ConnectionParameters, PlainCredentials

queue_name = "com.tonnie.message.type.queue"
topic_name = "com.tonnie.message.type.topic.python"
topic_routing_key = "com.tonnie.message.type.topic.#"
topic_exchanger = "amq.topic"

credentials = PlainCredentials(username="guest", password="guest")
connection = BlockingConnection(ConnectionParameters(host="localhost", port=5672, credentials=credentials))
channel = connection.channel()


def queue_callback_function(ch, method, properties, payload):
    message = payload.decode("utf-8")
    print('New Queue message received: ', message)
    if "send_py" in message:
        channel.basic_publish(exchange=topic_exchanger, routing_key=topic_routing_key, body=message+"_REPLAY_BY_PYTHON")


def topic_callback_function(ch, method, properties, payload):
    message = payload.decode("utf-8")
    print('New Topic message received: ', message)


channel.queue_declare(queue=queue_name)
channel.basic_consume(queue=queue_name, on_message_callback=queue_callback_function, auto_ack=True)

channel.queue_declare(queue=topic_name)
channel.queue_bind(exchange=topic_exchanger, queue=topic_name, routing_key=topic_routing_key)
channel.basic_consume(queue=topic_name, on_message_callback=topic_callback_function, auto_ack=True)


if __name__ == '__main__':
    channel.start_consuming()
