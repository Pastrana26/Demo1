FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY . .

RUN chmod +x mvnw
RUN ./mvnw package -DskipTests

EXPOSE 8080

ENTRYPOINT ["java","-cp","target/demo-0.0.1-SNAPSHOT/WEB-INF/classes:target/demo-0.0.1-SNAPSHOT/WEB-INF/lib/*","com.example.demo.DemoApplication"]