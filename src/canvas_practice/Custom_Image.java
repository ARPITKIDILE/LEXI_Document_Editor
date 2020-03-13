package canvas_practice;
import static canvas_practice.Canvas_Practice.width_of_character;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Custom_Image extends Glyph{
    
    Image img_custom;
Canvas_Practice cp;
    

    Custom_Image(Image image_custom,Canvas_Practice cp) {
      this.img_custom = image_custom;
      this.cp=cp;
    }

    public Custom_Image(Image image_custom) {
        this.img_custom=image_custom;
    }
    
    
    @Override
    void draw(Graphics g, int index) {
       
         
           int x1=index*Canvas_Practice.width_of_character;
       int y1=0;
       
       g.drawImage(img_custom, x1, y1, Canvas_Practice.width_of_character, Canvas_Practice.height_of_character, cp);

       
    }
    
       
   
   void drawCustomImage(Graphics g,int index,Image image_custom,Canvas_Practice cpo){
     
         
           int x1=index*Canvas_Practice.width_of_character;
       int y1=0;
       
       g.drawImage(Canvas_Practice.image_custom, x1, y1, Canvas_Practice.width_of_character, Canvas_Practice.height_of_character, cp);
   }
    
}
