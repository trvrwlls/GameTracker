package userinformation;

import gamelist.CurrentlyPlayingList;
import gamelist.Game;
import gamelist.PreviouslyPlayedList;
import gamelist.WishList;

import java.io.*;
import java.time.LocalDate;

/**
 * Class That stores User data
 * Passes information between files and classes
 */
public class UserInformation {

    private String username;
    private String password;
    private File userFolder;
    private String userPath;
    private String ABSOLUTEPATHUSERS;



    public UserInformation() {
        File USERS = new File("USERS");
        USERS.mkdirs();

        ABSOLUTEPATHUSERS = USERS.getAbsolutePath();



    }


// TODO THERE IS AN ERROR SOMEWHERE THAT WILL PUT FILES IN THE USERS FOLDER...

    /**
     *
     * @param username
     * @param password
     * @throws IOException
     */
    public boolean createNewUser(String username, String password) throws IOException {

        String userPath = ABSOLUTEPATHUSERS + "\\" + username;
        File userFolder = new File(userPath);

        if (userFolder.mkdir()) {

            this.username = username;


            try {
                this.userFolder = userFolder;
                File passwordFile = new File(userPath + "\\password.txt");
                passwordFile.createNewFile();
                writePassword(password);

                File wishListFile = new File(userPath + "\\wishlist.txt");
                wishListFile.createNewFile();

                File currentlyPlayingFile = new File(userPath + "\\currentlyplayinglist.txt");
                currentlyPlayingFile.createNewFile();

                File previouslyPlayedFile = new File(userPath + "\\previouslyplayedlist.txt");
                previouslyPlayedFile.createNewFile();



                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     *
     * @param username
     * @param password
     *
     * Will take and set the users information but not before checking if the file exists
     */
    public void login(String username, String password) throws IOException {
        this.username = username;
        this.password = password;
        userPath = ABSOLUTEPATHUSERS + "\\" + username;

    }


    /**
     *
     * @param username
     * @return
     * checks to see if the user exists
     */
    public boolean userExists(String username) {
        String potentialUsername = ABSOLUTEPATHUSERS + "\\" + username;

        if (username.isEmpty()) {
            return false;
        }
        
        File potentialUser = new File(potentialUsername);

        return potentialUser.exists();

    }


    /**
     *
     * @param username
     * @param password
     * @return
     * @throws IOException
     * checks if this is the correct password
     *
     */
    public boolean correctPassword(String username, String password) throws IOException {

        if (getPassword(username).equals(password)) {
            return true;
        }

        return false;


    }

    /**
     *
     * @param password
     * @throws IOException
     *
     * sets the users password
     */
    private void writePassword(String password) throws IOException {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(userFolder + "\\password.txt"));
            writer.write(password);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    /**
     *
     * @param username String
     * @return String
     *
     * Returns the given user's password
     */
    private String getPassword(String username) {

        String passwordPath = ABSOLUTEPATHUSERS + "\\" + username + "\\password.txt";

        try {

            BufferedReader reader = new BufferedReader(new FileReader(passwordPath));
            String line = reader.readLine();
            return line;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }


    /**
     *
     * @param wishList
     * Takes games from wishlist and stores them as text onto a file
     */
    public void writeUserWishList(WishList wishList) throws IOException {

        File wishlistFile = new File(ABSOLUTEPATHUSERS + "\\" + username + "\\wishlist.txt");

        BufferedWriter writer = new BufferedWriter(new FileWriter(wishlistFile));

        writer.write("");
        writer.flush();

        for (Game game : wishList.getListOfGames()) {

            writer.write(game.getTitle() + "~" + game.getAnticipation());
            writer.newLine();
        }

        writer.flush();

        writer.close();

    }

    /**
     *
     * @param currentlyPlayingList
     * Writes the data stored in the games from currentlyplayinglist to a file
     */
    public void writeUserCurrentlyPlayingList(CurrentlyPlayingList currentlyPlayingList) throws IOException {


        File currentlyPlayingFile = new File(ABSOLUTEPATHUSERS + "\\" + username + "\\currentlyplayingList.txt");


        BufferedWriter writer = new BufferedWriter(new FileWriter(currentlyPlayingFile));

        writer.write("");
        writer.flush();

        for (Game game : currentlyPlayingList.getListOfGames()) {
            String stringToWrite = game.getTitle() + "~" + game.getAnticipation() + "~" + game.getDateStarted();
            writer.write(stringToWrite);
            writer.newLine();
        }

        writer.flush();
        writer.close();

    }

    /**
     *
     * @param previouslyPlayedList
     * @throws IOException
     * Takes a previouslyplayedlist and writes the data stored in the list to a file
     */
    public void writeUserPreviouslyPlayedList(PreviouslyPlayedList previouslyPlayedList) throws IOException {

        File previouslyPlayedFile = new File(ABSOLUTEPATHUSERS + "\\" + username + "\\previouslyplayedList.txt");



        BufferedWriter writer = new BufferedWriter(new FileWriter(previouslyPlayedFile));

        writer.write("");
        writer.flush();

        for (Game game : previouslyPlayedList.getListOfGames()) {

            String review = game.getReview();
            if (review == null) {
                review = " ";
            }
            String stringToWrite = game.getTitle() + "~" + game.getAnticipation() + "~" + game.getRating() + "~" + game.getDateStarted() + "~" + game.getDateCompleted() + "~" + review;
            writer.write(stringToWrite);
            writer.newLine();
        }

        writer.flush();
        writer.close();
    }





    /**
     *
     * @return
     * @throws IOException
     *
     * reads games from a file and returns a WishList containing all of them
     */
    public WishList readUserWishList() throws IOException {
        WishList wishList = new WishList();
        String wishListPath = ABSOLUTEPATHUSERS + "\\" + username + "\\wishlist.txt";

        BufferedReader reader = new BufferedReader(new FileReader(wishListPath));

        String line = reader.readLine();

        while (line != null) {
            String [] lineList = line.split("~");
            wishList.addGame(new Game(lineList[0], Double.parseDouble(lineList[1])));
            line = reader.readLine();
        }
        return wishList;

    }

    /**
     *
     * @return
     * @throws IOException
     * reads information from a file and returns the information as a CurrentlyPlayingList
     */
    public CurrentlyPlayingList readUserCurrentlyPlayingList() throws IOException {
        CurrentlyPlayingList currentlyPlayingList = new CurrentlyPlayingList();
        String currentlyPlayingPath = ABSOLUTEPATHUSERS + "\\" + username + "\\currentlyplayingList.txt";

        BufferedReader reader = new BufferedReader(new FileReader(currentlyPlayingPath));

        String line = reader.readLine();
        String [] lineList;

        while (line != null) {
            lineList = line.split("~");
            currentlyPlayingList.addGame(new Game(lineList[0], Double.parseDouble(lineList[1]), LocalDate.parse(lineList[2])));
            line = reader.readLine();
        }
        return currentlyPlayingList;

    }

    /**
     *
     * @return
     * @throws IOException
     * Reads data from a file and returns the data type PreviouslyPlayedList
     */
    public PreviouslyPlayedList readUserPreviouslyPlayedList() throws IOException {
        PreviouslyPlayedList previouslyPlayedList = new PreviouslyPlayedList();
        String previouslyPlayedPath = ABSOLUTEPATHUSERS + "\\" + username + "\\previouslyplayedlist.txt";

        BufferedReader reader = new BufferedReader(new FileReader(previouslyPlayedPath));

        String line = reader.readLine();
        String [] lineList;

        while (line != null) {
            lineList = line.split("~");
            previouslyPlayedList.addGame(new Game(lineList[0], Double.parseDouble(lineList[1]), Integer.parseInt(lineList[2]), LocalDate.parse(lineList[3]), LocalDate.parse(lineList[4]), lineList[5]));
            line = reader.readLine();
        }
        return previouslyPlayedList;
    }




    /**
     * @return
     * returns the username
     */
    public String getUsername() {
        return username;
    }

}
