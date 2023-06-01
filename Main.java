/**
 * Harry Potter Triwizard maze game.
 * CNSCC110 Software Development Coursework Lancaster University College at BJTU.
 * 
 * This program implements a Java GUI game based on the concept of the Triwizard maze from the Harry Potter series.
 * Players can roll dice, move their chess pieces, and draw cards to navigate through the maze and reach the end goal.
 * The game supports 2 or 4 players and provides various game mechanics and interactions.
 * The GUI elements are designed using JavaFX.
 * 
 * The Main class serves as the entry point for the game. It initializes the game components, manages the game state,
 * and handles user interactions. The game logic is implemented in this class.
 * 
 * The Player class represents a player in the game. Each player has a name, a chess piece, and a current position on the game board.
 * Players can roll dice, move their chess pieces, and interact with other game elements. The Player class provides methods
 * for rolling the dice, moving the chess piece, and updating the player's state.
 * 
 * The Chess class represents a chess piece on the game board. Each chess piece has a unique identifier and a current position
 * on the game board. The Chess class provides methods for moving the chess piece and updating its position.
 * 
 * The ChessCell class represents a cell on the game board. Each cell can contain various elements such as walls, pathways,
 * and special locations. The ChessCell class provides methods for initializing the cell, checking its contents, and updating
 * the cell's state.
 * 
 * This program is a coursework project for the CNSCC110 Software Development course at Lancaster University College
 * at BJTU. It is not an official Harry Potter game and is intended for educational purposes only.
 * 
 * References:
 * - https://github.com/bayooow/Triwizard-MazeGame
 * - https://github.com/Eluny/FlyingChess
 * - https://github.com/hugeBlack/flyingChess
 * - https://github.com/apriljdai/Chinese-Flying-Chess
 * - https://github.com/lizi003/planegame
 * - https://github.com/WendellGul/FlyingChess
 * 
 * @author Qiu Teng (Project Leader,BJTU ID 21726062)
 * 
 *         (Design and implement code)
 *         BJTU ID 21726062
 *         
 *         
 *         Li Shaoxu
 *         Develop more function and version control
 *         BJTU ID 21722009
 *         
 *         Li Yuge
 *         Version control and ensure that testing activities align with project goals
 *         BJTU ID 21722012
 *         
 *         Kuang Xun
 *         Test software, write report and comment
 *         BJTU ID 21722008
 *         
 * @version 2.0
 */




// Import JavaFX and Java classes required for the GUI and game logic.
import javafx.animation.AnimationTimer;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.util.*;

/**
 * The Main class represents the entry point of the chess-like game program. It contains the main method
 * and implements the game logic and user interface. The game board and player actions are managed within
 * this class.
 *
 * <p>
 * Attributes:
 * - players: The list of Player instances representing the players of the game.
 * - currentPlayId: The index of the current player in the players list.
 * - oper: The current operation type. 1 for rolling the dice, 2 for moving the chess piece, 3 for using a card.
 * - result: The result of the dice roll.
 * - skipPlayerList: The list of players who are restricted from playing their turn.
 * - circleChessList: The list of ChessCell instances representing the circular game board.
 * - cardPane: The pane used to display cards during the game.
 * - cardLabel: The label used to display card information.
 *
 * <p>
 * Methods:
 * - circleChess(): Creates the circular game board by positioning ChessCell instances.
 * - homeChess(double startX, double startY, String dir): Constructs the "HOME" area ChessCell instances.
 * - startChess(Image image, double startX, double startY, String dir): Creates Chess instances with images and starting positions.
 * - onSelectChess(MouseEvent event): Handles the mouse click event on a Chess instance.
 * - moveChess(Chess chess, Integer result): Moves the Chess piece based on the dice result.
 * - moveTransition(Chess chess, ChessCell chessCell): Performs the transition animation to move the Chess.
 * - moveToStart(Chess oldChess): Moves an existing Chess piece to the starting area.
 * - chessCard(Chess chess): Handles the chess card event.
 * - getPlayer(): Retrieves the player who is rolling the dice.
 * - nextPlayer(): Moves the game to the next player and performs necessary actions.
 * - startChessCell(double startX, double startY, String dir): Constructs ChessCell instances with starting coordinates.
 * - buildMenu(String text, EventHandler<ActionEvent> e): Builds a menu button with the specified text and event handler.
 * - setAnchor(Node node, Double top, Double left, Double right, Double bottom): Sets the anchor properties of a Node.
 * - main(String[] args): The main method of the Java program.
 * References:
 * - https://github.com/bayooow/Triwizard-MazeGame
 * - https://github.com/Eluny/FlyingChess
 * - https://github.com/hugeBlack/flyingChess
 * - https://github.com/apriljdai/Chinese-Flying-Chess
 * - https://github.com/lizi003/planegame
 * - https://github.com/WendellGul/FlyingChess
 * 
 * @author Qiu Teng (Project Leader,BJTU ID 21726062)
 * 
 *         (Design and implement code)
 *         BJTU ID 21726062
 *         
 *         
 *         Li Shaoxu
 *         Develop more function and version control
 *         BJTU ID 21722009
 *         
 *         Li Yuge
 *         Version control and ensure that testing activities align with project goals
 *         BJTU ID 21722012
 *         
 *         Kuang Xun
 *         Test software, write report and comment
 *         BJTU ID 21722008
 *         
 * @version 2.0     
 */
public class Main extends Application {

// GUI elements and data structures for game elements.
private VBox menu;
private AnchorPane root;

private final List<Image> diceList = Arrays.asList(
    new Image("resources/images/1.png"),// Dice face with value 1
    new Image("resources/images/2.png"),// Dice face with value 2
    new Image("resources/images/3.png"),// Dice face with value 3
    new Image("resources/images/4.png"),// Dice face with value 4
    new Image("resources/images/5.png"),// Dice face with value 5
    new Image("resources/images/6.png") // Dice face with value 6
);
 // Additional images for various game components.
private final Image chess1 = new Image("resources/images/chess1.png");// Image of mover 1
private final Image chess2 = new Image("resources/images/chess2.png");// Image of mover 2
private final Image chess3 = new Image("resources/images/chess3.png");// Image of mover 3
private final Image chess4 = new Image("resources/images/chess4.png");// Image of mover 4
private final Image cardImage = new Image("resources/images/card.png");
private final Image backgroundImage1 = new Image("resources/images/1-player.png");// Background image
private final Image backgroundImage2 = new Image("resources/images/2-player.png");// Background image
private final Image backgroundImage3 = new Image("resources/images/3-player.png");// Background image
private final Image backgroundImage4 = new Image("resources/images/4-player.png");// Background image


private Button sieve;

// List of players
private List<Player> players;

private int currentPlayId;
  // Game operation: 1 roll the dice, 2 select chess piece, 3 draw a card
private int oper = 1;
  // Dice value
private int result = 0;
private TextArea infoLabel;

private List<ChessCell> circleChessList;
    // Skip
private final List<Player> skipPlayerList = new ArrayList<>();
private AnchorPane cardPane;
private Label cardLabel;


private MediaPlayer backgroundMediaPlay;

/**
 * Entry point of the JavaFX application.
 * Sets up the main menu and initializes the game when a player chooses the number of players.
 * 
 * @param primaryStage The primary stage of the JavaFX application.
 * @throws Exception if an error occurs during the application's start.
 * @author Qiu Teng 21726062
 * @version 2.0
 */
@Override
public void start(Stage primaryStage) throws Exception {
        // Initialize the root node and the menu, and set the menu's position and style.
        root = new AnchorPane();
        menu = new VBox();
        menu.setAlignment(Pos.CENTER);
        menu.setStyle("-fx-background-color: rgba(255,255,255,0.20)");
        setAnchor(menu, 100.0, 250.0, 250.0, 1.0);
        // Add menu items to start the game or exit the application.
        menu.getChildren().add(buildMenu("Group1-Subgroup7\n\n  Qiu Teng (Leader)\n     Li Shaoxu\n     Li Yuge\n     Kuang Xun", e -> this.startGame(4)));
        menu.getChildren().add(buildMenu(" Two Players", e -> this.startGame(2)));
        menu.getChildren().add(buildMenu("Four Players", e -> this.startGame(4)));
        menu.getChildren().add(buildMenu("Exit", e -> Platform.exit()));
       
        menu.setSpacing(40);
        root.getChildren().add(menu);
//    menu.setVisible(false);
//    startGame(2);
//    root.setStyle("-fx-background-color: #000000");
        
         // Set the application scene and show the application window.
        BackgroundSize backgroundSize = new BackgroundSize(800, 800, false, false, false, false);
        BackgroundImage backgroundImage = new BackgroundImage(new Image("resources/images/start_background.jpg"), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, backgroundSize);
        root.setBackground(new Background(backgroundImage));
        primaryStage.setScene(new Scene(root, 800, 800));
        primaryStage.setResizable(false);
        primaryStage.setTitle("Harry Potter Triwizard Maze Game");
        primaryStage.show();
}

    /**
     * Begins the game.
     * Initializes the game board and player pieces.
     * @param player the number of players.
     */
public void startGame(int player) {

    menu.setVisible(false);
    BackgroundSize backgroundSize = new BackgroundSize(800, 800, false, false, false, false);
    BackgroundImage backgroundImage = new BackgroundImage(new Image("resources/images/main.png"), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, backgroundSize);
    root.setBackground(new Background(backgroundImage));
    Media media = new Media(new File("resources/audio/background-music.mp3").toURI().toString());
    //USE MediaPlayer  to play music
    backgroundMediaPlay = new MediaPlayer(media);
    backgroundMediaPlay.setAutoPlay(true);
    backgroundMediaPlay.setVolume(0.5);
    backgroundMediaPlay.play();
    backgroundMediaPlay.setCycleCount(MediaPlayer.INDEFINITE);
    Pane pane = new Pane();
    pane.setPrefWidth(150);
    pane.setPrefHeight(150);
    setAnchor(pane, null, null, 0.0, 0.0);
    infoLabel = new TextArea("Player" + currentPlayId + "：Please roll the dice!\n");
    infoLabel.setWrapText(true);
    infoLabel.setEditable(false);
    infoLabel.setPrefHeight(140);
    infoLabel.setPrefWidth(140);
    infoLabel.setLayoutX(0);
    infoLabel.setLayoutY(0);
    pane.getChildren().add(infoLabel);
    cardPane = new AnchorPane();
    cardPane.setPrefWidth(150);
    cardPane.setPrefHeight(150);
    cardLabel = new Label();
    cardLabel.setPrefWidth(150);
    cardLabel.setWrapText(true);
    cardLabel.setLayoutX(10);
    cardLabel.setLayoutY(10);
    cardPane.getChildren().add(cardLabel);
    setAnchor(cardPane, null, 5.0, null, 5.0);
    backgroundSize = new BackgroundSize(150, 150, false, false, false, false);
    backgroundImage = new BackgroundImage(cardImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, backgroundSize);
    cardPane.setBackground(new Background(backgroundImage));
    root.getChildren().addAll(pane);
    Rectangle rectangle = new Rectangle();
    rectangle.setX(75);
    rectangle.setY(75);
    rectangle.setHeight(650);
    rectangle.setWidth(650);
    rectangle.setArcHeight(400);
    rectangle.setArcWidth(400);
    rectangle.setFill(Color.web("#fffdd0"));
    Rotate rotate = new Rotate();
    rotate.setAngle(-45);
    rotate.setPivotX(400);
    rotate.setPivotY(400);
    rectangle.getTransforms().add(rotate);
    Rectangle rectangle1 = new Rectangle();
    rectangle1.setHeight(70);
    rectangle1.setWidth(70);
    rectangle1.setFill(Color.web("#006400"));
    rectangle1.setArcHeight(5);
    rectangle1.setArcWidth(5);
    rectangle1.setX(365);
    rectangle1.setY(365);

    Button sieve = new Button();
    sieve.setPrefWidth(50);
    sieve.setPrefHeight(50);
    ImageView imageView = new ImageView(diceList.get(0));
    imageView.setFitWidth(50);
    imageView.setFitHeight(50);
    sieve.setGraphic(imageView);

    sieve.setOnAction(event -> {
        if (oper != 1) {
            infoLabel.appendText("Player" + currentPlayId + "：" + "Please select the dice you want to act on\n");
            return;
        }
        // roll the dice 
        randomSieve();
        if (isRun()) {
            infoLabel.appendText("Player" + currentPlayId + "：Current dice result:" + result + "，Please select the dice you want to act on\n");
            oper = 2;
        } else {
            infoLabel.appendText("Player" + currentPlayId + "：Current dice result:" + result + "，No dice could be moved\n");
            nextPlayer();
        }
    });
    this.sieve = sieve;
    setAnchor(sieve, 375.0, 375.0, 375.0, 375.0);
    root.getChildren().addAll(rectangle, rectangle1, sieve);
    players = this.initPlayer(player);
    ImageView imageView1 = new ImageView(backgroundImage1);
    imageView1.setFitHeight(600);
    imageView1.setFitWidth(600);
    setAnchor(imageView1, null, 100.0, null, 100.0);
    ImageView imageView2 = new ImageView(backgroundImage2);
    imageView2.setFitHeight(600);
    imageView2.setFitWidth(600);
    setAnchor(imageView2, null, null, 0.0, 100.0);

    ImageView imageView3 = new ImageView(backgroundImage3);
    imageView3.setFitHeight(600);
    imageView3.setFitWidth(600);
    setAnchor(imageView3, 0.0, null, 0.0, null);


    ImageView imageView4 = new ImageView(backgroundImage4);
    imageView4.setFitHeight(600);
    imageView4.setFitWidth(600);
    setAnchor(imageView4, 30.0, 100.0, null, null);
    root.getChildren().addAll(imageView1, imageView2, imageView3, imageView4);
    this.currentPlayId = 0;
    root.getChildren().add(cardPane);
}

    /**
     * Randomly rolls a die.
     * Sets the 'result' variable to the outcome of the roll.
     */
private void randomSieve() {
    Random random = new Random();
    int index = random.nextInt(6);
    result = index + 1;
    AnimationTimer animationTimer = new AnimationTimer() {
        int i = 0;
        int j = 0;

        @Override
        public void handle(long now) {
            i++;
            j++;
            if (i % 10 == 0) {
                sieve.setGraphic(new ImageView(diceList.get(j % 6)));
            }
        }
    };

    PauseTransition transition = new PauseTransition();
    transition.setDelay(Duration.seconds(1));
    transition.setOnFinished(e -> {
        animationTimer.stop();
        // set the outcome
        ImageView iv = new ImageView(diceList.get(index));
        iv.setFitWidth(50);
        iv.setFitHeight(50);
        sieve.setGraphic(iv);
    });
    transition.play();
    animationTimer.start();
}

    /**
     * Checks if a player's piece can move.
     * @return true if a player's piece can move; false otherwise.
     */
private boolean isRun() {
    for (Chess chess : players.get(this.currentPlayId).getChessList()) {
        if (chess.isSuccess()) {
            continue;
        }
        if (chess.isStart()) {
            return true;
        }
        if (!chess.isStart() && result == 6) {
            return true;
        }
    }
    return false;
}


    /**
     * Appends a message to the information label in the GUI.
     * @param text the text to append to the information label.
     */
private void playerInfo(String text) {
    infoLabel.appendText("Player" + currentPlayId + "：" + text + "\n");
}

    /**
     * Initializes player pieces on the game board.
     * @param num the number of players.
     * @return a list of Player objects.
     */
public List<Player> initPlayer(int num) {
    List<ChessCell> chessCellList = new ArrayList<>();
    List<Chess> chessList = new ArrayList<>();
    List<ChessCell> startChessCell1 = startChessCell(290, 50, "X");
    chessCellList.addAll(startChessCell1);
    List<ChessCell> startChessCell2 = startChessCell(290, 700, "X");
    chessCellList.addAll(startChessCell2);
    List<ChessCell> startChessCell3 = startChessCell(50, 290, "Y");
    chessCellList.addAll(startChessCell3);
    List<ChessCell> startChessCell4 = startChessCell(700, 290, "Y");
    chessCellList.addAll(startChessCell4);

    List<ChessCell> homeChessCell3 = homeChess(125, 375, "X");
    chessCellList.addAll(homeChessCell3);
    List<ChessCell> homeChessCell4 = homeChess(460, 375, "X");
    chessCellList.addAll(homeChessCell4);
    List<ChessCell> homeChessCell1 = homeChess(375, 125, "Y");
    chessCellList.addAll(homeChessCell1);
    List<ChessCell> homeChessCell2 = homeChess(375, 460, "Y");
    chessCellList.addAll(homeChessCell2);

    circleChessList = circleChess();
    chessCellList.addAll(circleChessList);

    ArrayList<Player> players = new ArrayList<>();
    if (num == 2) {
        Label player0 = new Label("Player 0");
        setAnchor(player0, 25.0, 400.0, null, null);
        root.getChildren().add(player0);
        Label player1 = new Label("Player 1");
        setAnchor(player1, null, 400.0, null, 25.0);
        root.getChildren().add(player1);
        List<Chess> chessList1 = startChess(chess1, 290, 50, "X");
        List<Chess> chessList2 = startChess(chess2, 290, 700, "X");
        chessList.addAll(chessList1);
        chessList.addAll(chessList2);

        players.add(buildPlayer(chess1, chessList1, homeChessCell1, startChessCell1, 3));
        players.add(buildPlayer(chess2, chessList2, homeChessCell2, startChessCell2, 1));
    }

    if (num == 4) {
        Label player0 = new Label("Player 0");
        setAnchor(player0, 25.0, 390.0, null, null);
        root.getChildren().add(player0);
        Label player1 = new Label("Player 2");
        setAnchor(player1, null, 390.0, null, 25.0);
        root.getChildren().add(player1);
        Label player2 = new Label("Player 3");
        setAnchor(player2, 270.0, 60.0, null, null);
        root.getChildren().add(player2);
        Label player3 = new Label("Player 1");
        setAnchor(player3, 270.0, null, 60.0, null);
        root.getChildren().add(player3);
        List<Chess> chessList1 = startChess(chess1, 290, 50, "X");
        List<Chess> chessList2 = startChess(chess2, 700, 290, "Y");
        List<Chess> chessList3 = startChess(chess3, 290, 700, "X");
        List<Chess> chessList4 = startChess(chess4, 50, 290, "Y");
        chessList.addAll(chessList1);
        chessList.addAll(chessList2);
        chessList.addAll(chessList3);
        chessList.addAll(chessList4);

        players.add(buildPlayer(chess1, chessList1, homeChessCell1, startChessCell1, 3));
        players.add(buildPlayer(chess2, chessList2, homeChessCell4, startChessCell4, 0));
        players.add(buildPlayer(chess3, chessList3, homeChessCell2, startChessCell2, 1));
        players.add(buildPlayer(chess4, chessList4, homeChessCell3, startChessCell3, 2));
    }
    root.getChildren().addAll(chessCellList);
    root.getChildren().addAll(chessList);
    return players;
}

    /**
     * This method is used to build a Player object by setting its image, chess list, home list, 
     * start list, start index and end index. The function also connects all the chess pieces to the player.
     *
     * @param image - Image object to be set as the player's image
     * @param chess - List of Chess objects which belong to the player
     * @param homeChessCell - List of ChessCell objects which are the home cells of the player
     * @param startChessCell - List of ChessCell objects which are the start cells of the player
     * @param index - Index of the player which will be used to calculate the start index and end index
     * @return Player object after it has been set up
     * Qiu Teng 21726062
     */
private Player buildPlayer(Image image, List<Chess> chess, List<ChessCell> homeChessCell, List<ChessCell> startChessCell, int index) {
 // Creating a new Player object
Player player = new Player();
   // Setting player's image, chess list, home list, start list, start index, and end index
player.setImage(image);
player.setChessList(chess);
// Setting the home cells for the player's pieces
player.setHomeList(homeChessCell);
  // Setting the start cells for the player's pieces.
player.setStartList(startChessCell);
player.setStartIndex(index * 7);
    // Setting the end index for the player's pieces.
player.setEndIndex((27 + index * 7) % 28);

  // Setting the player for each Chess object in the chess list

for (Chess item : chess) {
item.setPlayer(player);
}
 // Setting the chess for each ChessCell object in the startChessCell list

for (int i = 0; i < startChessCell.size(); i++) {
startChessCell.get(i).setChess(chess.get(i));

}
System.out.println("playerId: " + index + ",startIndex:" + player.getStartIndex() + ", endIndex：" + player.getEndIndex());
  // Returns the newly created player.
return player;
}

    /**
     * This method is used to create the game board. The board is circular and has 28 cells.
     * The cells are positioned by rotating the initial cell around a pivot point. Every 7 cells, 
     * the rotation angle is increased by 90 degrees. Certain cells are designated as card cells.
     *
     * @return List of ChessCell objects that form the game board
     */
private List<ChessCell> circleChess() {
    // Initialize an empty list to hold the cells
    ArrayList<ChessCell> chessList = new ArrayList<>();
      // Initialize the starting rotation angle and the x offset
    double angle = 10.25;
    int x = 0;
      // Create a set of indices at which a card cell should be created
    
    Set<Integer> cards = new HashSet<>(Arrays.asList(22, 24, 26, 2, 4, 8, 10, 12, 16, 18));
    
      // Create 28 cells, adjusting the rotation angle and x offset as necessary
    for (int i = 0; i < 28; i++) {
        if (i % 7 == 0) {
            x = x + 90;
            angle = x + 10.25;
        }
        ChessCell chess = new ChessCell();
        if (cards.contains(i)) {
            chess.setCard(true);
            chess.setText("card");
        }
        chess.setLayoutX(375);
        chess.setLayoutY(95);
        Rotate rotate = new Rotate();
        rotate.setAngle(angle);
        rotate.setPivotX(0);
        rotate.setPivotY(280);
        chess.getTransforms().addAll(rotate, new Rotate(-angle, 0, 0));
        chessList.add(chess);
        angle += 11.5;
    }

    return chessList;
}


/**
 * Constructs a list of ChessCell instances representing the "HOME" area.
 * Each ChessCell instance is assigned a corresponding letter from the "HOME" string array.
 *
 * @param startX The x-coordinate of the starting position for the ChessCell instances.
 * @param startY The y-coordinate of the starting position for the ChessCell instances.
 * @param dir The direction in which the ChessCell instances are derived.
 * @return The constructed list of ChessCell instances representing the "HOME" area.
 */
private List<ChessCell> homeChess(double startX, double startY, String dir) {
    List<ChessCell> chessList = startChessCell(startX, startY, dir);
    
    String[] home = new String[]{"H", "O", "M", "E"};
     // Assign each ChessCell instance the corresponding letter from the "HOME" string array
    for (int i = 0; i < chessList.size(); i++) {
        chessList.get(i).setText(home[i]);
    }
    return chessList;
}

/**
 * This method constructs a list of Chess instances, each with a specified image and 
 * starting position. Each chess piece also has a mouse click event handler attached to it.
 *
 * @param image The Image instance to be associated with each Chess.
 * @param startX The x-coordinate of the starting position for the chess.
 * @param startY The y-coordinate of the starting position for the chess.
 * @param dir The direction in which the chess instances are derived.
 * @return The constructed list of Chess instances.
 */
private List<Chess> startChess(Image image, double startX, double startY, String dir) {
List<Chess> chessList = new ArrayList<>();
 // Create four Chess instances and add them to the list
chessList.add(new Chess(image));
chessList.add(new Chess(image));
chessList.add(new Chess(image));
chessList.add(new Chess(image));
// Set the layout coordinates and attach a mouse click event handler to each Chess instance

for (Chess chess : chessList) {
chess.setLayoutX(startX);
chess.setLayoutY(startY);
 // Mouse click event handler
chess.addEventHandler(MouseEvent.MOUSE_CLICKED, (event -> {
if (event.getClickCount() == 2 && event.getButton().name().equals(MouseButton.PRIMARY.name())) {
this.onSelectChess(event);
}
}));
  // Update the starting position coordinates based on the direction
if (Objects.equals(dir, "X")) {
startX += 55;
} else {
startY += 55;
}
}
return chessList;
}

/**
 * Handles the mouse click event on a Chess instance.
 * Performs various checks and actions based on the game rules.
 *
 * @param event The MouseEvent representing the mouse click event.
 */
private void onSelectChess(MouseEvent event) {
if (oper == 1) {
playerInfo("Please roll the dice first");
return;
}
Chess chess = (Chess) event.getSource();

  // Check if the selected Chess belongs to the current player
if (!players.get(currentPlayId).equals(chess.getPlayer())) {
playerInfo("Please roll the dice first");
return;
}

  // Check if the Chess is not at the starting position and the dice result is not 6
if (!chess.isStart() && result != 6) {
playerInfo("The dice cannot be moved");
return;
}
 // Check if the Chess has already reached the goal
if (chess.isSuccess()) {
playerInfo("The dice cannot be moved");
return;
}
boolean moved = moveChess(chess, result);
 // Check if the player has reached the goal
if (chess.getPlayer().isSuccess()) {
playerInfo("Win");
}
// Check the current operation type and perform corresponding actions
if (moved && oper == 2) {
nextPlayer();
}
if (moved && oper == 3) {
chessCard(chess);
}
}

/**
 * Moves the Chess piece based on the dice result.
 *
 * @param chess The Chess instance to be moved.
 * @param result The result of the dice roll.
 * @return true if the Chess was successfully moved, false otherwise.
 */
private boolean moveChess(Chess chess, Integer result) {
    Player player = chess.getPlayer();
    if (chess.isStart()) {
         // Clear the ChessCell at the current index
        circleChessList.get(chess.getCurrentIndex()).setChess(null);
         // Calculate the end index based on the current index and dice result
        int endResult = (chess.getCurrentIndex() + result) % circleChessList.size();
        // Check if the Chess will reach the HOME area
        if (chess.getSetup() + result > 28) {
            chess.setSetup(chess.getSetup() + result);
      
            
               // Move to the first available ChessCell in the HOME area
             for (ChessCell chessCell : player.getHomeList()) {
                if (chessCell.getChess() == null) {
                    boolean moved = moveTransition(chess, chessCell);
                    if (moved) {
                        chess.setSuccess(true);
                        chessCell.setChess(chess);
                        System.out.println("Go into HOME");
                    }
                    return moved;
                }
            }
        } else if (chess.getSetup() + result < 0) {
            // Move the Chess back to the starting area
            this.moveToStart(chess);
            return true;
        } else {
            // Move the Chess to the target ChessCell
            ChessCell chessCell = circleChessList.get(endResult);
            boolean moved = moveTransition(chess, chessCell);
            if (moved) {
                chess.setCurrentIndex(endResult);
                chess.setSetup(chess.getSetup() + result);
            }
            return moved;
        }
    } else {
        // Move the Chess from the starting area to the outer circle
        ChessCell chessCell = circleChessList.get(player.getStartIndex());
        boolean moved = moveTransition(chess, chessCell);
        if (moved) {
            // Update the Chess properties
            player.getStartList().get(player.getChessList().indexOf(chess)).setChess(null);
            chess.setCurrentIndex(player.getStartIndex());
            chess.setStart(true);
            chess.setSetup(1);
        }
        return moved;
    }
    return false;
}


/**
 * Performs the transition animation to move the Chess to a target ChessCell.
 *
 * @param chess The Chess instance to be moved.
 * @param chessCell The target ChessCell to move the Chess to.
 * @return true if the Chess was successfully moved, false otherwise.
 */
private boolean moveTransition(Chess chess, ChessCell chessCell) {
    // Check if the target ChessCell already contains a Chess belonging to the same player
    if (chessCell.getChess() != null && chessCell.getChess().getPlayer().equals(chess.getPlayer())) {
        playerInfo("The target position is our mover, please re-roll the dice");
        // Set the operation to rolling the dice
        this.oper = 1;
        return false;
    }
    // Calculate the translation values for the transition animation
    Bounds bounds = chessCell.localToParent(chessCell.getLayoutBounds());
    TranslateTransition transition = new TranslateTransition();
    transition.setToX(bounds.getMinX() - chess.getLayoutX());
    transition.setToY(bounds.getMinY() - chess.getLayoutY());
    transition.setDuration(Duration.seconds(1));
    Media media = new Media(new File("resources/audio/chess.mp3").toURI().toString());
    //MediaPlayer playmusic
    MediaPlayer mediaPlayer = new MediaPlayer(media);
    mediaPlayer.setVolume(0.5);
    mediaPlayer.play();
    // Set the Chess as the node for the transition animation and play it
    transition.setNode(chess);
    transition.play();

    Chess oldChess = chessCell.getChess();
    if (oldChess != null) {
        // Move the existing Chess piece to the starting area
        moveToStart(oldChess);
    }
     // Set the Chess as the new occupant of the target ChessCell
    chessCell.setChess(chess);
    if (chessCell.isCard()) {
        this.oper = 3;
    }
    return true;
}


/**
 * Moves the existing Chess piece to the starting area.
 *
 * @param oldChess The Chess instance to be moved.
 */
private void moveToStart(Chess oldChess) {
    // Check for collision and move the Chess to an available ChessCell in the starting area
    for (ChessCell cell : oldChess.getPlayer().getStartList()) {
        if (cell.getChess() == null) {
            oldChess.setSetup(0);
            oldChess.setStart(false);
            oldChess.setCurrentIndex(0);
            moveTransition(oldChess, cell);
            break;
        }
    }
}


/**
 * Handles the chess card event.
 * Displays a card with a random effect and performs corresponding actions.
 *
 * @param chess The Chess instance triggering the card event.
 */
private void chessCard(Chess chess) {
     // Randomly select a card
    int index = new Random().nextInt(5);
    this.playerInfo("Draw NO." + (index + 1) + "card");
    TranslateTransition transition = new TranslateTransition();
    transition.setDuration(Duration.seconds(2));
    transition.setToX(330);
    transition.setToY(-330);
    transition.setNode(cardPane);
    transition.setAutoReverse(true);
    transition.setCycleCount(2);
    transition.setToZ(99);
    
     // Perform actions based on the selected card index
    if (index == 0) {
        this.playerInfo("Bonus one extra roll of dice.");
        cardLabel.setText("Bonus one extra roll of dice.");
        transition.play();
        transition.setOnFinished(event -> {
            this.oper = 1;
            this.playerInfo("Please roll the dice");
        });
    }
    if (index == 1) {
        this.playerInfo("Reward the mover one block forward.");
        cardLabel.setText("Reward the mover one block forward.");
        transition.play();
        transition.setOnFinished(event -> {
            this.moveChess(chess, 1);
            this.nextPlayer();
        });

    }

    if (index == 2) {
        this.playerInfo("Specify the player to roll the dice, and if the result is 1 2 3, skip the next chance to roll the dice. If it is 4 5 6, you are one block back.");
        cardLabel.setText("Specify the player to roll the dice, and if the result is 1 2 3, skip the next chance to roll the dice. If it is 4 5 6, you are one block back.");
        transition.play();
        transition.setOnFinished(event -> {
            Platform.runLater(() -> {
                Player player = getPlayer();
                this.randomSieve();
                if (result > 3) {
                    this.playerInfo("Result is" + result + "Your own mover takes a step back.");
                    this.moveChess(chess, -1);
                    this.nextPlayer();
                } else {
                    this.playerInfo("Result is" + result + "Player" + players.indexOf(player) + "Skip the next dice roll");
                    this.skipPlayerList.add(player);
                    this.nextPlayer();
                }
            });
        });

    }

    if (index == 3) {
        this.playerInfo("The mover goes directly to the HOME Path.");
        cardLabel.setText("The mover goes directly to the HOME Path.");
        transition.play();
        transition.setOnFinished(event -> {
            this.moveChess(chess, 28);
            this.nextPlayer();
        });

    }
    if (index == 4) {
        this.playerInfo("The mover is sent back to the initial area");
        cardLabel.setText("The mover is sent back to the initial area");
        transition.play();
        transition.setOnFinished(event -> {
            this.moveChess(chess, -28);
            this.nextPlayer();
        });

    }
}

/**
 * Retrieves the player who is rolling the dice.
 * Displays a dialog to prompt the user to select the player.
 * Returns the selected player.
 *
 * @return The selected Player instance.
 */
private Player getPlayer() {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Select Player");
    alert.setHeaderText("Please specify the player who is shaking the dice.");
     // Set the content text based on the number of players
    if (players.size() > 2) {
        alert.setContentText("Starting from the top player, the clockwise order is player 0, player 1, player 2, player 3.");
    } else {
        alert.setContentText("Starting from the top player, the clockwise is player 0, then player 1");
    }
      // Create button types for each player
    ButtonType buttonType0 = new ButtonType("Player 0", ButtonBar.ButtonData.CANCEL_CLOSE);
    ButtonType buttonType1 = new ButtonType("Player 1", ButtonBar.ButtonData.CANCEL_CLOSE);
    ButtonType buttonType2 = new ButtonType("Player 2", ButtonBar.ButtonData.CANCEL_CLOSE);
    ButtonType buttonType3 = new ButtonType("Player 3", ButtonBar.ButtonData.CANCEL_CLOSE);
    alert.getButtonTypes().setAll(buttonType0, buttonType1);
    if (players.size() > 2) {// Set the button types based on the number of players
        alert.getButtonTypes().setAll(buttonType2, buttonType3);
    }
    
    // Remove the current player from the button types
    alert.getButtonTypes().remove(this.currentPlayId);
    
     // Show the alert and wait for the user to select a player
    Optional<ButtonType> buttonResult = alert.showAndWait();
    Player player;
     // Assign the selected player based on the chosen button type
    if (buttonResult.get() == buttonType0) {
        player = players.get(0);
    } else if (buttonResult.get() == buttonType1) {
        player = players.get(1);
    } else if (buttonResult.get() == buttonType2) {
        if (players.size() > 2) {
            player = players.get(2);
        } else {
            player = players.get(1);
        }
    } else {
        player = players.get(3);
    }
    return player;
}

/**
 * Moves the game to the next player.
 * Checks for game end conditions and performs necessary actions.
 */
private void nextPlayer() {
    // Check if the current player has completed their goal
    if (players.get(this.currentPlayId).isSuccess()) {
        playerInfo("You have completed your goal.");
    }
        // Check if the previous operation was rolling the dice and the result was 6
    if (this.oper == 2 && this.result == 6) {
        playerInfo("Your last throw result is 6, and you are rewarded with an extra dice rolling.");
        this.oper = 1;
        return;
    }
    
    // Set the operation to rolling the dice
    this.oper = 1;
    this.currentPlayId = (currentPlayId + 1) % players.size();
        // Check if the next player is restricted from playing this turn
    if (!players.get(currentPlayId).isSuccess()) {
        Player player = players.get(currentPlayId);
         // If the player is in the skip list, skip their turn
        if (skipPlayerList.remove(player)) {
            playerInfo("You are restricted this turn");
            nextPlayer();
            return;
        }
        playerInfo("Roll the dice");
        return;
    }
    // Check the number of players who have reached the goal
    long count = players.stream().filter(Player::isSuccess).count();
    if (count > 1) {
        // Move to the next player
        nextPlayer();
    } else {
        playerInfo("Game is over");
    }
}

/**
 * Constructs a list of ChessCell instances with the specified starting coordinates and direction.
 *
 * @param startX The x-coordinate of the starting position for the ChessCell instances.
 * @param startY The y-coordinate of the starting position for the ChessCell instances.
 * @param dir The direction in which the ChessCell instances are derived.
 * @return The constructed list of ChessCell instances.
 */
private List<ChessCell> startChessCell(double startX, double startY, String dir) {
    List<ChessCell> chessList = new ArrayList<>();
     // Create four ChessCell instances and set their layout coordinates
    chessList.add(new ChessCell());
    chessList.add(new ChessCell());
    chessList.add(new ChessCell());
    chessList.add(new ChessCell());
    for (ChessCell chess : chessList) {
        chess.setLayoutX(startX);
        chess.setLayoutY(startY);
        // Update the starting position coordinates based on the direction
        if (Objects.equals(dir, "X")) {
            startX += 55;
        } else {
            startY += 55;
        }
    }
    return chessList;
}

/**
 * Builds a menu button with the specified text and event handler.
 *
 * @param text The text to be displayed on the menu button.
 * @param e The event handler to be attached to the menu button.
 * @return The constructed menu button.
 */
public Button buildMenu(String text, EventHandler<ActionEvent> e) {
Button button = new Button(text);
button.setStyle("-fx-fill: #000000;-fx-background-insets: 0;");
button.setFont(Font.font(24));
button.setOnAction(e);
return button;
}

/**
 * Sets the anchor properties of a Node within an AnchorPane.
 *
 * @param node The Node to set the anchor properties for.
 * @param top The top anchor value.
 * @param left The left anchor value.
 * @param right The right anchor value.
 * @param bottom The bottom anchor value.
 */
public void setAnchor(Node node, Double top, Double left, Double right, Double bottom) {
AnchorPane.setTopAnchor(node, top);
AnchorPane.setLeftAnchor(node, left);
AnchorPane.setRightAnchor(node, right);
AnchorPane.setBottomAnchor(node, bottom);
}

public static void main(String[] args) {
launch(args);
}
}

