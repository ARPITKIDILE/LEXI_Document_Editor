package canvas_practice;
import java.util.ArrayList;
public class Memento {
    ArrayList<Glyph>componentArray=new ArrayList<>();    
    ArrayList<Glyph> getState(){
        return componentArray;
    }
    void setState(ArrayList<Glyph> c){
        for(int i=0;i<c.size();i++){
            componentArray.add(c.get(i));
        }
    }
}
