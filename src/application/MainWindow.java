package application;

import java.awt.Toolkit;
import java.math.BigDecimal;
import java.util.Optional;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainWindow extends Application {
	
	private User user;
	private static Alert alert;
	
	public MainWindow(User user) {
		this.user = user;
	}
	
	@Override
	public void start(Stage stage) {
        //String version = System.getProperty("java.version");
        //Label l = new Label ("Hello, JavaFX 11, running on "+version);
        //Scene scene = new Scene (new StackPane(l), 300, 200);
		
		// The box to contain the whole layout. Please read more on Vertical boxes layout on java FX
		VBox wrapperBox = new VBox ();
		// Set a padding for the VBox (top, left, bottom, right)
		wrapperBox.setPadding(new Insets(80,60,10,60));
		wrapperBox.setSpacing(50);
		
		// Of course you know how a label works already but the line below it is the style i choose to give this label
		// You can tweak this styling. Read on java FX css styling
		// {{name}} will be replaced with our user's name from DB during our next pull
		Label welcomeMssg = new Label("Welcome "+ this.user.getName() +" to NEO Bank, Happy banking !! \n"+this.user.getBalance()+"");
		welcomeMssg.setStyle("-fx-font-family:monospace;"
					        	+"-fx-font-size:29px;");
		
		// Border pane here holds the vertically aligned buttons in the left and right screen position
		BorderPane borderPane = new BorderPane();
		// VBox = vertical box -> holds my left and right aligned buttons
		VBox leftButtons = new VBox();
		leftButtons.setSpacing(20);

		VBox rightButtons = new VBox();
		rightButtons.setSpacing(20);
		
		/**
		 * All buttons to display at the left side of the screen.
		 * Note the preferred width and height are all equal (for uniformity)
		 * Please the "BUY AIRTIME" button, feel free to change it to whatever you want 
		 * we will add click events to all buttons at the time of our next git pull
		 */
		Button withdrawBtn = new Button("WITHDRAW");
		withdrawBtn.setPrefWidth(280);
		withdrawBtn.setPrefHeight(60);
		withdrawBtn.setOnAction(e -> processWithdraw(e, stage, this.user));
		
		Button depositBtn = new Button("DEPOSIT");
		depositBtn.setPrefWidth(280);
		depositBtn.setPrefHeight(60);
		depositBtn.setOnAction(e -> processDeposit(e, stage, this.user));
		
		Button transferBtn = new Button("TRANSFER");
		transferBtn.setPrefWidth(280);
		transferBtn.setPrefHeight(60);
		transferBtn.setOnAction(e -> processTransfer(e, stage, this.user));
		
		Button airtimeBtn = new Button("BUY AIRTIME");
		airtimeBtn.setPrefWidth(280);
		airtimeBtn.setPrefHeight(60);
		airtimeBtn.setOnAction(e -> processBuyAirtime(e, stage, this.user));
		
		// Adding all the buttons to the leftButtons VBox (vertical box) so they can be placed vertically one after the other
		leftButtons.getChildren().addAll(withdrawBtn, depositBtn, transferBtn, airtimeBtn);
		// adding the leftButtons VBox to the left side using the border pane layout.
		// please try to read more on border pane layout.
		borderPane.setLeft(leftButtons);
		
		
		
			
		
		
		
		
		
		/**
		 * All buttons to display at the right side of the screen.
		 * Note the preferred width and height are all equal (for uniformity)
		 * we will add click events to all buttons at the time of our next git pull
		 */
		Button statement_balance_Btn = new Button("STATEMENT & BALANCE");
		statement_balance_Btn.setPrefWidth(280);
		statement_balance_Btn.setPrefHeight(60);
		statement_balance_Btn.setOnAction(e -> statementAndBalance(e, user, stage));
		
		Button pay_beneficiaryBtn = new Button("PAY BENEFICIARY");
		pay_beneficiaryBtn.setPrefWidth(280);
		pay_beneficiaryBtn.setPrefHeight(60);
		transferBtn.setOnAction(e -> processBeneficiary(e, stage, this.user));
		
		Button change_pinBtn = new Button("CHANGE PIN");
		change_pinBtn.setPrefWidth(280);
		change_pinBtn.setPrefHeight(60);
		change_pinBtn.setOnAction(e -> changePin(e, stage, this.user));
		
		Button quitBtn = new Button("QUIT");
		quitBtn.setPrefWidth(280);
		quitBtn.setPrefHeight(60);
		quitBtn.setOnAction(e -> quit(stage));
		
		// Adding all the buttons to the rightButtons VBox (vertical box) so they can be placed vertically one after the other
		rightButtons.getChildren().addAll(statement_balance_Btn, pay_beneficiaryBtn, change_pinBtn, quitBtn);
		// adding the leftButtons VBox to the right side using the border pane layout.
		// please try to read more on border pane layout.
		borderPane.setRight(rightButtons);
		
		// My wrapper box should stack contents at the top center 
		// Note: it contains the welcome message at top and the border pane which contains the left and right buttons
		wrapperBox.setAlignment(Pos.TOP_CENTER);
		wrapperBox.getChildren().addAll(welcomeMssg, borderPane);
		
		// Scene to display on stage
		Scene scene = new Scene(wrapperBox);
		// Gives the stage/window a name
		stage.setTitle("My ATMInterface Project");
        stage.setScene(scene);
        stage.show();
        stage.setMaximized(true);
    }

	
	private void processBeneficiary(ActionEvent e, Stage stage, User user2) {
		// Creates a dialog box to carry out the transfer operation
		Dialog<ButtonType> dialog = new Dialog<>();
		dialog.setTitle("PAY BENEFICIARY");
		dialog.setHeaderText("Choose the beneficiary account number you want to transfer to and click on 'Continue'");
		dialog.setHeight(300);
		dialog.setWidth(300);
		dialog.initOwner(stage);
		ButtonType continueBtn = new ButtonType("Continue", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(continueBtn);
		
		// Grid pane(layout) containing the form to be set in the dialog box 
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		// Transfer form
		
		Label acc = new Label("Beneficiary Account: ");
		TextField accNoTxt = new TextField();
		accNoTxt.setPromptText("Enter the destination account number");
		accNoTxt.setOnKeyPressed(evt -> intOnly(evt, accNoTxt));
		
		
		Label transferAmt = new Label("Amount: ");
		TextField transferAmtTxt = new TextField();
		transferAmtTxt.setPromptText("Enter the amount to transfer");
		transferAmtTxt.setOnKeyPressed(evt -> intOnly(evt, transferAmtTxt));
		// Button indicating currency symbol for amount field
		Button zarBtn = new Button("ZAR");
		/**
		 * Add the currency symbol button (nairaBtn in this case) and the amount text field in the inputGroup horizontal box
		 * NOTE: This is to create a fancy looking amount text field with the currency symbol button prepended to it
		 * e.g in the case of bootstrap's input-group class.
		 */
		HBox inputGroup = new HBox(zarBtn, transferAmtTxt);
		inputGroup.setSpacing(-5);
		
		
		// Add the form to the grid pane
		grid.addRow(0, acc, accNoTxt);
		grid.addRow(0, transferAmt, inputGroup);
		

		Platform.runLater(() -> accNoTxt.requestFocus());
		dialog.getDialogPane().setContent(grid);
		Optional<ButtonType> result = dialog.showAndWait();
		try {
			if (result.get() == continueBtn) {
				if( !(accNoTxt.getText()==null||transferAmtTxt.getText().trim().equals("")) ) {	
					int i = processTransfer(user, Float.valueOf(transferAmtTxt.getText()), accNoTxt.getText());
					 if(i>0) {
						Message.info("PAY BENEFICIARY", "Transfer Successful !", "Successfully transferred ZAR "+transferAmtTxt.getText()+" to "+accNoTxt.getText()+". Your new account balance is ZAR "+ user.getBalance() +"", stage, alert);
						Transaction t = new Transaction(user.getId(), "TRANSFER", BigDecimal.valueOf(Long.parseLong(transferAmtTxt.getText())), new UsersDAO().getUser(accNoTxt.getText()).getId());
						System.out.println(new UsersDAO().getUser(accNoTxt.getText()).getId());
						new TransactionDAO().save(t);
							
						// Refresh the stage
						Stage newStage = new Stage();
						MainWindow newWindow = new MainWindow(user);
						try {
							newWindow.start(newStage);
						} catch (Exception e1) {
							// ERROR 001 - Unable to start a stage
							e1.printStackTrace();
						}
						newStage.show();
						stage.close();
					 }
					 else if(i == -1) {
						 Message.error("PAY BENEFICIARY", "Process incomplete", "Unable to make this transfer, user with provided account number does not exist", stage, alert);
					 }
					 else {
						 Message.error("PAY BENEFICIARY", "Process incomplete", "Unable to process your request", stage, alert);
					 }
				}
				else {
					Message.error("PAY BENEFICIARY", "Process incomplete", "Available field must be filled", stage, alert);
				}
			}
			else {
				e.consume();
			}
		}
		catch(Exception Ex) {
			e.consume();
		}
	}

	private void statementAndBalance(ActionEvent e, User user, Stage stage) {
		Stage newStage = new Stage();
		BalAndStmtWindow newWindow = new BalAndStmtWindow(user);
		try {
			newWindow.start(newStage);
		} catch (Exception e1) {
			// ERROR 001 - Unable to start a stage
			e1.printStackTrace();
		}
		newStage.show();
		//stage.close();
	}
	
	
	/**
	 * This method gets called when the user clicks on the buy airtime button
	 * @param e
	 * @param stage
	 * @param user2
	 */
	private void processBuyAirtime(ActionEvent e, Stage stage, User user) {
		// Creates a dialog box to carry out the change password operation
		Dialog<ButtonType> dialog = new Dialog<>();
		dialog.setTitle("BUY AIRTIME");
		dialog.setHeaderText("Enter the amount you wish to deposit and click 'Authorize'\n Please note that tist airtime will be topped up to the phone number linked to thia account");
		dialog.setHeight(300);
		dialog.setWidth(300);
		dialog.initOwner(stage);
		ButtonType authorizeBtn = new ButtonType("Authorize", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(authorizeBtn);
		
		// Grid pane(layout) containing the form to be set in the dialog box 
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));
	
		// Change password form
		Label depositAmt = new Label("Amount: ");
		TextField depositAmtTxt = new TextField();
		depositAmtTxt.setPromptText("Enter airtime amount to top up");
		depositAmtTxt.setOnKeyPressed(evt -> intOnly(evt, depositAmtTxt));
		// Button indicating currency symbol for amount field
		Button zarBtn = new Button("ZAR");
		/**
		 * Add the currency symbol button (nairaBtn in this case) and the amount text field in the inputGroup horizontal box
		 * NOTE: This is to create a fancy looking amount text field with the currency symbol button prepended to it
		 * e.g in the case of bootstrap's input-group class.
		 */
		HBox inputGroup = new HBox(zarBtn, depositAmtTxt);
		inputGroup.setSpacing(-5);
		
		
		// Add the form to the grid pane
		grid.addRow(0, depositAmt, inputGroup);
		
	
		Platform.runLater(() -> depositAmtTxt.requestFocus());
		dialog.getDialogPane().setContent(grid);
		Optional<ButtonType> result = dialog.showAndWait();
		try {
			
			if (result.get() == authorizeBtn) {
				if( !(depositAmtTxt.getText()==null||depositAmtTxt.getText().trim().equals("")) ) {
					TextInputDialog txtDialog = new TextInputDialog();
					txtDialog.setTitle("Security check");
					txtDialog.setHeaderText("Enter your pin to continue");
					txtDialog.setContentText("Please enter your pin:");
					txtDialog.initOwner(stage);
	
					Optional<String> txtDialogResult = txtDialog.showAndWait();
					if (txtDialogResult.isPresent()) {
						String entererPin = txtDialogResult.get();
						try {
							Integer.parseInt(entererPin);
						}
						catch(Exception ex) {
							Message.error("BUY AIRTIME", "Process incomplete", "You provided an incorrect pin", stage, alert);
						}
						
						
						if(user.getPin() == Integer.parseInt(entererPin)) {
							
							user.setBalance(user.getBalance()-Float.valueOf(depositAmtTxt.getText()));
							int i = new UsersDAO().update(user);
		    				 if(i>0) {
								Message.info("BUY AIRTIME", "Airtime Top up Successful !", "Your new account balance is ZAR "+ user.getBalance() +"", stage, alert);
								Transaction t = new Transaction(user.getId(), "WITHDRAW", BigDecimal.valueOf(Long.parseLong(depositAmtTxt.getText()), 2));
								new TransactionDAO().save(t);
								// Refresh the stage
								Stage newStage = new Stage();
								MainWindow newWindow = new MainWindow(user);
								try {
									newWindow.start(newStage);
								} catch (Exception e1) {
									// ERROR 001 - Unable to start a stage
									e1.printStackTrace();
								}
								newStage.show();
								stage.close();
		    				 }
		    				 else {
		    					 Message.error("BUY AIRTIME", "Process incomplete", "Unable to process your request", stage, alert);
		    				 }
						}
						else {
							Message.error("BUY AIRTIME", "Process incomplete", "You provided an incorrect pin", stage, alert);
						}
					}
					else {
						Message.error("BUY AIRTIME", "Process incomplete", "Available field must be filled", stage, alert);
					}
				}
				else {
					Message.error("BUY AIRTIME", "Process incomplete", "Available field must be filled", stage, alert);
				}
			}
			else {
				e.consume();
			}
		}
		catch(Exception Ex) {
			e.consume();
		}
	}
	
	

	/**
	 * This methods processes user transfer, debits the initiator of the transfer and credits the receiver of the transfer
	 * @param e
	 * @param stage
	 * @param user2
	 */
	private void processTransfer(ActionEvent e, Stage stage, User user) {
		// Creates a dialog box to carry out the transfer operation
		Dialog<ButtonType> dialog = new Dialog<>();
		dialog.setTitle("TRANSFER FUND");
		dialog.setHeaderText("Enter the account number you want to transfer to and click on 'Continue'");
		dialog.setHeight(300);
		dialog.setWidth(300);
		dialog.initOwner(stage);
		ButtonType continueBtn = new ButtonType("Continue", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(continueBtn);
		
		// Grid pane(layout) containing the form to be set in the dialog box 
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		// Transfer form
		
		Label acc = new Label("Account Number: ");
		TextField accNoTxt = new TextField();
		accNoTxt.setPromptText("Enter the destination account number");
		accNoTxt.setOnKeyPressed(evt -> intOnly(evt, accNoTxt));
		
		
		Label transferAmt = new Label("Amount: ");
		TextField transferAmtTxt = new TextField();
		transferAmtTxt.setPromptText("Enter the amount to transfer");
		transferAmtTxt.setOnKeyPressed(evt -> intOnly(evt, transferAmtTxt));
		// Button indicating currency symbol for amount field
		Button zarBtn = new Button("ZAR");
		/**
		 * Add the currency symbol button (nairaBtn in this case) and the amount text field in the inputGroup horizontal box
		 * NOTE: This is to create a fancy looking amount text field with the currency symbol button prepended to it
		 * e.g in the case of bootstrap's input-group class.
		 */
		HBox inputGroup = new HBox(zarBtn, transferAmtTxt);
		inputGroup.setSpacing(-5);
		
		
		// Add the form to the grid pane
		grid.addRow(0, acc, accNoTxt);
		grid.addRow(0, transferAmt, inputGroup);
		

		Platform.runLater(() -> accNoTxt.requestFocus());
		dialog.getDialogPane().setContent(grid);
		Optional<ButtonType> result = dialog.showAndWait();
		try {
			if (result.get() == continueBtn) {
				if( !(accNoTxt.getText()==null||transferAmtTxt.getText().trim().equals("")) ) {	
					int i = processTransfer(user, Float.valueOf(transferAmtTxt.getText()), accNoTxt.getText());
					 if(i>0) {
						Message.info("TRANSFER FUND", "Transfer Successful !", "Successfully transferred ZAR "+transferAmtTxt.getText()+" to "+accNoTxt.getText()+". Your new account balance is ZAR "+ user.getBalance() +"", stage, alert);
						Transaction t = new Transaction(user.getId(), "TRANSFER", BigDecimal.valueOf(Long.parseLong(transferAmtTxt.getText())), new UsersDAO().getUser(accNoTxt.getText()).getId());
						System.out.println(new UsersDAO().getUser(accNoTxt.getText()).getId());
						new TransactionDAO().save(t);
						
						int beneficiaryAdd = Message.confirm("ADD BENEFICIARY", null, "Do you want to save "+accNoTxt.getText()+" as a beneficiary ?", "YES", "NO", stage, alert);
						if(beneficiaryAdd == 1) addBeneficiary(user, accNoTxt.getText(), stage);
							
							
						// Refresh the stage
						Stage newStage = new Stage();
						MainWindow newWindow = new MainWindow(user);
						try {
							newWindow.start(newStage);
						} catch (Exception e1) {
							// ERROR 001 - Unable to start a stage
							e1.printStackTrace();
						}
						newStage.show();
						stage.close();
					 }
					 else if(i == -1) {
						 Message.error("TRANSFER FUND", "Process incomplete", "Unable to make this transfer, user with provided account number does not exist", stage, alert);
					 }
					 else {
						 Message.error("TRANSFER FUND", "Process incomplete", "Unable to process your request", stage, alert);
					 }
				}
				else {
					Message.error("TRANSFER FUND", "Process incomplete", "Available field must be filled", stage, alert);
				}
			}
			else {
				e.consume();
			}
		}
		catch(Exception Ex) {
			e.consume();
		}
	}
	
	
	
	/**
	 * Method for adding a beneficiary after successful transfer
	 * @param user
	 * @param beneficiary
	 * @param stage
	 */
	private void addBeneficiary(User user, String beneficiary, Stage stage) {
		user.setBeneficiaries(beneficiary+"|");
		new UsersDAO().update(user);
		
		Message.info("ADD BENEFICIARY", null, "Successfully added "+beneficiary+" as a beneficiary", stage, alert);
	}
	
	

	/**
	 * This method gets called when a user initiates a transfer
	 * @param user The user who initiated the transfer
	 * @param amount The amount to transfer
	 * @param destinationAccNo The receiver of the transferred fund
	 * @return an integer > 0 if transfer was successful
	 */
	private int processTransfer(User user, Float amount, String destinationAccNo) {
		// Checks if a user with the account number to transfer to exists
		if(new UsersDAO().getUser(destinationAccNo) == null) return -1;
		
		// Debit sender
		user.setBalance(user.getBalance()-amount);
		
		// Initialize and credit receiver
		User receiver = new UsersDAO().getUser(destinationAccNo);
		receiver.setBalance(receiver.getBalance()+amount);
		
		int debited = new UsersDAO().update(user);
		int credited = new UsersDAO().update(receiver);
		
		if(debited==credited && debited>0) {
			return 1;
		}
		
		return 0;
	}

	/**
	 * This method gets called when a user makes a withdraw
	 * @param e The action event source
	 * @param stage The stage on which the action was performed
	 * @param user2 The user object trigerring the action
	 */
	private void processWithdraw(ActionEvent e, Stage stage, User user) {
		Stage newStage = new Stage();
		WithdrawWindow newWindow = new WithdrawWindow(user);
		try {
			newWindow.start(newStage);
		} catch (Exception e1) {
			// ERROR 001 - Unable to start a stage
			e1.printStackTrace();
		}
		newStage.show();
		stage.close();
	}
	

	/**
	 * This method gets called when a user tries to make a deposit
	 * @param e The action event source
	 * @param stage The stage on which the action was performed
	 * @param user2 The user object trigerring the action
	 */
	private void processDeposit(ActionEvent e, Stage stage, User user) {
		// Creates a dialog box to carry out the change password operation
		Dialog<ButtonType> dialog = new Dialog<>();
		dialog.setTitle("MAKE A DEPOSIT");
		dialog.setHeaderText("Enter the amount you wish to deposit and click 'Authorize'");
		dialog.setHeight(300);
		dialog.setWidth(300);
		dialog.initOwner(stage);
		ButtonType authorizeBtn = new ButtonType("Authorize", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(authorizeBtn);
		
		// Grid pane(layout) containing the form to be set in the dialog box 
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		// Change password form
		Label depositAmt = new Label("Amount: ");
		TextField depositAmtTxt = new TextField();
		depositAmtTxt.setPromptText("Enter the amount to deposit");
		depositAmtTxt.setOnKeyPressed(evt -> intOnly(evt, depositAmtTxt));
		// Button indicating currency symbol for amount field
		Button zarBtn = new Button("ZAR");
		/**
		 * Add the currency symbol button zarBtn in this case) and the amount text field in the inputGroup horizontal box
		 * NOTE: This is to create a fancy looking amount text field with the currency symbol button prepended to it
		 * e.g in the case of bootstrap's input-group class.
		 */
		HBox inputGroup = new HBox(zarBtn, depositAmtTxt);
		inputGroup.setSpacing(-5);
		
		
		// Add the form to the grid pane
		grid.addRow(0, depositAmt, inputGroup);
		

		Platform.runLater(() -> depositAmtTxt.requestFocus());
		dialog.getDialogPane().setContent(grid);
		Optional<ButtonType> result = dialog.showAndWait();
		try {
			
			if (result.get() == authorizeBtn) {
				if( !(depositAmtTxt.getText()==null||depositAmtTxt.getText().trim().equals("")) ) {
					TextInputDialog txtDialog = new TextInputDialog();
					txtDialog.setTitle("Security check");
					txtDialog.setHeaderText("Enter your pin to continue");
					txtDialog.setContentText("Please enter your pin:");
					txtDialog.initOwner(stage);

					Optional<String> txtDialogResult = txtDialog.showAndWait();
					if (txtDialogResult.isPresent()) {
						String entererPin = txtDialogResult.get();
						try {
							Integer.parseInt(entererPin);
						}
						catch(Exception ex) {
							Message.error("MAKE DEPOSIT", "Process incomplete", "You provided an incorrect pin", stage, alert);
						}
						
						
						if(user.getPin() == Integer.parseInt(entererPin)) {
							
							fundUser(user, Float.valueOf(depositAmtTxt.getText()));
							int i = new UsersDAO().update(user);
		    				 if(i>0) {
								Message.info("MAKE DEPOSIT", "Deposit Successful !", "Your new account balance is ZAR "+ user.getBalance() +"", stage, alert);
								Transaction t = new Transaction(user.getId(), "DEPOSIT", BigDecimal.valueOf(Long.parseLong(depositAmtTxt.getText()), 2));
								new TransactionDAO().save(t);
								// Refresh the stage
								Stage newStage = new Stage();
								MainWindow newWindow = new MainWindow(user);
								try {
									newWindow.start(newStage);
								} catch (Exception e1) {
									// ERROR 001 - Unable to start a stage
									e1.printStackTrace();
								}
								newStage.show();
								stage.close();
		    				 }
		    				 else {
		    					 Message.error("MAKE DEPOSIT", "Process incomplete", "Unable to process your request", stage, alert);
		    				 }
						}
						else {
							Message.error("MAKE DEPOSIT", "Process incomplete", "You provided an incorrect pin", stage, alert);
						}
					}
					else {
						Message.error("MAKE DEPOSIT", "Process incomplete", "Available field must be filled", stage, alert);
					}
				}
				else {
					Message.error("MAKE DEPOSIT", "Process incomplete", "Available field must be filled", stage, alert);
				}
			}
			else {
				e.consume();
			}
		}
		catch(Exception Ex) {
			e.consume();
		}
	}
	
	

	/**
	 * Logout method which closes the current stage and opens the auth/login stage
	 * @param stage The current stage
	 */
	private void quit(Stage stage) {
		if(Message.confirm("Quit", "Are you sure you want to quit ?", null, "YES", "NO", stage, alert) == 1) {
			Stage newStage = new Stage();
			Auth newWindow = new Auth();
			try {
				newWindow.start(newStage);
			} catch (Exception e1) {
				// ERROR 001 - Unable to start a stage
				e1.printStackTrace();
			}
			newStage.show();
			stage.close();
		}
		else {
			return;
		}
	}
	
	
	/**
	 * Change Pin method.
	 * Gets called when the user clicks on the change pin button
	 * @param stage The current stage where the action was performed
	 * @param user The current user object
	 */
	private void changePin(Event e, Stage stage, User user) {
		// Creates a dialog box to carry out the change password operation
		Dialog<ButtonType> dialog = new Dialog<>();
		dialog.setTitle("Change pin");
		dialog.setHeaderText("Please, fIll the form below and click on proceed");
		dialog.initOwner(stage);
		ButtonType proceedBtn = new ButtonType("Proceed", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(proceedBtn);
		
		// Grid pane(layout) containing the form to be set in the dialog box 
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		// Change password form
		Label password1 = new Label("Enter old pin: ");
		PasswordField passwrd1 = new PasswordField();
		passwrd1.setPromptText("Old password");
		Label password2 = new Label("Enter new pin: ");
		PasswordField passwrd2 = new PasswordField();
		passwrd2.setPromptText("New password");
		Label password3 = new Label("Confirm new pin: ");
		PasswordField passwrd3 = new PasswordField();
		passwrd3.setPromptText("confirm new pin");
		
		// Add the form to the grid pane
		grid.addRow(0, password1, passwrd1);
		grid.addRow(2, password2, passwrd2);
		grid.addRow(4, password3, passwrd3);
		

		Platform.runLater(() -> passwrd1.requestFocus());
		dialog.getDialogPane().setContent(grid);
		Optional<ButtonType> result = dialog.showAndWait();
		try {
			
			if (result.get() == proceedBtn) {
				if(!(passwrd1==null||passwrd1.getText().trim().equals("")) && !(passwrd2==null||passwrd2.getText().trim().equals("")) && !(passwrd3==null||passwrd3.getText().trim().equals(""))) {
					if(user.getPin() == Integer.parseInt(passwrd1.getText())) {
						if((passwrd2.getText().equals(passwrd3.getText()))) {
							user.setPin(Integer.valueOf(passwrd2.getText()));
							int i = new UsersDAO().update(user);
		    				 if(i>0) {
								Message.info("Change pin", "Process complete", "Pin changed successfully !", stage, alert);
		    				 }
		    				 else {
		    					 Message.error("Change pin", "Process incomplete", "Unable to change pin", stage, alert);
		    				 }
						}
						else {
							Message.error("Change pin", "Process incomplete", "New Pins not matching", stage, alert);
						}
					}
					else {
						Message.error("Change pin", "Process incomplete", "Old pin not correct", stage, alert);
					}
				}
				else {
					Message.error("Change pin", "Process incomplete", "All fields must be filled", stage, alert);
				}
			}
			else {
				e.consume();
			}
		}
		catch(Exception Ex) {
			e.consume();
		}
	}
	
	
	
	/**
	 * This method ensures that integers are the only input entered in a text field
	 * @param e The key event source
	 * @param The field whose input is been validated
	 */
	private void intOnly(KeyEvent e, TextField field) {
		if(!((e.getCode().isDigitKey()) || (e.getCode().equals(KeyCode.BACK_SPACE) ) || (e.getCode().isArrowKey()) || (e.getCode().equals(KeyCode.DELETE)))) {
			Toolkit.getDefaultToolkit().beep();
			field.setEditable(false);
		}
		else {
			field.setEditable(true);
		}
	}
	
	
	
	
	private void fundUser(User user, float amount) {
		float newBalance = user.getBalance() + amount;
		user.setBalance(newBalance);
		return;	
	}
	
}
