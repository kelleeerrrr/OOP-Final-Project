# QuizCraft: The Comprehensive Interactive Test System

The QuizCraft: The Comprehensive Interactive Test System is a Java-based application designed to streamline quiz creation, management, and participation. This program features distinct functionalities for administrators and users, promoting efficient quiz administration and interactive user experiences. The system supports secure logins, result tracking, and score visualization.
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
## Application of OOP Principles  
This project employs the following Object-Oriented Programming (OOP) principles:  

### 1. **Encapsulation**  
- All data members such as `questions`, `users`, and `results` are private, ensuring controlled access through getter and setter methods.  
- The `Person` class encapsulates user-specific properties (`id`, `username`, `password`).  

### 2. **Inheritance**  
- The `Admin` and `User` classes inherit common attributes and methods from the abstract `Person` class, reducing redundancy.  

### 3. **Polymorphism**  
- The abstract method `menu()` is overridden in both `Admin` and `User` classes to define role-specific functionalities.  

### 4. **Abstraction**  
- The `Person` class is declared abstract to provide a base structure while hiding implementation details from subclasses.  

---

## Sustainable Development Goal (SDG) Integration  
### Chosen SDG: **Quality Education (SDG 4)**  
This project aligns with SDG 4 by promoting:  
- Accessible, engaging, and organized quiz systems to enhance the learning experience.  
- A platform for users to improve knowledge retention through interactive assessments.  
- Secure and transparent scoring to motivate continuous learning.  

---

## Instructions for Running the Program  

1. **Prerequisites**  
   - Ensure you have Java Development Kit (JDK) installed on your system.  
   - Recommended version: JDK 8 or higher.  

2. **Clone the Repository**  
   Run the following command in your terminal to clone this repository:  
   ```bash  
   git clone [https://github.com/kelleeerrrr/QuizCraft.git]

3. **Compile the Program**
   Navigate to the project directory and compile the code using:
   ```bash
   javac QuizCraft.java  
4. **Run the Program**
   Execute the program by entering:
   ```bash
   java QuizCraft  
## Contributor
 
| Name                         | Role       | Email                         |
|------------------------------|------------|-------------------------------|
| [Rivera, Irish](https://github.com/kelleeerrrr)      | Developer  | 23-00679@g.batstate-u.edu.ph  |
 
### Course
CS 211 - Object-Oriented Programming
 
### Course Facilitator
Ms. Fatima Marie Agdon, MSCS
