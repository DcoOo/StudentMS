package alpha.studentms.util;

import java.security.MessageDigest;

/**
 * 加密工具类
 * 
 * @author joker
 *
 */
public class EncryptUtils {

	/**
	 * 把用户密码通过MD5加密形成新的加密后的字符串
	 * @param password 用户密码
	 * @return 新的加密后的字符串
	 */
	public static String encoderByMd5(String password) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] strTemp = password.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(EncryptUtils.encoderByMd5("HAPPY"));
	}
}
