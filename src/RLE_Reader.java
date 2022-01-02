package GameOfLife;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.rmi.server.ExportException;

public class RLE_Reader {

    public static int[][] getPattern(String name){
        try {
            File file = new File("./res/" + name);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            int[][] pattern = null;
            int r=0, c=0;
            while ((st = br.readLine()) != null) {
                if(st.substring(0,1).equals("#")) { //comment line
                    System.out.println(st);
                }
                else if(st.substring(0,1).equals("x")) {  //dimension line
                    int commaLoc = st.indexOf(",");
                    int cols = Integer.parseInt(st.substring(4,commaLoc));
                    int nextCommaLoc = st.indexOf(",", commaLoc+1);
                    int rows = Integer.parseInt(st.substring(commaLoc+6,nextCommaLoc));
                    pattern = new int[rows][cols];
                    System.out.println(pattern.length);
                    System.out.println(pattern[0].length);
                }else{ //pattern line
                    int i = 0;
                    int len = st.length();
                    int run = 0;
                    while(i < len){
                        char current = st.charAt(i);
                        if(Character.isDigit(current)){
                            run = Integer.parseInt(current+"");
                            if(Character.isDigit(st.charAt(i+1))){
                                run = Integer.parseInt(st.substring(i,i+2));
                                i++;
//                                if(Character.isDigit(st.charAt(i+2)))
//                                {
//                                    run = Integer.parseInt(st.substring(i, i+3));
//                                    i++;
//                                }
                            }
                            System.out.println( run);
                        }
                        if(current=='b'){
                            if(run == 0) {
                                pattern[r][c] = 0;
                                c++;
                            }
                            while(run > 0){
                                pattern[r][c] = 0;
                                c++;
                                run--;
                            }
                        }if(current=='o'){
                            if(run == 0) {
                                pattern[r][c] = 1;
                                c++;
                            }
                            while(run > 0){
                                pattern[r][c] = 1;
                                c++;
                                run--;
                            }
                        }if(current=='$'){
                            r++;
                            c=0;
                        }
                        i++;
                    }
                }
            }
            return pattern;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
