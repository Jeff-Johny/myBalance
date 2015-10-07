sh /home/user/Softwares/apache-tomcat-8.0.26/bin/catalina.sh stop
mvn clean compile install
rm -vfr /home/user/Softwares/apache-tomcat-8.0.26/webapps/myBalance
cp -v /home/user/my-stuff/myBalance/target/myBalance.war /home/user/Softwares/apache-tomcat-8.0.26/webapps/
sh /home/user/Softwares/apache-tomcat-8.0.26/bin/catalina.sh jpda start