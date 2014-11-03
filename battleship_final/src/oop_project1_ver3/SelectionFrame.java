/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oop_project1_ver3;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;


/**
 *
 * @author Manos
 */
public class SelectionFrame extends JFrame implements ActionListener {
    
      private ImageIcon JFrame_Icon = new ImageIcon("icon6.png");
     public static final int NONE = 0;
  public static final int SINGLE = 1;
  
  //Array of picture's names
  private static final String[] paths = { "spbtn.jpg", "quitbtn.jpg" };

  //
  private SelectionPanel backgroundPanel;
  private JButton[] buttons;
  private int btnYCoord = 110;
  private int userChoice;
  private String enteredName;
  
     public SelectionFrame()
  {
    super("Battleship!");
    
  
    setDefaultCloseOperation(3);
    setLocation(493, 233);
    this.backgroundPanel = new SelectionPanel();
    //Our window takes size from the image's dinmesions.
    setSize(this.backgroundPanel.getImageWidth(), this.backgroundPanel.getImageHeight());
    setResizable(false);
    this.buttons = new JButton[2];
    setLayout(null);
    this.userChoice = 0;
    setVisible(true);
    this.setIconImage(JFrame_Icon.getImage());

       
  }
     
  
     
       private void initializeButtons()
  {
     //Buttons layout SETUP
    for (int i = 0; i < 2; i++)
    {
      ImageIcon buttonIcon = new ImageIcon(paths[i]);
      this.buttons[i] = new JButton(buttonIcon);
      this.buttons[i].setSize(buttonIcon.getIconWidth(), buttonIcon.getIconHeight());
      this.buttons[i].setLocation(40, this.btnYCoord);
      Border buttonBorder = BorderFactory.createEtchedBorder(Color.CYAN, Color.BLACK);
      this.buttons[i].setBorder(buttonBorder);
      this.btnYCoord += 40;
      this.buttons[i].addActionListener(this);
      add(this.buttons[i]);
    }
  }

  public void showFrame()
  {
    initializeButtons();
    this.backgroundPanel.startScreen();
    add(this.backgroundPanel);
  }

     @Override
  public void actionPerformed(ActionEvent event)
  {
    try //Exception
    {
      if ((event.getSource() instanceof JButton))
      {
        JButton clickedButton = (JButton)event.getSource();
        if (clickedButton == this.buttons[1]) {
          System.exit(0);
        }
       
          else
          {
            this.userChoice = 1;
          }
        }
     //   Placement x = new Placement();
     //   x.getPlacementPanel()
      //  String playerName = player.getText();
    }
    
  
    catch (NullPointerException e)
    {
    }
  }


  
  //Getter for userchoice
  public int getUserChoice()
  {
    return this.userChoice;
  }

  public String getEnteredNames()
  {
    return this.enteredName;
  }
}
