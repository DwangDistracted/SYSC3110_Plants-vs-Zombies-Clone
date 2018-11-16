package ui;
import assets.*;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Images
{
	private static final File PROJECT_DIRECTORY = new File(""); 
	private static final String SUNFLOWER_IMAGE_PATH = PROJECT_DIRECTORY.getAbsolutePath() + "\\images\\Plant_Sunflower.png";
	private static final String PEASHOOTER_IMAGE_PATH = PROJECT_DIRECTORY.getAbsolutePath() + "\\images\\Plant_Peashooter.png";
	private static final String REG_ZOMBIE_IMAGE_PATH = PROJECT_DIRECTORY.getAbsolutePath() + "\\images\\Zombie_Regular.png";
	private static final String DEFAULT_IMAGE_PATH = PROJECT_DIRECTORY.getAbsolutePath() + "\\images\\Grass.jpg";
	private static BufferedImage SUNFLOWER_IMAGE;
	private static BufferedImage PEASHOOTER_IMAGE;
	private static BufferedImage REG_ZOMBIE_IMAGE;
	private static BufferedImage DEFAULT_IMAGE;
	
	public static Images __Images = new Images();
	
	private Images() {
		File projectDirectory = new File("");
		
		try {
			SUNFLOWER_IMAGE = ImageIO.read(new File(SUNFLOWER_IMAGE_PATH));
			PEASHOOTER_IMAGE = ImageIO.read(new File(PEASHOOTER_IMAGE_PATH));
			REG_ZOMBIE_IMAGE = ImageIO.read(new File(REG_ZOMBIE_IMAGE_PATH));
			DEFAULT_IMAGE = ImageIO.read(new File(DEFAULT_IMAGE_PATH));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static Image getPlantImage(PlantTypes plant)
	{
		File projectDirectory = new File("");
		
		switch(plant) {
			case SUNFLOWER:
				return SUNFLOWER_IMAGE;
			case PEASHOOTER:
				return PEASHOOTER_IMAGE;
			default:
				System.out.println("Invalid Plant type");
		}
		
		return null;
	}
	
	public static Image getZombieImage(ZombieTypes zombie) {
		
		File projectDirectory = new File("");
		
		switch(zombie) {
			case REG_ZOMBIE:
				return REG_ZOMBIE_IMAGE;
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


