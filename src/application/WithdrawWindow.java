package application;

import java.math.BigDecimal;
import java.util.Optional;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class WithdrawWindow extends Application {
	
	private User user;
	private static Alert alert;
	
	public WithdrawWindow(User user) {
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
		
		
		Label welcomeMssg = new Label("Please, click on the amount you wish to withdraw");
		welcomeMssg.setStyle("-fx-font-family:monospace;"
					        	+"-fx-font-size:29px;");
		Label noteMssg = new Label("NOTE: This ATM is unable to print receipt at the moment");
		noteMssg.setStyle("-fx-font-family:monospace;"
					        	+"-fx-font-size:22px;");
		
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
		 */
		Button _500Btn = new Button("500");
		_500Btn.setPrefWidth(280);
		_500Btn.setPrefHeight(60);
		_500Btn.setOnAction(e -> withdraw(e, stage, user, _500Btn.getText()));
		
		Button _1000Btn = new Button("1000");
		_1000Btn.setPrefWidth(280);
		_1000Btn.setPrefHeight(60);
		_1000Btn.setOnAction(e -> withdraw(e, stage, user, _1000Btn.getText()));
		
		Button _5000Btn = new Button("5000");
		_5000Btn.setPrefWidth(280);
		_5000Btn.setPrefHeight(60);
		_5000Btn.setOnAction(e -> withdraw(e, stage, user, _5000Btn.getText()));
		
		Button _10000Btn = new Button("10000");
		_10000Btn.setPrefWidth(280);
		_10000Btn.setPrefHeight(60);
		_10000Btn.setOnAction(e -> withdraw(e, stage, user, _10000Btn.getText()));
		
		// Adding all the buttons to the leftButtons VBox (vertical box) so they can be placed vertically one after the other
		leftButtons.getChildren().addAll(_500Btn, _1000Btn, _5000Btn, _10000Btn);
		// adding the leftButtons VBox to the left side using the border pane layout.
		// please try to read more on border pane layout.
		borderPane.setLeft(leftButtons);
		
		
		
			
		
		
		
		
		
		/**
		 * All buttons to display at the right side of the screen.
		 * Note the preferred width and height are all equal (for uniformity)
		 */
		Button _20000Btn = new Button("20000");
		_20000Btn.setPrefWidth(280);
		_20000Btn.setPrefHeight(60);
		_20000Btn.setOnAction(e -> withdraw(e, stage, user, _20000Btn.getText()));
		
		Button _25000Btn = new Button("25000");
		_25000Btn.setPrefWidth(280);
		_25000Btn.setPrefHeight(60);
		_25000Btn.setOnAction(e -> withdraw(e, stage, user, _25000Btn.getText()));
		
		Button otherAmtBtn = new Button("Other Amount");
		otherAmtBtn.setPrefWidth(280);
		otherAmtBtn.setPrefHeight(60);
		otherAmtBtn.setOnAction(e -> customWithdraw(e, stage, user));
		
		Button backBtn = new Button("Go Back");
		backBtn.setPrefWidth(280);
		backBtn.setPrefHeight(60);
		backBtn.setOnAction(e -> goBack(e, stage, user));
		
		// Adding all the buttons to the rightButtons VBox (vertical box) so they can be placed vertically one after the other
		rightButtons.getChildren().addAll(_20000Btn, _25000Btn, otherAmtBtn, backBtn);
		// adding the leftButtons VBox to the right side using the border pane layout.
		// please try to read more on border pane layout.
		borderPane.setRight(rightButtons);
		
		// My wrapper box should stack contents at the top center 
		// Note: it contains the welcome message at top and the border pane which contains the left and right buttons
		wrapperBox.setAlignment(Pos.TOP_CENTER);
		wrapperBox.getChildren().addAll(welcomeMssg, noteMssg, borderPane);
		
		// Scene to display on stage
		Scene scene = new Scene(wrapperBox);
		// Gives the stage/window a name
		stage.setTitle("My ATMInterface Project - Withdraw Funds");
        stage.setScene(scene);
        stage.show();
        stage.setMaximized(true);
    }

	
	
	/** 
	 * This method gets called when a user makes a withdraw
	 * 
	 * @param e The action event source
	 * @param stage The stage on which the action was performed
	 * @param user The user object trigerring the action
	 * @param amt The amount set to withdraw
	 */
	private void withdraw(ActionEvent e, Stage stage, User user, String amt) {
		float userAmt = user.getBalance();
		float withdrawAmt;
		try {
			Float.parseFloat(amt);
		}
		catch(Exception ex) {
			Message.error("Invalid Amount", null, "The system could not recognize the amount you entered.", stage, alert);
		}
		withdrawAmt = Float.parseFloat(amt);
		
		// If the amount to withdraw is more than the user amount
		if(withdrawAmt > userAmt) {
			Message.error("Insuffcient Fund", null, "You do not have sufficient fund to carry out this transaction", stage, alert);
		}
		
		user.setBalance(userAmt-withdrawAmt);
		Transaction t = new Transaction(user.getId(), "WITHDRAW", BigDecimal.valueOf((long) withdrawAmt, 2));
		new TransactionDAO().save(t);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		Message.info("Withdraw Successful", "Please take your cash", "Your new account balance is"+user.getBalance()+"", stage, alert);
		goBack(null, stage, user);
		
		return;
	}
	

	

	/**
	 * Logout method which closes the current stage and opens the auth/login stage
	 * @param e Action event source
	 * @param stage The current stage
	 * @param user 
	 */
	private void goBack(ActionEvent e, Stage stage, User user) {
		if(Message.confirm("Go Back", "Do you want to end this transaction?", null, "YES", "NO", stage, alert) == 1) {
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
			return;
		}
	}
	
	
	
	/**
	 * Withdraw method for other amount (custom amount)
	 * Gets called when the user clicks on the 'Other Amount' button
	 * @param stage The current stage where the action was performed
	 * @param user The current user object
	 */
	private void customWithdraw(ActionEvent e, Stage stage, User user) {
		// Creates a dialog box to carry out the change password operation
		Dialog<ButtonType> dialog = new Dialog<>();
		dialog.setTitle("Withdraw Other Amount");
		dialog.setHeaderText("Please, enter the amount you want to withdraw and continue");
		dialog.initOwner(stage);
		ButtonType withdrawBtn = new ButtonType("Withdraw", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(withdrawBtn);
		
		// Grid pane(layout) containing the form to be set in the dialog box 
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		// Change password form
		Label amount = new Label("Amount: ");
		TextField amtText = new TextField();
		amtText.setPromptText("Enter the amount you want to withdraw");
		
		// Add the form to the grid pane
		grid.addRow(0, amount, amtText);
		

		Platform.runLater(() -> amtText.requestFocus());
		dialog.getDialogPane().setContent(grid);
		Optional<ButtonType> result = dialog.showAndWait();
		try {
			
			if (result.get() == withdrawBtn) {
				withdraw(e, stage, user, amtText.getText());
			}
			else {
				e.consume();
			}
		}
		catch(Exception Ex) {
			e.consume();
		}
	}
	
}
