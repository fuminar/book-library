# Book Library API

API for managing library books inventory.

# How to run?

You can run this project in different ways:

### Docker

The project's image can be pulled
from [Docker Hub](https://hub.docker.com/repository/docker/gabriellorandi96/book-library).

Or simply using the **docker pull** command:

```
docker pull gabriellorandi96/book-library
```

And run the command:

```
docker run -p 8080:8080 gabriellorandi96/book-library:latest
```

### IDE

Requirements:

- Java 11
- Maven
- A Java IDE

The project should be imported as **maven project** from your IDE, and then run.

---

# Using the API

With the project running locally, the swagger-ui Api Documentation can be accessed on: <http:localhost:8080/swagger-ui/>

### Books

#### A Book can be created using the POST method explained in  **Book Operations**.

Example:
POST: /api/v1/books
Body:

```json
{
  "bookName": "Book test",
  "authorNames": [
    "Author 1"
  ],
  "publisher": "Publisher test",
  "publicationYear": 2022,
  "summary": "sumary test",
  "fileFormat": null
}
```

Response:

```json
{
  "id": "94bc5e97-f294-4b99-9d2c-198258fbc449",
  "name": "Book test",
  "authors": [
    {
      "id": "7487bd95-b84b-46d4-867b-a3e0c5086a05",
      "name": "Author 1"
    }
  ],
  "publisher": null,
  "publicationYear": 2022,
  "summary": "sumary test",
  "inventory": 1,
  "fileFormat": null,
  "bookType": "BOOK"
}
```

An EBook can also be created, the only difference from a Book is its file format.

Example:
POST: /api/v1/books
Body:

```json
{
  "bookName": "Book test",
  "authorNames": [
    "Author 1"
  ],
  "publisher": "Publisher test",
  "publicationYear": 2022,
  "summary": "sumary test",
  "fileFormat": "pdf"
}
```

Response:

```json
{
  "id": "94bc5e97-f294-4b99-9d2c-198258fbc449",
  "name": "Book test",
  "authors": [
    {
      "id": "7487bd95-b84b-46d4-867b-a3e0c5086a05",
      "name": "Author 1"
    }
  ],
  "publisher": null,
  "publicationYear": 2022,
  "summary": "sumary test",
  "inventory": 1,
  "fileFormat": "pdf",
  "bookType": "E_BOOK"
}
```

---

#### There's also a GET endpoint to list all Books with pagination.

Example:
GET: /api/v1/books

Response:

```json
{
  "hasNext": false,
  "pageSize": 20,
  "pageNumber": 0,
  "totalElements": 1,
  "items": [
    {
      "id": "94bc5e97-f294-4b99-9d2c-198258fbc449",
      "name": "Book test",
      "authors": [
        {
          "id": "7487bd95-b84b-46d4-867b-a3e0c5086a05",
          "name": "Author 1"
        }
      ],
      "publisher": null,
      "publicationYear": 2022,
      "summary": "sumary test",
      "inventory": 1,
      "fileFormat": null,
      "bookType": "BOOK"
    }
  ]
}
```

---

#### List Books by Authors.

Example:
GET: /api/v1/books/authors?authorsId={authorId}
*NOTE*: The param 'authorsId' supports multiple ids separated by ',', for instance authorsId=123456,65321.

Response:

```json
{
  "hasNext": false,
  "pageSize": 20,
  "pageNumber": 0,
  "totalElements": 1,
  "items": [
    {
      "id": "6be677da-ec4c-43af-a975-ae506b6119b8",
      "name": "Book test",
      "authors": [
        {
          "id": "5e51e944-6e4d-41f5-afbb-a6dda4460f5b",
          "name": "Author 1"
        }
      ],
      "publisher": null,
      "publicationYear": 2022,
      "summary": "sumary test",
      "inventory": 1,
      "fileFormat": null,
      "bookType": "BOOK"
    }
  ]
}
```

---

#### Delete Book.

DELETE: /api/v1/books/{bookId}

---

#### Update Book.

PUT: /api/v1/books/{bookId}

Example:
PUT: /api/v1/books/94bc5e97-f294-4b99-9d2c-198258fbc449
Body:

```json
{
  "bookName": "Book test",
  "authorNames": [
    "Author 1"
  ],
  "publisher": "Publisher test",
  "publicationYear": 2022,
  "summary": "sumary test",
  "fileFormat": "pdf"
}
```

Response:

```json
{
  "id": "94bc5e97-f294-4b99-9d2c-198258fbc449",
  "name": "Book test",
  "authors": [
    {
      "id": "7487bd95-b84b-46d4-867b-a3e0c5086a05",
      "name": "Author 1"
    }
  ],
  "publisher": null,
  "publicationYear": 2022,
  "summary": "sumary test",
  "inventory": 1,
  "fileFormat": "pdf",
  "bookType": "E_BOOK"
}
```

#### Update Book Inventory

PUT: /api/v1/books/{bookId}/update-inventory
To update the inventory the body request must contain the `operation` (ADD or REMOVE) and the `amount`.

Example:
PUT: /api/v1/books/f39ef283-2a23-456f-b2db-e8e0853ddb46/update-inventory
Removing:
Body:

```json
{
  "operation": "REMOVE",
  "amount": 1
}
```

Response:

```json
{
  "bookId": "f39ef283-2a23-456f-b2db-e8e0853ddb46",
  "bookName": "Book test",
  "inventory": 0
}
```

Adding:
Body:

```json
{
  "operation": "ADD",
  "amount": 1
}
```

Response:

```json
{
  "bookId": "f39ef283-2a23-456f-b2db-e8e0853ddb46",
  "bookName": "Book test",
  "inventory": 2
}
```

# Technical Notes

## Stack

This project uses the following stack:

- Java 11
- Spring 2.5.1
- Maven

Some Dependencies used:

- H2 for embedded database
- Flyway for database migration
- Swagger (SpringFox) for API documentation

## Plugins

This project uses JIB maven plugin from Google to generate a docker image and push to Docker HUb.

Documentation [here](https://github.com/GoogleContainerTools/jib/tree/master/jib-maven-plugin).

The command to use the plugin is:

```
mvn compile jib:build
```

# Problem with Pageable and Swagger

There is a known [issue](https://github.com/springfox/springfox/issues/2623)
with Swagger trying to deserialize Pageable entity.

The workaround was creating an annotation that configures the pagination attributes of Swagger and uses it together
with @ApiIgnore annotation to ignore the Pageable attributes.

ApiPageable interface:

```java

@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@ApiImplicitParams({
        @ApiImplicitParam(name = "page", dataTypeClass = Integer.class, paramType = "query", value = "Number of the page."),
        @ApiImplicitParam(name = "size", dataTypeClass = Integer.class, paramType = "query", value = "Number of records per page."),
        @ApiImplicitParam(name = "sort", allowMultiple = true, dataTypeClass = String.class, paramType = "query", value = "Sorting criteria in the format: property,asc|desc.")
})
public @interface ApiPageable {
}
```

Usage:

```java

@RestController
public class ExampleController {
    @ApiPageable
    @GetMapping
    public ResponseEntity<Void> usageExample(@ApiIgnore Pageable pageable) {
        return ResponseEntity.noContent().build();
    }
}
```