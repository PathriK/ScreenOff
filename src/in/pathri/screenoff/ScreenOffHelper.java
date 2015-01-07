package in.pathri.screenoff;

import in.pathri.screenoff.loghelper.LogHelper;
import in.pathri.screenoff.loghelper.LogHelper.Level;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ScreenOffHelper {
	private static String LOG = "in.pathri.screenoff.assist.ScreenOffHelper";

	public static void screenOff() {
		LogHelper.customLog(LOG, Level.DEBUG, "Into Screen Off");
		try {
			Process su = Runtime.getRuntime().exec("su");
			InputStreamHandler in = new InputStreamHandler(su.getInputStream(), false);
			InputStreamHandler er = new InputStreamHandler(su.getErrorStream(), false);
			DataOutputStream outputStream = new DataOutputStream(su.getOutputStream());
			outputStream.writeBytes("sendevent /dev/input/event0 1 116 1\n");
			outputStream.flush();
			outputStream.writeBytes("sendevent /dev/input/event0 0 0 0\n");
			outputStream.flush();
			outputStream.writeBytes("sendevent /dev/input/event0 1 116 0\n");
			outputStream.flush();
			outputStream.writeBytes("sendevent /dev/input/event0 0 0 0\n");
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

	private static class InputStreamHandler extends Thread {
		private final InputStream stream;
		private final boolean sink;
		StringBuffer output;

		public String getOutput() {
			return output.toString();
		}

		InputStreamHandler(InputStream stream, boolean sink) {
			this.sink = sink;
			this.stream = stream;
			start();
		}

		@Override
		public void run() {
			try {
				if (sink) {
					while (stream.read() != -1) {
					}
				} else {
					output = new StringBuffer();
					BufferedReader b = new BufferedReader(new InputStreamReader(stream));
					String s;
					while ((s = b.readLine()) != null) {
						output.append(s);
					}
				}
			} catch (IOException ignored) {
				// System.out.println(ignored);
				LogHelper.customLog(LOG, Level.ERROR, ignored.getMessage());
			}
		}
	}

}
