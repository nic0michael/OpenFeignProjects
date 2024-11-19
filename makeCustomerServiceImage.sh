#!/bin/bash




# Define directories and image names
BASE_DIR=$(pwd)
CUSTOMER_SERVICE_DIR="$BASE_DIR/CustomerService"
CUSTOMER_STUB_SERVICE_DIR="$BASE_DIR/CustomerStubService"

CUSTOMER_SERVICE_IMAGE="customer-service:latest"
CUSTOMER_STUB_SERVICE_IMAGE="customer-stub-service:latest"


SERVICE_NAME="CustomerService"
IMAGE_NAME="customer-service:latest"
COMPOSE_FILE="docker-compose-custservice.yml"


# Build Docker images
echo "Building Docker image for CustomerService..."
docker build -t $CUSTOMER_SERVICE_IMAGE $CUSTOMER_SERVICE_DIR || {
  echo "Failed to build CustomerService image"
  exit 1
}


echo "Docker image : CustomerService built successfully!"


# Start service
echo "Starting $SERVICE_NAME using $COMPOSE_FILE..."

#docker compose -f $COMPOSE_FILE up -d || {
docker-compose -f docker-compose-custservice.yml up -d ||{
  echo "Failed to start $SERVICE_NAME"
  exit 1
}

echo "$SERVICE_NAME is running successfully!"
