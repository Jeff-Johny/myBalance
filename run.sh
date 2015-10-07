sh /home/user/Softwares/apache-tomcat-8.0.26/bin/catalina.sh stop
mvn clean compile install
rm -vfr /home/user/Softwares/apache-tomcat-8.0.26/webapps/CounterWebApp
cp -v /home/user/my-stuff/workspace-mvn1/CounterWebApp/target/CounterWebApp.war /home/user/Softwares/apache-tomcat-8.0.26/webapps/
sh /home/user/Softwares/apache-tomcat-8.0.26/bin/catalina.sh jpda start