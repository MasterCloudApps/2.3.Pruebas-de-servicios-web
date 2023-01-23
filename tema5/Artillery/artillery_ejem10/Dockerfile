FROM maven:3.8.7-eclipse-temurin-17 as builder
COPY pom.xml /code/pom.xml
WORKDIR /code
RUN mvn dependency:go-offline
COPY . /code/
RUN mvn package

FROM eclipse-temurin:17-jdk
COPY --from=builder /code/target/*.jar /usr/app/springboot-chat.jar
WORKDIR /usr/app
EXPOSE 5000
CMD [ "java", "-jar", "springboot-chat.jar" ] 

# BUILD  docker build -t maes95/webchat .
# RUN_APP docker run -p 5000:5000 maes95/webchat
# DEPLOY:
# - heroku login
# - heroku container:login
# - heroku container:push web
# - heroku container:release web
