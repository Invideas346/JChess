package wolfg.aig.JCHess.Logic;

import org.lwjgl.glfw.GLFW;

public class Game {
    private Player player1;
    private Player player2;
    private Board board;

    public Game() {
        super();
        /* Get Name of both players */
        init();
    }

    public void init() {
        player1 = new Player("Player1");
        player2 = new Player("Player1");
        board = new Board();
    }

    public void init(String namePlayer1, String namePlayer2) {
        player1 = new Player(namePlayer1);
        player2 = new Player(namePlayer2);
    }

    public void tick() {
        /* Get Input */
        /* React to input (Game logic) */
        /* Queue all render calls */
    }
}