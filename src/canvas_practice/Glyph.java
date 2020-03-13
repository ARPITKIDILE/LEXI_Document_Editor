package canvas_practice;
import java.awt.Graphics;
import java.util.ArrayList;

public abstract class Glyph {
     ArrayList<Glyph>char_list;

    
    
    void addComponent(Glyph glyph){
    }
    
    void removeComponent(){
    }
    
    Glyph getComponent(int index){
        return (Glyph) char_list.get(index);
    }
    
    ArrayList<Glyph> getComponentList(){
        return char_list;
    }
    
    abstract  void draw(Graphics g,int index);
  
    int getSize(){
        return 0;
    }
     public CustomIterator getIterator(){
           return null;
            
        }
    Memento createMemento(){
        return null;
    }
    
    void setMemento(Memento memento){
    }
}
