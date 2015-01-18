package in.pathri.screenoff;

import in.pathri.screenoff.loghelper.LogHelper;
import in.pathri.screenoff.loghelper.LogHelper.Level;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
public class MainActivity extends Activity {
	private static String LOG = "in.pathri.screenoff";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		LogHelper.customLog(LOG, Level.DEBUG, "Into OnCreate");
//		setContentView(R.layout.activity_main);
		SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		if(!localSharedPreferences.contains("DEVICE_PATH")){
			String device = InputDeviceHelper.getPowerDevice();
			localSharedPreferences.edit().putString("DEVICE_PATH", device).commit();
		}
		String devicePath = localSharedPreferences.getString("DEVICE_PATH", "");
		if(devicePath.startsWith("/dev/input")){
			ScreenOffHelper.screenOff(devicePath);							
		}
		else{
			LogHelper.customLog(LOG, Level.ERROR, "Unknown Device Path. Terminating");
		}
		finish();
	}
}
