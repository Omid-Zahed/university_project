package app.packages.features.MainMenuItems;

import app.contract.Feature;
import app.contract.Storage;
import app.contract.Ui;
import app.packages.Data.UserData;

public class EditUser implements Feature {
    Ui ui;
    Storage storage;
    public EditUser(Ui ui, Storage storage) {
      this.ui=ui;
      this.storage=storage;
    }
    @Override
    public String getName() {
        return "Edit User";
    }

    @Override
    public Boolean Run() {
        this.ui.showMessage("Edit User");
        this.ui.line();
        while (true) {
            this.ui.showMessage("for exit enter 0 ");
            this.ui.showMessage("please enter the user national id");
            String national=this.ui.getInput();
            if (national.trim().equals("0")){
                break;}
            if (this.storage.exists(national)){
                UserData user=new UserData();
                user= (UserData) this.storage.get(national,user);
                this.ui.DisplayUserInformation(user);
                this.ui.showMessage("do you want to edit this user?(y/n)");
                if (this.ui.getInput().trim().equals("y")){
                    this.ui.showMessage("please enter the new user Name - string");
                    user.Name=(this.ui.getInput());
                    this.ui.showMessage("please enter the new user GPA - double");
                    user.GPA=(this.ui.getInput());
                    this.ui.showMessage("please enter the new user Field - string");
                    user.Field=(this.ui.getInput());
                    this.ui.line();
                    this.ui.showMessage("the new user information is :");
                    this.ui.DisplayUserInformation(user);
                    this.ui.showMessage("do you want to save this user?(y/n)");
                    if (this.ui.getInput().trim().equals("y")){
                        this.storage.delete(national);
                        this.storage.store(user.National,user);
                        this.ui.showMessage("the user information is saved");
                    }
                    else {
                        this.ui.showMessage("the user information is not saved");
                    }

                }else {
                    continue;
                }
            }
            else{
                this.ui.showMessage("user not found");
                this.ui.line();
            }
        }


        return  true;
    }
}
