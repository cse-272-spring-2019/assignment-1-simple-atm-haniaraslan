package atm;

import java.util.Date;
import java.util.Scanner;

public class User {
	private String ID = "123456";
	private String password = "0000";
	int row = 0;
	private double balance, amount;
	private String[] history = new String[5];
	Scanner scan = new Scanner(System.in);
	private Date date = new Date();

	public User() {
		balance = 0.0;

	}

	public boolean userValidation(String enteredID, String enteredpassword) {

		return ((enteredID.endsWith(ID)) && (enteredpassword.equals(password)));
	}

	public boolean deposit(String enteredAmount) {
		amount = Double.valueOf(enteredAmount);

		if (amount < 0)
			return false;
		else {
			this.balance += amount;
			history[row%5] = ("deposit : " + Double.toString(amount) + "\nat : " + date.toString());
			row++;
//			if (row == 5) {
//				for (int i = 0 ; i< 5; i++) {
//					history[i] = history[i+1];
//				}
//			}
		}
		return true;
	}

	public boolean withdraw ( String enteredAmount ) {
		amount = Double.valueOf(enteredAmount);

		if (amount > balance)
			return false;
		else {
			balance -= amount;
			history[row%5] = ("withdraw : " + Double.toString(amount) + "\nat : " + date.toString());
			row++;
//			if (row == 5) {
//				for (int i = 0 ; i< 5; i++) {
//					history[i] = history[i+1];
//				}
//			}
		}
		return true;
	}

	public double balanceInquiry() {

		return this.balance;
	}
	

	public String getHistory(int row) {
		return (history[row%5]);
	}

}
