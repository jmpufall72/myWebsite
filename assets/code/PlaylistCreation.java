///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title:           Stevie Wonder Shuffled Top 5 Playlist
//
// Author:          Jonathan Pufall
// Email:           your jpufall@wisc.edu
//
///////////////////////////////// CITATIONS ////////////////////////////////////
//
// ChatGTP; Helped in debugging the topFivePlaylist method for iterating
//          through ArrayList.
//
///////////////////////////////// REFLECTION ///////////////////////////////////
//
// 1. Function of the Program:
//    - I wrote this code to display a playlist of songs by an artist, in this
//      case it's Stevie Wonder, and allow the user to select their top 5
//      favorites to be put in a separate shuffled playlist.
// 2. Why did you choose arrays vs ArrayLists? In other words, what are the
//    differences and how did you take those into account?
//    - In my code, I used multiple ArrayLists and 1 Array. When I needed to
//      do more complicated manipulations of data within an Array like adding
//      or switching elements, I used an ArrayList since they are more
//      manipulative. When I had a set number of elements that I just needed
//      storage for, I used an Array since it's more simple.
// 3. How did you decide which test cases to create?
//    - I thought about the most common errors that would make the method fail,
//      and I build the test cases and the tested method simultaneously to
//      create a seamless test program.
// 4. What I Learned:
//    - I learned how to use and manipulate Arrays and ArrayLists, and call them
//      to and from methods. I also learned how to implement test cases, testing
//      a method that processes an ArrayList.
//
/////////////////////////////// 80 COLUMNS WIDE ////////////////////////////////

import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

/**
 * This class contains the entirety of the code needed to make the user's top 5 playlist
 * and shuffle it.
 */
public class PlaylistCreation {

    /**
     * This main method establishes a list of songs, prompts the user to pick five, and
     * calls methods to create the user's playlist and shuffle it. It then prints out
     * the user's shuffled playlist.
     *
     * @param args unused
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        //Creates ArrayList for songs in playlist
        ArrayList<String> initialPlaylist = new ArrayList<>();

        //Adds individual songs to playlist
        initialPlaylist.add("Sir Duke");
        initialPlaylist.add("Superstition");
        initialPlaylist.add("Signed, Sealed, Delivered (I'm Yours)");
        initialPlaylist.add("Higher ground");
        initialPlaylist.add("I Wish");
        initialPlaylist.add("As");
        initialPlaylist.add("For Once in My Life");
        initialPlaylist.add("I Just Called to Say I Love You");
        initialPlaylist.add("Isn't she Lovely");
        initialPlaylist.add("Part Time Lover");

        //Calls for user input
        System.out.println("Listed Below is a playlist with 10 songs by Stevie Wonder:");
        for (int i = 0; i < initialPlaylist.size(); i++) {
            System.out.println((i + 1) + ": " + initialPlaylist.get(i));
        }
        System.out.println("Chose 5 songs to create your top 5 playlist by entering" +
                " the corresponding song numbers.");
        System.out.println("Example: \"1 4 6 8 10\" would give you the songs " +
                initialPlaylist.get(0) +
                ", " + initialPlaylist.get(3) + ", " + initialPlaylist.get(5) + ", etc.");
        System.out.println("Your top 5 playlist will then be shuffled for you!");

        //Creates array for the users chosen removed songs
        int[] chosenSongs = new int[5];

        //Assures user entered something
        if (!input.hasNext()) {
            System.out.println("Nothing was entered");
            return;
        } else {
            for (int i = 0; i < 5; i++) {
                //Assures user entered 5 numbers
                if (input.hasNextInt()) {
                    chosenSongs[i] = input.nextInt() - 1;
                    //Assures user entered a number between 1 and 10
                    if (chosenSongs[i] < 0 || chosenSongs[i] > 9) {
                        System.out.println("Enter numbers between 1 and 10");
                        return;
                    }
                } else {
                    System.out.println("Input must be 5 numbers");
                    return;
                }
            }
        }

        //Closes scanner
        input.close();

        //Creates playlist of top five songs by calling the topFivePlaylist method
        ArrayList<String> userPlaylist = topFivePlaylist(initialPlaylist, chosenSongs);

        //Shuffles top five playlist by calling the shufflePlaylist method
        ArrayList<String> shuffledUserPlaylist = shufflePlaylist(userPlaylist);

        //Prints out the shuffled playlist in an organized list
        System.out.println("Your shuffled top 5 playlist:");
        for (int i = 0; i < shuffledUserPlaylist.size(); i++) {
            System.out.println((i + 1) + ": " + shuffledUserPlaylist.get(i));
        }

    }

    /**
     * This method creates a new playlist based off of the user's song choices
     * and returns that playlist to the main method.
     *
     * @param initialPlaylist (Playlist of 10 Stevie Wonder songs)
     * @param chosenSongs     (Array of 5 songs chosen by user)
     * @return The sum of the numbers in the array.
     */
    public static ArrayList<String> topFivePlaylist(ArrayList<String> initialPlaylist,
                                                    int[] chosenSongs) {

        ArrayList<String> userPlaylist = new ArrayList<>();

        //Handles an empty initial playlist or no songs chosen by the user
        if (initialPlaylist.isEmpty()) {
            return userPlaylist; //Returns an empty playlist
        }

        //Adds user's chosen valid songs to user's playlist
        int songsAdded = 0; //Track the number of songs added
        for (int songIndex : chosenSongs) {
            //Check if the index is within the bounds of the initial playlist
            if (songIndex >= 0 && songIndex < initialPlaylist.size()) {
                userPlaylist.add(initialPlaylist.get(songIndex));
                songsAdded++;

                //Check if the maximum number of requested songs has been added
                if (songsAdded >= 5) {
                    break;
                }
            } else {
                return userPlaylist;
            }
        }

        //Returns user's playlist to main method
        return userPlaylist;
        }

    /**
     * This method shuffles the users top 5 playlist and returns that shuffled
     * playlist to the main method.
     *
     * @param userPlaylist (Playlist of top 5 songs)
     * @return ArrayList of the shuffled top 5 playlist
     */
    public static ArrayList<String> shufflePlaylist(ArrayList<String> userPlaylist) {
        Random random = new Random();

        //Shuffles playlist
        for (int i = userPlaylist.size() - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            String temp = userPlaylist.get(i);
            userPlaylist.set(i, userPlaylist.get(j));
            userPlaylist.set(j, temp);
        }

        //Returns shuffled playlist to main method
        return userPlaylist;
    }
}