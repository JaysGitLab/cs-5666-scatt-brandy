package scatt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class Sprite {	
	
	/**
	 * Static method to get sprite count from json file STRING
	 * @param jsonFile String representation of the json file
	 * @return number of sprites, -1 if spriteCount not found
	 */
	public static int getCountFromString(String jsonFile){
		if(!jsonFile.contains("spriteCount")){
			return -1;
		}
		int scIndex = jsonFile.lastIndexOf("\"spriteCount\":");
		int comma = jsonFile.indexOf(',', scIndex);
		String ss = jsonFile.substring(scIndex + 15, comma);
		return Integer.parseInt(ss);
				
	}
	/**
	 * Static method that returns the number of sprites in a json file.
	 * @param jsonTextFile json file
	 * @return number of sprites, -1 if spriteCount not found, -2 if IOException 
	 */
	public static int getCountFromFile(File jsonTextFile){
		try {
			FileReader fr = new FileReader(jsonTextFile);
			BufferedReader br = new BufferedReader(fr);
			String fileStr = "";
			String line = br.readLine();
			while(line != null){
				fileStr += line;
				line = br.readLine();
			}
			br.close();
			fr.close();
			return getCountFromString(fileStr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return -2;
		}
	}

}
