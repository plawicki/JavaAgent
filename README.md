Spring-boot with agent
====

to run:

1. `mvn package` in agentOrange
2. copy jar with dependecies from target to myrest/target folder
3. `mvn package && java -javaagent:target/agent-1.0-SNAPSHOT-jar-with-dependencies.jar -jar target/myrest-1.0-SNAPSHOT.jar`