package code_details.Abstract;

public abstract class absLoginPage{
   public abstract void check(String username,String password);
   protected void forgetPassDetails(String email){
      System.out.println("go to file and retrive");
   }
}