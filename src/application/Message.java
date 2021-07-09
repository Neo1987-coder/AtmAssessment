package application;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class Message {

	public static Alert error(String x, String y, String z, Stage stage, Alert alert) {
		alert = new Alert(AlertType.ERROR);
		alert.setTitle(x);
		alert.setHeaderText(y);
		alert.setContentText(z);
		alert.initOwner(stage);
		alert.showAndWait();

		return alert;
	}

	public static Alert info(String x, String y, String z, Stage stage, Alert alert) {
		alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(x);
		alert.setHeaderText(y);
		alert.setContentText(z);
		alert.initOwner(stage);
		alert.showAndWait();

		return alert;
	}

	public static int confirm(String x, String y, String z, String button1, String button2, Stage stage, Alert alert) {
		int $x = 0;

		alert = new Alert(AlertType.CONFIRMATION);
		alert.initOwner(stage);
		alert.setTitle(x);
		alert.setHeaderText(y);
		alert.setContentText(z);

		ButtonType btn1 = new ButtonType(button1);
		ButtonType btn2 = new ButtonType(button2);
		ButtonType btn3 = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

		alert.getButtonTypes().setAll(btn1, btn2, btn3);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == btn1) {
			$x = 1;
		} else if (result.get() == btn2) {
			$x = 2;
		} else {
			$x = 3;
		}

		return $x;
	}

	public static Alert warning(String x, String y, String z, Stage stage, Alert alert) {
		alert = new Alert(AlertType.WARNING);
		alert.setTitle(x);
		alert.setHeaderText(y);
		alert.setContentText(z);
		alert.initOwner(stage);
		alert.showAndWait();

		return alert;
	}

	public static Alert exception(String x, String y, String z, String button1, Stage stage, Alert alert) {

		alert = new Alert(AlertType.ERROR);
		alert.setTitle(x);
		alert.setHeaderText(y);
		alert.setContentText(z);

		Exception ex = new Exception(z);

		// Create expandable Exception.
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		ex.printStackTrace(pw);
		String exceptionText = sw.toString();

		Label label = new Label("Exception stacktrace:");

		TextArea textArea = new TextArea(exceptionText);
		textArea.setEditable(false);
		textArea.setWrapText(true);

		textArea.setMaxWidth(Double.MAX_VALUE);
		textArea.setMaxHeight(Double.MAX_VALUE);
		GridPane.setVgrow(textArea, Priority.ALWAYS);
		GridPane.setHgrow(textArea, Priority.ALWAYS);

		GridPane expContent = new GridPane();
		expContent.setMaxWidth(Double.MAX_VALUE);
		expContent.add(label, 0, 0);
		expContent.add(textArea, 0, 1);

		// Set expandable Exception into the dialog pane.
		alert.getDialogPane().setExpandableContent(expContent);

		alert.showAndWait();

		return alert;
	}

	public static int warningProceed(String x, String y, String z, String button1, Stage stage, Alert alert) {
		int $x = 0;

		alert = new Alert(AlertType.CONFIRMATION);
		alert.initOwner(stage);
		alert.setTitle(x);
		alert.setHeaderText(y);
		alert.setContentText(z);

		ButtonType btn1 = new ButtonType(button1);
		ButtonType btn2 = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

		alert.getButtonTypes().setAll(btn1, btn2);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == btn1) {
			$x = 1;
		} else if (result.get() == btn2) {
			$x = 2;
		} else {
			$x = 3;
		}

		return $x;
	}

}
