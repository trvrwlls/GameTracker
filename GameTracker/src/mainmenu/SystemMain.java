package mainmenu;

import gamelist.CurrentlyPlayingList;
import gamelist.PreviouslyPlayedList;
import gamelist.WishList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import loginscenes.LoginController;
import loginscenes.NewUserController;

import mainmenuscenes.CurrentlyPlayingController;
import mainmenuscenes.MainMenuController;
import mainmenuscenes.PreviouslyPlayedController;
import mainmenuscenes.WishlistController;

import userinformation.UserInformation;

import java.io.IOException;
import java.util.Scanner;


public class SystemMain extends Application {

    private Parent root;
    private Stage primaryStage;

    private WishList wishlist = new WishList();
    private CurrentlyPlayingList currentlyPlayingList = new CurrentlyPlayingList();
    private PreviouslyPlayedList previouslyPLayedList = new PreviouslyPlayedList();

    private UserInformation userInformation = new UserInformation();


    /**
     * https://stackoverflow.com/questions/56369152/how-to-make-a-proper-mvc-pattern-with-javafx-and-scene-builder
     * I took this as inspiration for my MVC pattern.
     * As before seeing this I hadn't seen an example of MVC pattern with scene builder and .fxml files.
     * I did only use it for the structure everything else was on my own.
     */

    /**
     * @param primaryStage
     * start method saves the primary stage to be used multiple times throughout the program
     */
    @Override
    public void start(Stage primaryStage) {

        // login scene make this a method that can be run here
        this.primaryStage = primaryStage;
        startLoginMenu();

    }

    /**
     * Loads LoginScene.fxml
     * and calls and instantiates variables within its controller, LoginController
     * opens the loaded scene
     */
    public void startLoginMenu() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("LoginScene.fxml"));
            root = loader.load();

            LoginController controller = loader.getController();


            controller.initialize(userInformation, this);

            this.primaryStage.setTitle("Login");
            this.primaryStage.setScene(new Scene(root, 190, 130));
            this.primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @throws IOException
     * Loads and opens the scene NewUserScene.fxml
     * Instantiates variables within the controller NewUserController
     *
     */
    public void startNewUserMenu() throws IOException {

        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("NewUserScene.fxml"));
            root = loader.load();

            NewUserController controller = loader.getController();

            controller.initialize(userInformation, this);

            primaryStage.setScene(new Scene(root, 170, 160));
            primaryStage.setTitle("New User");
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the scene MainMenuScene
     * Opens the controller, MainMenuController, and initializes variables within it
     * Opens the loaded scene
     */
    public void startMainMenu() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("MainMenuScene.fxml"));
            root = loader.load();


            MainMenuController controller = loader.getController();
            controller.initialize(userInformation, this);


            primaryStage.setTitle("Main Menu");
            primaryStage.setScene(new Scene(root, 300, 115));
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Begins the logout process, making new lists and creating a new UserInformation reference
     * for a fresh page for the next user
     */
    public void startLogoutProcess() {
        try {

            userInformation.writeUserWishList(wishlist);
            userInformation.writeUserCurrentlyPlayingList(currentlyPlayingList);
            userInformation.writeUserPreviouslyPlayedList(previouslyPLayedList);


        } catch (IOException e) {
            e.printStackTrace();
        }
        wishlist.clearList();
        currentlyPlayingList.clearList();
        previouslyPLayedList.clearList();
        userInformation = new UserInformation();

        startLoginMenu();
    }


    /**
     * Loads and opens WishListMenuScene.fxml
     * Opens and calls the controller, WishListController, and instantiates variables within
     */
    public void startWishListMenu() {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("WishlistMenuScene.fxml"));
            root = loader.load();

            WishlistController controller = loader.getController();
            controller.initialize(userInformation, this);

            primaryStage.setTitle("Wishlist");
            primaryStage.setScene(new Scene(root, 450, 315));
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads and opens CurrentlyPlayingMenuScene.fxml
     * Opens and calls the controller, CurrentlyPlayingController, and instantiates variables within
     */
    public void startCurrentlyPlayingMenu() {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("CurrentlyPlayingMenuScene.fxml"));
            root = loader.load();

            CurrentlyPlayingController controller = loader.getController();
            controller.initialize(userInformation, this);


            primaryStage.setTitle("Currently Playing");
            primaryStage.setScene(new Scene(root, 500, 315));
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startPreviouslyPlayedMenu() {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("PreviouslyPlayedMenuScene.fxml"));
            root = loader.load();

            PreviouslyPlayedController controller = loader.getController();
            controller.initialize(userInformation, this);

            primaryStage.setTitle("Previously Played");
            primaryStage.setScene(new Scene(root, 700, 375));
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads and opens PreviouslyPlayedMenuScene.fxml
     * Opens and calls the controller, PreviouslyPlayedController, and instantiates variables within
     */
    public void setUsersLists() {
        try {
            wishlist = userInformation.readUserWishList();
            currentlyPlayingList = userInformation.readUserCurrentlyPlayingList();
            previouslyPLayedList = userInformation.readUserPreviouslyPlayedList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * return this.wishList
     */
    public WishList getWishlist() {
            return this.wishlist;
    }

    /**
     * @return this.currentlyPlayingList
     */
    public CurrentlyPlayingList getCurrentlyPLayingList() {
        return this.currentlyPlayingList;
    }

    /**
     * @return this.previouslyPlayedList
     */
    public PreviouslyPlayedList getPreviouslyPlayedList() {
        return this.previouslyPLayedList;
    }

    public static void main(String [] args) {

        Scanner input = new Scanner(System.in);
        System.out.println("Please enter how you would like to access the program.\n1 - GUI\n0 - Console");
        int userInput = Integer.parseInt(input.nextLine());
        while (!(userInput == 1 || userInput == 0)) {
            System.out.println("You have input the wrong value");
            userInput = Integer.parseInt(input.nextLine());
        }
        if (userInput == 1) {
            launch(args);
            return;
        }

        SystemMenu systemMenu = new SystemMenu();
    }

}

