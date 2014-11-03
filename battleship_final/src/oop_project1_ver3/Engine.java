package oop_project1_ver3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.*;
//import java.awt.Window.setIconImages();

public class Engine extends JFrame implements ActionListener {

    // public static final int WNDWIDTH = 665;
    // public static final int WNDHEIGHT = 625;
    //public static final int WNDXCOORD = 10;
    //public static final int WNDYCOORD = 10;
    PlayBoard playBoard;
    private JPanel placementPanel;
    private Computer computer;
    JPanel playBoardPanel;
    // JPanel gameBoardPanel;
    public Computer comp;
    Placement placementBoard;
    // private ImageIcon test = new ImageIcon("battleship.jpg");
    private ImageIcon JFrame_Icon = new ImageIcon("icon6.png");

    public Engine(int width, int height) {

        // System.out.println("TEST ENGINE CLAASS");
        setTitle("Battleship Board - Place Your Ships");
        setSize(width, height);
        setIconImage(JFrame_Icon.getImage());
        setLocation(624, 373);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        setLayout(null);


        //  System.out.println("TEST ENGINE clas2");

        //Making an object menu type.
        Menu menu = new Menu(this);

        newGame(); //Here we drop the Placement Panel into the Frame.

        //Call menu's class constructor with getter as parameter.
        super.setJMenuBar(menu.getMenuBar());





        setDefaultCloseOperation(3);
    }

    //This method installs placement panel to engine frame and starts the game.
    public void newGame() {

        if (computer != null) {
            int answer = JOptionPane.showConfirmDialog(getContentPane(), "Are you sure you want to start a new game?", "Are you sure?", 0);
            if (answer == 1) {
                return;
            }
        }
        if (this.placementPanel != null) {
            remove(this.placementPanel);
        }
        if (this.playBoardPanel != null) {
            remove(this.playBoardPanel);
        }
        // boolean isVisible;
        this.placementBoard = new Placement();
        this.placementBoard.setFrame(this);
        this.placementPanel = this.placementBoard.getPlacementPanel();
        this.playBoard = new PlayBoard();
        this.playBoardPanel = this.playBoard.getPanelPlayBoard();


        if (this.computer != null) {
            this.computer = new Computer();
            this.playBoard.setPlayer(this.computer);
        }

        JButton confirmButton = this.placementBoard.getConfirmButton();
        confirmButton.addActionListener(this);
        add(this.placementPanel);

        paintComponents(getGraphics());
    }

    public void getMyShips() {
        int[][] myBoardShips = this.placementBoard.getAllShipCoords();
        System.out.println("DISPLAYING MY BOARD SHIPS ARRAY: ");
        for (int[] arr : myBoardShips) {

            System.out.println(Arrays.toString(arr));
        }


        this.playBoard.setMyShips(myBoardShips);
        this.computer.sendPlayerShips(myBoardShips);
    }

    public void gameStart() {
        remove(this.placementPanel); //removes placement panel board.
        this.placementBoard.dispose(); //Releases all of the native screen resources used by this Window
        add(this.playBoardPanel);
        this.playBoard.setFrame(this); //Sets to playboard the same JFrame as Placement's board.
        this.setTitle("Battleship Is Live! Have Fun!"); //Sets new title
        paintComponents(getGraphics()); //Paints the new componets of the JFrame.

    }

    //Action listener for Confirm button.
    @Override
    public void actionPerformed(ActionEvent event) {
        JButton clickedButton = (JButton) event.getSource();
        if (clickedButton.getName().equals("Confirm")) { //If clicked button == confirm then

            getMyShips();
            this.computer.setPlayerGameBoard(this.playBoard);
            this.computer.generateComputerBoard();
            gameStart();

        }
    }

    public void setPlayer(Computer comp) {
        this.computer = comp;
        this.playBoard.setPlayer(computer);
    }
}
