FROM jboss/wildfly

COPY --chown=jboss:jboss config/wildfly /opt/jboss/config
ADD --chown=jboss:jboss https://repo1.maven.org/maven2/org/mariadb/jdbc/mariadb-java-client/2.7.1/mariadb-java-client-2.7.1.jar /var/mariadb-connector-java.jar
RUN chmod +x /opt/jboss/config/*.sh
RUN /opt/jboss/config/jboss-init.sh "/opt/jboss/wildfly" "/opt/jboss/config/config.batch" "/opt/jboss/config/jboss.properties"
RUN rm -rf /opt/jboss/wildfly/standalone/configuration/standalone_xml_history

ADD build/libs/TrainHarder-0.0.2.war /opt/jboss/wildfly/standalone/deployments/ROOT.war
