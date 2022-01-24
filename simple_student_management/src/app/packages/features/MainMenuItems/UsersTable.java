package app.packages.features.MainMenuItems;

import app.contract.Feature;
import app.contract.RawData;
import app.contract.Storage;
import app.contract.Ui;
import app.packages.Data.UserData;

public class UsersTable implements Feature {

    protected Ui ui;
    Integer sort_column=1;
    String  sort_data_type="number";
    String  sort_type="asc";
    protected Storage storage;
    public UsersTable(Ui ui, Storage storage) {
        this.ui = ui;
        this.storage = storage;
    }

    @Override
    public String getName() {
        return "Users List";
    }

    @Override
    public Boolean Run() {
        while (true) {
            //for users list
            UserData[] users=this.getUsers();
            Integer padding=20;

            //display table
            this.printHeader(padding);
            this.printBody(users,padding);

            this.ui.showMessage("for exit inter 0");
            this.ui.showMessage("for sort inter (column number : type sort : data type ) for example : 1:asc:number or 2:desc:string");
            String input =this.ui.getInput();
            if (input.equals("0")) {
                break;
            }
            else{
                String[] input_split=input.split(":");
                if (input_split.length==3) {
                    try {
                        this.sort_column=Integer.parseInt(input_split[0]);
                        this.sort_type=input_split[1];
                        this.sort_data_type=input_split[2];
                    }catch (Exception e) {
                        this.ui.showError("invalid input");
                    }

                }
            }

        }

        return true;
    }


    protected void printHeader(Integer padding) {
        String[] header={"National","Name","Field","GPA"};

        String header_content="";
        for (int i = 0; i < header.length; i++) {
            header_content+=header[i];
            for (int j = 0; j < (padding-header[i].length()); j++) {
                header_content+=" ";
            }
            header_content+="|";
        }
        this.ui.showMessage(header_content);
        this.print_table_line(padding*4);

    }
    protected void printBody(UserData[] list,Integer padding) {

        for (int i = 0; i < list.length; i++) {
            if (list[i]==null) {
                continue;
            }
            String body_content="";
            Integer padding_Name=padding- list[i].Name.length();
            Integer padding_National=padding- list[i].National.length();
            Integer padding_GPA=padding- list[i].GPA.length();
            Integer padding_Field=padding- list[i].Field.length();

            body_content+=list[i].National;
            body_content=this.addPaddingToString(body_content,padding_National);
            body_content+="|";
            body_content+=list[i].Name;
            body_content=this.addPaddingToString(body_content,padding_Name);
            body_content+="|";
            body_content+=list[i].Field;
            body_content=this.addPaddingToString(body_content,padding_Field);
            body_content+="|";
            body_content+=list[i].GPA;
            body_content=this.addPaddingToString(body_content,padding_GPA);
            body_content+="|";
            this.ui.showMessage(body_content);
            this.print_table_line(padding*4);
        }
    }

    protected String addPaddingToString(String str,Integer padding) {
        for (int i = 0; i < padding; i++) {
            str+=" ";
        }
        return  str;
    }
    protected UserData[] getUsers() {
        RawData[] data=this.storage.all(new UserData());
        //cast RawData to UserData
        UserData[] users=new UserData[data.length];
        for (int i = 0; i < data.length; i++) {
            users[i]=(UserData)data[i];
        }
        users=this.applySort(users);
        return users;
    }
    protected void  print_table_line(Integer length) {
        length+=4;
        String line="";
        for (int i = 0; i < length; i++) {
            line+="-";
        }
        this.ui.showMessage(line);
    }
    protected UserData[] applySort(UserData[] list) {
        if (this.sort_column!=null && this.sort_type!=null) {
            try {
                if (this.sort_type.equals("asc")) {
                    return  this.AcsSortUsersList(list);
                }else if (this.sort_type.equals("desc")) {
                    return  this.DescSortUsersList(list);
                }
            }catch (Exception e) {
                this.ui.showError("sort type is not valid");

            }

        }
        return  list;
    }

    private String getDataSelectedForSortFromUserObject(UserData user) {
        if (this.sort_column==1) {
            return user.National;
        }
        else if (this.sort_column==2) {
            return user.Name;
        }
        else if (this.sort_column==3) {
            return user.Field;
        }
        else if (this.sort_column==4) {
            return user.GPA;
        }
        return  null;
    }
    private UserData[] AcsSortUsersList(UserData[] list) {
        for (int i = 0; i < list.length; i++) {
            for (int j = i + 1; j < list.length; j++) {
                if (this.sort_data_type.equals("string")) {
                    if (this.getDataSelectedForSortFromUserObject(list[i]).compareToIgnoreCase(this.getDataSelectedForSortFromUserObject(list[j])) > 0) {
                        UserData t = list[i];
                        list[i] = list[j];
                        list[j] = t;
                    }
                }else if (this.sort_data_type.equals("number")) {

                    if (Float.parseFloat(this.getDataSelectedForSortFromUserObject(list[i]))>Float.parseFloat(this.getDataSelectedForSortFromUserObject(list[j]))) {
                        UserData t = list[i];
                        list[i] = list[j];
                        list[j] = t;
                    }
                }

            }

        }
        return list;
    }
    private UserData[] DescSortUsersList(UserData[] list) {
        for (int i = 0; i < list.length; i++) {
            for (int j = i + 1; j < list.length; j++) {
                if (this.sort_data_type.equals("string")) {
                    if (this.getDataSelectedForSortFromUserObject(list[i]).compareToIgnoreCase(this.getDataSelectedForSortFromUserObject(list[j])) < 0) {
                        UserData t = list[i];
                        list[i] = list[j];
                        list[j] = t;
                    }
                }
                else if (this.sort_data_type.equals("number")) {

                    if (Float.parseFloat(this.getDataSelectedForSortFromUserObject(list[i]))<Float.parseFloat(this.getDataSelectedForSortFromUserObject(list[j]))) {
                        UserData t = list[i];
                        list[i] = list[j];
                        list[j] = t;
                    }
                }
            }

        }
        return list;
    }
}
