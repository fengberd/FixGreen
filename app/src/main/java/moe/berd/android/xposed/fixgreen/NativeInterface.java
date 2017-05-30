package moe.berd.android.xposed.fixgreen;


public class NativeInterface
{
	public static native byte[] compressImage(int[] input,int width,int height,int quality,int mode);
}
