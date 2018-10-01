# RabbitMQ Demo
This app demonstrates using RabbitMQ with Java and Spring AMQP.

### Prerequisites
[Docker](https://www.docker.com/get-started) is required to run this demo.

### Running the demo
To start the demo, type `docker-compose up` in a terminal.

Docker will download and build all other dependencies, followed by starting up three services: RabitMQ, Producer, and Consumer.

Go to the address http://localhost:15672, using `guest` as both username and password, and click on the queues tab. The Consumer app should automatically create queues the Producer app will place data into.

Now open http://localhost:8080/run to start up the producer.

