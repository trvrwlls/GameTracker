package loginscenes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import mainmenu.SystemMain;
import userinformation.UserInformation;

/**
 * Controller class for LoginScene.fxml
 * LoginControllers logic class is LoginMenuHandler
 */
public class LoginController {

    @FXML
    TextField usernameTextField;
    @FXML
    PasswordField passwordField;

    private UserInformation userInformation;

    private LoginMenuHandler loginMenuHandler;

    private SystemMain systemMain;

    /**
     * @param event
     * Using the logic class LoginMenuHandler to see if the conditions to press the button are met
     * If true the next scene will be called, i.e. systemMain.startMainMenu()
     */
    public void loginButtonHandler(ActionEvent event) {
        if (loginMenuHandler.handleLoginButton(event)) {
            try {
                systemMain.startMainMenu();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /**
     *
     * @param event
     * New user scene will be called, i.e. systemMain.startNewUserMenu()
     */
    public void newUserButtonHandler(ActionEvent event) {
        try {
            systemMain.startNewUserMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     *
     * @param userInformation
     * @param systemMain
     * Used to pass information from SystemMain to LoginController then onto the logic
     */
    public void initialize(UserInformation userInformation, SystemMain systemMain) {
        this.systemMain = systemMain;
        this.userInformation = userInformation;
        loginMenuHandler = new LoginMenuHandler(this, userInformation);
    }
}
