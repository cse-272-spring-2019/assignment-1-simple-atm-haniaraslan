package atm;

import javafx.application.Application;
import javafx.scene.Scene;
//import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
//import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import atm.User;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;

public class GUI extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	String amount;
	Scene menuScene, balanceScene, depositScene, withdrawScene, historyScene, errorIDScene, errorWithdrawScene,
			errorDepositScene, errorCharactersSceneD, errorCharactersSceneW, loginScene;
	Label historyTransaction, balanceLabel;
	User user = new User();
	boolean valid;
	int row = 0;

	public void start(Stage primaryStage) {

		balanceLabel = new Label(" Current Balance " + user.balanceInquiry());
		historyTransaction = new Label(user.getHistory(row));
		primaryStage.setTitle("ATM");

		// -----------------------------------------------------------------------------------------------------------------

		// TODO : display user login page
		Label cardIDLabel = new Label("  Card ID  ");
		Label passwordLabel = new Label("  Password  ");
		TextField cardIDField = new TextField();
		PasswordField passwordField = new PasswordField();
		Button submit = new Button("next");

		GridPane grid1 = new GridPane();
		grid1.add(cardIDLabel, 0, 1);
		grid1.add(passwordLabel, 0, 2);
		grid1.add(cardIDField, 2, 1);
		grid1.add(passwordField, 2, 2);
		grid1.setVgap(3);
		grid1.add(submit, 2, 7);

		submit.setOnAction(e -> {
			String fetchedID = cardIDField.getText();
			String fetchedPassword = passwordField.getText();

			if (user.userValidation(fetchedID, fetchedPassword) == true) {
				primaryStage.setScene(menuScene);
			} else {
				primaryStage.setScene(errorIDScene);

			}
		});

		loginScene = new Scene(grid1, 400, 200);
		// ----------------------------------------------------------------------------------------------------------------

		// TODO : display menu
		Button deposit = new Button("   Deposit   ");
		Button withdraw = new Button("  Withdraw ");
		Button balance = new Button(" current balance ");
		Button historyButton = new Button(" Balance History ");
		Button logout = new Button("  Logout  ");

		GridPane grid2 = new GridPane();

		grid2.setHgap(7);
		grid2.setVgap(2);
		grid2.add(balance, 5, 3);
		grid2.add(deposit, 14, 3);
		grid2.add(withdraw, 14, 11);
		grid2.add(historyButton, 5, 11);
		grid2.add(logout, 10, 14);

		balance.setOnAction(e -> primaryStage.setScene(balanceScene));
		deposit.setOnAction(e -> primaryStage.setScene(depositScene));
		withdraw.setOnAction(e -> primaryStage.setScene(withdrawScene));
		historyButton.setOnAction(e -> primaryStage.setScene(historyScene));
		logout.setOnAction(e -> primaryStage.setScene(loginScene));

		menuScene = new Scene(grid2, 400, 200);

		// -----------------------------------------------------------------------------------------------------------------

		// TODO : display balance page
		Button menu1 = new Button("  main menu  ");
		Button logout1 = new Button(" logout ");
		Button exit = new Button("  exit  ");

		GridPane grid3 = new GridPane();
		grid3.setHgap(3);
		grid3.add(balanceLabel, 1, 4);
		grid3.setVgap(3);
		grid3.add(logout1, 12, 8);
		grid3.add(menu1, 4, 7);
		grid3.setVgap(6);
		grid3.add(exit, 12, 9);

		exit.setOnAction(e -> System.exit(0));
		logout1.setOnAction(e -> primaryStage.setScene(loginScene));
		menu1.setOnAction(e -> primaryStage.setScene(menuScene));

		balanceScene = new Scene(grid3, 400, 200);

		// -----------------------------------------------------------------------------------------------------------------

		// TODO : display deposit page
		Button menu3 = new Button(" Back to menu ");
		Button next4 = new Button("  Next  ");
		Label depositLabel = new Label(" enter amount ");
		TextField depositField = new TextField();

		GridPane grid4 = new GridPane();
		grid4.setVgap(7);
		grid4.setHgap(6);
		grid4.add(depositLabel, 7, 2);
		grid4.add(depositField, 7, 4);
		grid4.add(next4, 8, 6);
		grid4.add(menu3, 4, 1);

		next4.setOnAction(e -> {
			amount = depositField.getText();
			try {
				Double.parseDouble(amount);
			}
			catch (NumberFormatException m) {
				primaryStage.setScene(errorCharactersSceneD);
			}
			valid = user.deposit(amount);
			if (!valid) {
					primaryStage.setScene(errorDepositScene);
			} else {

					historyTransaction.setText(user.getHistory(row));
					row ++;
					balanceLabel.setText(" Current Balance " + user.balanceInquiry());
					primaryStage.setScene(balanceScene);
				}
		});

		menu3.setOnAction(e -> primaryStage.setScene(menuScene));
		depositScene = new Scene(grid4, 400, 200);

		// -----------------------------------------------------------------------------------------------------------------

		// TODO : display withdraw page
		Button menu2 = new Button(" Back to menu ");
		Button next5 = new Button("  Next  ");
		Label withdrawLabel = new Label(" enter amount ");
		TextField withdrawField = new TextField();

		GridPane grid5 = new GridPane();
		grid5.setVgap(7);
		grid5.setHgap(6);
		grid5.add(withdrawLabel, 7, 2);
		grid5.add(withdrawField, 7, 4);
		grid5.add(next5, 8, 6);
		grid5.add(menu2, 4, 1);
		next5.setOnAction(e -> {
			amount = withdrawField.getText();
			try {
				Double.parseDouble(amount);
			}
			catch (NumberFormatException m) {
				primaryStage.setScene(errorCharactersSceneW);
			}
				valid = user.withdraw(amount);
				if (!valid) {
					primaryStage.setScene(errorWithdrawScene);
				} else {
					historyTransaction.setText(user.getHistory(row));
					row++;
					balanceLabel.setText(" Current Balance " + user.balanceInquiry());
					primaryStage.setScene(balanceScene);
				}
		});
		menu2.setOnAction(e -> primaryStage.setScene(menuScene));

		withdrawScene = new Scene(grid5, 400, 200);

		// -----------------------------------------------------------------------------------------------------------------

		// TODO : display ERROR! page
		Label errorID = new Label("  Incorrect User ID or Password");
		Label errorWithdraw = new Label("unavailable amount of money\nplease enter a vaild amount ");
		Label errorDeposit = new Label("unvaild amount of money\nplease enter a vaild amount ");
		Label errorCharactersD = new Label("Invalid! Cannot enter Characters");
		Label errorCharactersW = new Label("Invalid! Cannot enter Characters");
		
		Button tryAgainDeposit = new Button("Try Again");
		Button tryAgainID = new Button("Try Again");
		Button tryAgainWithdraw = new Button("Try Again");
		Button tryAgainCharactersD = new Button("Try Again");
		Button tryAgainCharactersW = new Button("Try Again");
		
		Button exitID = new Button(" exit ");
		Button exitDeposit = new Button(" exit ");
		Button exitWithdraw = new Button(" exit ");
		Button exitCharactersD = new Button(" exit ");
		Button exitCharactersW = new Button(" exit ");

		
		GridPane gridID = new GridPane();
		GridPane gridDeposit = new GridPane();
		GridPane gridWithdraw = new GridPane();
		GridPane gridCharactersD = new GridPane();
		GridPane gridCharactersW = new GridPane();
		
		gridCharactersD.setVgap(3);
		gridCharactersD.setHgap(9);
		gridCharactersD.add(errorCharactersD, 11, 4);
		gridCharactersD.add(tryAgainCharactersD, 12, 6);
		gridCharactersD.setVgap(6);
		gridCharactersD.add(exitCharactersD, 13, 13);

		exitCharactersD.setOnAction(e -> System.exit(0));
		tryAgainCharactersD.setOnAction(e -> primaryStage.setScene(depositScene));
		
		errorCharactersSceneD = new Scene(gridCharactersD, 400, 200);
		
		gridCharactersW.setVgap(3);
		gridCharactersW.setHgap(9);
		gridCharactersW.add(errorCharactersW, 11, 4);
		gridCharactersW.add(tryAgainCharactersW, 12, 6);
		gridCharactersW.setVgap(6);
		gridCharactersW.add(exitCharactersW, 13, 13);

		exitCharactersW.setOnAction(e -> System.exit(0));
		tryAgainCharactersW.setOnAction(e -> primaryStage.setScene(withdrawScene));
		
		errorCharactersSceneW = new Scene(gridCharactersW, 400, 200);


		gridID.setVgap(3);
		gridID.setHgap(9);
		gridID.add(errorID, 11, 4);
		gridID.add(tryAgainID, 12, 6);
		gridID.setVgap(6);
		gridID.add(exitID, 13, 13);

		exitID.setOnAction(e -> System.exit(0));
		tryAgainID.setOnAction(e -> primaryStage.setScene(loginScene));

		errorIDScene = new Scene(gridID, 400, 200);

		gridDeposit.setVgap(3);
		gridDeposit.setHgap(9);
		gridDeposit.add(errorDeposit, 11, 4);
		gridDeposit.add(tryAgainDeposit, 12, 6);
		gridDeposit.setVgap(4);
		gridDeposit.add(exitDeposit, 13, 13);

		exitDeposit.setOnAction(e -> System.exit(0));
		tryAgainDeposit.setOnAction(e -> primaryStage.setScene(depositScene));

		errorDepositScene = new Scene(gridDeposit, 400, 200);

		gridWithdraw.setVgap(4);
		gridWithdraw.setHgap(9);
		gridWithdraw.add(errorWithdraw, 11, 5);
		gridWithdraw.add(tryAgainWithdraw, 12, 7);
		gridWithdraw.setVgap(4);
		gridWithdraw.add(exitWithdraw, 13, 13);

		exitWithdraw.setOnAction(e -> System.exit(0));
		tryAgainWithdraw.setOnAction(e -> primaryStage.setScene(withdrawScene));

		errorWithdrawScene = new Scene(gridWithdraw, 400, 200);

		// --------------------------------------------------------------------------------------------------------------------

		// TODO : display history page
		Button menu = new Button("  back to menu  ");
		Button next = new Button("next");
		Button previous = new Button("previous");

		GridPane gridHistory = new GridPane();

		gridHistory.setVgap(5);
		gridHistory.setHgap(4);
		gridHistory.add(historyTransaction, 5, 2);
		gridHistory.add(menu, 1, 1);
		gridHistory.add(previous, 1, 19);
		gridHistory.add(next, 19, 19);
		
		next.setOnAction(e -> {
			row = row + 1;
			if (row > 4) {
				historyTransaction.setText("No more available transactions");
				
			}
			
			historyTransaction.setText(user.getHistory(row));

			primaryStage.setScene(historyScene);
		});
		previous.setOnAction(e -> {
			row = row - 1;
			if (row <= 0) {
				historyTransaction.setText("No more available transactions");
			}
			historyTransaction.setText(user.getHistory(row));

			primaryStage.setScene(historyScene);
		});
		menu.setOnAction(e -> primaryStage.setScene(menuScene));

		historyScene = new Scene(gridHistory, 400, 200);

		// --------------------------------------------------------------------------------------------------------------------
		primaryStage.setScene(loginScene);
		primaryStage.show();

	}
}
