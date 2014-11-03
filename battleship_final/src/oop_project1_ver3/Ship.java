package oop_project1_ver3;

import java.util.Arrays;
import javax.swing.ImageIcon;

public class Ship {

    private String name;
    private int size;
    private int orientation;
    private int shipX;
    private int shipY;
    public int piecesHit;
    private int button;
    private boolean sunk;
    private int[][] shipCoords;// = new int[5][3];
    // /* Arrays of images to keep ship icons.
    private ImageIcon[][] shipIcons = new ImageIcon[2][5];
    private ImageIcon[][] shipSunkIcons = new ImageIcon[2][5];
    // */
    private int[] lastPieceHit;

    //This methos is used for the Artificial Intelligence, the game status checking method and for sunk ships true/false status.
    public Ship() {
        this.size = 1;
        this.orientation = 0;
        this.piecesHit = 0;
        this.sunk = false;
        this.lastPieceHit = new int[2];
    }

    //This method is used for creating the SHIPS
    public Ship(int tempSize, String tempName) {
        this();
        this.size = tempSize;
        this.name = tempName;
        this.shipCoords = new int[this.size][2];
        this.shipIcons = new ImageIcon[2][this.size];
        this.shipSunkIcons = new ImageIcon[2][this.size];
    }

    //This method is used for creating the Buttons of the ships with their icons.
    public Ship(int tempSize, String tempName, int tempButton) {
        this();
        this.size = tempSize;
        this.name = tempName;
        this.button = tempButton;
        this.shipCoords = new int[this.size][2];
        this.shipIcons = new ImageIcon[2][this.size];
        this.shipSunkIcons = new ImageIcon[2][this.size];


    }

//Getters Setters
    public int getButton() {
        return this.button;
    }

    public int[][] getCoords() {
        return this.shipCoords;
    }

    public int getSize() {
        return this.size;
    }

    public ImageIcon[][] getShipIcons() {
        return this.shipIcons;
    }

    public ImageIcon[][] getShipSunkIcons() {
        return this.shipSunkIcons;
    }

    public int[] getLastPieceHit() {
        return this.lastPieceHit;
    }

    public String getName() {
        return this.name;
    }

    public int getOrientation() {
        return this.orientation;
    }

    public int getX() {
        return this.shipX;
    }

    public int getY() {
        return this.shipY;
    }

    public boolean isSunk() {
        return this.sunk;
    }

    public void setCoords(int[][] tempShipCoords) {
        this.shipCoords = tempShipCoords;
    }

    //this setter is called by makeShips method. Here we set the coordinates of the ships for both the computer and the player.
    public void setPos(int tempX, int tempY) {
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&   ");
        System.out.println("tempX: " + tempX);
        System.out.println("tempY: " + tempY);

        this.shipX = tempX;
        this.shipY = tempY;
    }

    //Method for rotating the ships
    public void rotateShip() {
        if (this.orientation == 0) {
            this.orientation = 1;
        } else {
            this.orientation = 0;
        }
        
    }

    public void makeCoords() {
        for (int i = 0; i < this.size; i++) {
            if (this.orientation == 0) {
                this.shipCoords[i][0] = (this.shipX + i);
                this.shipCoords[i][1] = this.shipY;
            } else {
                this.shipCoords[i][0] = this.shipX;
                this.shipCoords[i][1] = (this.shipY + i);
            }
        }
    }

    public void makeIcons() {
        //Ship names format is: NumberA 1 - 5 _0 or 1_ NumberB 0 - 4
        //NumberA indicates the size of the ship, middle number indicates Horizontal or Vertical and NumberB indicates the Ship number
        for (int i = 0; i < this.size; i++) {

            this.shipIcons[0][i] = new ImageIcon("images/ships/" + this.size + "_0_" + i + ".gif");
            this.shipSunkIcons[0][i] = new ImageIcon("images/ships/sunk/" + this.size + "_0_" + i + ".gif");
            // System.out.println("Making Icons method testing         " + i);
        }
        for (int i = 0; i < this.size; i++) {
            this.shipIcons[1][i] = new ImageIcon("images/ships/" + this.size + "_1_" + i + ".gif");
            this.shipSunkIcons[1][i] = new ImageIcon("images/ships/sunk/" + this.size + "_1_" + i + ".gif");

            //   System.out.println("Making Icons method testing 2       " + i);
            //System.out.println("" + shipIcons[0][i]);
        }
    }

    public boolean checkMove(int x, int y) {
        System.out.println("");
        System.out.println("CHECKING MOVE AT SHIP CLASS: ");
        System.out.println("Xcoord " + x);
        System.out.println("Ycoord " + y);


        for (int i = 0; i < this.size; i++) {
            if ((this.shipCoords[i][0] == x) && (this.shipCoords[i][1] == y)) {
                this.piecesHit += 1;
                this.lastPieceHit[0] = x;
                this.lastPieceHit[1] = y;

                if (this.piecesHit == this.size) {
                    this.sunk = true;
                }
                return true;
            }
            System.out.println("SHIP COORDS ARRAY AT SHIP CLASS: ");
            System.out.println("");
            for (int[] z : shipCoords) {
                System.out.println("");
                System.out.println(Arrays.toString(z));
            }
        }

        return false;
    }

    public boolean checkCoord(int x, int y) {
        for (int i = 0; i < this.size; i++) {
            if ((this.shipCoords[i][0] == x) && (this.shipCoords[i][1] == y)) {
                return true;
            }
        }
        return false;
    }
}
