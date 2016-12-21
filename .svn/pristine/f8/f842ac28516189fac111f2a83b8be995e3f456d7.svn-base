/*
 * TCSS 305 - Project Tetris
 */
package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import model.AbstractPiece;
import model.Board;

/**
 * Displays the next coming-up piece in the side panel. It has an observer 
 * connection with Board class. 
 * 
 * @author Munkhbayar Ganbold
 * @version Autumn 2015 TCSS 305
 */
public class NextPiecePanel extends JPanel implements Observer {
     
    /** Auto-generated serial UID.  */
    private static final long serialVersionUID = 1L;
    
    /** A Default grid size for game board. */
    private static final int DEFAULT_GRID_SIZE = 20;
    
    /** A Default grid size for game board. */
    private static final int MEDIUM_GRID_SIZE = 30;
    
    /** A Default grid size for game board. */
    private static final int LARGE_GRID_SIZE = 40;
    
    /** A Default size for the frame of next piece display. */
    private static final int DEFAULT_FRAME_SIZE = 120;
  
    /** An instance variable for frame size.    */
    private int myRectangleFrameSize;
    
    /** A main game board in use.   */
    private final Board myBoard;
    
    /** An instnace variable for grid size. */
    private int myGridSize;
    
    /**
     *  Constructs the Next Piece Panel with given board. Sets the 
     *  preferred size to default values. 
     * @param theBoard Main game board
     */
    public NextPiecePanel(final Board theBoard) {
        super();
        myBoard = theBoard;
        myGridSize = DEFAULT_GRID_SIZE;
        setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(DEFAULT_FRAME_SIZE, DEFAULT_FRAME_SIZE));
        myRectangleFrameSize = DEFAULT_FRAME_SIZE;
    }
    
    
    /**
     *  Changes the dimension of GUI with given size.
     * @param theSizeName grid size name
     */
    public void setGridSize(final String theSizeName) {
        final String name = theSizeName;
        switch (name) {
            case "Large" : myGridSize = LARGE_GRID_SIZE;
            myRectangleFrameSize = DEFAULT_FRAME_SIZE * 2;
                            break;
            case "Small" : myGridSize = DEFAULT_GRID_SIZE;
            myRectangleFrameSize = DEFAULT_FRAME_SIZE;
                break;
            case "Medium" : myGridSize = MEDIUM_GRID_SIZE;
            myRectangleFrameSize = DEFAULT_FRAME_SIZE + DEFAULT_FRAME_SIZE / 2;
                break;
            default:
                break;     
        }
        this.setPreferredSize(new Dimension(myRectangleFrameSize, 
                                            myRectangleFrameSize));
    }
    
    /**
     * 
     */
    @Override
    protected void paintComponent(final Graphics theGrap) {
        super.paintComponent(theGrap);
        final Graphics2D g2d = (Graphics2D) theGrap;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);
        //draw big frame
        g2d.setStroke(new BasicStroke(2));
        g2d.setColor(Color.BLUE);
        g2d.drawRect(0, 0, myRectangleFrameSize, myRectangleFrameSize);
        
        final int [][] temp = ((AbstractPiece) myBoard.getNextPiece()).getBoardCoordinates();
        
        
        for (int i = 0; i < temp.length; i++) {
            final int x = (temp[i][0] - 2) * myGridSize;
            final int y = ((24 - temp[i][1])) * myGridSize;
            g2d.fillRect(x, y, myGridSize, myGridSize);
            g2d.setColor(Color.yellow);
            
            final Rectangle2D frame = new Rectangle2D.Double(x ,
                                          y, myGridSize, myGridSize);
            g2d.draw(frame);
            g2d.setColor(Color.BLUE);
        }
    }


    @Override
    public void update(final Observable theObser, final Object theArg) {
        repaint();
    }

}
