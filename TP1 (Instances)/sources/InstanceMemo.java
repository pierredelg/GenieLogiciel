import java.util.*;
public class InstanceMemo{

        public int n;
        public final static ArrayList<InstanceMemo> liste = new ArrayList<>();

    public InstanceMemo(){
        liste.add(this);
        n = liste.size();
    }
    public static int nombreInstances(){
        return liste.size();
    
    }
    public static void afficherInstances(){

          if(liste.size() <= 10){ 
			for(int i = 0;i <= liste.size()-1;i++){
                       System.out.println("Je suis l'instance numéro " + 					liste.get(i).n);
			}
          }
          else{
				for(int i = liste.size()-10;i <= liste.size()-1;i++){
                       System.out.println("Je suis l'instance numéro " + 					liste.get(i).n);
			}
          }
    }


}
