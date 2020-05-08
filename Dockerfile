FROM tomcat:9.0-jdk8-openjdk
WORKDIR $CATALINA_HOME
ENV RDS_url=jdbc:postgresql://rayan-database.cfku8f0dz80m.us-east-2.rds.amazonaws.com:5432/project1
ENV RDS_username=vakilr
ENV RDS_password=easyPass123
ENV RDS_defaultSchema=forum
ARG WAR_FILE=./target/*.war
COPY $WAR_FILE ./webapps
EXPOSE 8080
CMD ["catalina.sh", "run"]