package mainmenu;

import gamelist.*;
import userinformation.UserInformation;

import java.io.IOException;
import java.util.Scanner;

// The methods in this class will take input from a user and ask questions depending on input

/**
 * 
 * @author Trevor
 *
 */
public class SystemMenu {

	
	// instantiation of variables
	
	private static WishList wishList = new WishList();
	private static CurrentlyPlayingList currentlyPlayingList = new CurrentlyPlayingList();
	private static PreviouslyPlayedList previouslyPlayedList = new PreviouslyPlayedList();
	
	private UserInformation userInformation;
	
	private static Scanner userInput = new Scanner(System.in);
	
	final private static String UNFINISHED = " Currently Unavailable"; // Simple string to be used across multiple methods to let the user know if a part of the menu can be accessed
	

    public SystemMenu() {
		userInformation = new UserInformation();

		System.out.println("Are you a returning user or making a new account?\n1 - Returning User\n0 - New User");

		int loginChoice = Integer.parseInt(userInput.nextLine());

		while (true) {
			if (loginChoice == 1) {
				login();
				break;
			}
			if (loginChoice == 0) {
				newUser();
				break;
			}
			System.out.println("Are you a returning user or making a new account?\n1 - Returning User\n0 - New User");
			loginChoice = Integer.parseInt(userInput.nextLine());
		}

		menuAlgorithm();


	}
	
	/***
	 * @method login
	 *
	 * Logs a user in by accessing the userFile class
	 */
	public void login() {


		System.out.print("Please input your username: "); // user instructions
		String username = userInput.nextLine(); // read user input

		while (!userInformation.userExists(username)) {

			System.out.println("No user by that username was found");

			username = userInput.nextLine(); // read user input

		}

		System.out.print("Please input "+ username +"'s password: ");
		String password = userInput.nextLine();

		try {
			while (!userInformation.correctPassword(username, password)) {
				System.out.println("That was the incorrect password for " + username);
				System.out.print("Please input "+ username +"'s password: ");

				password = userInput.nextLine(); // read user input
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			userInformation.login(username, password);
			setGameLists();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Gets input from the user to create a newuser
	 */
	private void newUser() {
		System.out.print("Username: ");
		String username = userInput.nextLine();
		System.out.print("Password: ");
		String password = userInput.nextLine();


		try {
			while (!userInformation.createNewUser(username, password)) {
				System.out.println("There was something wrong with the information provided, please try again.");
				System.out.print("Username: ");
				username = userInput.nextLine();
				System.out.print("Password: ");
				password = userInput.nextLine();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		}



	private void setGameLists() throws IOException {
		wishList = userInformation.readUserWishList();
		currentlyPlayingList = userInformation.readUserCurrentlyPlayingList();
		previouslyPlayedList = userInformation.readUserPreviouslyPlayedList();
	}


	/**
	 * @method menuAlgorithm
	 *
	 * @return void
	 * 
	 * Takes a user through menu choices for ease of code access
	 */
	public void menuAlgorithm() {

		int menuChoice; // instantiation of menuChoice 
		do {
			System.out.println("What list would you like to access?\n1 - Wishlist\n2 - Currently Playing \n3 - Previously Played\n0 - Exit Program"); // instructions for user on how to properly use the menu


			menuChoice = Integer.parseInt(userInput.nextLine()); // getting user input

			
			String output = "You've chosen "; // less typing down the road


			/**
			 * Case 1 will access @method wishListMenu()
			 * Case 2 will access @method currentlyPlayingMenu()
			 * Case 3 will access @method previouslyPlayedMenu()
			 * Case 0 will exit the system 
			 * if no proper input was given the code will run again
			 */
			switch (menuChoice) { // switch statement for menu choices
			case 1: 
				System.out.println(output + "wishlist.\n");
				wishListMenu();
				break;
			case 2:
				System.out.println(output + "current.\n");
				currentlyPlayingMenu();
				break;
			case 3:
				System.out.println(output + "previous.\n");
				previouslyPlayedMenu();
				break;
			case 0:
				System.out.println(output + "to exit the system.\n");
				System.exit(0);
				break;
				
			default: 
				System.out.println("You failed to input a proper value.\n");
			}
			
		} while (!(menuChoice == 0));
		
		System.exit(0);
	}
	
	
	
	

	/**
	 * @method wishlistMenu
	 *
	 * @return None
	 * 
	 * This method will loop through a console menu for the user to mutate their wishlist
	 */
	public void wishListMenu() {


		int wishlistChoice;
		String output = "You've chosen to ";
		
		do {
			/**
			 * This is just so the user knows what their wishlist looks like currently as long as there is at least 1 value in it
			 */
			if (wishList.getSizeOfList() != 0) {
				System.out.println("These are the games currently on your wishlist:");
				System.out.println(wishList.titlesToString() + "\n");
			}
			
			/**
			 * Gather user input and run it through a switch statement
			 * Case 1 will access @method addGameToWishListMenu()
			 * Case 2 will access @method beginGameMenu()
			 * Case 3 will access @method removeGameFromWishListMenu()
			 * Case 4 will access @method gameInformationMenu()
			 * Case 5 will access @method clearAllGamesWishListMenu()
			 * Case 0 will return to main menu
			 */
			System.out.println("What would you like to do to your system today? \n1 - Add new game to wishlist\n2 - Begin a game (move game from wishlist to currently playing)\n3 - Remove game from wishlist\n4 - Get information about a game\n5 - Clear all games from wishlist\n0 - return to main menu");

			wishlistChoice = Integer.parseInt(userInput.nextLine());
			
			switch (wishlistChoice) {
				case 1:
					System.out.println(output + "add games to wishlist.\n");
					addGamesMenu(wishList);
					break;
				case 2:
					System.out.println(output + "move a game from your wishlist to currently playing.\n");
					beginGameMenu();
					break;
				case 3:
					System.out.println(output + "remove games from wishlist.\n");
					removeGamesMenu(wishList);
					break;
				case 4:
					System.out.println(output +"get information about a game added to this list.\n");
					gameInformationMenu(wishList);
					break;
				case 5:
					System.out.println(output + "clear all games from wishlist.\n");
					clearGameListMenu(wishList);
					break;
				case 0:
					System.out.println(output + "return to the main menu.");
					return;
				default:
					System.out.println("You failed to input a valid number.");
					break;
				}
		} while (! (wishlistChoice == 0));	
		
		}
	
	


	
	// EVERYTHING BELOW IS RELATED TO CURRENTLY PLAYING MENU
	
	/**
	 * @method currentlyPlayingMenu
	 *
	 * @return void
	 * 
	 * 
	 * This method will loop through a console menu that allows the user to mutate the list of games they're currently playing
	 */
	private void currentlyPlayingMenu() {
		
		int currentlyPlayingMenuChoice;
		String output = "You've chosen to ";
		
		do {
			/**
			 * This is just so the user knows what games they're currently playing as long as there is at least 1 value in it
			 */
			if (currentlyPlayingList.getSizeOfList() != 0) {
				System.out.println("These are the games you're currently playing:");
				System.out.println(currentlyPlayingList.titlesToString());
			}
			
			
			/**
			 * Gather user input and run it through a switch statement
			 * Case 1 will access @method addGameToCurrentlyPlayingMenu()
			 * Case 2 will access @method finshGame()
			 * Case 3 will access @method removeGameCurrentlyPlayingMenu()
			 * Case 4 will access @method getGameInfoToStringMenu()
			 * Case 5 will access @method clearAllGamesCurrentlyPlayingMenu()
			 * Case 0 will return to main menu
			 */
			System.out.println("What would you like to do to your system today? \n1 - Add a new game to currently playing\n2 - Finish a game you're currently playing\n3 - Remove game you're currently playing\n4 - Get information about a game in this list\n5 - Clear all games from list\n0 - Return to main menu");
			currentlyPlayingMenuChoice = Integer.parseInt(userInput.nextLine());
			switch (currentlyPlayingMenuChoice) {
				case 1:
					System.out.println(output + "add a new game to currently playing.\n");
					addGamesMenu(currentlyPlayingList);
					break;
				case 2:
					System.out.println(output + "finish a game you're currently playing.\n");
					finishGameMenu();
					break;
				case 3:
					System.out.println(output + "remove a game you're currently playing\n");
					removeGamesMenu(currentlyPlayingList);
					break;
				case 4:
					System.out.println(output + "get information about a game added to this list.\\n");
					gameInformationMenu(currentlyPlayingList);
					break;
				case 5:
					System.out.println(output + "clear all game you're currently playing.\n");
					clearGameListMenu(currentlyPlayingList);
					break;
				case 0:
					System.out.println(output + "return to the main menu.\n");
					return;
				default:
					System.out.println("You've input an invalid number.\n");
					break;
			}
			//currentlyPlayingScanner.close();
		} while (! (currentlyPlayingMenuChoice == 0));	
		
	}

	

	/**
	 * @method previouslyPlayedMenu
	 * 
	 * This method will loop through a menu to allow a user to mutate and modify games in their previously played list
	 */
	private void previouslyPlayedMenu() {

		int previouslyPlayedChoice;
		String output = "You've chosen ";
		
		
		do {
			/**
			 * This is just so the user knows what their previously finished game list looks like currently as long as there is at least 1 value in it
			 */
			if (previouslyPlayedList.getSizeOfList() != 0) {
				System.out.println("These are the games you have previously finished:");
				System.out.println(previouslyPlayedList.titlesToString());
			}
			

			/**
			 * Gather user input and run it through a switch statement
			 *
			 * Case 1 will access @method addReviewMenu()
			 * Case 2 will access @method addRatingMenu()
			 * Case 3 will access @method removeGamesMenu()
			 * Case 4 will access @method gameInformationMenu()
			 * Case 5 will access @method clearGamesListMenu()
			 * Case 0 will return to main menu
			 */
			System.out.println("What would you like to do to your list of previously played games? \n1 - Add a review to a game\n2 - Add a rating to a game\n3 - Remove a game from previously played\n4 - Get information about a game you've previously played\n5 - Clear all games from previously played\n0 - return to main menu");
			previouslyPlayedChoice = Integer.parseInt(userInput.nextLine());
			
			switch (previouslyPlayedChoice) {
				case 1:
					System.out.println(output + "add a review to a game.\n");
					addReviewMenu();
					break;

				case 2:
					System.out.println(output + "add a rating to a game.\n");
					addRatingMenu();
					break;
				case 3:
					System.out.println(output + "remove a game.\n");
					removeGamesMenu(previouslyPlayedList);
					break;
				case 4:
					System.out.println(output + "get information about a game.\n");
					gameInformationMenu(previouslyPlayedList);
					break;
				case 5:
					System.out.println(output + "clear entire list.\n");
					clearGameListMenu(previouslyPlayedList);
					break;
				case 0:
					System.out.println(output + "to return to the main menu.\n");
					return;
				default:
					System.out.println("You've input an invalid number.\n");
					break;
			}
			
		} while (! (previouslyPlayedChoice == 0));	

		}

	private void addRatingMenu() {
		System.out.println("What game would you like to add a rating to?");
		String title = userInput.nextLine();
		if (title.isEmpty()) {
			System.out.println("Returning to the previous menu");
			return;
		}
		while (!previouslyPlayedList.hasGame(title)) {
			System.out.println("The game " + title + " does not exist in your list");
			title = userInput.nextLine();
		}
		System.out.println("What rating would you like to add for " + title + "\n(Must be a integer between 1-10)");

		int rating = Integer.parseInt(userInput.nextLine());

		while (!(0 <= rating && rating <=10)) {
			System.out.println("The integer was not between the bounds");
			rating = Integer.parseInt(userInput.nextLine());
		}

		previouslyPlayedList.updateRating(title, rating);
		updateList(previouslyPlayedList);
	}


	private void addReviewMenu() {
		System.out.println("What game would you like to add review to?");
		String title = userInput.nextLine();
		if (title.isEmpty()) {
			System.out.println("Returning to the previous menu");
			return;
		}
		while (!previouslyPlayedList.hasGame(title)) {
			System.out.println("The game " + title + " does not exist in your list");
			title = userInput.nextLine();
		}
		System.out.println("What review would you like to add for " + title);
		String review = userInput.nextLine();

		while (review.contains("~")) {
			System.out.println("Reviews cannot contain \"~\"");
			System.out.println("What review would you like to add for " + title);
			review = userInput.nextLine();
		}
		previouslyPlayedList.updateReview(title, review);

		updateList(previouslyPlayedList);
	}


	/**
	 * @param gameList (GameList)
	 * Menu that adds games to a given gamelist
	 */
	private void addGamesMenu(GameList gameList) {

		double anticipation = -1;
		Game newGameToAdd; // instantiating of a Game variable


		System.out.println("Please enter the name of the game you would like to add");
		String title = userInput.nextLine();

		if (title.isEmpty()) {
			System.out.println("Returning to the previous menu");
			return;
		}
		System.out.println("Please enter a number between 1 and 10 to rate much you want to play this game: ");

		try {
			anticipation = Double.parseDouble(userInput.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("Please only enter numbers between 1-10");
		}


		while (title.contains("~") || !(0<= anticipation && anticipation <= 10)) {


			if (title.contains("~")) {
				System.out.println("There is something wrong with the title you've given");
			}
			if (!(0 <= anticipation && anticipation <= 10)) {
				System.out.println("Only enter numbers between 1-10");
			}

			System.out.println("Please enter the name of the game you would like to add");
			title = userInput.nextLine();
			System.out.println("Please enter a number between 1 and 10 to rate much you want to play this game: ");
			try {
				anticipation = Double.parseDouble(userInput.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Please only enter numbers between 1-10");
				continue;
			}
			title = userInput.nextLine();

		}
		newGameToAdd = new Game(title, anticipation);

		if (gameList == currentlyPlayingList) {
//			newGameToAdd = new Game(title, anticipation, true);
//			gameList.addGame(newGameToAdd);
//			updateList(currentlyPlayingList);
			newGameToAdd.startGame();

		}
//		newGameToAdd = new Game(title, anticipation);
		gameList.addGame(newGameToAdd);
		updateList(wishList);
		updateList(currentlyPlayingList);

	}


		

	
	
	/**
	 * @method removeGamesMenu
	 * @param gameList (GameList)
	 * @return None
	 * 
	 * This method will get a title from the user and remove the game with a matching title from a given GameList
	 */
	private void removeGamesMenu(GameList gameList) {
		System.out.println("What is the name of the game you would like to remove: ");
		String title = userInput.nextLine();

		if (title.isEmpty()) {
			System.out.println("Returning to the previous menu");
			return;
		}
		while (!gameList.hasGame(title)) {
			System.out.println("There was no title by the name " + title + " found");
			System.out.println("What is the name of the game you would like to remove: ");
			title = userInput.nextLine();
		}
		gameList.removeGame(title); // i want to change this around to see if the game was actually removed

		updateList(wishList);
		updateList(currentlyPlayingList);
		updateList(previouslyPlayedList);
	}

	
	/**
	 * @method gameInformationMenu
	 * @param gameList (GameList)
	 * @return None
	 * 
	 */
	private void gameInformationMenu(GameList gameList) {
		System.out.println("What is the title of the game you would like to see? ");
		String title = userInput.nextLine();
		if (title.isEmpty()) {
			System.out.println("Returning to the previous menu");
			return;
		}
		while (!gameList.hasGame(title)) {
			System.out.println("There is no game named " + title);
			title = userInput.nextLine();
		}

		System.out.println(gameList.getGameInformationToString(title));
		
	}
	
	/**
	 * @method clearAllGamesCurrentlyPlayingMenu
	 * @param
	 * @return void
	 * clear all games inside of currentlyPlayingList
	 */
	private void clearGameListMenu(GameList gameList) {
		gameList.clearList();
		String output = "Your wishlist was successfully cleared.";
		System.out.println(output);
		updateList(wishList);
		updateList(currentlyPlayingList);
		updateList(previouslyPlayedList);
	}

	/**
	 * Moves a game from wishlist to currently playing
	 */
	private void beginGameMenu() {
		System.out.print("Please enter the name of the game you would like to start: ");
		String title = userInput.nextLine();
		if (title.isEmpty()) {
			System.out.println("Returning to the previous menu");
			return;
		}
		while (!wishList.hasGame(title)) {
			System.out.println("There is no game by the the title " + title + " in your wishList");
			System.out.print("Title: ");
			title = userInput.nextLine();
		}

		wishList.beginGame(title, currentlyPlayingList);
		System.out.println("Your game has been started!");

		updateList(wishList);
		updateList(currentlyPlayingList);
		
	}

	/**
	 * Completes a game, moving the game from currentlyPlayingList to previouslyPlayedList.
	 */
	private void finishGameMenu() {
		System.out.println("Please enter the name of the game you would like to Complete: ");
		String title = userInput.nextLine();
		while (!currentlyPlayingList.hasGame(title)) {
			System.out.println("There is no game by the the title " + title + " in your Currently Playing List");
			title = userInput.nextLine();
		}

		currentlyPlayingList.completeGame(title, previouslyPlayedList);
		System.out.println("Your game has been started!");

		updateList(currentlyPlayingList);
		updateList(previouslyPlayedList);

	}

	/**
	 *
	 * @param wishList
	 * saves wishlist to the users file
	 */
	private void updateList(WishList wishList) {
		try {
			userInformation.writeUserWishList(wishList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 *
	 * @param currentlyPlayingList
	 * saves currenylyPlayingList to the users file
	 */
	private void updateList(CurrentlyPlayingList currentlyPlayingList) {
		try {
			userInformation.writeUserCurrentlyPlayingList(currentlyPlayingList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 *
	 * @param previouslyPlayedList
	 * saves previouslyPlayedList to the users file
	 */
	private void updateList(PreviouslyPlayedList previouslyPlayedList) {
		try {
			userInformation.writeUserPreviouslyPlayedList(previouslyPlayedList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	}
	
