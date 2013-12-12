package com.yourcompany.struts;


public class Decryption {

    public int i,j=0,d=1,h,n,l=8,l2=0,l1=0;
    public char a[]=new char[50000];
    public String t,st="",st1="",vj="";
    public String w =new String();
    public String r =new String();
    public String y="";
    public int c=0;

    public String decrypt(String str2)
     {
	int len=str2.length();
	for(i=0;i<len;i++)
	{
            char aa= ((str2).charAt(i));
            a[i]=aa;
            if(a[i]=='A')
            {
		st="1010";
		st1=st;
            }
            else if(a[i]=='B')
            {
                st="1011";
		st1=st;
            }
            else if(a[i]=='C')
            {
		st="1100";
		st1=st;
	    }
            else if(a[i]=='D')
            {
		st="1101";
		st1=st;
            }
            else if(a[i]=='E')
            {
                st="1110";
		st1=st;
            }
            else if(a[i]=='F')
            {
		st="1111";
                st1=st;
            }
            else if(a[i]=='0')
            {
		st="0000";
                st1=st;
            }
            else if(a[i]=='1')
            {
		st="0001";
		st1=st;
            }
            else if(a[i]=='2')
            {
		st="0010";
		st1=st;
            }
            else if(a[i]=='3')
            {
		st="0011";
                st1=st;
            }
            else if(a[i]=='4')
            {
		st="0100";
		st1=st;
            }
            else if(a[i]=='5')
            {
            	st="0101";
		st1=st;
            }
            else if(a[i]=='6')
            {
            	st="0110";
		st1=st;
            }
            else if(a[i]=='7')
            {
            	st="0111";
                st1=st;
            }
            else if(a[i]=='8')
            {
		st="1000";
            }
            else if(a[i]=='9')
            {
		st="1001";
            }
            	w=w+st;
            }
            char w_ch[]=w.toCharArray();
            String x=w.toString();
            //System.out.println("Length:"+len);
            try
            {
                if(len==8)
		{
                    int k;
                    k=len;
                    for(j=k-1;j>=0;j--)
                    {
                    	c=c+(w_ch[j]-48)*d;
                        y=y+c;
                    	d=d*2;
                    }
                    System.out.print("\n");
                    vj=vj+(char)c;
                    System.out.print((char)c);
                }
		else
		{
                    int k;
                    for(i=0;i<10000;i++)
                    {
			c=0; d=1;
			k=i*8;
			for(j=k+7;j>=k;j--)
			{
                            c=c+(w_ch[j]-48)*d;
                            y=y+c;
                            d=d*2;
			}
			vj=vj+(char)c;
                    }
                     //System.out.println("The dec data is"+vj );
		}
            }
            catch(Exception e)
            {

            }
	return vj;
     }
}