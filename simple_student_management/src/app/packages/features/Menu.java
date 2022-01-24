package app.packages.features;

import app.contract.Feature;
import app.contract.Ui;
import app.packages.features.MainMenuItems.Exit;

public class Menu implements Feature {

    protected  String title;
    protected  Feature[] features;
    protected  Ui ui;
    Menu(Ui ui,String title, Feature ... features) {
        this.title = title;
        this.features = features;
        this.ui = ui;
    }


    @Override
    public String getName() {
        return "Main Menu";
    }

    @Override
    public Boolean Run() {

        while (true) {
            this.ui.line();
            this.ui.showMessage(this.title);
            this.ui.line();
            this.ui.showMessage("Select an option");
            this.ui.showMessage("if you want to exit inter 0");
            for (int i = 0; i < this.features.length; i++) {
                this.ui.showMessage((i+1) + ": " + this.features[i].getName());
            }
            int option = Integer.parseInt(this.ui.getInput());
            if (option ==0){
               if ( this.onExitFromMenu()==true){
                   break;
               }

            }
            if (option < 0 || option >= this.features.length+1) {
                this.ui.showError("Invalid option");
            }
            if (option > 0 && option <= this.features.length) {
                this.features[option-1].Run();
            }
        }
        return true;
    }

    protected boolean onExitFromMenu(){
     return   true;
    }
}
