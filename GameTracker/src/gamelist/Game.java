package gamelist;

import java.time.LocalDate;


/**
 * @author Trevor
 * This class is to be used to make a game class allows to build what is needed to define a game.
 */
public class Game {

    private String title;
    private double anticipation; //this value will be between 1-10

    private LocalDate dateStarted;

    private LocalDate dateCompleted;


    private int rating; // this value will be between 1-10
    private String review;


    // decide if i should keep this
    //private LocalDate timeTakenToFinish;
    // if no time dont include

    /**
     * @Constructor
     * @param title (string)
     * @param anticipation (int)
     *
     */
    public Game(String title, double anticipation) {
        this.title = title;
        this.anticipation = anticipation;
        dateStarted = null;
        dateCompleted = null;
        rating = -1;
        review = " ";

    }


    /**
     * @constructor
     * @param title (string)
     * @param anticipation (int)
     * @param date (LocalDate)
     *
     * This constructor is made to bypass the wishlist step and just add the the game you wish to play straight into your currently playing library.
     */
    public Game(String title, double anticipation, boolean bool) {

        this.title = title;
        this.anticipation = anticipation;
        this.dateStarted = LocalDate.now();

        dateCompleted = null;
        rating = -1;
        review = " ";
    }

    /**
     * @constructor
     * @param title (string)
     * @param anticipation (int)
     * @param date (LocalDate)
     *
     * This Constructor is used for games that have already been started
     */
    public Game(String title, double anticipation, LocalDate dateStarted) {
        this.title = title;
        this.anticipation = anticipation;
        this.dateStarted = dateStarted;

        dateCompleted = null;
        rating = -1;
        review = " ";
    }

    /**
     *
     * @param cloneGame (Game)
     *
     *
     */
    public Game(Game cloneGame) {
        this.title = cloneGame.title;
        this.anticipation = cloneGame.anticipation;
        this.dateStarted = cloneGame.dateStarted;
        this.dateCompleted = cloneGame.dateCompleted;
        this.rating = cloneGame.rating;
        this.review = cloneGame.review;
    }

    public Game(String title, double anticipation, int rating, LocalDate dateStarted, LocalDate dateCompleted, String review) {
        this.title = title;
        this.anticipation = anticipation;
        this.rating = rating;
        this.dateStarted = dateStarted;
        this.dateCompleted = dateCompleted;
    }


    /**
     * @method startGame
     //* @param dateStarted (LocalDate)
     *
     * This method sets a start date for your begun game.
     * Primarily will be used by current and/or wishlist.
     */
    public void startGame() {
        if (this.dateStarted == null) {
            this.dateStarted = LocalDate.now();
        }
    }

    /**
     * @method endGame
     //* @param dateCompleted (LocalDate)
     * @param rating (int)
     * @param review
     *
     * This method will only be used when a user has finished their game
     */
    public void finishGame(int rating, String review) {
        this.dateCompleted = LocalDate.now();
        this.rating = rating;
        this.review = review;
    }

    /**
     *
     */
    public void finishGame() {
        dateCompleted = LocalDate.now();
        rating = -1;
        review = " ";
    }


    /**
     * @method qualitiesToString
     * @return gameQualities (String)
     *
     * Creates a string that contains all of the qualities of a game
     */
    public String qualitiesToString() {
        String qualities = "";
        String adding;

        if (!(title == null)) {
            adding = "Title: " + title + "\n";
            qualities = qualities + adding;
        }
        if (anticipation > 0) {
            adding = "Anticipation: " + anticipation + "\n";
            qualities = qualities + adding;
        }
        if (!(dateStarted == null)) {
            adding = "Date Started: " + dateStarted + "\n";
            qualities = qualities + adding;
        }
        if (!(dateCompleted == null)) {
            adding = "Date Finished: " + dateCompleted + "\n";
            qualities = qualities + adding;
        }
        if (rating >= 0) {
            adding = "Rating: "+ rating + "\n";
            qualities = qualities + adding;
        }
        if (!(review == " ")) {
            adding = "Review: " + review + "\n";
            qualities = qualities + adding;
        }

        return qualities;
    }

    /******************************************************************************
     // A whole bunch getters and setters for all of the initialized class variables


     */
    /**
     * @return title variable
     */
    public String getTitle() {
        return title;
    }


    /**
     * @return dateStarted ( LocalDate)
     */
    public LocalDate getDateStarted() {
        return dateStarted;
    }

    /**
     * @return
     */
    public LocalDate getDateCompleted() {
        return dateCompleted;
    }


    public String getReview() {
        return review;
    }


    public void setReview(String review) {
        this.review = review;
    }


    public double getAnticipation() {
        return anticipation;
    }


    /**
     //* @param newAnticipation (int)
     *
     * This is a way to change the anticipation.
     * Only allowed to be used in the wishlist because anticipation wont have an effect any other time.
     */
    public void setAnticipation(double anticipation) {
        this.anticipation = anticipation;
    }


    /**
     * @method rating
     //* @param rating (int)
     *
     * This is a way way to change the rating made on a game.
     * Only be allowed to be used after you have finished the game.
     */
    public int getRating() {
        return rating;
    }

    /**
     * @method setRating
     * @param rating
     *
     * Should only used after a game has been completed.
     */
    public void setRating(int rating) {
        this.rating = rating;
    }
}