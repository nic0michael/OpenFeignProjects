#!/bin/bash

# Stop CustomerStubService
echo "Stopping CustomerService..."
docker-compose down || {
  echo "Failed to stop CustomerService"
}

echo "All services stopped CustomerService successfully!"
