import java.util.List;
import java.util.ArrayList;
//import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
//import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class Kadai1{
    public static void main(String[] args){
        List<String> strList = new ArrayList<String>(); 
        try{
            
            //ファイルの読み込み
                // FileReader fr = new FileReader(args[0]);
                // BufferedReader br = new BufferedReader(fr);
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(args[0]),"UTF-8");
            BufferedReader br = new BufferedReader(inputStreamReader);
            while(true){
                String line = br.readLine();  //1行ずつ読み込む
                if(line == null){  //最後の行(null)までいったらループを抜ける
                    break;
                }
                strList.add(line);  //読み込んだものを "strList" に追加
            }
            br.close();
            
            //ファイルの書き込み
                // FileWriter fw = new FileWriter(args[1]);
                // BufferedWriter bw = new BufferedWriter(fw);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(args[1]),"UTF-8");
            BufferedWriter bw = new BufferedWriter(outputStreamWriter);
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