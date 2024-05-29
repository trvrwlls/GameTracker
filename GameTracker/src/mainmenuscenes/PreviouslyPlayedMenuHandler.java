package mainmenuscenes;

import gamelist.Game;
import gamelist.PreviouslyPlayedList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import mainmenu.SystemMain;
import userinformation.UserInformation;

import java.time.LocalDate;

/**
 * Handler for PreviouslyPlayedController
 * Extends the Abstract class GameListsHandlers
 */
public class PreviouslyPlayedMenuHandler extends GameListsHandlers {

    private final TableColumn<Game, Integer> ratingColumn;
    private final TextField ratingTextField;
    private final TextArea reviewTextArea;
    private TableColumn<Game, LocalDate> dateCompletedColumn;

    private PreviouslyPlayedList previouslyPlayedList;



    private TableColumn<Game, LocalDate> dateStartedColumn;


    /**
     *
     * @param previouslyPlayedController
     * @param userInformation
     * @param systemMain
     * Sets data stored in the super class as well as the data stored in this class to proper data from userInformation and the controller class
     */
    public PreviouslyPlayedMenuHandler(PreviouslyPlayedController previouslyPlayedController, UserInformation userInformation, SystemMain systemMain) {

        super.gameListTableView = previouslyPlayedController.previouslyPlayedTableView;
        super.titleColumn = previouslyPlayedController.titleColumn;
        super.anticipationColumn = previouslyPlayedController.anticipationColumn;

        this.dateStartedColumn = previouslyPlayedController.dateStartedColumn;
        this.dateCompletedColumn = previouslyPlayedController.dateCompletedColumn;

        this.ratingColumn = previouslyPlayedController.ratingColumn;

        this.ratingTextField = previouslyPlayedController.ratingTextField;
        this.reviewTextArea = previouslyPlayedController.reviewTextArea;

        super.userInformation = userInformation;

        this.previouslyPlayedList = systemMain.getPreviouslyPlayedList();

        super.selectedGameList = previouslyPlayedList;

        fillTableView();

    }


    /**
     *
     * @param event
     * Gets the rating put in the rating textfield and the selected game and updates the game and the table to show the rating
     * checks whether the data if formatted properly then adds the rating if so
     */
    public void handleAddRatingButton(ActionEvent event) {
        int rating;
        try {
            rating = Integer.parseInt(ratingTextField.getText());
            if (! (0 <= rating && rating <= 10)) {
                Alert inputError = new Alert(Alert.AlertType.ERROR);
                inputError.setHeaderText("Input Error");
                inputError.setContentText("Rating must be an integer between 0-10");
                inputError.showAndWait();
                return;
            }
        } catch (NumberFormatException e) {
            Alert inputError = new Alert(Alert.AlertType.ERROR);
            inputError.setHeaderText("Input Error");
            inputError.setContentText("Rating must be an integer between 0-10");
            inputError.showAndWait();
            return;
        }
        // get the game that is changed
        Game selectedGame = gameListTableView.getSelectionModel().getSelectedItem();
        // remove the uneditted game
        gameListObservableList.remove(selectedGame);
        // set the game's rating
        selectedGame.setRating(rating);
        // update the rating of the game in previously played
        previouslyPlayedList.updateRating(selectedGame, rating);
        // add the updated game to the table
        gameListObservableList.add(selectedGame);

        updateList(previouslyPlayedList);
        ratingTextField.clear();
    }

    /**
     *
     * @param event
     * handler for addReviewbutton
     * Updates a chosen game with a give review
     */
    public void handleAddReviewButton(ActionEvent event) {
        String review = reviewTextArea.getText();

        if (review.contains("~")) {
            Alert inputError = new Alert(Alert.AlertType.ERROR);
            inputError.setHeaderText("Contains unusable value");
            inputError.setContentText("Reviews cannot contain \"~\" ");
            inputError.showAndWait();
            return;
        }
        Game selectedGame = gameListTableView.getSelectionModel().getSelectedItem();

        previouslyPlayedList.updateReview(selectedGame, review);
        updateList(previouslyPlayedList);
        reviewTextArea.clear();
    }

    /**
     *
     * @param event
     * Gets the review set under the game and fills the TextArea with said review, so it can be edited and viewed
     */
    public void handleViewReviewButton(ActionEvent event){
        Game selectedGame = gameListTableView.getSelectionModel().getSelectedItem();
        reviewTextArea.setText(selectedGame.getReview());
    }

    /**
     * Handler for removeGameButton
     * Gets the selected game from the table and removes it from the table as well as wishlist
     */
    void handleRemoveGameButton(ActionEvent event) {
        Game gameToRemove = gameListTableView.getSelectionModel().getSelectedItem();
        previouslyPlayedList.removeGame(gameToRemove);
        gameListObservableList.remove(gameToRemove);

        updateList(previouslyPlayedList);
    }

    /**
     * This Fills the TableView with values from the games in the PreviouslyPlayedList
     * I used the code from https://www.youtube.com/watch?v=uh5R7D_vFto, as I needed factories in order to make it work, and dont fully understand factories
     * Sets the columns with data from my object Game
     */
    void fillTableView() {
        super.fillObservableList();

        super.titleColumn.setCellValueFactory(new PropertyValueFactory<Game, String>("title"));
        super.anticipationColumn.setCellValueFactory(new PropertyValueFactory<Game, Integer>("anticipation"));
        this.ratingColumn.setCellValueFactory(new PropertyValueFactory<Game, Integer>("rating"));
        this.dateStartedColumn.setCellValueFactory(new PropertyValueFactory<Game, LocalDate>("dateStarted"));
        this.dateCompletedColumn.setCellValueFactory(new PropertyValueFactory<Game, LocalDate>("dateCompleted"));

        super.gameListTableView.setItems(super.gameListObservableList);

    }

}
