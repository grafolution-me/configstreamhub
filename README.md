# ConfigStreamHub
ConfigStreamHub is a blog system which uses SpringBoot and Kafka. It 
demonstrates how those technology solutions work. 

## Structure
the f

### Hexagonal architecture
the hexagonal architecture separates business logic from interfaces. This design principle
prescribes the use of interfaces. furthermore, the scaleabily is therefore also better.

The hexagonal architecture enforces a strict separation between application logic and external influences 
through the systematic use of ports and adapters, enhancing modularity and maintainability, whereas the layered architecture 
recommends but does not enforce such a strict separation.

By decoupling application logic from infrastructure and external services, the hexagonal architecture provides greater 
flexibility for integrating with different environments or technologies simply by swapping out the corresponding adapters.

The explicit boundaries defined by ports and adapters in the hexagonal architecture improve testability, making it easier 
to isolate and test application logic independently from its dependencies.

Although both architectures support testing and mocking through the use of interfaces, the hexagonal architecture's structured
approach to separating concerns leads to better testability and higher code quality.


The choice between the hexagonal and layered architectures ultimately depends on the project's specific needs, the development team's preferences, and the long-term goals of the software development effort, with the hexagonal architecture offering a more disciplined framework for achieving flexibility, testability, and maintainability.
````
ConfigStreamHub
└── src
    └── main
        └── java
            └── at.grafolution.ConfigStreamHub
                ├── application   //  Use Cases
                ├── domain        // businesobjects 
                │   ├── model     // Domain-Modelle
                ├── ports         // Ports (Interfaces )
                │   ├── input     // Input Ports (e.g. API Interfaces)
                │   └── output    // Output Ports (e.g. DB, Messaging)
                └── adapters      // Adapters (Implementations)
                    ├── web       // Web-Adapter (e.g. REST-Controller)
                    ├── persistence // DB-Adapter (e.g. Repositories)
                    └── messaging // Messaging-Adapter (e. g. Kafka Listeners)

````

