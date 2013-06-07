import exceptions.LexException;
import exceptions.ParseException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import statements.Statement;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Bertie
 * Date: 07/06/13
 * Time: 02:00
 * To change this template use File | Settings | File Templates.
 */
public class Editor extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		stage.setTitle("Daejo");
		StackPane root = new StackPane();
		VBox rootVBox = new VBox(10);
		final TextArea daejoText = new TextArea();
		final TextArea outputText = new TextArea();
		HBox hbox = new HBox(10);
		VBox daejoVBox = new VBox();
		VBox outputVBox = new VBox();
		final Button button = new Button("Parse");
		String style = "-fx-text-fill: white;" +
			"-fx-background-color: black;" +
			"-fx-font-size: 12;";
		daejoText.setStyle(style);
		daejoText.setPromptText("Write Daejo code here...");
		outputText.setStyle(style);
		outputText.setEditable(false);
		outputText.setPromptText("Output will display here");
		hbox.getChildren().addAll(daejoVBox, outputVBox);
		hbox.setAlignment(Pos.CENTER);
		VBox.setVgrow(daejoText, Priority.ALWAYS);
		VBox.setVgrow(outputText, Priority.ALWAYS);
		VBox.setVgrow(hbox, Priority.ALWAYS);
		daejoVBox.getChildren().add(daejoText);
		daejoVBox.setAlignment(Pos.CENTER);
		outputVBox.getChildren().add(outputText);
		outputVBox.setAlignment(Pos.CENTER);
		button.setOnAction(new EventHandler<ActionEvent>() {
			private boolean ready = true;
			@Override
			public void handle(ActionEvent actionEvent) {
				if(!ready) return;
				ready = false;
				button.setText("Parsing...");
				String toParse = daejoText.getText();
				Transformer transformer = new Transformer(toParse);
				try {
					List<Statement> statements = transformer.transform();
					StringBuilder sb = new StringBuilder();
					for(Statement statement : statements) {
						sb.append(statement);
						sb.append("\n");
					}
					outputText.setText(sb.toString());
				} catch(LexException e) {
					outputText.setText(String.format(
						"Invalid token at line %d, column %d\n",
						e.getLine(), e.getColumn()));
				} catch(ParseException e) {
					outputText.setText("Couldn't parse input. Message:\n" +
						e.getMessage());
				}
				outputText.setText(outputText.getText() + "\nInput: \"" + toParse + "\"");
				button.setText("Parse");
				ready = true;
			}
		});
		rootVBox.setAlignment(Pos.CENTER);
		rootVBox.setPadding(new Insets(25, 25, 25, 25));
		rootVBox.getChildren().addAll(hbox, button);
		root.getChildren().add(rootVBox);

		Scene scene = new Scene(root);
		scene.setFill(Color.FORESTGREEN);
		stage.setScene(scene);
		//stage.setFullScreen(true);
		stage.show();
	}
}