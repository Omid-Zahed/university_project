import app.Core;

public class Main {

    public static void main(String[] args) {
           try{
               Core App= new Core();
               App.init();
               App.run();
           }
           catch (Exception e){
              System.err.println("sorry something went wrong");
              System.err.println("Please try again");
              System.err.println("Error: "+e.getMessage());
           }
            main(args);

    }


}
