import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

class hashMaker{
    MessageDigest digest;

    public hashMaker(){
        try{
            digest = MessageDigest.getInstance("SHA-256");
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public String hash(int i){
        byte[] bytes = (i + "").getBytes(StandardCharsets.UTF_16);

        return bytesToString(bytes);
    }

    public String bytesToString(byte[] bytes){
        StringBuffer sb = new StringBuffer();
        for(int i = 1; i < bytes.length; i++)
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        return sb.toString();
    }
}