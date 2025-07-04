package org.fventura.whattheflip;

public class Card {
    private String name;
    private String imagePath;
    private boolean isMatched = false;

    // Stores the deck, with images of each card
    public Card(String name, String imagePath){
        this.name = name;
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public boolean isMatched() {
        return isMatched;
    }

    public void setMatched(boolean matched) {
        this.isMatched = matched;
    }




}
