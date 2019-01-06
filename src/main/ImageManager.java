package main;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class ImageManager {
	BufferedImage image;
	
	public Image getImage(String filename){
		URL url=this.getClass().getClassLoader().getResource(filename);
		System.out.println(url);
		
		try {
			image=ImageIO.read(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}	
}
