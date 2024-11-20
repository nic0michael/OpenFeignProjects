#!/bin/bash

# Define variables
SERVICE_NAME="CustomerService"

# Start service
echo "Starting $SERVICE_NAME using Compose File"
docker compose up -d || {
  echo "Failed to start $SERVICE_NAME"
  exit 1
}

echo "$SERVICE_NAME is running successfully!"
