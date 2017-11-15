# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](http://keepachangelog.com/en/1.0.0/)
and this project adheres to [Semantic Versioning](http://semver.org/spec/v2.0.0.html).

## [0.1.0] - 2017-11-09
### Added
- Endpoint for inserting daily fitness data
- Return number of notifications as response on calling the endpoint
- Endpoint for adding a patient

### Changed
- Fixed bugs for creating a patient
- Formatted JSON body for requests from android client

## Iteration 3
### Added
- Password hashing
- Created README.md
- Test cases for notification logic
- Basic unit tests for testing notification logic
- Configured travis.yml
- Marketing Communication Report
- Draft for notification logic
- Documentation for calculating number of notifications
- Decision tree diagram for assigning patient's status
- Google Fit API intgration
- Fetch daily delta step count
- Fetch daily delta calories expended
- Sqlite database to client side
- ProgressBar in login activity
- Models for Patient, Push Notification
- Databse connection to PostgreSQL
- Controller with endpoints for login and creating a patient
- DAO classes for Patient
- Graph UI for displaying the step count
- Basic push notification upon patient login
- Google Cloud Messaging registration
- Obtain device id from GCM
- Patient DAO Implementation
- Exception Handling

### Changed
- Documentation for calculating number of notifications added
- Login UI sketch changed to reflect the email and SSN
- Corrected RESTful endpoint example for creating a callback
- Revised UML class diagram to capture all models
- Restructured backend code and got rid of junks, added proper documentation

##Iteration 2
### Added
- UML Diagrams
- Documentation for RESTful endpoints
- Skeleton code for backend server
- Patient login layout

### Changed
- UI sketches to reflect interation with Google Fit API
- Added non-CRUD features: Callback flexibility to the patient; Notification calculation in the backend

##Iteration 1
### Added
- Vision Statement
- Feature List
- UI Sketches
- Architecture
- Use Cases

