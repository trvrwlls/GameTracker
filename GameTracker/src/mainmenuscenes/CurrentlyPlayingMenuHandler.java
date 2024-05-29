package mainmenuscenes;

import gamelist.CurrentlyPlayingList;
import gamelist.Game;
import gamelist.PreviouslyPlayedList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import mainmenu.SystemMain;
import userinformation.UserInformation;

import java.time.LocalDate;


/**
 * Child class of GameListsHandlers (Abstract)
 * Logic Class of CurrentlyPlayingController
 */
public class CurrentlyPlayingMenuHandler extends GameListsHandlers {

    private CurrentlyPlayingList currentlyPlayingList;
    private PreviouslyPlayedList previouslyPlayedList;



    private TableColumn<Game, LocalDate> dateStartedColumn;


    /**

     /**
     *
     * @param currentlyPlayingController
     * @param userInformation
     * @param systemMain
     * Sets data stored in the super class as well as the data stored in this class to proper data from userInformation and the controller class
     */
    public CurrentlyPlayingMenuHandler(CurrentlyPlayingController currentlyPlayingController, UserInformation userInformation, SystemMain systemMain) {

        super.titleTextField = currentlyPlayingController.titleTextField;
        super.anticipationTextField = currentlyPlayingController.anticipationTextField;

        super.gameListTableView = currentlyPlayingController.currentlyPlayingTableView;
        super.titleColumn = currentlyPlayingController.titleColumn;
        super.anticipationColumn = currentlyPlayingController.anticipationColumn;

        this.dateStartedColumn = currentlyPlayingController.dateStartedColumn;

        super.systemMain = systemMain;

        super.userInformation = userInformation;

        this.currentlyPlayingList = systemMain.getCurrentlyPLayingList();
        this.previouslyPlayedList = systemMain.getPreviouslyPlayedList();

        super.selectedGameList = currentlyPlayingList;

        fillTableView();
    }

    /**
     *
     * @param event
     * Overwrites the method in parent class
     * removes a game from the table
     */
    @Override
    void handleRemoveGameButton(ActionEvent event) {
        Game gameToRemove = gameListTableView.getSelectionModel().getSelectedItem();
        if (gameToRemove != null) {
            currentlyPlayingList.removeGame(gameToRemove);
            gameListObservableList.remove(gameToRemove);
        }

    }


    /**
     *
     * @param event
     * @return
     * When the AddGameButton is pressed this method will
     * Take data from titleTextField and anticipationTextField
     * Check whether the data is formatted correctly
     * If it is formatted correctly, will add a game to currentlyPlayingList
     */
    protected boolean handleAddGameButton(ActionEvent event) {
        String title = super.titleTextField.getText();

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
        int anticipation;
        try {
            anticipation = Integer.parseInt(super.anticipationTextField.getText());
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
            inputError.setContentText("The anticipation must be a number between 0-10");
            inputError.showAndWait();
            return false;
        }


        Game gameToAdd = new Game(title, anticipation, true);

        super.selectedGameList.addGame(gameToAdd);
        super.gameListObservableList.add(gameToAdd);
        return true;

    }



    /**
     *
     * @param event
     * Moves the selected game from currentlyplaying list to previouslyplayed list
     */
    public void handleFinishGameButton(ActionEvent event) {
        Game gameToFinish = super.gameListTableView.getSelectionModel().getSelectedItem();
        currentlyPlayingList.completeGame(gameToFinish, previouslyPlayedList);
        super.gameListObservableList.remove(gameToFinish);

        updateList(currentlyPlayingList);
        updateList(previouslyPlayedList);



    }


    /**
     * This Fills the TableView with values from the games in the CurrentlyPlayingList
     * I used the code from https://www.youtube.com/watch?v=uh5R7D_vFto, as I couldn't find another way to store data within the list
     */
    void fillTableView() {
        super.fillObservableList();

        super.titleColumn.setCellValueFactory(new PropertyValueFactory<Game, String>("title"));
        super.anticipationColumn.setCellValueFactory(new PropertyValueFactory<Game, Integer>("anticipation"));
        this.dateStartedColumn.setCellValueFactory(new PropertyValueFactory<Game, LocalDate>("dateStarted"));

        super.gameListTableView.setItems(super.gameListObservableList);

    }

}
