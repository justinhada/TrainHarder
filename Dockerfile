FROM jboss/wildfly

COPY --chown=jboss:jboss config/wildfly /opt/jboss/config/
RUN mkdir /opt/jboss/dependencies
ADD --chown=jboss:jboss https://repo1.maven.org/maven2/org/mariadb/jdbc/mariadb-java-client/2.7.3/mariadb-java-client-2.7.3.jar /opt/jboss/dependencies/mariadb-connector-java.jar
RUN chmod +x /opt/jboss/config/*.sh
RUN sed -i 's/<resolve-parameter-values>false<\/resolve-parameter-values>/<resolve-parameter-values>true<\/resolve-parameter-values>/g' /opt/jboss/wildfly/bin/jboss-cli.xml
RUN /opt/jboss/config/jboss-init.sh "/opt/jboss/wildfly" "/opt/jboss/config/config.batch"
RUN rm -rf /opt/jboss/wildfly/standalone/configuration/standalone_xml_history
RUN chmod 0777 /opt/jboss -R

ADD build/libs/TrainHarder.war /opt/jboss/wildfly/standalone/deployments/TrainHarder.war

RUN /opt/jboss/wildfly/bin/add-user.sh -u admin -p admin

ENV LAUNCH_JBOSS_IN_BACKGROUND true

CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]
