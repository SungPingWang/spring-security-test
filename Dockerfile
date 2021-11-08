FROM openjdk:8-jdk
LABEL maintainer=sungpingWang

#ENV
#RUN
COPY target/*jar /app.jar
EXPOSE 8030

ENTRYPOINT ["/bin/sh","-c","java -Dfile.encoding=utf8 -jar app.jar"]