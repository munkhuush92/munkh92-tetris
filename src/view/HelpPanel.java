/*
 * TCSS 305 - Project Tetris
 */
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;


/**
 *  The Help panel that displays keyboard help instruction for the user.
 * @author Munkhbayar Ganbold
 * @version Autumn 2015 TCSS 305
 */
public class HelpPanel extends JPanel implements PropertyChangeListener {
    /** A generated serial UID. */
    private static final long serialVersionUID = -4408051484847094550L;

    /** A default value for dimension of side panel.    */
    private static final Dimension DEFAULT_DIMENSION_HELP = new Dimension(180, 130);
    
    /** A constant order number for hard Drop key.  */
    private static final int NUMBER_THREE  = 3;
    
    /** A constant order number for rotate key.  */
    private static final int NUMBER_FOUR  = 4;
    
    /** A Default size for font.    */
    private static final int CONSTANT_FONT_VALUE = 12;
    
    //String of keyboard keys
    
    /** A name of the left keyboard key.    */
    private String myMoveLeftKey;
    
    /** A name of the right keyboard key.    */
    private String myMoveRightKey;
    
    /** A name of the down keyboard key.    */
    private String myMoveDownKey;
    
    /** A name of the hard drop keyboard key.    */
    private String myHardDropKey;
    
    /** A name of the rotate keyboard key.    */
    private String myRotateKey;
    
    /** A current keyboard keys are in use. */
    private final List<Integer> myCurrentKeys; 
    
    /** A main text area in which keyboard help instructions are in.    */
    private JTextArea myHelpTextArea;
    

    /** 
     * Constructs the help panel.
     *  @param theController of tetris
     */
    public HelpPanel(final GUIController theController) {
        super();
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        final GUIController currentController = theController;
        myCurrentKeys = currentController.getCurrentKeys();
        myMoveLeftKey = KeyEvent.getKeyText(KeyEvent.VK_LEFT);
        myMoveRightKey = KeyEvent.getKeyText(KeyEvent.VK_RIGHT);
        myMoveDownKey = KeyEvent.getKeyText(KeyEvent.VK_DOWN);
        myHardDropKey = KeyEvent.getKeyText(KeyEvent.VK_SPACE);
        myRotateKey = KeyEvent.getKeyText(KeyEvent.VK_UP);
        this.add(createText());
    }
    
    /**
     *  Helper method which construct the String representation of
     *  help instruction text.
     * @return String representation of help text
     */
    private String helpText() {
        final String newLine = "\n";
        final String helpInstructions = "Keyboard Instruction " + newLine 
                        + "To move left = " + myMoveLeftKey + newLine 
                        + "To move right = " + myMoveRightKey + newLine 
                        + "To move down = " + myMoveDownKey + newLine 
                        + "To rotate = " + myRotateKey + newLine 
                        + "To hard drop = " + myHardDropKey + newLine 
                        + "To pause/resume the game = P" + newLine 
                        + "To mute the sound = M";
        return helpInstructions;
    }
    
    
    
    /**
     *  Creates the help instruction of keyboard keys that moves
     *  the Tetris piece.
     * @return JTextArea has help instruction.
     */
    private JTextArea createText() {
        myHelpTextArea = new JTextArea(helpText());
        myHelpTextArea.setWrapStyleWord(true);
        myHelpTextArea.setFont(new Font("set", Font.BOLD, CONSTANT_FONT_VALUE));
        myHelpTextArea.setPreferredSize(DEFAULT_DIMENSION_HELP);
        myHelpTextArea.setFocusable(false);
        myHelpTextArea.setBackground(Color.orange);
        return myHelpTextArea;
    }
    /**
     * Changes the tetris game keys.
     */
    private void setText() { 
        myMoveLeftKey = KeyEvent.getKeyText(myCurrentKeys.get(0));
        myMoveRightKey = KeyEvent.getKeyText(myCurrentKeys.get(1));
        myMoveDownKey = KeyEvent.getKeyText(myCurrentKeys.get(2));
        myHardDropKey = KeyEvent.getKeyText(myCurrentKeys.get(NUMBER_THREE));
        myRotateKey = KeyEvent.getKeyText(myCurrentKeys.get(NUMBER_FOUR));
        
    }

    @Override
    public void propertyChange(final PropertyChangeEvent theEvt) {
        if ("Keys are changed".equals(theEvt.getPropertyName())) {
            setText();
            myHelpTextArea.setText(helpText());
        }
        
    }
    
}
