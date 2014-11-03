package oop_project1_ver3;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class SelectionPanel extends JPanel
{
  //Variable with the name of our pic
  private ImageIcon imageIcon;
  private Dimension windowSize;
  private int imageHeight;
  private int imageWidth;
  public String playerName = "Player's" ;

  
  protected SelectionPanel()
  {
    
    this.imageIcon = new ImageIcon("battleship.jpg"); //Save image to imageIcon
    Image image = this.imageIcon.getImage();
    

    this.imageWidth = image.getWidth(null);
    this.imageHeight = image.getHeight(null);
    
            //Debugging
           // System.out.println(System.getProperty("user.dir"));
         //   System.out.println(image.getHeight(null));
           // System.out.println(image.getWidth(null));
            //*Debugging
    if ((this.imageWidth == -1) || (this.imageHeight == -1)) //Check if background image is succesfully loaded
    {
      JOptionPane.showMessageDialog(JOptionPane.getDesktopPaneForComponent(this), "Could not load background image", "Fatal error!", 0);

      System.exit(-1);
    }
    this.windowSize = new Dimension(this.imageWidth, this.imageHeight);
  }

  protected void startScreen()
  {
    setOpaque(false);
    setSize(this.windowSize);
  }

  protected int getImageHeight()
  {
    return imageHeight;
  }

  protected int getImageWidth()
  {
    return imageWidth;
  }

  @Override
  public void paintComponent(Graphics g)
  {
    
    g.drawImage(this.imageIcon.getImage(), 0, 0, null);
    super.paintComponent(g);
   
  }
}