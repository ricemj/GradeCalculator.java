import java.util.Objects;
import java.util.Scanner;

public class Main {
    //initialize variables as static so they can be used throughout class
    static Scanner keyboard;
    static String input;
    static boolean isRunning = true;

    public static void main(String[] args) {
        //program runs until user prompts exit
        while(isRunning) {
            keyboard = new Scanner(System.in);

            //lists calculator options
            System.out.println("------------------------------");
            System.out.println("Type 's' to calculate semester GPA");
            System.out.println("Type 'f' to calculate grade needed for final");
            System.out.println("Type 'c' to calculate cumulative GPA");
            System.out.println("Type 'x' to exit");

            //temporarily stores user input
            input = keyboard.nextLine();

            //checks user input, if 's' program runs semesterGPA method
            if (Objects.equals(input, "s")) {
                semesterGPA();
            }
            //checks user input, if 'f' program runs finalGPA method
            if (Objects.equals(input, "f")) {
                finalGPA();
            }
            //checks user input, if 'c' program runs totalGPA method
            if (Objects.equals(input, "c")) {
                totalGPA();
            }
            //checks user input, if 'x' program exits
            if (Objects.equals(input, "x")) {
                isRunning = false;
            }
        }
    }

    public static void totalGPA() {
        //initializes variables
        boolean isEntering = true;
        int totalClasses = 0;
        double cumulativeGPA = 0.0;

        //continues to let user input grades until user prompts calculation
        while(isEntering) {
            System.out.print("Enter the number of classes you received a specific grade or press 'c' to calculate: ");
            //temporarily stores user input
            input = keyboard.nextLine();

            //checks user input, if 'c' program ends input loop and calculates cumulative GPA
            if(Objects.equals(input, "c")) {
                isEntering = false;
                //divides the total number of grade points by the total classes
                cumulativeGPA /= totalClasses;
                System.out.println("------------------------------");
                System.out.println("Your overall GPA is " + truncateTwoDecimal(cumulativeGPA));
            }
            else {
                //stores the number of classes for a specific grade
                int tempClasses = Integer.parseInt(input);
                //saves total number of classes user inputs by storing each tempClasses input
                totalClasses += tempClasses;

                System.out.print("Enter the grade received: ");
                //temporarily stores user input
                input = keyboard.nextLine();

                //adds the number of grade points earned based on Penn State University grading scale
                if (Objects.equals(input, "A")) {
                    cumulativeGPA += (tempClasses * 4.0);
                }
                if (Objects.equals(input, "A-")) {
                    cumulativeGPA += (tempClasses * 3.67);
                }
                if (Objects.equals(input, "B+")) {
                    cumulativeGPA += (tempClasses * 3.33);
                }
                if (Objects.equals(input, "B")) {
                    cumulativeGPA += (tempClasses * 3.0);
                }
                if (Objects.equals(input, "B-")) {
                    cumulativeGPA += (tempClasses * 2.67);
                }
                if (Objects.equals(input, "C+")) {
                    cumulativeGPA += (tempClasses * 2.33);
                }
                if (Objects.equals(input, "C")) {
                    cumulativeGPA += (tempClasses * 2.0);
                }
                if (Objects.equals(input, "D")) {
                    cumulativeGPA += (tempClasses * 1.0);
                }
                if (Objects.equals(input, "F")) {
                    cumulativeGPA += (tempClasses * 0.0);
                }




            }
        }

    }

    public static void semesterGPA() {
        double semGPA = 0.0;

        //prompts user to input total number of classes this semester
        System.out.print("Enter the number of classes this semester: ");
        input = keyboard.nextLine();
        //stores number of classes this semester
        int numClasses = Integer.parseInt(input);

        for (int i = 1; i <= numClasses; i++) {
            //prompts user for GPA for each class
            System.out.print("Enter the GPA of class " + i + ": ");
            input = keyboard.nextLine();
            //adds user input to semGPA variable
            semGPA += Double.parseDouble(input);
        }
        //calculates average by dividing total grade points by number of classes
        semGPA /= numClasses;
        //truncates semGPA to two decimal places
        semGPA = truncateTwoDecimal(semGPA);

        System.out.println("------------------------------");
        //prints semester GPA
        System.out.println("Your semester GPA is " + semGPA);


    }

    public static void finalGPA() {
        //prompt user for current grade in class
        System.out.print("Enter your current grade: ");
        input = keyboard.nextLine();
        //store current grade as decimal
        double currentGrade = Double.parseDouble(input) * .01;

        //prompt user for % of assignments left
        System.out.print("Enter the % of assignments left: ");
        input = keyboard.nextLine();
        //store grade left as decimal
        double gradeLeft = Double.parseDouble(input) * .01;

        //prompt user for desired grade
        System.out.print("Enter the % grade desired: ");
        input = keyboard.nextLine();
        //store desired grade as decimal
        double desiredGrade = Double.parseDouble(input) * .01;

        //formula for final GPA
        double required = ((desiredGrade - (currentGrade * (1 - gradeLeft))) / gradeLeft);
        //change two displayed variables to percentage
        required *= 100;
        desiredGrade *= 100;

        System.out.println("------------------------------");

        //print grade required to get desired grade
        System.out.println("You need at least " + truncateTwoDecimal(required) + "% on the remaining assignments " +
                "to get a " + truncateTwoDecimal(desiredGrade) + "% overall");
    }

    //truncates doubles to two decimal places
    public static double truncateTwoDecimal(double unroundedNum) {
        int truncatedNumInt = (int)(unroundedNum * Math.pow(10,2));
        return truncatedNumInt / Math.pow(10,2);
    }

}
