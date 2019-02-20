Spring Boot - SSL/TLS Setup
===========================

This project depends on it's counterpart **ssl-tls-client** module which will run on port 8081.

Before getting started, we’ll create a self-signed certificate. We’ll use either of the following certificate formats:

PKCS12: Public Key Cryptographic Standards is a password protected format that can contain multiple certificates and keys; it’s an industry-wide used format

JKS: Java KeyStore is similar to PKCS12; it’s a proprietary format and is limited to the Java environment.

- First we should generate a keystore:

**PKCS12 keystore format**

`keytool -genkeypair -alias seedollar -keyalg RSA -keysize 2048 -storetype PKCS12 -keystore seedollar.p12 -validity 3650`

_Make sure you enter: **localhost** for the first name so that you get CN=localhost_

**JKS keystore format**

`keytool -genkeypair -alias seedollar -keyalg RSA -keysize 2048 -keystore seedollar.jks -validity 3650`

# Illustration

Start up the **ssl-tls-server** module, as well as the **ssl-tls-client** module. Because both applications are pointing to the same
keystore (seedollar.p12), we illustrate how curl invocations to the ssl-tls-client application will make
TLS rest invocations to the ssl-tls-server application in-turn. Below are the requests you can make to the ssl-tls-client application: 

##CLIENT REST API

### Add new car
`curl -X POST 'http://localhost:8081/cars' -H 'Content-Type: application/json' -d '{"make":"VW", "model":"TT", "plate":"GYE-434-MP", "isElectric": false}'`

### Show all cars
`curl -X GET 'http://localhost:8081/cars'`

### Get car for ID
`curl -X GET 'http://localhost:8081/cars/{carId}'`





