/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package summative;

/**
 *
 * @author 342799913
 */

import processing.core.PApplet;
import processing.core.PImage;

public class mysketch extends PApplet{
    private Person Quyuan;
    private Person npc1;
    //---------------------------------------------
    private Person testblock;
    int stage = 0;
    //interact npcs
    private boolean showInfo,showInfo1 = false; // make one for each npc
    private Button startButton;
    private PImage bg,bg1,bg2;
    
    public void settings(){
        //window size
        size(800,600);
    }
    
    public void setup(){
        //set background color
        background (252,246,217);
        textSize(25);
        Quyuan = new Person(this,450,300,"QuYuan",2,"images/biggerQuyuan.png");
        npc1 = new Person (this,200,330,"npc",0,"images/npc1.png");
        startButton = new Button (this,330,120,"images/startbutton1.png");
        bg= loadImage("images/menu.png");
        bg1= loadImage("images/village1.jpg");
        bg2 = loadImage("images/water.png");
        //PApplet p, int x, int y, int speed, String imagePath
        //------------------------------------------------
        testblock = new Person (this, 30, 550, 0, "images/testblock.png");
    }

    public void draw(){
        background(252,246,217); //clear screen
        
        if (stage ==0){
            fill(0);
            image(bg,0,0,width,height);
            text("Click Start or press Enter to begin!",250,100);
            startButton.draw();
        } else if (stage==1){
            image(bg2,0,0,width,height);
            Quyuan.draw();
            if (keyPressed){
                if (keyCode == LEFT){
                    Quyuan.move(-2,0);
                } else if (keyCode == RIGHT){
                    Quyuan.move(2,0);
                } else if (keyCode == UP){
                    Quyuan.move(0,-2);
                } else if (keyCode == DOWN){
                    Quyuan.move(0,2);
                }
            }
            drawCollisions();
            //---------------------------------------------
            testblock.draw();
            
        } else if (stage ==2){
            image(bg1,0,0,width,height);
            npc1.draw();
            Quyuan.draw();
            if (keyPressed){
                if (keyCode == LEFT){
                    Quyuan.move(-2,0);
                } else if (keyCode == RIGHT){
                    Quyuan.move(2,0);
                } else if (keyCode == UP){
                    Quyuan.move(0,-2);
                } else if (keyCode == DOWN){
                    Quyuan.move(0,2);
                }
            }
        
            drawCollisions();
            //person1.displayInfo(this);
            if (showInfo){
                Quyuan.displayInfo(this);
            }
            if (showInfo1){
                npc1.displayInfo(this);
            }
            
            //---------------------------------------------
            testblock.draw();
        }// end stage 2

    } //end draw   
   
    
    public void mousePressed(){
        if (stage ==0){
            if (startButton.isClicked(mouseX, mouseY)){
                stage = 1;
            }
        }
        if (stage ==2){
            if (Quyuan.isClicked(mouseX, mouseY)){
                showInfo= !showInfo;
                System.out.println(showInfo);
                System.out.println("hit1");
            }
            if (npc1.isClicked(mouseX, mouseY)){
                showInfo1= !showInfo1;
                System.out.println(showInfo1);
                System.out.println("hit1");
            }
        }
    }
    
    public void drawCollisions(){
        if(stage ==1){
            if (Quyuan.isCollidingWith(testblock)){
                stageincrease();
            }
        }//close if stage
        if(stage ==2){
            if (Quyuan.isCollidingWith(npc1)){
                fill(255);
                this.text("Hi!", Quyuan.x-5, Quyuan.y+5);
            }//close if
        }//close if stage
    }
    
    public void stageincrease(){
        stage +=1;
    }
    
    public void keyPressed(){
        if (stage ==0){
            if (keyCode == ENTER){
            stage = 1;
            }
        }
    }
        
           
} // close class
