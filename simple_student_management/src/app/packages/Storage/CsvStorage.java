package app.packages.Storage;

import app.contract.RawData;
import app.contract.Storage;
import app.packages.Data.UserData;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class CsvStorage implements Storage {





    protected String file_path;
    public CsvStorage(String data_path) {
        this.file_path = data_path;
    }

    @Override
    public void store(String key, RawData value) {
        //todo : change to append in file
        try {
            String content  = this.read_file(this.file_path);
            content+=key+","+value.getData()+"\n";
            this.save_file(content,this.file_path);

        }
        catch (FileNotFoundException e){
            throw new RuntimeException("File not found code: 0872");
        } catch (IOException e) {
           throw new RuntimeException("File not found code: 0871");
        }
    }



    @Override
    public void delete(String key) {
        try {
            String content  = "";
            String data=this.read_file(this.file_path);
            String[] array_data=data.split("\n");
            for (String line : array_data) {
                String[] line_data=line.split(",");
                if(line_data[0].equals(key)){
                    continue;
                }
                content+=line+"\n";
            }
            this.save_file(content,this.file_path);

        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found code: 0872121");
        } catch (IOException e) {
            throw new RuntimeException("File not found code: 087721");
        }
    }

    @Override
    public boolean exists(String key) {
        try {

            String data=this.read_file(this.file_path);
            String[] array_data=data.split("\n");
            for (String line : array_data) {
                String[] line_data=line.split(",");
                if(line_data[0].equals(key)){
                  return true;
                }
            }
            return false;

        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found code: 0872121");
        } catch (IOException e) {
            throw new RuntimeException("File not found code: 087721");
        }


    }

    @Override
    public void clear() {
        try {
            this.save_file("",this.file_path);
        } catch (IOException e) {
           throw new RuntimeException("File not found code: 08732");
        }
    }

    @Override
    public RawData get(String key, RawData type) {
        try {

            String data=this.read_file(this.file_path);
            String[] array_data=data.split("\n");
            for (String line : array_data) {
                String[] line_data=line.split(",");
                if(line_data[0].equals(key)){
                  type.setData(line);
                  return  type;
                }
            }
            return null;

        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found code: 0872121");
        } catch (IOException e) {
            throw new RuntimeException("File not found code: 087721");
        }
    }

    @Override
    public RawData[] all(RawData type) {
        try {
           String data= this.read_file(this.file_path);
           String[] array_data=data.split("\n");
           RawData[] result=new RawData[array_data.length];
           for (int i = 0; i < array_data.length; i++) {
               if (array_data[i].trim().equals("")){
                   continue;
               }
                //create new object from type class
               result[i]= (RawData) type.copy();
               result[i].setData(array_data[i]);
           }
           return result;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;

    }

    protected String read_file(String path) throws FileNotFoundException, FileNotFoundException {
        String export="";
        File myObj = new File(path);
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            String text= myReader.nextLine().trim();
            if (text.equals("")){
                continue;
            }
            export+=text+"\n";

        }
        myReader.close();
        return  export;
    }

    protected void save_file(String content,String path) throws IOException, IOException {
        FileWriter myWriter = new FileWriter(path);
        myWriter.write(content);
        myWriter.close();
    }

}
