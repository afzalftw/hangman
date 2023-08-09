Hangman

Introduction:
----------------
The Hangman game is a word guessing game where players attempt to guess a secret word, one letter at a time. For each incorrect guess, an ASCII art representation of a hangman is displayed. The player's goal is to guess the word before the entire hangman is displayed.

Features:
---------
1. Player vs AI Mode: Allows a single player to play against the computer AI.
2. Dual Player Mode: Allows two players to take turns guessing a word.
3. Hangman Graphics: Displays ASCII art hangman graphics representing the number of incorrect guesses made by the player.
4. Try Again: After a game ends, players are prompted to play again or exit the game.

Code Structure:
---------------
The HangMan class contains the main game logic and user interfaces. It includes the following methods:

1. main(String[] args):
   - Entry point of the program.
   - Displays a menu for selecting the game mode: Player vs AI or Dual Player.
   - Calls the menu() method.

2. menu():
   - Displays the main menu with game mode options.
   - Calls playerVsAi() or dualPlayer() based on user choice.

3. playerVsAi():
   - Implements the Player vs AI game mode.
   - Loads a list of words from a file and selects a random word for the game.
   - Calls the play() method to start the game with the selected word.

4. dualPlayer():
   - Implements the Dual Player game mode.
   - Allows two players to take turns entering a word for the other player to guess.
   - Calls the play() method to start the game with the entered word.

5. play(String word):
   - Manages the core gameplay loop for a single game.
   - Displays ASCII art hangman graphics, secret word progress, and prompts for letter guesses.
   - Checks the correctness of guesses and updates the game status accordingly.
   - Ends the game when the player guesses the word or runs out of attempts.
   - Provides an option to play again by calling the tryAgain() method.

6. tryAgain():
   - Asks the player if they want to play another game.
   - Returns true if the player wants to play again, false otherwise.

7. HANGMAN_PICS:
   - An array of strings representing ASCII art for different stages of the hangman.

Usage:
------
1. Run the program.
2. Choose a game mode from the main menu.
3. Follow the on-screen instructions to play the game.
4. After the game ends, decide whether to play again or exit.

Improvements:
-------------
1. The code provides an interactive Hangman game experience with ASCII art.
2. The addition of tryAgain() enhances replayability.
3. User input validation prevents incorrect input and improves user experience.
4. The code is organized into well-defined methods, making it easy to understand and maintain.

