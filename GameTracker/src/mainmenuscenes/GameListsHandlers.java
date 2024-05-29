package mainmenuscenes;

import gamelist.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import mainmenu.SystemMain;
import userinformation.UserInformation;

import java.io.IOException;

/**
 * Abstract method for the gamelist controllers (i.e WishlistController, CurrentlyPlayingController, PreviouslyPlayedController
 * Contains methods used by all
 * Information is passed between parent and child classes
 */
public abstract class GameListsHandlers {

    protected TableColumn<Game, String> titleColumn;
    protected TableColumn<Game, Integer> anticipationColumn;
    protected TableView<Game> gameListTableView;

    protected UserInformation userInformation;
    protected SystemMain systemMain;
    protected TextField titleTextField;
    protected TextField anticipationTextField;

    protected GameList selectedGameList;


    protected ObservableList<Game> gameListObservableList= FXCollections.observableArrayList();


    /**
     * @param event
     * @throws IOException
     *
     * Simply Removes a selected game from a list
     */
    abstract void handleRemoveGameButton(ActionEvent event);


    /**
     * This Fills the TableView with values from the games in the WishList
     * I used the code from https://www.youtube.com/watch?v=uh5R7D_vFto, as I couldn't find another way to store data within the list
     */
    void fillTableView() {
        fillObservableList();

        titleColumn.setCellValueFactory(new PropertyValueFactory<Game, String>("title"));
        anticipationColumn.setCellValueFactory(new PropertyValueFactory<Game, Integer>("anticipation"));

        gameListTableView.setItems(gameListObservableList);

    }

    /**
     * Stores games within the observable list
     * An ObservableList is a list that can store data on a TableView
     */
    void fillObservableList() {
        for (Game game : selectedGameList.getListOfGames()) {
            gameListObservableList.add(game);
        }
    }


    /**
     *
     * @param wishList
     * @return
     * Updates the file that stores the users wishlist in UserInformation
     */
    protected boolean updateList(WishList wishList) {
        try {
            userInformation.writeUserWishList(wishList);;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    /**
     *
     * @param currentlyPlayingList
     * @return boolean
     * Updates the file that stores the users currentlyPlayingList in UserInformation
     */
    protected boolean updateList(CurrentlyPlayingList currentlyPlayingList) {
        try {
            userInformation.writeUserCurrentlyPlayingList(currentlyPlayingList);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     *
     * @param previouslyPlayedList
     * @return boolean
     * Updates the file that stores the users previouslyPlayedList in UserInformation
     */
    protected boolean updateList(PreviouslyPlayedList previouslyPlayedList) {
        try {
            userInformation.writeUserPreviouslyPlayedList(previouslyPlayedList);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


}
