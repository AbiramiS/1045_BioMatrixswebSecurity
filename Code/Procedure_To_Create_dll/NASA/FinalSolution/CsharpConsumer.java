
class CsharpConsumer {

	static{
		System.loadLibrary("JNICsharpBridge");
	}

	public static void main(String[] args) {
		CsharpConsumer csc = new CsharpConsumer();
		csc.reigsterAssemblyHandler("FaceRegonization");
		System.out.println("Result of adding = "+ csc.addTwoNos(1, 0));
		//System.out.println("Result of adding = "+ csc.addThreeNos(7, 5, 3));
		//System.out.println("Result of adding = "+ csc.subTwoNos(7, 5));
	}
	native int addTwoNos(int a,int b);
	//native int addThreeNos(int a,int b,int c);
	//native int subTwoNos(int a,int b);
	native int reigsterAssemblyHandler(String str);
}
