package apple;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class ame {

	static  String File_name="C:\\Users\\zx781\\OneDrive\\桌面\\2-12.jpg";
	static  String File_name_out="C:\\Users\\zx781\\OneDrive\\桌面\\Visual Cryptography and Secret Image Sharing\\2-12.jpg";
	static String Gray_scale_File_name="C:\\Users\\zx781\\OneDrive\\桌面\\Visual Cryptography and Secret Image Sharing\\3-10.jpg";
	static  String File_name_input="C:\\Users\\zx781\\OneDrive\\桌面\\2-1.png";
	public static void main(String[] args) throws IOException {
   

		 int W=300;
		 int H=300;
		 int share=0;
           // 原始圖像
            //噪声编码法(noise encoding) 127
		 
		 
		// 論文 視覺密碼研究 
 	
		//---------Visual_Cryptography- 視覺密碼學   解密後 現實肉眼即可看得出來  他不是密碼學 他要現實------------------------------//
			//	 https://youtu.be/bb24mBADuG0    
		
		 
 	 // Visual_Cryptography_convert(File_name,File_name_out,W,H,share);// OK 1*1 他是 AND 不是 XNOR 也不是 XOR 解密動作不能變    考慮肉眼問題  麥克筆 跟 麥克筆 疊合 不可能為1
		 /*
		  * 0   0    0  黑
		  * 0   1   0  黑
		  * 1   0    0  黑
		  * 1    1   1  白
		  */
	 //-------------------------------------------------------//
		 //----Computer-Visual_Cryptography--虛擬----電腦視覺密碼 三項 ---新型視覺密碼學 電腦螢幕出來就 肉眼看可以---給後面密碼學的學者------------------------------------------------------------------------//
		// Visual_Cryptography_Convert(File_name,File_name_out,W,H,share); //XOR//(OK) 1*1  視覺較完整性機密影像加密 // 黑色pixel_share_1 = pixel_share_2//白色pixel_share_1 != pixel_share_2		 
		 //-------------------------------------------------------------//
		 
      //   Visual_Cryptography_convert_1(File_name,File_name_out,W,H,share);  //1*1 (OK)比較可觀視覺密碼，最後是黑色為亂數去下 所以沒差不會亂掉

		 //Visual_Cryptography_2(File_name,File_name_out,W,H,share);// 2*2 視覺密碼編碼 OK 像素變大

		   Visual_Cryptography_share(File_name,File_name_out,W,H,share);  //經典 OK 視覺分享影像  組合特性-搞清楚了OK 像素越大 越清楚  使用者越多越不清楚
		
		// Visual_Cryptography(File_name,File_name_out,W,H,share); // 經典 OK 可欲試視覺密碼    我要的輸出 加入 輸入影像  File_name  輸出 File_name_out 第二章分享影像
	
	   // Visual_Cryptography_share_input(File_name,File_name_out,W,H,share); //平移視覺密碼影像(還在搞)
	}
	
	public static    void Visual_Cryptography_convert_1(String File_name ,String File_name_out,int W,int H,int share) throws IOException
	{
		 BufferedImage RGB_img = ImageIO.read(new File(File_name));
		/* //讀取高度
		 int height = RGB_img.getHeight();
        //讀取寬度
        int width = RGB_img.getWidth();
        int [] gray_img = new int[width * height];
        gray_img = Gray(RGB_img);
        */
		 RGB_img=resize(W,H,RGB_img);
		 // 調整像素
        RGB_img=(BufferedImage) convert(RGB_img);
        RGB_img= to0_255(RGB_img);
        BufferedImage RGB_img_share ;
        RGB_img_share=resize(W,H,RGB_img);
        RGB_img = RGB_img_share_convert_1(RGB_img,RGB_img_share,share);
        ImageIO.write(RGB_img, "png", new File(File_name_out));
	}
	
	public static BufferedImage  RGB_img_share_convert_1(BufferedImage RGB_img,BufferedImage RGB_img_share,int share) throws IOException {
		
		System.out.print("\n");
		  System.out.print("讀取影像 pixel \n");
	    int width = RGB_img.getWidth();
	    int height = RGB_img.getHeight();
	    int[]  [] pixels = new int[width][height];
	    for(int i=0;i<width;i++)
	    {
	    	  for(int j=0;j<height;j++)
		        {
	    		  int rgb = RGB_img.getRGB(i, j);
	    		  int r = (rgb & 0xff0000) >> 16;
	              int g = (rgb & 0x00ff00) >> 8;
	              int b = rgb & 0x0000ff;
	              int gray = (r + g + b) / 3;
	              pixels[i][j]=gray;
	    		  System.out.print(" gray.pixel =  ["+i+"] ["+ j+"] =  "+gray );
		        }
	    	  System.out.print("\n");
	    }
	  
//	     
	    //--------------------------------------------------//
//	    ImageShow(pixels);
	//---------------------------------------------------------//
	    

		 int[]  [] pixel_share_1 = new int[width][height];
		 int[]  [] pixel_share_2 = new int[width][height];

	    

	    for(int i=0;i<pixel_share_1.length;i=i+2)
	    {
	    	  for(int j=0;j<pixel_share_1[0].length;j=j+2)
		        {
	    	if( pixels[i][j]==255) // 255 為白色
	    	{
	
	    		int 	r =(int)(Math.random()* 4);
	    		if(r==0)
	    		{


	    			 pixel_share_1[i][j]=1;   pixel_share_1[i+1][j]=1;
		    		 
	    			 pixel_share_1[i][j+1]=0;     pixel_share_1[i+1][j+1]=0;
		    		  
		    		  pixel_share_2[i][j]=1;    pixel_share_2[i+1][j]=1; 
			    		 
		    		  pixel_share_2[i][j+1]=0;	     pixel_share_2[i+1][j+1]=0;
		    		
	    		}
	    		if(r==1)
	    		{
	    			  pixel_share_1[i][j]=0;      pixel_share_1[i+1][j]=0; 
		    		 
	    			  pixel_share_1[i][j+1]=1;		     pixel_share_1[i+1][j+1]=1;
		    		
		    		  
		    		  
		    		  pixel_share_2[i][j]=0;        pixel_share_2[i+1][j]=0;
			    		   
		    		  pixel_share_2[i][j+1]=1;	      pixel_share_2[i+1][j+1]=1;
	    		}
	    		if(r==2)
	    		{
	    			  pixel_share_1[i][j]=1;      pixel_share_1[i+1][j]=0; 
		    		 
	    			  pixel_share_1[i][j+1]=1;		     pixel_share_1[i+1][j+1]=0;
		    		
		    		  
		    		  
		    		  pixel_share_2[i][j]=1;        pixel_share_2[i+1][j]=0;
			    		   
		    		  pixel_share_2[i][j+1]=1;	      pixel_share_2[i+1][j+1]=0;
	    		}
	    		if(r==3)
	    		{
	    			  pixel_share_1[i][j]=0;      pixel_share_1[i+1][j]=1; 
		    		 
	    			  pixel_share_1[i][j+1]=0;		     pixel_share_1[i+1][j+1]=1;
		    		
		    		  
		    		  
		    		  pixel_share_2[i][j]=0;        pixel_share_2[i+1][j]=1;
			    		   
		    		  pixel_share_2[i][j+1]=0;	      pixel_share_2[i+1][j+1]=1;
	    		}
	    	}
	    	if( pixels[i][j]==0) {
	    		int 	r =(int)(Math.random()* 4);
	    		if(r==0)
	    		{


	    			 pixel_share_1[i][j]=0;      pixel_share_1[i+1][j]=0; 
		    		 
	    			  pixel_share_1[i][j+1]=1;		     pixel_share_1[i+1][j+1]=1;
		    		
	    			 
		    		  
	    			  pixel_share_2[i][j]=1;    pixel_share_2[i+1][j]=1; 
			    		 
		    		  pixel_share_2[i][j+1]=0;	     pixel_share_2[i+1][j+1]=0;
		    		
	    		}
	    		if(r==1)
	    		{
	    			
	                      pixel_share_1[i][j]=1;   pixel_share_1[i+1][j]=1;
		    		 
	    			 pixel_share_1[i][j+1]=0;     pixel_share_1[i+1][j+1]=0;
		    		  
		    		  
		    		  pixel_share_2[i][j]=0;        pixel_share_2[i+1][j]=0;
			    		   
		    		  pixel_share_2[i][j+1]=1;	      pixel_share_2[i+1][j+1]=1;
	    		}
	    		
	    		
	    		
	    		if(r==2)
	    		{


	    			 pixel_share_1[i][j]=1;      pixel_share_1[i+1][j]=0; 
		    		 
	    			  pixel_share_1[i][j+1]=1;		     pixel_share_1[i+1][j+1]=0;
		    		
	    			 
		    		  
	    			  pixel_share_2[i][j]=0;    pixel_share_2[i+1][j]=1; 
			    		 
		    		  pixel_share_2[i][j+1]=0;	     pixel_share_2[i+1][j+1]=1;
		    		
	    		}
	    		if(r==3)
	    		{
	    			
	                      pixel_share_1[i][j]=0;   pixel_share_1[i+1][j]=1;
		    		 
	    			 pixel_share_1[i][j+1]=0;     pixel_share_1[i+1][j+1]=1;
		    		  
		    		  
		    		  pixel_share_2[i][j]=1;        pixel_share_2[i+1][j]=0;
			    		   
		    		  pixel_share_2[i][j+1]=1;	      pixel_share_2[i+1][j+1]=0;
	    		}
	    		
	    		
	    		
	    		
	    		
	    		
	    	}
	    		
	   
		        }
		        }
	    
	    
	    
	    
	    
	    
	    
	    
	    

	     pixel_share_1=pixel_share_0(pixel_share_1); // 分享影像1白色 跟 黑色一樣
	    
	     pixel_share_2=pixel_share_0(pixel_share_2); // 分享影像2  白色跟黑色 不一樣

	      int[]  [] pixel_share = new int[pixel_share_1[0].length][pixel_share_1.length];
	  pixel_share=pixel_share_AND(pixel_share,pixel_share_1,pixel_share_2);
	  switch(share)
	  {
	  /*
	   * pixel_share = 該圖像
	   * pixel_share_1= 那個影藏影像share_1
	   * pixel_share_2= 那個影藏影像share_2
	   */
	  case 0:
	      RGB_img_share=getImagePixel(RGB_img_share,pixel_share);
	    	break;
	  case 1:
	      RGB_img_share=getImagePixel(RGB_img_share,pixel_share_1);
	    	break;
	  case 2:
	      RGB_img_share=getImagePixel(RGB_img_share,pixel_share_2);
	    	break;
	  default: 
	      System.out.println("其他數字 _ BUG"); 
	      break;
	}
	return RGB_img_share;	
	}
	
	
	
	
	
	
	
	
	
	
	public static  void Visual_Cryptography_share_input(String File_name ,String File_name_out,int W,int H,int share) throws IOException
	{
		 BufferedImage RGB_img = ImageIO.read(new File(File_name));
		 RGB_img=resize(W,H,RGB_img);
		 // 調整像素
        RGB_img=(BufferedImage) convert(RGB_img);
	      // 影像轉灰接
        RGB_img= to0_255(RGB_img);  
        //二值化
        //-------------------------------------------------------------------------------------//
        String File_name_input="C:\\Users\\zx781\\OneDrive\\桌面\\2-1.png";
        BufferedImage RGB_img_share =ImageIO.read(new File(File_name_input));
        RGB_img_share=resize(W,H,RGB_img_share);
        RGB_img_share=(BufferedImage) convert(RGB_img_share);
	      // 影像轉灰接
        RGB_img_share= to0_255(RGB_img_share);
        //------------------------------------main-----------------------------------------------//
        RGB_img = RGB_img_share_Visual_Cryptography(RGB_img,RGB_img_share,share);
        ImageIO.write(RGB_img, "png", new File(File_name_out));
	}
	public static    void Gray_scale_Visual_Cryptography_Convert(String File_name ,String File_name_out,int W,int H,int share) throws IOException
	{
		 BufferedImage RGB_img = ImageIO.read(new File(File_name));
		/* //讀取高度
		 int height = RGB_img.getHeight();
        //讀取寬度
        int width = RGB_img.getWidth();
        int [] gray_img = new int[width * height];
        gray_img = Gray(RGB_img);
        */
		 RGB_img=resize(W,H,RGB_img);
		 // 調整像素
        RGB_img=(BufferedImage) convert(RGB_img);
        BufferedImage RGB_img_share ;
        RGB_img_share=resize(W,H,RGB_img);
        RGB_img = RGB_img_Gray_Convert(RGB_img,RGB_img_share,share);
        ImageIO.write(RGB_img, "png", new File(File_name_out));
	}
public static BufferedImage  RGB_img_Gray_Convert(BufferedImage RGB_img,BufferedImage RGB_img_share,int share) throws IOException {
	
	System.out.print("\n");
	  System.out.print("讀取影像 pixel \n");
    int width = RGB_img.getWidth();
    int height = RGB_img.getHeight();
    int[]  [] pixels = new int[width][height];
    for(int i=0;i<width;i++)
    {
    	  for(int j=0;j<height;j++)
	        {
    		  int rgb = RGB_img.getRGB(i, j);
    		  int r = (rgb & 0xff0000) >> 16;
              int g = (rgb & 0x00ff00) >> 8;
              int b = rgb & 0x0000ff;
              int gray = (r + g + b) / 3;
              pixels[i][j]=gray;
    		  System.out.print(" gray.pixel =  ["+i+"] ["+ j+"] =  "+gray );
	        }
    	  System.out.print("\n");
    }
  
    
//     
    //--------------------------------------------------//
   ImageShow(pixels);
//---------------------------------------------------------//
    for(int i=0;i<pixels.length;i++)
    {
    	  for(int j=0;j<pixels[0].length;j++)
	        {
    		//  pixels[i][j] [ 原圖變數 ] =[ 大 一1 + [加入雜訊不能小於0] ] XOR [ 小  一0 [加入雜訊不能小於0] ] = pixels[i][j]  原影像像素 依樣
    	 
    		  /**/
    
    		 
	        }
	        }
    
    

 
  switch(share)
  {
  /*
   * pixel_share = 該圖像
   * pixel_share_1= 那個影藏影像share_1
   * pixel_share_2= 那個影藏影像share_2
   */
  case 0:
      RGB_img_share=getImagePixel(RGB_img_share,pixels);
    	break;
  default: 
      System.out.println("其他數字 _ BUG"); 
      break;	
  }
  	return RGB_img_share;	
	
	}
	
	
	public static    void Visual_Cryptography_Convert(String File_name ,String File_name_out,int W,int H,int share) throws IOException
	{
		 BufferedImage RGB_img = ImageIO.read(new File(File_name));
		/* //讀取高度
		 int height = RGB_img.getHeight();
        //讀取寬度
        int width = RGB_img.getWidth();
        int [] gray_img = new int[width * height];
        gray_img = Gray(RGB_img);
        */
		 RGB_img=resize(W,H,RGB_img);
		 // 調整像素
        RGB_img=(BufferedImage) convert(RGB_img);
        RGB_img= to0_255(RGB_img);
        BufferedImage RGB_img_share ;
        RGB_img_share=resize(W,H,RGB_img);
        RGB_img = RGB_img_share_Convert(RGB_img,RGB_img_share,share);
        ImageIO.write(RGB_img, "png", new File(File_name_out));
	}
	public static BufferedImage  RGB_img_share_Convert(BufferedImage RGB_img,BufferedImage RGB_img_share,int share) throws IOException {
		
		System.out.print("\n");
		  System.out.print("讀取影像 pixel \n");
	    int width = RGB_img.getWidth();
	    int height = RGB_img.getHeight();
	    int[]  [] pixels = new int[width][height];
	    for(int i=0;i<width;i++)
	    {
	    	  for(int j=0;j<height;j++)
		        {
	    		  int rgb = RGB_img.getRGB(i, j);
	    		  int r = (rgb & 0xff0000) >> 16;
	              int g = (rgb & 0x00ff00) >> 8;
	              int b = rgb & 0x0000ff;
	              int gray = (r + g + b) / 3;
	              pixels[i][j]=gray;
	    		  System.out.print(" gray.pixel =  ["+i+"] ["+ j+"] =  "+gray );
		        }
	    	  System.out.print("\n");
	    }
	  
//	     
	    //--------------------------------------------------//
//	    ImageShow(pixels);
	//---------------------------------------------------------//
	    

		 int[]  [] pixel_share_1 = new int[width][height];
		 int[]  [] pixel_share_2 = new int[width][height];

	    for(int i=0;i<pixel_share_1.length;i++)
	    {
	    	  for(int j=0;j<pixel_share_1[0].length;j++)
		        {
	    	if( pixels[i][j]==0) // 黑色pixel_share_1 = pixel_share_2
	    	{
	    		  int 	r =(int)(Math.random()* 2);
	    		  pixel_share_1[i][j]=r;
	 
	    			pixel_share_2[i][j]= pixel_share_1[i][j];
	    	}
	    	else  // 白色 部分pixel_share_2 不等於 pixel_share_1
	    	{
	    		  int 	r =(int)(Math.random()* 2);
	    		  pixel_share_1[i][j]=r;
	  		  if(  pixel_share_1[i][j]==1)
	  		  {
	  			pixel_share_2[i][j]=0;
	  		  }
	  		  if(  pixel_share_1[i][j]==0)
	  		  {
	  			pixel_share_2[i][j]=1;
	  		  }
	    	}
		        }
		        }

	     pixel_share_1=pixel_share_0(pixel_share_1); // 分享影像1白色 跟 黑色一樣
	    
	     pixel_share_2=pixel_share_0(pixel_share_2); // 分享影像2  白色跟黑色 不一樣

	      int[]  [] pixel_share = new int[pixel_share_1[0].length][pixel_share_1.length];
	  pixel_share=pixel_XOR(pixel_share,pixel_share_1,pixel_share_2);
	  switch(share)
	  {
	  /*
	   * pixel_share = 該圖像
	   * pixel_share_1= 那個影藏影像share_1
	   * pixel_share_2= 那個影藏影像share_2
	   */
	  case 0:
	      RGB_img_share=getImagePixel(RGB_img_share,pixel_share);
	    	break;
	  case 1:
	      RGB_img_share=getImagePixel(RGB_img_share,pixel_share_1);
	    	break;
	  case 2:
	      RGB_img_share=getImagePixel(RGB_img_share,pixel_share_2);
	    	break;
	  default: 
	      System.out.println("其他數字 _ BUG"); 
	      break;
	}
	return RGB_img_share;	
	}
	public static    void Visual_Cryptography_Gray_scale_share(String File_name ,String File_name_out,int W,int H,int share) throws IOException
	{
		 BufferedImage RGB_img = ImageIO.read(new File(File_name));
		 RGB_img=resize(W,H,RGB_img);
		 // 調整像素
        RGB_img=(BufferedImage) convert(RGB_img);
	      // 影像轉灰接
        //-------------------------------------------------------------------------------------//
        BufferedImage RGB_img_share =RGB_img;
        RGB_img_share=resize(W,H,RGB_img_share);
        //------------------------------------main-----------------------------------------------//
        RGB_img = RGB_img_Gray_scale_share_Visual_Cryptography_share(RGB_img,RGB_img_share,share);
        ImageIO.write(RGB_img, "png", new File(File_name_out));
	}
public static BufferedImage  RGB_img_Gray_scale_share_Visual_Cryptography_share(BufferedImage RGB_img,BufferedImage RGB_img_share,int share) throws IOException {
		
		System.out.print("\n");
		  System.out.print("讀取影像 pixel \n");
	    int[]  [] pixels = getpixels(RGB_img);	    
	    int[]  [] pixel_share_1 = new int[RGB_img.getWidth()][RGB_img.getHeight()];
		   int[]  [] pixel_share_2 = new int[RGB_img.getWidth()][RGB_img.getHeight()];
	//---------------------------------------------------------//
        for(int i=0;i<pixel_share_1.length;i++)
        {
        	  for(int j=0;j<pixel_share_1[0].length;j++)
  	        {
        		  int 	r =(int)(Math.random()* 256);
        		  pixel_share_1[i][j]=r;
        		   // 0-255
  	        }
  	        }
        
	    for(int i=0;i<pixels.length;i++)
        {
        	  for(int j=0;j<pixels[0].length;j++)
  	        {

        		  if( pixels[i][j]>128) // 255 為白色 
              	{
              		  int 	r =(int)(Math.random()* 256);
              		pixel_share_2[i][j]= r;
              	
              	}
              	else  // 黑色 部分 main 主要
              	{
            
            		  
            		if(  pixel_share_1[i][j]>128)
           		  {
           			pixel_share_2[i][j]=1;
           			
           		  }
            		
           		 if(  pixel_share_1[i][j]<128)
           		  {
           			pixel_share_2[i][j]=0;
           		  }
     
        		  
  	        }
  	        }
        }
	    
        	  
        
         
             pixel_share_2=pixel_share_0(pixel_share_2); // 分享影像2  白色跟黑色 不一樣
             
    
	    int[]  [] pixel_share = new int[RGB_img.getWidth()][RGB_img.getHeight()];
	     pixel_share=pixel_share_AND(pixel_share,pixel_share_1,pixel_share_2);
	     
	  switch(share)
	  {
	  /*
	   * pixel_share = 該圖像
	   * pixel_share_1= 那個影藏影像share_1
	   * pixel_share_2= 那個影藏影像share_2
	   */
	  case 0:
		  System.out.println("輸出影像"); 
	      RGB_img_share=getImagePixel(RGB_img_share,pixel_share_1);
	    	break;
	  default: 
	      System.out.println("其他數字 _ BUG"); 
	      break;
	}
	return RGB_img_share;	
	}
	public static    void Visual_Cryptography_share(String File_name ,String File_name_out,int W,int H,int share) throws IOException
	{
		 BufferedImage RGB_img = ImageIO.read(new File(File_name));
		 RGB_img=resize(W,H,RGB_img);
		 // 調整像素
        RGB_img=(BufferedImage) convert(RGB_img);
	      // 影像轉灰接
        RGB_img= to0_255(RGB_img);  
        //二值化
        //-------------------------------------------------------------------------------------//
        BufferedImage RGB_img_share =RGB_img;
        RGB_img_share=resize(W,H,RGB_img_share);
        //------------------------------------main-----------------------------------------------//
        RGB_img = RGB_img_share_Visual_Cryptography_share(RGB_img,RGB_img_share,share);
        ImageIO.write(RGB_img, "png", new File(File_name_out));
	}	
public static BufferedImage  RGB_img_share_Visual_Cryptography_share(BufferedImage RGB_img,BufferedImage RGB_img_share,int share) throws IOException {
		
		System.out.print("\n");
		  System.out.print("讀取影像 pixel \n");
		  int input=3;
	    int[]  [] pixels = getpixels(RGB_img);	
	    
	    int[] [] [] pixel_share_array = new int[input][RGB_img.getWidth()][RGB_img.getHeight()];
	    int[]  [] pixel_share_1 = new int[RGB_img.getWidth()][RGB_img.getHeight()];
        int[]  [] pixel_share_2 = new int[RGB_img.getWidth()][RGB_img.getHeight()];
        int[]  [] pixel_share_3 = new int[RGB_img.getWidth()][RGB_img.getHeight()];
        
         
        
        Binary   binary = new Binary();
        String  [] Binary_NN=binary.Binary_main(input);
        String   Binary ;
        System.out.print("Binary_NN.length= "+Binary_NN.length +"  \n" );
        
        for( int i=0;i<Binary_NN.length;i++)
        {
        	Binary=Binary_NN[i];
        	 System.out.print("Binary= "+Binary +"  " );
        }
    	System.out.print("\n");
        
             int  RGB_img_pixe=0;
    	    for(int i=0;i<RGB_img.getWidth();i++)
            {
            	  for(int j=0;j<RGB_img.getHeight();j++)
      	        {
            		  
            		  if(pixels[i][j]==255)
            		  {
            			  int 	r =(int)(Math.random()* 2);
           			   if(r==0) {
           			 
           				
           				for(int k=0;k<input;k++)
           				{
           					pixel_share_array[k][i][j]=0;  
               			
           				}
           			   }
           			
          			   if(r==1) {
          				 for(int k=0;k<input;k++)
            				{
            					pixel_share_array[k][i][j]=1;  
                			
            				}
          			   }  
    		              }
            		  if(pixels[i][j]==0)
            		  {
            			
            			//  int 	r =(int)(Math.random()* Binary_NN.length );//0~5 
            			
            			 
            			  if(RGB_img_pixe>=Binary_NN.length ) // 循環情況確保每次都中
            			  {
            				  RGB_img_pixe=0;
            			  }
            			  Binary=Binary_NN[RGB_img_pixe];
            			  for(int k=0;k<input;k++)
          				{
          					pixel_share_array[k][i][j]=Character.getNumericValue(Binary.charAt(k));  
              			
          				}
            			 // System.out.print("Binary_NN.length ="+Binary_NN.length+"RGB_img_pixe = "+RGB_img_pixe+"\n");
            			  RGB_img_pixe++;
            		  }
            		  
            		  
            		  
      	        }
            }
            	  
      	        
   
    	
    	pixel_share_1=binary.pixe(0, pixel_share_array, pixel_share_1);
    	pixel_share_2=binary.pixe(1, pixel_share_array, pixel_share_2);
    	pixel_share_3=binary.pixe(2, pixel_share_array, pixel_share_3);
    	pixel_share_1=pixel_share_0(pixel_share_1); // 分享影像1白色 跟 黑色一樣
    	   
        pixel_share_2=pixel_share_0(pixel_share_2); // 分享影像2  白色跟黑色 不一樣
        pixel_share_3=pixel_share_0(pixel_share_3); // 分享影像2  白色跟黑色 不一樣
         int[]  [] pixel_share = new int[pixel_share_1[0].length][pixel_share_1.length];
     pixel_share=pixel_share_AND(pixel_share,pixel_share_2,pixel_share_3);
     pixel_share=pixel_share_AND(pixel_share,pixel_share_1,pixel_share);
	//---------------------------------------------------------//
	  switch(share)
	  {
	  /*
	   * pixel_share = 該圖像
	   * pixel_share_1= 那個影藏影像share_1
	   * pixel_share_2= 那個影藏影像share_2
	   */
	  case 0:
		  System.out.println("輸出影像"); 
	      RGB_img_share=getImagePixel(RGB_img_share,pixel_share);
	    	break;
	  default: 
	      System.out.println("其他數字 _ BUG"); 
	      break;
	}
	return RGB_img_share;	
	}
	public static    void Visual_Cryptography(String File_name ,String File_name_out,int W,int H,int share) throws IOException
	{
		 BufferedImage RGB_img = ImageIO.read(new File(File_name));
		 RGB_img=resize(W,H,RGB_img);
		 // 調整像素
        RGB_img=(BufferedImage) convert(RGB_img);
	      // 影像轉灰接
        RGB_img= to0_255(RGB_img);  
        //二值化
        //-------------------------------------------------------------------------------------//
        String File_name_input="C:\\Users\\zx781\\OneDrive\\桌面\\2-1.png";
        BufferedImage RGB_img_share =ImageIO.read(new File(File_name_input));
        RGB_img_share=resize(W,H,RGB_img_share);
        RGB_img_share=(BufferedImage) convert(RGB_img_share);
	      // 影像轉灰接
        RGB_img_share= to0_255(RGB_img_share);
        //------------------------------------main-----------------------------------------------//
        RGB_img = RGB_img_share_Visual_Cryptography(RGB_img,RGB_img_share,share);
        ImageIO.write(RGB_img, "png", new File(File_name_out));
	}

	public static BufferedImage  RGB_img_share_Visual_Cryptography(BufferedImage RGB_img,BufferedImage RGB_img_share,int share) throws IOException {
		
		System.out.print("\n");
		  System.out.print("讀取影像 pixel \n");
	    int[]  [] pixels = getpixels(RGB_img);	    
	    System.out.print("讀取影像 pixel \n");
	    int[]  [] pixels_2 = getpixels(RGB_img_share);
	    System.out.print("share_ 1  \n");
	 
	    
	    
	    int[]  [] pixel_share_1 = new int[RGB_img.getWidth()][RGB_img.getHeight()];
        int[]  [] pixel_share_2 = new int[RGB_img.getWidth()][RGB_img.getHeight()];
        int[]  [] pixel_share_3 = new int[RGB_img_share.getWidth()][RGB_img_share.getHeight()];
        int[]  [] pixel_share_4 = new int[RGB_img_share.getWidth()][RGB_img_share.getHeight()];
        for(int i=0;i<pixel_share_1.length;i++)
        {
        	  for(int j=0;j<pixel_share_1[0].length;j++)
  	        {
        		  int 	r =(int)(Math.random()* 2);
        		  pixel_share_1[i][j]=r;
        		  
  	        }
  	        }
        
        
        for(int i=0;i<pixel_share_1.length;i++)
        {
        	  for(int j=0;j<pixel_share_1[0].length;j++)
  	        {
        	if( pixels[i][j]==255) // 255 為白色 
        	{
        		  if(  pixel_share_1[i][j]==1)
         		  {
          			pixel_share_2[i][j]=1;
         		  }
         		  if(  pixel_share_1[i][j]==0)
         		  {
         			 pixel_share_2[i][j]=0;
         		  }
        	}
        	else  // 黑色 部分 main 主要
        	{
      
      		  
      		if(  pixel_share_1[i][j]==1)
     		  {
     			pixel_share_2[i][j]=0;
     			
     		  }
      		
     		 if(  pixel_share_1[i][j]==0)
     		  {
     			pixel_share_2[i][j]=1;
     		  }
     		 
     		  
        	}
  	        }
  	        }
//-----------------------------------------------//
        for(int i=0;i<pixel_share_3.length;i++)
        {
        	  for(int j=0;j<pixel_share_3[0].length;j++)
  	        {
        	if( pixels_2[i][j]==255) // 255 為白色 
        	{
        		/*
        		  int 	r =(int)(Math.random()* 2);
        		  pixel_share_3[i][j]=r; //改進
        		  */
        		  if(  pixel_share_1[i][j]==1)
         		  {
          			pixel_share_3[i][j]=1;
         		  }
         		  if(  pixel_share_1[i][j]==0)
         		  {
         			 pixel_share_3[i][j]=0;
         		  }
        	}
        	else  // 黑色 部分
        	{
        		  
          		if(  pixel_share_1[i][j]==1)
         		  {
          			pixel_share_3[i][j]=0;
         		  }
         		  if(  pixel_share_1[i][j]==0)
         		  {
         			 pixel_share_3[i][j]=1;
         		  }
        	}
  	        }
  	        }

        
        pixel_share_1=pixel_share_0(pixel_share_1); // 分享影像1白色 跟 黑色一樣
   
       pixel_share_2=pixel_share_0(pixel_share_2); // 分享影像2  白色跟黑色 不一樣
       
       pixel_share_3=pixel_share_0(pixel_share_3); // 分享影像1白色 跟 黑色一樣
         int[]  [] pixel_share = new int[pixel_share_1[0].length][pixel_share_1.length];
     pixel_share=pixel_share_AND(pixel_share,pixel_share_1,pixel_share_3);
	    
	    
	    
	//---------------------------------------------------------//
	  switch(share)
	  {
	  /*
	   * pixel_share = 該圖像
	   * pixel_share_1= 那個影藏影像share_1
	   * pixel_share_2= 那個影藏影像share_2
	   */
	  case 0:
		  System.out.println("輸出影像"); 
	      RGB_img_share=getImagePixel(RGB_img_share,pixel_share_2);
	    	break;
	  default: 
	      System.out.println("其他數字 _ BUG"); 
	      break;
	      
	      
	}
	  
	return RGB_img_share;	
	}

	public static    void Visual_Cryptography_2(String File_name ,String File_name_out,int W,int H,int share) throws IOException
	{
		 BufferedImage RGB_img = ImageIO.read(new File(File_name));
		 RGB_img=resize(W,H,RGB_img);
		 // 調整像素
        RGB_img=(BufferedImage) convert(RGB_img);
	      // 影像轉灰接
        RGB_img= to0_255(RGB_img);  
        //二值化
        BufferedImage RGB_img_share ;
        RGB_img_share=resize(W,H,RGB_img);
        RGB_img = RGB_img_share_1_2(RGB_img,RGB_img_share,share);
        ImageIO.write(RGB_img, "png", new File(File_name_out));
	}
	public static    void Visual_Cryptography_convert(String File_name ,String File_name_out,int W,int H,int share) throws IOException
	{
		 BufferedImage RGB_img = ImageIO.read(new File(File_name));
		/* //讀取高度
		 int height = RGB_img.getHeight();
        //讀取寬度
        int width = RGB_img.getWidth();
        int [] gray_img = new int[width * height];
        gray_img = Gray(RGB_img);
        */
		 RGB_img=resize(W,H,RGB_img);
		 // 調整像素
        RGB_img=(BufferedImage) convert(RGB_img);
        RGB_img= to0_255(RGB_img);
        BufferedImage RGB_img_share ;
        RGB_img_share=resize(W,H,RGB_img);
        RGB_img = RGB_img_share_convert(RGB_img,RGB_img_share,share);
        ImageIO.write(RGB_img, "png", new File(File_name_out));
	}
	public  static  int[]  [] getpixels(BufferedImage RGB_img)
	{
	
		System.out.print("\n");
        int width = RGB_img.getWidth();
        int height = RGB_img.getHeight();
        int[]  [] pixels = new int[width][height];
        for(int i=0;i<width;i++)
        {
        	  for(int j=0;j<height;j++)
  	        {
        		  int rgb = RGB_img.getRGB(i, j);
        		  int r = (rgb & 0xff0000) >> 16;
                  int g = (rgb & 0x00ff00) >> 8;
                  int b = rgb & 0x0000ff;
                  int gray = (r + g + b) / 3;
                  pixels[i][j]=gray;
        		  System.out.print(" pixel =  ["+i+"] ["+ j+"] =  "+gray );
  	        }
        	  System.out.print("\n");
        }
		
		return pixels;
	}
public static BufferedImage  RGB_img_share_size_min(BufferedImage RGB_img,BufferedImage RGB_img_share,int share) throws IOException {
		
		System.out.print("\n");
		  System.out.print("讀取影像 pixel \n");
        //----------------------------------------//
   
     int[]  []  pixels=getpixels(RGB_img);
	  System.out.print("pixels[0].length ="+pixels[0].length + "   pixels .length ="+pixels.length +"\n");       
		 int[]  [] pixel_share_1 = new int[pixels.length][pixels[0].length];
	     int[]  [] pixel_share_2 = new int[pixels.length][pixels[0].length];
	     for(int i=0;i<pixels.length;i++)
	     {
	     	  for(int j=0;j<pixels[0].length;j++)
		        {
	     	if( pixels[i][j]==255) // 255 為白色
	     	{
	     		  int 	r =(int)(Math.random()* 6);// 0 到 5 所以6
	     		  pixel_share_1[i][j]=r;
	     		/*  if(  pixel_share_1[i][j]==0)
	       		  {
	       			pixel_share_2[i][j]=0;
	       		  }
	       		  if(  pixel_share_1[i][j]==1)
	       		  {
	       			pixel_share_2[i][j]=1;
	       		  }
	       		  */
	       		pixel_share_2[i][j]=  pixel_share_1[i][j];
	       		  //------------------------------------------------//
	       		    
	     	}
	     	else  // 黑色 部分
	     	{
	     		int 	r =(int)(Math.random()* 6);
	   		  pixel_share_1[i][j]=r;   
	   		  switch( pixel_share_1[i][j])
	          {
	     	case 0:
	     		pixel_share_2[i][j]= (pixel_share_1[i][j]+2);//0對2
	  	    	break;
	      	case 1:
	     		pixel_share_2[i][j]=(pixel_share_1[i][j]+2); //1對3
	  	    	break;
	      	case 2:
	     		pixel_share_2[i][j]=(pixel_share_1[i][j]-2);
	  	    	break;
	      	case 3:
	     		pixel_share_2[i][j]=(pixel_share_1[i][j]-2);
	  	    	break;
	      	case 4:
	     		pixel_share_2[i][j]=5;
	  	    	break;
	      	case 5:
	     		pixel_share_2[i][j]=4;
	  	    	break;
	 
	          }
	     	}
		        }
		        }
	   //  ImageShow(pixel_share_1);
	  //   ImageShow(pixel_share_2);
	     share_1_2  Share = new share_1_2();
	     pixel_share_1=Share.pixel_2_2share_1(pixel_share_1);
	     pixel_share_2=Share.pixel_2_2share_1(pixel_share_2);
	     int[]  [] pixel_share = new int[pixel_share_1[0].length][pixel_share_1.length];
	     pixel_share=pixel_share_AND(pixel_share,pixel_share_1,pixel_share_2);
     switch(share)
     {
     /*
      * pixel_share = 該圖像
      * pixel_share_1= 那個影藏影像share_1
      * pixel_share_2= 那個影藏影像share_2
      */
     case 0:
         RGB_img_share=getImagePixel(RGB_img_share,pixel_share);
	    	break;
     case 1:
         RGB_img_share=getImagePixel(RGB_img_share,pixel_share_1);
	    	break;
     case 2:
         RGB_img_share=getImagePixel(RGB_img_share,pixel_share_2);
	    	break;
     default: 
         System.out.println("其他數字 _ BUG"); 
         break;
  }
     //------------------------------------------------------//
     
     //------------------------------------------------------//
     
     return RGB_img_share;	
	}
public static BufferedImage  RGB_img_share_1_2(BufferedImage RGB_img,BufferedImage RGB_img_share,int share) throws IOException {
		
		System.out.print("\n");
		  System.out.print("讀取影像 pixel \n");
        int[]  [] pixels =  getpixels(RGB_img);
        //--------------------------------------------------//
        
        
        
        
        
        
        
        //--------------------------------------------------------//
   	 int[]  [] pixel_share_1 = new int[pixels.length][pixels[0].length];
     int[]  [] pixel_share_2 = new int[pixels.length][pixels[0].length];
     for(int i=0;i<pixels.length;i++)
     {
     	  for(int j=0;j<pixels[0].length;j++)
	        {
     	if( pixels[i][j]==255) // 255 為白色
     	{
     		  int 	r =(int)(Math.random()* 6);// 0 到 5 所以6
     		  pixel_share_1[i][j]=r;
     		/*  if(  pixel_share_1[i][j]==0)
       		  {
       			pixel_share_2[i][j]=0;
       		  }
       		  if(  pixel_share_1[i][j]==1)
       		  {
       			pixel_share_2[i][j]=1;
       		  }
       		  */
       		pixel_share_2[i][j]=  pixel_share_1[i][j];
       		  //------------------------------------------------//
       		    
     	}
     	else  // 黑色 部分
     	{
     		int 	r =(int)(Math.random()* 6);
   		  pixel_share_1[i][j]=r;   
   		  switch( pixel_share_1[i][j])
          {
     	case 0:
     		pixel_share_2[i][j]= (pixel_share_1[i][j]+2);//0對2
  	    	break;
      	case 1:
     		pixel_share_2[i][j]=(pixel_share_1[i][j]+2); //1對3
  	    	break;
      	case 2:
     		pixel_share_2[i][j]=(pixel_share_1[i][j]-2);
  	    	break;
      	case 3:
     		pixel_share_2[i][j]=(pixel_share_1[i][j]-2);
  	    	break;
      	case 4:
     		pixel_share_2[i][j]=5;
  	    	break;
      	case 5:
     		pixel_share_2[i][j]=4;
  	    	break;
 
          }
     	}
	        }
	        }
   //  ImageShow(pixel_share_1);
  //   ImageShow(pixel_share_2);
     
     share_1_2  Share = new share_1_2();
     pixel_share_1=Share.pixel_2_2share_1(pixel_share_1);
     pixel_share_2=Share.pixel_2_2share_1(pixel_share_2);
     int[]  [] pixel_share = new int[pixel_share_1[0].length][pixel_share_1.length];
     pixel_share=pixel_share_AND(pixel_share,pixel_share_1,pixel_share_2);
     switch(share)
     {
     /*
      * pixel_share = 該圖像
      * pixel_share_1= 那個影藏影像share_1
      * pixel_share_2= 那個影藏影像share_2
      */
     case 0:
         RGB_img_share=getImagePixel(RGB_img_share,pixel_share);
	    	break;
     case 1:
         RGB_img_share=getImagePixel(RGB_img_share,pixel_share_1);
	    	break;
     case 2:
         RGB_img_share=getImagePixel(RGB_img_share,pixel_share_2);
	    	break;
     default: 
         System.out.println("其他數字 _ BUG"); 
         break;
  }
     return RGB_img_share;	
	}
public static BufferedImage  RGB_img_share_size(BufferedImage RGB_img,BufferedImage RGB_img_share,int share) throws IOException {
		
		System.out.print("\n");
		  System.out.print("讀取影像 pixel \n");
        //----------------------------------------//
   
     int[]  []  pixels=pixels_size(getpixels(RGB_img));
     

	  System.out.print("pixels[0].length ="+pixels[0].length + "   pixels .length ="+pixels.length +"\n");       
	  
	 	 int[]  [] pixel_share_1 = new int[pixels.length][pixels[0].length];
	     int[]  [] pixel_share_2 = new int[pixels.length][pixels[0].length];
	     for(int i=0;i<pixels.length;i++)
	     {
	     	  for(int j=0;j<pixels[0].length;j++)
		        {
	     	if( pixels[i][j]==255) // 255 為白色
	     	{
	     		  int 	r =(int)(Math.random()* 6);// 0 到 5 所以6
	     		  pixel_share_1[i][j]=r;
	     		/*  if(  pixel_share_1[i][j]==0)
	       		  {
	       			pixel_share_2[i][j]=0;
	       		  }
	       		  if(  pixel_share_1[i][j]==1)
	       		  {
	       			pixel_share_2[i][j]=1;
	       		  }
	       		  */
	       		pixel_share_2[i][j]=  pixel_share_1[i][j];
	       		  //------------------------------------------------//
	       		    
	     	}
	     	else  // 黑色 部分
	     	{
	     		int 	r =(int)(Math.random()* 6);
	   		  pixel_share_1[i][j]=r;   
	   		  switch( pixel_share_1[i][j])
	          {
	     	case 0:
	     		pixel_share_2[i][j]= (pixel_share_1[i][j]+2);//0對2
	  	    	break;
	      	case 1:
	     		pixel_share_2[i][j]=(pixel_share_1[i][j]+2); //1對3
	  	    	break;
	      	case 2:
	     		pixel_share_2[i][j]=(pixel_share_1[i][j]-2);
	  	    	break;
	      	case 3:
	     		pixel_share_2[i][j]=(pixel_share_1[i][j]-2);
	  	    	break;
	      	case 4:
	     		pixel_share_2[i][j]=5;
	  	    	break;
	      	case 5:
	     		pixel_share_2[i][j]=4;
	  	    	break;
	 
	          }
	     	}
	     	
	     	
		        }
		        }
	   //  ImageShow(pixel_share_1);
	  //   ImageShow(pixel_share_2);
	     
	     share_1_2  Share = new share_1_2();
	     pixel_share_1=Share.pixel_2_2share_1(pixel_share_1);
	     pixel_share_2=Share.pixel_2_2share_1(pixel_share_2);
	     int[]  [] pixel_share = new int[pixel_share_1[0].length][pixel_share_1.length];
	     pixel_share=pixel_share_AND(pixel_share,pixel_share_1,pixel_share_2);
     switch(share)
     {
     /*
      * pixel_share = 該圖像
      * pixel_share_1= 那個影藏影像share_1
      * pixel_share_2= 那個影藏影像share_2
      */
     case 0:
         RGB_img_share=getImagePixel(RGB_img_share,pixel_share);
	    	break;
     case 1:
         RGB_img_share=getImagePixel(RGB_img_share,pixel_share_1);
	    	break;
     case 2:
         RGB_img_share=getImagePixel(RGB_img_share,pixel_share_2);
	    	break;
     default: 
         System.out.println("其他數字 _ BUG"); 
         break;
  }
     //------------------------------------------------------//
     
     return RGB_img_share;	
	}
public static BufferedImage  RGB_img_share_convert(BufferedImage RGB_img,BufferedImage RGB_img_share,int share) throws IOException {
	
	System.out.print("\n");
	  System.out.print("讀取影像 pixel \n");
    int width = RGB_img.getWidth();
    int height = RGB_img.getHeight();
    int[]  [] pixels = new int[width][height];
    for(int i=0;i<width;i++)
    {
    	  for(int j=0;j<height;j++)
	        {
    		  int rgb = RGB_img.getRGB(i, j);
    		  int r = (rgb & 0xff0000) >> 16;
              int g = (rgb & 0x00ff00) >> 8;
              int b = rgb & 0x0000ff;
              int gray = (r + g + b) / 3;
              pixels[i][j]=gray;
    		  System.out.print(" gray.pixel =  ["+i+"] ["+ j+"] =  "+gray );
	        }
    	  System.out.print("\n");
    }
  
//     
    //--------------------------------------------------//
//    ImageShow(pixels);
//---------------------------------------------------------//
    

	 int[]  [] pixel_share_1 = new int[width][height];
	 int[]  [] pixel_share_2 = new int[width][height];

    for(int i=0;i<pixel_share_1.length;i++)
    {
    	  for(int j=0;j<pixel_share_1[0].length;j++)
	        {
    	if( pixels[i][j]==255) // 255 為白色
    	{
    		  int 	r =(int)(Math.random()* 2);
    		  pixel_share_1[i][j]=r;
 
    			pixel_share_2[i][j]=  pixel_share_1[i][j]; //改進
    	}
    	else  // 黑色 部分
    	{
    		int 	r =(int)(Math.random()* 2);
  		  pixel_share_1[i][j]=r;
  		  if(  pixel_share_1[i][j]==1)
  		  {
  			pixel_share_2[i][j]=0;
  		  }
  		  if(  pixel_share_1[i][j]==0)
  		  {
  			pixel_share_2[i][j]=1;
  		  }
    	}
	        }
	        }

     pixel_share_1=pixel_share_0(pixel_share_1); // 分享影像1白色 跟 黑色一樣
    
     pixel_share_2=pixel_share_0(pixel_share_2); // 分享影像2  白色跟黑色 不一樣

      int[]  [] pixel_share = new int[pixel_share_1[0].length][pixel_share_1.length];
  pixel_share=pixel_share_AND(pixel_share,pixel_share_1,pixel_share_2);
  switch(share)
  {
  /*
   * pixel_share = 該圖像
   * pixel_share_1= 那個影藏影像share_1
   * pixel_share_2= 那個影藏影像share_2
   */
  case 0:
      RGB_img_share=getImagePixel(RGB_img_share,pixel_share);
    	break;
  case 1:
      RGB_img_share=getImagePixel(RGB_img_share,pixel_share_1);
    	break;
  case 2:
      RGB_img_share=getImagePixel(RGB_img_share,pixel_share_2);
    	break;
  default: 
      System.out.println("其他數字 _ BUG"); 
      break;
}
return RGB_img_share;	
}
 public static BufferedImage  RGB_img_share(BufferedImage RGB_img,BufferedImage RGB_img_share,int share) throws IOException {
		System.out.print("\n");
		  System.out.print("讀取影像 pixel \n");
        int width = RGB_img.getWidth();
        int height = RGB_img.getHeight();
        int[]  [] pixels = new int[width][height];
        for(int i=0;i<width;i++)
        {
        	  for(int j=0;j<height;j++)
  	        {
        		  int rgb = RGB_img.getRGB(i, j);
        		  int r = (rgb & 0xff0000) >> 16;
                  int g = (rgb & 0x00ff00) >> 8;
                  int b = rgb & 0x0000ff;
                  int gray = (r + g + b) / 3;
                  pixels[i][j]=gray;
        		  System.out.print(" pixel =  ["+i+"] ["+ j+"] =  "+gray );
  	        }
        	  System.out.print("\n");
        }
        //--------------------------------------------------//


        	 int[]  [] pixel_share_1 = new int[width][height];
             int[]  [] pixel_share_2 = new int[width][height];
    
             for(int i=0;i<width;i++)
             {
             	  for(int j=0;j<height;j++)
       	        {
             	if( pixels[i][j]==255) // 255 為白色
             	{
             		  int 	r =(int)(Math.random()* 2);
             		  pixel_share_1[i][j]=r;
       
             			pixel_share_2[i][j]=  pixel_share_1[i][j]; //改進
             	}
             	else  // 黑色 部分
             	{
             		int 	r =(int)(Math.random()* 2);
           		  pixel_share_1[i][j]=r;
           		  if(  pixel_share_1[i][j]==1)
           		  {
           			pixel_share_2[i][j]=0;
           		  }
           		  if(  pixel_share_1[i][j]==0)
           		  {
           			pixel_share_2[i][j]=1;
           		  }
             	}
       	        }
       	        }
         
              pixel_share_1=pixel_share_0(pixel_share_1); // 分享影像1白色 跟 黑色一樣
              pixel_share_2=pixel_share_0(pixel_share_2); // 分享影像2  白色跟黑色 不一樣
               int[]  [] pixel_share = new int[pixel_share_1[0].length][pixel_share_1.length];
           pixel_share=pixel_XOR(pixel_share,pixel_share_1,pixel_share_2);
           switch(share)
           {
           /*
            * pixel_share = 該圖像
            * pixel_share_1= 那個影藏影像share_1
            * pixel_share_2= 那個影藏影像share_2
            */
           case 0:
               RGB_img_share=getImagePixel(RGB_img_share,pixel_share);
     	    	break;
           case 1:
               RGB_img_share=getImagePixel(RGB_img_share,pixel_share_1);
     	    	break;
           case 2:
               RGB_img_share=getImagePixel(RGB_img_share,pixel_share_2);
     	    	break;
           default: 
               System.out.println("其他數字 _ BUG"); 
               break;
        }
        return RGB_img_share;	
	}
	public static int [][] pixels_size (int [][]pixels_size)
	{
        int[]  [] pixel_share_ = new int[pixels_size[0].length/4][pixels_size.length/4];
	      for(int I=0,i=0;I<pixels_size.length;I=I+4,i++)
	        {
	        	  for(int J=0,j=0;J<pixels_size[0].length;J=J+4,j++)
	  	        {
	        		  pixel_share_[i][j] =pixe_16(pixels_size,I,J);
	  	        }
	     
	  	        }
	
		return pixel_share_;
	}
 
	public static  int pixe_16(int [][] pixels_size,int I, int J )
	{


		     int [] pixe = new int[15] ;//0 1 2
            // 4*4=16 0 15 扣掉 原點 -1
		  	pixe[0]= pixels_size[I][J+1];//上底
		  	pixe[1]= pixels_size[I][J+2];
		  	pixe[2]= pixels_size[I][J+3];
		  	
			

		  	pixe[3]= pixels_size[I+1][J];//下底
			pixe[4]= pixels_size[I+2][J];
			pixe[5]= pixels_size[I+3][J];
			
		  	pixe[6]= pixels_size[I+1][J+1];//1
		  	
		  	pixe[7]= pixels_size[I+2][J+2];
		  	pixe[8]= pixels_size[I+2][J+1];
			pixe[9]= pixels_size[I+1][J+2];	//3
		
			
			pixe[10]= pixels_size[I+3][J+3];
			pixe[11]= pixels_size[I+3][J+1];
			pixe[12]= pixels_size[I+3][J+2];
			pixe[13]= pixels_size[I+1][J+3];
			pixe[14]= pixels_size[I+2][J+3];//5
			
			
			
			
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

	public static   BufferedImage getImagePixel (  BufferedImage bi,    int[]  [] pixel_share) throws IOException
	{
	      int width = bi.getWidth();
	        int height = bi.getHeight();
	        int[]  [] pixels = new int[width][height];
	        for(int i=0;i<width;i++)
	        {
	        	  for(int j=0;j<height;j++)
	  	        {
	                  int gray=pixel_share[i][j];
	                   if(gray>255)
	                   {
	                	   gray=255;
	                	   
	                   }
	                   if(gray<0)
	                   {
	                	   gray=0;
	                   }
	                  System.out.print(" getImagePixel =  ["+i+"] ["+ j+"] =  "+  gray );
	                  int Rgb=new Color(gray,gray,gray).getRGB();
	                  bi.setRGB(i,j,Rgb); //where i,j is the boundaries of the image

	                 
	  	        }
	        	  System.out.print("\n");
	        }
			 // ImageIO.write(bi, "png", new File(File_name_out));
			return bi;
	}
	public static   int[]  []pixel_share_ANDOR ( int[]  [] pixel_share, int[]  [] pixel_share_1,int[]  [] pixel_share_2)
	{
for(int i=0;i<pixel_share_1.length;i++)
{
	 for(int j=0 ;j<pixel_share_1[0].length;j++)
       {
		  pixel_share[i][j]=pixel_share_1[i][j]*pixel_share_2[i][j];
			if(pixel_share[i][j]>=255)
			{
				pixel_share[i][j]=255;
			}
			
		
			 
		
		
			}
	
		 
//255 *0 ;	255  *255  0 *0  利用 AND 去解
	
		 
       }
return pixel_share;

}
	
	
	public static  int[][] pixel_XOR( int[]  [] pixel_share, int[]  [] pixel_share_1,int[]  [] pixel_share_2)
	{
		
		for(int i=0;i<pixel_share_1.length;i++)
		{
			 for(int j=0 ;j<pixel_share_1[0].length;j++)
		       {
				 
				 
				
				 if(pixel_share_1[i][j]==0&&pixel_share_2[i][j]==0) // 嘿嘿
				 {
					
					 pixel_share[i][j]=0;//黑   
				 }
				 if(pixel_share_1[i][j]==0&&pixel_share_2[i][j]==255)// 黑  -- 白
				 {
					
					 pixel_share[i][j]=255; // 白 OK
				 }
				 if(pixel_share_1[i][j]==255&&pixel_share_2[i][j]==0) // 黑跟白
				 {
					
					 pixel_share[i][j]=255; // 白-OK
				 }
				 if(pixel_share_1[i][j]==255&&pixel_share_2[i][j]==255) // 白 白
				 {
					
					 pixel_share[i][j]=0;//  黑 - OK 	// 電腦沒限制 
				 }
	
			
				 
		       }

		}
		
		
		return pixel_share;
	}
	public static   int[]  []pixel_share_AND ( int[]  [] pixel_share, int[]  [] pixel_share_1,int[]  [] pixel_share_2)
	{

		for(int i=0;i<pixel_share_1.length;i++)
		{
			 for(int j=0 ;j<pixel_share_1[0].length;j++)
		       {
				 
				 
				
				 if(pixel_share_1[i][j]==0&&pixel_share_2[i][j]==0)
				 {
					
					 pixel_share[i][j]=0;
				 }
				 if(pixel_share_1[i][j]==0&&pixel_share_2[i][j]==255)
				 {
					
					 pixel_share[i][j]=0;
				 }
				 if(pixel_share_1[i][j]==255&&pixel_share_2[i][j]==0)
				 {
					
					 pixel_share[i][j]=0;
				 }
				 if(pixel_share_1[i][j]==255&&pixel_share_2[i][j]==255)
				 {
					
					 pixel_share[i][j]=255;
				 }
		//255 *0 ;	255  *255  0 *0  利用 AND 去解
			
				 
		       }

		}
//ImageShow(pixel_share_1);
return pixel_share;
	
	}
	public static int[]  [] pixel_share_2 ( int[]  [] pixel_share)
	{	    
		
        int[]  [] pixel_share_1 = new int[pixel_share[0].length*2][pixel_share.length*2];
        System.out.print("\n");
        System.out.print(" 擴張-OK \n");
        System.out.print("\n");
  	       //    ImageShow(pixel_share);
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
        	//	  ImageShow(pixel_share_1); //----OK---//     
        		  for(int i=0,I=0;I<pixel_share_1[0].length;i++,I=I+2)
        	        {
        	        	  for(int j=0,J=0;J<pixel_share_1.length;j++,J=J+2)
        	  	        {
        	        		  
        	        	//	 System.out.print("   pixel_share ["+I+"]"+"["+J+"] ="+pixel_share_1[I][J]);
        	        		 switch(pixel_share_1[I][J]) { //修改的地方  程式設定黑白的程式。
        	        		 //白色   
        	        		 case 0:
        	        		    	pixel_share_1[I][J]=0;	pixel_share_1[I][J+1]=255;
        	        		    
        	        		    	pixel_share_1[I+1][J]=0; pixel_share_1[I+1][J+1]=255;
        	        		    	
        	        		    	break;
        	         		    case 1:
        	        		    	pixel_share_1[I][J]=255; pixel_share_1[I][J+1]=0;
        	        		    	
        	        		    	pixel_share_1[I+1][J]=255;     	pixel_share_1[I+1][J+1]=0;
        	        		
        	        		    	break;
        	         		/*    case 2:
        	        		    	pixel_share_1[I][J]=0;     	pixel_share_1[I][J+1]=0;
        	        		
        	        		    	pixel_share_1[I+1][J]=255;     	pixel_share_1[I+1][J+1]=255;
        	        		
        	        		    	break;
        	         		    case 3:
        	        		    	pixel_share_1[I][J]=255;     	pixel_share_1[I][J+1]=255;
        	        		
        	        		    	pixel_share_1[I+1][J]=0; 	    	pixel_share_1[I+1][J+1]=0;
        	        	
        	        		    	break;
        	         		    case 4:
        	        		    	pixel_share_1[I][J]=0;  	pixel_share_1[I][J+1]=255;
        	        		   
        	        		    	pixel_share_1[I+1][J]=255; 	pixel_share_1[I+1][J+1]=0;
        	        		    
        	        		    	break;
        	         		    case 5:
        	        		    	pixel_share_1[I][J]=255; 	pixel_share_1[I][J+1]=0;
        	        		    
        	        		    	pixel_share_1[I+1][J]=0; 	pixel_share_1[I+1][J+1]=255;
        	        		    
        	        		    	break;
        	        		    	//--黑色--//
        	         			 case 6:
         	        		    	pixel_share_1[I][J]=255;	pixel_share_1[I][J+1]=0;
         	        		 
         	        		    	pixel_share_1[I+1][J]=255;  pixel_share_1[I+1][J+1]=0;
         	        		    	
         	        		    	break;
         	         		    case 7:
         	        		    	pixel_share_1[I][J]=0;	pixel_share_1[I][J+1]=255;
         	        		    
         	        		    	pixel_share_1[I+1][J]=0;  pixel_share_1[I+1][J+1]=255;
         	        		 
         	        		    	break;
         	         		    case 8:
         	        		    	pixel_share_1[I][J]=255; 	pixel_share_1[I][J+1]=255;
         	        		    
         	        		    	pixel_share_1[I+1][J]=0; 	pixel_share_1[I+1][J+1]=0;
         	        		    
         	        		    	break;
         	         		    case 9:
         	        		    	pixel_share_1[I][J]=0;  	pixel_share_1[I][J+1]=0;
         	        		   
         	        		    	pixel_share_1[I+1][J]=255; pixel_share_1[I+1][J+1]=255;
         	        		    	
         	        		    	break;
         	         		    case 10:
         	        		    	pixel_share_1[I][J]=255; 	pixel_share_1[I][J+1]=0;
         	        		    
         	        		    	pixel_share_1[I+1][J]=0;   	pixel_share_1[I+1][J+1]=255;
         	        		  
         	        		    	break;
         	         		    case 11:
         	        		    	pixel_share_1[I][J]=0; 	    	pixel_share_1[I][J+1]=255;
         	        	
         	        		    	pixel_share_1[I+1][J]=255;     	pixel_share_1[I+1][J+1]=0;
         	        		
         	        		    	break;
         	        		    	*/
        	        		    	
        	        		}
        	       
        	  	        }
        	  	        }
        		  System.out.print(" 插入黑白share _2-OK \n"); 
        		  ImageShow(pixel_share_1);
        		  System.out.print("\n");
				return pixel_share_1;
        		  
       
	}
	public static   int[][] pixel_share_0 ( int[]  [] pixel_share)
	{	    
		
        int[]  [] pixel_share_1 = new int[pixel_share[0].length][pixel_share.length];
        pixel_share_1=pixel_share;
        		  System.out.print(" 放入擴張-OK \n");
        		  System.out.print("\n");
        		  ImageShow(pixel_share_1); //----OK---//     
        		  for(int i=0;i<pixel_share_1[0].length;i++)
        	        {
        	        	  for(int j=0;j<pixel_share_1.length;j++)
        	  	        {
        	        		  
        	        	//	 System.out.print("   pixel_share ["+I+"]"+"["+J+"] ="+pixel_share_1[I][J]);
        	        		 switch(pixel_share_1[i][j]) { //修改的地方  程式設定黑白的程式。
        	        		    case 0:
        	        		    	pixel_share_1[i][j]=0;    	
        	        		    	break;
        	         		    case 1:
        	        		    	pixel_share_1[i][j]=255;   	
        	        		    	break;
        	        		}
        	  	        }
        	  	        }
        		  
        		  System.out.print(" 插入黑白-OK \n"); 
        		  ImageShow(pixel_share_1);
        		  System.out.print("\n");
				return pixel_share_1;
	}
	public static   int[][] pixel_share_1 ( int[]  [] pixel_share)
	{	    
		
        int[]  [] pixel_share_1 = new int[pixel_share[0].length*2][pixel_share.length*2];
        System.out.print("\n");
        System.out.print(" 擴張-OK \n");
        System.out.print("\n");
  	           ImageShow(pixel_share);
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
        		  ImageShow(pixel_share_1); //----OK---//     
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
        	        		    	pixel_share_1[I][J]=255;   	pixel_share_1[I][J+1]=0;
        	        		  
        	        		    	pixel_share_1[I+1][J]=255; 	pixel_share_1[I+1][J+1]=0;

        	        		    	break;
        	         		/*    case 2:
        	        		    	pixel_share_1[I][J]=0;    	pixel_share_1[I][J+1]=0;
        	        		 
        	        		    	pixel_share_1[I+1][J]=255;    	pixel_share_1[I+1][J+1]=255;
        	        		  
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
        	        		    	
        	        		    	*/
        	        		}
        	       
        	  	        }
        	  	        }
        		  System.out.print(" 插入黑白-OK \n"); 
        		  ImageShow(pixel_share_1);
        		  System.out.print("\n");
				return pixel_share_1;
        		  
       
	}
	public static void ImageShow( int[]  [] pixel_share)
	{

		for(int i=0;i<pixel_share[0].length;i++)
		{
			for(int j=0;j<pixel_share.length;j++)
			{
				 System.out.print(" pixel_share["+i+"]"+"["+j+"] ="+pixel_share[i][j]);
			}
			  System.out.println("\n");
		}
		
		  System.out.println("\n");
	}
	   //灰階轉換
    public static int[] Gray(BufferedImage RGB_img){
        int width = RGB_img.getWidth();
        int height = RGB_img.getHeight();
        int [] pixels = new int[width * height];
        int [] pixels_result = new int[width * height];
        // Get RGB pixels
        RGB_img.getRGB(0, 0, width, height, pixels, 0, width);
        /*
          RGB Process
          int red      = (pixel >> 16) & 0xff; 右移16位之後原来的高8位就在低8位的位置上了，再與0xff就只剩下了原来的高8位數值
          int green    = (pixel >>  8) & 0xff; 右移8位之後原来的中間8位就在低8位的位置上了，再與0xff就只剩下了原来的中間8位數值
          int blue     = (pixel      ) & 0xff; 與0xff就直接得到了低8位数值
        */
        for(int i = 0; i < width*height ; i++){
            int rgb = pixels[i];
            int red = (rgb & 0xff0000) >> 16;
            int green= (rgb & 0x00ff00) >> 8;
            int blue= rgb & 0x0000ff;
            //轉換灰階公式
            int gray = (int)(0.299 * red + 0.587 * green + 0.114 * blue); // 由 RGB 來計算 Y 值
            pixels[i] = (0xff000000 | gray<<16 | gray<<8 | gray);
            pixels_result[i] = gray; //解決黃點問題
        }
        BufferedImage gray_image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);  //image 轉 BfferedImage
        gray_image.setRGB(0, 0, width, height, pixels, 0, width);

        try {
            File file_gray = new File(File_name);
            ImageIO.write(gray_image, "jpg", file_gray);
        }catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		return pixels_result;
    }
    public static Image convert(BufferedImage source) throws IOException {
    int    width = source.getWidth();
    int    height = source.getHeight();
        int gray;
        int[] pixels = new int[width * height];
        source.getRGB(0, 0, width, height, pixels, 0, width);
        for (int y = 0; y < height; y++) {
          int index = y * width;
          for (int x = 0; x < width; x++) {
            int rgb = pixels[index];
            int r = (rgb & 0xff0000) >> 16;
            int g = (rgb & 0x00ff00) >> 8;
            int b = rgb & 0x0000ff;
            gray = (r + g + b) / 3;
            pixels[index++] = (0xff000000 | (gray << 16) | (gray << 8) | gray);
          }
        }
     

        BufferedImage bimage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        bimage.setRGB(0, 0, width, height, pixels, 0, width);
        File file_gray = new File(File_name_out);
        ImageIO.write(bimage, "png",file_gray);
        System.out.print(" OK ");
        return bimage;
      }
    public static BufferedImage resize(int targetWidth, int targetHeight,  BufferedImage src) {
        double scaleW = (double) targetWidth / (double) src.getWidth() ;
        double scaleH = (double) targetHeight / (double) src.getHeight() ;
        double scale = scaleW < scaleH ? scaleW : scaleH;
        BufferedImage result = new BufferedImage((int) (src.getWidth() * scale), (int) (src.getHeight() * scale), BufferedImage.TYPE_INT_ARGB);
    Graphics2D g2d = result.createGraphics();
        g2d.drawImage(src, 0, 0, result.getWidth(), result.getHeight(), null);
        g2d.dispose();
        return result;
    }
	public static BufferedImage  to0_255(BufferedImage source) throws IOException {
		  System.out.print("\n");
		 int    width = source.getWidth();
		    int    height = source.getHeight();
	        int[]  [] pixels = new int[width][height];
	        int n=0;
	        for(int i=0;i<width;i++)
	        {
	        	  for(int j=0;j<height;j++)
	  	        {
	        		  int rgb = source.getRGB(i, j);
	        		  int r = (rgb & 0xff0000) >> 16;
	                  int g = (rgb & 0x00ff00) >> 8;
	                  int b = rgb & 0x0000ff;
	                  int gray = (r + g + b) / 3;
	                  if(gray>255/2)
	                  {
	                	  gray=255;
	                		int max=new Color(gray,gray,gray).getRGB();
	                	  source.setRGB(i, j, max);
	                  }
	                  else
	                  {
	                	  gray=0;
	                 		int min=new Color(gray,gray,gray).getRGB();
	                	  source.setRGB(i, j, min);
	                  }
	        		  System.out.print(" gray ["+i+"] ["+ j+"] =  " +gray +" ");
	        		n++;
	  	        }
	        	  System.out.print("\n");
	        }
		  System.out.print("pixe = "+n);
		  ImageIO.write(source, "png", new File(File_name_out));
		return source;
	  }

}
