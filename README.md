# Project Solution

## Build

The app requires a MySQL server which is included in the docker compose.

Build the application jar with Maven:
```
mvn package 
```

Create the app's Docker image:
```
docker build -t oranje:1.0 .
```
Run the Docker composition:
```
docker-compose up -d
```
After a minute or so the site will be accessible on:  
http://localhost:8080

## Accounts

Be aware of the capitalization and spaces.

Username | Password | Roles
--- | --- | ---
`Admin` | admin | *Admin*
`User 1` | user1 | *Editor, User*
`User 2` | user2 | *Editor*
`User 3` | user3 | *User*
 |  | 

<br/><br/>
**Database**: oranje_main
Username | Password
--- | --- |
`root` | jF4IvxcS5VLpWMPeS3Yj |
`oranje` | bXZqOLfh3k | 
 |  | 

<br/><br/>
# Notes

## Technical Info
The back end is built on **Java 15** with **Spring Boot 2.5.0** in **IntelliJ IDEA**. 
Persistence is handled by **JPA** and **Hibernate** in a **MySQL** database.
The front is built on **HTML** with **Thymeleaf**, **jQuery**, **Bootstrap** and **CSS**. 

The captcha verification is handled by Google's **reCaptcha**. The keys are domain dependent and configured to localhost, 127.0.0.1 and play-with-docker.com. If you'd like to change it, you can create your own key [here](https://www.google.com/recaptcha/admin/create).  

