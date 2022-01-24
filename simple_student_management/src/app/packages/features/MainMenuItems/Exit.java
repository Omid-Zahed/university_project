package app.packages.features.MainMenuItems;

import app.contract.Feature;
import app.contract.Ui;

import static java.lang.System.exit;

public class Exit implements Feature {
    Ui ui;
    public Exit(Ui ui){
        this.ui=ui;
    }
    @Override
    public String getName() {
        return "exit";
    }

    @Override
    public Boolean Run() {
        this.ui.showMessage("are you sure you want to exit?");
        this.ui.showMessage("press y to confirm");
        String input=this.ui.getInput();
        if(input.equals("y")){
            this.ui.showMessage("goodbye");
            exit(0);
        }else {
            return false;
        }
        return  true;
    }
}
