package loginscenes;

import mainmenu.SystemMain;
import userinformation.UserInformation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * Controller for NewUserScene.fxml
 */
public class NewUserController {

    @FXML
    TextField usernameTextField;
    @FXML
    PasswordField passwordField;
    @FXML
    PasswordField confirmPasswordField;

    private SystemMain systemMain;
    private NewUserMenuHandler newUserMenuHandler;
    private UserInformation userInformation;


    /**
     *
     * @param event
     * Creates a new user
     */
    public void createUserButtonHandler(ActionEvent event) {

        if (newUserMenuHandler.handleCreateUserButton(event)) {
            try {
                systemMain.startMainMenu();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    /**
     *
     * @param userInformation
     * @param systemMain
     * Instantiates variables within NewUserController
     * Passes information between SystemMain and NewUserController
     */
    public void initialize(UserInformation userInformation, SystemMain systemMain) {
        this.systemMain = systemMain;
        this.userInformation = userInformation;
        newUserMenuHandler = new NewUserMenuHandler(this, userInformation);

    }
}

