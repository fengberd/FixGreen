package moe.berd.android.xposed.fixgreen;

import android.app.AlertDialog;
import android.content.*;
import android.content.pm.*;
import android.graphics.*;
import android.os.*;
import android.support.v7.app.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends ActionBarActivity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.main);
		TextView text=(TextView)findViewById(R.id.label_android_status);
		if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N)
		{
			text.setText(R.string.label_android_status_fail);
			text.setTextColor(Color.YELLOW);
		}
		else
		{
			text.setText(R.string.label_android_status_pass);
			text.setTextColor(Color.GREEN);
		}
		final MainActivity this_=this;
		findViewById(R.id.button_hide_from_launcher).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				new AlertDialog.Builder(this_).setTitle(R.string.dialog_hide_title)
						.setMessage(R.string.dialog_hide_message)
						.setIcon(android.R.drawable.ic_dialog_alert)
						.setPositiveButton(android.R.string.yes,new DialogInterface.OnClickListener()
						{
							public void onClick(DialogInterface dialog,int whichButton)
							{
								getPackageManager().setComponentEnabledSetting(new ComponentName(this_,moe.berd.android.xposed.fixgreen.MainActivity.class),PackageManager.COMPONENT_ENABLED_STATE_DISABLED,PackageManager.DONT_KILL_APP);
								Toast.makeText(MainActivity.this,"LOL",Toast.LENGTH_SHORT).show();
								finish();
							}
						})
						.setNegativeButton(android.R.string.no,null)
						.show();
			}
		});
	}
}
