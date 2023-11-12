# MQTT Spring Boot Application

This is a Spring Boot application that integrates with the Eclipse Paho MQTT client library to handle MQTT messages. The application includes components for message reception, MQTT configuration, and RESTful APIs for managing connection details and MQTT data.

## MqttMessageReceiver Component

The `MqttMessageReceiver` component is responsible for processing MQTT messages. It is annotated with `@Component` and receives an instance of `MqttClient` through constructor injection. You can extend this class to add methods for handling MQTT messages, such as message listening and message processing.

```java
@Component
public class MqttMessageReceiver {

    private final MqttClient mqttClient;

    @Autowired
    public MqttMessageReceiver(MqttClient mqttClient) {
        this.mqttClient = mqttClient;
    }

    // Add methods for handling MQTT messages
    // For example:
    // - Listening for messages
    // - Processing incoming messages
    // - etc.
}
```

## MqttConfig Configuration

The `MqttConfig` class is a Spring configuration class responsible for creating and configuring the MQTT client. It uses the values provided in the application.properties file for configuring the MQTT connection parameters. The `MqttClient` bean is created and configured with connection options. Additionally, a callback is set to handle MQTT client events, such as connection loss and message arrival.

```java
@Configuration
public class MqttConfig {

    // Properties are injected from application.properties
    // ...

    @Autowired
    private MqttDataRepository mqttDataRepository;

    @Autowired
    private ConnectionDetailsRepository connectionDetailsRepository;

    @Bean
    public MqttClient mqttClient() throws MqttException {
        // Configuration of MQTT client
        // ...
        return client;
    }
}
```

## ConnectionDetailsController

The `ConnectionDetailsController` class is a REST controller that provides endpoints for managing connection details. It includes methods for retrieving all connection details, distinct ports, custom fields, distinct host names, and all usernames and passwords.

```java
@RestController
@RequestMapping("/api/connection-details")
public class ConnectionDetailsController {

    // Constructor injection of ConnectionDetailsRepository
    // ...

    // RESTful endpoints for managing connection details
    // ...
}
```

## MqttDataController

The `MqttDataController` class is a REST controller that provides endpoints for managing MQTT data. It includes methods for retrieving all MQTT data, joined data, MQTT data by connection ID, distinct topics, and all messages by topic.

```java
@RestController
@RequestMapping("/api/mqtt-data")
public class MqttDataController {

    // Constructor injection of MqttDataRepository
    // ...

    // RESTful endpoints for managing MQTT data
    // ...
}
```

## Entity Classes

The application includes two JPA entity classes: `ConnectionDetails` and `MqttData`. These classes represent the data model for connection details and MQTT data, respectively.

## Repository Interfaces

The repository interfaces `ConnectionDetailsRepository` and `MqttDataRepository` extend `JpaRepository` and include custom queries for data retrieval.

Feel free to extend and modify this application to suit your specific requirements.
