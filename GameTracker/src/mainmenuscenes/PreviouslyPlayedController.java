package mainmenuscenes;

import gamelist.Game;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import mainmenu.SystemMain;

import userinformation.UserInformation;

import java.time.LocalDate;

/**
 * The controller class for PreviouslyPlayedMenuScene.fxml
 * The logic is CurrentlyPlayingMenuHandler
 */
public class PreviouslyPlayedController {

    private SystemMain systemMain;
    private UserInformation userInformation;

    private PreviouslyPlayedMenuHandler previouslyPlayedMenuHandler;

    private ObservableList<Game> wishListObservableList = FXCollections.observableArrayList();



    @FXML
    Label welcomeLabel;
    @FXML
    TextArea reviewTextArea;
    @FXML
    TextField ratingTextField;



    @FXML
    TableView<Game> previouslyPlayedTableView;

    @FXML
    TableColumn<Game, String> titleColumn;
    @FXML
    TableColumn<Game, Integer> anticipationColumn;
    @FXML
    TableColumn<Game, Integer> ratingColumn;
    @FXML
    TableColumn<Game, LocalDate> dateStartedColumn;
    @FXML
    TableColumn<Game, LocalDate> dateCompletedColumn;


    public void addRatingButtonHandler(ActionEvent event) {
        previouslyPlayedMenuHandler.handleAddRatingButton(event);
    }


    public void addReviewButtonHandler(ActionEvent event) {
        previouslyPlayedMenuHandler.handleAddReviewButton(event);
    }


    public void viewReviewButtonHandler(ActionEvent event) {
        previouslyPlayedMenuHandler.handleViewReviewButton(event);
    }


    public void removeGameButtonHandler(ActionEvent event) {
        previouslyPlayedMenuHandler.handleRemoveGameButton(event);
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
     * This method calls the startWishListMenu in SystemMain
     * This changes the scenes from previouslyPlayedMenuScene to currentlyPlayingMenuScene
     */
    public void currentlyPlayingButtonHandler(ActionEvent event) {
        try {
            systemMain.startCurrentlyPlayingMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    /**
     * This method begins the logout process
     * Can be seen in more detail in SystemMain
     */
    public void logoutButtonHandler(ActionEvent event) {
        systemMain.startLogoutProcess();
    }


    /**
     *
     * @param userInformation
     * @param systemMain
     * This is used to instantiate PreviouslyPlayedController's handler
     * Also important for getting data from SystemMain
     */
    public void initialize(UserInformation userInformation, SystemMain systemMain) {
        this.userInformation = userInformation;
        this.systemMain = systemMain;

        welcomeLabel.setText("USER: " + userInformation.getUsername());

        previouslyPlayedMenuHandler = new PreviouslyPlayedMenuHandler(this, userInformation, systemMain);
    }

}
