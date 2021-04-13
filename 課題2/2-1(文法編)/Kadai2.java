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
            Object obj = new String[]{"Hello","World"};
            Integer intObj = (Integer) obj;
        }catch(ClassCastException e){
            e.printStackTrace();
       }
    }
}