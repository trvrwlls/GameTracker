package gamelist;

/**
 * Child class of GameList
 * Stores CurrentlyPlayingList and methods for manipulating CurrentlyPlayingList
 */
public class CurrentlyPlayingList extends GameList {


	/**
	 *
	 * @param game
	 * @param previouslyPlayedList
	 */
	public void completeGame(Game game, PreviouslyPlayedList previouslyPlayedList) {
		this.removeGame(game);
		game.finishGame();
		previouslyPlayedList.addGame(game);
	}

	/**
	 *
	 * @param title (String)
	 * @param previouslyPlayedList
	 */
	public void completeGame(String title, PreviouslyPlayedList previouslyPlayedList) {

		Game gameToRemove = getGameByTitle(title);

		this.removeGame(gameToRemove);
		gameToRemove.finishGame();
		previouslyPlayedList.addGame(gameToRemove);
	}

}