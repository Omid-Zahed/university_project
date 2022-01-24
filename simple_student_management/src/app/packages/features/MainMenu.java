package app.packages.features;

import app.contract.Feature;
import app.contract.Ui;
import app.packages.features.MainMenuItems.AddUser;
import app.packages.features.MainMenuItems.Exit;

public class MainMenu extends Menu {


    public MainMenu(Ui ui, String title, Feature... features) {
        super(ui, title, features);
    }

    @Override
    protected boolean onExitFromMenu() {
        return    new Exit(this.ui).Run();
    }
}
