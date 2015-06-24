FROM edermag/tomcat-8-dev

WORKDIR /source

ENV app facelets-bootstrap-demo

ADD pom.xml /source/pom.xml
ADD src /source/src

RUN mvn clean package && \
    mv /source/target/$app-0.0.1-SNAPSHOT.war $CATALINA_HOME/webapps/          

CMD ["catalina.sh", "run"]


