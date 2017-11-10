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

## [0.1.0] - 2017-11-06
### Added
- Endpoint for creating a patient
- Endpoint for patient login
- Password hashing
- Created README.md
- Test cases for notification logic

### Changed
- Modified notification logic
- Removed junk folders

## [0.1.0] - 2017-10-31
### Added
- Basic unit tests for testing notification logic
- Configured travis.yml
- Marketing Communication Report
- Draft for notification logic
- Documentation for calculating number of notifications
- Decision tree diagram for assigning patient's status 

### Changed
- UI sketch for patient home page

## [0.1.0] - 2017-10-29
### Added
- Google Fit API intgration
- Fetch daily delta step count
- Fetch daily delta calories expended
- Sqlite database to client side
- ProgressBar in login activity

### Changed
- SQL Exception handling
- Parse Exception handling
- PatientNotFound Exception handling

## [0.1.0] - 2017-10-26
### Added
- Graph UI for displaying the step count
- Basic push notification upon patient login
- Google Cloud Messaging registration
- Obtain device id from GCM
- Patient DAO Implementation
- Tests for patient DAO implementation

### Changed
- Migrated to PostgreSQL from MySQL
