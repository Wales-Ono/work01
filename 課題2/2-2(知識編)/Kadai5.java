import java.util.Map;
import java.util.TreeMap;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;

public class Kadai5{
    public static void main(String[] args){        
        Map<String,String> map = new TreeMap<>(); //TreeMapで自然順序付け

        Map<String,String> mapComp = new TreeMap<>(new Comparator<String>() {
            @Override   //昇順にする
            public int compare(String s1, String s2) {
                if(s1.length() > s2.length()){    //後のほうが短かったら1を返す
                    return 1;
                }else if(s1.length() < s2.length()){  //後のほうが長かったら-1を返す
                    return -1;
                }
                return s1.compareTo(s2);  //アルファベットの順番を比較する
            }
        });
                
        try{
            //ファイルの読み込み
            FileReader fr = new FileReader(args[0]);
            BufferedReader br = new BufferedReader(fr);
            while(true){
                String line = br.readLine();
                if(line == null){
                    break;
                }
                String[] str = line.split(" ");
                map.put(str[0],str[1]);
                mapComp.put(str[0],str[1]);
            }
            
            //-----------キーの文字列順序（アルファベット辞書順）で出力------------
            for(Map.Entry<String,String> m1:map.entrySet()){ 
                System.out.println(m1.getKey() + " : " + m1.getValue());
            }
                System.out.println();
            
            //----------キー名の短い順、同じ長さの場合はアルファベット辞書順---------
            for(Map.Entry<String,String> m2:mapComp.entrySet()){ 
                System.out.println(m2.getKey() + " : " + m2.getValue());
            }
        }catch(FileNotFoundException e){
            System.out.println("ファイルがありません");
        }catch(IOException e){
            System.out.println("読み込みエラー");
        }
    }
}