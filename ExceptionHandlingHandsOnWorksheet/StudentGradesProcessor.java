package ExceptionHandlingHandsOnWorksheet;

//import java.io.*;
//import java.util.*;
//
//public class StudentGradesProcessor {
//    private static final String FILE_NAME = "grades.txt";
//
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        while (true) {
//            System.out.println("Menu:");
//            System.out.println("1. Process Grades");
//            System.out.println("2. Exit");
//            System.out.print("Choose an option: ");
//            int choice = scanner.nextInt();
//            scanner.nextLine(); // Consume newline
//
//            if (choice == 1) {
//                processGrades();
//            } else if (choice == 2) {
//                System.out.println("Exiting...");
//                break;
//            } else {
//                System.out.println("Invalid choice. Please try again.");
//            }
//        }
//        scanner.close();
//    }
//
//    private static void processGrades() {
//        List<Student> students = new ArrayList<>();
//        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                String[] parts = line.split(" ");
//                if (parts.length != 2) {
//                    System.out.println("Invalid line format: " + line);
//                    continue;
//                }
//                String name = parts [0];
//                try {
//                    int grade = Integer.parseInt(parts[1]);
//                    if (grade < 0 || grade > 100) {
//                        throw new InvalidGradeException("Grade out of range: " + grade);
//                    }
//                    students.add(new Student(name, grade));
//                } catch (NumberFormatException e) {
//                    System.out.println("Invalid grade for student " + name + ": " + parts[1]);
//                } catch (InvalidGradeException e) {
//                    System.out.println(e.getMessage());
//                }
//            }
//        } catch (FileNotFoundException e) {
//            System.out.println("File not found: " + FILE_NAME);
//        } catch (IOException e) {
//            System.out.println("Error reading file: " + e.getMessage());
//        }
//
//        if (students.isEmpty()) {
//            System.out.println("No valid grades found.");
//            return;
//        }
//
//        int totalGrades = 0;
//        List<Student> failingStudents = new ArrayList<>();
//        for (Student student : students) {
//            totalGrades += student.getGrade();
//            if (student.getGrade() < 40) {
//                failingStudents.add(student);
//            }
//        }
//
//        double averageGrade = (double) totalGrades / students.size();
//        System.out.println("Valid student names and grades:");
//        for (Student student : students) {
//            System.out.println(student.getName() + ": " + student.getGrade());
//        }
//        System.out.println("Average grade: " + averageGrade);
//        System.out.println("Failing students:");
//        for (Student student : failingStudents) {
//            System.out.println(student.getName() + ": " + student.getGrade());
//        }
//    }
//
//    static class Student {
//        private String name;
//        private int grade;
//
//        public Student(String name, int grade) {
//            this.name = name;
//            this.grade = grade;
//        }
//
//        public String getName() {
//            return name;
//        }
//
//        public int getGrade() {
//            return grade;
//        }
//    }
//
//    static class InvalidGradeException extends Exception {
//        public InvalidGradeException(String message) {
//            super(message);
//        }
//    }
//}

import java.io.*;
import java.util.*;

public class StudentGradesProcessor {
    private static final String OUTPUT_FILE_NAME = "results.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the file path: ");
        String filePath = scanner.nextLine();

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Process Grades");
            System.out.println("2. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 1) {
                processGrades(filePath);
            } else if (choice == 2) {
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    private static void processGrades(String filePath) {
        List<Student> students = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length != 2) {
                    System.out.println("Invalid line format: " + line);
                    continue;
                }
                String name = parts[0];
                try {
                    int grade = Integer.parseInt(parts[1]);
                    if (grade < 0 || grade > 100) {
                        throw new InvalidGradeException("Grade out of range: " + grade);
                    }
                    students.add(new Student(name, grade));
                } catch (NumberFormatException e) {
                    System.out.println("Invalid grade for student " + name + ": " + parts[1]);
                } catch (InvalidGradeException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        if (students.isEmpty()) {
            System.out.println("No valid grades found.");
            return;
        }

        int totalGrades = 0;
        List<Student> failingStudents = new ArrayList<>();
        for (Student student : students) {
            totalGrades += student.getGrade();
            if (student.getGrade() < 40) {
                failingStudents.add(student);
            }
        }

        double averageGrade = (double) totalGrades / students.size();

        try (PrintWriter writer = new PrintWriter(new FileWriter(OUTPUT_FILE_NAME))) {
            writer.println("Valid student names and grades:");
            for (Student student : students) {
                writer.println(student.getName() + ": " + student.getGrade());
            }
            writer.println("Average grade: " + averageGrade);
            writer.println("Failing students:");
            for (Student student : failingStudents) {
                writer.println(student.getName() + ": " + student.getGrade());
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }

        System.out.println("Results written to " + OUTPUT_FILE_NAME);
    }

    static class Student {
        private String name;
        private int grade;

        public Student(String name, int grade) {
            this.name = name;
            this.grade = grade;
        }

        public String getName() {
            return name;
        }

        public int getGrade() {
            return grade;
        }
    }

    static class InvalidGradeException extends Exception {
        public InvalidGradeException(String message) {
            super(message);
        }
    }
}
