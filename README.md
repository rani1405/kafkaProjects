# kafkaProjects

#How can we create kafka topic partition replica?


1)Start Zookeeper Server sh bin/zookeeper-server-start.sh config/zookeeper.properties

2)Start Kafka Server / Broker sh bin/kafka-server-start.sh config/server.properties

3)Create topic sh bin/kafka-topics.sh --bootstrap-server localhost:9092 --create --topic topic1 --partitions 3 --replication-factor 1

4)Create 3 kafka replica with 3 broker

sh bin/kafka-topics.sh --bootstrap-server localhost:9092,localhost:9093,localhost:9094 --create --topic topicReplica --partitions 3 --replication-factor 3

5)Check which broker has partition leader and which one partition follower

sh bin/kafka-topics.sh --describe --bootstrap-server localhost:9092,localhost:9093,localhost:9094  --topic topicReplica

Output- 

Topic: topicReplica	Partition: 0	Leader: 0	Replicas: 0,2,1	Isr: 0,2,1
Topic: topicReplica	Partition: 1	Leader: 1	Replicas: 1,0,2	Isr: 1,0,2
Topic: topicReplica	Partition: 2	Leader: 2	Replicas: 2,1,0	Isr: 2,1,0


Partition: Indicates the partition number.
Leader: The broker ID that is the leader for this partition. The leader is responsible for all reads and writes for the partition.
Replicas: A list of broker IDs that host replicas of the partition (including both the leader and followers).
Isr (In-Sync Replicas): A subset of Replicas that are currently in sync with the leader.


6)Start the schema registry

sh bin/schema-registry-start etc/schema-registry/schema-registry.properties


*************************Consumer application(C2)******************
This is consumer application with schema version v2(id, name, empEmail).