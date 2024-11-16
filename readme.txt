
Open the two services using these URLs in the browser:

http://localhost:8980/customers/7007
expect to get

{
  "name": "James Bond",
  "id": 7007,
  "email": "jamesB@mi6.gov.uk"
}

and

http://localhost:8981/customers/114

expect to get :
{
  "name": "James Bond",
  "id": 114,
  "email": "jamesB@mi6.gov.uk"
}
