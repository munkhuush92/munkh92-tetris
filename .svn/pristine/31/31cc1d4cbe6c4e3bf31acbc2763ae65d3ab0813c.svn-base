/*
 * TCSS 305 - Project Tetris
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Observable;

import javax.sound.sampled.LineUnavailableException;
import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;
import javax.swing.Timer;

import model.Board;
import sound.TetrisMusic;

/**
 *  Main class for GUI.
 * @author Munkhbayar Ganbold
 * @version Autumn 2015 TCSS 305
 */
public class GUIMain extends Observable implements PropertyChangeListener {
    /** A default width size of tetris game board.  */
    private static final int DEFAUL_WIDTH_BOARD = 10;
    
    /** A default height size of tetris game board.  */
    private static final int DEFAUL_HEIGHT_BOARD = 20;
    
    /** A default message for end of the game.  */
    private static final String GAME_OVER_MESSAGE = "Game Over";
    
    /** A Constant String value for small size. */
    private static final String SMALL_GRID = "Small";
    
    /** A Constant String value for medium size. */
    private static final String MEDIUM_GRID = "Medium";
    
    /** A Constant String value for small size. */
    private static final String LARGE_GRID = "Large";
    
    /** A Constant String value for level. */
    private static final String LEVEL = "Level ";
    
    /** A default level-up delay value for timer. Every time the player enters the new level
     * timer delay decreases by this value.
     */
    private static final int LEVELUP_DELAY_VALUE = 200;
    
    /** A default delay value for starting the timer. */
    private static final int DEFAULT_DELAY_VALUE = 1000;
    
    /** A ToolKit. */
    private static final Toolkit KIT = Toolkit.getDefaultToolkit();
    
    /** The Dimension of the screen. */
    private static final Dimension SCREEN_SIZE = KIT.getScreenSize();

    /** The location of mario logo in the project.  */
    private static final String MARIO_LOGO = "backgroundpics/logo.png";

    /** A Timer for running tetris game.    */
    private Timer myTimer;
    
    /** A Main frame of the tetris. */
    private final JFrame myFrame;
    
    /** A Game panel.   */
    private final GamePanel myGamePanel;
    
    /** Tetris's side panel that has score board and next piece display.    */
    private final JPanel mySidePanel;
    
    /** Main board that we use to create the pieces.    */
    private final Board myBoard;

    /** A Controller for tetris.    */
    private final GUIController myKeyController;
    
    /** A Panel that displays the next piece for tetris.    */
    private NextPiecePanel myNextPiecePanel;

    /** A Panel that displays the help instruction of keyboard keys.    */
    private HelpPanel myHelpPanel;
    
    /** Boolean value that keeps track of sound is on and off.  */
    private boolean mySoundOnOff;
    
    /** A Label that shows level state. */
    private JLabel myLevelState;
    
    /** A Score panel that has score and cleared lines. */
    private ScorePanel myScorePanel;
    
    /** A Boolean value that keeps track of whether game is over or not.    */
    private boolean myIsGameInProgress;

    /** A menu item for starting a new game.    */
    private JMenuItem myNewGameMenuItem;

    /** A menu item for ending the current game.    */
    private JMenuItem myEndGameMenuItem;
   
    /** 
     * Constructs the GUI of tetris. Initializes the instance variables of tetris.
     */
    public GUIMain() {
        super();
        myFrame = new JFrame("My Tetris");
        myBoard = new Board(DEFAUL_WIDTH_BOARD, DEFAUL_HEIGHT_BOARD, null);
        mySidePanel = new JPanel();
        myGamePanel = new GamePanel(myBoard);
        myKeyController = new GUIController(myBoard);
        myFrame.addKeyListener(myKeyController);
        createAddNextPiece();
        buildMenus(); 
        initGUI(); 
    }
    
    /**
     *  Builds the menu items and their actions.
     */
    private void buildMenus() {
        final JMenuBar menuBar = new JMenuBar();
        final JMenu optionsMenu = new JMenu("Options");
        final JMenuItem customizeKeys = new JMenuItem("Customize keys...");
        customizeKeys.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                myKeyController.setVisibleFrame(true);      
            }   
        });
        final JMenuItem gridButton = new JMenuItem("Grid On/Off");
        gridButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvt) {
                myGamePanel.setGridOn(true);
                myGamePanel.repaint();
            }   
        });
        optionsMenu.add(gridButton);
        final JMenuItem pauseButton = new JMenuItem("Pause/Play");
        pauseButton.setMnemonic(KeyEvent.VK_P);
        pauseButton.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.getKeyText(KeyEvent.VK_P)));
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvt) {
                if (myTimer.isRunning()) {
                    myTimer.stop();
                    setChanged();
                    notifyObservers("Pause");
                } else {
                    myTimer.start();
                    setChanged();
                    notifyObservers("Play");
                }  
            }
        });
        final JMenuItem muteButton = new JMenuItem("Sound On/ Off");
        muteButton.setMnemonic(KeyEvent.VK_M);
        muteButton.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.getKeyText(KeyEvent.VK_M)));
        muteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvt) {
                if (mySoundOnOff) {
                    setChanged();
                    notifyObservers("Sound Off");
                    mySoundOnOff = false;
                } else {
                    mySoundOnOff = true;
                    setChanged();
                    notifyObservers("Sound On");
                }
            }
        });
        optionsMenu.add(muteButton);
        optionsMenu.add(pauseButton);
        //size choose
        final JMenu chooseSizeMenu = new JMenu("Screen size options");
        final ButtonGroup radioButtonGroup = new ButtonGroup();
        final JRadioButtonMenuItem small = new JRadioButtonMenuItem(SMALL_GRID);
        final JRadioButtonMenuItem medium = new JRadioButtonMenuItem(MEDIUM_GRID);
        final JRadioButtonMenuItem large = new JRadioButtonMenuItem(LARGE_GRID);
        
        final SizeAction sizeButtonAction = new SizeAction();
        small.addActionListener(sizeButtonAction);
        medium.addActionListener(sizeButtonAction);
        large.addActionListener(sizeButtonAction);
        
        radioButtonGroup.add(small);
        radioButtonGroup.add(medium);
        radioButtonGroup.add(large);
        small.setSelected(true);
        chooseSizeMenu.add(small);
        chooseSizeMenu.add(medium);
        chooseSizeMenu.add(large);
        //add setting that start new game and end game.
        optionsMenu.add(addStartGameMenu());
        optionsMenu.add(addEndGameMenu());
        optionsMenu.add(chooseSizeMenu);
        optionsMenu.add(customizeKeys);
        menuBar.add(optionsMenu);  
        menuBar.add(helpScoreMenu());
        myFrame.setJMenuBar(menuBar);
    }

    /**
     *  Creates a menu that has a help menu item and about menu item.
     * @return JMenu
     */
    private JMenu helpScoreMenu() {
        final JMenu helpMenu = new JMenu("Help");
        final JMenuItem aboutMenuItem = new JMenuItem("About");
        aboutMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvt) {
                JOptionPane.showMessageDialog(myFrame, 
                                "Programmed by Munkhbayar Ganbold \n Tetris Winter 2015",
                                null, 0, new ImageIcon(MARIO_LOGO));   
            }
        });
        final JMenuItem helpScoreMenuItem = new JMenuItem("Instruction for Game Score");
        helpScoreMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvt) {
                JOptionPane.showMessageDialog(myFrame, 
                           "Level goes up by one every time the player clears the 5 lines"
                           + "\n Every time the game levels up, FPS decreases by 200", null
                           , 0 , new ImageIcon(MARIO_LOGO));   
            }
            
        });
        
        helpMenu.add(aboutMenuItem);
        helpMenu.add(helpScoreMenuItem);
        return helpMenu;
    }

    /**
     * Creates a menu item that ends the current game.
     * @return JMenuItem
     */
    private JMenuItem addEndGameMenu() {
        myEndGameMenuItem = new JMenuItem("End Game");
        myEndGameMenuItem.setEnabled(myIsGameInProgress);
        myEndGameMenuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent theEvt) {
                myIsGameInProgress = false;
                myTimer.stop();
                setChanged();
                notifyObservers(GAME_OVER_MESSAGE);
                JOptionPane.showMessageDialog(myFrame, GAME_OVER_MESSAGE);
                
                myBoard.deleteObservers();
                myEndGameMenuItem.setEnabled(myIsGameInProgress);
                myNewGameMenuItem.setEnabled(!myIsGameInProgress);
            } 
        });
        return myEndGameMenuItem;
    }
    
    /**
     * Creates a menu item that start a new game.
     * resets the board state to its original.
     * @return JMenuItem
     */
    private JMenuItem addStartGameMenu() {
        myNewGameMenuItem = new JMenuItem("New Game");
        myNewGameMenuItem.setEnabled(!myIsGameInProgress);
        myNewGameMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvt) {
                myIsGameInProgress = true;
                mySoundOnOff = true;
                setChanged();
                notifyObservers("Open the sounds");
                myBoard.addObserver(myGamePanel);
                myBoard.addObserver(myNextPiecePanel);
                myBoard.newGame(DEFAUL_WIDTH_BOARD, DEFAUL_HEIGHT_BOARD, null);
                myTimer.start();
                myGamePanel.resetLines();
                myScorePanel.reset();
                myLevelState.setText("Level 0");
                myTimer.setDelay(DEFAULT_DELAY_VALUE);
                myNewGameMenuItem.setEnabled(!myIsGameInProgress);
                myEndGameMenuItem.setEnabled(myIsGameInProgress);
                
            }  
        });
        return myNewGameMenuItem;
    }

    /**
     * Creates the next piece display panel and help panel.
     * Also, it adds the next piece observer to the board observerable.
     */
    private void createAddNextPiece() {
        mySidePanel.setLayout(new BoxLayout(mySidePanel, BoxLayout.PAGE_AXIS));
        myNextPiecePanel = new NextPiecePanel(myBoard);
        myHelpPanel = new HelpPanel(myKeyController);
        myKeyController.addPropertyChangeListener(myHelpPanel);
        //level JLabel 
        myLevelState = new JLabel();

        //add score panel
        myScorePanel = new ScorePanel(myGamePanel);
        myScorePanel.add(myLevelState);
        myScorePanel.repaint();
        myGamePanel.addPropertyChangeListener(myScorePanel);
        myGamePanel.addPropertyChangeListener(this);
        myHelpPanel.add(myScorePanel);
        mySidePanel.add(myNextPiecePanel);
        mySidePanel.add(myHelpPanel);  
    }

    /**
     * Initializes the GUI of tetris.
     */
    private void initGUI() {
        
        //add music
        TetrisMusic tetisMusic;
        try {
            tetisMusic = new TetrisMusic();
            myBoard.addObserver(tetisMusic);
            this.addObserver(tetisMusic);
            this.addObserver(myGamePanel);
            myKeyController.addPropertyChangeListener(tetisMusic);
        } catch (final LineUnavailableException exp) {
            exp.printStackTrace();
        }
       
        //local variables
        myTimer = new Timer(DEFAULT_DELAY_VALUE, new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                myBoard.step();
                
                if (myTimer.isRunning() && (myBoard.isGameOver())) {
                        myIsGameInProgress = false;
                        setChanged();
                        notifyObservers(GAME_OVER_MESSAGE);
                        JOptionPane.showMessageDialog(myFrame, GAME_OVER_MESSAGE);
                        myTimer.stop();
                        myEndGameMenuItem.setEnabled(myIsGameInProgress);
                        myNewGameMenuItem.setEnabled(!myIsGameInProgress);
                    }
                }     
            
           
        });
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // add the observer (game panel) to the board.
        mySidePanel.setBackground(Color.WHITE);
        myFrame.setLayout(new BorderLayout());
        myFrame.add(myGamePanel, BorderLayout.WEST);
        myFrame.add(mySidePanel, BorderLayout.EAST);

    }
    /**
     * Helper method for frame to become visible to user.
     */
    public void display() {
        myFrame.setResizable(false);
        myFrame.pack();
        myFrame.setLocation(SCREEN_SIZE.width / 2 - myFrame.getWidth() / 2,
                            SCREEN_SIZE.height / 2 - myFrame.getHeight() / 2);
        myFrame.setVisible(true);
        
    }
    
    @Override
    public void propertyChange(final PropertyChangeEvent theEvt) {
        //local variable
        String levelState = "";
        
        if ("lines are cleared".equals(theEvt.getPropertyName())) {
            
            final int clearedLines = (Integer) theEvt.getNewValue();
            final int levelDecider =  clearedLines / 5 + 1;
            levelState = LEVEL + levelDecider;
            myTimer.setDelay(DEFAULT_DELAY_VALUE - (levelDecider - 1) * LEVELUP_DELAY_VALUE);
            setChanged();
            notifyObservers(levelDecider);
        }
        
        myLevelState.setText(levelState);
        
    }
    /**
     *  Main method to runs the GUI and starts the game.
     * @param theArgs Null
     */
    public static void main(final String [] theArgs) {   
        
        final GUIMain main = new GUIMain();
        main.display();
    }
    
    /**
     *  Inner Class which handles radion buttons' actions.
     * @author Munkhbayar Ganbold
     * @version Autumn 2015
     */
    class SizeAction extends AbstractAction {

        /** A default generated serial code.    */
        private static final long serialVersionUID = 1L;

        @Override
        public void actionPerformed(final ActionEvent theEvent) {
            if (theEvent.getActionCommand().equals(SMALL_GRID)) {
                myGamePanel.setGridSize(SMALL_GRID);
                myNextPiecePanel.setGridSize(SMALL_GRID);
            } else if (theEvent.getActionCommand().equals(MEDIUM_GRID)) {
                myGamePanel.setGridSize(MEDIUM_GRID);
                myNextPiecePanel.setGridSize(MEDIUM_GRID);
            } else if (theEvent.getActionCommand().equals(LARGE_GRID)) {
                myGamePanel.setGridSize(LARGE_GRID);
                myNextPiecePanel.setGridSize(LARGE_GRID);
            }
            
            mySidePanel.setSize(new Dimension(myNextPiecePanel.getWidth(), 
                  myGamePanel.getHeight()));
            myHelpPanel.setPreferredSize(myHelpPanel.getPreferredSize());
            myHelpPanel.setPreferredSize(myHelpPanel.getPreferredSize());
            myGamePanel.repaint();
            myNextPiecePanel.repaint();
            myFrame.pack();
            
        }   
    }

   

}
