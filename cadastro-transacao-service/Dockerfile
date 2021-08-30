FROM openjdk:11
RUN adduser --system --group spring
USER spring:spring
ENV JAR_FILE=./target/cadastro_transacao.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]