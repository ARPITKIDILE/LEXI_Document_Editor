package canvas_practice;
abstract class  Command {
    
    Memento memento;
    abstract void execute(String params);
    
   abstract void unexecute();
    
   Memento saveMemento(){
       //
       return null;
   }
}
