# Spring Boot Backend example with JWT Spring Security & Spring Data JPA & CRUD & Email Verification

![](https://miro.medium.com/v2/resize:fit:828/format:webp/1*EcbUIt-Fgf55UQGXcPx9cQ.png)

JSON Web Tokens (JWT) have become a popular method for securing modern web applications. JWTs allow you to transmit information securely between parties as a compact, self-contained, and digitally signed JSON object.



# JWT and Mail Dependency

To get started, you’ll need to add the necessary dependencies to your pom.xml file. In this example, we'll use Spring Security and the jjwt library for JWT support. Here's what your dependencies section should look like:

```
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-api</artifactId>
            <version>0.12.3</version>
        </dependency>
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-impl</artifactId>
            <version>0.12.3</version>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-jackson</artifactId> <!-- or jjwt-gson if Gson is preferred -->
            <version>0.12.3</version>
            <scope>runtime</scope>
        </dependency>
         <dependency>
            <groupId>com.sun.mail</groupId>
            <artifactId>javax.mail</artifactId>
            <version>1.6.2</version>
        </dependency>
 ```

For SECRET_KEY : [https://seanwasere.com/generate-random-hex/](https://seanwasere.com/generate-random-hex/)
