package apple;

public class Binary {

	public static String [] Binary_main(int  input_n) {		
	  
       int n=1;
       for(int  i=0;i<input_n;i++)
       {
       	n=n*2;
       //    System.out.print(n+" ");
       }
     //-----------得到最高位數 8-----------------//
//4 3 2 1       
// 8 4 2 1 
       System.out.print(" \n");
      int I=0;
   
      int  [] N  = new int [n];
       for(int i=0;i<N.length;i++)
       {
       	I=i;
       N[i]=I;
   //    	   System.out.print(" I ="+I+"  \n");
       }
       
       int  [] NN  = new int [N.length-2];
       for(int i=0;i<N.length-1;i++)
       {
       	
       		N[i]=N[i+1];
       	
       }

       for(int i=0;i<NN.length;i++)
       {
       	
       	NN[i]=N[i];
 //      	  System.out.print(NN[i]+" ");
       }
    	  String  [] Binary_NN  = new String [ NN.length]; 
    //---------------求得他可能發生的事件 砍掉 0與 都是1的情況-----------------------------//
       System.out.print("  \n");
       for(int F=0;F< NN.length;F++)
       {
       	
       	  int A= NN[F];
       	//   System.out.print(" 輸入的數字 ==  "+A +" \n");
    	     int B=0;
    	     int C=0;
    	 
    	     String Binary = "" ;
    	     int i1 = 0;
    	     do
    	     {
    	    	
    	    	B=A%2;
    	    	A=A/2;
    	    	i1++;

    	    	  C=i1+1;
    	    	  Binary=Binary+B;
    		    //	 System.out.print(" A ="+A+ " i= "+B +"  Binary = "+Binary+"  \n");
    		    	 
    	    	  if(A<1)
    	    	  {
    	    		  break;
    	    	  }
    	    
    	     } while (i1 <C);// 形成一種永續機
    	  //   System.out.print(" A ="+A+ " i= "+B +"  Binary = "+Binary+"  \n");
    	   //  System.out.print("Binary.length() [0~4=5]= "+(Binary.length()) +"  Binary "+Binary+"  i ="+i1 +"  \n");
    	          //----------------補0----------------------------------//
    	     for(int i=0;i<input_n-i1;i++)
    	     {
    	    	 Binary=Binary+0;
    	     }
    	 //    System.out.print("\n Binary.length() [0~4=5]= "+(Binary.length()) +"  Binary "+Binary+"  i ="+i1 +"  \n");
    	    String  Binary_chart;
  	     String  [] Binary_arry  = new String [Binary.length()];

  	     for(int i=0;i<Binary.length();i++)
  	     {
  	    	 Binary_chart=String.valueOf((Binary.charAt(i)));
  	    	 Binary_arry[i]=Binary_chart;
  	     }
  	     //-------------------------把位置轉正-----------------------------------//
  	     String  [] Binary_Arry  = new String [Binary.length()]; //Binary_Arry [可能發生的事件] [ Binary.length()]
  	     
  	  String   binary="";
  	     for(int i=0,j=Binary.length()-1;i<Binary.length();i++,j--)
  	     {
  	    
  	    	 // System.out.print("Binary ["+i+"]= "+ Binary_arry[i]+"\n");
  	    	 Binary_Arry[i]=Binary_arry[j];
  	     }

  	     for(int i=0;i<Binary.length();i++)
  	     {
  	    	
  	    	binary=binary+Binary_Arry[i];
  	     }

  	 // System.out.print("  binary = "+ binary+"\n");
       	Binary_NN[F]=binary;
       }
      //------------------------------------------------------------// 
      for(int i=0;i<Binary_NN.length;i++)
  	     {
       //	  System.out.print("  Binary_NN = "+ Binary_NN[i]+"\n");
  	     }
       
	return Binary_NN;
       
  /*
       //------------------A<1 代表結束 求他的  二進制--------------------------------------//
    */
    
	}

	public static  int [][] pixe(int out,int  [][][]pixe,int [][]pixe_2)
	{
	
		for(int i=0;i<pixe_2.length;i++)
		{
			

			for(int j=0;j<pixe_2[0].length;j++)
			{
				
				pixe_2[i][j]=pixe[out][i][j];
			}
			
		}
		
		return pixe_2;
	}
	

}
