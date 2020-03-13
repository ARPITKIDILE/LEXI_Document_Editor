package canvas_practice;
public class InsertCharacterCommand extends Command {

    Glyph glyph;
    Custom_Character ch;

    public InsertCharacterCommand(Glyph glyph, Custom_Character ch) {
        this.glyph = glyph;
        this.ch = ch;
    }
    
    
    
    @Override
    void execute(String params) {
        glyph.addComponent(ch);
    }

    @Override
    void unexecute() {
        
    }
    
}
