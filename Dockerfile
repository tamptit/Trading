FROM eclipse-temurin:11

ARG JAR_FILE=target/Order-*.jar

WORKDIR /order/app
#you could give this a default value as well
ARG var_evn="prod"
ENV var_evn=$var_evn
COPY $JAR_FILE order-0.0.1.jar

#ENTRYPOINT ["java -Dspring.profiles.active=$var_evn", "-jar", "order-0.0.1.jar"]