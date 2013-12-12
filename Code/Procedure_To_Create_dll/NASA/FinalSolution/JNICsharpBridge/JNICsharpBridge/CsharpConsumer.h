
#include "jni.h"
/* Header for class CsharpConsumer */

#ifndef _Included_CsharpConsumer
#define _Included_CsharpConsumer
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     CsharpConsumer
 * Method:    addTwoNos
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_CsharpConsumer_addTwoNos
  (JNIEnv *, jobject, jint, jint);

  JNIEXPORT jint JNICALL Java_CsharpConsumer_reigsterAssemblyHandler
  (JNIEnv * env, jobject jobj, jstring str);
#ifdef __cplusplus
}
#endif
#endif
