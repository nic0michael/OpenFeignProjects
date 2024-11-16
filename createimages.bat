@echo off

:: Define directories and image names
set BASE_DIR=%cd%
set CUSTOMER_SERVICE_DIR=%BASE_DIR%\CustomerService
set CUSTOMER_STUB_SERVICE_DIR=%BASE_DIR%\CustomerStubService

set CUSTOMER_SERVICE_IMAGE=customer-service:latest
set CUSTOMER_STUB_SERVICE_IMAGE=customer-stub-service:latest

:: Build Docker images
echo Building Docker image for CustomerService...
docker build -t %CUSTOMER_SERVICE_IMAGE% %CUSTOMER_SERVICE_DIR%
if errorlevel 1 (
    echo Failed to build CustomerService image
    exit /b 1
)

echo Building Docker image for CustomerStubService...
docker build -t %CUSTOMER_STUB_SERVICE_IMAGE% %CUSTOMER_STUB_SERVICE_DIR%
if errorlevel 1 (
    echo Failed to build CustomerStubService image
    exit /b 1
)

echo Docker images built successfully!
pause
