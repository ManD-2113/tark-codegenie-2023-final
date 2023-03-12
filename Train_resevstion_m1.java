import java.util.*;


class Train_Data{
    int train_No;
    String sourceCity;
    String destinationCity;
    int distance;
    
    Train_Data(int trainNo, String startingCity, String  endingCity){
        String[] sourceCityarray = startingCity.split("-");
        String[] destinationCityarray = startingCity.split("-");
        this.train_No = trainNo;
        this.sourceCity=sourceCityarray[0];
        this.destinationCity = destinationCityarray[0];
        distance = (Integer.parseInt(destinationCityarray[1])) - (Integer.parseInt(sourceCityarray[1]));

    }


}

class Coches {
    int train_No;
    List<String> sliperCoches_no = new ArrayList<String>();
    List<String> t3acCoches_no = new ArrayList<String>();
    List<String> t2acCoches_no = new ArrayList<String>();
    List<String> t1acCoches_no = new ArrayList<String>();
    List<Integer> seats_in_sliperCoches = new ArrayList<Integer>();
    List<Integer> seats_in_t3acCoches = new ArrayList<Integer>();
    List<Integer> seats_in_t2acCoches = new ArrayList<Integer>();
    List<Integer> seats_in_t1acCoches = new ArrayList<Integer>();
    Coches(){}

    void definingCoches(String s){
        String[] coches_and_seats = s.split("-");
        if(s.charAt(0) == 'S' ){
            this.sliperCoches_no.add(coches_and_seats[0]);
            this.seats_in_sliperCoches.add(Integer.parseInt(coches_and_seats[1]));
        }
        else if(s.charAt(0) == 'B'){
            this.t3acCoches_no.add(coches_and_seats[0]);
            this.seats_in_t3acCoches.add(Integer.parseInt(coches_and_seats[1]));
        }
        else if(s.charAt(0) == 'A'){
            this.t2acCoches_no.add(coches_and_seats[0]);
            this.seats_in_t2acCoches.add(Integer.parseInt(coches_and_seats[1]));
        }
        else if(s.charAt(0) == 'H'){
            this.t1acCoches_no.add(coches_and_seats[0]);
            this.seats_in_t1acCoches.add(Integer.parseInt(coches_and_seats[1]));
        }
        else{
            this.train_No = Integer.parseInt(s);
        }
    }
    
}

public class Train_resevstion_m1 {
    static List<Train_Data> cityData = new ArrayList<>();
    static List<Coches> cochesData= new ArrayList<>();

    public static void main(String[] args) {
        Scanner inp = new Scanner(System.in);
        // System.out.println("Enter total no of trains:");
        int n =inp.nextInt();
        inp.nextLine();

        String[] inpString = new String[n*2];

        for (int i = 0; i < inpString.length; i++) {
            inpString[i] = inp.nextLine();
        }
        // for (String string : inpString) {
        //     System.out.println(string);
        // }


        
        for (String string : inpString) {
            
            String[] train = string.split(" ");

            if (train.length == 3){
                
                int number = Integer.parseInt(train[0]);
                Train_Data t1 = new Train_Data(number, train[1], train[2]);
                cityData.add(t1);
            }

            else if(train.length >= 4){
                Coches c1 = new Coches();
                for (String string2 : train) {
                    c1.definingCoches(string2);
                    }
                cochesData.add(c1);
                    
                }

            }
            System.out.println("----------------------------");
            for (Train_Data t1 : cityData) {
                System.out.println(t1.train_No+" "+t1.sourceCity+" "+t1.destinationCity+" "+t1.distance);
            }
            System.out.println("----------------------");
            for (Coches c : cochesData) {
                System.out.println(c.train_No);
                printList(c.sliperCoches_no);
                // printList(c.t1acCoches);
                // printList(c.t2acCoches);
                // printList(c.t3acCoches);
            }
            // String userInput = inp.nextLine();
            // String [] inputarr = userInput.split(" ");
        }
        // inp.close();
        static void printList(List<String> c){
            for (String string : c) {
                System.out.println(string);
            }
        }
    }

