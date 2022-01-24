import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

class main{
    public static void main(String args[]) {
    App app = new App(new Storage(),new Calculator());
    app.run();
}
        }

class  App {
        protected Storage storage;
        protected Calculator calculator;
        protected Scanner scanner;
        public App(Storage storage,Calculator calculator){
                this.storage=storage;
                this.calculator=calculator;
                this.scanner=new Scanner(System.in);
        }
       public void run(){
                try {
                        print("welcome to java ;)");
                        print("please enter your shape path : ");
                        String path= this.scanner.nextLine();
                        print("app is fetching your shape from : "+path);
                        List list_files=storage.get_list_files_from_path(path);
                        print("find "+list_files.stream().count()+" files");
                        print("----------------------");
                        AtomicInteger ordinal = new AtomicInteger(0);
                        list_files.forEach((data)-> {print("# "+ Integer.toString(ordinal.getAndIncrement())+" | "+ data);});
                        print("----------------------");
                        print("please enter your shape number : ");
                        Integer number = Integer.valueOf(this.scanner.nextLine());
                        print("----------------------");
                        print("you choose : "+list_files.get(number));
                        print("----------------------");
                        print("please enter your Scale (number): ");
                        Integer scale =  Integer.valueOf(this.scanner.nextLine());
                        print("----------------------");
                        print("please enter export file name :  ");
                        String export_file_name=path+"/"+this.scanner.nextLine();
                        print("------------");
                        print("target file : "+list_files.get(number));
                        print("scale  : "+Integer.toString(scale));
                        print("export file : "+export_file_name);
                        print("do you accept ? (y/n)");
                        String accept=this.scanner.nextLine();
                        if (accept.equals("y")){
                                print("processing...");
                                this.process(list_files.get(number).toString(),scale,export_file_name);
                                print("done");
                                print("insert any key to continue");
                                this.scanner.nextLine();
                                run();
                        }else {run();}

                }catch (Exception e){
                        System.err.println("error : "+e.getMessage());
                        System.err.println("run again");
                        System.err.println("----------------------");

                       run();
                }





       }
       protected void process(String main_file,Integer scale,String export_file) throws IOException {
                String text=storage.read_file(main_file);
                String export_text= calculator.calculate(text,scale);
                storage.save_file(export_text,export_file);
       }
       protected void print(String text){
                System.out.println(text);
       }
}
class Storage {

        public String read_file(String path) throws FileNotFoundException {
                String export="";
                File myObj = new File(path);
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                        export+= myReader.nextLine();
                        export+="\n";
                }
                myReader.close();
                return  export;
        }

        protected void save_file(String content,String path) throws IOException {

                FileWriter myWriter = new FileWriter(path);
                myWriter.write(content);
                myWriter.close();
        }

        public List get_list_files_from_path(String path){
                File folder = new File(path);
                File[] listOfFiles = folder.listFiles();
                List<String> listOfFilesString = new ArrayList<String>();
                for (int i = 0; i < listOfFiles.length; i++) {
                        if (listOfFiles[i].isFile()) {
                                listOfFilesString.add(listOfFiles[i].getPath());
                        }
                }
                return  listOfFilesString;
        }
}
class Calculator{

        public String calculate(String text,int Scale){
               String export="";
               String[] lines=  text.split("\n");
               //scale y
                for (int i = 0; i < lines.length; i++) {
                            for (int k = 0; k < Scale; k++) {
                            export+=lines[i]+"\n";
                    }
                }

                //scale x
                lines=export.split("\n");
                export="";
                for (int i = 0; i < lines.length; i++) {

                        String[] point=lines[i].split("");
                        for (int j = 0; j < point.length; j++) {
                                for (int k = 0; k < Scale; k++) {
                                        export+=point[j];
                                }
                        }

                        export+="\n";
                }

             return  export;
        }

}