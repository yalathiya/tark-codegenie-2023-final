import java.util.*;
class Train{
    int trainNumber;
    String source;
    String destination;
    int distance;

    Train(int trainNumber, String source, String destination, int distance){
        this.trainNumber = trainNumber;
        this.source = source;
        this.destination = destination;
        this.distance = distance;
    }

    int coachArrays[] = new int[4];
    int coachCount[] = new int[4];
    int coachTickets[] = new int[4];

    
    // coachArrays[0] = 0;
    // coachArrays[1] = 0;
    // coachArrays[2] = 0;
    // coachArrays[3] = 0;
    // 0th index for sleeper class
    // 1st index for 3A class
    // 2nd index for 2A class
    // 3rd index for 1A class
    void addCoach(String CoachName, int CoachCapacity){
        // System.out.println("**ADDCOACH**");
        // System.out.println("CoachName"+CoachName);
        switch(CoachName){
            case "S":  this.coachArrays[0] += CoachCapacity; 
                       this.coachCount[0]++;
                       break;
            case "B":  this.coachArrays[1] += CoachCapacity; 
                       this.coachCount[1]++;
                       break;
            case "A":  this.coachArrays[2] += CoachCapacity; 
                       this.coachCount[2]++;
                       break;
            case "H":  this.coachArrays[3] += CoachCapacity;
                       this.coachCount[3]++; 
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

    int[] fillsZero(int []arr){
        for(int i=0; i<arr.length; i++){
            arr[i] = 0;
        }
        return arr;
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
            // System.out.println(t1.source);
            train.add(t1);
            // System.out.println("t1.source"+t1.source);
            for(int i=1; i<arrr.length; i++){ 
                int indexTemp = -1;
                for(int j=0; j<arrr[i].length(); j++){
                    if(arrr[i].charAt(j) == '-'){
                        indexTemp = j;
                        break;
                    }
                }
                // System.out.println(arrr[i]);
                t1.addCoach(arrr[i].substring(0, indexTemp-1), Integer.parseInt(arrr[i].substring(indexTemp+1, arrr[i].length())));
                // System.out.println("**coachArray**"+t1.coachArrays[0]);
                // System.out.println("**coachCount**"+t1.coachCount[0]);
            }
        }

        String pnr = "100000001";
        int tkt = Integer.parseInt(pnr);
        while(true){
            String instruction = sc.nextLine();
            String instructionText[] = instruction.split(" ");
            boolean flag = true;
            //instructionText[0] = source
            //instructionText[1] = destination
            //instructionText[2] = date
            //instructionText[3] = class
            //instructionText[4] = number of passengers
            int size = train.size();
            // System.out.println(size);
            // for(int i=0; i<size; i++){
            //     System.out.println(instructionText[i]);
            // }
            // System.out.println(train.get(0).source);
            // System.out.println(train.get(0).destination);
            // System.out.println(train.get(0).trainNumber);
            for(int i=0; i<size; i++){
                // System.out.println("**");
                // System.out.println(instructionText[0]);
                // System.out.println(train.get(0).source);
                // System.out.println(train.get(0).destination);
                // System.out.println(instructionText[1]);
                if((train.get(i).source.equals(instructionText[0])) && (train.get(i).destination.equals(instructionText[1]))){
                    
                    // System.out.println("****");
                    // System.out.println("in3 "+instructionText[3]);
                    switch(instructionText[3]){
                        case "SL":  //System.out.println("coachCount "+train.get(i).coachCount);
                        train.get(i).coachArrays[0] -= Integer.parseInt(instructionText[4]);
                                    if(train.get(i).coachCount[0] >0 && train.get(i).coachArrays[0] > 0){
                                        int fairSL = Integer.parseInt(instructionText[4])*train.get(i).distance*1;
                                        
                                        System.out.println(tkt+" "+fairSL);
                                        System.out.println("Remaining Sits "+train.get(i).coachArrays[0]);
                                        flag = false;
                                        tkt++;
                                    }
                                    else if(train.get(i).coachCount[0] <= 0 || train.get(i).coachArrays[0] <= 0){
                                        System.out.println("No Seats Available");
                                        flag = false;
                                    }
                                    break;
                        case "3A":  train.get(i).coachArrays[1] -= Integer.parseInt(instructionText[4]);
                                    if(train.get(i).coachCount[1] >0 && train.get(i).coachArrays[1] > 0){
                                        int fair3A = Integer.parseInt(instructionText[4])*train.get(i).distance*2;
                                        
                                        System.out.println("Remaining Sits "+train.get(i).coachArrays[1]);
                                        System.out.println(tkt+" "+fair3A);
                                        flag = false;
                                        tkt++;
                                    }
                                    else if(train.get(i).coachCount[1] <= 0 || train.get(i).coachArrays[1] <= 0){
                                        System.out.println("No Seats Available");
                                        flag = false;
                                    }
                                    break;
                        case "2A":  train.get(i).coachArrays[2] -= Integer.parseInt(instructionText[4]);
                                    if(train.get(i).coachCount[2] >0 && train.get(i).coachArrays[2] > 0){
                                        int fair2A = Integer.parseInt(instructionText[4])*train.get(i).distance*3;
                                        
                                        System.out.println("Remaining Sits "+train.get(i).coachArrays[2]);
                                        System.out.println(tkt+" "+fair2A);
                                        flag = false;
                                        tkt++;
                                    }
                                    else if(train.get(i).coachCount[2] <= 0 || train.get(i).coachArrays[2] <= 0){
                                        System.out.println("No Seats Available");
                                        flag = false;
                                    }
                                    break;

                        case "1A":  train.get(i).coachArrays[3] -= Integer.parseInt(instructionText[4]);
                                    if(train.get(i).coachCount[3] >0 && train.get(i).coachArrays[3] > 0){
                                        int fair1A = Integer.parseInt(instructionText[4])*train.get(i).distance*4;
                                        
                                        System.out.println("Remaining Sits "+train.get(i).coachArrays[3]);
                                        System.out.println(tkt+" "+fair1A);
                                        flag = false;
                                        tkt++;
                                    }
                                    else if(train.get(i).coachArrays[3] <= 0){
                                        System.out.println("No Seats Available");
                                        flag = false;
                                    }
                                    break;
                    }
                    break;
                }
            }
            if(flag){
                System.out.println("No Trains Available");
            } 
            // flag = true
        }
    }
}
