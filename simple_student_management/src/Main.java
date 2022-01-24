import app.Core;

public class Main {

    public static void main(String[] args) {
       Core App= new Core();
       App.init();
       while (true) {
           App.run();
           try{}
           catch (Exception e){
              System.err.println("sorry something went wrong");
              System.err.println("Please try again");
              System.err.println("Error: "+e.getMessage());
           }
       }


    }


}
