FROM eclipse-temurin:11

ARG JAR_FILE=target/Order-*.jar

WORKDIR /order/app
#you could give this a default value as well
ENV profile=prod

COPY $JAR_FILE order-0.0.1.jar
ENTRYPOINT java -Dspring.profiles.active=$profile -jar order-0.0.1.jar
#ENTRYPOINT ["java", "-Dspring.profiles.active=$SPRING_PROFILE", "-jar", "order-0.0.1.jar"]