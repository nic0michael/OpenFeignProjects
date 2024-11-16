docker build -t customerservice:latest .

docker compose up
====== GOOGLE SEARCH =============================
docker one microservice calls another microservice
https://stackoverflow.com/questions/69136949/how-to-allow-microservices-to-communicate-with-each-other

https://shdhumale.wordpress.com/2021/06/17/calling-one-microservice-from-another-microservice-using-docker-composefiles-for-docker-container/
- You can download the code from the given github location.
consumer :-
https://github.com/shdhumale/springboot-docker-consumer.git
Producer:-
https://github.com/shdhumale/springboot-docker-producer.git
================================================
When you accessing localhost inside the docker container -
it's localhost of the container, not the localhost
of your host machine. If it's docker composer you can
use service name as reference instead of localhost,
e.g. if you want from service microservice_be-admin_1
reach microservice_be-auth_1 you need to call
microservice_be-auth_1:3000

=========================

