package org.fventura.whattheflip;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameLogic {
    private List<Card> deck = new ArrayList<>();
    private List<Card> flippedCards = new ArrayList<>();
    private int misses = 0;

    public GameLogic() {
        createDeck();
        Collections.shuffle(deck);
    }

    private void createDeck() {
        String[] names = {
                "bass", "catfish", "clams", "crab",
                "flounder", "lobster", "squid", "swordfish"
        };

        for (String name : names) {
            String filePath = "cards/" + name + ".png";
            deck.add(new Card(name, filePath));
            deck.add(new Card(name, filePath));
        }
    }

    public List<Card> getDeck() {
        return deck;
    }

    public boolean canFlip(Card card) {
        return !card.isMatched() && !flippedCards.contains(card) && flippedCards.size() < 2;
    }

    public void flip(Card card) {
        flippedCards.add(card);
    }

    public boolean isReadyToCheck() {
        return flippedCards.size() == 2;
    }

    public boolean checkMatch() {
        if (isMatch()) {
            markMatched();
            return true;
        } else {
            misses++;
            return false;
        }
    }

    public boolean isMatch() {
        if (flippedCards.size() != 2) return false;
        return flippedCards.get(0).getName().equals(flippedCards.get(1).getName());
    }


    public void markMatched() {
        for (Card card : flippedCards) {
            card.setMatched(true);
        }
    }

    public List<Card> getFlippedCards() {
        return flippedCards;
    }

    public void clearFlipped() {
        flippedCards.clear();
    }

    public int getMisses() {
        return misses;
    }

    public boolean isGameOver() {
        for (Card card : deck) {
            if (!card.isMatched()) {
                return false;
            }
        } return true;
    }
}
