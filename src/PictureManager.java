import java.awt.List;
import java.io.File;
import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Objects;

public class PictureManager {
	
	
	public static void main(String[] args) {

		final String PICTURES_FOLDER_PATH = "C:\\Users\\Lucas\\Pictures\\_fotos";

		ArrayList<String> nameList = new ArrayList<String>();
		ArrayList<String> md5List = new ArrayList<String>();
		ArrayList<String> nameListToDelete = new ArrayList<String>();
		ArrayList<String> md5ListToDelete = new ArrayList<String>();	

		File folder = new File(PICTURES_FOLDER_PATH);
		Boolean isDeleted = false;

		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {

				String list = listOfFiles[i].getName();				

				MD5Manager file = new MD5Manager(PICTURES_FOLDER_PATH + "\\" + list);
				String md5 = file.getMD5();

				nameList.add(list);
				md5List.add(md5);
				
				System.out.println(list + ", " + md5);

			}
		}

		if (nameList.size() == md5List.size()) {
			int tam = nameList.size();
			for (int i = 0; i < tam; i++) { // comparacao entre o index 0 e todos os elementos do outro array
				for (int k = i + 1; k < tam; k++) {
					if ((nameList.get(i).equals(nameList.get(k))) || (md5List.get(i).equals(md5List.get(k)))  ) { 
						//System.out.println(nameList.get(i)+ ", " + md5List.get(k)); //arquivos para serem deletados
						
						nameListToDelete.add(nameList.get(i));
						md5ListToDelete.add(md5List.get(k));
											
						break; //o break faz com que seja feita apenas uma comparação, impedindo dupla comparacao do mesmo arquivo
					}

				}
			}
		}		
		
		try{
			for(int i = 0 ; i < nameListToDelete.size() ; i++){
				folder = new File(PICTURES_FOLDER_PATH+"\\"+nameListToDelete.get(i));
				isDeleted = folder.delete();
				if(isDeleted){
					System.out.println("Copied file deleted sucessfully!");
				}
				else{
					System.out.println("Error while trying to delete file!");
				}
			}
		}catch(Exception e){
			System.out.println(e.toString());
		}

		
		
	}
}
