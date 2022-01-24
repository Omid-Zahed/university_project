package app.packages.Ui;

import app.contract.Ui;
import app.packages.Data.UserData;

import java.util.Scanner;

public class Cmd implements Ui {
    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void showError(String error) {
        System.err.println(error);
    }

    @Override
    public void showSuccess(String success) {
        System.out.println("success : "+success);
    }

    @Override
    public String getInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    @Override
    public void line() {
        System.out.println("----------------------------------------");
    }

    @Override
    public void DisplayUserInformation(UserData userData) {
       this.line();
       System.out.println("| Name     : "+userData.Name);
        this.line();
       System.out.println("| National : "+userData.National);
        this.line();
       System.out.println("| GPA      : "+userData.GPA);
        this.line();
       System.out.println("| Field    : "+userData.Field);
        this.line();

    }
}
