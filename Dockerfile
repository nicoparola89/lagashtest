FROM java:8
VOLUME /tmp
ADD target/test-0.0.1-SNAPSHOT.jar test-0.0.1-SNAPSHOT.jar
RUN bash -c 'touch /test-0.0.1-SNAPSHOT.jar'
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/test-0.0.1-SNAPSHOT.jar"]
