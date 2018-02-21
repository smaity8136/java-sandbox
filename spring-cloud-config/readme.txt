This example shows how you can setup a Spring Cloud Config Server. There are 2 modules:

1) cloud-config-server
2) cloud-config-client
3) a local git repository (config-repo)

The cloud-config-server module will act as the intermediary and thus needs the @EnableConfigServer annotation.
The cloud-config-server will run on port 8888, so you can just start it by executing:

gradle bootRun

The cloud-config-client module will consume the spring cloud server information and will run on port 8300. You must
configure the application.yml to point to the cloud-config-server uri (localhost:8888)

To start the client execute: gradle bootRun

To test the config consumption, browse to:

http://localhost:8300/director

Which will present the director name configured in the git repository's 'cloud-config-client.properties' file.

You can also see the cloud-config server's configuration at:

http://localhost:8888/cloud-config-client/default