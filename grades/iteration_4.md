# Iteration 4 Evaluation - Group 19

**Evaluator: [Singh, Shirish](mailto:shirish@jhu.edu)**

### Implementation progress
[25 points] (More features working?  Improved demo running?  Good progress on non-CRUD including some integration? Project is looking to have appropriate degree of difficulty overall?)

- The project has made good progress since last iteration. However, there is more to be done.
- When the nurse adds a new patient, the information doesn't get added to the patient list. There are many other bugs. [-5 Points]
- Small code base (4.2k lines of code) means more elegance in features for the final presentation.
- Please work on the UI to make it intuitive. Study some good UI design paradigms.

#### Code Quality
[10 points] (Good file structure?  Good use of object-oriented design principles thus far in codebase?  Correct use of frameworks?  Are there other frameworks or tools you should be using that you are not now?)

- Code quality and structure has been improved since last iteration.
- Android repo needs restructuring. Some java files are redundant.

### Testing
[20 points] (A fully functioning test harness using good code methodology in terms of test setup and tear-down?)

- JUnit has been used appropriately.

#### Test coverage
[10 points] Good coverage with tests?

- Good code coverage of 89.1%. Acceptable coverage for the given code base size is 90%. [-1 Points]
- Almost all methods have been tested considering different execution paths arising because of conditional statements. [-2 Points]
- Method closeConnection in the DbConnector class has no coverage. [-2 Points]

#### Travis
[5 points] (Travis-CI integration tests working?  Tests are at integration level?)

- Tests cover both unit level and integration level cases.
- Integration tests can be consolidated even further.

#### Fully automated build of your project with a README.md describing how to build it
[5 points]

- README.md provides all necessary information for building the project.

### Deployment
[5 points] (Server deployed on Heroku or AWS or other?)

- Successfully deployed on Heroku.

### Advanced Git usage
[10 points] (Still being good about regular commits, pull requests, etc?  Additionally, development primarily taking place on feature branches, no personal-named branches, good git workflow overall?)

- Git has been used appropriately.

### Iteration submission and reporting

- All good.

#### CHANGELOG.md updated and project boards updated, filling in goals for iterations 5 and 6
[10 points]

- Everything good in this section.

### Other Remarks

- There is no format/check for dates, gender, email, address, etc. Make sure you enforce certain rules in correspondance with your database.
- When considering the above changes, try to reduce the cognitive load of the user by providing GUI based calendar date selector.
- UI can be made more informative to assist the users.

**Grade: 90/100**