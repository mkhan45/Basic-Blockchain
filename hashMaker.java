import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

class hashMaker {
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

        return digest.digest(bytes).toString();
    }
}