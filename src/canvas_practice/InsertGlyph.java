package canvas_practice;
public class InsertGlyph extends Command{

    Glyph glyph;
    Custom_Character cc;
    Custom_Rectangle cr;

    public InsertGlyph(Glyph g,Custom_Character cc) {
   this.glyph=g;
   this.cc=cc;
    }
     public InsertGlyph(Glyph g,Custom_Rectangle cc) {
   this.glyph=g;
   this.cr=cc;
    }

    public InsertGlyph(Glyph glyph, Custom_Character cc, Custom_Rectangle cr) {
        this.glyph = glyph;
        this.cc = cc;
        this.cr = cr;
    }
     
     
    
    
    @Override
    void execute(String params) {
        memento=glyph.createMemento();
       if(params.equalsIgnoreCase("space")){
           glyph.addComponent(cr);
       }
       else{
           glyph.addComponent(cc);
       }
    }

    @Override
    void unexecute() {
        glyph.setMemento(memento);
        System.out.println("Undo Called ");
    }
    
}
