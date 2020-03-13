package canvas_practice;
import java.util.ArrayList;
public interface CustomIterator {
    
    boolean hasNext();
    Glyph next();
}

class CharacterIterator implements CustomIterator{

    int index=0;
    ArrayList<Glyph>compoList;

    public CharacterIterator(ArrayList<Glyph>compoList) {
        this.compoList=compoList;
    }
    @Override
    public boolean hasNext() {
        System.out.println("Size = "+index);
        return index<compoList.size();
    }

    @Override
    public Glyph next() {
        if(hasNext()){
            Glyph item=(Glyph)compoList.get(index);
            index+=1;
            return item;
        }
        return null;
    }
    
}
