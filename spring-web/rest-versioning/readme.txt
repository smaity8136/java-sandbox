This exmaple show's 3 ways you can version your REST API:

1) Specifying the version in the URL path, for example:

http://localhost:8080/employee/v1/permanent
http://localhost:8080/employee/v2/permanent

2) Using a request parameter to specify the version

http://localhost:8080/employee/turnkey?version=1
http://localhost:8080/employee/turnkey?version=2

3) Using a header

http://localhost:8080/employee/all [HEADER = X-API-VERSION=1]
http://localhost:8080/employee/all [HEADER = X-API-VERSION=2]
