# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](http://keepachangelog.com/en/1.0.0/)
and this project adheres to [Semantic Versioning](http://semver.org/spec/v2.0.0.html).

## Iteration 6
### Added
- Notification JSON validation
- Javadoc to backend code
- Endpoints to fetch and update number of notifications
- Mailing feature to send email upon new patient addition
- Notification page for nurse to control the number of notifications to be sent
- Collapse feature in callback list page
- Checks for email and password fields on Android

### Changed
- Error handling for failure of Ajax requests
- Date validity checks for date of birth and hospital visit date
- Removed void return types from Android and backend
- Added check boxes for diabetic and catheter in update patient page
- Added data validation checks from input form fields
- Removed unnecessary toasts notification in Android
- Created sections for patient information
- Android UI enhancements
- UI changes to how callbacks are displayed
- Fixed callback JSON validation

## Iteration 5
### Added
- Calendar for date fields input in nurse Frontend
- JavaScript field values check before form submission

### Changed
- Fixed alarms to persist after device restarts
- Scrollable homepage layout with fixed portrait orientation
- Removed trivial test cases for getters and setters, fixed issue with code coverage
- Changed return type of DbConnector.java connectionClose() method to boolean
- Covered all if else cases in test
- Removed duplicate classes from android code
- Case sensitivity fixed in email validations

## Iteration 4
### Added
- JSON validation classes for POST and PUT requests
- Edit patient in nurse frontend
- Heroku deployment at https://postop.herokuapp.com
- Test cases for endpoints, callback logic and notification logic
- Javadoc for android code
- Frontend integration with Spark Java code
- Layout for patient to create a callback
- Functionality to allow a nurse to add a patient
- Nurse frontend to display list of pending callbacks, list of patients
- Scheduler for sending push notifications for drinking water
- Scheduler for sending Google fit data and fetching the notification count
- Logout button for patient in android client
- IOException and UnsupportedExceptionHandling
- Endpoint for sending push notifications
- Callback logic
- Endpoint to fetch a list of pending callbacks
- Endpoint to update callback
- Callback model, Dao interface and implementation
- Endpoint to fetch a list of all patients
- Endpoint to fetch a patient
- Endpoint for updating a patient
- Endpoint for inserting google fit data and returning the number of notifications
- Endpoint for inserting daily fitness data
- Patient Login model
- Password hashing
- Return number of notifications as response on calling the endpoint
- Endpoint for adding a patient

### Changed
- Updated Travis to build both backend and android
- Switched from SSN to password login
- Notification Logic
- Created CHANGELOG.md
- Restructured android code and added Javadoc
- Updated project board for all iterations
- Added relevant endpoints
- Updated README to include language versions, technology stack
- Fixed bugs for creating a patient
- Formatted JSON body for requests from android client

## Iteration 3
### Added
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

## Iteration 2
### Added
- UML Diagrams
- Documentation for RESTful endpoints
- Skeleton code for backend server
- Patient login layout

### Changed
- UI sketches to reflect integration with Google Fit API
- Added non-CRUD features: Callback flexibility to the patient; Notification calculation in the backend

## Iteration 1
### Added
- Vision Statement
- Feature List
- UI Sketches
- Architecture
- Use Cases
