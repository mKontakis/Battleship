package oop_project1_ver3;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.*;

public class PlayBoard implements ActionListener {

    public static final int BOARD_SIZE = 10;
    public static final int MAX_SHIPS = 5;
    private JLabel status;
    private JButton[][] myBoard;
    private JButton[][] opponentBoard;
    private Ship[] opponentShips;
    private Ship[] myShips;// = new Ship[5];
    private JLabel myShipsRemainingLabel;
    private JLabel opponentShipsRemainingLabel;
    private int myShipsRemainingValue; //Starting Value for ships remaining
    private int opponentShipsRemainingValue; //Same as adove
    private JPanel panelPlayBoard;
    private JFrame gameEngineFrame;
    private Computer computer;
    private int myTurn = -1;
    private boolean gotOpponentShips = false;
  //  public static JButton[][] inBoard = new JButton[10][10];
    private ImageIcon sea = new ImageIcon("sea.gif");
    private ImageIcon fire = new ImageIcon("fire.gif");
    private ImageIcon miss = new ImageIcon("miss.gif");
  //  private Ship[] myShipsPos = new Ship[5];
    public Placement shipsPlacedBoard;
    int manos = 999;
    

    public PlayBoard() {
        this.panelPlayBoard = new JPanel();
        this.panelPlayBoard.setLayout(null); //Setting null layout
        this.panelPlayBoard.setVisible(true);
        this.panelPlayBoard.setBounds(0, 0, 650, 345); //Setting width and height of the JPanel
        
        
        JLabel myBoardLabel = new JLabel("Player's Board"); //Setting label for the players board
        myBoardLabel.setBounds(355, 3, 100, 15); //Setting position, width, height
        this.panelPlayBoard.add(myBoardLabel);

        JLabel myShipsRemainingLabel = new JLabel("Ships Remaining:"); //Label for player's ships remaining
        myShipsRemainingLabel.setBounds(510, 3, 100, 15); //Position & Size again for Remaining Ships label
        this.panelPlayBoard.add(myShipsRemainingLabel); //Adds the JLabel to the playBoard
        this.myShipsRemainingValue = 5; //Setting remaining ships equals to 5 (ships total number)
        this.myShipsRemainingLabel = new JLabel(String.valueOf(this.myShipsRemainingValue));
        this.myShipsRemainingLabel.setBounds(615, 3, 100, 15);
        this.panelPlayBoard.add(this.myShipsRemainingLabel); //Adds the Value of remaining ships to the JPanel

        //Same  job for opponent's board:
        JLabel opponentBoardLabel = new JLabel("Enemy Board");
        opponentBoardLabel.setBounds(25, 3, 100, 15);
        this.panelPlayBoard.add(opponentBoardLabel);

        //Same job for enemy's ships remaining Label:
        JLabel opponentShipsRemainingLabel = new JLabel("Ships Remaining");
        opponentShipsRemainingLabel.setBounds(180, 3, 100, 15);
        this.panelPlayBoard.add(opponentShipsRemainingLabel);
        this.opponentShipsRemainingValue = 5;
        this.opponentShipsRemainingLabel = new JLabel(String.valueOf(this.opponentShipsRemainingValue));
        this.opponentShipsRemainingLabel.setBounds(285, 3, 100, 15);
        this.panelPlayBoard.add(this.opponentShipsRemainingLabel);

        //Setting up JLabel for game status information
        this.status = new JLabel("Status: ");
        this.status.setBounds(15, 323, 400, 15);
        this.panelPlayBoard.add(status);
        

        //Setting Up The Boards
        this.myBoard = new JButton[10][10]; //player's board
        this.opponentBoard = new JButton[10][10]; //Enemy's board

        this.myShips = new Ship[5];
        this.opponentShips = new Ship[5];

        makeBoard(this.opponentBoard, true, 10, 20);
        makeBoard(this.myBoard, false, 340, 20);



    }

    //Getter for PanelPlayBoard;
    public JPanel getPanelPlayBoard() {
        return this.panelPlayBoard;
    }

    public void setOpponentShips(int[][] opponentShipPosition) {
        if (this.myTurn == -1) {
            this.myTurn = 0;
          //  this.status.setText("Status: No need to set status because it is never visible, because computer move is instant and we cant see it");
        } else if (this.myTurn == 1) {
            this.status.setText("Status: Make a move!!");

        }
        System.out.println("OPPONENT SHIP POSITION AT SET OPPONENT SHIP: \n");
        for (int[] x : opponentShipPosition) {
                System.out.println("");
                System.out.println(Arrays.toString(x));
            }
        
        this.gotOpponentShips = true;
        
        System.out.println("GOT OPPONENT SHIPS? : " + String.valueOf(gotOpponentShips));
        makeShips(opponentShips, opponentShipPosition);
        this.opponentShipsRemainingLabel.setText(String.valueOf(this.opponentShipsRemainingValue));
    }
    
    //This method is getting called from Engine class, getMyShip method.
    public void setMyShips(int[][] myShipPos) {

        if (this.myTurn == -1) {
            this.myTurn = 1;
            this.status.setText("hkljhjkgvjg");
        }
        System.out.println("");
        System.out.println("setMyShips");
        for (int[] arr : myShipPos) {

            System.out.println(Arrays.toString(arr));
        }
       // System.out.println("MY BOARD\n");
        System.out.println("");
//        for(JButton[] arr : myBoard) {
//            
//                System.out.println(Arrays.toString(arr));
//            }
      //  System.out.println("MY SHIPS\n");
//         for(Ship arr : myShips) {
//            
//                System.out.println(arr);
//            }
        makeShips(this.myShips, myShipPos);
        paintShips(this.myBoard, this.myShips);


    }
    
     public void setPlayer(Computer inComputer) {
        this.computer = inComputer;
    }

   

    public void setFrame(JFrame inFrame) {
        this.gameEngineFrame = inFrame;
    }
    
        //Method for ship making, with parameters an array of ships, for temporary saving of the ships, and an array of cordinates(int), for starting position of the ships.
    private void makeShips(Ship[] temporaryShips, int[][] startingPosition) {
        //Temporary saving ships. Overloading constructor Ships(parA, parB) with 2 parameters from class Ship.
        
            System.out.println("MAKING SHIPS AT PLAYBOARD: \n");
          for (int[] arr : startingPosition) {

            System.out.println(Arrays.toString(arr));
        }
        
        //Making ship objects
        temporaryShips[0] = new Ship(5, "Aircraft Carrier");
        temporaryShips[1] = new Ship(4, "Battleship");
        temporaryShips[2] = new Ship(3, "Cruiser");
        temporaryShips[3] = new Ship(2, "Destroyer");
        temporaryShips[4] = new Ship(1, "Submarine");
        
        for (int i = 0; i < 5; i++) {
            int x = startingPosition[i][0];
            int y = startingPosition[i][1];
            
            //
            int orientation = startingPosition[i][2];
            System.out.println("$#############################################################################################################################");
            System.out.println("STARTING POSITION ");
            System.out.println("XXXXXXXXXXXXXXXXXX      " + x);
            System.out.println("YYYYYYYYYYYYYYYYYY      \n\n\n" + y);
            
            temporaryShips[i].setPos(x, y);

            //If ship is vertical then:
            if (orientation > 0) 
                temporaryShips[i].rotateShip(); //Rotates
            
                temporaryShips[i].makeCoords(); //Makes new coords
                temporaryShips[i].makeIcons(); //Makes new icon
            //} else{
            //    temporaryShips[i].rotateShip(); //Rotates
            //   temporaryShips[i].makeCoords(); //Makes new coords
            //    temporaryShips[i].makeIcons(); //Makes new icon
            
        }
        
        System.out.println("METAVLITI MANOS: " + manos);
        manos++;

    }

    //the method for the making of the boards
    public void makeBoard(JButton[][] inBoard, boolean includeListeners, int posX, int posY) {
        
        /* Configuration of the board */
        JPanel newBoard = new JPanel();
        newBoard = new JPanel();
        newBoard.setLayout(new GridLayout(10, 10));
        newBoard.setVisible(true);
        newBoard.setBounds(posX, posY, 300, 300);

        Insets margins = new Insets(0, 0, 0, 0); //Specifying the space between the containers must leave between of its edges

        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                inBoard[x][y] = new JButton(); //Each of the index transforms into a button
                inBoard[x][y].setMargin(margins); //Sets space for margin between the button's border and the label
                inBoard[x][y].setToolTipText(x + "," + y); //Info of the cordinates when hover on a index
                inBoard[x][y].setName(x + "," + y);
                inBoard[x][y].setIcon(sea);
                inBoard[x][y].setHorizontalTextPosition(0); //Sets the horizontal position of the text 
                // System.out.println("Testing " + x);




                if (includeListeners) {
                    inBoard[x][y].addActionListener(this); //Setting up the listeners
                }
                newBoard.add(inBoard[x][y]); //Adding the board.
            }


        }
        this.panelPlayBoard.add(newBoard);
    }
    
    //Debugging method
    public void displayShipPositions(Ship[] tempShips) {
               
        System.out.println("DISPLAYING SHIP POSITIONS: \n");
        for (int i = 0; i < 5; i++) {
            int size = tempShips[i].getSize();
            int[][] shipCoords = tempShips[i].getCoords();
            ImageIcon[][] shipIcons = tempShips[i].getShipIcons();
            int orientation = tempShips[i].getOrientation(); 
             for(int[] x : shipCoords) {
                System.out.println("");
                System.out.println(Arrays.toString(x));
            }
             
             System.out.println("ORIENTATION " + orientation);
                }
        
     
    }

   


    //This method paints our ships at Playboard.
    private void paintShips(JButton[][] inBoard, Ship[] temporaryShips) {
      //  JButton[][] inBoard = new JButton[10][10];
        displayShipPositions(temporaryShips);
        for (int i = 0; i < 5; i++) {
            int size = temporaryShips[i].getSize();
            int[][] shipCoords = temporaryShips[i].getCoords();
            ImageIcon[][] shipIcons = temporaryShips[i].getShipIcons();
            int orientation = temporaryShips[i].getOrientation();
//            System.out.println("SHIP COORDINATES: " + shipCoords);
            System.out.println("SIZE: " + size);
            System.out.println("ORIENTATION: " + orientation);
            //temporaryShips.getSize doesnt works
            System.out.println("SHIP COORDS AT PAINT SHIPS METHOD: ");
            for(int[] x : shipCoords) {
                System.out.println("");
                System.out.println(Arrays.toString(x));
            }
            for (int j = 0; j < size; j++) {
                int x = shipCoords[j][0];
                int y = shipCoords[j][1];
                System.out.println("XCOORD AT METHOD PAINT SHIPS: " + x);
                System.out.println("YCOORD AT METHOD PAINT SHIPS: " + y);
                
       //      if (inBoard[x][y].getIcon().equals(sea)) 

                   
                    inBoard[x][y].setIcon(shipIcons[orientation][j]);
                
           }
        }
    }
    
    //this methid paints sunk ships as soon as they sink. Same as paintShips
    private void paintSunkShips(JButton[][] inBoard, Ship inShip) {
        int size = inShip.getSize();
        int[][] shipCoords = inShip.getCoords();
        ImageIcon[][] shipSunkIcons = inShip.getShipSunkIcons();
        int orientation = inShip.getOrientation();

        for (int i = 0; i < size; i++) {
            int x = shipCoords[i][0];
            int y = shipCoords[i][1];

            inBoard[x][y].setIcon(shipSunkIcons[orientation][i]);
        }
    }

    public void getMove(int x, int y) {
        if(this.computer == null) 
        checkMove(this.myBoard, this.myShips, x, y);
        else
        checkMove(this.myBoard, this.myShips, x, y);
      
        if (this.myTurn == -1) 
            return;
         
        
        this.myTurn = 1;
        this.status.setText("Status: Waiting for your move.");
        
        
    }

    public JButton[][] getMyBoard() {
        return this.myBoard;
    }

    public JButton[][] getOpponentBoard() {
        return this.opponentBoard;
    }

 public void makeMove(int x, int y)
  {
      System.out.println("MAKING MOVE WITH COORDS:   X " + x + "  Y " +y );
    checkMove(this.opponentBoard, this.opponentShips, x, y);
    if (this.myTurn == -1)
      return;
    this.myTurn = 0;
    this.status.setText("Status: No need - Computer instantly reacts");
   

    if (this.computer != null)
      this.computer.getMove(x, y);
  }

    public void checkGame() {
        System.out.println("CHECKING GAME: ");
        if (this.myShipsRemainingValue == 0) {
            System.out.println("MY SHIPS REMAINING VALUE: \n" + myShipsRemainingValue);
            if (this.computer != null) {
            JOptionPane.showMessageDialog(this.gameEngineFrame.getContentPane(), "You Lost. Select New Game from menu to play again.");
            }
            this.status.setText("Status: You Lost!");
            this.myTurn = -1;
        } else if (this.opponentShipsRemainingValue == 0) {
            if (this.computer != null) {
             System.out.println("OPPONENT SHIPS REMAINING VALUE: \n" + opponentShipsRemainingValue);
            
            JOptionPane.showMessageDialog(this.gameEngineFrame.getContentPane(), "You Win. Select New Game from menu to play again.");
            }
            this.status.setText("Status: You Win!");
            this.myTurn = -1;
        }
    }

    public void checkMove(JButton[][] inBoard, Ship[] inShips, int x, int y) {
        boolean hit = false;
       // Ship[] inShips = new Ship[5];
        int i = -1;
        System.out.println("CHECKING MOVE AT CLASS PLAYBOARD (checkMove): ");
        System.out.println("xCoord " + x);
        System.out.println("yCoord " + y);
        System.out.println("");
        do {
            i++;
            System.out.println("INSHIPS: ");
            System.out.println("!!!!!!!!!!!!!!!! " + i);
         //   inShips[i] = new Ship();
            hit = inShips[i].checkMove(x, y);
            System.out.println("IS HIT? : " + String.valueOf(hit));
        } while ((i < 4) && (!hit)); //Here we check for times if hit. One for each ship.
        
        
        if (hit) { //If a ship has been hit, then put fire icon in that cell
            inBoard[x][y].setIcon(this.fire);
            System.out.println("ZZZZZZZZZZZZZZZZZZZZZZZ");
            if (inShips[i].isSunk()) { //If a ship sunks, paint the sunk ship in the board.
                paintSunkShips(inBoard, inShips[i]);
            

            if (this.myTurn == 1) { //If a ship is sunk, minus one to the opponent ships remaining value. Same for my ships remaining value.
                this.opponentShipsRemainingValue -= 1;
                this.opponentShipsRemainingLabel.setText(String.valueOf(this.opponentShipsRemainingValue));

            } else {
                this.myShipsRemainingValue -= 1;
                this.myShipsRemainingLabel.setText(String.valueOf(this.myShipsRemainingValue));
            }
              checkGame(); //Checks the game if finished.
        }
        }
        else {
            inBoard[x][y].setIcon(this.miss); //If hit misses, set icon "miss".
        }
       
    
       
    

    }

    @Override
    public void actionPerformed(ActionEvent event) {
        System.out.println("VALUE OF GOT OPPONENT SHIPS " + String.valueOf(gotOpponentShips)); //Debugging 
        if (!this.gotOpponentShips) {
            return;
        }
        if (this.myTurn == 0) {
            return;
        }
        if (this.myTurn == -1) {
            return;
        }


        JButton clickedButton = (JButton) event.getSource();
        
        
        
        String Name = clickedButton.getName(); //Name takes the value of X and Y cord. (format X,Y)
        int commaPos = Name.indexOf(",");
     
        
        int x = Integer.parseInt(Name.substring(0, commaPos)); //Parses integer from 0 to commaPos to generate X value
        int y = Integer.parseInt(Name.substring(commaPos + 1));//Parses integer to commaPos + 1 to generate Y value
        System.out.println("");
           System.out.println("DEBUGGING\n");
        System.out.println("COMMA POSITION: " + commaPos);
        System.out.println("CLICKED BUTTON: " + Name);
        System.out.println("XCOORD CLICKED: " + x);
        System.out.println("YCOORD CLICKED: " + y);
        if (!this.opponentBoard[x][y].getIcon().equals(sea)) { //If the spot's icon we hit is not equels to sea equals to INVALID MOVE
            this.status.setText("Status: Invalid move, try again");
            return;
        }
makeMove(x, y);
    }
}
