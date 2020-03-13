package canvas_practice;
import static canvas_practice.Canvas_Practice.width_of_character;
import java.awt.Graphics;

public class Custom_Rectangle extends Glyph{
    
    
      void drawCustomRectangle(Graphics g,int index){
       int x1=index*Canvas_Practice.width_of_character;
       int y1=0;
       g.drawRect(x1, y1, Canvas_Practice.width_of_character, Canvas_Practice.height_of_character);
    }

    @Override
    void draw(Graphics g, int index) {
         
        int x1=index*Canvas_Practice.width_of_character;
        int y1=0;
       g.drawRect(x1, y1, Canvas_Practice.width_of_character, Canvas_Practice.height_of_character);
    }
}
