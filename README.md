# maxim-document-manager

### Configuration
Below is an example of an `application.properties` file:

```properties
spring.datasource.url=jdbc:sqlite:documents.db
spring.datasource.driver-class-name=org.sqlite.JDBC
spring.datasource.username=
spring.datasource.password=

spring.jpa.database-platform=org.hibernate.community.dialect.SQLiteDialect
spring.jpa.hibernate.ddl-auto=update
```

### Running
To start the application run:
```bash
mvn clean javafx:run
```

### Features
- Creating three types of documents
- Viewing and changing added documents
- Saving and reading documents from files
- Storing documents in a database
### In future
- Deleting documents

### Implementation
The main class of the application (`DocumentManagerApplication`) works with the abstract class (`AbstractDocumentForm`) representing a form for adding new documents.
That abstract class as a result returns an abstract document object (`AbstractDocument`) thus implementing a Factory Method design pattern. Hence, the main class 
works only with abstractions thus staying decoupled and easily extandable.
