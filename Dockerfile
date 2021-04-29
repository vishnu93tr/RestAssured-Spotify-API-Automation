FROM maven:3.8.1-jdk-8-slim
MAINTAINER vishnuvardhan vishnu26121993@gmail.com
WORKDIR /usr/share/Spotify-API-Automation
ADD src/test/java/com/spotify/oauth2 src/test/java/com/spotify/oauth2
ADD src/test/resources src/test/resources
ADD pom.xml pom.xml
ADD playlist.xml playlist.xml
ADD healthcheck.sh healthcheck.sh
ENTRYPOINT sh healthcheck.sh