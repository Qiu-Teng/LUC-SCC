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
 * The Player class represents a player in the Harry Potter Triwizard Maze Game.
 * Each Player has an associated image, a list of chess pieces (Chess) they control,
 * a list of home cells (ChessCell) where their chess pieces start, and a list of start cells
 * (ChessCell) where their chess pieces aim to reach. The start index is the index of the first
 * cell where a chess piece is placed and the end index is the index of the last cell where a
 * chess piece aims to reach.
 * <p>
 * Each Player provides getter and setter methods for each attribute and a method to check if
 * all their chess pieces have reached their destinations.
 *
 * CNSCC110 Software Development Coursework-Lancaster University College at BJTU
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
class Player {
    /**
     * The image associated with this player.
     */
    private Image image;

    /**
     * The list of chess pieces this player controls.
     */
    private List<Chess> chessList;
    /**
     * The list of home cells where this player's chess pieces start.
     */
    private List<ChessCell> homeList;
    
    /**
     * The list of start cells where this player's chess pieces aim to reach.
     */
    private List<ChessCell> startList;
    /**
     * The index of the first cell where a chess piece of this player is placed.
     */
    private int startIndex;
    /**
     * The index of the last cell where a chess piece of this player aims to reach.
     */
    private int endIndex;
    
    
    public List<ChessCell> getStartList() {
            return startList;
        }
    
    public void setStartList(List<ChessCell> startList) {
            this.startList = startList;
        }
    
    /**
     * Checks if all this player's chess pieces have reached their destinations.
     *
     * @return True if all chess pieces have reached their destinations, false otherwise.
     */
    public boolean isSuccess() {
            for (Chess chess : chessList) {
                if (!chess.isSuccess()) {
                    return false;
                }
            }
            return true;
        }
    
    public void setImage(Image image) {
            this.image = image;
        }
    
    public Image getImage() {
            return image;
        }
     // Sets the list of chess pieces for this player.
    public void setChessList(List<Chess> chessList) {
            this.chessList = chessList;
        }
    
    // Returns the list of chess pieces associated with this player.
    public List<Chess> getChessList() {
            return chessList;
        }
     // Sets the list of home cells for this player's chess pieces.
    public void setHomeList(List<ChessCell> homeList) {
            this.homeList = homeList;
        }
       // Returns the list of home cells for this player's chess pieces.
    public List<ChessCell> getHomeList() {
            return homeList;
        }
      // Sets the starting index for this player's chess pieces on the board.
    public void setStartIndex(int startIndex) {
            this.startIndex = startIndex;
        }
    
    public int getStartIndex() {
            return startIndex;
        }
     // Sets the ending index for this player's chess pieces on the board.
    public void setEndIndex(int endIndex) {
            this.endIndex = endIndex;
        }
    
    public int getEndIndex() {
            return endIndex;
        }
    }
    