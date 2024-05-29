package loginscenes;

import userinformation.UserInformation;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;


/**
 * Logic class for NewUserController
 */
public class NewUserMenuHandler {

    private UserInformation userInformation;

    private TextField usernameTextField;
    private PasswordField passwordField;
    private  PasswordField confirmPasswordField;

    private String username;
    private String password;
    private String confirmPassword;


    /**
     * Constructor
     * @param newUserController
     * @param userInformation
     *
     */
    protected NewUserMenuHandler(NewUserController newUserController, UserInformation userInformation) {

        this.usernameTextField = newUserController.usernameTextField;
        this.passwordField = newUserController.passwordField;
        this.confirmPasswordField = newUserController.confirmPasswordField;

        this.userInformation = userInformation;
    }


    /**
     *
     * @param event
     * @return
     * Creates and
     */
    public boolean handleCreateUserButton(ActionEvent event) {

        username = usernameTextField.getText();
        password = passwordField.getText();
        confirmPassword = confirmPasswordField.getText();

        try {

            if (properlyFormatedFields()) {
                if (userInformation.createNewUser(username, password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
        }

        private boolean properlyFormatedFields() {

            if (!password.equals(confirmPassword)) {
                Alert inputError = new Alert(Alert.AlertType.ERROR);
                inputError.setHeaderText("Passwords do not match");
                inputError.setContentText("To create an account passwords must match");
                inputError.showAndWait();
                return false;
            }

            if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Alert inputError = new Alert(Alert.AlertType.ERROR);
                inputError.setHeaderText("Blank Field");
                inputError.setContentText("Text fields cannot be left blank.");
                inputError.showAndWait();

                return false;
            }
            if (userInformation.userExists(username)) {
                Alert inputError = new Alert(Alert.AlertType.ERROR);
                inputError.setHeaderText("Username Taken");
                inputError.setContentText("The username " + username + " is already taken.");
                inputError.showAndWait();

                return false;
            }
            return true;
        }
    }

