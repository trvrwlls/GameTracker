package mainmenuscenes;

import gamelist.CurrentlyPlayingList;
import gamelist.Game;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import mainmenu.SystemMain;
import userinformation.UserInformation;

import java.time.LocalDate;

/**
 * The controller class for CurrentlyPlayingMenuScene.fxml
 * The logic class for CurrentlyPlayingController is CurrentlyPlayingMenuHandler
 */
public class CurrentlyPlayingController {

    private SystemMain systemMain;
    private UserInformation userInformation;

    private CurrentlyPlayingMenuHandler currentlyPlayingMenuHandler;


    private ObservableList<Game> wishListObservableList = FXCollections.observableArrayList();


    @FXML
    Label welcomeLabel;

    @FXML
    TextField titleTextField;
    @FXML
    TextField anticipationTextField;

    @FXML
    TableView<Game> currentlyPlayingTableView;
    @FXML
    TableColumn<Game, String> titleColumn;
    @FXML
    TableColumn<Game, Integer> anticipationColumn;
    @FXML
    TableColumn<Game, LocalDate> dateStartedColumn;


    /**
     *
     * @param event
     * Calls the logic class CurrentlyPlayingMenuHandler and the method handleAddGameButton
     * If everything is true, will clear the TextFields
     */
    public void addGameButtonHandler(ActionEvent event) {
        if (currentlyPlayingMenuHandler.handleAddGameButton(event) ) {
            titleTextField.clear();
            anticipationTextField.clear();
        }
    }


    public void removeGameButtonHandler(ActionEvent event) {
        currentlyPlayingMenuHandler.handleRemoveGameButton(event);
    }

    public void finishGameButtonHandler(ActionEvent event) {
        currentlyPlayingMenuHandler.handleFinishGameButton(event);
    }

    /**
     * Calls the SystemMain method startWishList menu, startWishListMenu()
     * This starts WishlistMenuScene.fxml
     */
    public void wishListButtonHandler(ActionEvent event) {
        try {
            systemMain.startWishListMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param event
     * Calls the SystemMain method to start the PreviouslyPlayed menu, startPreviouslyPlayedMenu()
     */
    public void previouslyPlayedButtonHandler(ActionEvent event) {
        try {
            systemMain.startPreviouslyPlayedMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     *
     * @param event
     * Begins SystemMains startLogoutProcess()
     * which allows another user to login
     */
    public void logoutButtonHandler(ActionEvent event) {
        systemMain.startLogoutProcess();
    }


    /**
     *
     * @param userInformation
     * @param systemMain
     * Used to initialize variables and pass variables from startCurrentlyPlayingMenu in SystemMain to here
     */
    public void initialize(UserInformation userInformation, SystemMain systemMain) {
        this.userInformation = userInformation;
        this.systemMain = systemMain;
        welcomeLabel.setText("USER: " + userInformation.getUsername());

        currentlyPlayingMenuHandler = new CurrentlyPlayingMenuHandler(this, userInformation, systemMain);

    }





}
