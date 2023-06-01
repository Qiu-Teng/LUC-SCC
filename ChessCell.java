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
 * This class, ChessCell, extends the Button class and represents a cell on the game board.
 * The ChessCell is a component of the Harry Potter Triwizard Maze Game developed as part of the 
 * CNSCC110 Software Development Coursework at Lancaster University College at BJTU.
 * <p>
 * Each ChessCell object has two attributes: 
 * - card (boolean): A flag indicating whether the cell has a card.
 * - chess (Chess): The chess piece that currently occupies this cell.
 * <p>
 * The ChessCell class provides a constructor and getter and setter methods for each attribute.
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
class ChessCell extends Button {

    /**
     * Flag indicating whether the cell has a card.
     */
    private boolean card;
        
    /**
     * The chess piece that currently occupies this cell.
     */
    private Chess chess;
    
    /**
     * Constructs a ChessCell.
     * Also configures the display attributes of the cell.
     */
    public ChessCell() {
        setPadding(Insets.EMPTY);
        setHeight(50);
        setWidth(50);
        setPrefSize(50, 50);
        setStyle("-fx-background-radius:  50px");
    }
    
    /**
     * Sets the chess piece that currently occupies this cell.
     *
     * @param chess The chess piece.
     */
    public void setChess(Chess chess) {
        this.chess = chess;
    }
    
    /**
     * Returns the chess piece that currently occupies this cell.
     *
     * @return The chess piece.
     */
    public Chess getChess() {
        return chess;
    }
    
    /**
     * Sets the flag indicating whether the cell has a card.
     *
     * @param card The card flag.
     */
    
    public void setCard(boolean card) {
        this.card = card;
    }
    
    /**
     * Returns the flag indicating whether the cell has a card.
     *
     * @return The card flag.
     */
    public boolean isCard() {
        return card;
    }
    
    }