package api;

import engine.UserInstruction;
import engine.backend.GameStatus;
import grid.Grid;
import player.Player;

/**
 * The game instance interface
 * @author Aninda Manocha
 */

public interface IGameInstance {

	/**
	 * Gets the player character
	 * @return the player character
	 */
	public Player getPlayer();

	/**
	 * Gets the current grid that the player is located in
	 * @return the current grid
	 */
	public Grid getGrid();

	/**
	 * Gets the score of the game
	 * @return the game
	 */
	public int getScore();

	/**
	 * Gets the status of the game
	 * @return the status
	 */
	public GameStatus getGameStatus();

	/**
	 * Moves the player based on the user input
	 * @param input - the user input
	 */
	public void movePlayer(UserInstruction input);
}
