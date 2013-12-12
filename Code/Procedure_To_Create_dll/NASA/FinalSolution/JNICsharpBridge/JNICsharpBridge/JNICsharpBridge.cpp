// JNICsharpBridge.  : main project file.

#include "stdafx.h"
#include "CsharpConsumer.h"
using namespace System::Reflection;
using namespace System;
using namespace FaceRegonization;
using System::Runtime::InteropServices::Marshal;
ref class Test
{
public:
	static String^ pathBase = "G:\\nasa\\JNICsharpBridge";

	static void setPathBase(String^ path){
		pathBase = path;
	}	
   static Assembly^ MyResolveEventHandler( Object^ sender, ResolveEventArgs^ args )
   {
			printf("AssemblyResolve Handler called");
			Console::WriteLine("current path :"+pathBase);
            Assembly^ MyAssembly;
			Assembly^ objExecutingAssemblies;
            String^ strTempAssmbPath = "";			
			objExecutingAssemblies = Assembly::GetExecutingAssembly();
            array<AssemblyName ^>^ arrReferencedAssmbNames = objExecutingAssemblies->GetReferencedAssemblies();
            strTempAssmbPath = "D:\\FaceRegonization.dll";
			MyAssembly = Assembly::LoadFrom(strTempAssmbPath);
            return MyAssembly;
   }
};

/*
 * Class:     CsharpConsumer
 * Method:    addTwoNos
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_CsharpConsumer_addTwoNos
  (JNIEnv * env, jobject jobj, jint a, jint b)
{
	SSIM^ ad = gcnew SSIM();
	int res = (int)ad->addTwoNumberes(a,b);
	return res ;
}

/*
 * Method:    reigsterAssemblyHandler
 * Signature: (Ljava/lang/String;)I

 This method tells our bridge code from where to load .Net asseblies.
 Custom loading of .net aseeblies frees us from registering assemblies.
 */
JNIEXPORT jint JNICALL Java_CsharpConsumer_reigsterAssemblyHandler
  (JNIEnv * env, jobject jobj, jstring str)
{
		AppDomain^ currentDomain = AppDomain::CurrentDomain;
	const char* ttt = (env)->GetStringUTFChars(str,NULL);
	String^ tmp = Marshal::PtrToStringAnsi((IntPtr)((char*)ttt));
		 Test::setPathBase(tmp);
		 	(env)->ReleaseStringUTFChars(str,ttt);
	currentDomain->AssemblyResolve += gcnew ResolveEventHandler( Test::MyResolveEventHandler );
	printf("Registered AssemblyResolve Handler with");
	Console::Write(tmp);
	return 0;
}