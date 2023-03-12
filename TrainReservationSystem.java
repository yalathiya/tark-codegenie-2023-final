import java.util.*;
class Train{
    int trainNumber;
    String source;
    String destination;
    int distance;

    Train(int trainNumber, String source, String destination, int distance){
        trainNumber = this.trainNumber;
        source = this.source;
        destination = this.destination;
        distance = this.distance;
    }

    int coachArrays[] = {0,0,0,0};
    int coachCount[] = {0,0,0,0};
    int coachTickets[] = {1,1,1,1};
    // coachArrays[0] = 0;
    // coachArrays[1] = 0;
    // coachArrays[2] = 0;
    // coachArrays[3] = 0;
    // 0th index for sleeper class
    // 1st index for 3A class
    // 2nd index for 2A class
    // 3rd index for 1A class
    void addCoach(String CoachName, int CoachCapacity){
        switch(CoachName){
            case "S":  coachArrays[0] += CoachCapacity; 
                       coachCount[0]++;
                       break;
            case "B":  coachArrays[1] += CoachCapacity; 
                       coachCount[1]++;
                       break;
            case "A":  coachArrays[2] += CoachCapacity; 
                       coachCount[2]++;
                       break;
            case "H":  coachArrays[3] += CoachCapacity;
                       coachCount[3]++; 
                       break;
        }
    }

    void bookTicket(String CoachName){
        switch(CoachName){
            case "S":   
                       coachTickets[0]++;
                       break;
            case "B":  
                       coachTickets[1]++;
                       break;
            case "A":  
                       coachTickets[2]++;
                       break;
            case "H":  
                       coachTickets[3]++; 
                       break;
        }
    }

}

class TrainReservationSystem{
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();
        sc.nextLine();
        ArrayList<Train> train = new ArrayList<>();  
        Train t1;
        for(int in=0; in<input; in++){
            String trainDetails = sc.nextLine();
            String coachDetails = sc.nextLine();
            String arr[] = trainDetails.split(" ");
            // System.out.println(arr.length);

            // for(int i=0; i<arr.length; i++){
            //     System.out.println("****"+arr[i]);
            // }
            String arrr[] = coachDetails.split(" ");
            // for(int i=0; i<arrr.length; i++){
            //     System.out.println(arrr[i]);
            // }
            String src = "";
            for(int i=0; i<arr[1].length(); i++){
                if(arr[1].charAt(i) == '-'){
                    break;
                }
                src += arr[1].charAt(i);
            }

            String dest = "";
            int index = -1;
            for(int i=0; i<arr[2].length(); i++){
                if(arr[2].charAt(i) == '-'){
                    index = i;
                    break;
                }
                dest += arr[2].charAt(i);
            }

            String dist = arr[2].substring(index+1, arr[2].length());
            int dis = Integer.parseInt(dist);

            t1 = new Train(Integer.parseInt(arr[0]), src, dest, dis);
            train.add(t1);
            for(int i=1; i<arrr.length; i++){ 
                int indexTemp = -1;
                for(int j=0; j<arrr[i].length(); j++){
                    if(arrr[i].charAt(j) == '-'){
                        indexTemp = j;
                        break;
                    }
                }
                System.out.println(arrr[i]);
                t1.addCoach(arrr[i].substring(0, indexTemp), Integer.parseInt(arrr[i].substring(indexTemp+1, arrr[i].length())));
            }
        }

        String pnr = "100000001";
        int tkt = Integer.parseInt(pnr);
        while(true){
            String instruction = sc.nextLine();
            String instructionText[] = instruction.split(" ");
            //instructionText[0] = source
            //instructionText[1] = destination
            //instructionText[2] = date
            //instructionText[3] = class
            //instructionText[4] = number of passengers
            int size = train.size();
            // System.out.println(train.get(0).trainNumber);
            for(int i=0; i<size; i++){
                if(train.get(i).source == instructionText[0] && train.get(i).destination == instructionText[1]){
                    switch(instructionText[3]){
                        case "SL":  if(train.get(i).coachCount[0] >0 && train.get(i).coachArrays[0] > 0){
                                        int fair = Integer.parseInt(instructionText[4])*train.get(i).distance*1;
                                        System.out.println(tkt+" "+fair);
                                    }
                                    break;
                        case "3A":  if(train.get(i).coachCount[1] >0 && train.get(i).coachArrays[1] > 0){
                                        int fair = Integer.parseInt(instructionText[4])*train.get(i).distance*2;
                                        System.out.println(tkt+" "+fair);
                                    }
                                    break;
                        case "2A":  if(train.get(i).coachCount[2] >0 && train.get(i).coachArrays[2] > 0){
                                        int fair = Integer.parseInt(instructionText[4])*train.get(i).distance*3;
                                        System.out.println(tkt+" "+fair);
                                    }
                                    break;
                        case "1A":  if(train.get(i).coachCount[3] >0 && train.get(i).coachArrays[3] > 0){
                                        int fair = Integer.parseInt(instructionText[4])*train.get(i).distance*4;
                                        System.out.println(tkt+" "+fair);
                                    }
                                    break;
                    }
                    break;
                }
            }
            System.out.println("INVALID");
        }
    }
}
