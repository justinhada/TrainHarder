FROM jboss/wildfly

COPY --chown=jboss:jboss config/wildfly /opt/jboss/config
COPY --chown=jboss:jboss config/mariadb/mariadb-java-client.jar /var/mariadb-connector-java.jar
RUN chmod +x /opt/jboss/config/*.sh
RUN /opt/jboss/config/jboss-init.sh "/opt/jboss/wildfly" "/opt/jboss/config/config.batch" "/opt/jboss/config/jboss.properties"
RUN rm -rf /opt/jboss/wildfly/standalone/configuration/standalone_xml_history

ADD build/libs/TrainHarder-0.0.2.war /opt/jboss/wildfly/standalone/deployments/ROOT.war
