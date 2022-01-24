package app.contract;

public interface RawData extends Cloneable {

   String data = "";
   public String getData();
   public void setData(String data);
   public Object copy()throws CloneNotSupportedException;

}
