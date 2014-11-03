/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oop_project1_ver3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;



/**
 *
 * @author Manos
 */
//Generating the menu bar.
public class Menu implements ActionListener {
    private Engine cc;
    private JMenuBar menuBar;
    private JMenu game;
    private JMenu help;
    private JMenuItem aboutItem;
    private JMenuItem quitItem;
    private JMenuItem newGameItem;
    
    public Menu (Engine cc) {
        System.out.println("testing menu class");
        this.cc = cc;
        this.menuBar = new JMenuBar();
        this.game = new JMenu("Game");
        this.help = new JMenu("Help");
        
        makeGameMenu();
        makeAboutMenu();
       
        
    }

   
    
   private void makeGameMenu() {
        System.out.println("Making game menu");
        this.newGameItem = new JMenuItem("New Game");
        this.quitItem = new JMenuItem("Quit Game");
        this.game.add(this.newGameItem);
        this.newGameItem.addActionListener(this);
        this.game.addSeparator();
        
        this.quitItem.addActionListener(this);
        
        
         this.game.add(this.quitItem);
         this.menuBar.add(this.game);
        
    }
    
   
   
   private void makeAboutMenu() {
        this.aboutItem = new JMenuItem("About");
        this.help.add(aboutItem);
        this.aboutItem.addActionListener(this);
        this.menuBar.add(this.help);
        
                
    }
   
   
     public JMenuBar getMenuBar()
  {
    return this.menuBar;
  }
     
   public void actionPerformed(ActionEvent event) {
       JMenuItem source = (JMenuItem)event.getSource();
       
       //If user clicks new game then:
       if (source == this.newGameItem) {
           this.cc.newGame();
       }
       //If user clicks Quit then:
       else if (source == this.quitItem) {
           System.exit(0);
       }
       //If user clicks Help - About then:
       else if (source == this.aboutItem) {
           JOptionPane.showMessageDialog(null, "This Battleship Game was created by Manos Kontakis for Object Oriented Programming Lab", "About", 1);
       }
   }
    
 
      
      
}

