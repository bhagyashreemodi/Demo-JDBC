public class Test extends Thread{
      public static void main(String argv[]){
            Test t = new Test();
            t.start();
      }

      public void start(){
            for(int i = 0; i < 10; i++){
                  System.out.println("Value of i = " + i);
            }
      }
}