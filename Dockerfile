FROM java:8-alpine
MAINTAINER Your Name <you@example.com>

ADD target/uberjar/my-app.jar /my-app/app.jar

EXPOSE 3000

CMD ["java", "-jar", "/my-app/app.jar"]
