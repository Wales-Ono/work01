import java.util.Map;
import java.util.HashMap;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Kadai4{
    public static  void main(String[] args){
        Map<String,String> map = new HashMap<>();
        try{
            FileReader fr = new FileReader(args[0]);
            BufferedReader br = new BufferedReader(fr);

            //読み込み + セパレート + mapに格納
            while(true){
                String line = br.readLine();
                if(line == null){
                    break;
                }
                String[] str = line.split(" "); //読み込んだものを　" " でセパレート
                map.put(str[0],str[1]); //str[0]をkey,str[1]をvalueとしてmapに格納
            }
            
            //-------すべてのkeyとvalueを出力----------
            for(Map.Entry<String,String> m:map.entrySet()){ 
                System.out.println(m.getKey() + " : " + m.getValue());
            }
                // mapに格納された情報を1つずつ取り出して出力も可(keyの一覧を取得してから、各キーに対応するvalueを1つずつ取り出さなければならない)
                // for(String key:map.keySet()){   //keyの一覧を取得
                //     String value = map.get(key);   //keyを指定してvalueを取り出す
                //     System.out.println(key + " : " + value);
                // }    とも書ける


            //-------key「TOKYO」の値を出力する------------
            String value = map.get("TOKYO");
            System.out.println("TOKYO=> " + value);

        }catch(FileNotFoundException e){
            System.out.println("ファイルがありません");
        }catch(IOException e){
            System.out.println("読み込みエラー");
        }
    }
}