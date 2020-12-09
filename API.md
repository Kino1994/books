
# API REST



## Indices

* [Ungrouped](#ungrouped)

  * [DELETE EXISTING COMMENT 204](#1-delete-existing-comment-204)
  * [DELETE NOT FOUND COMMENT 404](#2-delete-not-found-comment-404)
  * [GET BOOK BY ID WITH EMPTY COMMENTS 200](#3-get-book-by-id-with-empty-comments-200)
  * [GET BOOK BY ID WITH ONE COMMENT 200](#4-get-book-by-id-with-one-comment-200)
  * [GET BOOKS 200](#5-get-books-200)
  * [POST BOOK 201](#6-post-book-201)
  * [PUT COMMENT 200](#7-put-comment-200)
  * [PUT COMMENT IN A NO EXISTING BOOK 404](#8-put-comment-in-a-no-existing-book-404)
  * [PUT COMMENT WITH WRONG SCORE  400](#9-put-comment-with-wrong-score--400)


--------


## Ungrouped



### 1. DELETE EXISTING COMMENT 204


DELETE COMMENT


***Endpoint:***

```bash
Method: DELETE
Type: 
URL: http://localhost:8080/books/0/comment/0
```



### 2. DELETE NOT FOUND COMMENT 404



***Endpoint:***

```bash
Method: DELETE
Type: 
URL: http://localhost:8080/books/0/comment/5
```



### 3. GET BOOK BY ID WITH EMPTY COMMENTS 200



***Endpoint:***

```bash
Method: GET
Type: 
URL: http://localhost:8080/books/2
```



### 4. GET BOOK BY ID WITH ONE COMMENT 200



***Endpoint:***

```bash
Method: GET
Type: 
URL: http://localhost:8080/books/0
```



### 5. GET BOOKS 200



***Endpoint:***

```bash
Method: GET
Type: 
URL: http://localhost:8080/books
```



### 6. POST BOOK 201



***Endpoint:***

```bash
Method: POST
Type: RAW
URL: http://localhost:8080/books
```



***Body:***

```js        
{
  "author": "Joaquin",
  "editorial": "la editorial de Joaquin",
  "publication_year": 2020,
  "summary": "Resumen del libro de Joaquin",
  "tittle": "El libro de Joaquin"
}
```



### 7. PUT COMMENT 200



***Endpoint:***

```bash
Method: PUT
Type: RAW
URL: http://localhost:8080/books/0/comment
```



***Body:***

```js        
{
  "message": "Este libro es buenisimo",
  "name": "joaquin",
  "score": 5
}
```



### 8. PUT COMMENT IN A NO EXISTING BOOK 404



***Endpoint:***

```bash
Method: PUT
Type: RAW
URL: http://localhost:8080/books/22/comment
```



***Body:***

```js        
{
  "message": "Este libro es buenisimo",
  "name": "joaquin",
  "score": 5
}
```



### 9. PUT COMMENT WITH WRONG SCORE  400



***Endpoint:***

```bash
Method: PUT
Type: RAW
URL: http://localhost:8080/books/2/comment
```



***Body:***

```js        
{
  "message": "Este libro es buenisimo",
  "name": "joaquin",
  "score": 7
}
```



---
[Back to top](#api-rest)
> Made with &#9829; by [thedevsaddam](https://github.com/thedevsaddam) | Generated at: 2020-12-09 06:26:32 by [docgen](https://github.com/thedevsaddam/docgen)
