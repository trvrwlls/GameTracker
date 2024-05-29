package mainmenuscenes;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import mainmenu.SystemMain;
import userinformation.UserInformation;

/**
 * MainMenuController is a class for controlling the MainMenuScene.fxml file
 * This communicates with SystemMain which starts different scenes
 * and also communicates with MainMenuHandler
 */
public class MainMenuController {

    protected SystemMain systemMain;
    private UserInformation userInformation;


    @FXML
    Label welcomeLabel;


    /**
     *
     * @param event
     * will open the wishlist scene
     * Method will accomplish this task by calling the "startWishListMenu" in SystemMain
     */
    public void wishlistButtonHandler(ActionEvent event) {
        try {
            systemMain.startWishListMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param event
     * Will open the Currently playing scene
     * Method will accomplish this task by calling the "startWishListMenu" in SystemMain
     */
    public void currentlyPlayingButtonHandler(ActionEvent event) {
        try {
            systemMain.startCurrentlyPlayingMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param event
     * Will open the previously played scene
     * Method will acomplish this task by calling the "startWishListMenu" in SystemMain
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
     * After pressing the button the scene will become login
     */
    public void logoutButtonHandler(ActionEvent event) {
        systemMain.startLogoutProcess();
    }


    /**
     *
     * @param userInformation
     * @param systemMain
     * This initializes the file, setting variables and setting the wishlists to the data in the users file
     */
    public void initialize(UserInformation userInformation, SystemMain systemMain) {
        this.systemMain = systemMain;
        this.userInformation = userInformation;
        welcomeLabel.setText("USER: "+userInformation.getUsername());


        systemMain.setUsersLists();

    }




}
