package canvas_practice;
import java.awt.Graphics;
import java.util.ArrayList;
public class RowComposite extends Glyph {

    public RowComposite() {
        char_list=new ArrayList<>();
    }
    
    
     void addComponent(Glyph glyph){
       char_list.add(glyph);
    }
    
    void removeComponent(){
        char_list.remove(char_list.size()-1);
    }
    
    Glyph getComponent(int index){
        return (Glyph) char_list.get(index);
    }
    
    ArrayList<Glyph> getComponentList(){
        return char_list;
    }

    int getSize(){
        return char_list.size();
    }
    @Override
    void draw(Graphics g, int index) {     
     for(int i=0;i<char_list.size();i++){
         Glyph glyph=char_list.get(i);
             glyph.draw(g, i); 
     }
    }
     public CustomIterator getIterator(){
            CustomIterator itr=new CharacterIterator(char_list);
            return itr;
            
        }

    Memento createMemento(){
       Memento memento=new Memento();
       memento.setState(char_list);
        return memento;
    }
    
    void setMemento(Memento memento){
        char_list=memento.getState();
       
    }
    
}
