# Stage 1: Build stage
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app

# 1. copy parent pom
COPY pom.xml ./pom.xml
COPY api-gateway/pom.xml api-gateway/pom.xml
COPY discovery-server/pom.xml discovery-server/pom.xml
# 2. copy pom của service
COPY order-service/pom.xml order-service/pom.xml
COPY stock-service/pom.xml stock-service/pom.xml
COPY users-service/pom.xml users-service/pom.xml


# 3. download dependency (cache layer)
RUN mvn -B -q -e -DskipTests dependency:go-offline

# 4. copy source của service
COPY order-service/src order-service/src

# 5. build service
RUN mvn -pl order-service clean package -DskipTests

# Stage 2: Runtime stage
FROM eclipse-temurin:25-jdk

# Install curl cho health check (Debian sử dụng apt-get)
RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*

# Tạo non-root user để tăng tính bảo mật
RUN useradd -r -u 1001 appuser
WORKDIR /app

# Copy file jar từ stage build
# Lưu ý: Tên file JAR khớp với artifactId trong pom.xml
COPY --from=build /app/target/order-service-*.jar app.jar

# Chuyển sang non-root user
RUN chown appuser:appuser /app/app.jar
USER appuser

# Cấu hình môi trường
ENV SPRING_PROFILES_ACTIVE=prod
ENV JAVA_OPTS="-XX:MaxRAMPercentage=75.0"

# Health check
HEALTHCHECK --interval=30s --timeout=10s --start-period=60s --retries=3 \
  CMD curl -f http://localhost:8080/actuator/health || exit 1

# Expose port (mặc định Spring Boot là 8080)
EXPOSE 8080

#ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -Dspring.profiles.active=$SPRING_PROFILES_ACTIVE -jar app.jar"]