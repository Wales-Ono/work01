public class Kadai1{
    public static void main(String[] args){
        // 現在の開発環境のエンコーディングを調べる
        // System.out.println(System.getProperty("file.encoding"));

        System.out.println("引数は" + args.length + "個指定されています。");
        for(int i=0;i < args.length;i++){
        System.out.println(i+1 + "番目の引数は「" + args[i] +"」です。");
        }
    }
}