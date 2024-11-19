#!/bin/bash

# Stop CustomerService
echo "Stopping CustomerService..."
docker-compose -f docker-compose-custservice.yml down || {
  echo "Failed to stop CustomerService"
}

echo "All services stopped CustomerService successfully!"
