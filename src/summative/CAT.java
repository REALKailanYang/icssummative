package summative;


import processing.core.PApplet;
import summative.Person;
import summative.mysketch;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author lilli
 */
public class CAT extends Person {
    
    public CAT(PApplet p, int x, int y, String name, int speed, String imagePath) {
        super(p, x, y, name, speed, imagePath);
    }
    
    String [] cattalk = {
        " ", //stage 0;
        " ", //stage 1;
        "Meow meow meow!", //stage 2
        "Meow (make zhongzi now?)", //stage 3;
        "Meow (Let's start with the leaf)", //stage 4;
        "Meoww (Rice next!)"   //stage 5
    };
    
    @Override
    public String getDialogue() {
        return cattalk[mysketch.stage]; 
    }
    
}
