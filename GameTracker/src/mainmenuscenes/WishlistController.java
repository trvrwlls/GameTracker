package mainmenuscenes;

import gamelist.CurrentlyPlayingList;
import gamelist.Game;
import gamelist.WishList;
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

/**
 * WishlistMenuHandler is a class for controlling the WishlistMenuScene.fxml file
 * This communicates with SystemMain which starts different scenes
 * and also communicates with WishlistMenuHandler (logic class)
 */
public class WishlistController {

    private SystemMain systemMain;
    private UserInformation userInformation;
    private WishlistMenuHandler wishListMenuHandler;


    private ObservableList<Game> wishListObservableList = FXCollections.observableArrayList();


    @FXML
    Label welcomeLabel;
    @FXML
    TextField titleTextField;
    @FXML
    TextField anticipationTextField;
    @FXML
    TableView<Game> wishListTableView;
    @FXML
    TableColumn<Game, String> titleColumn;
    @FXML
    TableColumn<Game, Integer> anticipationColumn;


    /**
     *
     * @param event
     * Checks to see if the handler runs
     * if so clear the TextField
     */
    public void addGameButtonHandler(ActionEvent event) {
        if (wishListMenuHandler.handleAddGameButton(event) ) {
            titleTextField.clear();
            anticipationTextField.clear();
        }
    }

    public void removeGameButtonHandler(ActionEvent event) {
        wishListMenuHandler.handleRemoveGameButton(event);
    }


    public void startGameHandler(ActionEvent event) {
        wishListMenuHandler.handleStartGameButton(event);
    }

    /**
     * This method calls the startWishListMenu method in SystemMain
     * This changes the scenes from WishlistMenuScene to CurrentlyPlayingMenuScene
     */
    public void currentlyPlayingButtonHandler(ActionEvent event) {
        try {
            systemMain.startCurrentlyPlayingMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Calls the startPreviouslyPlayedMenu method in SystemMain
     * This changes the scenes from WishlistMenuScene to PreviouslyPlayedMenuScene
     */
    public void previouslyPlayedButtonHandler(ActionEvent event) {
        try {
            systemMain.startPreviouslyPlayedMenu();
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
     * Initializes variables within the wishlist controller
     * Also starts the wishlistMenuHandler the logic class for this controller
     */
    public void initialize(UserInformation userInformation, SystemMain systemMain) {
        this.userInformation = userInformation;
        this.systemMain = systemMain;
        welcomeLabel.setText("USER: " + userInformation.getUsername());

        wishListMenuHandler = new WishlistMenuHandler(this, userInformation, systemMain);
    }




}
