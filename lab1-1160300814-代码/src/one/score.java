package one;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class score {
	public static void main(String[] args) throws IOException {
		File file1 = new File("src\\Untitled 1.txt");
		InputStreamReader reader1 = new InputStreamReader(new FileInputStream("src\\Untitled 1.txt")); 
	    BufferedReader br1 = new BufferedReader(reader1);
	    File file2 = new File("src\\seg_FMM.txt");
		InputStreamReader reader2 = new InputStreamReader(new FileInputStream("src\\seg_FMM.txt")); 
	    BufferedReader br2 = new BufferedReader(reader2);
		String line1 = "",line2 = "",line3 = ""; 
		int fc=0,bc=0,n=0;
		int size1=0,s2=0,s3=0;
		line1 = br1.readLine();
		line2 = br2.readLine();
		while (line1!=null&&line2!=null&&line3!=null) {
			int flag=0;
			n++;
			String[] d1=line1.replaceAll("\\]", "").replaceAll("\\[", "").replaceAll("[a-z]", "").replaceAll("[A-Z]", "").split("\\s+");
			String[] d2=line2.split(" ");
			String[] d3=line3.split(" ");
			size1=size1+d1.length-1;
			s2+=d2.length;
			s3+=d3.length;			
			for(int i=0;i<d2.length;i++) {
				for(int j=1;j<d1.length;j++)
				if(d2[i].equals(d1[j])) {
					fc++;	
					break;
					}			
			}				
			for(int i=0;i<d3.length;i++) {								
				for(int j=1;j<d1.length;j++)
					if(d3[i].equals(d1[j])) {
						bc++;	
						break;
						}
			}
			line1 = br1.readLine(); 
			line2 = br2.readLine(); 
		}		
		double fp=(double)fc/s2;
		double fr=(double)fc/size1;
		double bp=(double)bc/s3;
		double br=(double)bc/size1;
		File file = new File("src\\score.txt");		
        file.createNewFile();                               
	    FileWriter fw = new FileWriter(file,false);
	    fw.write("FMM precision: "+fp+" recall: "+fr+" F: "+2*fp*fr/(fp+fr)+"\n"); 
		fw.flush();
		fw.close();	
	}
}
