FROM jboss/wildfly

ENV POSTGRES_DRIVER_VERSION=42.2.12
ENV DB_HOST ms_pg
ENV DB_PORT 5432
ENV DB_USER postgres
ENV DB_PASS postgres
ENV DB_NAME books
ENV DATASOURCE_NAME booksDS
ENV DATASOURCE_JNDI java:/booksDS

COPY --chown=1001:0  install-app-pg.sh  /tmp/

user root
RUN echo 'hosts: files mdns4_minimal [NOTFOUND=return] dns mdns4' >> /etc/nsswitch.conf
RUN chmod +x /tmp/install-app-pg.sh

user jboss
RUN /tmp/install-app-pg.sh

RUN /opt/jboss/wildfly/bin/add-user.sh admin Admin#007 --silent
RUN chown -R jboss:jboss /opt/jboss/wildfly/


COPY --chown=1001:0  target/estudo-jobs.war  /opt/jboss/wildfly/standalone/deployments/

##CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]

CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-c", "standalone-full.xml", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]

