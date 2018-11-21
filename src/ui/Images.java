package ui;
import assets.*;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Class to load images.
 * 
 * @author Derek Shao
 *
 */

public class Images
{
	private static BufferedImage sunflowerImage = null;
	private static BufferedImage peashooterImage = null;
	private static BufferedImage potatoMineImage = null;
	private static BufferedImage airMonkeyImage = null;
	private static BufferedImage regZombieImage = null;
	private static BufferedImage expZombieImage = null;
	private static BufferedImage jukZombieImage = null;
	private static BufferedImage airZombieImage = null;
	private static BufferedImage mowerImage = null;
	private static BufferedImage grassImage = null;
	private static BufferedImage titleBanner = null;
	private static BufferedImage titleSplash = null;

	private Images() {}

	/**
	 * Pre-load and cache all images
	 * 
	 * @return true if images successfully loadaed, false otherwise
	 */
	public static boolean preloadImages() {
		try {
			sunflowerImage = ImageIO.read(new File("images\\Plant_Sunflower.png"));
			peashooterImage =  ImageIO.read(new File("images\\Plant_Peashooter.png"));
			potatoMineImage = ImageIO.read(new File("images\\Potato_Mine.png"));
			airMonkeyImage = ImageIO.read(new File("images\\Air_Monkey.png"));
			regZombieImage =  ImageIO.read(new File("images\\Zombie_Regular.png"));
			expZombieImage = ImageIO.read(new File("images\\Exploding_Zombie.png"));
			jukZombieImage = ImageIO.read(new File ("images\\Juking_Zombie.png"));
			airZombieImage = ImageIO.read(new File ("images\\Air_Zombie.png"));
			mowerImage =  ImageIO.read(new File("images\\Lawnmower.png"));
			grassImage = ImageIO.read(new File("images\\GrassTile.jpg"));
			titleBanner =  ImageIO.read(new File("images\\title-top-background.jpg"));
			titleSplash = ImageIO.read(new File("images\\title-background.jpg"));
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Get the image for a specified plant type
	 * 
	 * @param plant type
	 * @return plant image
	 */
	public static Image getPlantImage(PlantTypes plant)
	{
		switch(plant) {
		case SUNFLOWER:
			return sunflowerImage;
		case PEASHOOTER:
			return peashooterImage;
		case AIRMONKEY:
			return airMonkeyImage;
		case POTATOMINE:
			return potatoMineImage;
		default:
			System.out.println("Invalid Plant type");
		}

		return null;
	}

	/**
	 * Get the image for a specified zombie type
	 * 
	 * @param zombie
	 * @return zombie image
	 */
	public static Image getZombieImage(ZombieTypes zombie) {
		switch(zombie) {
		case REG_ZOMBIE:
			return regZombieImage;
		case EXP_ZOMBIE:
			return expZombieImage;
		case JUK_ZOMBIE:
			return jukZombieImage;
		case AIR_ZOMBIE:
			return airZombieImage;
		default:
			System.out.println("Invalid zombie type");
		}

		return null;
	}

	/**
	 * Get the lawn mower image
	 * 
	 * @return lawn mower image
	 */
	public static Image getLawnMowerImage() {
		return mowerImage;
	}

	/**
	 * Get the grass tile image
	 * 
	 * @return grass tile image
	 */
	public static Image getGrassTileImage() {
		return grassImage;
	}
	
	/**
	 * Get the title banner image.
	 * 
	 * @return title banner image
	 */
	public static Image getTitleBannerImage() {
		return titleBanner;
	}
	
	/**
	 * Get the title splash image.
	 * 
	 * @return title splash image
	 */
	public static Image getTitleSplashImage() {
		return titleSplash;
	}
	
	
}


