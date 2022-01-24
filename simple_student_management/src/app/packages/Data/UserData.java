package app.packages.Data;

import app.contract.RawData;

public class UserData implements RawData {

    public String Name;
    public String National;
    public String GPA;
    public String Field;


    @Override
    public String getData() {
        if (National=="0" || National.trim()==""){throw new RuntimeException("user national is empty or 0");}
        this.Name.replace(",", " ");
        this.National.replace(",", " ");
        this.GPA.replace(",", " ");
        this.Field.replace(",", " ");
        return this.Name+","+this.National+","+this.GPA+","+this.Field;
    }

    @Override
    public void setData(String data) {

        String[] arr = data.split(",");
        if (arr.length!=5){return;}
        this.Name = arr[1];
        this.National = arr[2];
        this.GPA = arr[3];
        this.Field = arr[4];
    }


    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    public Object copy() throws CloneNotSupportedException {
        return super.clone();
    }
}
