# maxim-document-manager

### Features
- Creating three types of documents
- Viewing and changing added documents
- Saving and reading documents from files
### In future
- Storing documents in a database

### Implementation
The main class of the application (`DocumentManagerApplication`) works with the abstract class (`AbstractDocumentForm`) representing a form for adding new documents.
That abstract class as a result returns an abstract document object (`AbstractDocument`) thus implementing a Factory Method design pattern. Hence, the main class 
works only with abstractions thus staying decoupled and easily extandable.
