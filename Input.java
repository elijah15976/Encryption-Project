import java.util.Scanner;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

class Input{
  public static Scanner sc = new Scanner(System.in);
  
  public static String readString(){
    return sc.nextLine();
  }
  
  public static String readFile(String fn){
    String file = "";
    try{
      file = new String(Files.readAllBytes(Paths.get(fn)), StandardCharsets.UTF_8);
    }catch(Exception e){

    }
    return  file;
  }

  public static void writeFile(String file, String data) {
        try {
            Files.write(Paths.get(file), data.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}