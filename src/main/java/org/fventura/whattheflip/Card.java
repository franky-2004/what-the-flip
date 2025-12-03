package org.fventura.whattheflip;

/**
 * @author Franky Ventura
 * @studentID 0816694
 * @date June 20th 2025
 * This class represents a card in the memory matching game.
 * Each card has a name, image path, and boolean to track whether it has been matched.
 */

public class Card {
    private String name; // Name of each card (used for matching)
    private String imagePath; // Path to the image used to display each card
    private boolean isMatched = false; // Tracks if a card has already been matched

    // Sets the name and image path of each card
    public Card(String name, String imagePath){
        this.name = name;
        this.imagePath = imagePath;
    }

    // Returns the card's name
    public String getName() {
        return name;
    }

    // Returns the card's image path
    public String getImagePath() {
        return imagePath;
    }

    // Returns true if the card has already been matched
    public boolean isMatched() {
        return isMatched;
    }

    // Sets the card's matched state (true or false)
    public void setMatched(boolean matched) {
        this.isMatched = matched;
    }




}
