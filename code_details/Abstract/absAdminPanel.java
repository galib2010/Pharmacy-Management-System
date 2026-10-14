package code_details.Abstract;

public abstract class absAdminPanel{
   public abstract void medicineDetails();
   public abstract void userView();
   protected abstract void salesInfo();

   public void addUpdate(){
      System.out.println("update medicine,catagory,stock,price");
   }

   protected abstract void remove();

   public void saveMedDetails(String medName,String imgPath,int s,double p,String type){
       System.out.println("medicine update");
   }
   protected abstract void updateStock(String Name, String stock);
   protected abstract void updatePrice(String Name, String price);

}