/*
 * TCSS 305 - Project Tetris
 */
package view;


import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *  Score board that display the total score and total cleared lines 
 *  in the side panel.
 * 
 * @author Munkhbayar Ganbold
 * @version Autumn 2015 TCSS 305
 */
public class ScorePanel extends JPanel implements PropertyChangeListener {
    
    /**
     * 
     */
    private static final long serialVersionUID = -4715877706123530750L;

    /** A default value for dimension of score panel.    */
    private static final Dimension DEFAULT_DIMENSION_HELP = new Dimension(200, 120);
    
    /** A default message that display how lines are cleared.   */
    private static final String CLEARED_LINES_LABEL = "My cleared line(s): ";
    
    /** A default message that display how points the player scored.   */
    private static final String SCORE_LABEL = "My score is: ";
    
    /** A default score point for each line.    */
    private static final int DEFAULT_POINT_ONELINE = 10;
    
    /** A Label that shows the current score of the game.   */
    private final JLabel myScoreLabel;
    
    /** A Label that shows the total number of cleared lines.   */
    private final JLabel myClearedLinesLabel;
    
    /** Main game panel.    */
    private final GamePanel myGamePanel;
    
    
    /**
     * Constructs score panel with given game panel and 
     * initializes its labels.
     * @param theGamePanel of Tetris
     */
    public ScorePanel(final GamePanel theGamePanel) {
        super();
        setPreferredSize(DEFAULT_DIMENSION_HELP);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        myGamePanel = theGamePanel;
        myScoreLabel = new JLabel(SCORE_LABEL + myGamePanel.getClearedLines()
            * DEFAULT_POINT_ONELINE);
        myClearedLinesLabel = new JLabel(CLEARED_LINES_LABEL
        + myGamePanel.getClearedLines());
        add(myClearedLinesLabel);
        add(myScoreLabel);
    }
    

    @Override
    public void propertyChange(final PropertyChangeEvent theEvt) {
     
        if ("lines are cleared".equals(theEvt.getPropertyName())) {
            myClearedLinesLabel.setText(CLEARED_LINES_LABEL 
                                            + theEvt.getNewValue());
            myScoreLabel.setText(SCORE_LABEL + (Integer) theEvt.getNewValue() 
                * DEFAULT_POINT_ONELINE);
        }
    }
 
    /**
     * Resets the score panel whenever there is a new game.
     * it also notifies game panel to resets its lines.
     */
    public void reset() {
        myScoreLabel.setText(SCORE_LABEL 
                             + myGamePanel.getClearedLines() * DEFAULT_POINT_ONELINE);
        myClearedLinesLabel.setText(CLEARED_LINES_LABEL
                                    + myGamePanel.getClearedLines());
    }
    
}
