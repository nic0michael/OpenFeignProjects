#!/bin/bash

# Define variables
SERVICE_NAME="CustomerService"
IMAGE_NAME="customer-service:latest"
COMPOSE_FILE="docker-compose-custservice.yml"

# Build Docker image
echo "Building Docker image for $SERVICE_NAME..."
docker build -t $IMAGE_NAME "./$SERVICE_NAME" || {
  echo "Failed to build $SERVICE_NAME image"
  exit 1
}

# Start service
echo "Starting $SERVICE_NAME using $COMPOSE_FILE..."
docker-compose up -d || {
  echo "Failed to start $SERVICE_NAME"
  exit 1
}

echo "$SERVICE_NAME is running successfully!"
