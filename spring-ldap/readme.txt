Example to show how we can use the spring-ldap-core module to integrate with an LDAP server.

The LDAP server can be mocked using the docker image: https://hub.docker.com/r/osixia/openldap/

1) Get a local container running for openldap:

    docker run -d --name local-openldap osixia/openldap

2) Configure your /etc/hosts file to point to to your container IP address by first inspecting docker "bridge" network:

    docker network inspect bridge

3) Startup the spring boot application:

   gradle bootRun

4) Use any rest client to invoke the following:

    - ADD USER:
        http://localhost:8080/add (POST)

        Body JSON:

        {
        	"username": "test4",
        	"password": "password4"
        }

    - SEARCH USER:
        http://localhost:8080/search (POST)

        Body JSON:

        {
            "username": "test4",
            "password": "password4"
        }

    - AUTHENTICATE USER:
        http://localhost:8080/authenticate/{username} (GET)




