FROM amazoncorretto:21-alpine
EXPOSE 8080

WORKDIR /app

#ADD target/money-bucket-*.jar money-bucket.jar

CMD [ "java", "-jar", "money-bucket.jar" ]