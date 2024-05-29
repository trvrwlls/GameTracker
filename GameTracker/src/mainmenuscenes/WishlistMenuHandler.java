package mainmenuscenes;


import gamelist.CurrentlyPlayingList;
import gamelist.Game;
import gamelist.WishList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import mainmenu.SystemMain;
import userinformation.UserInformation;

/**
 * Handler for WishlistController
 * Extends the Abstract class GameListsHandlers
 */
public class WishlistMenuHandler extends GameListsHandlers {


    private WishList wishList;
    private CurrentlyPlayingList currentlyPlayingList;

    private UserInformation userInformation;

    /**
     *
     * @param wishlistController
     * @param userInformation
     * @param systemMain
     * System
     */
    protected WishlistMenuHandler(WishlistController wishlistController, UserInformation userInformation, SystemMain systemMain) {
        super.userInformation = userInformation;
        this.userInformation = userInformation;
        super.systemMain = systemMain;

        super.titleTextField = wishlistController.titleTextField;
        super.anticipationTextField = wishlistController.anticipationTextField;
        super.gameListTableView = wishlistController.wishListTableView;
        super.titleColumn = wishlistController.titleColumn;
        super.anticipationColumn = wishlistController.anticipationColumn;

        wishList = systemMain.getWishlist();
        currentlyPlayingList = systemMain.getCurrentlyPLayingList();

        super.selectedGameList = wishList;

        super.fillTableView();

    }

    /**
     *
     * @param event
     * @return
     * When the AddGameButton is pressed this method will
     * Take data from titleTextField and anticipationTextField
     * Check whether the data is formatted correctly
     * If it is formatted correctly, will add a game to wishlist
     */
    protected boolean handleAddGameButton(ActionEvent event) {
        String title = titleTextField.getText();
        if (title.isBlank()) {
            Alert inputError = new Alert(Alert.AlertType.ERROR);
            inputError.setHeaderText("Empty Field");
            inputError.setContentText("Missing title");
            inputError.showAndWait();
            return false;
        }
        if (title.contains("~")) {
            Alert inputError = new Alert(Alert.AlertType.ERROR);
            inputError.setHeaderText("Contains unusable value");
            inputError.setContentText("Titles cannot contain \"~\" ");
            inputError.showAndWait();
            return false;
        }
        double anticipation;
        try {
            anticipation = Double.parseDouble(anticipationTextField.getText());
            if (! (0 <= anticipation && anticipation <= 10)) {
                Alert inputError = new Alert(Alert.AlertType.ERROR);
                inputError.setHeaderText("Input Error");
                inputError.setContentText("The anticipation must be a number between 0-10");
                inputError.showAndWait();
                return false;
            }
        } catch (NumberFormatException e) {
            Alert inputError = new Alert(Alert.AlertType.ERROR);
            inputError.setHeaderText("Input Error");
            inputError.setContentText("Anticipation must be a number between 0-10");
            inputError.showAndWait();
            return false;
        }

        Game gameToAdd = new Game(title, anticipation);
        selectedGameList.addGame(gameToAdd);
        gameListObservableList.add(gameToAdd);

        try {
            userInformation.writeUserWishList(wishList);
            return true;
        } catch (Exception e) {
            System.out.println("Something went wrong while saving");
            return false;
        }

    }


    /**
     * Handler for removeGameButton
     * Gets the selected game from the table and removes it from the table as well as wishlist
     */
    @Override
    protected void handleRemoveGameButton(ActionEvent event) {
        Game selectedGame = gameListTableView.getSelectionModel().getSelectedItem();

        if (selectedGame == null) {
            return;
        }

        wishList.removeGame(selectedGame);
        gameListObservableList.remove(selectedGame);



    }

    /**
     *
     * @param event
     * When startGameButton is pressed the method will get the game selected from the TableView
     * start the game and more it into the users currently playing list
     */
    protected void handleStartGameButton(ActionEvent event) {
        Game selectedGame = gameListTableView.getSelectionModel().getSelectedItem();
        if (selectedGame == null) {
            return;
        }
        gameListObservableList.remove(selectedGame);
        wishList.beginGame(selectedGame, currentlyPlayingList);
        updateList(currentlyPlayingList);
        updateList(wishList);
    }



}
