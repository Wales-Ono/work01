public class Kadai2{
    public static void main(String[] args){
       try{
            args = null;
            System.out.println(args[0]);
        }catch(NullPointerException e ){
            e.printStackTrace();
        }
        try{
            args = new String[]{"Hello","World"};
            System.out.println(args[10]);
        }catch(ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
        }
        try{
            Object obj = "Hello World"; //Object型の変数の値は代入できる
            Integer intObj = (Integer) obj; //String型からInteger型はキャストできない
        }catch(ClassCastException e){
            e.printStackTrace();
       }
    }
}