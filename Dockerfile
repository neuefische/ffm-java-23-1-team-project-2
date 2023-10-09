FROM openjdk:17

EXPOSE 8081

ADD backend/target/recipe.jar app.jar

CMD [ "sh", "-c", "java -jar /app.jar" ]