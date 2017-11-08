package alpha.studentms.util;

import java.util.UUID;

/**
<<<<<<< HEAD
 * UUID generater
 * Created by Administrator on 2016/11/27.
=======
 * 
 * @author Administrator
 *
>>>>>>> f43d606c546a55095ddbd2b0dd7c9456ff3d6eac
 */
public class UUIDGenerater {

    public static String getUUID(){
        UUID uuid = UUID.randomUUID();

        String a = uuid.toString();
        a = a.toUpperCase();
        a = a.replaceAll("-","");
        return a;
    }
}
