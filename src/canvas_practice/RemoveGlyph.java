package canvas_practice;
public class RemoveGlyph extends Command  {

    Glyph glyph;

    public RemoveGlyph(Glyph glyph) {
        this.glyph = glyph;
    }    
    @Override
    void execute(String params) {
        
        if(glyph.getSize()==0){
        }
        else{
            memento=glyph.createMemento();
            glyph.removeComponent();
        }
    }

    @Override
    void unexecute() {
        glyph.setMemento(memento);
        System.out.println("Redo Called");
    }
    
}
