import java.io.Console;

class Main {
  public static void main(String[] args) {
    menu();
  }

  static void menu(){
    String line = "\n\u001B[33m------------------------------------------------------\u001B[37m\n";
    int status = 0;
    String filename = "";
    String file = "";
    int choice = 0;
    System.out.println("\n\u001B[31mThis program can only save 1 encrypted file at a time.\nPlease export \u001B[36mEncrypted.encp\u001B[31m, \u001B[36mSeed.txt\u001B[31m, and \u001B[36mpw\u001B[31m before encrypting your second file.\u001B[37m");
    while(status != 3){
      if(status == 0){
        System.out.println("\n");
        System.out.println("Choose Action: ");
        System.out.println("1) Encrypt");
        System.out.println("2) Decrypt");
        System.out.println("3) Exit");
        System.out.print("~ ");
        status = Integer.parseInt(Input.readString());
      }
      else if(status == 1){
        System.out.println("\n");
        System.out.println("Import a file (1) or Write text (2)");
        choice = Integer.parseInt(Input.readString());
        if(choice == 1){
          System.out.print("Enter your file name: ");
          filename = Input.readString();
          file = Input.readFile(filename);
        }
        else if(choice == 2){
          System.out.print("Enter texts: ");
          file = Input.readString();
        }
        if(choice == 1 || choice == 2){
          System.out.println("\n");
          System.out.println("Original:");
          System.out.println(file);
          System.out.println(line);
          finalEncrypt(file);
          System.out.println(line);
          System.out.println("\u001B[31mEncrypted File saved as \u001B[36mEncrypted.encp\u001B[37m");
        }
        choice = 0;
        status = 0;
      }
      else if(status == 2){
        System.out.println("\n");
        System.out.print("Enter your file name: ");
        filename = Input.readString();
        file = Input.readFile(filename);
        System.out.println("\n");
        System.out.println("Original:");
        System.out.println(file);
        System.out.println(line);
        file = Input.readFile(filename);
        decrypt(file);
        System.out.println(line);
        System.out.println("\u001B[31mDecrypted File saved as \u001B[36mDecrypted.txt\u001B[37m");
        status = 0;
      }
      else{
        status = 0;
      }
    }
  }

  public static Console cons = System.console();

  static void finalEncrypt(String text){
    //Actual Encryption
    String bld = text;
    bld = Encrypt.substitution(bld, 'e', (char)27);
    bld = Encrypt.substitution(bld, 'o', (char)31);
    bld = Encrypt.invertBinary(bld);
    bld = Encrypt.scramble(bld);
    bld = Encrypt.swapTwo(bld);
    bld = Encrypt.asciiAdd(bld, 2);
    bld = Encrypt.asciiMult(bld, 2);
    bld = Encrypt.asciiSub(bld, 4);

    //Seed Encryption
    String seed = Input.readFile("Seed.txt");
    int seedNum = Integer.parseInt(seed);
    seedNum += 47;
    seed = String.valueOf(seedNum);
    seed = Encrypt.asciiMult(seed, 254);
    Input.writeFile("Seed.txt", seed);
    
    //Password Encryption
    char[] passwordChar = cons.readPassword("Create a Password: ");
    String password = "";
    for(int i = 0; i < passwordChar.length; i++){
      password += passwordChar[i];
    }
    Input.writeFile("pw", Password.hash(password));
    if(password != ""){
      bld += '.';
    }
    else{
      bld += '+';
    }
    System.out.println("\n\u001B[33m------------------------------------------------------\u001B[37m\n");
    
    //Output Encrypted String
    System.out.println("Encrypted:");
    System.out.println(bld);
    Input.writeFile("Encrypted.encp", bld);
    
  }
  
  static void decrypt(String text){
    //Password Authentication
    String pw = Input.readFile("pw");
    if(text.substring(text.length()-1).equals(".")){
      char[] passwordChar = cons.readPassword("Enter your Password: ");
      String password = "";
      for(int i = 0; i < passwordChar.length; i++){
        password += passwordChar[i];
      }
      if(Password.check(password, pw)){
        text = text.substring(0, text.length() - 1);
      }
      else{
        System.out.println("\u001B[31mWrong Password!\u001B[37m");
        return;
      }
      System.out.println("\n\u001B[33m------------------------------------------------------\u001B[37m\n");
    }
    else{
      text = text.substring(0, text.length() - 1);
    }
    
    //Seed Decryption
    String seed = Input.readFile("Seed.txt");
    seed = Encrypt.asciiDiv(seed, 254);
    int seedNum = Integer.parseInt(seed);
    seedNum -= 47;
    
    //Actual Decryption
    String bld = text;
    bld = Encrypt.asciiAdd(bld, 4);
    bld = Encrypt.asciiDiv(bld, 2);
    bld = Encrypt.asciiSub(bld, 2);
    bld = Encrypt.swapTwo(bld);
    bld = Encrypt.unscramble(bld, seedNum);
    bld = Encrypt.invertBinary(bld);
    bld = Encrypt.substitution(bld, (char)31, 'o');
    bld = Encrypt.substitution(bld, (char)27, 'e');

    //Output Decrypted String
    System.out.println("Decrypted:");
    System.out.println(bld);
    Input.writeFile("Decrypted.txt", bld);
  }
}