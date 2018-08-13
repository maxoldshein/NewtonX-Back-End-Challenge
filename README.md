# NewtonX-Back-End-Challenge
Maxwell Oldshein's code submission for the NewtonX Back End Development Coding Challenge.

## My Implementation
To complete the Back End Development coding challenge, I created a RESTful API that implements some CRUD operations with RESTEasy. The operations that I implemented were adding a user by sending the API a JSON payload containing the user's first name and last name (the user is assigned a unique identifier by the API and the user is sent back as a JSON object to confirm their addition), the ability to get a specific user by their unique identifier, and the ability to get a list of all the current user's from the API as JSON data.

## My Testing
I tested this RESTful API on my local machine through the localhost option of Apache Tomcat 9 in the Eclipse IDE. The application that I used to test the API is called Postman ([https://www.getpostman.com](https://www.getpostman.com)), which I utilized to send the API a multitude of GET and POST requests to verify its functionality. For example, to test the the functionality of adding a user from a JSON payload, I used Postman to send a POST request to the path http://localhost:8080/NewtonXBackEndChallenge/user/add with a JSON payload of:
```json
{	
  "firstName": "Maxwell",
  "lastName": "Oldshein"
}
```
and received a JSON object back of 
```json
{
    "id": 1,
    "firstName": "Maxwell",
    "lastName": "Oldshein"
}
``` 
to verify that the API was working correctly.

## How To Interact With The RESTful API:
### 1. How To Add A User.
To add a user, you must follow the path of http://localhost:8080/NewtonXBackEndChallenge/user/add and send a POST request with a JSON payload containing the user's first name and last name. For example:
```json
{
  "firstName": "Maxwell",
  "lastName": "Oldshein"
}
```
If the user is successfully added, you will receive a JSON payload back of:
```json
{
    "id": 1,
    "firstName": "Maxwell",
    "lastName": "Oldshein"
}
```
The ID (1 in the above JSON data) is assigned automatically by the API and is unique to each user. It is initially set to a value of 1 and is automatically incremented each time a user is added to ensure that no two users have the same unique identifier. It was designed to act similarly to a automatically incremented primary key in an SQL database.

If the user was added with their first name or last name as a blank string ("") or as a null value (the value of firstName or lastName was not provided in the JSON payload), the JSON payload you receive back will look like:
```json
{
    "id": -1,
    "firstName": "USER WAS NOT ADDDED; INVALID NAME: FIRST OR LAST NAME NOT PROVIDED!",
    "lastName": "USER WAS NOT ADDDED; INVALID NAME: FIRST OR LAST NAME NOT PROVIDED!"
}
```
The ID of -1 indicates that there was an error, and the string returned in the first name and last name fields will explain what went wrong. In the case that the JSON payload contains a user with an ID of -1, the user from the JSON payload you originally sent to the API was not added to the list of current users.

### 2. How To Get A Specific User.
To get a specific user, you must follow the path of http://localhost:8080/NewtonXBackEndChallenge/user/{id}/get and send a GET request, where {id} is the unique identifier of the specific user that you wish to retrieve. From the above example, to get the user that we added, we would follow the path of http://localhost:8080/NewtonXBackEndChallenge/user/1/get and send a GET request. This would return a JSON payload of the specific user with the unique identifier you provided. For example:
```json
{
    "id": 1,
    "firstName": "Maxwell",
    "lastName": "Oldshein"
}
```
If you provide a unique identifier of a user that does not exist, the JSON payload that you will receive back will look like:
```json
{
    "id": -1,
    "firstName": "User does not exist.",
    "lastName": "User does not exist."
}
```
The ID of -1 indicates that an error was encountered in getting the user with the unique identifier that you provided, and the string returned in the first name and last name fields will explain what went wrong. In the case that the JSON payload you received back has an ID of -1, the user with the unique identifier you requested does not exist in the current users list.

### 3. Getting All The Current Users.
To get a list of all the current users, you must follow the path of http://localhost:8080/NewtonXBackEndChallenge/user/getAllUsers and send a GET request. This would return a JSON payload of all the current users. For example, it might look like this:
```json
[
    {
        "id": 1,
        "firstName": "Maxwell",
        "lastName": "Oldshein"
    },
    {
        "id": 2,
        "firstName": "Steve",
        "lastName": "Jobs"
    },
    {
        "id": 3,
        "firstName": "Tim",
        "lastName": "Cooke"
    },
    {
        "id": 4,
        "firstName": "Bill",
        "lastName": "Gates"
    },
    {
        "id": 5,
        "firstName": "Jeff",
        "lastName": "Bezos"
    }
]
```

## Error Handling
I added error handling to the operations of adding a user with a JSON payload and getting a user by their unique identifier. To indicate that an error was encountered, I return a JSON object with an ID value of -1. 
### 1. Error Handling When Adding A User.
In the case of adding a user, I return an error if a user is added with a blank string ("") value or null value (the value of the firstName or lastName field was not provided) in the fistName field or the lastName field of the JSON payload sent to the API. For example, sending a JSON payload of:
```json
{
	"firstName": "",
	"lastName": "Oldshein"
}
```
or
```json
{
	"lastName": "Oldshein"
}
```
would receive a JSON payload back of:
```json
{
    "id": -1,
    "firstName": "USER WAS NOT ADDDED; INVALID NAME: FIRST OR LAST NAME NOT PROVIDED!",
    "lastName": "USER WAS NOT ADDDED; INVALID NAME: FIRST OR LAST NAME NOT PROVIDED!"
}
```
The receipt of JSON object with an id value of -1 indicates that there was an error. The description of what went wrong is provided in the firstName and lastName values of the JSON object. In this case, the error is "USER WAS NOT ADDDED; INVALID NAME: FIRST OR LAST NAME NOT PROVIDED!" because either a blank string ("") value or null value (no value in the firstName field or lastName field) was provided in the initial JSON payload sent to the API. When an error is encountered while adding a user, the user in question is not added to the list of current users and is only sent back as a JSON object to indicate that an error was encountered (ID value of -1 and firstName and lastName value of "USER WAS NOT ADDDED; INVALID NAME: FIRST OR LAST NAME NOT PROVIDED!").

I did not provide error handling for duplicate first names or last names because of the fact that it is not uncommon for people to have the same first name or last name as other people. For example, "John Smith" could be a name that many users would share, as it a very common name for people to have.

### 2. Error Handling When Getting A Specific User By Their Unique Identifier.
In the case of retrieving a specific user by their unique identifier, I return an error if a unique identifier is provided that does not belong to an existing user. For example, if the list of all current users is:
```json
[
    {
        "id": 1,
        "firstName": "Maxwell",
        "lastName": "Oldshein"
    },
    {
        "id": 2,
        "firstName": "Steve",
        "lastName": "Jobs"
    },
    {
        "id": 3,
        "firstName": "Tim",
        "lastName": "Cook"
    },
    {
        "id": 4,
        "firstName": "Bill",
        "lastName": "Gates"
    },
    {
        "id": 5,
        "firstName": "Jeff",
        "lastName": "Bezos"
    }
]
```
and you send a GET request to the path http://localhost:8080/NewtonXBackEndChallenge/user/1000/get (where the unique identifier you provided is 1000), you would receive a JSON payload back of:
```json
{
    "id": -1,
    "firstName": "User does not exist.",
    "lastName": "User does not exist."
}
```
If you receive a JSON object back that has an id value of -1, an error was encountered processing your request. A description of the specific error that was encounter is returned in the firstName and lastName fields of the JSON object. In the above case, you requested the user with the unique identifier of 1000, which does not exist in the list of current users, so a user JSON object was returned with an id of -1 to indicate that the user does not exist and the error explanation is provided in both the firstName field and lastName field, which both state "User does not exist.".
