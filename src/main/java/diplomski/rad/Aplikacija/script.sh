#!/bin/bash

jflex scanner.flex
java -classpath .:mongo-java-driver-3.11.0.jar -jar java-cup-11b.jar parser.cup
javac -classpath .:java-cup-11b.jar Yylex.java
javac -classpath .:java-cup-11b.jar sym.java
javac -classpath .:java-cup-11b.jar:mongo-java-driver-3.11.0.jar parser.java
#java -classpath .:java-cup-11b-runtime.jar:mongo-java-driver-3.11.0.jar parser test.txt
