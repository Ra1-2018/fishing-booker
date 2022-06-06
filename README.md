# fishing-booker

Web application for booking cottages, boats, and adventures.

Developers
Student 1: Nikola Ivanović RA1/2018
Student 2: Bogdana Živković RA2/2018
Student 3: Lenka Isidora Aleksić RA23/2018

Running our project locally:
Backend
Maven SpringBoot application
It can be started normally as a Java project in Eclipse or IntelliJ. We used Java 17 version. The server is running on port 8080. PostgreSQL is needed for the backend. 

Frontend
The frontend is an Angular application. It can be started by running npm install and then npm run serve in the mentioned folder. The URL address is http://localhost:4200/.

Heroku deployment
Frontend - https://fishing-booker-client.herokuapp.com
Backend - https://fishing-booker-app.herokuapp.com

Emails and passwords for all roles
Client: nikola.iv.99@gmail.com, ftn
Cottage owner: bogdanaz207@gmail.com, ftn
Boat owner: mila@gmail.com, ftn
Instructor: porodica1aleksic@gmail.com, ftn
Administrator: lenkaisidora.aleksic@gmail.com, ftn

In case heroku app refuses to send emails, it is required to allow access to google acount used for mailing that is noted in application.properties via https://accounts.google.com/b/0/DisplayUnlockCaptcha
