import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
//import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.BufferedWriter;
//import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class Kadai1{
    public static void main(String[] args){
        List<String> strList = new ArrayList<String>(); 
        try{
            // BufferedReader br = new BufferedReader(new FileReader("textA.txt"));
            
            //ファイルの読み込み
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(args[0]),"UTF-8"));
            while(true){
                String line = br.readLine();
                strList.add(line);
                if(line == null){
                    break;
                }
            }
            br.close();
            
            //ファイルの書き込み
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]),"UTF-8"));
            for (int i = 0; i < strList.size()-1; i++) {
                bw.write(strList.get(i));
                bw.newLine();
            }
            bw.close();

            System.out.println("書き込み完了");
        }catch(FileNotFoundException e){
            System.out.println("ファイルが開けません");
        }catch(IOException e){
            System.out.println("読み込みエラー");
        }
    }
}