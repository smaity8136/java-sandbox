To test the ETag validation do the following:

- Create a new user entry:

`curl -X POST \
  http://localhost:8080/users \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: e4199f11-21c4-449e-98fd-689fdf694f56' \
  -H 'cache-control: no-cache' \
  -d '{
    "uid": 1111,
    "firstName": "Bert",
    "lastName": "Burton",
    "birthDate": "1965-02-06T12:11:04.785",
    "active": false
}'`

- Perform the initial GET:

`curl -X GET \
  'http://localhost:8080/users/1111/cache?=' \
  -H 'Postman-Token: 646f66ff-ed90-4b33-9b9e-e4d2a899a713' \
  -H 'cache-control: no-cache'`

- Perform the subsequent GET with the 'If-None-Match' header and token populated:

`curl -X GET \
  'http://localhost:8080/users/1111/cache?=' \
  -H 'If-None-Match: "0249f364aa869f7dfd6b4820a7c18bdb8"' \
  -H 'Postman-Token: ced93690-e284-4e80-a0b1-bbc948910327' \
  -H 'cache-control: no-cache'`

Should result in the following 304 Not Modified response:

`HTTP/1.1 304
Cache-Control: max-age=60
ETag: "0249f364aa869f7dfd6b4820a7c18bdb8"
X-Content-Type-Options: nosniff
X-XSS-Protection: 1; mode=block
X-Frame-Options: DENY
Date: Thu, 14 Feb 2019 18:32:46 GMT`

