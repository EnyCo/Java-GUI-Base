public class ClockThread implements Runnable
{
    private int counter = 0;

    public int getCounter(){
        return counter;
    }

    public void run(){
        while (true){
            try{
                Thread.sleep(Main.getThreadSleep());
            } catch(Exception e){
                System.out.println("Oops time isn't working");
            }
            counter += Main.getThreadSleep();

            if (counter % 500 == 0 && Main.getActiveTextBox() != null) {//textbox counter stuff
                Main.getActiveTextBox().setSeeBar(!Main.getActiveTextBox().getSeeBar());
            }
        }
    }
}