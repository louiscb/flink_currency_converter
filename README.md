# Flink Currency Converter  

Extremely simple Flink application that converts currencies for events on an incoming Kinesis source stream and writes them to a sink Kinesis stream. Based on the AWS Kinesis application examples.

## How to run

1. First you need to compile the Jar of this project to upload to AWS. You can do this with: `mvn package -Dflink.version=1.15.3`. 
2. The python script `producer.py` will send events to a Kinesis stream `ExampleInputStream`, run it with `python producer.py`.
2. You can then run the consumer locally which listens to events coming from Flink with the changed currency: `pything consumer.py`.