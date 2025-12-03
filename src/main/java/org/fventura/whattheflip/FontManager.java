package org.fventura.whattheflip;

import javafx.scene.text.Font;

/**
 * @author Franky Ventura
 * @studentID 0816694
 * @date July 10th 2025
 * This class is to store and manage our two different fonts (Dynapuff and Raleway)
 * We use these fonts in a variation of different sizes, some are pane specific (creditsFont, gameWinPopupFont) as we
 * needed to create different font variables for different font sizes
 */

public class FontManager {
    public static Font titleFont;
    public static Font textFont;
    public static Font youWonFont;
    public static Font creditsFont;
    public static Font authorFont;
    public static Font gameWinPopupFont;

    public static void loadFonts() {
        titleFont = Font.loadFont(FontManager.class.getResourceAsStream("/fonts/Dynapuff.ttf"), 80);
        textFont = Font.loadFont(FontManager.class.getResourceAsStream("/fonts/Raleway.ttf"), 20);
        creditsFont = Font.loadFont(FontManager.class.getResourceAsStream("/fonts/Raleway.ttf"), 32);
        authorFont = Font.loadFont(FontManager.class.getResourceAsStream("/fonts/Dynapuff.ttf"), 60);
        gameWinPopupFont = Font.loadFont(FontManager.class.getResourceAsStream("/fonts/Raleway.ttf"), 28);
        youWonFont = Font.loadFont(FontManager.class.getResourceAsStream("/fonts/Raleway.ttf"), 80);
    }
}
