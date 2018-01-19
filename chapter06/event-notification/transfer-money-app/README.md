## Hack docker
docker-machine start default
eval $(docker-machine env default)
docker-machine ip default

## Start RabbitMQ
docker-compose up

## Hit the endppoint
curl -d'{"customer_id":"1a2b", "origin_account_number":"A1", "destination_account_number":"Z1", "amount":888, "external_bank":"true"}' -H "Content-Type: application/json" http://localhost:8080/transfer