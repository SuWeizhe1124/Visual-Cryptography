package apple;

public class getSize_pixe {

	public static void main(String[] args) {

		
		
		
	}
public static  int[][]  pixe_size_3(int [][] pixe)
{
	 int[]  [] pixel_share_ = new int[pixe[0].length/2][pixe.length/2];
     for(int I=0,i=0;I<pixe.length;I=I+2,i++)
       {
       	  for(int J=0,j=0;J<pixe[0].length;J=J+2,j++)
 	        {
       		  pixel_share_[i][j] =pixe_3(pixe,I,J);
 	        }
    
 	        }

	
	return pixel_share_;
}
	
public static  int pixe_3(int [][] pixels_size,int I, int J )
{


	     int [] pixe = new int[3] ;//0 1 2
        // 4*4=16 0 15 ¦©±¼ ­ìÂI -1
	  	pixe[0]= pixels_size[I][J+1];//¤W©³
	  	pixe[1]= pixels_size[I+1][J];
	  	pixe[2]= pixels_size[I+1][J+1];		
	  	int n=0;
		int N=0;
            for(int i=0;i<pixe.length;i++)
            {
            	if(pixe[i]==255)
            	{
            		n++;
            	}
            	if(pixe[i]==0)
            	{
            		N++;
            	}
            }
            
            //----------------------------------//
   	     int pixe_3 ;
          
                if(n>N)
	            {
	            	pixe_3=255;
	            }
                else
	            {
	            	pixe_3=0;
	            }
	          
           
       
       
//System.out.print("pixels_size["+I+"]["+J+"] = "+pixels_size[I][J]+" "+pixels_size[I][J+1]+" "+pixels_size[I+1][J]+" "+pixels_size[I+1][J+1]+"pixe_3 = "+pixe_3+"    ");
    	  //	System.out.print(pixe_3+" ");
            return pixe_3;

            
            
}
	
	
	
	
	
}
