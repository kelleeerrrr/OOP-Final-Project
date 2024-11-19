# QuizCraft: The Comprehensive Interactive Test System

This Java-based Comprehensive Interactive Test System provides functionality for both administrators and users. The system supports operations like creating, editing, deleting, and viewing quiz questions, as well as managing user accounts and tracking quiz results. It also includes features like generating reports and resetting passwords.

## Features

### Admin Features:
- **Add Questions**: Admins can add new quiz questions with associated answers.
- **Edit Questions**: Admins can modify existing questions for a specific quiz.
- **Delete Questions**: Admins can remove questions from a specific quiz.
- **View All Questions**: Admins can view all the questions for a specific quiz.
- **View Quiz Scores**: Admins can view the scores of all users for a specific quiz.
- **Generate Reports**: Admins can generate a detailed report of quiz results, showing user names, quiz IDs, scores, and the dates the quizzes were taken.
- **Admin Logout**: Admins can log out of the system.

### User Features:
- **Take Quiz**: Users can take a quiz by answering the questions.
- **View Scores**: Users can view their past quiz scores.
- **Reset Password**: Users can reset their password.
- **User Logout**: Users can log out of the system.

### Account Management:
- **Admin Signup**: Admins can sign up by providing a username and password.
- **User Signup**: Users can sign up by providing a username and password.
- **Admin Login**: Admins can log in using their credentials.
- **User Login**: Users can log in using their credentials.

## Structure and Classes

### `Question` Class
Represents a single quiz question. Each `Question` object contains:
- `quizId`: The identifier for the quiz.
- `question`: The text of the question.
- `answer`: The correct answer for the question.

### `User` Class
Represents a user in the system. Each `User` object contains:
- `id`: The unique ID for the user.
- `username`: The username of the user.
- `password`: The password of the user.
- `isAdmin`: A boolean value indicating whether the user is an admin.

### `Result` Class
Represents a user's quiz result. Each `Result` object contains:
- `userId`: The ID of the user who took the quiz.
- `quizId`: The ID of the quiz.
- `score`: The score the user achieved on the quiz.
- `dateTaken`: The date and time when the quiz was taken.

### `QuizSystem` Class
The main class that handles the quiz logic. It includes methods to:
- Add, edit, and delete quiz questions.
- Authenticate users and admins.
- Track quiz scores and results.
- Generate reports.
- Handle user interactions in the menu system.

## Dependencies
- Java 8 or higher for `java.time.LocalDateTime` and `java.util.Scanner`.
- No additional external libraries are required.


## Contributor
 
| Name                         | Role       | Email                         |
|------------------------------|------------|-------------------------------|
| [Rivera, Irish](https://github.com/kelleeerrrr)      | Developer  | 23-00679@g.batstate-u.edu.ph  |
 
### Course
CS 211 - Object-Oriented Programming
 
### Course Facilitator
Ms. Fatima Marie Agdon, MSCS
