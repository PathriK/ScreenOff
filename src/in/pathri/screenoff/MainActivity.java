package in.pathri.screenoff;

import in.pathri.screenoff.loghelper.LogHelper;
import in.pathri.screenoff.loghelper.LogHelper.Level;
import android.app.Activity;
import android.os.Bundle;
public class MainActivity extends Activity {
	private static String LOG = "in.pathri.screenoff";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		LogHelper.customLog(LOG, Level.DEBUG, "Into OnCreate");
//		setContentView(R.layout.activity_main);
		ScreenOffHelper.screenOff();
		finish();
	}
}
