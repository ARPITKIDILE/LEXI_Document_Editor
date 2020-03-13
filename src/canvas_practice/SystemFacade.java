package canvas_practice;
import static canvas_practice.Canvas_Practice.glyph;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Stack;
import javax.swing.BorderFactory;

public class SystemFacade {    
    Custom_Rectangle cr;
     private final Stack<Command> commandStack;
    private final Stack<Memento> mementoStack;
    private int undoRedoPointer = -1;
    public SystemFacade() {
        cr=new Custom_Rectangle();
        commandStack=new Stack<>();
        mementoStack=new Stack<>();
    }
    void insertComponent(Glyph glyph,char ch,CustomCharacterFlyweightFactory cff){
        Command c;
        Custom_Character cc=cff.getCustomCharacter(ch);
       c=new InsertGlyph(glyph, cc, cr);
       String type="";
       if(ch==' '){
           type="space";
       }
       
       else {
           type="char";
       }
       c.execute(type);
       undoRedoPointer+=1;
       commandStack.push(c);
    }
    
    void insertImage(Glyph glyph,Image bimage){
      Custom_Image ci=new Custom_Image(bimage,Canvas_Practice.cp);
      glyph.addComponent(ci);
    }
    
    void removeComponent(Glyph glyph){
             Command c=new RemoveGlyph(glyph);
             c.execute("No Need");
             commandStack.push(c);
             undoRedoPointer+=1;
    }
    
    void changeFontSize(Integer fontSize){
       
        String fontFace=Canvas_Practice.monoFont.getFamily();
        Font f=new Font("monospaced", Font.BOLD, fontSize);
        FontMetrics fm=Toolkit.getDefaultToolkit().getFontMetrics(f);
        Canvas_Practice.width_of_character=fm.stringWidth("C");
        Canvas_Practice.height_of_character=fm.getAscent();
        Canvas_Practice.monoFont=f;
        
    }
    
    void changeFontFace(String fontFace){
        
        Integer fontSize=Canvas_Practice.monoFont.getSize();
        Font f=new Font(fontFace, Font.BOLD, fontSize);
        FontMetrics fm=Toolkit.getDefaultToolkit().getFontMetrics(f);
        Canvas_Practice.width_of_character=fm.stringWidth("C");
        Canvas_Practice.height_of_character=fm.getAscent();
        Canvas_Practice.monoFont=f;
    }
    
   void undoTask(Glyph glyph){
       System.out
                .println("undoPointer: " + undoRedoPointer + ", stackSize: " + commandStack.size());
       if(undoRedoPointer!=-1){
           Command command = commandStack.get(undoRedoPointer);
        command.unexecute();
        try{
             if(undoRedoPointer>=0){
            undoRedoPointer-=1;
        }
        }
        catch(ArrayIndexOutOfBoundsException c){
            System.out.println("Indexed Out of Bounded");
        }
       }
        
       
   }
    
    void redo(){
        System.out
                .println("undoPointer: " + undoRedoPointer + ", stackSize: " + commandStack.size());
       if(undoRedoPointer == commandStack.size() - 1)
        { return; }
        undoRedoPointer++;
        Command command = commandStack.get(undoRedoPointer);
        command.execute("No Params");
    }
    
    
       void countChracters(Glyph component){
                int count=0;
              
               System.out.println("-----------------------------------------------------");
               CustomIterator itr=component.getIterator();
               while(itr.hasNext()){
                   count+=1; 
                    Glyph next = itr.next();
               }
               System.out.println("---------------------  Count = "+count+"  ------------------------------");
               Canvas_Practice.count_Words.setText("Count = "+count);
              
    }
       
       void changeBorder(Canvas_Practice cp,Color c1){
           cp.setBorder(BorderFactory.createLineBorder(c1,5));
       }
}
