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
    public static int stage,step,story1 = 0;
    private static int loopcount = 1;
    //interact npcs
    private boolean showInfo,showInfo1,story2,talknpc1 = false; // make one for each npc
    private Button startButton, startzhongzi, laterzhongzi,later,proceed,throwzhongzi;
    private PImage bg,bg1,bg2, bg3;
    public boolean talktocat, talktocat2, zhongzistart = false;
    //make zhongzi
    //public makezhongzi leaf,leaf1,date,date1,rice,rice1,zhongzi,yarn;
    public makezhongzi leaf,date,rice,zhongzi,yarn,bigzhongzi;
    
    makezhongzi [] build = new makezhongzi[5];
    String [] story = {
        "Story line 1 ",
        "Story line 2 ",
        "Story line 3",
        "Story line 4",
        ""
    };
    
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
        later = new Button (this, 441, 70, "images/later.png");
        proceed = new Button (this, 220, 70, "images/proceed.png");
        throwzhongzi = new Button (this, 600,150, "images/throwzhongzi.png");
        bg= loadImage("images/menu.png");
        bg1= loadImage("images/village1.jpg");
        bg2 = loadImage("images/water.png");
        bg3 = loadImage("images/table.png");
        
        //making zhongzi
        leaf = new makezhongzi(this, 600 ,25,"images/leaf.png");
        date = new makezhongzi(this, 57, 440, "images/red.png");
        rice = new makezhongzi(this, 25, 25, "images/rice.png");  
        yarn = new makezhongzi(this, 5, 250, "images/yarn.png");
        zhongzi = new makezhongzi(this, 0,0, "images/small zhongzi.png");
        bigzhongzi = new makezhongzi(this,0,0,"images/bigzhongzi.png");
        
        build[0] = new makezhongzi(this, 25,534, "images/cat.png"); //filler
        build[1] = new makezhongzi(this, 320, 50, "images/leaf1.png"); //leaf
        build[2] = new makezhongzi(this, 335, 255, "images/rice1.png"); // rice
        build[3] = new makezhongzi(this, 335, 255, "images/red1.png"); // date
        build [4] = new makezhongzi(this, 0, 0, "images/small zhongzi.png"); //zhongzi    
        

        //PApplet p, int x, int y, int speed, String imagePath
        //------------------------------------------------
        testblock = new Person (this, 368, 385,"images/testblock.png");
        
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
            
            if (talknpc1 == true){
                this.text(story[story1],npc1.x-25,npc1.y+5);
                this.text(" ",mc.x-25,mc.y+5);
            }
 
        } // end stage 3
        else if (stage >=4 && stage < 8){
            image(bg3,0,0,width,height);
            cat.draw();
            leaf.draw();
            date.draw();
            rice.draw();
            yarn.draw();
            
            cat.moveTo(25,534);
            fill(0);
            this.text(cat.getDialogue(), cat.x+15, cat.y+5);
            
            for (int i = step; i <4; i++){
                //build[step].draw();
                for (int a = 0; a <= step; a++){
                    build[a].draw();    
                } 
            }
        }
        else if (stage == 8){
            image(bg3,0,0,width,height);
            cat.draw();
            leaf.draw();
            date.draw();
            rice.draw();
            yarn.draw();
            bigzhongzi.draw();
            later.draw();
            proceed.draw();
            fill(0);
            this.text(cat.getDialogue(), cat.x+15, cat.y+5);
            text("Make more or continue story?", 217, 30);
        }
        else if (stage == 9){
            image(bg2,0,0,width,height);
            cat.draw();
            mc.draw();
            
            cat.moveTo(437, 259);
            mcmovement();
            
           
            throwzhongzi.draw();
        }
        //System.out.println(stage);
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
        else if (stage >= 4 && stage < 8){
                if (step == 0 && leaf.isClicked(mouseX, mouseY)){
                    stepincrease();
                    stageincrease();
                    System.out.println("step: " + step + " complete");
                }
                else if (step == 1 && rice.isClicked(mouseX, mouseY)){
                    stepincrease();
                    stageincrease();
                    System.out.println("step: " + step + " complete");
                }
                else if (step == 2 && date.isClicked(mouseX, mouseY)){
                    stepincrease();
                    stageincrease();
                    System.out.println("step: " + step + " complete");
                }
                else if (step == 3 && yarn.isClicked(mouseX,mouseY)){
                    stepincrease();
                    stageincrease();
                    System.out.println("step: " + step + " complete");
                }
        }
        else if (stage == 8){
            if (proceed.isClicked(mouseX,mouseY)){
                stageincrease();
                mc.x=578;
                mc.y=239;
            } else if (later.isClicked(mouseX,mouseY)){
                stage = 4;
                step = 0;
                loopcount +=1;
                System.out.println(loopcount);
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
            } else if (mc.isCollidingWith(npc3)){
                fill(0);
                this.text("Eeek!", npc3.x-25, npc3.y+5);
            } else if (mc.isCollidingWith(npc2)){
                fill(0);
                this.text("NOOOOOOOOO!", npc2.x-25, npc2.y+5);
            } else if (mc.isCollidingWith(npc1)){
                story2 = true;
                this.text("What's going on? (press Enter)", mc.x-15, mc.y+5);
            }
            
        }
        else if (stage ==9){
            if (mc.isCollidingWith(cat)){
                fill(255);
                this.text(cat.getDialogue(), cat.x-25, cat.y+5);
                System.out.println("collided!");
            }
        }
    }
    
    public void stepincrease(){
        step +=1;
    }
    
    public void stageincrease(){
        stage +=1;
    }
    
    public void keyPressed(){
        if (keyCode == ENTER && stage ==0)
            stageincrease();
        if (stage == 3 && story2 == true){
            if (key == ENTER) {
                    talknpc1= true;
                    if (story1 < 4)
                        story1++;  
            }
        }    
        
    }
        
           
} // close class
