import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

class QuizSystem {

    static final int ADD_QUESTION = 1;
    static final int EDIT_QUESTION = 2;
    static final int DELETE_QUESTION = 3;
    static final int VIEW_ALL_QUESTIONS = 4;
    static final int VIEW_SCORES = 5;
    static final int GENERATE_REPORT = 6;
    static final int ADMIN_LOGOUT = 7;

    static final int TAKE_QUIZ = 1;
    static final int VIEW_SCORES_USER = 2;
    static final int RESET_PASSWORD = 3;
    static final int USER_LOGOUT = 4;

    static final int SIGNUP_ADMIN = 1;
    static final int SIGNUP_USER = 2;
    static final int LOGIN_ADMIN = 3;
    static final int LOGIN_USER = 4;
    static final int EXIT = 5;

    static class Question {
        String quizId;
        String question;
        String answer;

        Question(String quizId, String question, String answer) {
            this.quizId = quizId;
            this.question = question;
            this.answer = answer;
        }
    }

    static class User {
        int id;
        String username;
        String password;
        boolean isAdmin;

        User(int id, String username, String password, boolean isAdmin) {
            this.id = id;
            this.username = username;
            this.password = password;
            this.isAdmin = isAdmin;
        }
    }

    static class Result {
        int userId;
        String quizId;
        int score;
        String dateTaken;

        Result(int userId, String quizId, int score, String dateTaken) {
            this.userId = userId;
            this.quizId = quizId;
            this.score = score;
            this.dateTaken = dateTaken;
        }
    }

    static List<Question> questions = new ArrayList<>();
    static List<User> users = new ArrayList<>();
    static List<Result> results = new ArrayList<>();

    static int nextUserId = 1;

    static boolean isUsernameExists(String username) {
        return users.stream().anyMatch(user -> user.username.equals(username));
    }

    static boolean authenticateAdmin(String username, String password) {
        return users.stream().anyMatch(user -> user.username.equals(username) && user.password.equals(password) && user.isAdmin);
    }

    static boolean authenticateUser(String username, String password) {
        return users.stream().anyMatch(user -> user.username.equals(username) && user.password.equals(password) && !user.isAdmin);
    }

    static void addQuizQuestion(String quizId, String question, String answer) {
        questions.add(new Question(quizId, question, answer));
    }

    static void editQuizQuestion(Scanner scanner) {
        System.out.print("Enter Quiz ID to edit questions: ");
        String quizId = scanner.next();
        scanner.nextLine();

        List<Question> quizQuestions = getQuestionsByQuizId(quizId);
        if (quizQuestions.isEmpty()) {
            System.out.println("No questions found for Quiz ID: " + quizId);
            return;
        }

        for (int i = 0; i < quizQuestions.size(); i++) {
            System.out.println((i + 1) + ". " + quizQuestions.get(i).question);
        }

        System.out.print("Enter the question number to edit: ");
        int questionIndex = scanner.nextInt() - 1;
        scanner.nextLine();

        if (questionIndex >= 0 && questionIndex < quizQuestions.size()) {
            Question question = quizQuestions.get(questionIndex);
            System.out.print("Enter New Question: ");
            String newQuestion = scanner.nextLine();
            System.out.print("Enter New Answer: ");
            String newAnswer = scanner.nextLine();

            question.question = newQuestion;
            question.answer = newAnswer;
            System.out.println("Question updated successfully.");
        } else {
            System.out.println("Invalid question number.");
        }
    }

    static void deleteQuizQuestion(Scanner scanner) {
        System.out.print("Enter Quiz ID to delete questions: ");
        String quizId = scanner.next();
        scanner.nextLine();

        List<Question> quizQuestions = getQuestionsByQuizId(quizId);
        if (quizQuestions.isEmpty()) {
            System.out.println("Quiz ID not found: " + quizId);
            return;
        }

        for (int i = 0; i < quizQuestions.size(); i++) {
            System.out.println((i + 1) + ". " + quizQuestions.get(i).question);
        }

        System.out.print("Enter the question number to delete: ");
        int questionIndex = scanner.nextInt() - 1;
        scanner.nextLine();

        if (questionIndex >= 0 && questionIndex < quizQuestions.size()) {
            questions.remove(quizQuestions.get(questionIndex));
            System.out.println("Question deleted successfully.");
        } else {
            System.out.println("Invalid question number.");
        }
    }

    static List<Question> getQuestionsByQuizId(String quizId) {
        List<Question> quizQuestions = new ArrayList<>();
        for (Question question : questions) {
            if (question.quizId.equals(quizId)) {
                quizQuestions.add(question);
            }
        }
        return quizQuestions;
    }

    static void viewAllQuestions(String quizId) {
        List<Question> quizQuestions = getQuestionsByQuizId(quizId);
        if (quizQuestions.isEmpty()) {
            System.out.println("Quiz ID not found: " + quizId);
            return;
        }

        System.out.println("| NO. | QUESTION                            | ANSWER              |");
        System.out.println("+-----+-------------------------------------+---------------------+");
        for (int i = 0; i < quizQuestions.size(); i++) {
            System.out.printf("| %-3d | %-35s | %-19s |\n", i + 1, quizQuestions.get(i).question, quizQuestions.get(i).answer);
        }
    }

    static void viewScores(String quizId) {
        List<Result> quizResults = new ArrayList<>();
        for (Result result : results) {
            if (result.quizId.equals(quizId)) {
                quizResults.add(result);
            }
        }

        quizResults.sort((r1, r2) -> Integer.compare(r2.score, r1.score));

        if (quizResults.isEmpty()) {
            System.out.println("Quiz ID not found: " + quizId);
            return;
        }

        System.out.println("| Username       | Score | Date Taken           |");
        System.out.println("+----------------+-------+----------------------+");
        for (Result result : quizResults) {
            String username = users.stream().filter(user -> user.id == result.userId).map(user -> user.username).findFirst().orElse("Unknown");
            System.out.printf("| %-14s | %-5d | %-20s |\n", username, result.score, result.dateTaken);
        }
    }

    static void viewScoresForUser(int userId) {
        List<Result> userResults = new ArrayList<>();
        for (Result result : results) {
            if (result.userId == userId) {
                userResults.add(result);
            }
        }

        if (userResults.isEmpty()) {
            System.out.println("No results found for you.");
            return;
        }

        System.out.println("| Quiz ID       | Score | Date Taken           |");
        System.out.println("+---------------+-------+----------------------+");
        for (Result result : userResults) {
            System.out.printf("| %-13s | %-5d | %-20s |\n", result.quizId, result.score, result.dateTaken);
        }
    }

    static void generateReport() {
        System.out.println("| Username       | Quiz ID | Score | Date Taken           |");
        System.out.println("+----------------+---------+-------+----------------------+");
        for (Result result : results) {
            String username = users.stream().filter(user -> user.id == result.userId).map(user -> user.username).findFirst().orElse("Unknown");
            System.out.printf("| %-14s | %-7s | %-5d | %-20s |\n", username, result.quizId, result.score, result.dateTaken);
        }
    }

    static void takeQuiz(String quizId, int userId, Scanner scanner) {
        List<Question> quizQuestions = getQuestionsByQuizId(quizId);
        if (quizQuestions.isEmpty()) {
            System.out.println("No questions available for this quiz ID.");
            return;
        }

        int score = 0;
        String dateStr = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        for (Question question : quizQuestions) {
            System.out.println("+---------------------------------------+");
            System.out.println("| " + question.question);
            System.out.println("+---------------------------------------+");

            System.out.print("Your answer: ");
            String userAnswer = scanner.nextLine();

            if (userAnswer.equals(question.answer)) {
                score++;
            }
        }

        results.add(new Result(userId, quizId, score, dateStr));
        System.out.println("You scored " + score + " out of " + quizQuestions.size() + " on the quiz.");
    }

    static void resetPassword(int userId, String newPassword) {
        users.stream()
                .filter(user -> user.id == userId)
                .findFirst()
                .ifPresent(user -> user.password = newPassword);
        System.out.println("Password updated successfully.");
    }

    static void signUpAdmin(String username, String password) {
        if (isUsernameExists(username)) {
            System.out.println("Username already exists.");
            return;
        }

        User newAdmin = new User(nextUserId++, username, password, true);
        users.add(newAdmin);
        System.out.println("Admin account created successfully.");
    }

    static void signUpUser(String username, String password) {
        if (isUsernameExists(username)) {
            System.out.println("Username already exists.");
            return;
        }

        User newUser = new User(nextUserId++, username, password, false);
        users.add(newUser);
        System.out.println("User account created successfully.");
    }

    static void loginAdmin(String username, String password) {
        if (authenticateAdmin(username, password)) {
            System.out.println("Admin login successful.");
        } else {
            System.out.println("Invalid username or password for admin.");
        }
    }

    static void loginUser(String username, String password) {
        if (authenticateUser(username, password)) {
            System.out.println("User login successful.");
        } else {
            System.out.println("Invalid username or password for user.");
        }
    }

    // Admin Menu
    static void menuAdmin(Scanner scanner) {
        while (true) {
            System.out.println("+----------------------------------------+");
            System.out.println("| ************** Admin Menu ************ |");
            System.out.println("+----------------------------------------+");
            System.out.println("| 1. Add Question                        |");
            System.out.println("| 2. Edit Question                       |");
            System.out.println("| 3. Delete Question                     |");
            System.out.println("| 4. View All Questions                  |");
            System.out.println("| 5. View Scores                         |");
            System.out.println("| 6. Generate Report                     |");
            System.out.println("| 7. Logout                              |");
            System.out.println("+----------------------------------------+");

            System.out.print("Enter your choice: ");
            int choice = -1;
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Consume invalid input
                continue;
            }

            switch (choice) {
                case ADD_QUESTION:
                    System.out.print("Enter Quiz ID: ");
                    String quizId = scanner.nextLine();
                    System.out.print("Enter Question: ");
                    String question = scanner.nextLine();
                    System.out.print("Enter Answer: ");
                    String answer = scanner.nextLine();
                    addQuizQuestion(quizId, question, answer);
                    break;

                case EDIT_QUESTION:
                    editQuizQuestion(scanner);
                    break;

                case DELETE_QUESTION:
                    deleteQuizQuestion(scanner);
                    break;

                case VIEW_ALL_QUESTIONS:
                    System.out.print("Enter Quiz ID: ");
                    String quizIdToView = scanner.nextLine();
                    viewAllQuestions(quizIdToView);
                    break;

                case VIEW_SCORES:
                    System.out.print("Enter Quiz ID: ");
                    String quizIdForScores = scanner.nextLine();
                    viewScores(quizIdForScores);
                    break;

                case GENERATE_REPORT:
                    generateReport();
                    break;

                case ADMIN_LOGOUT:
                    System.out.println("Logging out...");
                    return;

                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    // User Menu
    static void menuUser(Scanner scanner, int userId) {
        while (true) {
            System.out.println("+----------------------------------------+");
            System.out.println("| ************** User Menu ************* |");
            System.out.println("+----------------------------------------+");
            System.out.println("| 1. Take Quiz                           |");
            System.out.println("| 2. View Scores                         |");
            System.out.println("| 3. Reset Password                      |");
            System.out.println("| 4. Logout                              |");
            System.out.println("+----------------------------------------+");

            System.out.print("Enter your choice: ");
            int choice = -1;
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Consume invalid input
                continue;
            }

            switch (choice) {
                case TAKE_QUIZ:
                    System.out.print("Enter Quiz ID: ");
                    String quizId = scanner.nextLine();
                    takeQuiz(quizId, userId, scanner);
                    break;

                case VIEW_SCORES_USER:
                    viewScoresForUser(userId);
                    break;

                case RESET_PASSWORD:
                    System.out.print("Enter new password: ");
                    String newPassword = scanner.nextLine();
                    resetPassword(userId, newPassword);
                    break;

                case USER_LOGOUT:
                    System.out.println("Logging out...");
                    return;

                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    // Main Menu
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("+----------------------------------------+");
            System.out.println("| ************ Main Menu *************** |");
            System.out.println("+----------------------------------------+");
            System.out.println("| 1. Admin Signup                        |");
            System.out.println("| 2. User Signup                         |");
            System.out.println("| 3. Admin Login                         |");
            System.out.println("| 4. User Login                          |");
            System.out.println("| 5. Exit                                |");
            System.out.println("+----------------------------------------+");

            System.out.print("Enter your choice: ");
            int choice = -1;
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Consume invalid input
                continue;
            }

            switch (choice) {
                case SIGNUP_ADMIN:
                    System.out.print("Enter Username: ");
                    String adminUsername = scanner.nextLine();
                    System.out.print("Enter Password: ");
                    String adminPassword = scanner.nextLine();
                    signUpAdmin(adminUsername, adminPassword);
                    break;

                case SIGNUP_USER:
                    System.out.print("Enter Username: ");
                    String userUsername = scanner.nextLine();
                    System.out.print("Enter Password: ");
                    String userPassword = scanner.nextLine();
                    signUpUser(userUsername, userPassword);
                    break;

                case LOGIN_ADMIN:
                    System.out.print("Enter Username: ");
                    String loginAdminUsername = scanner.nextLine();
                    System.out.print("Enter Password: ");
                    String loginAdminPassword = scanner.nextLine();
                    if (authenticateAdmin(loginAdminUsername, loginAdminPassword)) {
                        System.out.println("Admin login successful.");
                        menuAdmin(scanner);
                    } else {
                        System.out.println("Invalid username or password for admin.");
                    }
                    break;

                case LOGIN_USER:
                    System.out.print("Enter Username: ");
                    String loginUserUsername = scanner.nextLine();
                    System.out.print("Enter Password: ");
                    String loginUserPassword = scanner.nextLine();
                    if (authenticateUser(loginUserUsername, loginUserPassword)) {
                        System.out.println("User login successful.");
                        // Find user ID
                        int userId = users.stream()
                            .filter(u -> u.username.equals(loginUserUsername))
                            .findFirst()
                            .get()
                            .id;
                        menuUser(scanner, userId);
                    } else {
                        System.out.println("Invalid username or password for user.");
                    }
                    break;

                case EXIT:
                    System.out.println("Exiting the system.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
