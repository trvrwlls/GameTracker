package gamelist;

/**
 * Child class of GameList
 * Stores PreviouslyPlayedList and methods for manipulating PreviouslyPlayedList
 */
public class PreviouslyPlayedList extends GameList {

    /**
     *
     * @param gameToUpdate
     * @param rating
     * Updates a given game and its new rating
     * does this by calling Game.setRating method
     */
    public void updateRating(Game gameToUpdate, int rating) {
        for (Game game : super.getListOfGames()) {
            if (gameToUpdate == game) {
                game.setRating(rating);
            }
        }
    }
    public void updateRating(String title, int rating) {
        Game selectedGame = getGameByTitle(title);
        selectedGame.setRating(rating);
    }
    /**
     *
     * @param gameToUpdate
     * @param review
     * Updates a given game with its new review
     * Does this by calling Game.setReview method
     */
    public void updateReview(Game gameToUpdate, String review) {
        for (Game game : super.getListOfGames()) {
            if (gameToUpdate == game) {
                game.setReview(review);
            }
        }
    }

    public void updateReview(String title, String review) {
        Game gameToUpdate = getGameByTitle(title);
        gameToUpdate.setReview(review);
    }


}