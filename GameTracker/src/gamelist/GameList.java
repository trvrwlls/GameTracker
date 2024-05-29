package gamelist;

import java.util.ArrayList;


/**
 * Parent class
 * Stores ArrayLists of Games
 * Methods Normally manipulate said ArrayLists
 */
public class GameList {


    private ArrayList<Game> listOfGames;


    /**
     * Constructor
     * used to initialize an ArrayList
     */
    public GameList() {
        listOfGames = new ArrayList<Game>();
        //sizeOfList = 0;
    }




    /**
     * @param game
     * Adds a game to a GameList
     */
    public void addGame(Game game) {
        listOfGames.add(game);
    }

    /**
     * @param title (String)
     * @return output (String)
     * This method will remove games base on the title given in the parameters and will return the output of if the game was removed
     *
     */
    public void removeGame(String title) {
        Game gameToDelete=null;
        gameToDelete = getGameByTitle(title);
        listOfGames.remove(gameToDelete);
    }


    /**
     *
     * @method removeGame
     * @param game (Game)
     *
     * If the game exits in the list of games this method will remove the game from our list
     */
    public void removeGame(Game game) {
		if (listOfGames.contains(game)) {
        listOfGames.remove(game);
		}
    }



    /**
     * @method clearList
     * Will clear an entire listOfGames
     */
    public void clearList() {
        listOfGames.clear();
    }

    /***
     *
     * @method gameListTitleToString
     * @return output (String)
     */
    public String titlesToString() {

        String output = "";

        final String  COMASPACE = ", ";
        int count = 0;
        int size = listOfGames.size();

        for (Game game : listOfGames) {
            output = output + game.getTitle();

            if (count != size-1) {
                output += COMASPACE;
            }

            count +=1;
        }
        return output;

    }


    /** This will be an edit to instead get and return a game it will get and return information about a game
     * @method getGameInformationToString
     * @param title (String)
     * @return output (String)
     *
     * This method will take a title, search for a game with a matching title in the list and return the qualities of the game
     */
    public String getGameInformationToString(String title) {

        String output = "No game was found";

        for (Game game : listOfGames) {
            if (game.getTitle().equals(title)) {
                output = game.qualitiesToString();
            }
        }
        return output;
    }


    /**
     *
     * @param title
     * @return
     * If given the title of the game it will return the game
     */
    protected Game getGameByTitle(String title) {
        for (Game game : listOfGames) {
            if (game.getTitle().equals(title)) {
                return game;
            }
        }
        return null;
    }



    /**
     * @return the size of a GameList
     */
    public int getSizeOfList() {
        return listOfGames.size();
    }

    /**
     *
     * @return listOfGames
     */
    public ArrayList<Game> getListOfGames() {
        // this needs to be adjusted so it sends a deep copy of this

        return listOfGames;
    }

    /**
     *
     * @param title
     * @return true if the game exists in the GameList or false if the game does not exist
     */
    public boolean hasGame(String title) {
        for (Game game : listOfGames) {
            if (game.getTitle().equals(title)) {
                return true;
            }
        }
        return false;
    }

}
