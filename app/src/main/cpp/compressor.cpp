#include <iostream>

#include <jni.h>

#include "JpegCompress.hpp"

extern "C"
{
	JNIEXPORT jbyteArray JNICALL Java_moe_berd_android_xposed_fixgreen_NativeInterface_compressImage(JNIEnv* env,jobject thiz,jintArray data,jint width,jint height,jint quality,jint mode);
}

jbyteArray Java_moe_berd_android_xposed_fixgreen_NativeInterface_compressImage(JNIEnv* env,jobject thiz,jintArray data,jint width,jint height,jint quality,jint mode)
{
	JpegCompress comp(width,height);
	
	jint* input=env->GetIntArrayElements(data,0);
	uint8_t* buffer=new uint8_t[width*height*3];
	for(int index=0;index<width*height;index++)
	{
		uint8_t* rgb=&buffer[index*3];
		rgb[0]=(input[index] & 0xff0000) >> 16;
		rgb[1]=(input[index] & 0xff00) >> 8;
		rgb[2]=(input[index] & 0xff) >> 0;
	}
	comp.compress(buffer,quality,(J_DCT_METHOD)mode);
	
	env->ReleaseIntArrayElements(data,input,JNI_ABORT);
	delete[] buffer,data;
	
	jbyteArray result=env->NewByteArray(comp.getSize());
	env->SetByteArrayRegion(result,0,comp.getSize(),(jbyte*)comp.getBuffer());
	return result;
}
