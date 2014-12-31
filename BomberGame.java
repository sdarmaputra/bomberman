package BomberButtiClient;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.*;
/**
* The BomberMan class contains all of the game itself. From the map and the players are loaded here
*/
public class BomberGame extends JPanel implements ActionListener {
    BomberMap map; 
    ArrayList<BomberPlayer> players = new ArrayList<>(); //Collection of different players
    BomberImages images; //An object of the Bomber Images classes, which contains all the images for the game
    BomberPlayer winner; //An object of Bomber Player to the player who has won the game to load in
    Timer timer; //The timer that creates the pulses for the map and players
    int totalPlayers; //Total players
    boolean gameOver; //Boolean variable to set the status of the game
    private int idCounter; //A counter for each player to be able to assign a unique ID
    
    /**
     * Default constructor
     */
    public BomberGame() {
        idCounter = 0;
        images = new BomberImages();
        timer = new Timer(150, this);
    }
    
    /**
     * Function to load the game
     */
    public void loadGame() {
        this.map = new BomberMap(this); //Create new folder object
        this.gameOver = false; //not gameover
        initPlayers(); //players load
    }
    
    /**
     * Function to start with the game
     */
    public void startGame() {
        timer.stop(); //The timer allows to stop the pulses if it was already running
        loadGame(); //The game (re) load
        timer.start(); //Start the timer
        
    }
    
    /**
     * Function to load with the players
     */
    public void initPlayers() {
        players.removeAll(players); //Possibly remove players charged back
        players.add(new BomberPlayer(this,map,1,1,1,"Player 1")); //Add one player at coordinate (1,1)
        players.add(new BomberPlayer(this,map,2,27,27,"Player 2")); //Player 2 Add to coordinate (27,27)
        //For all players load the corresponding control keys
        for (BomberPlayer i : players) {
            i.loadKeys();
        }
    }
    
    /**
     * Function to create a player
     * @param name: Player name
     * @return it is that the player has been awarded
     */
    public int createPlayer(String name) {
        players.add(new BomberPlayer(this,map,++idCounter,1,1,name));
        return idCounter;
    }
    
    /**
     * Get function to query the number of players
     * @return players.size()
     */
    public int getAmountOfPlayers() {
        return players.size();
    }
    
    /**
     * Function to get the object of the class Bomber Images (containing images) to request
     * @return images
     */
    public BomberImages getImages() {
        return images;
    }
    
    /**
    * Is performed on each pulse of the timer
    * @param e
    */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            map.act(); //Giving a pulse map
            for (BomberPlayer i : players) {
                i.act(); //Give players a pulse
            }
            repaint(); //Redraw the screen
        }
    }
    
    /**
    * Is performed when a key is pressed
    * @param evt
    */
    public void keyPressed(KeyEvent evt) {
        for (BomberPlayer i : players) {
            i.keyPressed(evt); //Forward key information to each player
        }
    }
    
    /**
    * Is executed when a key is released
    * @param evt
    */
    public void keyReleased(KeyEvent evt) {
        for (BomberPlayer i : players) {
            i.keyReleased(evt); //Forward key information to each player
        }
    }
    
    /**
    * Can be called to end the game
    */
    public void endGame() {
        this.gameOver = true;
        for (Iterator i = players.listIterator(); i.hasNext();) {
            BomberPlayer player = (BomberPlayer) i.next(); 
                if (!player.getIsDead()) {
                this.winner = player;
            }
        }
    }
    
    /**
    * Writing here happens to the screen
    * @param g
    */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        map.draw(g);
        for (BomberPlayer i : players) {
            i.draw(g);
        }
        
        if (gameOver) {
            g.drawString("Game Over! The Winner is: " +winner.getName(), 20, 605);
        }
    }
}
