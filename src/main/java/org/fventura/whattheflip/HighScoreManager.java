package org.fventura.whattheflip;

import java.io.*;

/**
 * @author Franky Ventura
 * @studentID 0816694
 * @date July 2nd 2025
 * This class updates highscore.txt if someone gets fewer misses than the current lowest miss count
 */
public class HighScoreManager {
    private static final String FILE_NAME = "highscore.txt"; // File that saves the high score

    // Get the saved high score, or -1 if no score saved
    public static int getHighScore() {
        // Opens and reads the file
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))){
            // Reads the first line from the file
            String line = reader.readLine();
            // If the line exists, return the integer
            if (line != null) {
                return Integer.parseInt(line); // Converts the line to an integer
            } else {
                // If the file is empty, return -1
                return -1;
            }
        } catch (IOException | NumberFormatException e) {
            // If the file can't be read, or if the number is invalid, return -1
            return -1;
        }
    }

    // Save a new high score if it's lower than the current one
    public static void trySaveHighScore(int misses) {
        int currentHighScore = getHighScore(); // Loads current high score

        // Checks if this is the first score or if it's better than the current high score
        if (currentHighScore == -1 || misses < currentHighScore) {
            // Write the new high score to the file
            try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
                writer.println(misses); // This saves the number of misses
            } catch (IOException e) {
                // If the file cannot be written to, show this error:
                System.out.println("Error saving high score");
                e.printStackTrace();
            }
        }
    }
}
