package tests;

import main.Game;
import main.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    private Player player;
    private Game game;

    @BeforeEach
    void setUp() {
        Game game = new Game();
    }

    @AfterEach
    void tearDown() {
    }

    // Scenario 1: Player requests letters secret code
    @Test
    public void testRequestLetters() {
        // Given
        game.setWords(new String[]{"soul", "bald", "orange"});

        // When
        String secretCode = game.requestCode(true);

        // Then
        assertNotNull(secretCode);
        assertTrue(secretCode.matches("[a-zA-Z]+"));
        assertEquals(1, player.getCodesAttempted());
    }

    // Scenario 2: Player requests numbers secret code
    @Test
    public void testRequestNumbers() {
        // When
        String secretCode = game.requestCode(false);

        // Then
        assertNotNull(secretCode);
        assertTrue(secretCode.matches("\\d+"));
        assertEquals(1, player.getCodesAttempted());
    }

    // Scenario 3: Player requests letters secret code but no phrases file exists
    @Test
    public void testRequestLettersNoFile() {
        // Given
        game.setWords(new String[]{});

        // When
        String errorMessage = game.requestCode(true);

        // Then
        assertEquals("Error: No words available. Exiting the game.", errorMessage);
    }

    // Scenario 4: Player enters a guess
    @Test
    public void testPlayerEntersGuess() {
        // Given
        game.setWords("bald");

        // When
        String result = game.enterGuess("balb");

        // Then
        assertEquals("Bulls: 3, Cows: 1", result);
        assertEquals(1, player.getCodesAttempted());
    }

    // Scenario 5: Player enters the correct guess and successfully deciphers the code
    @Test
    public void testPlayerEntersCorrectGuess() {
        // Given
        game.setWords("bald");

        // When
        String result = game.enterGuess("bald");

        // Then
        assertEquals("Congratulations! You've successfully deciphered the code.", result);
        assertEquals(1,player.getCodesDeciphered());
    }

    // Scenario 6: Player enters a guess with an invalid length
    @Test
    public void testInvalidLengthGuess() {
        // Given
        game.setWords("bald");

        // When
        String errorMessage = game.enterGuess("ba");

        // Then
        assertEquals("Error: Invalid guess length. Please try again.", errorMessage);
    }

    // Scenario 7: Player enters an invalid guess for a letters code
    @Test
    public void testInvalidLettersGuess() {
        // Given
        game.setWords("bald");

        // When
        String errorMessage = game.enterGuess("1234");

        // Then
        assertEquals("Error: Invalid guess. Guess should only contain letters.", errorMessage);
    }

    // Scenario 8: Player enters an invalid guess for a numbers code
    @Test
    public void testInvalidNumbersGuess() {
        // Given
        game.setWords("1234");

        // When
        String errorMessage = game.enterGuess("bald");

        // Then
        assertEquals("Error: Invalid guess. Guess should only contain numbers.", errorMessage);
    }

    // Scenario 9: Player wants to undo a single letter/number in the guess
    @Test
    public void testUndoSingleLetterOrNumber() {
        // Given
        game.setWords("bald");
        game.enterGuess("balb");

        // When
        String updatedGuess = game.undoGuess('b');

        // Then
        assertEquals("bal", updatedGuess);
    }

    // Scenario 10: Player wants to undo a single letter/number in the guess when the player hasn’t guessed yet
    @Test
    public void testUndoSingleLetterOrNumberNoGuessYet() {
        // Given
        game.setWords("bald");

        // When
        String errorMessage = game.undoGuess('d');

        // Then
        assertEquals("Error: No guess made yet. Please make a guess first.", errorMessage);
    }

    // Scenario 11: Player wants to undo an invalid letter/number in the guess
    @Test
    public void testUndoInvalidLetterOrNumber() {
        // Given
        game.setWords("bald");
        game.enterGuess("balb");

        // When
        String errorMessage = game.undoGuess('z');

        // Then
        assertEquals("Error: Invalid letter/number to undo. Please try again.", errorMessage);
    }

    @Test
    void game() {
    }

    @Test
    void getHint() {
    }

    @Test
    void loadPlayer() {
    }

    @Test
    void playGame() {
    }

    @Test
    void requestCode() {
        String secretCode = game.requestCode();
        assertNotNull(secretCode);
        assertEquals(4,secretCode.length());
    }

    @Test
    void enterGuess() {
    }

    @Test
    void undoGuess() {
    }

    @Test
    void saveGame() {
    }

    @Test
    void loadGame() {
    }

    @Test
    void showSolution() {
    }
}