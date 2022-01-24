package app.contract;

import app.packages.Data.UserData;

public interface Ui {

    public void showMessage(String message);
    public void showError(String error);
    public void showSuccess(String success);
    public String getInput();
    public void line();
    public void DisplayUserInformation(UserData userData);
}
