/*
 * TCSS 305 - Project Tetris
 */
package view;

import java.awt.Choice;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import model.Board;

/**
 * A GUI controller that handles the user's keyboard events with board.
 * 
 * @author Munkhbayar Ganbold
 * @version Autumn 2015
 */
public class GUIController extends KeyAdapter implements Observer {
    /** A constant number for order of left key in the list. (first element). */
    private static final int LEFT_KEY_ORDER_VALUE = 0;
    
    /** A constant number for order of right key in the list. (second element). */
    private static final int RIGHT_KEY_ORDER_VALUE = 1;
    
    /** A constant number for order of down key in the list. (third element). */
    private static final int DOWN_KEY_ORDER_VALUE = 2;
    
    /** A constant number for order of hard drop key in the list. (fourth element). */
    private static final int HARDDROP_KEY_ORDER_VALUE = 3;
    
    /** A constant number for order of rotate key in the list. (fourth element). */
    private static final int ROTATE_KEY_ORDER_VALUE = 4;
    
    /** A main game panel.  */
    private final Board myBoard;
    
    /** The left key integer value. */
    private final int myLeftKey;
    
    /** The right key integer value. */
    private final int myRightKey;
    
    /** The down key integer value. */
    private final int myDownKey;
    
    /** The hard drop key integer value. */
    private final int myHardDropKey;
    
    /** The rotate key integer value. */
    private final int myRotateKey;
    
    /** A List for storing keyboard keys.   */
    private final List<Integer> myKeys;
    
    /** An Inner class KeyboardKeys where all the keyboard values are stored.   */
    private final KeyboardKeys myKeyboardData;
    
    /** My main property change support for listeners.  */
    private final PropertyChangeSupport myPcs = new PropertyChangeSupport(this);

    /** Constructs GUI Controller with given board, and
     * Initializes the instance variables.
     * @param theBoard Main game board
     */
    public GUIController(final Board theBoard) {
        super();
        myBoard = theBoard;
        myLeftKey = KeyEvent.VK_LEFT;
        myRightKey = KeyEvent.VK_RIGHT;
        myDownKey =  KeyEvent.VK_DOWN;
        myHardDropKey = KeyEvent.VK_SPACE;
        myRotateKey = KeyEvent.VK_UP;
        myKeyboardData = new KeyboardKeys();
        myKeys = new ArrayList<Integer>();
        addKeysToList();  
    }
    
    /**
     * Adds the PropertyChangeListener thePcl to the list of PropertyChangeListeners
     * managed by objects of this class. 
     * 
     * @param thePcl the PropertyChangeListener added.
     */
    public void addPropertyChangeListener(final PropertyChangeListener thePcl) {
        this.myPcs.addPropertyChangeListener(thePcl);
    }
 
    /** Add key elements to the List.
     * Also, adds this class to this observerable Keyboard class. 
     */
    private void addKeysToList() {
        //add the this observer class to InnerClass KeyBoard (observable).
        myKeyboardData.addObserver(this);
        myKeys.add(myLeftKey);
        myKeys.add(myRightKey);
        myKeys.add(myDownKey);
        myKeys.add(myHardDropKey);
        myKeys.add(myRotateKey);
    }


    /** Modifies the keyboard keys with given list of keys.
     *  modifies the keys in specific order : 1) left key, 2) right key,
     *  3) down key, 4) hard drop key, 5) rotate key.
     * @param theKeyList A New List of Keys
     */
    public void setKeys(final List<Choice> theKeyList) {
        for (int i = 0; i < theKeyList.size(); i++) {
            myKeys.set(i, myKeyboardData.getKeyValues().
                       get(theKeyList.get(i).getSelectedItem()));
            myPcs.firePropertyChange("Keys are changed", null, myKeys);
        } 
  
    }
    
    /**
     *  Accessor method for getting current keyboard keys.
     * @return List of Keyboard keys for playing Tetris
     */
    public List<Integer> getCurrentKeys() {
        return myKeys;
    }

    @Override
    public void keyPressed(final KeyEvent theEvent) {
        if (myKeys.get(LEFT_KEY_ORDER_VALUE) == theEvent.getKeyCode()) {
            myBoard.moveLeft();
        } else if (myKeys.get(RIGHT_KEY_ORDER_VALUE) == theEvent.getKeyCode()) {
            myBoard.moveRight();
        } else if (myKeys.get(DOWN_KEY_ORDER_VALUE) == theEvent.getKeyCode()) {
            myBoard.moveDown();
        } else if (myKeys.get(HARDDROP_KEY_ORDER_VALUE) == theEvent.getKeyCode()) {
            myBoard.hardDrop();
            
            myPcs.firePropertyChange("Hard drop pressed", null, 1);
        } else if (myKeys.get(ROTATE_KEY_ORDER_VALUE) == theEvent.getKeyCode()) {
            myBoard.rotate();
        }
      
     
        
    }
    
    /**
     *  Changes the visibility of the frame. 
     * @param theVisible Boolean value for visibility of the frame.
     */
    public void setVisibleFrame(final boolean theVisible) {
        myKeyboardData.myFrame.setVisible(theVisible);
    }


    @Override
    public void update(final Observable theObser, final Object theArg) {
        setKeys(myKeyboardData.getChoices()); 
    }
    
    /**
     *  Inner class which creates a JFrame with keyboard choices that
     *  user can choose.
     * @author Munkhbayar Ganbold
     * @version Autumn 2015 TCSS 305
     */
    class KeyboardKeys extends Observable {
        
        /** A Default size for width of the frame.  */
        private static final int DEFAULT_WIDTH_SIZE = 320;
        
        /** A Default size for height of the frame.  */
        private static final int DEFAULT_HEIGHT_SIZE = 220;
        
        /** A Default size for width of the choice option.  */
        private static final int CHOICE_WIDTH_SIZE = 80;
        
        /** A Default size for height of the choice option.  */
        private static final int CHOICE_HEIGHT_SIZE = 20;
        
        /** A list of string that has all the keyboard key values.  */
        private final List<String> myKeyNames;
        
        /** A Hash map where it stores keyboard key name and its keycode.   */
        private final Map<String, Integer> myKeyValueList;

        /** A List of choices.  */
        private final List<Choice> myChoices;
        
        /** The Frame of the customizing keyboard keys. */
        private final JFrame myFrame;
        
        /**
         * Constructs the Keyboard keys class and initializes 
         * instance variables.
         */
        public KeyboardKeys() {
            super();
            myFrame = new JFrame("Keyboard changes");
            myKeyNames = new ArrayList<String>();
            
            myChoices = new ArrayList<Choice>();
            myKeyValueList = new HashMap<String, Integer>();
            initComponents();
            myFrame.setLayout(new FlowLayout());
            myFrame.setResizable(false);
            myFrame.setLocationRelativeTo(null);
            myFrame.pack();
        }
        
        /**
         *  Initializes the components (Choices and Button).
         *  and it loads the keys with all the possible keyboard keys.
         */
        private void initComponents() {
            loadKeys();
            myFrame.setPreferredSize(new Dimension(DEFAULT_WIDTH_SIZE, DEFAULT_HEIGHT_SIZE));
            final Choice leftKeyChoice = addChoice("Left key", 30, 30);
            leftKeyChoice.select("Left");
            myChoices.add(leftKeyChoice);
            
            final Choice rightKeyChoice = addChoice("Right key", 30, 50);
            rightKeyChoice.select("Right");
            myChoices.add(rightKeyChoice);
            
            final Choice downKeyChoice  = addChoice("Down key", 30, 70);
            downKeyChoice.select("Down");
            myChoices.add(downKeyChoice);
            
            final Choice hardDropKeyChoice = addChoice("Harddrop key", 30, 90);
            hardDropKeyChoice.select("␣");
            
            myChoices.add(hardDropKeyChoice);
            
            final Choice rotateKeyChoice = addChoice("Rotate key", 30, 110);
            rotateKeyChoice.select("Up");
            myChoices.add(rotateKeyChoice);
            
            final JButton doneButton = new JButton("Done");
            doneButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent theEvent) {
                    setChanged();
                    notifyObservers();
                    myFrame.dispatchEvent(new WindowEvent(
                              myFrame, WindowEvent.WINDOW_CLOSING)); 
                }   
            });
            myFrame.add(doneButton);
        }

        /**
         *  Adds the choices to the container with its string names.
         * @param theName Name of Choice
         * @param theX coordinate of location
         * @param theY coordinate of location
         * @return Choice
         */
        private Choice addChoice(final String theName, final int theX, final int theY) {
            final JLabel optionLabel = new JLabel(theName);
            optionLabel.setBounds(theX, theY, CHOICE_WIDTH_SIZE, CHOICE_HEIGHT_SIZE);
            final Choice key = new Choice();
            for (final String name : myKeyNames) {
                key.add(name);
            }
            
            key.setBounds(theX, theY, CHOICE_HEIGHT_SIZE, CHOICE_HEIGHT_SIZE);
            myFrame.add(optionLabel);
            myFrame.add(key);
            return key;
        }
        
        /**
         *  Loads all the available keyboard keys in the list and hashmap.
         */
        private void loadKeys() {
            final Field[] fields =  KeyEvent.class.getFields();
            for (final Field f: fields) {
                if (f.getName().startsWith("VK")) {
                    try {
                        myKeyNames.add(KeyEvent.getKeyText(f.getInt(null)));
                        myKeyValueList.put(KeyEvent.getKeyText(f.getInt(null)),
                                               f.getInt(null));
                        final String ignoreSting = "RIGHT";
                        //Stop loading when it passes right arrow key.
                        if (f.getName().equalsIgnoreCase(ignoreSting)) {
                            break;
                        }
                    } catch (final IllegalArgumentException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }           
            }   
        }
        
        /**
         *  Accessor method for getting the map of keyboard values.
         * @return Map with keyboard keys' names and values.
         */
        public Map<String, Integer> getKeyValues() {
            return (HashMap<String, Integer>) myKeyValueList;

        }
      
        /**
         * Accessor method for getting keyboard keys' names.
         * @return List of String names of keyboard keys
         */
        public List<Choice> getChoices() {
            return (ArrayList<Choice>) myChoices;   
        }
    }  
}
