package ui;
import assets.*;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;




public class Images
{
	public Image getImage(Unit unit)
	{
		
	    BufferedImage image = null;
	    Image newImage = null;
	    URL url = null;
	    
	    
	    if(unit instanceof Flower)
	    {
	    	 try
	    	 {
	    		url = new URL("https://vignette.wikia.nocookie.net/vsbattles/images/3/31/Sunflower_HD.png/revision/latest?cb=20171107053038.JPG");
	 	        image = ImageIO.read(url);
	 	        newImage = image.getScaledInstance(80,80, Image.SCALE_DEFAULT);
	 	     } 
	    	 catch (MalformedURLException ex) 
	    	 {
	 	        System.out.println("Malformed URL");
	 	        return null;
	 	     }
	    	 catch (IOException iox)
	    	 {
	 	        System.out.println("Can not load file");
	 	        return null;
	 	     }
	    	
	    	return newImage;
	    }
	    else if(unit instanceof Peashooter)
	    {
	    	 try
	    	 {
	    		 url = new URL("https://vignette.wikia.nocookie.net/vsbattles/images/e/e6/IMG_5964.png/revision/latest?cb=20171216115102.jpg");
	    		 image = ImageIO.read(url);
	    		 newImage = image.getScaledInstance(80,80, Image.SCALE_DEFAULT);
	 	     } 
	    	 catch (MalformedURLException ex) 
	    	 {
	    		 System.out.println("Malformed URL");
	 	         return null;
	 	     }
	    	 catch (IOException iox)
	    	 {
	    		 System.out.println("Can not load file");
	    		 return null;
	 	     }
	    	return newImage;
	    }
	    else if(unit instanceof Regular_Zombie)
	    {
	    	 try
	    	 {
	 	        url = new URL("https://vignette.wikia.nocookie.net/vsbattles/images/6/6d/Reckoning.Patty.Walker.png/revision/latest?cb=20170522111709.jpg");
	 	        image = ImageIO.read(url);
	 	        newImage = image.getScaledInstance(80,80, Image.SCALE_DEFAULT);
	 	     } 
	    	 catch (MalformedURLException ex) 
	    	 {
	 	        System.out.println("Malformed URL");
	 	        return null;
	 	     }
	    	 catch (IOException iox)
	    	 {
	 	        System.out.println("Can not load file");
	 	        return null;
	 	     }
	    	return newImage;
	    }
	    
	    
	    return null;
	    
	}

	public Image getImage(Plant plant) 
	{
		BufferedImage image = null;
	    Image newImage = null;
	    URL url = null;
	    
	    
	    if(plant instanceof Flower)
	    {
	    	 try
	    	 {
	    		url = new URL("https://vignette.wikia.nocookie.net/vsbattles/images/3/31/Sunflower_HD.png/revision/latest?cb=20171107053038.JPG");
	 	        image = ImageIO.read(url);
	 	        newImage = image.getScaledInstance(80,80, Image.SCALE_DEFAULT);
	 	     } 
	    	 catch (MalformedURLException ex) 
	    	 {
	 	        System.out.println("Malformed URL");
	 	        return null;
	 	     }
	    	 catch (IOException iox)
	    	 {
	 	        System.out.println("Can not load file");
	 	        return null;
	 	     }
	    	
	    	return newImage;
	    }
	    else if(plant instanceof Peashooter)
	    {
	    	 try
	    	 {
	    		 url = new URL("https://vignette.wikia.nocookie.net/vsbattles/images/e/e6/IMG_5964.png/revision/latest?cb=20171216115102.jpg");
	    		 image = ImageIO.read(url);
	    		 newImage = image.getScaledInstance(80,80, Image.SCALE_DEFAULT);
	 	     } 
	    	 catch (MalformedURLException ex) 
	    	 {
	    		 System.out.println("Malformed URL");
	 	         return null;
	 	     }
	    	 catch (IOException iox)
	    	 {
	    		 System.out.println("Can not load file");
	    		 return null;
	 	     }
	    	return newImage;
	    }
		return null;
	}
	
	public Image getImage2(Plant plant) throws IOException
	{
		Image image = null;
		if(plant instanceof Peashooter)
		{
	    	 ImageIcon img = new ImageIcon("images\\IMG_5964.JPG");
			 image = img.getImage(); // transform it
			 image = image.getScaledInstance(80,80, Image.SCALE_DEFAULT);
	    	 
	    	 return image;
	    }
		else if(plant instanceof Flower)
		{
			 ImageIcon img = new ImageIcon("images\\Sunflower_HD.JPG");
			 image = img.getImage(); // transform it
			 image = image.getScaledInstance(80,80, Image.SCALE_DEFAULT);
	    	 
	    	 return image;
		}
		
		return null;
	}
}


