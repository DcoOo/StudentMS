package alpha.studentms.util;

import java.util.UUID;

/**
 * 
 * @author Administrator
 *
 */
public class UUIDGenerater {

    public static String getUUID(){
        UUID uuid = UUID.randomUUID();

        String a = uuid.toString();
        a = a.toUpperCase();
        a = a.replaceAll("-","");
        return a;
    }
    
    public static void main(String[] args){
    	System.out.println(getUUID());
    	System.out.println(getUUID());
    	System.out.println(getUUID());
    	System.out.println(getUUID());
    	System.out.println(getUUID());
    	System.out.println(getUUID());
    }
}
