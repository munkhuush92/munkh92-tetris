/*
 * TCSS 305 - Project Tetris
 */
package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import model.AbstractPiece;
import model.Block;
import model.Board;
import model.Piece;

/**
 * A Display of a Tetris game board panel.
 * 
 * @author Munkhbayar Ganbold
 * @version Autumn 2015
 */
public class GamePanel extends JPanel implements Observer {

    /** Automatic creation serail code.*/
    private static final long serialVersionUID = 1L;
    
    /** A Constant value for Y coordinate which we'll use for calculating
     *  y coordinate for paint component. */
    private static final int Y_COORDINATE_CONSTANT = 19;
    
    /** A constant integer value for level 3.   */
    private static final int LEVEL_THREE = 3;
    
    /** A constant integer value for level 4.   */
    private static final int LEVEL_FOUR = 4;
    
    /** A Default width of game board.      */
    private static final int DEFAUL_WIDTH_GAME = 10;
    
    /** A Default height of game board.      */
    private static final int DEFAUL_HEIGHT_GAME = 20;
    
    /** A Default grid size for game board. */
    private static final int DEFAULT_GRID_SIZE = 20;
    
    /** A Default grid size for game board. */
    private static final int MEDIUM_GRID_SIZE = 28;
    
    /** A Default grid size for game board. */
    private static final int LARGE_GRID_SIZE = 35;
    
    
    /** A Tile or Grid size for GUI and game panel. */
    private int myGridSize;
    
    /** A background image that is being drawn. */
    private Image myImg;
    
    /** A Game board for Game panel.    */
    private final Board myBoard;
    
    /** A boolean value for setting grid on and off.    */
    private boolean myGridIsOn;
    
    /** A counter for cleared lines during the game.    */
    private int myClearedLines;
    
    /** this hold the previous height of the frozen pieces. */
    private int myPreviousLines;

    /** An Integer that counts the level or difficulty of the game. */
    private Integer myLevelCounter;
    
    /** Level one background image. */
    private Image myLevelOneImage;
    
    /** Level two background image. */
    private  Image myLevelTwoImage;
    
    /** Level three background image. */
    private Image myLevelThreeImage;

    /** Level four background image. */
    private Image myLevelFourImage;

    /** Level five background image. */
    private Image myLevelFiveImage;

   
    /**
     * Constructs the game panel with given board, and 
     * initializes the instance variables.
     * @param theBoard Main Board
     */
    public GamePanel(final Board theBoard) {
        super();
        myLevelCounter = 0;
        myBoard = theBoard;
        myClearedLines = 0;
        myGridSize = DEFAULT_GRID_SIZE;
        this.setBackground(Color.lightGray);
        this.setSize(new Dimension(theBoard.getWidth() * myGridSize, 
                                   theBoard.getHeight() * myGridSize));
        this.setPreferredSize(new Dimension(theBoard.getWidth() * myGridSize, 
                                            theBoard.getHeight() * myGridSize));
        loadsBackrgoundPics();
    }
    
    /**
     * Loads background images from the project folder named backgroundpics.
     */
    private void loadsBackrgoundPics() {
        try {
            myLevelOneImage = ImageIO.read(new File("backgroundpics/lvl1.jpeg"));
            myLevelTwoImage = ImageIO.read(new File("backgroundpics/lvl2.jpeg"));
            myLevelThreeImage = ImageIO.read(new File("backgroundpics/lvl3.jpeg"));
            myLevelFourImage = ImageIO.read(new File("backgroundpics/lvl4.jpeg"));
            myLevelFiveImage = ImageIO.read(new File("backgroundpics/lvl5.jpeg"));
        } catch (final IOException exception) {
            exception.printStackTrace();
        }
        
    }
    /**
     *  Changes the dimension of GUI with given size.
     * @param theSizeName grid size name
     */
    public void setGridSize(final String theSizeName) {
        final String name = theSizeName;
        switch (name) {
            case "Large" : myGridSize = LARGE_GRID_SIZE;
                break;
            case "Small" : myGridSize = DEFAULT_GRID_SIZE;
                break;
            case "Medium" : myGridSize = MEDIUM_GRID_SIZE;
                break;
            default:
                break; 
         
        }
        this.setSize(new Dimension(DEFAUL_WIDTH_GAME * myGridSize, 
                                   DEFAUL_HEIGHT_GAME * myGridSize));
        this.setPreferredSize(new Dimension(DEFAUL_WIDTH_GAME * myGridSize, 
                                            DEFAUL_HEIGHT_GAME * myGridSize));
    }
    @Override
    protected void paintComponent(final Graphics theGrap) {
        super.paintComponent(theGrap);
        final Graphics2D g2d = (Graphics2D) theGrap;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);
        
        drawBackgroundImage(g2d, myLevelCounter);
        // if grid is on, then drawing the lines
        int xCoordinate = 0;
        int yCoordinate = 0;
        if (myGridIsOn) {
            g2d.setStroke(new BasicStroke(1));
            g2d.setPaint(Color.lightGray);
            while (xCoordinate < getWidth()) {
                xCoordinate += myGridSize; 
                g2d.drawLine(xCoordinate, 0, xCoordinate, getHeight());
            }
            
            while (yCoordinate < getHeight()) {
                yCoordinate += myGridSize; 
                g2d.drawLine(0, yCoordinate, getWidth(), yCoordinate);
            }
        }
        final int [][] temp = ((AbstractPiece)
                        myBoard.getCurrentPiece()).getBoardCoordinates();
        //draw the frozen blocks
        final LinkedList<Block[]> frozenBlock = 
                        (LinkedList<Block[]>) myBoard.getFrozenBlocks();
        //to calculate how many lines are cleared.
        if (myPreviousLines > frozenBlock.size() && myPreviousLines != 0) {
            myClearedLines += myPreviousLines - frozenBlock.size();
            firePropertyChange("lines are cleared", myPreviousLines, myClearedLines);
        }
        int y = 0;
        for (final Block[] b: frozenBlock) {
            for (int i = 0; i < b.length; i++) {
              
                if (!(b[i].equals(Block.EMPTY))) {
                    final GradientPaint whiteEdgeDifferentColor = 
                                    new GradientPaint(i * myGridSize, 
                                     (Y_COORDINATE_CONSTANT - y) * myGridSize
                                     , Color.WHITE, i * myGridSize + myGridSize / 2, 
                                      ((Y_COORDINATE_CONSTANT - y)) * myGridSize + myGridSize 
                                      , decideColorPiece(b[i]));
                    
                    g2d.setPaint(whiteEdgeDifferentColor);
                    g2d.fillRect(i * myGridSize, (Y_COORDINATE_CONSTANT - y) 
                                 * myGridSize, myGridSize, myGridSize);
                    g2d.setPaint(Color.yellow);
                    g2d.setStroke(new BasicStroke(2));
                    final Rectangle2D frame = new Rectangle2D.Double(i * myGridSize,
                        (Y_COORDINATE_CONSTANT - y) * myGridSize, myGridSize, myGridSize);
                    g2d.draw(frame);  
                  
                } 
            }
            
            y++;  
        }
        //draw the current piece
        drawCurrentPieces(g2d, temp);
        myPreviousLines = frozenBlock.size();
        
    }
    

    /**
     * Determines the color of the piece with given block.
     * @param theBlock current block on the board
     * @return Color of the piece
     */
    private Color decideColorPiece(final Block theBlock) {
        Color currentColor = null;
        if (theBlock == Block.I) {
            currentColor = Color.BLUE;
        } else if (theBlock == Block.L) {
            currentColor = Color.ORANGE;
        } else if (theBlock == Block.S) {
            currentColor = Color.LIGHT_GRAY;
        } else if (theBlock == Block.J) {
            currentColor = Color.CYAN;
        } else if (theBlock == Block.T) {
            currentColor = Color.RED;
        } else if (theBlock == Block.Z) {
            currentColor = Color.PINK;
        } else if (theBlock == Block.O) {
            currentColor = Color.GREEN;
        }
        return currentColor;      
    }
    
    /**
     * Determines the color of the piece with given piece.
     * @param thePiece current piece on the board
     * @return Color of the piece
     */
    private Color decideColor(final Piece thePiece) {
        Color currentColor = null;
        
        switch (((AbstractPiece) thePiece).getBlock()) {
            case I : currentColor = Color.BLUE;
                break;
            case L : currentColor = Color.ORANGE;
                break;
            case S : currentColor = Color.LIGHT_GRAY;
                break;
            case  J: currentColor = Color.CYAN;
                break;
            case T : currentColor = Color.RED;
                break;
            case Z : currentColor = Color.PINK;
                break;
            case O : currentColor = Color.GREEN;
                break;
            default: //do nothing
           
        }
        
        return currentColor;
    }
    
    /**
     * This method draws the current piece on the game panel
     * using the given 2D graphics and 2D array of coordinate values.
     * @param theG2d 2D graphics
     * @param theValues 
     */
    private void drawCurrentPieces(final Graphics2D theG2d, final int[][] theValues) {
        GradientPaint whiteEdgeDifferentColor = null;
        Color newColor = null;
        
        for (int i = 0; i < theValues.length; i++) {
            newColor = decideColor(myBoard.getCurrentPiece());
            whiteEdgeDifferentColor = new GradientPaint(theValues[i][0] * myGridSize,
                                         (Y_COORDINATE_CONSTANT - theValues[i][1]) * myGridSize
                              , Color.WHITE, theValues[i][0] * myGridSize + myGridSize / 2, 
                              (Y_COORDINATE_CONSTANT - theValues[i][1]) * myGridSize 
                              + myGridSize , newColor);
            theG2d.setPaint(whiteEdgeDifferentColor);
            theG2d.fillRect(theValues[i][0] * myGridSize,
                            (Y_COORDINATE_CONSTANT - theValues[i][1]) 
                            * myGridSize , myGridSize, myGridSize);
            theG2d.setPaint(Color.yellow);
            
            final Rectangle2D frame = new Rectangle2D.Double(theValues[i][0] 
                            * myGridSize, (Y_COORDINATE_CONSTANT - theValues[i][1])
                            * myGridSize, myGridSize, myGridSize);
            theG2d.draw(frame);
        }
    }
    
    /**
     * Draws the background image depending on level number give.
     * @param theG2d 2D graphics
     * @param theLevelNumber of game
     */
    private void drawBackgroundImage(final Graphics2D theG2d, final int theLevelNumber) {
        if (theLevelNumber == 0 || theLevelNumber == 1) {
            myImg = myLevelOneImage;
        } else if (theLevelNumber == 2) {
            myImg = myLevelTwoImage;
        } else if (theLevelNumber == LEVEL_THREE) {
            myImg = myLevelThreeImage;
        } else if (theLevelNumber == LEVEL_FOUR) {
            myImg = myLevelFourImage;
        } else {
            myImg = myLevelFiveImage;
        }
        theG2d.drawImage(myImg, 0, 0, 
                     DEFAUL_WIDTH_GAME * myGridSize, DEFAUL_HEIGHT_GAME * myGridSize, null);
        
    }
    
    /**
     * Reset the lines and the background picture to their original state.
     */
    public void resetLines() {
        myClearedLines = 0;
        myPreviousLines = 0;
        //also reset myImamge to default
        myLevelCounter = 0;
        myImg = myLevelOneImage;
    }
    
    /**
     *  Accessor method for getting cleared lines.
     * @return total number of cleared lines
     */
    public int getClearedLines() {
        return myClearedLines;
    }

    @Override
    public void update(final Observable theObservable, final Object theArg) {
        if (theObservable instanceof GUIMain && theArg instanceof Integer) {
            myLevelCounter = (Integer) theArg;
        }
        repaint();
    }

    /** 
     * Sets grid on the game panel on and off.
     * @param theGridIsOn of Game panel.
     */
    public void setGridOn(final boolean theGridIsOn) {
        if (myGridIsOn) {
            myGridIsOn = false;
        } else {
            myGridIsOn = theGridIsOn;
        }
    }
}
