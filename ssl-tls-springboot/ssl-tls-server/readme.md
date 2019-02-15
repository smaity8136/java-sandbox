Spring Boot - SSL/TLS Setup
===========================

Before getting started, we’ll create a self-signed certificate. We’ll use either of the following certificate formats:

PKCS12: Public Key Cryptographic Standards is a password protected format that can contain multiple certificates and keys; it’s an industry-wide used format

JKS: Java KeyStore is similar to PKCS12; it’s a proprietary format and is limited to the Java environment.

- First we should generate a keystore:

**PKCS12 keystore format**

`keytool -genkeypair -alias seedollar -keyalg RSA -keysize 2048 -storetype PKCS12 -keystore seedollar.p12 -validity 3650`

**JKS keystore format**

`keytool -genkeypair -alias seedollar -keyalg RSA -keysize 2048 -keystore seedollar.jks -validity 3650`


REST API
 
`curl -X POST 'http://localhost:8080/cars' -H 'Content-Type: application/json' -d '{"carId": 1111, "make": "Audi", "model": "A6", "isElectric":false, "plate": "GBH-547-GP"}'`

`curl 'http://localhost:8080/cars/1111`


