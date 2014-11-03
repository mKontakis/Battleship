/*The Artificial Intelligence (AI) of the game, was found on the Web 
  and it has been adjusted to our program. */


package oop_project1_ver3;

//Setting up Computer's AI
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import javax.swing.JFrame;

//Setting up computer ship's placement
public class Computer {

    private Placement computerPlacementBoard;
    public PlayBoard computerGameBoard;
    private PlayBoard playerGameBoard;
    private int[][] compMoves = new int[100][2];
    private int numMove = 0;
    private int[][] playerIntBoard;
    private Ship[] playerShipArray = new Ship[5];
    private List shipsTracking = new ArrayList();
    public int[][] sunkShipCoord;
      private int shipGuessOrientation = -1;
        private int nextDirection;
    

    protected Computer() {
        //boolean isVisible;
        this.computerPlacementBoard = new Placement(); //Creation of computer's board
        //this.computerPlacementBoard.setVisible(false);
        this.computerGameBoard = new PlayBoard();
        this.computerGameBoard.setFrame(new JFrame());
    }

    public void generateComputerBoard() {
        this.computerPlacementBoard.randomPlaceShips(); //Randomly places the computer ships.
        int[][] compShips = this.computerPlacementBoard.getAllShipCoords(); //Getting computer ship's coordinates
         
        System.out.println("COMPUTER SHIPS COORDINATES: &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        for (int[] arr : compShips) {
             
            System.out.println(Arrays.toString(arr));
        }
        this.playerGameBoard.setOpponentShips(compShips); //Makes the ships, at PlayBoard grid
        this.computerGameBoard.setMyShips(compShips); //

    }

    //Gets move
    public void getMove(int x, int y) {
//        System.out.println("GETTING MOVE FROM COMPUTER CLASS: ");
//        System.out.println("%%%%%%%%%%%%COMPUTER CLASS X COORD FIRST COMES: " + x);
//        System.out.println("%%%%%%%%%%%%COMPUTER CLASS Y COORD FIRST COMES: " + y);
        this.computerGameBoard.getMove(x, y); //Gets move from the player from PlayBoard class t through computerGameBoard variable.

        int XCoord = this.compMoves[this.numMove][0];
        int YCoord = this.compMoves[this.numMove][1];
         System.out.println("!@#*(!@(#&*!@*&#!@*(#*(!@&*$^!@&$^#!@&*(#@!#!@");
           for(int[] zz : compMoves) {
                System.out.println("");
               
                System.out.println(Arrays.toString(zz));
            }
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXCOORD: " + XCoord);
        System.out.println("YYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYCOORD: " + YCoord);
        this.playerGameBoard.getMove(XCoord, YCoord); //Getting move from player's instance
        this.computerGameBoard.makeMove(XCoord, YCoord); //Makes move from computer's omstamce
        this.numMove = numMove + 1;
    }

    public void setPlayerPlayBoard(int[][] inShips) {
        this.computerGameBoard.setOpponentShips(inShips);
        makeBoard(inShips);
        simGame();
    }

    public void setPlayerGameBoard(PlayBoard inBoard) {

        this.playerGameBoard = inBoard;
    }

    //Checks if the move is vaalid
  private int validMove(int x, int y)
  {
    if ((x >= 0) && (x < 10) && (y >= 0) && (y < 10))
    {
      if (this.playerIntBoard[x][y] > -2)
        return 1;
    }
    else if ((x < 0) || (x >= 10) || (y < 0) || (y >= 10)) {
      return -5;
    }

    return this.playerIntBoard[x][y];
  }
  
    //Random move generator for the computer
    private int[] getRandomMove() {
        int[] coords = new int[2];

        Random randSeed = new Random();
        int xCoord;
        int yCoord;
        do {
            xCoord = randSeed.nextInt(10);
            yCoord = randSeed.nextInt(10);
        } while (validMove(xCoord, yCoord) != 1);

        coords[0] = xCoord;
        coords[1] = yCoord;

        return coords;
    }
    
    
    

    public void sendPlayerShips(int[][] inShips) {
        this.computerGameBoard.setOpponentShips(inShips);
        makeBoard(inShips);
        simGame();
    }
    
    /*The Artificial Intelligence (AI) of the game, was found on the Web 
      and it has been adjusted to our program. */
    

    public void makeBoard(int[][] startingPosition) {
        this.playerIntBoard = new int[10][10];

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                this.playerIntBoard[i][j] = -1;
            }

        }


        this.playerShipArray[0] = new Ship(5, "Aircraft Carrier");
        this.playerShipArray[1] = new Ship(4, "Battleship");
        this.playerShipArray[2] = new Ship(3, "Cruiser");
        this.playerShipArray[3] = new Ship(2, "Destroyer");
        this.playerShipArray[4] = new Ship(1, "Submarine");


        for (int i = 0; i < 5; i++) {
            int x = startingPosition[i][0];
            int y = startingPosition[i][1];
            int orientation = startingPosition[i][2];

            this.playerShipArray[i].setPos(x, y);

            if (orientation > 0) {
                this.playerShipArray[i].rotateShip();

            }
            this.playerShipArray[i].makeCoords();

            int[][] shipCoords = this.playerShipArray[i].getCoords();
            int size = this.playerShipArray[i].getSize();


            for (int j = 0; j < size; j++) {
                x = shipCoords[j][0];
                y = shipCoords[j][1];
                this.playerIntBoard[x][y] = i;
            }
        }

    }

private int[] makeIntMove(Ship inShip, int[] coords)
  {
    int xCoord = coords[0];
    int yCoord = coords[1];

    int[] hitCoords = inShip.getLastPieceHit();
    int hitXCoord = hitCoords[0];
    int hitYCoord = hitCoords[1];

    if ((xCoord == hitXCoord) && (yCoord == hitYCoord))
    {
      if (inShip.piecesHit > 1)
      { 
        if (inShip.getOrientation() == 0)
          this.shipGuessOrientation = 1;
        else {
          this.shipGuessOrientation = 0;
        }
      }
    }
    else
    {
      xCoord = hitXCoord;
      yCoord = hitYCoord;

      if ((this.shipGuessOrientation == 0) && (this.nextDirection == 0)) {
        this.nextDirection = 1;
      } else if ((this.shipGuessOrientation == 0) && (this.nextDirection == 1)) {
        this.nextDirection = 0;
      } else if ((this.shipGuessOrientation == 1) && (this.nextDirection == 2)) {
        this.nextDirection = 3;
      } else if ((this.shipGuessOrientation == 1) && (this.nextDirection == 3)) {
        this.nextDirection = 2;
      } else if (this.shipGuessOrientation == -1)
      {
        this.nextDirection += 1;
        if (this.nextDirection > 3)
          this.nextDirection = 0;
      }
      else {
        System.err.println("DEBUG: FATAL ERROR -- Ran out of moves");
      }

    }

    int isValid;
    do
    {
      if (this.nextDirection == 0)
        yCoord--;
      else if (this.nextDirection == 1)
        yCoord++;
      else if (this.nextDirection == 2)
        xCoord++;
      else if (this.nextDirection == 3) {
        xCoord--;
      }

      isValid = validMove(xCoord, yCoord);

      if ((isValid == -5) || (isValid == -3) || (isValid == -4))
      {
        if ((this.shipGuessOrientation == 0) && (this.nextDirection == 0)) {
          this.nextDirection = 1;
        } else if ((this.shipGuessOrientation == 0) && (this.nextDirection == 1)) {
          this.nextDirection = 0;
        } else if ((this.shipGuessOrientation == 1) && (this.nextDirection == 2)) {
          this.nextDirection = 3;
        } else if ((this.shipGuessOrientation == 1) && (this.nextDirection == 3)) {
          this.nextDirection = 2;
        } else if (this.shipGuessOrientation == -1)
        {
          xCoord = hitXCoord;
          yCoord = hitYCoord;

          this.nextDirection += 1;
          if (this.nextDirection > 3)
            this.nextDirection = 0;
        }
        else {
          System.err.println("DEBUG: FATAL ERROR -- Ran out of moves");
        }
      }
    }
    while (isValid != 1);

    coords[0] = xCoord;
    coords[1] = yCoord;

    return coords;
  }
    
    
  private void simGame()
  {
    int[] coords = new int[2];

    Ship hitShip = null;

    for (int move = 0; move < 100; move++)
    {
      if (this.shipsTracking.size() > 0)
        coords = makeIntMove((Ship)this.shipsTracking.get(this.shipsTracking.indexOf(hitShip)), coords);
      else {
        coords = getRandomMove();
      }

      int xCoord = coords[0];
      int yCoord = coords[1];

      if (this.playerIntBoard[xCoord][yCoord] >= 0)
      {
        hitShip = this.playerShipArray[this.playerIntBoard[xCoord][yCoord]];
        hitShip.checkMove(xCoord, yCoord);

        if (hitShip.isSunk())
        {
          if (this.shipsTracking.contains(hitShip)) {
            this.shipsTracking.remove(this.shipsTracking.indexOf(hitShip));
          }
          sinkShip(hitShip);

          if (this.shipsTracking.size() > 0)
          {
            Ship nextShip = (Ship)this.shipsTracking.get(0);
            hitShip = nextShip;
            int[] nextShipHitCoords = nextShip.getLastPieceHit();
            xCoord = nextShipHitCoords[0];
            yCoord = nextShipHitCoords[1];
          }

          this.shipGuessOrientation = -1;
        }
        else if (!this.shipsTracking.contains(hitShip))
        {
          this.shipsTracking.add(hitShip);
        }

        if (!hitShip.isSunk()) {
          this.playerIntBoard[xCoord][yCoord] = -2;
        }
      }
      else
      {
        this.playerIntBoard[xCoord][yCoord] = -3;
      }

      this.compMoves[move][0] = coords[0];
      this.compMoves[move][1] = coords[1];
    }
  }

    public void sinkShip(Ship inShip) {
       // sunkShipCoord = new int[10][10];
        
     //  inShip = new Ship();
        
        int[][] sunkShipCoord = inShip.getCoords(); //Saves the coordinates of the sunk ship
        int size = inShip.getSize(); //

        for (int i = 0; i < size; i++) {
            int x = sunkShipCoord[i][0];
            int y = sunkShipCoord[i][1];
            System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%X:" + x);
            System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%Y:" + y);
            this.playerIntBoard[x][y] = -4;
        }
    }
}
