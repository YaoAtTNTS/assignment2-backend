# Project Setup Guide for Windows

## Prerequisites

1. Java 1.8

Refer to the [Java 1.8 installation guide](https://www.java.com/en/download/help/windows_manual_download.html) for Windows.

2. Mysql 8

Refer to the [Mysql installation guide](https://www3.ntu.edu.sg/home/ehchua/programming/sql/MySQL_HowTo.html) for Windows.


## Run the Application Locally
1. Open cmd, navigate to the directory where you want to download the source code.

2. Run 'git clone https://github.com/YaoAtTNTS/assignment2-backend.git' to download the source code.

3. Run 'cd ./assignment2-backend' to navigate to the project root dir.

4. Open the file ./assignment2-backend/src/main/resources/application.yml, change the username at line 12 and the password at line 13 into your own mysql client username and password.

5. Run './gradlew clean bootJar'

6. Run 'cd ./build/libs' to navigate to the project jar package dir.

7. Run 'java -jar assignment2-0.0.1-SNAPSHOT' to start the application.

8. Keep the cmd program on.

## Create DB schema and table
There are 2 ways.
### 1. Use MySQL Workbench.
1.1 Connect to your mysql server.

1.2 Run 'CREATE SCHEMA `assignment` DEFAULT CHARACTER SET utf8;' to create the schema.

1.3 Open the sql script file in the /db dir under the project root dir. Run the sql script to create the invoice table.

### 2. Use cmd.
2.1 Run 'mysql -uroot -p'

2.2 Enter your password. You will enter into mysql command line.

2.3 Run sql script 'CREATE SCHEMA `assignment` DEFAULT CHARACTER SET utf8' to create the schema;

2.4 Run sql script 'source /your/project/dir/db/assignment_invoice.sql' to create the invoice table.