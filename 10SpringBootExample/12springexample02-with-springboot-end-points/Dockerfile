FROM java:8
VOLUME /tmp
ADD gs-spring-boot-docker-0.1.0.jar example01-1.0-SNAPSHOT.jar
RUN bash -c 'touch /example01-1.0-SNAPSHOT.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/example01-1.0-SNAPSHOT.jar"]