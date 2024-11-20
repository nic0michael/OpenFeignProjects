# 1. Prerequisites
## 1.1 Install Docker
The instructions can be found here: \
[install-docker-on-ubuntu](http://rino.kozow.com/devops/posts/install-docker-on-ubuntu/)
## 1.2 Install Java
```
sudo apt update
sudo apt upgrade -y
sudo apt install openjdk-17-jdk -y
```
## 1.3 Create /dockerize folder
```
sudo su -

mkdir /dockerize -p

chown -R nickm: /dockerize/
```
## 1.4 Create Jar files
You need to clone this from github and build the two projects \
1. git clone https://github.com/nic0michael/OpenFeignProjects.git \
2. First you Maven build : CustomerStubService
3. Then you run CustomerStubService
4. then you can maven build CustomerService
5 The JAR files will be in the target folders
## 1.5 Copy Jar files :
```
# Copy JAR file from:
C:\Users\NicoMichael\Documents\projects\OpenFeignProjects\CustomerService\target
# to:
\\wsl.localhost\Ubuntu-24.04\dockerize\customer-template-service
# also to:
\\wsl.localhost\Ubuntu-24.04\dockerize\customer-template-and-stub-service

# AND Copy JAR file from:
C:\Users\NicoMichael\Documents\projects\OpenFeignProjects\CustomerStubService\target
# to:
\\wsl.localhost\Ubuntu-24.04\dockerize\customer-stub-template-service
# also to:
\\wsl.localhost\Ubuntu-24.04\dockerize\customer-template-and-stub-service
```
## 1.6 Get Ubuntu Server Ip address
Run these commands:
```
sudo su -

ip a
```

Expect to get:
```
2: eth0: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1500 qdisc mq state UP group default qlen 1000
    link/ether 00:15:5d:6d:c4:00 brd ff:ff:ff:ff:ff:ff
    inet 172.31.69.165/20 brd 172.31.79.255 scope global eth0
       valid_lft forever preferred_lft forever
    inet6 fe80::215:5dff:fe6d:c400/64 scope link
       valid_lft forever preferred_lft forever
```

From this we update the code and use Stub Server IP address as : 172.31.69.165

# 2. Building Dockerfiles and running Docker Containers
## 2.1 For customer-template-service

run these commands
```
# If you are not logged in as root become root
sudo su -

cd /dockerize/customer-template-service

nano Dockerfile
```
Put this in the file :
```
FROM openjdk:17-alpine

ADD CustomerService-0.0.3-SNAPSHOT.jar customer-template-service.jar

ENTRYPOINT [ "java","-jar","customer-template-service.jar" ]

```
### 2.1.1 Building the Docker Image
Run this command :
```
docker build -t templateservice:latest .
```
### 2.1.2 Create Docker Compose file
Run this command :
```
nano compose.yaml
```
Put this in the file:
```
version: '3'
services:
  tpltservice:
    build:
      context: .
      dockerfile: Dockerfile
    image: templateservice:latest
    ports:
      - "8980:8980"



```
### 2.1.3 Start the Docker Container
Run this command:
```
docker compose up -d
```
## 2.2 For customer-template-stub-service

```
cd /dockerize/customer-template-service

nano Dockerfile
```
Put this in the file :
```
FROM openjdk:17-alpine

ADD CustomerStubService-0.0.3-SNAPSHOT.jar customer-stub-template-service.jar

ENTRYPOINT [ "java","-jar","customer-stub-template-service.jar" ]

```
### 2.2.1 Building the Docker Image
Run this command :
```
docker build -t stubservice:latest .
```
### 2.2.2 Create Docker Compose file
Run this command :
```
nano compose.yaml
```
Put this in the file:
```
version: '3'
services:
  stubservice:
    build:
      context: .
      dockerfile: Dockerfile
    image: stubservice:latest
    ports:
      - "8981:8981"

```
### 2.2.3 Start the Docker Container
Run this command:
```
docker compose up -d
```
## 2.3 Building One Docker Container for our two Docker Images
First stop both Docker containers with this command from their folders
```
cd /dockerize/customer-template-service

docker compose down

cd /dockerize/customer-template-stub-service

docker compose down
```
### 2.3.1 Creating a single compose file for both Docker Images
Run these commands
```
cd cd /dockerize/customer-template-service

nano compose.yaml
```

Put this into the file
```
version: '3.8'

services:
  stubservice:
    build:
      context: .
      dockerfile: Dockerfile
    image: stubservice:latest
    ports:
      - "8981:8981"
    healthcheck:
      test: ["CMD-SHELL", "curl -f http://172.31.69.165:8981/actuator/health || exit 1"]
      interval: 10s
      timeout: 10s
      retries: 5
      start_period: 30s
    restart: unless-stopped

  tpltservice:
    build:
      context: .
      dockerfile: Dockerfile
    image: templateservice:latest
    ports:
      - "8980:8980"
    restart: unless-stopped

```
Start the Docker Container (Its one container)
```
docker compose up -d
```
### 2.3.2 Testing this : 
Testing Stub Service : Open Browser in this URL : \
http://172.31.69.165:8981/customers/6007

Expect to get:
```
{"name":"James Bond","id":6007,"email":"jamesB@mi6.gov.uk"}
```


Testing whole System : Open Browser in this URL : \
http://localhost:8980/customers/2356

Expect to get:
```
{"name":"James Bond","id":2356,"email":"jamesB@mi6.gov.uk"}
```



