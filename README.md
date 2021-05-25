# Forge Portfolio Rework-Backend
## What is the Forge Portfolio Rework?
The Forge Portfolio Rework is a reimagining of the portfolio system currently in place at Revature. This system aims to create a new portfolio system that is both more intuitive for the user and makes it easier to understand the requirements that Revature has in place for them. The rework consiste of a front end and a back end supported by a Jenkins assisted EC2 server that controls the currently deployed version of each end of the project.

## What does the backend consist of?
The backend consists of the models for each subsection of a given portfolio driven by the Spring Framework as well as the necessary functions to support creating, updating, and deleting sections of the portfolio. Each subsection of a given portfolio can be updated independently without affecting the other sections of the portfolio which prevents minor changes to a specific function from causing a breakdown across the entire application. The database embedded within the back end is a H2 database which allows for the system to run without a PostgreSQL database already running at startup.

## Who developed the initial version of this rework?
This rework was initially developed by the Java React CDE group number 210322, trained by Mehrab Rehman in the Spring of 2021. The back end was headed by Charles Radcliffe, the front end was headed by Peter Moore, and the DevOps was primarily a colaboration between Nick Wags and Joseph Allen.

