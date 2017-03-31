#
# makefile 
#
# input file for 'make' build tool ( /usr/bin/make )
# to build solution
#
# @author Dr. Fenwick
# @author Matt Stone
# @version Spring 2017
#

JUNIT_JAR = /usr/share/java/junit-4.10.jar
HAMCREST_JAR = /usr/share/java/hamcrest/core-1.1.jar

default: 
	@echo "usage: make target"
	@echo "available targets: compile, test, clean"

compile:# ./scatt/student.java ./src/scatt/test/*.java ./src/scatt/test/app/unzipper/*.java\
./src/scatt/test/gui/*.java ./src/scatt/*.java ./src/scatt/gui/*.java
	javac -cp .:$(JUNIT_JAR) ./scatt/test/*.java
	javac -cp .:$(JUNIT_JAR) ./scatt/test/app/*.java
	javac -cp .:$(JUNIT_JAR) ./scatt/test/app/unzipper/*.java
	javac -cp .:$(JUNIT_JAR) ./scatt/test/gui/*.java	
	javac ./scatt/*.java
	javac ./scatt/gui/*.java

	

clean:
	rm -f ./scatt/*.class
	rm -f ./scatt/gui/*.class
	rm -f ./scatt/test/*.class
	rm -f ./scatt/test/app/*.class
	rm -f ./scatt/test/gui/*.class
	rm -f ./scatt/test/app/unzipper/*.class
	

# java packages are part of class name; when specifying the directory (going through package folders) use dots like below
# ie scatt/test/app will not work; it must be scatt.test.app
# see this link: http://stackoverflow.com/questions/15548580/junit-cannot-find-class
test: # .scatt/test/*.class ./scatt/test/app/*.class ./scatt/test/app/*.class 
	java -cp .:$(JUNIT_JAR):$(HAMCREST_JAR) org.junit.runner.JUnitCore scatt.test.AllTests
	
testio:
	java -cp .:$(JUNIT_JAR):$(HAMCREST_JAR) org.junit.runner.JUnitCore scatt.test.app.unzipper.TestUnZipper

	
	
