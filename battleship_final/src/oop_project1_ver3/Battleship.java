/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oop_project1_ver3;

import javax.swing.*;

/**
 *
 * @author Manos
 */
public class Battleship
    
{
  
  
  private static SelectionFrame selectionFrame;
  private Engine engineWindow;
 
  private Computer ai;


  
  //When running the program the choice dialog pops up
  public void choiceDialog()
  {
   
    
    selectionFrame = new SelectionFrame();
    selectionFrame.showFrame();
    selectionFrame.setVisible(true);
    
    
    //As soon as no button is clicked, that means that user selection is 0 by default, program is idle.
    //Ayto to rwtisa sto stackoverflow, gt dn eixa idea pws na to kanw.
    while (selectionFrame.getUserChoice() == 0)
    {
      try
      {
        Thread.sleep(1000L);
      }
      catch (InterruptedException e)
      {
      }
    }
    //Mexri edw.
    selectionFrame.setVisible(false);
    handleUserSelection();
    battleshipGUI();
    selectionFrame.dispose();
  }
  
  public String nameGet () {
    String playerName = JOptionPane.showInputDialog(null, "Enter Your Name", "Enter Name", JOptionPane.QUESTION_MESSAGE);
      return playerName;
    
}

  
  
  //If user clicks SINGLE PLAYER, user choice == 1 and it opens placement.
   private void battleshipGUI()
  {
    if (selectionFrame.getUserChoice() == 1)
    {
        
      this.engineWindow = new Engine(665, 405);
      this.engineWindow.setPlayer(this.ai);
    }
    else
    {
     
    }
    this.engineWindow.setVisible(true);
  }
  
  
  
  
//Handls the user's selectiion and calls computer class, in order for computer to set up ships etc.
  private void handleUserSelection()
  {
    switch (selectionFrame.getUserChoice())
    {
    case 1:
      this.ai = new Computer();
      break;
    
    }
  }


}

