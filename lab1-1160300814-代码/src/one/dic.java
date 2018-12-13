package one;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class dic {
    public static void main(String[] args) throws IOException  {
    File file = new File("src\\199801_segUntitled 1.txt");
    InputStreamReader reader = new InputStreamReader(  
                new FileInputStream("src\\Untitled 1.txt")); 
    BufferedReader br = new BufferedReader(reader); 
    String line = ""; 
    int i=1,a=0;
    String[] d=new String [300];
    String[] c=new String [300];
    Map<String, Integer> words = new HashMap<String, Integer>();
    line = br.readLine();
    String l[] = new String[23040]; 
    List<String> cx=new ArrayList<String>();
    while (line!=null) {        
        c=line.replaceAll("\\]", "").replaceAll("\\[", "").split("\\s+");
        for(int j=0;j<c.length;j++) {
            String[] q=c[j].split("/");
            if(q.length==2)
               cx.add(q[1]);
        }
        d = line.replaceAll("\\]", "").replaceAll("\\[", "").replaceAll("[a-z]", "").replaceAll("[A-Z]", "").split("\\s+");         
        for(i=1;i<d.length;i++) {
            String s =d[i].replaceAll("[a-z]", "");
            
            if(!words.containsKey(s))
            {
                words.put(s, s.length());
            }
                        
        }
        line = br.readLine();               
        i++;                             
    } 
    List<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String,Integer>>(words.entrySet());
    Collections.sort(list,new Comparator<Map.Entry<String,Integer>>() {
        public int compare(Entry<String, Integer> o1,
                Entry<String, Integer> o2) {
            return o2.getValue().compareTo(o1.getValue());
        }        
    });
    int v=0,ns=0;
    for(int j=0;j<cx.size();j++) {
        if(cx.get(j).equals("v"))
            v++;
        if(cx.get(j).equals("ns"))
            ns++;   
    }
    System.out.println("×ÖµäÖÐÓÐ´Ê:"+(v+ns)+"¸ö  v:" + v+"; ns:"+ns);
    int n=0;
    File f = new File("src\\dic.txt");
    f.createNewFile();
    FileWriter fw = new FileWriter(f,false);
    BufferedWriter bw = new BufferedWriter(fw);
    for(Map.Entry<String,Integer> mapping:list){        
        bw.write(mapping.getKey()+" ");
        n++;
        if(n%50==0) bw.write("\n");
      }
    bw.flush();  
    bw.close();
    fw.close();
    
    }
}