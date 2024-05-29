package gamelist;

/**
 * Child class of GameList
 * Stores WishList and methods for manipulating WishList
 */
public class WishList extends GameList {




    /**
     * @method beginGame
     * @param title
     * @param currentlyPlayingList
     * moves game from wishlist ot currently playing list
     */
    public void beginGame(String title, CurrentlyPlayingList currentlyPlayingList) {
        Game gameToRemove =null;

        for (Game wishListGame: this.getListOfGames()) {
            if (wishListGame.getTitle().equals(title)) {

                gameToRemove = wishListGame;

            }
        }
        this.removeGame(gameToRemove);
        gameToRemove.startGame();
        currentlyPlayingList.addGame(gameToRemove);
        return;

    }
    /**
     * @method beginGame
     * @param game
     * @param currentlyPlayingList
     * moves game from wishlist ot currently playing list
     */
    public void beginGame(Game game, CurrentlyPlayingList currentlyPlayingList) {
        Game gameToRemove =null;

        for (Game wishListGame: this.getListOfGames()) {
            if (wishListGame.equals(game)) {

                gameToRemove = wishListGame;

            }
        }
        this.removeGame(gameToRemove);
        gameToRemove.startGame();
        currentlyPlayingList.addGame(gameToRemove);
        return;

    }

}