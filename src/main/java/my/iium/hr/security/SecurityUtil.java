package my.iium.hr.security;

import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;

//CODE SANITIZATION.
public class SecurityUtil {

	private SecurityUtil() {
		throw new IllegalStateException("Utility class");
	}

	/**
	 * Remove escape characters like Html/Js scripts from input if present
	 * 
	 * @param str Input string
	 * @return sanitize string
	 */

	public static String cleanIt(String str) {
		return Jsoup.clean(str, Safelist.relaxed());
	}

}
