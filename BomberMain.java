package BomberButtiClient;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

/**
* Main Class

*/
public class BomberMain extends JFrame {
    private BomberGame game; //Object of class BomberGame
    
    /**
     * Default constructor
     */
    public BomberMain() {
        initUI(); //Loading the User Interface
        
        
        /**
         * Creating the start button
         */
        JButton b = new JButton("Start the game!");
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                BomberMain.this.startGame();
            } 
        });
        add(b);
        
        
        setSize(400, 400);
        setResizable(false);
        setTitle("BomberButti");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        show(); //Make everything visible
    }
    
    /**
     * Functie om het spel te start
     */
    public void startGame() {
        hide(); //Hide all
        getContentPane().removeAll();
        game = new BomberGame();
        add(game);
        addKeyListener(new KeyAdapter() {
            /**
            * Invoked when a button is pressed
             *param Possibly keyboard event
            */
            @Override
            public void keyPressed(KeyEvent evt) {
                if (game != null) {
                    game.keyPressed(evt);
                }
            }
            /**
            * Invoked when a key is released
             *param Possibly keyboard event
            */
            @Override
            public void keyReleased(KeyEvent evt) {
                if (game != null) {
                    game.keyReleased(evt);
                }
            }
        });
        game.startGame();
        show();
    }
    
    /**
     * Function to initialize the User Interface
     */
    private void initUI() {
        initMenubar(); //menubar load
    }
    
    /**
     * Function to initialize the menubar
     */
    private void initMenubar() {
        /* Declare and initialize menus */
        JMenuBar menubar = new JMenuBar(); //Create menubar
        JMenu file = new JMenu("File"); //Add new menu
        JMenu help = new JMenu("Help"); //Add help menu
        
        /* Declare and initialize menu item's */
        JMenuItem start = new JMenuItem("Start the Game" );
        JMenuItem exit = new JMenuItem("Exit");
        JMenuItem manual = new JMenuItem("User Manual");
        
        /* Tooltips */
        start.setToolTipText("Start the game");
        exit.setToolTipText("Exit");
        manual.setToolTipText("Open the game manual");
        
        /* Event handling */
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                game.startGame();
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                System.exit(1);
            }
        });
        manual.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //Open User Manual
                Desktop dt = Desktop.getDesktop();
                File f = new File("BomberButti_Manual.chm");
                try {
                    dt.open(f);
                }
                catch(IOException e) {
                    
                }
            }
        });
        
        file.add(start);
        file.add(exit);
        help.add(manual);
        
        menubar.add(file);
        menubar.add(help);
        
        setJMenuBar(menubar);
    }
    
    
    
    /**
    * @param args the command line arguments
    */
    public static void main(String[] args) {
        // TODO code application logic here
        BomberMain main = new BomberMain();
    }
}
