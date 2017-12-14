/* Good Work
 * score 18
 * you are setting spades as shortest even though it contains Ace or queen or king. check the requirements. You total computed points are equaling to 25 which should be equal to 26.
 */
package com.project2;

public class GameDriver {

    public static void main(String[] args) {
        Game game = new Game(5);
        game.playAGame();
    }
}
