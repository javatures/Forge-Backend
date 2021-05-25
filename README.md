# Forge Portfolio Rework-Backend
## What is the Forge Portfolio Rework?
The Forge Portfolio Rework is a reimagining of the portfolio system currently in place at Revature. This system aims to create a new portfolio system that is both more intuitive for the user and makes it easier to understand the requirements that Revature has in place for them. The rework consiste of a front end and a back end supported by a Jenkins assisted EC2 server that controls the currently deployed version of each end of the project.

## What does the backend consist of?
The backend consists of the models for each subsection of a given portfolio driven by the Spring Framework as well as the necessary functions to support creating, updating, and deleting sections of the portfolio. Each subsection of a given portfolio can be updated independently without affecting the other sections of the portfolio which prevents minor changes to a specific function from causing a breakdown across the entire application. The database embedded within the back end is a H2 database which allows for the system to run without a PostgreSQL database already running at startup.

# Features
## Data Tables and Models
The models used to create the datatables are as follows. 
- User
    - This model stores the basic information about the users and is linked to portfolios in a one to many relationship
- Portfolio
    - This model acts as a kind of header that all other fields eventually point to it is linked to a specific User on a many to on relationship and to the other fields in a one to many relationship
- AboutMe
    - This model contains basic contact information and a section for the user to store a quick bio about himself/herself
- Certification
    - This model contains the details of a certification earned by the User that they would like to include in the Portfolio.
- Education
    - This model stores the details of a degree earned by the User including the university name, degree type, and graduation date
- Equivalency
    - This model stores information regarding an industry equivalency for a specified technology.
- GitHub
    - This model stores the information regarding the GitHub page that the user is associated with.
- Honor
    - This model contains the details reguarding any awards given to the user.
- Project
    - This model contains the detals of a project created by or in part by the user.
- WorkExperience
    - This model contains the iformation regarding the project done while Working at Revature. 
- WorkHistory
    - This model stores the information to the user's work history prior to their time at Revature.

## Controllers
Each model has its own controller whose functions are mapped to a specific url once the frontend is connected to the backend. The URL list can be found [here](https://docs.google.com/spreadsheets/d/1iYWKSQJV-0d60wPDlXnOQ2at9KlfnnFLT9VjeWImkYE/edit#gid=0). Each controller has basic CRUD functionality as well as a function to get all of a specific model by their associated portfolio where applicable. 

## JSON Upload/Download
The system is able to take a JSON representation of a portfolio to be passed into the backend for storage as well as produce a JSON representation of a given portfolio for the user to download. 

## Test Driven Development
Each major function associated with the controllers has been Unit Tested to ensure the functionality of the code meets the requirments of the tests. Each test was designed to ensure that if something goes wrong the developer tasked with fixing the problem will be able to pinpoint which function needs to be looked at to fix it. 


# Who developed the initial version of this rework?
This rework was initially developed by the Java React CDE group number 210322, trained by Mehrab Rehman in the Spring of 2021. The back end was headed by Charles Radcliffe, the front end was headed by Peter Moore, and the DevOps was primarily a colaboration between Nick Wags and Joseph Allen.

# Technologies Used
- Java
- Spring Framework for Java
- H2 embedded database
- Lombok Library for Java