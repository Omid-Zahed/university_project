package app;

import app.contract.Feature;
import app.contract.Storage;
import app.contract.Ui;
import app.packages.Storage.CsvStorage;
import app.packages.Ui.Cmd;
import app.packages.features.MainMenu;
import app.packages.features.MainMenuItems.*;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Core {

    protected Storage storage;
    protected Ui ui;
    protected Feature feature;


    protected MainMenu mainMenu;
    public void init(){
        this.init_configs();
        this.init_dependencies();
        this.init_menu();
    }

    protected void init_configs(){

        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        Config.setOption("storage_path",s+"/src/users.csv");
    }
    protected void init_menu(){
        Feature addUser    = new AddUser(this.ui,this.storage);
        Feature editUser   = new EditUser(this.ui,this.storage);
        Feature removeUser = new RemoveUser(this.ui,this.storage);
        Feature list       = new UsersTable(this.ui,this.storage);
        Feature exit       = new Exit(this.ui);

        this.mainMenu = new MainMenu(this.ui,"Main menu : ", addUser,editUser,removeUser,list,exit);
    }
    protected void init_dependencies(){
        this.ui=new Cmd();
        this.storage=new CsvStorage(Config.getOption("storage_path"));
    }
    public void run(){
        this.mainMenu.Run();
    }



}
