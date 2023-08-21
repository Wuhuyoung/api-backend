FROM java:8-alpine
COPY ./api-backend-0.0.1-SNAPSHOT.jar /tmp/app.jar
EXPOSE 7529
ENTRYPOINT java -jar /tmp/app.jar