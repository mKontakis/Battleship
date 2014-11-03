/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oop_project1_ver3;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;

import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Placement extends JFrame implements ActionListener, MouseListener, ChangeListener {

    private JPanel placementPanel;
    private JFrame gameEngineFrame;
    private JButton confirmButton = new JButton("Confirm");
    private JButton[][] myBoard;
    private JButton[] shipButton = new JButton[5]; //Array of buttons for the ships
    private Ship[] boardShips = new Ship[5]; //Array of ships objects to save ships
    private Ship shipSelected;
    private Ship highlightedShip;
    private int validPoints;
    private int pointsOnBoard;
    private List shipsPlaced = new ArrayList();
    private int highlightedShipArrayPosition;
    Cursor normalCursor = new Cursor(0);
    public Battleship gettingName = new Battleship();
    //public boolean isVisible = true;
    // public JLabel player;
    // public SelectionFrame pName;
    // private Engine en;
    //private JTextField qq;
    //  private Ship shipSelected;
    //  private Ship highlightedShip;
    private ImageIcon bad = new ImageIcon("bad.gif");
    private ImageIcon delImgIcon = new ImageIcon("DeleteIcon.gif");
    private ImageIcon sea = new ImageIcon("sea.gif");
    private Image delImg = this.delImgIcon.getImage();
    private Cursor delCursor = Toolkit.getDefaultToolkit().createCustomCursor(this.delImg, new Point(16, 16), "Delete Cursor");
    //String for keeping the player's name
    public String playerName = "Player's";

    public Placement() {
        System.out.println("TEST PLACE");
//        qq = new JTextField(20);
//        qq.add(this);
//        qq.setLocation(200, 200);
//        qq.setSize(30, 30);
        System.out.println("TEST PLACE222");

        this.placementPanel = new JPanel(); //Creation of the JPanel
        this.placementPanel.setLayout(null);
        this.placementPanel.setBounds(0, 0, 800, 325); //Setting Width & Height
        this.placementPanel.setVisible(true);

        Insets margins = new Insets(0, 0, 0, 0);  //The insets object specifies the space that a container must leave at each of its edges.
        this.shipButton = new JButton[5]; //Creating the ship's buttons

        JPanel myBoard = new JPanel();
        myBoard.setLayout(new GridLayout(10, 10));
        myBoard.setBounds(10, 20, 300, 300); //Setting postiion of my board inside the JPanel and width & height
        this.placementPanel.add(myBoard); //Adding myBoard inside the placementPanel



        System.out.println("TEST get player name");
        //   String playerName = JOptionPane.showInputDialog(null, "Enter Your Name", "Enter Name", JOptionPane.QUESTION_MESSAGE);
        //System.out.println(playerName);

        playerName = gettingName.nameGet(); //Getting the player's name
        JLabel player = new JLabel(this.playerName + " Board"); //Setting the player's name 
        player.setVisible(true);
        player.setBounds(0, -5, 300, 30);
        player.setHorizontalAlignment(0);
        // System.out.println("LALALLALALA " + playerName);
        this.placementPanel.add(player);



        JPanel shipSelect = new JPanel();
        shipSelect.setLayout(new GridLayout(14, 1));
        shipSelect.setBackground(Color.getHSBColor(0.0F, 0.0F, 0.75F)); //Setting background color. Hue, saturation, brightness format. (HSB). Color found at google (web friendly colors).
        //Converted from RGB to HSB using an online converter. getHSBcolor syntax was found at oracle tutorials.
        shipSelect.setBounds(320, 20, 200, 300); //Setting postiion of the panel inside the JPanel and setting width & height
        this.placementPanel.add(shipSelect); //Adding the shipSelecte to placementPanel.

        JLabel selectShipLabel = new JLabel("Select Ships");
        selectShipLabel.setHorizontalAlignment(0); //alligns the text to the middle of the index.
        shipSelect.add(selectShipLabel);

        //Method for the creation of the ships.
        makeShips(shipSelect);

        //A blank label for spreading the indexes
        JLabel blankLabel1 = new JLabel();
        shipSelect.add(blankLabel1);

        //Rorate button
        JButton rotate = new JButton("Rotate");
        rotate.setName("Rotate");
        rotate.addActionListener(this);
        shipSelect.add(rotate); //Adds button to shipSelect panel

        // Random Ships Button.
        JButton random = new JButton("Random Ships");
        random.setName("Random");
        random.addActionListener(this);
        shipSelect.add(random);

        //A blank label for spreading the indexes
        JLabel blankLabel = new JLabel();
        shipSelect.add(blankLabel);

        //Reset button
        JButton reset = new JButton("Reset");
        reset.setName("Reset");
        reset.addActionListener(this);
        shipSelect.add(reset);

        //Confirm Button
        //Confirm Button action listener is added at Engine Class, New Game method
        this.confirmButton.setEnabled(false); //Gets enables as soon as we place all our ships
        this.confirmButton.setName("Confirm");
        shipSelect.add(this.confirmButton);

        //Making player's board buttons
        this.myBoard = new JButton[10][10];

        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                this.myBoard[x][y] = new JButton();
                this.myBoard[x][y].setMargin(margins); //Sets space for margin between the button's border and the label
                this.myBoard[x][y].setToolTipText(x + "," + y); //Cordinates info on hover
                this.myBoard[x][y].setName(x + "," + y); //Names of buttons. X,Y format.(
                this.myBoard[x][y].setIcon(this.sea); // Setting background icon
                // this.myBoard[x][y].setBackground(Color.red);
                this.myBoard[x][y].addChangeListener(this); //Listeners
                this.myBoard[x][y].addMouseListener(this); //Listeners
                this.myBoard[x][y].setHorizontalAlignment(0);
                myBoard.add(this.myBoard[x][y]); //Adding the board to the panel.
                // System.out.println("MYBOARD DEBUG. NAME:  " + this.myBoard[x][y].getName());

            }
        }

        //Instructions Panel
        JPanel instructionPanel = new JPanel();
        instructionPanel.setLayout(new GridLayout(14, 1));
        instructionPanel.setBounds(530, 20, 115, 300);
        instructionPanel.setBackground(Color.getHSBColor(0.0F, 0.0F, 0.75F)); //Setting background color. Hue, saturation, brightness format. (HSB). Color found at google (web friendly colors).
        //Converted from RGB to HSB using an online converter. getHSBcolor syntax was found at oracle tutorials.
        //Links: http://www.w3schools.com/html/html_colors.asp , http://www.workwithcolor.com/color-converter-01.htm?cp=BFBFBF&ch=0-0-75&cb=BFBFBF,FFFFFF,FFFFFF,FFFFFF,FFFFFF,FFFFFF,FFFFFF,FFFFFF,FFFFFF,FFFFFF
        this.placementPanel.add(instructionPanel);

        JLabel insLabel = new JLabel("Instructions");
        insLabel.setHorizontalAlignment(0);
        instructionPanel.add(insLabel);
        JLabel blankLabel2 = new JLabel();
        instructionPanel.add(blankLabel2);

        //Instructions !!
        JLabel ins1 = new JLabel("Click a ship from");
        JLabel ins2 = new JLabel("the panel to the");
        JLabel ins3 = new JLabel("right. Then drag");
        JLabel ins4 = new JLabel("your ship on the");
        JLabel ins5 = new JLabel("grid to the left.");
        JLabel ins6 = new JLabel("To rotate a ship,");
        JLabel ins7 = new JLabel("right click. To");
        JLabel ins8 = new JLabel("delete ship click");
        JLabel ins9 = new JLabel("the ship on grid.");
        JLabel ins10 = new JLabel("When done click");
        JLabel ins11 = new JLabel("the confirm button.");

        //Center Alligment
        ins1.setHorizontalAlignment(0);
        ins2.setHorizontalAlignment(0);
        ins3.setHorizontalAlignment(0);
        ins4.setHorizontalAlignment(0);
        ins5.setHorizontalAlignment(0);
        ins6.setHorizontalAlignment(0);
        ins7.setHorizontalAlignment(0);
        ins8.setHorizontalAlignment(0);
        ins9.setHorizontalAlignment(0);
        ins10.setHorizontalAlignment(0);
        ins11.setHorizontalAlignment(0);

        //Sentences integration to panel
        instructionPanel.add(ins1);
        instructionPanel.add(ins2);
        instructionPanel.add(ins3);
        instructionPanel.add(ins4);
        instructionPanel.add(ins5);
        instructionPanel.add(ins6);
        instructionPanel.add(ins7);
        instructionPanel.add(ins8);
        instructionPanel.add(ins9);
        instructionPanel.add(ins10);
        instructionPanel.add(ins11);


    }

    //Getters
    public JPanel getPlacementPanel() {
        return this.placementPanel;
    }

    public JButton getConfirmButton() {
        return this.confirmButton;
    }

    //Making the Ships. Buttons and objects.
    private void makeShips(JPanel inPanel) {
        System.out.println("make ships testing");
        this.shipButton[0] = new JButton("Aircraft Carrier");
        boardShips[0] = new Ship(5, "Aircraft Carrier", 0);

        this.shipButton[1] = new JButton("Battleship");
        boardShips[1] = new Ship(4, "Battleship", 1);

        this.shipButton[2] = new JButton("Cruiser");
        boardShips[2] = new Ship(3, "Cruiser", 2);

        this.shipButton[3] = new JButton("Destroyer");
        boardShips[3] = new Ship(2, "Destroyer", 3);


        this.shipButton[4] = new JButton("Submarine");
        boardShips[4] = new Ship(1, "Submarine", 4);

        //  System.out.println("make ships testing22");
        //   System.out.println(shipButton[0]);
        for (int i = 0; i < 5; i++) {
            this.shipButton[i].setName("" + i);
            //  System.out.println("SETTING NAME AT SHIP'S BUTTONS:    " + i);
            this.shipButton[i].addActionListener(this);
            inPanel.add(this.shipButton[i]);
            this.boardShips[i].makeIcons();
            //  System.out.println("NAME OF EACH SHIP" + shipButton[i].getName());
        }
        // System.out.println("MAKING OF SHIPS DONE");
    }

    private boolean getShipCoords() {
        System.out.println("INSIDE GET SHIP COORDS");
        this.validPoints = 0;
        this.pointsOnBoard = 0;


        int size = this.shipSelected.getSize(); //Gets tje size of the Ship from class Ship
        int orientation = this.shipSelected.getOrientation(); //Gets the orientation of the ship
        //Gets coords
        int x = this.shipSelected.getX();
        int y = this.shipSelected.getY();

        int[][] shipCoords = this.shipSelected.getCoords(); //Gets the cordinates of the selected ship

        for (int i = 0; i < size; i++) {
            if (orientation == 0) {
                if ((x + i >= 0) && (x + i < 10)) {
                    if (this.myBoard[(x + i)][y].getIcon().equals(this.sea)) {
                        this.validPoints += 1;
                    }
                    shipCoords[this.pointsOnBoard][0] = (x + i);
                    shipCoords[this.pointsOnBoard][1] = y;
                    this.pointsOnBoard += 1;

                    System.out.println("VALID POINTS: " + validPoints);
                    System.out.println("POINTS ON BOARD: " + pointsOnBoard);
                }

            } else if ((y + i >= 0) && (y + i < 10)) {
                if (this.myBoard[x][(y + i)].getIcon().equals(this.sea)) {
                    this.validPoints += 1;
                }
                shipCoords[this.pointsOnBoard][0] = x;
                shipCoords[this.pointsOnBoard][1] = (y + i);
                this.pointsOnBoard += 1;
            }

        }

        this.shipSelected.setCoords(shipCoords);
        boolean valid;
        //   boolean valid;
        if (this.validPoints < size) {
            valid = false;
        } else {
            valid = true;
        }
        return valid;
    }

    //If a ships is deleted, this methid clears(deletes) the old coordinates.
    private void clearOldCoords() {
        // int size = this.shipSelected.getSize();
        int[][] shipCoords = this.shipSelected.getCoords();

        Ship tempShip = this.shipSelected;

        for (int i = 0; i < this.pointsOnBoard; i++) {
            int x = shipCoords[i][0];
            int y = shipCoords[i][1];
            this.myBoard[x][y].setIcon(this.sea);
        }

        for (int i = 0; i < this.shipsPlaced.size(); i++) {
            this.shipSelected = ((Ship) this.shipsPlaced.get(i));
            paintShip(true);
        }

        this.shipSelected = tempShip;
    }

    private void paintShip(boolean valid) {
        int[][] shipCoords = this.shipSelected.getCoords();
        ImageIcon[][] shipIcons = this.shipSelected.getShipIcons();
        int orientation = this.shipSelected.getOrientation();

        if (valid) {
            this.pointsOnBoard = this.shipSelected.getSize();
            System.out.println("POINTS ON BOARD AT METHOD PAINT SHIPS: " + pointsOnBoard);
        }
        for (int i = 0; i < this.pointsOnBoard; i++) {
            int x = shipCoords[i][0];
            int y = shipCoords[i][1];
            if (valid) {
                this.myBoard[x][y].setIcon(shipIcons[orientation][i]);
                System.out.println("When this message appears, ships get paint at player's board.");
            } else {
                this.myBoard[x][y].setIcon(this.bad);
            }
        }
    }

    //This method, actually, places the ships onboard.
    private void placeShip() {
        this.validPoints = 0;
        this.pointsOnBoard = 0;
        this.shipButton[this.shipSelected.getButton()].setEnabled(false); //the ship's button that is placed, is now disabled.
        this.shipSelected.setCoords(this.shipSelected.getCoords()); //Keeps the coords of the ship.
        this.shipsPlaced.add(this.shipSelected);
        this.shipSelected = null;
        confirmBoard(); //Checks if the board is ready(all ships are placed)
    }

    //Random place ships. This method is used by Computer to randomly place the ships.
    public void randomPlaceShips() {
        Random generator = new Random();
        System.out.println("RANDOM PLACE SHIPS");
        if (this.confirmButton.isEnabled()) {
            resetBoard();
        }

        for (int i = 0; i < 5; i++) {
            if (this.shipButton[i].isEnabled()) { //Checks which ship is clicked.
                this.shipSelected = this.boardShips[i];
                boolean valid;
                do {
                    int x = generator.nextInt(10); //Generates random number for X cord.
                    int y = generator.nextInt(10); //Generates random number for Y cord.
                    boolean orientation = generator.nextBoolean(); //Generates random True or False. True for Horizontal, false for vertical.

                    this.shipSelected.setPos(x, y);
                    if (orientation) {
                        this.shipSelected.rotateShip();
                    }
                    valid = getShipCoords();
                } while (!valid);
                paintShip(valid);
                placeShip();

            }
        }
    }

    private void resetBoard() {
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                this.myBoard[x][y].setIcon(this.sea);
            }
        }
        for (int i = 0; i < 5; i++) {
            this.shipButton[i].setEnabled(true);
        }
        this.shipsPlaced.removeAll(this.shipsPlaced);
        this.confirmButton.setEnabled(false);
    }

    private void rotateShip() {
        if (this.shipSelected == null) {
            return;
        }
        this.shipSelected.rotateShip();
    }

    //This method generated an array that keeps coords from all the ships.
    public int[][] getAllShipCoords() {
        //5x3 Array. xCoord, yCoord, Orientation
        int[][] allShipCoords = new int[5][3];

        for (int i = 0; i < 5; i++) {
            allShipCoords[i][0] = this.boardShips[i].getX();
            allShipCoords[i][1] = this.boardShips[i].getY();
            allShipCoords[i][2] = this.boardShips[i].getOrientation();
        }

        return allShipCoords;
    }

    //Method to check if all ships are placed.
    private void confirmBoard() {
        for (int i = 0; i < 5; i++) {
            if (this.shipButton[i].isEnabled()) {
                return;
            }
        }
        this.confirmButton.setEnabled(true);
    }

    public void setFrame(JFrame inFrame) {
        this.gameEngineFrame = inFrame;
    }

    private void checkDeletable(int x, int y) {
        System.out.println("CHECK DELETABLE ACTIVATED");
        this.highlightedShipArrayPosition = -1;

        if ((this.shipSelected == null) && (!this.myBoard[x][y].getIcon().equals(this.sea))) { //If no ship is selected and myBoard[x][y] is NOT equals to sea, then
            this.gameEngineFrame.setCursor(this.delCursor);
            boolean foundShip;
            do {
                this.highlightedShipArrayPosition = highlightedShipArrayPosition + 1;
                this.highlightedShip = ((Ship) this.shipsPlaced.get(this.highlightedShipArrayPosition));
                foundShip = this.highlightedShip.checkCoord(x, y);
            } while (!foundShip);
        } else {
            this.highlightedShip = null;
        }
    }

    private void deleteShip() {
        int shipButtonInt = this.highlightedShip.getButton();
        this.shipButton[shipButtonInt].setEnabled(true);
        this.shipsPlaced.remove(this.highlightedShipArrayPosition);

        this.shipSelected = this.highlightedShip;
        this.pointsOnBoard = this.shipSelected.getSize();
        clearOldCoords();
        this.shipSelected = null;
        this.pointsOnBoard = 0;
        this.confirmButton.setEnabled(false);
    }

    @Override
    public void stateChanged(ChangeEvent event) {

        JButton currentButton = (JButton) event.getSource();
        System.out.println("STATE CHANGED");
        System.out.println("CURRENT BUTTON VALUE: " + currentButton.getName());
        String Name = currentButton.getName();
        System.out.println("NAME VALUE: " + Name);
        int commaPos = Name.indexOf(",");
        System.out.println("COMMA POS: " + commaPos);
        int x = Integer.parseInt(Name.substring(0, commaPos));
        int y = Integer.parseInt(Name.substring(commaPos + 1));

        System.out.println("X VALUE: " + x);
        System.out.println("Y VALUE: " + y);
        System.out.println("");
        checkDeletable(x, y); //As we hover through the board, it checks if it spots a ship to delete it.

        if (currentButton.getName().equals("")) {
            return;
        }
        if (this.shipSelected == null) {
            return;
        }
        this.shipSelected.setPos(x, y);
        System.out.println("SHIP SELECTED: " + shipSelected.getName());
        System.out.println("");
        clearOldCoords();
        boolean valid = getShipCoords();
        paintShip(valid);

        if ((currentButton.isFocusOwner()) && (valid)) { //Returns true if this Component is the focus owner.
            placeShip();
        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        JButton currentButton = (JButton) event.getSource();
        String name = currentButton.getName();
        System.out.println("ACTION LISTENER " + name);
        switch (name) {
            case "Rotate":
                rotateShip();
                break;
            case "Reset":
                resetBoard();
                break;
            case "Random":
                //   System.out.println("RANDOM SHIPS\n");
                System.out.println("SHIPS SELECTED VALUE AT ACTION LISTENER " + shipSelected);
                if (this.shipSelected != null) { //If one or more ships are placed, and random button is pushed, this clears and coords.
                    clearOldCoords();
                    this.validPoints = 0;
                    this.pointsOnBoard = 0;
                }
                //  System.out.println("CALLING RANDOM PLACE SHIPS METHOD");
                randomPlaceShips();
                break;
            default:
                if (this.shipSelected != null) {
                    clearOldCoords();
                    this.validPoints = 0;
                    this.pointsOnBoard = 0;
                }
                System.out.println("NAME OF THE SHIPS THAT IS CLICKED AT PLACEMENT'S ACTION LISTENER " + name);
                int shipArrayPos = Integer.parseInt(name);
                this.shipSelected = this.boardShips[shipArrayPos];
                break;
        }
    }

    @Override
    //This method gets called when user left clicks ON the ship after he placed it, to delete it. (Button 2)
    //During the placement if user RIGHT clicks ship rotates. (Button 3)
    public void mousePressed(MouseEvent e) {
        if ((e.getButton() == 1) && (this.highlightedShip != null)) {
            deleteShip();
        }
        if (this.shipSelected == null) {
            return;
        }
        if (e.getButton() == 3) {
            rotateShip();
            clearOldCoords();
            boolean valid = getShipCoords();
            paintShip(valid);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.gameEngineFrame.setCursor(this.normalCursor);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }
}
