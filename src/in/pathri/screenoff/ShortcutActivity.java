package in.pathri.screenoff;

import in.pathri.screenoff.loghelper.LogHelper;
import in.pathri.screenoff.loghelper.LogHelper.Level;
import android.app.Activity;
import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.os.Bundle;

public class ShortcutActivity extends Activity{
	private static String LOG = "in.pathri.screenoff";
	 @Override
	  protected void onCreate(final Bundle savedInstanceState)
	    {
	    super.onCreate(savedInstanceState);
	    final Intent shortcutIntent=new Intent("in.pathri.screenoff.SCREEN_OFF");
	    final ShortcutIconResource iconResource=Intent.ShortcutIconResource.fromContext(this,R.drawable.screenoff_icon);
	    final Intent intent=new Intent();
	    intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT,shortcutIntent);
	    intent.putExtra(Intent.EXTRA_SHORTCUT_NAME,getResources().getString(R.string.app_name));
	    intent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,iconResource);
	    LogHelper.customLog(LOG, Level.DEBUG, "Sent Intent");
	    setResult(RESULT_OK,intent);
	    finish();
	    }

}
