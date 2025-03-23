///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title:           Password Strength
//
// Author:          Jonathan Pufall
// Email:           your jpufall@wisc.edu
//
///////////////////////////////// CITATIONS ////////////////////////////////////
//
// No help was used in the creation of this code
//
///////////////////////////////// REFLECTION ///////////////////////////////////
//
// 1. Intended Problem to Solve:
//    - I designed this code to analyze the user's password to determine whether
//      the password is strong or weak based on certain criteria.
// 2. Biggest Challenges:
//    - The biggest challenge I faced was figuring out how to execute the
//      different methods in my code, using the array I established in the main
//      method.
// 3. What I Learned:
//    - I learned how to successfully implement an array into my code and use
//      it in various methods.
//
/////////////////////////////// 80 COLUMNS WIDE ////////////////////////////////

import java.util.Scanner;

/**
 * This class holds the entirety of the code needed to analyze the users password
 * and output the validation result.
 *
 * @author Jonathan Pufall
 */
public class PasswordStrength {

    /**
     * This main method prompts the user for input, reads that input, and then
     * analyzes the password by calling separate methods for validation.
     *
     * @param args unused
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter password: (must contain at least 10 characters, " +
                "one uppercase letter, one lowercase letter, and one number)");

        //Defines variables
        String userInput; //User's password
        int i; //Variable used in for-loop

        //Ensures password was entered and prints message if no input
        if (input.hasNext()) {
            userInput = input.nextLine(); //Assigns userInput with input entered
            //Ensures password contains no spaces and prints message if it does
            if (userInput.contains(" ")) {
                System.out.println("Invalid password: contains space(s)");
                return;
            }
        } else {
            System.out.print("No password provided");
            return;
        }

        //Closes scanner
        input.close();

        //Creates new array matching the index to number of characters in the password
        char[] userPassword = new char[userInput.length()];

        //Replaces null values in array with password characters
        for (i = 0; i < userInput.length(); i++) {
            userPassword[i] = userInput.charAt(i);
        }

        //Prints output and calls methods for password validation
        if (checkCharCount(userPassword) && checkUppercaseLetterRequirement(userPassword)
                && checkLowercaseLetterRequirement(userPassword)
                && checkNumberRequirement(userPassword)) {
            System.out.print("Strong password");
        } else {
            System.out.print("Weak password");
        }
        //interacts with the user and calls methods that you write
        //The separate TestH8CustomApp.java file contains test cases
        //called from a testH8CustomApp() method that demonstrate at least
        //one of your calculation methods is working correctly.

    }

    /**
     * This method ensures the user's password is at least 10 characters long and
     * outputs true if it is or false if it isn't.
     *
     * @param password (User's password from input)
     * @return (true if meets criteria and false if it doesn't)
     */
    public static boolean checkCharCount(char[] password) {
        boolean result;

        result = password.length >= 10;

        return result;
    }

    /**
     * This method ensures the user's password contains an uppercase letter and outputs
     * true if it does or false if it doesn't.
     *
     * @param password (User's password from input)
     * @return (true if meets criteria and false if it doesn't)
     */
    public static boolean checkUppercaseLetterRequirement(char[] password) {
        boolean result;
        int upperCaseletterCount = 0;
        int i;

        for (i = 0; i < password.length; i++) {
            if (Character.isUpperCase(password[i])) {
                upperCaseletterCount++;
            }
        }

        result = upperCaseletterCount >= 1;

        return result;
    }

    /**
     * This method ensures the user's password contains a lowercase letter and outputs
     * true if it does or false if it doesn't.
     *
     * @param password (User's password from input)
     * @return (true if meets criteria and false if it doesn't)
     */
    public static boolean checkLowercaseLetterRequirement(char[] password) {
        boolean result;
        int lowerCaseletterCount = 0;
        int i;

        for (i = 0; i < password.length; i++) {
            if (Character.isLowerCase(password[i])) {
                lowerCaseletterCount++;
            }
        }

        result = lowerCaseletterCount >= 1;

        return result;
    }

    /**
     * This method ensures the user's password contains a number and outputs
     * true if it does or false if it doesn't.
     *
     * @param password (User's password from input)
     * @return (true if meets criteria and false if it doesn't)
     */
    public static boolean checkNumberRequirement(char[] password) {
        boolean result;
        int numberCount = 0;
        int i;

        for (i = 0; i < password.length; i++) {
            if (Character.isDigit(password[i])) {
                numberCount++;
            }
        }

        result = numberCount >= 1;

        return result;
    }
}