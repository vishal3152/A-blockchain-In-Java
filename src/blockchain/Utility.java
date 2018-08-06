package blockchain;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.security.MessageDigest;

public class Utility {

    /**
     * @param block
     * @return
     */
    public static String getSHA256Hash(Block block) {

        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutput out = new ObjectOutputStream(bos)) {

            out.writeObject(block);
            out.flush();

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digestArray = md.digest(bos.toByteArray());

            StringBuffer sb = new StringBuffer();
            for (byte byt : digestArray) {
                sb.append(Integer.toString((byt & 0xff) + 0x100, 16).substring(1));
            }

            //System.out.println("Hex format : " + sb.toString());
            return sb.toString();
            // return new String(digestArray,StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

}
