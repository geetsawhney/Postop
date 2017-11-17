OOSE Project Overview Presentation Evaluation
---------------------------------------------
**Group 19**

**Evaluators: [Srivathsa, Pasumarthi](mailto:pvsriv@cs.jhu.edu) and [Menon, Hari](mailto:hmenon@cs.jhu.edu)**

### Attendance and contribution

One person did not talk! 

(-2 points)

### Overview of project

This could be improved a bit. The overview jumped in to the details of handling nurse callbacks etc, *before* explaining what the primary features of the app were (from a user's point of view). You should provide a bird's eye view of the app, from a user's point of view before you go in to the details of this sort of thing. (For example, it was not clear at the onset that the app provided two main features - water reminders for patients based on a dynamic schedule and requesting / handling nurse callbacks) 

Also some of these terms were not explained (Callback?) and had to be inferred from context. 

(-4 points)

### Demo

Minor glitches, including an app crash once.

(-2 points)

### Architecture

Good!

### Code review

See comments in the last section.

### Presentation organization

Beyond the points in the Overview section, the presentation was reasonably well-organized!

### Talking

Good!

### Quality of slides

You had reasonably good slides.

### Project comments emerging during the talk

- Currently the code implementing notification scheduling is very "rigid" - it uses a number of magic constants (this is a bad code-smell; see the refactoring lectures) and nested if-then-else statements which are extremely hard to adjust given the complex nature of your algorithm. Instead consider using a some decision-tree structure or some other pattern that simplifies additions / modifications. Discuss with your advisor(s).

- Do you have labelled patient history data?

**Grade:  92/100**
