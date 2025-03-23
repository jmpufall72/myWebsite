///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title:           Rewritten Romeo and Juliet
//
// Author:          Jonathan Pufall
// Email:           jpufall@wisc.edu
//
///////////////////////////////// CITATIONS ////////////////////////////////////
//
// No help used with the creation of this code.
//
///////////////////////////////// REFLECTION ///////////////////////////////////
//
// 1. Describe the problem you wrote the program to solve:
//    - I wrote this code to read the Romeo and Juliet file, and then write a new
//      version of it with the names "Romeo" and "Juliet" replaced with names of
//      the users choosing.
// 2. Why did you choose the method header for the read file method (e.g., return type,
//    parameters, throws clause)?
//    - I chose the name readRomeoAndJuliet because it essentially describes the entire
//      method. It uses the file from the main method which is passed as a parameter.
//      The return type is an ArrayList because in order to manipulate the data from the
//      file, I need to first allocate it into memory which can be manipulated.
//      Exceptions are then handled within the method itself.
// 3. Why did you choose the method header for the write file method (e.g., return type,
//    parameters, throws clause)?
//    - For the write method, I chose the name rewriteRomeoAndJuliet because it accurately
//      describes the method's purpose which is to manipulate the file data and write a
//      new file from that. The return type is a boolean because it either returns true
//      or false to the main method depending on if the file was created successfully to
//      let the user know. The main product of the method is the new file, so returning a
//      confirmation of that is useful. I chose the parameters by assuring the method
//      has everything it needs to rewrite the initial data in the newly created
//      ArrayList from the read method. This includes the file that is established in the
//      main method. The user input is also passed through for successful data manipulation.
//      Just like the read method, the exceptions are handled within the method.
// 4. What are the biggest challenges you encountered:
//    - The biggest challenge I encountered was creating the test cases so that they
//      would work with the parameters I established in my code.
// 5. What did you learn from this assignment:
//    - I learned how to successfully read from and write to files, as well as
//      how to handle exceptions relating to file manipulation.
//
/////////////////////////////// 80 COLUMNS WIDE ////////////////////////////////

import java.io.File;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * This class contains the entirety of the code needed to read the Romeo and Juliet text file
 * and write a new text file where all the instances of "Romeo" and "Juliet" are replaced with
 * the user's chosen names.
 *
 * @author Jonathan Pufall
 */
public class RewrittenRomeoAndJuliet {

    /**
     * This method reads the Romeo and Juliet file and transfers most of it into an ArrayList
     * which can be manipulated later.
     *
     * @param romeoAndJuliet The file from which the code reads from
     * @return ArrayList which contains the contents of the file line by line
     */
    public static ArrayList<String> readRomeoAndJuliet(File romeoAndJuliet) {
        //Declares and initiates variable
        boolean startedReading = false;

        //Creates new ArrayList to store data from file
        ArrayList<String> textFromFile = new ArrayList<>();

        try {
            //Opens scanner to read from file
            Scanner readFile = new Scanner(romeoAndJuliet);

            while (readFile.hasNextLine()) {
                String line = readFile.nextLine();

                //Establishes start of segment that is read from the file
                if (!startedReading && line.contains("THE TRAGEDY OF ROMEO AND JULIET")) {
                    startedReading = true; // Set flag to true to start reading
                }

                //Establishes end of segment that is read from the file
                if (line.contains("*** END OF THE PROJECT GUTENBERG EBOOK ROMEO AND JULIET ***")) {
                    break; // Exit the loop
                }

                //Add lines to the list if the code has started reading
                if (startedReading) {
                    textFromFile.add(line);
                }
            }

            //Closes scanner
            readFile.close();
            //Catches the FileNotFoundException and print out error message
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + romeoAndJuliet);
        }

        return textFromFile;
    }

    /**
     * This method replaces every instance of "Romeo" and "Juliet" with the user's
     * chosen names. Then, it writes a new file using the data from the established
     * ArrayList.
     *
     * @param rewrittenRomeoAndJuliet The file to which the code writes to
     * @param romeoAndJuliet          The ArrayList that holds the data to be written to new file
     * @param romeoReplacement        The user's chosen name to replace "Romeo" in the new file
     * @param julietReplacement       The user's chosen name to replace "Juliet" in the new file
     * @return true if the file was created, false otherwise.
     */
    public static boolean rewriteRomeoAndJuliet(File rewrittenRomeoAndJuliet,
                                                ArrayList<String> romeoAndJuliet,
                                                String romeoReplacement, String julietReplacement) {
        //Declares and initializes variable
        boolean confirmation = false;

        try (PrintWriter writer = new PrintWriter(rewrittenRomeoAndJuliet)) {
            for (String line : romeoAndJuliet) {
                //Replaces all occurrences of "Romeo" with the new name
                line = line.replaceAll("Romeo", romeoReplacement);
                line = line.replaceAll("ROMEO", romeoReplacement.toUpperCase());
                //Replaces all occurrences of "Juliet" with the new name
                line = line.replaceAll("Juliet", julietReplacement);
                line = line.replaceAll("JULIET", julietReplacement.toUpperCase());
                writer.println(line);
            }
            confirmation = true;

            //Catches the FileNotFoundException and print out error message
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + rewrittenRomeoAndJuliet);
        }

        return confirmation;
    }

    /**
     * This main method established files used in this program, prompts user for input, and
     * uses the input to manipulate the data in a file to create a new one by calling separate
     * methods.
     *
     * @param args unused
     */
    public static void main(String[] args) {

        //Establishes file to be read from
        File romeoAndJuliet = new File("RomeoAndJuliet.txt");
        //Establishes file to be written to
        File rewrittenRomeoAndJuliet = new File("RomeoAndJuliet-Rewritten.txt");

        //Opens scanner
        Scanner input = new Scanner(System.in);

        //Prompts user for input
        System.out.println("Input two names to replace \"Romeo\" and \"Juliet\"" +
                " separated by a space");
        System.out.println("Example: \"Jack Jill\" would replace \"Romeo\" " +
                "with \"Jack\" and \"Juliet\" with \"Jill\"");

        //Declares and initializes variables
        String romeoReplacement = null;
        String julietReplacement = null;

        //Ensures user entered something
        if (input.hasNext()) {
            //Ensures user entered two names
            if (input.hasNext()) {
                romeoReplacement = input.next(); //Assigns romeoReplacement with first input name
                julietReplacement = input.next(); //Assigns julietReplacement with second input name
            } else {
                System.out.println("Please enter two names separated by a space");
            }
        } else {
            System.out.println("No input entered");
            return;
        }

        //Closes scanner
        input.close();

        //Creates ArrayList and calls readRomeoAndJuliet method to fill in data
        ArrayList<String> textFromFile = readRomeoAndJuliet(romeoAndJuliet);

        //Calls rewriteRomeoAndJuliet method to write new file, and confirms file was created
        boolean confirmation = rewriteRomeoAndJuliet(rewrittenRomeoAndJuliet, textFromFile,
                romeoReplacement, julietReplacement);

        //Informs user of new file creation
        if (confirmation) {
            System.out.println("New file was successfully created");
        } else {
            System.out.println("There was an error creating new file");
        }
    }
}