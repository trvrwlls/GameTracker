package mainmenuscenes;

import javafx.event.ActionEvent;
import userinformation.UserInformation;
import mainmenu.SystemMain;

/**
 *
 */
public class MainMenuHandler {


    private SystemMain systemMain;
    private UserInformation userInformation;

    public MainMenuHandler(UserInformation userInformation, SystemMain systemMain) {
        this.userInformation = userInformation;
        this.systemMain = systemMain;
    }


    public void handleLogoutButton(ActionEvent event) {
        systemMain.startLogoutProcess();
    }



}
