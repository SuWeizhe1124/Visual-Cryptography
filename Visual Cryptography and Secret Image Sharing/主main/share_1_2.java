package apple;

public class share_1_2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		
		
		
	}
	
	
	
	
	
	
	
	
	public static int [][] pixel_2_2share_1(int[]  [] pixel_share)
	{
		ame  AM = new  ame();
		  int[]  [] pixel_share_1 = new int[pixel_share[0].length*2][pixel_share.length*2];
	        System.out.print("\n");
	        System.out.print(" 擴張-OK \n");
	        System.out.print("\n");
	        AM. ImageShow(pixel_share);
	        		  for(int i=0 ,I=0;i<pixel_share[0].length;i++,I=I+2)
	        	        {
	        	        	  for(int j=0 ,J=0;j<pixel_share.length;j++,J=J+2)
	        	  	        {	  
	        	        		// System.out.print("   pixel_share ["+i+"]"+"["+j+"] ="+pixel_share[i][j]);
	        	        		pixel_share_1[I][J]=pixel_share[i][j];        	        	
	        	    //    System.out.print("   pixel_share_1 ["+I+"]"+"["+J+"] ="+pixel_share_1[I][J]);
	        	  	        }
	        	  	        }
	        		  System.out.print(" 放入擴張-OK \n");
	        		  System.out.print("\n");
	        		  AM. ImageShow(pixel_share_1); //----OK---//     
	        		  for(int i=0,I=0;I<pixel_share_1[0].length;i++,I=I+2)
	        	        {
	        	        	  for(int j=0,J=0;J<pixel_share_1.length;j++,J=J+2)
	        	  	        {
	        	        		  
	        	        	//	 System.out.print("   pixel_share ["+I+"]"+"["+J+"] ="+pixel_share_1[I][J]);
	        	        		 switch(pixel_share_1[I][J]) { //修改的地方  程式設定黑白的程式。
	        	        		    case 0:
	        	        		    	pixel_share_1[I][J]=0;    	pixel_share_1[I][J+1]=255;
	        	        		 
	        	        		    	pixel_share_1[I+1][J]=0;   	pixel_share_1[I+1][J+1]=255;
	        	        		  
	        	        		    	break;
	        	         		    case 1:
	        	        		    	pixel_share_1[I][J]=0;   	pixel_share_1[I][J+1]=0;
	        	        		  
	        	        		    	pixel_share_1[I+1][J]=255; 	pixel_share_1[I+1][J+1]=255;

	        	        		    	break;
	        	         		    case 2:
	        	        		    	pixel_share_1[I][J]=255;    	pixel_share_1[I][J+1]=0;
	        	        		 
	        	        		    	pixel_share_1[I+1][J]=255;    	pixel_share_1[I+1][J+1]=0;
	        	        		  
	        	        		    	break;
	        	         		    case 3:
	        	        		    	pixel_share_1[I][J]=255;  	pixel_share_1[I][J+1]=255;
	        	        		   
	        	        		    	pixel_share_1[I+1][J]=0; 	pixel_share_1[I+1][J+1]=0;
	        	        		    
	        	        		    	break;
	        	         		    case 4:
	        	        		    	pixel_share_1[I][J]=0;   	pixel_share_1[I][J+1]=255;
	        	        		  
	        	        		    	pixel_share_1[I+1][J]=255;  	pixel_share_1[I+1][J+1]=0;
	        	        		    
	        	        		    	break;
	        	         		    case 5:
	        	        		    	pixel_share_1[I][J]=255; 	    	pixel_share_1[I][J+1]=0;
	        	        	
	        	        		    	pixel_share_1[I+1][J]=0;     	pixel_share_1[I+1][J+1]=255;
	        	        		    	break;		  
	        	         	
	        	        		}
	        	       
	        	  	        }
	        	  	        }
	        		  System.out.print(" 插入黑白-OK \n"); 
	        		  AM.  ImageShow(pixel_share_1);
	        		  System.out.print("\n");
		return pixel_share_1;
		
	}

}
