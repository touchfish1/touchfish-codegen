FROM java:alpine
VOLUME /tmp
ADD touchfish-codegen.jar app.jar
# ENTRYPOINT ["java","-Xms256m","-Xmx256m","-jar","/app.jar"]
ENTRYPOINT ["java","-jar","/app.jar"]