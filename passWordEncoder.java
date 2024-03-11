package publisher.data;

import java.security.MessageDigest;

public class passWordEncoder{

   private static final char digits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
};

  private static String byteArrayToHexString(byte[] b){
    StringBuffer hexString = new StringBuffer(b.length);
    for (int i = 0; i < b.length; i++){
      hexString.append(digits[(b[i] & 0xf0) >> 4]);
      hexString.append(digits[b[i] & 0x0f]);
    }
    return hexString.toString();
  }

  public static String encode(String password){
    try{
      MessageDigest md = MessageDigest.getInstance("SHA-256");
      md.update(password.getBytes("UTF-8"));
      byte[] digest = md.digest();
      return byteArrayToHexString(digest);
    }catch(Exception e){
    }
     throw new RuntimeException(e);
  }
}

public static void main(String[] args){
  String password = args[0];
  System.out.println(password + " -> " + passWordEncoder.encode(password));
}
}