package org.fventura.whattheflip;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Franky Ventura
 * @studentID 0816694
 * @date June 27th 2025
 * This class handles the core logic of the memory matching game.
 * It creates the card deck, tracks flipped cards, checked for matches,
 * counts missed attempts, and determines when the game is over.
 */

public class GameLogic {
    private List<Card> deck = new ArrayList<>(); // Shuffled list of all cards in the game
    private List<Card> flippedCards = new ArrayList<>(); // Stores up to 2 currently flipped cards
    private int misses = 0; // Counts failed matches

    public GameLogic(String theme) {
        // This creates and shuffles the deck
        createDeck(theme);
        Collections.shuffle(deck);
    }

    // Creates 8 pairs of cards and adds them to the deck
    private void createDeck(String theme) {
        deck.clear(); // Clear old cards

        String[] names;

        if (theme.equals("fruit")) {
            names = new String[] {
                    "apple", "blackberry", "dragonfruit", "orange",
                    "lemon", "pear", "strawberry", "watermelon"
            };
        } else {
            names = new String[] {
                    "bass", "catfish", "clams", "crab",
                    "flounder", "lobster", "squid", "swordfish"
            };
        }

        for (String name : names) {
            String filePath = "cards/" + name + ".png";
            // Add first and second copy of each card to the deck
            deck.add(new Card(name, filePath));
            deck.add(new Card(name, filePath));
        }
    }

    // Returns the full deck
    public List<Card> getDeck() {
        return deck;
    }

    // This checks if a card can be flipped (not already matched, not already flipped, and less than 2 flipped cards)
    public boolean canFlip(Card card) {
        return !card.isMatched() && !flippedCards.contains(card) && flippedCards.size() < 2;
    }

    // Adds a card to the list of flipped cards
    public void flip(Card card) {
        flippedCards.add(card);
    }

    // Returns true if exactly 2 cards have been flipped
    public boolean isReadyToCheck() {
        return flippedCards.size() == 2;
    }

    // Checks if the two flipped cards are a match and updates the game state
    public boolean checkMatch() {
        if (isMatch()) {
            markMatched();
            return true;
        } else {
            misses++;
            return false;
        }
    }

    // Returns true if the two flipped cards have the same name
    public boolean isMatch() {
        if (flippedCards.size() != 2) return false;
        return flippedCards.get(0).getName().equals(flippedCards.get(1).getName());
    }

    // Marks the currently flipped cards as matched
    public void markMatched() {
        for (Card card : flippedCards) {
            card.setMatched(true);
        }
    }

    // Returns the current list of flipped cards
    public List<Card> getFlippedCards() {
        return flippedCards;
    }

    // Clears the flipped cards list (usually after checking if there was a match)
    public void clearFlipped() {
        flippedCards.clear();
    }

    // Returns the number of failed match attempts (misses)
    public int getMisses() {
        return misses;
    }

    // Returns true if all cards in the deck have been matched
    public boolean isGameOver() {
        for (Card card : deck) {
            if (!card.isMatched()) {
                return false;
            }
        } return true;
    }
}
