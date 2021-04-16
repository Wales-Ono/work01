import java.util.List;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;


public class Kadai3{
    public static void main(String[] args){
        List<String> strList = new ArrayList<String>();
        try{
            //----------------ファイルの読み込み----------------------------
            FileReader fr = new FileReader(args[0]);
            BufferedReader br = new BufferedReader(fr);
            while(true){
                String line = br.readLine();
                if(line == null){
                    break;
                }
                strList.add(line);
            }
            br.close();

            //----------------ファイルの出力--------------------------
            //順番に表示
            for(int i=0;i<strList.size();i++){
                System.out.println(strList.get(i));
            }
            
            //逆順に表示
            for(int i=1;i<=strList.size();i++){
                System.out.println(strList.get(strList.size()-i));
            }
                // for(int i=strList.size()-1;i>=0;i--){
                //     System.out.println(strList.get(i));
                // }  とも書ける

            //「あいうえお」が格納されている行番号を出力（先頭行を1行目と数える）
            int index = strList.indexOf("あいうえお");
            System.out.println(index+1 + "行目");
        }catch(FileNotFoundException e){
            System.out.println("ファイルが開けません");
        }catch(IOException e){
            System.out.println("読み込みエラー");
        }
    }
}