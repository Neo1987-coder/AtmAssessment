package application;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BalAndStmtWindow extends Application {
	// TABLE VIEW AND DATA
	private ObservableList<ObservableList> data;
	private TableView<Transaction> tableview;
	
	private User user;
	//private static Alert alert;
	
	public BalAndStmtWindow(User user) {
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
		
		
		Label welcomeMssg = new Label("CURRENT BALANCE: ZAR "+this.user.getBalance()+"");
		welcomeMssg.setStyle("-fx-font-family:monospace;"
					        	+"-fx-font-size:29px;");
		
		
		tableview = new TableView<Transaction>();
		buildData();
		
		// My wrapper box should stack contents at the top center 
		// Note: it contains the welcome message at top and the border pane which contains the left and right buttons
		wrapperBox.setAlignment(Pos.TOP_CENTER);
		wrapperBox.getChildren().addAll(welcomeMssg, tableview);
		
		// Scene to display on stage
		Scene scene = new Scene(wrapperBox);
		// Gives the stage/window a name
		stage.setTitle("My ATMInterface Project - Balance And Account Statement");
        stage.setScene(scene);
        stage.show();
        stage.setMaximized(true);
    }
	
	
	
	
	
	public void buildData() {
		
		/**
		 * ******************************** TABLE COLUMN ADDED DYNAMICALLY *
		 *********************************
		 */
		/*
		 * for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) { // We are using
		 * non property style for making dynamic table final int j = i;
		 * 
		 * }
		 */
		//TableColumn<Transaction, String> col1 = new TableColumn<Transaction, String>("ID");
		//col1.setCellValueFactory(new PropertyValueFactory<>("sn"));
		
		TableColumn<Transaction, String> col2 = new TableColumn<Transaction, String>("DATE");
		col2.setCellValueFactory(new PropertyValueFactory<>("date"));
		
		TableColumn<Transaction, String> col3 = new TableColumn<Transaction, String>("TRANS. TYPE");
		col3.setCellValueFactory(new PropertyValueFactory<>("type"));
		
		TableColumn<Transaction, String> col4 = new TableColumn<Transaction, String>("AMOUNT");
		col4.setCellValueFactory(new PropertyValueFactory<>("amount"));
		
		TableColumn<Transaction, String> col5 = new TableColumn<Transaction, String>("TO");
		col5.setCellValueFactory(new PropertyValueFactory<>("receiversName"));
		
		TableColumn<Transaction, String> col6 = new TableColumn<Transaction, String>("BALANCE");
		col6.setCellValueFactory(new PropertyValueFactory<>("balance"));

		tableview.getColumns().addAll(col2, col3, col4, col5, col6);

		/**
		 * ****************************** Data added to ObservableList *
		 *******************************
		 */
		tableview.setItems(new TransactionDAO().getTransaction(this.user.getId()));
		tableview.autosize();
}
	
}
