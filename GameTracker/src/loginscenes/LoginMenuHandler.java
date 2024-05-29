package loginscenes;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import userinformation.UserInformation;

import java.io.IOException;

/**
 * Logic class for LoginController
 */
public class LoginMenuHandler {


    private UserInformation userInformation;

    private TextField usernameTextField;
    private PasswordField passwordField;


    /**
     *
     * @param loginController
     * @param userInformation
     * Constructor for LoginMenuHandler
     * Instantiates references to SystemMain variables
     */
    public LoginMenuHandler(LoginController loginController, UserInformation userInformation) {
        this.userInformation = userInformation;

        this.passwordField = loginController.passwordField;
        this.usernameTextField = loginController.usernameTextField;


    }


    /**
     *
     * @param event
     * @return
     * Gets information from TextFields and checks whether the information is values and logs the user in if information is valid
     */
    public boolean handleLoginButton(ActionEvent event) {
        // take the username in the text field and send it over to userInformation to check if a folder exists with that name
        // userInformation.userExists() will return true if there is a folder matching the given username
        String username = usernameTextField.getText();
        String password = passwordField.getText();


        if (!userInformation.userExists(username)) {

            Alert inputError = new Alert(Alert.AlertType.ERROR);
            inputError.setHeaderText("No User Found");
            inputError.setContentText("No user by the name " + username + " was found");
            inputError.showAndWait();

            clearFields();
            return false;
        }


        try {
            if (!userInformation.correctPassword(username, password)) {

                Alert inputError = new Alert(Alert.AlertType.ERROR);
                inputError.setHeaderText("Incorrect Password");
                inputError.setContentText("Incorrect Password for " + username);
                inputError.showAndWait();
                passwordField.clear();

                return false;
            }
        } catch (IOException e) {
            return false;
        }

        try {
            userInformation.login(username, password);
        } catch (IOException e) {
            return false;
        }

        return true;
    }


    /**
     * clears the LoginScene TextFields
     */
    private void clearFields() {
        usernameTextField.clear();
        passwordField.clear();
    }

}
