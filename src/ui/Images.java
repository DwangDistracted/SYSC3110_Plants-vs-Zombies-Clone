package ui;
import assets.*;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Images
{
	
	private Images() {}
	
	public static Image getPlantImage(PlantTypes plant)
	{
		File projectDirectory = new File("");
		
		switch(plant) {
			case SUNFLOWER:
				try {
					String imagePath = projectDirectory.getAbsolutePath() + "\\images\\Plant_Sunflower.png";
					BufferedImage img = ImageIO.read(new File(imagePath));
					return img;
				} catch(IOException e) {
					
					e.printStackTrace();
				}
			case PEASHOOTER:
				try {
					String imagePath = projectDirectory.getAbsolutePath() + "\\images\\Plant_Peashooter.png";
					BufferedImage img = ImageIO.read(new File(imagePath));
					return img;
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			default:
				System.out.println("Invalid Plant type");
		}
		
		return null;
	}
	
	public static Image getZombieImage(ZombieTypes zombie) {
		
		File projectDirectory = new File("");
		
		switch(zombie) {
			case REG_ZOMBIE:
				try {
					String imagePath = projectDirectory.getAbsolutePath() + "\\images\\Zombie_Regular.png";
					BufferedImage img = ImageIO.read(new File(imagePath));
					return img;
				} catch(IOException e) {
					
					e.printStackTrace();
				}
			default:
				System.out.println("Invalid zombie type");
		}
		
		return null;
	}
	
	public static Image getLawnMowerImage() {
		
		File projectDirectory = new File("");
		
		try {
			String imagePath = projectDirectory.getAbsolutePath() + "\\images\\Lawnmower.jpg";
			BufferedImage img = ImageIO.read(new File(imagePath));
			return img;
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public static Image getGrassTileImage() {
		
		File projectDirectory = new File("");
		
		try {
			String imagePath = projectDirectory.getAbsolutePath() + "\\images\\Grass.jpg";
			BufferedImage img = ImageIO.read(new File(imagePath));
			return img;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}


