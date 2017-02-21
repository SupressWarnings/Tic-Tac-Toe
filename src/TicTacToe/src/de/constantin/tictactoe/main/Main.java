package de.constantin.tictactoe.main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application{

	private Stage primaryStage;
	private Button[][] field;
	private Button[] buttons;
	private String player1 = "X";
	private String player2 = "O";
	private GridPane fieldPane, window;
	private Label won;
	private boolean player1turn = true;
	
	public static void main(String[] args){
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		field = new Button[3][3];
		buttons = new Button[2];
		fieldPane = new GridPane();
		window = new GridPane();
		won = new Label("");
		
		initField();
	}
	
	private void initField(){
		primaryStage.setTitle("Tic Tac Toe");
		primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("../res/tic_tac_toe_logo.png")));		
		fieldPane = new GridPane();
		fieldPane.setHgap(5);
		fieldPane.setVgap(5);
		fieldPane.setId("field");
		for(int x = 0; x < 3; ++x){
			for(int y = 0; y < 3; ++y){
				field[x][y] = new Button();
				final Button buttonHere = field[x][y];
				fieldPane.add(field[x][y], x, y);
				field[x][y].setPrefSize(64, 64);
				field[x][y].setId("button");
				field[x][y].setOnAction(new EventHandler<ActionEvent>(){

					@Override
					public void handle(ActionEvent arg0) {
						if(player1turn){
							buttonHere.setText(player1);
							player1turn = false;
						}else{
							buttonHere.setText(player2);
							player1turn = true;
						}
						checkWin();
						buttonHere.setOnAction(new EventHandler<ActionEvent>(){
							@Override
							public void handle(ActionEvent arg0) {}
							
						});
					}
					
				});
			}
		}
		
		buttons[0] = new Button("Restart");

		buttons[0].setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				for(int x = 0; x < 3; ++x){
					for(int y = 0; y < 3; ++y){
						field[x][y].setText("");
					}
				}
				won.setText("");
			}
			
		});
		
		won.setId("won");
		
		window.setPadding(new Insets(64));
		window.setHgap(16);
		window.setVgap(16);
		window.add(fieldPane, 0, 0);
		window.add(buttons[0], 0, 2);
		window.add(won, 0, 1);
		
		Scene playingScene = new Scene(window, 350, 500);
		playingScene.getStylesheets().add(Main.class.getResource("main.css").toExternalForm());
		
		primaryStage.setScene(playingScene);
		primaryStage.show();
	}

	private void checkWin(){
		for(Button[] row : field){
			if(row[0].getText().equals(row[1].getText()) 
					&& row[1].getText().equals(row[2].getText())
					&& !row[0].getText().equals("")){
				win(row[0].getText());
			}
		}
		for(int row = 0; row < 3; ++row){
			if(field[0][row].getText().equals(field[1][row].getText())
					&& field[0][row].getText().equals(field[2][row].getText())
					&& !field[0][row].getText().equals("")){
				win(field[0][row].getText());
			}
		}
		if(field[0][0].getText().equals(field[1][1].getText()) 
				&& field[0][0].getText().equals(field[2][2].getText())
				&& !field[0][0].getText().equals("")){
			win(field[0][0].getText());
		}
		if(field[2][0].getText().equals(field[1][1].getText()) 
				&& field[2][0].getText().equals(field[0][2].getText())
				&& !field[0][2].getText().equals("")){
			win(field[2][0].getText());
		}
	}
	
	private void win(String player){
		for(Button[] row : field){
			for(Button button : row){
				button.setOnAction(new EventHandler<ActionEvent>(){
					@Override
					public void handle(ActionEvent arg0) {}});;
			}
		}
		won.setText("Winner: " + player);
	}
}
