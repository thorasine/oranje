# Project Solution

Here you can find my solution for the test.

## Build

You can can either use either Docker or the in-memory database profile.  
The site will run on http://localhost:8080  

--- 

## With Docker
The app requires a MySQL server which is included in the docker compose. 

Create the application jar with Maven:
```
mvn -Dspring-boot.run.profiles=docker package
```

Create the app's Docker image:
```
docker build -t oranje:1.0 .
```
Run the Docker composition:
```
docker-compose up -d
```
After a minute or so the site will up.  

---
## With in-memory database
Just run the following Maven command:
```
mvn spring-boot:run -Dspring-boot.run.profiles=memorydb
```
---
## Accounts

Be aware of the capitalization and spaces.

Username | Password | Roles
--- | --- | ---
`Admin` | admin | *Admin*
`User 1` | user1 | *Editor, User*
`User 2` | user2 | *Editor*
`User 3` | user3 | *User*

**Database**: oranje_main
Username | Password
--- | --- |
`root` | jF4IvxcS5VLpWMPeS3Yj |
`oranje` | bXZqOLfh3k | 

# Notes

## Technical Info
The back end is built on **Java 15** with **Spring Boot 2.5.0** in **IntelliJ IDEA**. 
Persistence is handled by **JPA** and **Hibernate** in a **MySQL** database.
The front is built on **HTML** with **Thymeleaf**, **jQuery**, **Bootstrap** and **CSS**. 

The captcha verification is handled by Google's **reCaptcha**. The keys are domain dependent and configured to localhost, 127.0.0.1 and play-with-docker.com. If you'd like to change it, you can create your own key [here](https://www.google.com/recaptcha/admin/create).  

## Security

The captcha requirement are tied to the **user's IP address** and failed login attempts. It will not lift after a successful sign in, because that would be abusable, rather after a certain time has passed.  
If the captcha result indicates the user being a robot a certain amount of times, it will block the individual from logging in for a given time.  

---
Naturally the IP address is not a 100% certain way to identify users, since it is possible to change or hide it. In a real-world project one could also add other identifiers simultaneously. On top of that, one could enable mandatory captcha globally if the percentage of all unsuccessful logins compared to the total have surpassed a certain threshold.  

However I think for this small homework project these would have been an overkill.


