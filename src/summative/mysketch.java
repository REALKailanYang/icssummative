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
    public Person Quyuan,mc;
    public Person npc1,npc2,npc3;
    public CAT cat;
    //---------------------------------------------
    private Person testblock;
    public static int stage = 0;
    //interact npcs
    private boolean showInfo,showInfo1 = false; // make one for each npc
    private Button startButton, startzhongzi, laterzhongzi;
    private PImage bg,bg1,bg2;
    public boolean talktocat, talktocat2, zhongzistart = false;
    
    //private int speed;
    
    public void settings(){
        //window size
        size(800,600);
    }
    
    public void setup(){
        //set background color
        background (252,246,217);
        textSize(25);
        mc = new Person(this,220,400, "you",3,"images/mc1.png");
        Quyuan = new Person(this,506,223,"QuYuan",2,"images/biggerQuyuan.png");
        npc1 = new Person (this,200,330,"npc",1,"images/npc1.png");
        npc2 = new Person(this,500,270,"npc",1,"images/npc2.png");
        npc3 = new Person(this,700,450,"npc",1,"images/npc3.png");
        cat = new CAT(this,632,250,"cat",0,"images/cat.png");
        startButton = new Button (this,330,120,"images/startbutton1.png");
        startzhongzi = new Button (this,12,437,"images/startzhongzi.png");
        laterzhongzi = new Button (this,163,437,"images/zhongzilater.png");
        bg= loadImage("images/menu.png");
        bg1= loadImage("images/village1.jpg");
        bg2 = loadImage("images/water.png");
        //PApplet p, int x, int y, int speed, String imagePath
        //------------------------------------------------
        testblock = new Person (this, 368, 385, 0, "images/testblock.png");
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
            fill(255);
            text("Cutscene",50,50);
            Quyuan.draw();
            if (Quyuan.x >= 369){
                Quyuan.move(-1, -1);
            } else  {
                Quyuan.move(0,2); // consider drawing him upside down for this part
            }
            drawCollisions();
            //---------------------------------------------
            testblock.draw();
            
        } else if (stage ==2){
            image(bg1,0,0,width,height);
            fill(255);
            if(npc3.x>0){
                text("Cutscene",50,50);
            }
            npc1.draw();
            npc2.draw();
            npc3.draw();
            cat.draw();
            mc.draw();
            testblock.draw();
            
            testblock.moveTo(25,550);
            npc1.move(-4,2);
            npc2.move(-5,3);
            npc3.move(-6,0);

            mcmovement();
            
            drawCollisions();
            
            if (talktocat== false && npc3.x<0){
                text("Objective: ???",50,50);
            } else if (talktocat==true) {
                text("Objective: Follow the NPCS", 50,50);
            }
            
            if (showInfo){
                mc.displayInfo(this);
            }
            if (showInfo1){
                cat.displayInfo(this);
            }
            

                        
            //---------------------------------------------
            //testblock.draw();
            
        }// end stage 2
        else if (stage ==3){
            image(bg2,0,0,width,height);

            npc1.draw();
            npc2.draw();
            npc3.draw();
            cat.draw();
            mc.draw();
            
            npc1.moveTo(53,184);
            npc2.moveTo(413, 130);
            npc3.moveTo(693, 255);
            cat.moveTo(79, 428);
                
            mcmovement();
            
            drawCollisions();
            
            if (talktocat2 == true){
                startzhongzi.draw();
                laterzhongzi.draw();
            }
 
        }

    } //end draw   
    
    public void mcmovement(){
        if (keyPressed){
            if (keyCode == LEFT){
                mc.move(-2,0);
            } else if (keyCode == RIGHT){
                mc.move(2,0);
            } else if (keyCode == UP){
                mc.move(0,-2);
            } else if (keyCode == DOWN){
                mc.move(0,2);
            }
        }  
    }
    
    public void mousePressed(){
        System.out.println("x: " + mouseX + "y: " + mouseY);
        if (stage ==0){
            if (startButton.isClicked(mouseX, mouseY)){
                stage = 1;
            }
        }
        else if (stage ==2){
            if (mc.isClicked(mouseX, mouseY)){
                showInfo= !showInfo;
                System.out.println(showInfo);
                System.out.println("hit1");
            }
            if (cat.isClicked(mouseX, mouseY)){
                showInfo1= !showInfo1;
                System.out.println(showInfo1);
                System.out.println("hit1");
            }
        }
        else if (stage ==3){
            if (talktocat2 == true){
                if (startzhongzi.isClicked(mouseX, mouseY)){
                    stageincrease();
                }else if (laterzhongzi.isClicked(mouseX, mouseY)){
                    mc.x = 687;
                    mc.y = 436;
                    talktocat2 = false;
                }
            }
        }
    }
    
    public void drawCollisions(){
        if(stage ==1){
            if (Quyuan.isCollidingWith(testblock)){
                stageincrease();
            }
        }//close if stage
        else if(stage ==2){
            if (mc.isCollidingWith(cat)){
                fill(255);
                //this.text("Meow meow meow!", cat.x-35, cat.y+5);
                this.text(cat.getDialogue(), cat.x-35, cat.y+5);
                talktocat=true;
            } else if (mc.isCollidingWith(testblock)){
                stageincrease();
                mc.x=687;
                mc.y=436;
            }
        }//close if stage
        else if (stage ==3){
            if (mc.isCollidingWith(cat)){
                fill(0);
                //this.text("Meow (make zhongzi now?)",cat.x-40,cat.y+5);
                this.text(cat.getDialogue(), cat.x-40, cat.y+5);
                talktocat2 = true;
            }
        }
    }
    
//    public boolean makezhongzi(){
//        if (zhongzistart == false){
//            fill(0);
//            this.text("Meow (make zhongzi now?)",cat.x-40,cat.y+5);
//            talktocat2 = true;
//        } else{
//            return true;
//        }
//        return false;
//        
//    }
//    
    
    
    
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
