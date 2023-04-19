# redis-lettuce-demo
Redis Lettuce Client Demo Project

Steps to run:
1) First pull the redis-stack image in the container and ensure its up and running
   docker run -d --name redis-stack -p 6379:6379 -p 8001:8001 redis/redis-stack:latest
2) Then start the spring boot application, which connects to the redis server
3) Connect to RedisInsight via localhost:8001

Checkstyle configuration:
1) Currently, checkstyle/checkstyle.xml has only few rules
2) We can use the Google recommended Best coding pratices by adding the below rules to cater our project needs
   https://github.com/checkstyle/checkstyle/blob/master/src/main/resources/google_checks.xml