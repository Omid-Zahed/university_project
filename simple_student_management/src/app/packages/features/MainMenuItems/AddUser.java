package app.packages.features.MainMenuItems;

import app.contract.Feature;
import app.contract.Storage;
import app.contract.Ui;
import app.packages.Data.UserData;

public class AddUser implements Feature {
    Ui ui;
    Storage storage;
    public AddUser(Ui ui, Storage storage) {
      this.ui=ui;
      this.storage=storage;
    }
    @Override
    public String getName() {
        return "Add User";
    }

    @Override
    public Boolean Run() {
        this.ui.showMessage("Add User");
        this.ui.line();
        this.ui.showMessage("please enter the valid data ");
        UserData userData=new UserData();


        this.ui.showMessage("Enter User Name - string");
        userData.Name=this.ui.getInput();
        this.ui.showMessage("Enter User National Code - integer");
        userData.National=this.ui.getInput();
        //Grade point average
        this.ui.showMessage("Enter User Grade Point Average - double");
        userData.GPA=this.ui.getInput();
        //field
        this.ui.showMessage("Enter User Field - string");
        userData.Field=this.ui.getInput();
        this.ui.line();
        //print result
        this.ui.showMessage("User Name : "+userData.Name+"\n"+"User National Code : "+userData.National+"\n"+"User Grade Point Average : "+userData.GPA+"\n"+"User Field : "+userData.Field);
        this.ui.line();
        //accept
        this.ui.showMessage("Do you want to accept this data? (y/n)");
        this.ui.line();
        String accept=this.ui.getInput();
        if(accept.equals("y")){
            if (this.storage.exists(userData.National)){
                this.ui.showMessage("User already exists");
            }else{
                this.storage.store(userData.National,userData);
                this.ui.showMessage("User Added");
            }
        }
        else{
            this.ui.showMessage("User Not Added");
        }







        return  true;
    }
}
