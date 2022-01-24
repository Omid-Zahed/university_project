package app.packages.features.MainMenuItems;

import app.contract.Feature;
import app.contract.Storage;
import app.contract.Ui;
import app.packages.Data.UserData;

public class RemoveUser implements Feature {
    Ui ui;
    Storage storage;
    public RemoveUser(Ui ui, Storage storage) {
      this.ui=ui;
      this.storage=storage;
    }
    @Override
    public String getName() {
        return "Remove User";
    }

    @Override
    public Boolean Run() {
       this.ui.showMessage("Enter user National to remove");
       String national= this.ui.getInput();
       if ( this.storage.exists(national)){
           this.ui.showMessage("User found");
           this.ui.showMessage("do you want to remove user? (y/n)");
           String answer=this.ui.getInput();
           if (answer.equals("y")){
               this.storage.delete(national);
               this.ui.showMessage("User removed");
           }else {
               this.ui.showMessage("User not removed");
           }
       }else {
           this.ui.showMessage("User not found");
       }
        return  true;
    }
}
