package in.pathri.screenoff;

import in.pathri.screenoff.loghelper.LogHelper;
import in.pathri.screenoff.loghelper.LogHelper.Level;
import in.pathri.screenoff.roothelper.InputStreamHandler;

import java.io.DataOutputStream;

public class ScreenOffHelper {
	private static String LOG = "in.pathri.screenoff.ScreenOffHelper";

	public static void screenOff(String device) {
		LogHelper.customLog(LOG, Level.DEBUG, "Into Screen Off");
		String powerDown = "sendevent " + device + " 1 116 1\n";
		String powerUp = "sendevent " + device + " 1 116 0\n";
		String powerNull = "sendevent " + device + " 0 0 0\n";
		try {			
			Process su = Runtime.getRuntime().exec("su");
			InputStreamHandler in = new InputStreamHandler(su.getInputStream(), false);
			InputStreamHandler er = new InputStreamHandler(su.getErrorStream(), false);
			DataOutputStream outputStream = new DataOutputStream(su.getOutputStream());
//			outputStream.writeBytes("sendevent /dev/input/event0 1 116 1\n");
			outputStream.writeBytes(powerDown);
			outputStream.flush();
//			outputStream.writeBytes("sendevent /dev/input/event0 0 0 0\n");
			outputStream.writeBytes(powerNull);
			outputStream.flush();
//			outputStream.writeBytes("sendevent /dev/input/event0 1 116 0\n");
			outputStream.writeBytes(powerUp);
			outputStream.flush();
//			outputStream.writeBytes("sendevent /dev/input/event0 0 0 0\n");
			outputStream.writeBytes(powerNull);
			outputStream.flush();
			outputStream.writeBytes("exit\n");
			outputStream.flush();
			outputStream.writeBytes("exit\n");
			outputStream.flush();
			su.waitFor();
			LogHelper.customLog(LOG, Level.INFO, in.getOutput());
			LogHelper.customLog(LOG, Level.ERROR, er.getOutput());			
		} catch (Exception e) {
			// e.printStackTrace();
			LogHelper.customLog(LOG, Level.ERROR, e.getMessage());
		}
		LogHelper.customLog(LOG, Level.DEBUG, "Completed Screen Off");
	}

}
