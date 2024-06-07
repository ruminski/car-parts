# Car Parts

API docs available after starting the app:
- Swagger:<br> http://localhost:8080/swagger-ui/index.html
- OpenAPI:<br>
  http://localhost:8080/v3/api-docs
  http://localhost:8080/v3/api-docs.yaml

# Assumptions taken:
- Requires implementation of Authorization/Authentication to be public API. 
  - OAuth2.0/OIDC is the industry standard.
  - Additionally, for browser clients JWT must be stored in cookies.
- SQL migration/versioning solution is recommended e.g. Flyway/Liquibase
    - The additional non-B-tree indexes could be set at the load/performance test phase e.g. CREATE INDEX description_index ON part (col varchar_pattern_ops);
- All the money values are in EUR. To support multiple currencies model must be changed. See: Moneta - the JSR 354 reference implementation
- Pagination will be required for bigger data volumes


# Scope/Limitations
The automotive company has decided to provide its authorized dealers with information about
available car parts and components. that will contain the following information:
• Make, Model
• Production time of a given model (date range)
• Part name, Part price
• List of service campaigns related to a given part (name of campaign, campaign start date, campaign end date)
• Part availability in the warehouse (yes/no)
• Shipping time (number of days within which the part can be shipped from the warehouse)

1) Develop an appropriate database model. Save this model as an entity at the code level. Use the code-first approach to generate a database in the PostgreSQL database management system.
2) Prepare a set of test data, e.g. 20 parts for different car models and makes. Make this test set always available in the database after your application starts.
3) Divide the application into packages, as if they were to be separate application modules. Assume that the application will grow quite rapidly in the future and we need to be prepared for this
   in terms of organizing packages and later modules (and perhaps microservices) of the system.
4) The application should clearly show separate layers of the system, including at least the repository layer, the service layer and the controller layer.
5) The main goal of the application is to provide data via the REST API interface, so that external applications can use it. We are interested in displaying the following information:
   a. List of parts for a given car model and make, filtered by providing the name of the part or the description of the part (also only some words occurring in these elements)
   b. Information on the availability and possible shipping date for parts with the given id.
   c. Information about all service actions for a given vehicle (make/model) conducted in the given date range.
   In addition to the above, the REST API should enable:
   a. changing the description of the part with the given id,
   b. adding a service action to the part with the given id,
   The REST API should be additionally documented and testable using the Swagger library. The input/output format for the REST API should be JSON.
6) Write unit/integration tests for the public methods of the service layer.

Additional notes:
• The application should be written in Java using the Spring Boot framework.
• The preferred tool for building the application is Maven.
• The Spring Boot Data Rest mechanism should not be used.
• The code and task should be created independently. The use of ChatGPT and related AI tools or ready-made code fragments is prohibited, the code and project will be checked in this regard.

# Other useful information
To test DB DDL and visualize data
```shell
docker run -p 80:80 -e 'PGADMIN_DEFAULT_EMAIL=user@any.pl' -e 'PGADMIN_DEFAULT_PASSWORD=pass' -d dpage/pgadmin4
```
