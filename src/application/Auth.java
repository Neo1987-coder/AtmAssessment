package application;

import java.util.Optional;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Auth extends Application {
	
	private Alert alert;
	
	@Override
	public void start(Stage stage) {
        
		
		VBox wrapperBox = new VBox ();
		// Set a padding for the VBox (top, left, bottom, right)
		wrapperBox.setPadding(new Insets(80,60,10,60));
		wrapperBox.setSpacing(50);
		
		
		Label greeting = new Label("Hello !");
		greeting.setStyle("-fx-font-family:monospace;"
					        	+"-fx-font-size:25px;");
		
		Label welcomeMssg = new Label("Please click on the 'Start session' button below,");
		welcomeMssg.setStyle("-fx-font-family:monospace;"
	        	+"-fx-font-size:25px;");
		
		Label advice = new Label("to login and start making transactions");
		advice.setStyle("-fx-font-family:monospace;"
	        	+"-fx-font-size:25px;");
		
		Button sessionBtn = new Button("Start session");
		sessionBtn.setPrefWidth(280);
		sessionBtn.setPrefHeight(60);
		sessionBtn.setOnAction(e -> loginAuth(stage));
		
		
		BorderPane borderPane = new BorderPane();
		

		
		borderPane.setCenter(sessionBtn);
		
		
		wrapperBox.setAlignment(Pos.TOP_CENTER);
		wrapperBox.getChildren().addAll(greeting, welcomeMssg, advice, borderPane);
		
		// Scene to display on stage
		Scene scene = new Scene(wrapperBox);
		// Gives the stage/window a name
		stage.setTitle("My ATMInterface Project");
        stage.setScene(scene);
        stage.show();
        stage.setMaximized(true);
    }

	
	
	protected void loginAuth(Stage stage) {
		// New dialog with two button types [OK btn and CANCEL btn]
		Dialog<ButtonType> dialog = new Dialog<>();
		// Set dialog title and header text.....To be displayed at the top of the dialog
		dialog.setTitle("Customer Login");
		dialog.setHeaderText("Please enter your account number and pin in the fields below and click on proceed");
		// Set the button types.
		ButtonType proceedBtn = new ButtonType("Proceed", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(proceedBtn, ButtonType.CANCEL);

		
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 20, 15, 15));

		Label label1 = new Label("Account Number: ");
		TextField accNumber = new TextField();
		accNumber.setPromptText("Enter your account number here");
		accNumber.setPrefWidth(250);
				
		Label label2 = new Label("Pin: ");
		PasswordField pin = new PasswordField();
		pin.setPromptText("Enter your pin here");
		pin.setPrefWidth(250);
				
				

		// Set the login form in the grid////////////////////////////////
		grid.addRow(0, label1, accNumber);
		grid.addRow(2, label2, pin);
		////////////////////////////////////////////////////////////////////
				
		// Show the grid
		Platform.runLater(() -> accNumber.requestFocus());
		dialog.initOwner(stage);
		dialog.getDialogPane().setContent(grid);
		Optional<ButtonType> result = dialog.showAndWait();

		// If the user clicks on the proceed button
		if (result.get() == proceedBtn) {
			// Validate that compulsory fields are not empty
			if(accNumber.getText().isBlank() || pin.getText().isBlank() ) {
				Message.warning("Invalid Fields", null, "Please fill all provided fields with valid values.", stage, alert);
				return;
			}
			
			
			User newUser = new User();
			newUser = new UsersDAO().getUser(accNumber.getText(), Integer.parseInt(pin.getText()));
			
			if(newUser != null) {
				// If the data has a record in DB
				Stage newStage = new Stage();
				MainWindow newWindow = new MainWindow(newUser);
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
				// If no record in the database
				Message.error("User Not Found !", null, "Incorrect account number or pin.", stage, alert);
			}
			
		}
	}
	
}
