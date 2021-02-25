# build image with tag aos/~
docker build -t aos/orders-microservice:latest .
docker build -t aos/stock-microservice:latest .

# run with name in background mapping to port X from port Y
docker run --name orders-microservice -d -p 8110:8210 aos/orders-microservice:latest
docker run --name stock-microservice -d -p 8111:8211 aos/stock-microservice:latest

# access image via ssh
docker run -ti --entrypoint /bin/sh aos/orders-microservice:latest

# access container via ssh
docker exec -ti orders-microservice /bin/sh
