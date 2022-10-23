Build tools like Maven use a pattern to decide if a class is a test classes or not. The following is the list of classes Maven considers automatically during its build:

"**/Test*.java"           
includes all of its subdirectories and all Java filenames that start with Test.
**/*Test.java           
includes all of its subdirectories and all Java filenames that end with Test.
**/*Tests.java          
includes all of its subdirectories and all Java filenames that end with Tests.
**/*TestCase.java
includes all of its subdirectories and all Java filenames that end with TestCase.

location for tests
src/test/java - for test classes