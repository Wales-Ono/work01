import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


public class Kadai2{
    public static void main(String[] args){
        try{
            BufferedImage img = ImageIO.read(new File(args[0]));
            ImageIO.write(img,"jpg",new File(args[1]));
            System.out.println("ファイルを書き出しました");
        }catch(FileNotFoundException e){
            System.out.println("ファイルが開けません");
        }catch(IOException e){
            System.out.println("読み込みエラー");
        }catch(Exception e){
            System.out.println("エラー");
        }
    }
}