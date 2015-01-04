package in.pathri.screenoff.loghelper;

import in.pathri.screenoff.BuildConfig;
import android.os.Build;
import android.util.Log;

public class LogHelper {
	public enum Level {
		ERROR, DEBUG, INFO
	}

	public static void customLog(String LOG, Level logLevel, String msg) {
		if (BuildConfig.DEBUG) {
			switch (logLevel) {
			case ERROR:
				Log.e(LOG, msg);
				break;
			case DEBUG:
				Log.d(LOG, msg);
				break;
			case INFO:
				Log.i(LOG, msg);
				break;
			default:
				Log.w(LOG, msg);
				break;
			}
		}
	}
}
