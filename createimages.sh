#!/bin/bash

# Define directories and image names
BASE_DIR=$(pwd)
CUSTOMER_SERVICE_DIR="$BASE_DIR/CustomerService"
CUSTOMER_STUB_SERVICE_DIR="$BASE_DIR/CustomerStubService"

CUSTOMER_SERVICE_IMAGE="customer-service:latest"
CUSTOMER_STUB_SERVICE_IMAGE="customer-stub-service:latest"

# Build Docker images
echo "Building Docker image for CustomerService..."
docker build -t $CUSTOMER_SERVICE_IMAGE $CUSTOMER_SERVICE_DIR || {
  echo "Failed to build CustomerService image"
  exit 1
}

echo "Building Docker image for CustomerStubService..."
docker build -t $CUSTOMER_STUB_SERVICE_IMAGE $CUSTOMER_STUB_SERVICE_DIR || {
  echo "Failed to build CustomerStubService image"
  exit 1
}

echo "Docker images built successfully!"
