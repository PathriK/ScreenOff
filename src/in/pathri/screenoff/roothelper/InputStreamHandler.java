package in.pathri.screenoff.roothelper;

import in.pathri.screenoff.loghelper.LogHelper;
import in.pathri.screenoff.loghelper.LogHelper.Level;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

	public class InputStreamHandler extends Thread {
		private static String LOG = "in.pathri.screenoff.roothelper.InputStreamHandler";
		private final InputStream stream;
		private final boolean sink;
		StringBuffer output;

		public String getOutput() {
			return output.toString();
		}

		public InputStreamHandler(InputStream stream, boolean sink) {
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
						output.append(System.getProperty("line.separator"));
					}
				}
			} catch (IOException ignored) {
				// System.out.println(ignored);
				LogHelper.customLog(LOG, Level.ERROR, ignored.getMessage());
			}
		}
	}