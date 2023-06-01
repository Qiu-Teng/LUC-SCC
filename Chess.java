/**
 * This class, Chess, extends the Button class and represents a chess piece in the game.
 * The chess piece is an essential component of the Harry Potter Triwizard Maze Game developed 
 * as part of the CNSCC110 Software Development Coursework at Lancaster University College at BJTU.
 * <p>
 * Each Chess object has five attributes: 
 * - success (boolean): A flag indicating whether the piece has successfully reached its destination.
 * - start (boolean): A flag indicating whether the piece has started moving.
 * - player (Player): The player that owns this chess piece.
 * - currentIndex (int): The current index/position of the chess piece on the game board.
 * - setup (int): The number of steps the chess piece can take in one move.
 * <p>
 * The Chess class provides a constructor and getter and setter methods for each attribute.
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
 * @version 1.0     
 */
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
 * This class, Chess, extends the Button class and represents a chess piece in the game.
 * The chess piece is an essential component of the Harry Potter Triwizard Maze Game developed 
 * as part of the CNSCC110 Software Development Coursework at Lancaster University College at BJTU.
 * <p>
 * Each Chess object has five attributes: 
 * - success (boolean): A flag indicating whether the piece has successfully reached its destination.
 * - start (boolean): A flag indicating whether the piece has started moving.
 * - player (Player): The player that owns this chess piece.
 * - currentIndex (int): The current index/position of the chess piece on the game board.
 * - setup (int): The number of steps the chess piece can take in one move.
 * <p>
 * The Chess class provides a constructor and getter and setter methods for each attribute.
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
class Chess extends Button {

    /**
     * Flag indicating whether the piece has successfully reached its destination.
     */
    private boolean success;
    
    /**
     * Flag indicating whether the piece has started moving.
     */
    private boolean start;
    
    /**
     * The player that owns this chess piece.
     */
    private Player player;
    
    /**
     * The current index/position of the chess piece on the game board.
     */
    private int currentIndex;
    
    /**
     * The number of steps the chess piece can take in one move.
     */
    private int setup;
    
    /**
     * Constructs a chess piece using an image.
     * Also configures the display attributes of the chess piece.
     *
     * @param image The image of the chess piece.
     */
    public Chess(Image image) {
    ImageView value = new ImageView(image);
    value.setPreserveRatio(true);
    value.setFitWidth(50);
    value.setFitHeight(50);
    value.setStyle("-fx-background-radius:  50px;-fx-border-radius: 50px;");
    Rectangle rectangle = new Rectangle(value.prefWidth(-1), value.prefHeight(-1));
    rectangle.setArcWidth(50);
    rectangle.setArcHeight(50);
    value.setClip(rectangle);
    setPadding(Insets.EMPTY);
    setHeight(50);
    setWidth(50);
    setPrefSize(50, 50);
    setStyle("-fx-background-radius:  50px");
    setGraphic(value);
    }
    
    /**
     * Sets the number of steps the chess piece can take in one move.
     *
     * @param setup The number of steps.
     */
    public void setSetup(int setup) {
        this.setup = setup;
    }
    
    /**
     * Returns the number of steps the chess piece can take in one move.
     *
     * @return The number of steps.
     */
    
    public int getSetup() {
        return setup;
    }
       
    /**
     * Sets the current index/position of the chess piece on the game board.
     *
     * @param currentIndex The current index.
     */
    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }
    
    /**
     * Returns the current index/position of the chess piece on the game board.
     *
     * @return The current index.
     */
    public int getCurrentIndex() {
        return currentIndex;
    }
    
    /**
     * Sets the player that owns this chess piece.
     *
     * @param player The player.
     */
    public void setPlayer(Player player) {
        this.player = player;
    }
    
    /**
     * Returns the player that owns this chess piece.
     *
     * @return The player.
     */
    public Player getPlayer() {
        return player;
    }
        
    /**
     * Sets the flag indicating whether the piece has started moving.
     *
     * @param start The start flag.
     */
    public void setStart(boolean start) {
        this.start = start;
    }
        
    /**
     * Returns the flag indicating whether the piece has started moving.
     *
     * @return The start flag.
     */
    public boolean isStart() {
        return start;
    }
        
    /**
     * Sets the flag indicating whether the piece has successfully reached its destination.
     *
     * @param success The success flag.
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }
    
    /**
     * Returns the flag indicating whether the piece has successfully reached its destination.
     *
     * @return The success flag.
     */
    public boolean isSuccess() {
        return success;
    }
    
    }
    
    
    
    