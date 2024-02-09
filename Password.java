class Password {
  public static String hash(String password) {
    String hashed = enco(password);
    return hashed;
  }
  
  public static boolean check(String password, String hashed) {
    boolean isvalid = hashed.equals(enco(password)); 
    return isvalid;
  }

  public static String enco(String password) {
    String hashed = "";
    String tempPW = "";
    
    for (int i = 0; i < (1000 - password.length()); i++) {
      tempPW += (char)47;
    }
    
    tempPW += password;

    hashed = Encrypt.asciiMult(tempPW, 5);
    hashed = Encrypt.invertBinary(hashed);
    return hashed;
  }
}