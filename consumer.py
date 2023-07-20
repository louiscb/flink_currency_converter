import boto3

def print_record(Records):
  for record in Records:
        data_bytes = record.get('Data', b'')
        data_str = data_bytes.decode('utf-8')
        print(data_str)


def main():
    stream_name = 'ExampleOutputStream'
    print("Getting records from stream: {}".format(stream_name))
    
    try:
        kinesis_client = boto3.client('kinesis')

        response = kinesis_client.describe_stream(StreamName=stream_name)
        shard_id = response['StreamDescription']['Shards'][0]['ShardId']

        response = kinesis_client.get_shard_iterator(
            StreamName=stream_name,
            ShardId=shard_id,
            ShardIteratorType='TRIM_HORIZON'
        )
        shard_iterator = response['ShardIterator']

        record_count = 0

        try:
            while True:
                response = kinesis_client.get_records(
                    ShardIterator=shard_iterator,
                    Limit=10
                )
                shard_iterator = response['NextShardIterator']
                records = response['Records']
                record_count += len(records)
                print_record(records)
        except KeyboardInterrupt:
            print("\n Exiting consumer. Consumed {} records.".format(record_count))

    except ClientError as e:
        print("Couldn't get records from stream %s.", stream_name)
        raise


if __name__ == "__main__":
    main()