package moe.berd.android.xposed.fixgreen;

import android.app.*;
import android.os.*;

public class MainActivity extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
		this.setContentView(R.layout.main);
		/*Bitmap b=BitmapFactory.decodeFile("/sdcard/a.jpg");
		
		int[] argb=new int[b.getWidth()*b.getHeight()];
        b.getPixels(argb,0,b.getWidth(),0,0,b.getWidth(),b.getHeight());
		
		byte[] result=compressImage(argb,b.getWidth(),b.getHeight(),10,2);
		try
		{
			new FileOutputStream("/sdcard/compressed.jpg").write(result);
			((ImageView)findViewById(R.id.img1)).setImageBitmap(BitmapFactory.decodeByteArray(result,0,result.length));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}*/
	}

	public static String bin2hex(byte[] data)
	{
		StringBuilder sb=new StringBuilder(data.length*2);
		for(byte b:data)
		{
			sb.append(String.format("0x%02x ",b));
		}
		return sb.toString();
	}

}
