package summative;

import processing.core.PApplet;
import processing.core.PImage;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author lilli
 */
public class makezhongzi {
  public int x,y;
  private PImage image;
  private PApplet app;
  private int width,height;
  //private int speed = 2;
  
  public makezhongzi (PApplet p, int x, int y, String imagePath){
      this.app = p;
      this.x = x;
      this.y = y;
      this.image = app.loadImage(imagePath);
      this.width = image.width;
      this.height = image.height;
  }
  
    public void moveTo(int dx, int dy){
      x = dx;
      y = dy;
    }
    
    public void draw(){
      app.image(image, x, y);
    }
    
    public boolean isClicked(int mouseX, int mouseY) {
    int centerX = x+(image.pixelWidth/2);
    int centerY = y+(image.pixelHeight/2);        
    float d = PApplet.dist(mouseX, mouseY, centerX ,centerY );
    
    //gives us the dimensions of the image
    System.out.println("image height"+image.pixelHeight);
    System.out.println("image width"+image.pixelWidth);
    
    // returns true if  mouse clicked is within 16px from the center of image
    // we use 16px because the image is 32px by 32px
    return d < 50; 
  }
      public void move(int dx, int dy){
      x+= dx;
      y+= dy;
  }
}
