import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class Kadai10{
    public static void main(String[] args){
        List<String> strList = new ArrayList<String>(); 
        try{
            //ファイルの読み込み
            FileReader fr = new FileReader(args[0]);
            BufferedReader br = new BufferedReader(fr);
            while(true){
                String line = br.readLine();  //1行ずつ読み込む
                if(line == null){  //最後の行(null)までいったらループを抜ける
                    break;
                }
                strList.add(line);  //読み込んだものを "strList" に追加
            }
            br.close();
            
            //ファイルの書き込み
            FileWriter fw = new FileWriter(args[1]);
            BufferedWriter bw = new BufferedWriter(fw);

            for (int i = 0; i < strList.size(); i++) {
                bw.write(strList.get(i));   //中身を1コずつ取り出して書き込む
                bw.newLine();   //改行する
            }
            bw.close();

            System.out.println("書き込み完了");
        }catch(FileNotFoundException e){    //
            System.out.println("ファイルが開けません");
        }catch(IOException e){
            System.out.println("読み込みエラー");
        }
    }
}