package one;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class fmm {

    static String[] dict = new String[58000];
    static String[] d1 = new String[50];
    static int dictlen;
    private static int b;

    public static String[] readfile(String filename, int size) throws IOException {
        int i = 1, a = 0;
        File file = new File(filename);
        InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
        BufferedReader br = new BufferedReader(reader);
        String line = "";
        String[] d = new String[size];
        line = br.readLine();
        while (line != null) {
            d[a] = line;
            a++;
            line = br.readLine();
        }
        return d;
    }

    public static boolean find(String str) {
        if (str.length() > 7) {
            for (int a = 0; dict[a].length() - 1 != 7 && a < dictlen - 1; a++) {
                if (dict[a].replaceAll("/", "").equals(str))
                    return true;
            }
        } else if (str.length() == 1)
            for (int a = dictlen - 1; a > -1; a--) {
                if (dict[a].replaceAll("/", "").equals(str))
                    return true;
            }
        else {
            int a = 0;
            b = dictlen - 1;
            int mid = (a + b) / 2;
            while (dict[mid].length() - 1 != str.length()) {
                if (dict[mid].length() - 1 > str.length()) {
                    a = mid + 1;
                    mid = (a + b) / 2;
                } else if (dict[mid].length() - 1 < str.length()) {
                    b = mid - 1;
                    mid = (a + b) / 2;
                }
            }
            for (int i = mid; dict[i].length() - 1 == str.length() && i > -1; i--) {
                if (dict[i].replaceAll("/", "").equals(str)) {
                    return true;
                }
            }
            for (int j = mid + 1; dict[j].length() - 1 == str.length() && j < dictlen - 1; j++) {
                if (dict[j].replaceAll("/", "").equals(str))
                    return true;
            }
        }
        return false;
    }

    public static String FMM(String str) throws IOException {
        String s = "";
        if (str == null)
            s = "";
        else {
            int max = dict[0].length() - 1;
            if (str.length() < max)
                max = str.length();
            int start = 0, end = str.length();
            while (start < str.length()) {
                if (start + max < str.length() + 1)
                    end = start + max;
                else
                    end = str.length();
                while (!find(str.substring(start, end)) && end > -1 && end != start) {
                    end--;
                    if (end == start)
                        break;
                }
                if (end == start) {
                    end = end + 1;
                }
                s = s + str.substring(start, end) + "/ ";
                start = end;
            }
        }
        return s + "\n";
    }

    public static void main(String[] args) throws IOException {
        String[] sent = readfile("src\\Untitled 2.txt", 25000);
        String[] d = readfile("src\\dic.txt", 1500);
        int i = 0;
        for (i = 0; d[i + 1] != null; i++) {
            d1 = d[i].split(" ");
            System.arraycopy(d1, 0, dict, i * 50, 50);
        }
        d1 = d[i].split(" ");
        System.arraycopy(d1, 0, dict, i * 50, d1.length);
        dictlen = i * 50 + d1.length;
        List<String> fmm = new ArrayList<String>();
        List<String> bmm = new ArrayList<String>();
        long startTime = System.currentTimeMillis();
        for (int q = 0; sent[q] != null; q++) {
            fmm.add(q, FMM(sent[q]));
             //System.out.println(q);
        }
        File file = new File("src\\seg_FMM.txt");
        file.createNewFile();
        FileWriter fw = new FileWriter(file, false);
        for (int q = 0; q < fmm.size(); q++)
            fw.write(fmm.get(q));
        fw.flush();
        fw.close();

        long endTime = System.currentTimeMillis();
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");
    }
}