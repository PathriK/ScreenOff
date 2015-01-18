package in.pathri.screenoff;

import in.pathri.screenoff.loghelper.LogHelper;
import in.pathri.screenoff.loghelper.LogHelper.Level;
import in.pathri.screenoff.roothelper.InputStreamHandler;

import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InputDeviceHelper {
	private static String LOG = "in.pathri.screenoff.InputDeviceHelper";
	private static String lookupKeyCode = "0074";
	private static String lookupKeyType = "0001";
	
	public static String getPowerDevice() {
		List<String> lines = new ArrayList<String>();
		String device = "";
		lines = getEventDevices();
//		System.out.println(lines.size());
		if(lines.size() != 0){
			device = getEventDevice(lines);
		}
//		return "/dev/input/event3";
		return device;
	}
	
	public static String getEventDevice(List<String> lines){
		String device = "";
		boolean bolFound = false;
		LogHelper.customLog(LOG, Level.DEBUG, "Into getEventDevice");
		for(String line : lines){
			line = line.trim();			
			if(line.startsWith("add device")){
				device = line.split(":")[1];	
				device = device.trim();
			}					
			if(line.startsWith("KEY (" + lookupKeyType + "):")){
				if(line.contains(lookupKeyCode)){
					bolFound = true;
					break;
				}
			}
		}
		LogHelper.customLog(LOG, Level.DEBUG, "Out getEventDevice: " + device);
		if(bolFound){
			return device;
		}else{
			return "Not Found";			
		}
	}

	private static List<String> getEventDevices(){
		LogHelper.customLog(LOG, Level.DEBUG, "Into getEventDevices");
		String getEvent = "getevent -p\n";
		List<String> lines = new ArrayList<String>();
		try {
			Process su = Runtime.getRuntime().exec("su");
			InputStreamHandler in = new InputStreamHandler(su.getInputStream(), false);
			InputStreamHandler er = new InputStreamHandler(su.getErrorStream(), false);
			DataOutputStream outputStream = new DataOutputStream(su.getOutputStream());
			// outputStream.writeBytes("sendevent /dev/input/event0 1 116 1\n");
			outputStream.writeBytes(getEvent);
			outputStream.flush();
			outputStream.writeBytes("exit\n");
			outputStream.flush();
			outputStream.writeBytes("exit\n");
			outputStream.flush();
			su.waitFor();			
			LogHelper.customLog(LOG, Level.ERROR, er.getOutput());
//			LogHelper.customLog(LOG, Level.INFO, in.getOutput());
			String contents = in.getOutput();			
//			System.out.println(contents);
			lines = Arrays.asList(contents.split("\n"));			
		} catch (Exception e) {
			// e.printStackTrace();
			LogHelper.customLog(LOG, Level.ERROR, e.getMessage());
		}
		LogHelper.customLog(LOG, Level.DEBUG, "Completed getEventDevices");
		return lines;
	}
}
