# JAVA_HOME=/usr/lib/jvm/java-17-openjdk-17.0.3.0.7-1.fc35.x86_64
ui-test:
	mvn clean test -Dtest=CucumberRunner

rest-test:
	mvn clean test -Dtest=org.example.slot.rest.*Test
