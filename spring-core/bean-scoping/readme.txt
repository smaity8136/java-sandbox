This module will illustrate how to use Spring Bean scopes. There are 6 possible scopes:

1) Singleton
2) Prototype
3) Session
4) Request

The example defines 4 domain entities, namely: President, MP, Minister and finally Judge. These 4 beans will have different scopes defined accordingly:

- President -> Singleton
- MP -> Prototype
- Minister -> Session
- Judge -> Request

There are 2 REST controllers defined, each will be autowired with instances of the domain beans above. These controllers will expose endpoints which return a unique identifier for each domain when it is created via it's constructor. What you should notice is the following:

To start the example, execute the following command: gradle bootRun

SINGLETON
=========

The President instance is injected into both the ParliamentController and RegionalController classes. If you invoke the following GET endpoints, you will see the same identifier for the president as it's a singleton, so only one instance will exist in the Spring application context:

http://localhost:8080/parliament/president = 3db9e99f-7303-451f-953f-8a812192cd61
http://localhost:8080/regional/president = 3db9e99f-7303-451f-953f-8a812192cd61

PROTOTYPE
=========

The MP instance has a prototype scope which means that each time an injection is required, a new instance of the class will be created. Thus we inject the MP into both the ParliamentController and RegionalController and each will get their own unique instance:

http://localhost:8080/parliament/mp = 85fc4831-a60b-4597-bdd8-1aeb1c58b64b
http://localhost:8080/regional/mp = 2d529ed2-7a33-4002-8571-e33841b5dd94

SESSION
=======

The Minister class has been given session scope, which means a new bean instance will be created for each web session. To illustrate this example, you need 2 different web browsers (Firefox and Chrome) to create 2 seperate sessions:

FIREFOX: http://localhost:8080/parliament/minister = ed94f985-ef5d-4bba-ae27-197b66c7490c
FIREFOX: http://localhost:8080/regional/minister = ed94f985-ef5d-4bba-ae27-197b66c7490c

CHROME: http://localhost:8080/parliament/minister = 0b5993c2-7f68-40ba-9d9a-39f23949ae77
CHROME: http://localhost:8080/regional/minister = 0b5993c2-7f68-40ba-9d9a-39f23949ae77

Also note that the Minister class is a concrete class (not an interface) and has been given proxy mode = TARGET_CLASS

REQUEST
=======

The Judge interface has been given request scope, so each invocation on either controller will generate a different identifier because a new request is created each time:

http://localhost:8080/regional/judge = 40f8143c-bc74-4ae5-9d17-6a286ebb2124
http://localhost:8080/regional/judge = d76cb621-8eef-4eaa-ae69-3709533793b4
http://localhost:8080/regional/judge = 904bc90d-f143-436c-8113-ea25f8a05301

http://localhost:8080/parliament/judge = 5fd55c62-0804-48b8-a306-6c7cbada9c0c
http://localhost:8080/parliament/judge = 34076f58-1dce-46a5-bbd2-c1128ed95201
http://localhost:8080/parliament/judge = 5df7036c-93b3-4c83-98aa-5a1c253f0e88

Also note that the Judge is an interface, and thus has been given proxy mode = INTERFACES

