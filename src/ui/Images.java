package ui;
import assets.*;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Images
{
	private static BufferedImage sunflowerImage = null;
	private static BufferedImage peashooterImage = null;
	private static BufferedImage regZombieImage = null;
	private static BufferedImage mowerImage = null;
	private static BufferedImage grassImage = null;

	private Images() {}

	public static boolean preloadImages() {
		try {
			sunflowerImage = ImageIO.read(new File("images\\Plant_Sunflower.png"));
			peashooterImage =  ImageIO.read(new File("images\\Plant_Peashooter.png"));
			regZombieImage =  ImageIO.read(new File("images\\Zombie_Regular.png"));
			mowerImage =  ImageIO.read(new File("images\\Lawnmower.jpg"));
			grassImage = ImageIO.read(new File("images\\Grass.jpg"));
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	public static Image getPlantImage(PlantTypes plant)
	{
		switch(plant) {
		case SUNFLOWER:
			return sunflowerImage;
		case PEASHOOTER:
			return peashooterImage;
		default:
			System.out.println("Invalid Plant type");
		}

		return null;
	}

	public static Image getZombieImage(ZombieTypes zombie) {
		switch(zombie) {
		case REG_ZOMBIE:
			return regZombieImage;
		default:
			System.out.println("Invalid zombie type");
		}

		return null;
	}

	public static Image getLawnMowerImage() {
		return mowerImage;
	}

	public static Image getGrassTileImage() {
		return grassImage;
	}
}


