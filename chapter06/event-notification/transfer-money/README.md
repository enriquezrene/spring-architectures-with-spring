# RabbitMQ web interface

http://localhost:15672
guest/guest

curl -X POST -H "Content-Type: application/json" -d '{"customerId":"1a2b", "amount":999, "externalBank":"true", "accountDestinationNumber":"3c4d"}'  http://localhost:8080/transfer
