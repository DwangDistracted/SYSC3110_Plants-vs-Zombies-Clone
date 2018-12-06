package ui;
import assets.*;
import util.Logger;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Class to load images.
 * 
 * @author Derek Shao modified David Wang
 *
 */
public class Images
{
	private static BufferedImage sunflowerImage = null;
	private static BufferedImage twinSunflowerImage = null;
	private static BufferedImage peashooterImage = null;
	private static BufferedImage repeaterPeashooterImage = null;
	private static BufferedImage potatoMineImage = null;
	private static BufferedImage airMonkeyImage = null;
	private static BufferedImage wallnutImage = null;
	private static BufferedImage tallnutImage = null;
	private static BufferedImage jalapenoImage = null;
  
	private static BufferedImage kernelpultImage = null;
	private static BufferedImage melonpultImage = null;
	private static BufferedImage snowshooterImage = null;
	
	private static BufferedImage regZombieImage = null;
	private static BufferedImage rushZombieImage = null;
	private static BufferedImage sprintZombieImage = null;
	private static BufferedImage tankZombieImage = null;
	private static BufferedImage yetiZombieImage = null;
	private static BufferedImage expZombieImage = null;
	private static BufferedImage jukZombieImage = null;
	private static BufferedImage airZombieImage = null;
	private static BufferedImage enragedZombieImage = null;
	
	private static BufferedImage mowerImage = null;
	private static BufferedImage grassImage = null;
	private static BufferedImage titleBanner = null;
	private static BufferedImage titleSplash = null;

	private static Logger LOG = new Logger("Images");
	
	private Images() {}

	/**
	 * Pre-load and cache all images
	 * 
	 * @return true if images successfully loaded, false otherwise
	 */
	public static boolean preloadImages() {
		try {
			sunflowerImage = ImageIO.read(new File("images/Plant_Sunflower.png"));
			twinSunflowerImage = ImageIO.read(new File("images/Twin_Flower.png"));
			repeaterPeashooterImage = ImageIO.read(new File("images/Repeater_Peashooter.png"));
			peashooterImage =  ImageIO.read(new File("images/Plant_Peashooter.png"));
			potatoMineImage = ImageIO.read(new File("images/Potato_Mine.png"));
			airMonkeyImage = ImageIO.read(new File("images/Air_Monkey.png"));
			regZombieImage =  ImageIO.read(new File("images/Zombie_Regular.png"));
			expZombieImage = ImageIO.read(new File("images/Exploding_Zombie.png"));
			jukZombieImage = ImageIO.read(new File ("images/Juking_Zombie.png"));
			airZombieImage = ImageIO.read(new File ("images/Air_Zombie.png"));
			wallnutImage = ImageIO.read(new File("images/Plant_Wallnut.png"));
			tallnutImage = ImageIO.read(new File("images/Plant_Tallnut.png"));
			kernelpultImage = ImageIO.read(new File("images/Plant_Kernelpult.png"));
			melonpultImage = ImageIO.read(new File("images/Plant_Melonpult.png"));
			snowshooterImage = ImageIO.read(new File("images/Plant_Snowshooter.png"));
			jalapenoImage = ImageIO.read(new File("images/Jalapeno.png"));
			
			rushZombieImage =  ImageIO.read(new File("images/Zombie_Rush.png"));
			sprintZombieImage =  ImageIO.read(new File("images/Zombie_Sprinter.png"));
			tankZombieImage = ImageIO.read(new File("images/Zombie_Tank.png"));
			yetiZombieImage = ImageIO.read(new File("images/Zombie_Yeti.png"));
			enragedZombieImage = ImageIO.read(new File("images/Enraged_Zombie.png"));
			
			mowerImage =  ImageIO.read(new File("images/Lawnmower.png"));
			grassImage = ImageIO.read(new File("images/GrassTile.jpg"));
			
			titleBanner =  ImageIO.read(new File("images/title-top-background.jpg"));
			titleSplash = ImageIO.read(new File("images/title-background.jpg"));
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			LOG.error("Failed to Load Images");
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
		case TWIN_FLOWER:
			return twinSunflowerImage;
		case PEASHOOTER:
			return peashooterImage;
		case REPEATER_PEASHOOTER:
			return repeaterPeashooterImage;
		case AIRMONKEY:
			return airMonkeyImage;
		case POTATOMINE:
			return potatoMineImage;
		case WALLNUT:
			return wallnutImage;
		case TALLNUT:
			return tallnutImage;
		case KERNELPULT:
			return kernelpultImage;
		case MELONPULT:
			return melonpultImage;
		case SNOWSHOOTER:
			return snowshooterImage;
		case JALAPENO:
			return jalapenoImage;
		default:
			LOG.error("Invalid Plant type");
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
		case RUSH_ZOMBIE:
			return rushZombieImage;
		case SPRINT_ZOMBIE:
			return sprintZombieImage;
		case TANK_ZOMBIE:
			return tankZombieImage;
		case YETI_ZOMBIE:
			return yetiZombieImage;
		case ENRAGED_ZOMBIE:
			return enragedZombieImage;
		default:
			LOG.error("Invalid zombie type");
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


