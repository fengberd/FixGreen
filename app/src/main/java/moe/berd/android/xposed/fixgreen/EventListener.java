package moe.berd.android.xposed.fixgreen;

import android.annotation.*;
import android.graphics.*;
import android.os.*;

import java.io.*;

import de.robv.android.xposed.*;
import de.robv.android.xposed.callbacks.XC_LoadPackage.*;

public class EventListener implements IXposedHookLoadPackage
{
	@Override
	@SuppressLint({
			"SdCardPath",
			"UnsafeDynamicallyLoadedCode"
	})
	@SuppressWarnings("deprecation")
	public void handleLoadPackage(final LoadPackageParam aparam) throws Throwable
	{
		if("moe.berd.android.xposed.fixgreen".equals(aparam.packageName))
		{
			XposedHelpers.findAndHookMethod("moe.berd.android.xposed.fixgreen.MainActivity",aparam.classLoader,"itWorks",new XC_MethodHook()
			{
				@Override
				protected void beforeHookedMethod(MethodHookParam param) throws Throwable
				{
					param.setResult(true);
				}
			});
			return;
		}
		if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N)
		{
			return;
		}
		System.load("/data/data/moe.berd.android.xposed.fixgreen/lib/libcompressor.so");
		XposedHelpers.findAndHookMethod("android.graphics.Bitmap",aparam.classLoader,"compress",Bitmap.CompressFormat.class,int.class,OutputStream.class,new XC_MethodHook()
		{
			@Override
			protected void beforeHookedMethod(MethodHookParam param) throws Throwable
			{
				if(param.args[0]==Bitmap.CompressFormat.JPEG)
				{
					try
					{
						Bitmap img=(Bitmap)param.thisObject;
						int width=img.getWidth(), height=img.getHeight();
						int[] argb=new int[width * height];
						img.getPixels(argb,0,width,0,0,width,height);
						((OutputStream)param.args[2]).write(moe.berd.android.xposed.fixgreen.NativeInterface
								.compressImage(argb,width,height,(int)param.args[1],0));
						param.setResult(true);
					}
					catch(Exception e)
					{
						XposedBridge.log(e);
						param.setResult(false);
					}
				}
			}
		});
	}
}
