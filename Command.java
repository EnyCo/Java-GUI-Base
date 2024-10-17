public abstract class Command {
    public void execute(){
        System.out.println("default abstract execute ran");
    }
    
    public void execute(int param){
        System.out.println("in param abstract execute ran");
    }
}

