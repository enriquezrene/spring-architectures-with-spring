#Running the application

In order to facilitate the messaging server a docker-compose file is used.
If you're a mac user you'll have to firstly turn-on the VM and then get the IP address that should
be used replaced in the application.properties file (spring.rabbitmq.host)

## start docker VM
docker-machine start default
eval $(docker-machine env default)

## get the IP
docker-machine ip default

## Start RabbitMQ
docker-compose up

## Hit the endpoint which will do the transfer money process and later emmit an event:
curl -d'{"customer_id":"1a2b", "origin_account_number":"A1", "destination_account_number":"Z1", "amount":888, "external_bank":"true"}' -H "Content-Type: application/json" http://localhost:8080/transfer