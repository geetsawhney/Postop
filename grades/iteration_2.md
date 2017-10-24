# Iteration 2 Evaluation - Group 19

**Evaluator: [Singh, Shirish](mailto:shirish@jhu.edu)**
**Evaluator: [Arora, Rishab](mailto:rishab@cs.jhu.edu)**

### Features

- New features added for the user.
- Please look into what kind of additional data can be received from Google Fit.
- Consider adding features like notification for taking pills. Rethink your timelines if you do.
..- Nurse should be able to schedule the time to take pills.
- Feature details are still ambigious. How will the severity be calculated? How will the notification frequency be calculated? You need to flesh out these models. [-1 point]

### UI sketches

- UI sketches are self-explanatory. Please change the view of home activity; it should not be a replica of Google Fit. Or if you wish to use images/views that are not yours, cite them.
- The UI sketches should reflect the change in the login mechanism. The login Activity sketch should reflect the email and password rather than DOB and SSN. [-2 points]
- Slight ambiguity about what 'severity' is. One UI has it as a number, the other has it as "Extreme".

### Endpoint and/or other interface documentation

- Endpoints are well documented with detailed explanations in most cases.
- However, some of the descriptions do not match the targeted resource. For instance, "Create a callback" accepts the body of a patient instead of a callback object. [-2 points]
- The features talk about a "token", no mention of this anywhere in the API/UML.
- There is no visible association between a nurse and patient in the API but there is one in the UML. The only way to get patients is to get all of them, and then no way to filter.

### UML Class diagram

- Class diagrams do not cover all the entities of the application. Please revise it to incorporate the changes recommended in the last lab meeting. [-2 points]

### Architecture

- Architecture is good. However, please mention how are you planning to send notifications?

### Initial code

- The backend code is not well structured. Please change the Todo documentations to reflect the documentation of your application. [-4 Points]

#### Initial commit made

- All members have committed to the repo at least once.

### Other General Remarks

- Prepare a two-week plan for focusing the development efforts on the key features.
- Start prototyping the features which need third-party API or libraries.

**Grade: 89/100**
