class Encrypt{
  public static String asciiAdd(String text, int addFactor){
    String bld = "";
    int ascii = 0;
    for(int i = 0; i < text.length(); i++){
      ascii = (int)text.charAt(i);
      ascii += addFactor;
      bld += (char)ascii;
    }
    return bld;
  }

  public static String asciiSub(String text, int minFactor){
    String bld = "";
    int ascii = 0;
    for(int i = 0; i < text.length(); i++){
      ascii = (int)text.charAt(i);
      ascii -= minFactor;
      bld += (char)ascii;
    }
    return bld;
  }

  public static String asciiMult(String text, int mulFactor){
    String bld = "";
    int ascii = 0;
    for(int i = 0; i < text.length(); i++){
      ascii = (int)text.charAt(i);
      ascii *= mulFactor;
      bld += (char)ascii;
    }
    return bld;
  }

  public static String asciiDiv(String text, int divFactor){
    String bld = "";
    int ascii = 0;
    for(int i = 0; i < text.length(); i++){
      ascii = (int)text.charAt(i);
      ascii /= divFactor;
      bld += (char)ascii;
    }
    return bld;
  }

  public static String scramble(String text){
    String bld = "";
    int seed = (int)(Math.random()*20);
    Input.writeFile("Seed.txt", String.valueOf(seed));
    int ascii = 0;
    for(int i = 0; i < text.length(); i++){
      ascii = (int)text.charAt(i);
      if(seed != 0){
        ascii = (ascii * seed) - (2*seed);
      }
      bld += (char)ascii;
    }
    return bld;
  }
  
  public static String unscramble(String text, int seed){
    String bld = "";
    int ascii = 0;
    for(int i = 0; i < text.length(); i++){
      ascii = (int)text.charAt(i);
      if(seed != 0){
        ascii = (ascii + (2*seed))/seed;
      }
      bld += (char)ascii;
    }
    return bld;
  }

  public static String swapTwo(String text){
    String bld = "";
    for(int i = 0; i < text.length()-1; i+=2){
      bld += text.substring(i+1, i+2);
      bld += text.substring(i, i+1);
    }
    if(text.length()%2 != 0){
      bld += text.substring(text.length()-1);
    }
    return bld;
  }

  public static String substitution(String text, char pick, char replace){
    String bld = "";
    int ascii = 0;
    for(int i = 0; i < text.length(); i++){
      ascii = (int)text.charAt(i);
      if(ascii == (int)pick){
        ascii = (int)replace;
      }
      bld += (char)ascii;
    }
    return bld;
  }

  public static String invertBinary(String text){
    String bld = "";
    String binTemp = "";
    String bin = "";
    String binNew = "";
    int ascii = 0;
    for(int i = 0; i < text.length(); i++){
      binTemp = Integer.toBinaryString(text.charAt(i));
      for(int k = 0; k < (8 - binTemp.length()); k++){
        bin += "0";
      }
      bin += binTemp;
      for(int j = 0; j < bin.length(); j++){
        if(bin.charAt(j) == '1'){
          binNew += '0';
        }
        else if(bin.charAt(j) == '0'){
          binNew += '1';
        }
      }
      ascii = Integer.parseInt(binNew, 2);
      binNew = "";
      binTemp = "";
      bin = "";
      bld += (char)ascii;
    }
    return bld;
  }
}