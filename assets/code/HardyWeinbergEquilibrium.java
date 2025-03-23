///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title:           Hardy-Weinberg Equilibrium
//
// Author:          Jonathan Pufall
// Email:           jmpufall@gmail.com
//
///////////////////////////////// CITATIONS ////////////////////////////////////
//
// https://bio.libretexts.org/Bookshelves/Introductory_and_General_Biology/
// Book%3A_General_Biology_(Boundless)/19%3A_The_Evolution_of_Populations/
// 19.01%3A_Population_Evolution/19.1C%3A_Hardy-Weinberg_Principle_of_Equilibrium;
// Defined Hardy-Weinberg Equilibrium principle and equation.
//
///////////////////////////////// REFLECTION ///////////////////////////////////
//
// 1. Intended Problem to Solve
//    - I designed this program to solve for the different variables in the
//      Hardy-Weinberg equilibrium equation, an equation commonly used in
//      genetics to calculate the genetic variation of a population at
//      equilibrium. This is useful because you can derive the expected
//      genetic outcomes given a change in genotype or allele frequency.
// 2. Biggest Challenge I Encountered
//    - The biggest challenge I encountered with this program was figuring
//      out what variables to use and how to logically proceed with breaking
//      down the Hardy-Weinberg equilibrium equation so that it was most
//      simplistic and easy to read.
// 3. What I Learned
//    - I learned how to create a program from start to finish that solves a
//      problem of my choosing without any examples or guidelines. I also
//      learned how to successfully implement methods in my program for
//      efficiency, and how to adapt my program for potential errors.
//
/////////////////////////////// 80 COLUMNS WIDE ////////////////////////////////

import java.util.Scanner;
import java.lang.Math;

/**
 * This class holds the entirety of the code needed to use the Hardy-Weinberg
 * equation and output every unknown variable.
 *
 * @author Jonathan Pufall
 */
public class HardyWeinbergEquilibrium {
    /**
     * This main method prompts the user for input, reads that input, and then
     * provides an accurate solution to the Hardy-Weinberg equilibrium equation
     * by calling separate methods for calculations.
     *
     * @param args unused
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        //Defines variables
        String alleleType; //Type of allele (either dominant or recessive)
        double inputFrequency; //Frequency of allele from user input
        double dominantAlleleFrequency; //Frequency of dominant allele in population
        double recessiveAlleleFrequency; //Frequency of recessive allele in population

        //Prompts user for input
        System.out.println("Enter the allele type (either \"Dominant\" or \"Recessive\")" +
                " followed by a space and the allele frequency as a percentage: ");
        System.out.println("Ex.) \"Recessive 0.23\"");

        //Ensures input was entered and prints out a message if there is no input
        if (input.hasNext()) {
            alleleType = input.next(); //Assigns alleleType with input entered
            //Ensures user entered either "Dominant" or "Recessive" and prints message if not
            if (!alleleType.equals("Dominant") && !alleleType.equals("Recessive")) {
                System.out.println("Invalid input");
                return;
            }
        } else {
            System.out.println("Nothing was entered");
            return;
        }

        //Ensures value was entered and prints out message if no value was input
        if (input.hasNextDouble()) {
            inputFrequency = input.nextDouble(); //Assigns inputFrequency with value input
            //Checks if input frequency is between 0 and 1 and prints message if not
            if (inputFrequency < 0.0 || inputFrequency > 1.0) {
                System.out.println("Frequency must be between 0 and 1");
                return;
            }
        } else {
            System.out.println("Invalid input");
            return;
        }

        //Closes scanner
        input.close();

        // Calculates unknown allele frequency to four decimal places
        if (alleleType.equals("Dominant")) {
            dominantAlleleFrequency = inputFrequency;
            //Calculates recessive allele frequency from equation p + q = 1
            recessiveAlleleFrequency = Math.round((1.0 - dominantAlleleFrequency)
                    * 1000) / 1000.0;
        } else {
            recessiveAlleleFrequency = inputFrequency;
            //Calculates dominant allele frequency from equation p + q = 1
            dominantAlleleFrequency = Math.round((1.0 - recessiveAlleleFrequency)
                    * 1000) / 1000.0;
        }


        //Prints output and calls methods for value calculations
        System.out.println("Dominant allele frequency (p): "
                + dominantAlleleFrequency);
        System.out.println("Recessive allele frequency (q): "
                + recessiveAlleleFrequency);
        System.out.println("Homozygous dominant genotype frequency (p^2): "
                + computeHomoDominantFrequency(dominantAlleleFrequency));
        System.out.println("Heterozygous genotype frequency (2pq): "
                + computeHeteroFrequency(dominantAlleleFrequency, recessiveAlleleFrequency));
        System.out.println("Homozygous recessive genotype frequency (q^2): "
                + computeHomoRecessiveFrequency(recessiveAlleleFrequency));
    }

    /**
     * This method calculates the homozygous dominant genotype frequency using
     * the Hardy-Weinberg equilibrium equation (p^2 + 2pq + q^2 = 1). The
     * homozygous dominant genotype corresponds to p^2 in the equation, so
     * this method squares the dominant allele frequency.
     *
     * @param dominantAlleleFrequency (Frequency of dominant allele in population)
     * @return (Frequency of homozygous dominant genotype in population to four decimal places)
     */
    public static double computeHomoDominantFrequency(double dominantAlleleFrequency) {
        double homoDominantGenotypeFrequency;
        homoDominantGenotypeFrequency = Math.round(Math.pow(dominantAlleleFrequency,
                2.0) * 10000) / 10000.0;
        return homoDominantGenotypeFrequency;
    }

    /**
     * This method calculates the heterozygous genotype frequency using
     * the Hardy-Weinberg equilibrium equation (p^2 + 2pq + q^2 = 1). The
     * heterozygous genotype corresponds to 2pq in the equation, so this
     * method multiplies the product of recessive and dominant allele
     * frequencies by 2.
     *
     * @param dominantAlleleFrequency  (Frequency of dominant allele in population)
     * @param recessiveAlleleFrequency (Frequency of recessive allele in population)
     * @return (Frequency of heterozygous genotype in population to four decimal places)
     */
    public static double computeHeteroFrequency(double dominantAlleleFrequency,
                                                double recessiveAlleleFrequency) {
        double heteroGenotypeFrequency;
        heteroGenotypeFrequency = Math.round((2.0 * dominantAlleleFrequency *
                recessiveAlleleFrequency) * 10000) / 10000.0;
        return heteroGenotypeFrequency;
    }

    /**
     * This method calculates the homozygous recessive genotype frequency using
     * the Hardy-Weinberg equilibrium equation (p^2 + 2pq + q^2 = 1). The
     * homozygous recessive genotype corresponds to q^2 in the equation, so
     * this method squares the recessive allele frequency.
     *
     * @param recessiveAlleleFrequency (Frequency of recessive allele in population)
     * @return (Frequency of homozygous recessive genotype in population to four decimal places)
     */
    public static double computeHomoRecessiveFrequency(double recessiveAlleleFrequency) {
        double homoRecessiveGenotypeFrequency;
        homoRecessiveGenotypeFrequency = Math.round(Math.pow(recessiveAlleleFrequency,
                2.0) * 10000) / 10000.0;
        return homoRecessiveGenotypeFrequency;
    }
}

