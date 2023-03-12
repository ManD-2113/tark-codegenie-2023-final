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

    int totalseats_in_sliperCoch = 0;
    int totalseats_in_t3acCoches = 0;
    int totalseats_in_t2acCoches = 0;
    int totalseats_in_t1acCoches = 0;
    Coches(){}

    void definingCoches(String s){
        String[] coches_and_seats = s.split("-");
        if(s.charAt(0) == 'S' ){
            this.sliperCoches_no.add(coches_and_seats[0]);
            this.seats_in_sliperCoches.add(Integer.parseInt(coches_and_seats[1]));
            totalseats_in_sliperCoch = totalseats(totalseats_in_sliperCoch, Integer.parseInt(coches_and_seats[1]));
        }
        else if(s.charAt(0) == 'B'){
            this.t3acCoches_no.add(coches_and_seats[0]);
            this.seats_in_t3acCoches.add(Integer.parseInt(coches_and_seats[1]));
            totalseats_in_t3acCoches = totalseats(totalseats_in_t3acCoches, Integer.parseInt(coches_and_seats[1]));
        }
        else if(s.charAt(0) == 'A'){
            this.t2acCoches_no.add(coches_and_seats[0]);
            this.seats_in_t2acCoches.add(Integer.parseInt(coches_and_seats[1]));
            totalseats_in_t2acCoches = totalseats(totalseats_in_t2acCoches, Integer.parseInt(coches_and_seats[1]));
        }
        else if(s.charAt(0) == 'H'){
            this.t1acCoches_no.add(coches_and_seats[0]);
            this.seats_in_t1acCoches.add(Integer.parseInt(coches_and_seats[1]));
            totalseats_in_t1acCoches = totalseats(totalseats_in_t1acCoches, Integer.parseInt(coches_and_seats[1]));
        }
        else{
            this.train_No = Integer.parseInt(s);
        }
    }
    int totalseats (int seats, int n){
        seats = seats+n;
        return seats;
    }
    
}

class TicketBook {

    String Date;
    String type_of_couch;
    int pnr;
    int fair;
}

public class Train_resevstion_m1 {
    static List<Train_Data> cityData = new ArrayList<>();
    static List<Coches> cochesData= new ArrayList<>();
    static List<TicketBook> ticketBookData = new ArrayList<>(); 
    static int pnr = 100000001;
    static int charge;

    public static void main(String[] args) {
        Scanner inp = new Scanner(System.in);
        // System.out.println("Enter total no of trains:");
        int n =inp.nextInt();
        inp.nextLine();

        String[] inpString = new String[n*2];

        for (int i = 0; i < inpString.length; i++) {
            inpString[i] = inp.nextLine();
        }


        
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
            while (true){
                String userInput = inp.nextLine();
                String [] inputarr = userInput.split(" ");
                TicketBook t1 = new TicketBook();
                Boolean route = isRoute(inputarr[0], inputarr[1]);

                if(route){
                    int distance =  findDistance(inputarr[0],inputarr[1]);

                    if(inputarr[3].matches("SL")){
                        charge = Integer.parseInt(inputarr[4])*distance*1;
                        System.out.println(pnr+" "+ charge);
                        pnr+=1;
                    }
                    else if (inputarr[3].matches("1A")){
                        charge = Integer.parseInt(inputarr[4])*distance*2;
                        System.out.println(pnr+" "+ charge);
                        pnr+=1;
                    }
                    else if (inputarr[3].matches("2A")){
                        charge = Integer.parseInt(inputarr[4])*distance*3;
                        System.out.println(pnr+" "+ charge);
                        pnr+=1;
                    }
                    else if (inputarr[3].matches("3A")){
                        charge = Integer.parseInt(inputarr[4])*distance*4;
                        System.out.println(pnr+" "+ charge);
                        pnr+=1;
                    }

                }
                else{
                    System.out.println("No Trains Available");
                }

                }

                
                        
        }
            
        static void booktickets(int trainno, int pnrno, int fair, String date){
            for (Coches c : cochesData) {
                if(c.train_No == trainno){
                    TicketBook t = new TicketBook();
                    t.Date = date;
                    t.pnr  = pnrno;
                    t.fair = fair;

                }
            }
        }

        static int findDistance(String s, String d){
            for (Train_Data train_Data : cityData) {
                if(train_Data.sourceCity.matches(s)&&train_Data.destinationCity.matches(d)){
                    return train_Data.distance;
                }   
            }
            return 0;
        }


            static int avialableSeat (int train, String couch, int passenger){
                    for (Coches c : cochesData) {
                        if(c.train_No == train){
                            if (couch.matches("SL")){
                                int availableSeats = c.totalseats_in_sliperCoch;
                                c.totalseats_in_sliperCoch = c.totalseats_in_sliperCoch - passenger;
                                return availableSeats;
                            }
                            else if (couch.matches("1A")) {
                                int availableSeats = c.totalseats_in_t1acCoches;
                                c.totalseats_in_t1acCoches = c.totalseats_in_t1acCoches - passenger;
                                return availableSeats; 
                            }
                            else if (couch.matches("2A")) {
                                int availableSeats = c.totalseats_in_t2acCoches;
                                c.totalseats_in_t2acCoches = c.totalseats_in_t2acCoches - passenger;
                                return availableSeats;
                            }
                            else {
                                int availableSeats = c.totalseats_in_t3acCoches;
                                c.totalseats_in_t3acCoches = c.totalseats_in_t3acCoches - passenger;
                                return availableSeats;
                            }
                        }
                    }

                    return 0;
            }

            static boolean isRoute(String scity, String Dcity){
                for (Train_Data train_Data : cityData) {
                    if(train_Data.sourceCity.matches(scity)&&train_Data.destinationCity.matches(Dcity)){
                        return true;
                    }    
                }
                return false;
            }
    }
