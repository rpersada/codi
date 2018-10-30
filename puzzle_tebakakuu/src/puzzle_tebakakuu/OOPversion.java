/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzle_tebakakuu;
import java.util.Timer;
import java.util.TimerTask;
/**
 *
 * @author persada
 */
public class OOPversion {    
    /*
//        1     2     3
//      
//        8     0     4
//    
//        7     6     5
//    */
//    private byte GOAL_STATE[] = {1, 2, 3, 8, 0, 4, 7, 6, 5};
//    private byte AVAILABLE_INITIAL_STATE[][] = {
//        /*
//            1     2     3
//        
//            7     8     4
//        
//            6     0     5
//        */
//        { 1,2,3,7,8,4,6,0,5 },
//        /*
//            1     2     3
//
//            7     8     4
//
//            6     5     0
//        */
//        { 1,2,3,7,8,4,6,5,0 },
//        
//        /*
//        LOKAL OPTIMUM
//            1     2     3
//        
//            7     0     8
//        
//            6     5     4
//        */
//        { 1,2,3,7,0,8,6,5,4 },
//        /*
//        LOKAL OPTIMUM
//            1     0     3
//        
//            7     2     8
//        
//            6     5     4
//        */
//        { 1,0,3,7,2,8,6,5,4 },
//        //no solve state
//        /*
//            7     1     3
//        
//            0     2     8
//        
//            6     5     4
//        */
//        { 7,1,3,0,2,8,6,5,4 },
//    };
//    private byte INITIAL_STATE[], SUCC = 0;
//    //constructor
//    public OOPversion(int shuffle){
//        if(shuffle>AVAILABLE_INITIAL_STATE.length+1 || shuffle<1)
//            throw new ArithmeticException();
//        else
//            INITIAL_STATE = AVAILABLE_INITIAL_STATE[shuffle-1];
//    }
//    public byte getSUCC(){
//        return SUCC;
//    }
//     public void setSUCC(byte s){
//        SUCC = s;
//    }
//      public byte[] getInitialState(){
//        return INITIAL_STATE;
//    }
//      public byte[] getGoalState(){
//        return GOAL_STATE;
//    }
//    
//      public byte getPosMax(byte data[]){
//        byte posMax = 0;
//        for (byte i = 0; i < data.length; i++) {
//            if(data[i] > data[posMax])
//                posMax = i;
//        }
//        return posMax;
//    }
//      public String getBestMove(byte puz[]){
//        byte pn = getZeroPosition(puz);
//        String possibleMove[] = getPossibleMove(pn).split(",");
//        byte posBenar[] = new byte[possibleMove.length];
//        String pb[] = new String[possibleMove.length];
//        byte i = 0;
//        for(String p : possibleMove){
//            byte a[];
//            byte pz[] = puz.clone();
//            if("GESERKIRI".equals(p)){
//                a = moveLeft(pz).clone();
//            }else if("GESERBAWAH".equals(p)){
//                a = moveDown(pz).clone();
//            }else if("GESERATAS".equals(p)){
//                a = moveUp(pz).clone();
//            }else{
//                a = moveRight(pz).clone();
//            }
//            posBenar[i] = getHamming(a);
//            pb[i] = p;
//            i++;
//        }
//        return pb[getPosMax(posBenar)];
//    }
//    
//      public void printPuzzle(){
//        System.out.println("----------------------------");
//        for (int i = 1; i <= INITIAL_STATE.length; i++) {
//            System.out.print(INITIAL_STATE[i-1]+"\t");
//            if(i%3==0)
//                System.out.println("");
//        }
//        System.out.println("----------------------------");
//    }
//      public void printPuzzle(byte p[]){
//        System.out.println("----------------------------");
//        for (int i = 1; i <= p.length; i++) {
//            System.out.print(p[i-1]+"\t");
//            if(i%3==0)
//                System.out.println("");
//        }
//        System.out.println("----------------------------");
//    }
//      public byte getZeroPosition(byte[] INITIAL_STATE1){
//        for (byte i = 0; i < INITIAL_STATE1.length; i++) {
//            if(INITIAL_STATE1[i] == 0)
//                return i;
//        }
//        return 0;
//    }
//      public byte getZeroPosition(){
//        for (byte i = 0; i < INITIAL_STATE.length; i++) {
//            if(INITIAL_STATE[i] == 0)
//                return i;
//        }
//        return 0;
//    }
//      public String getPossibleMove(int pn){
//        String pm = "";
//        if(pn == 0){
//            pm = "GESERKIRI,GESERATAS";
//        }else if(pn == 1){
//            pm = "GESERKIRI,GESERATAS,GESERKANAN";
//        }else if(pn == 2){
//            pm = "GESERATAS,GESERKANAN";
//        }else if(pn == 3){
//            pm = "GESERKIRI,GESERATAS,GESERBAWAH";
//        }else if(pn == 4){
//            pm = "GESERKIRI,GESERATAS,GESERBAWAH,GESERKANAN";
//        }else if(pn == 5){
//            pm = "GESERATAS,GESERBAWAH,GESERKANAN";
//        }else if(pn == 6){
//            pm = "GESERBAWAH,GESERKIRI";
//        }else if(pn == 7){
//            pm = "GESERBAWAH,GESERKANAN,GESERKIRI";
//        }else if(pn == 8){
//            pm = "GESERBAWAH,GESERKANAN";
//        }
//        return pm;
//    }
//      public byte getHamming(){
//        byte benar = 0;
//        for (int i = 1; i <= GOAL_STATE.length; i++) {
//            if(INITIAL_STATE[i-1] == 0)
//                continue;
//            else if(INITIAL_STATE[i-1] == GOAL_STATE[i-1])
//                benar++;
//        }
//        return benar;
//    }
//       public byte getHamming(byte puz[]){
//        byte benar = 0;
//        for (int i = 1; i <= GOAL_STATE.length; i++) {
//            if(puz[i-1] == 0)
//                continue;
//            else if(puz[i-1] == GOAL_STATE[i-1])
//                benar++;
//        }
//        return benar;
//    }
//       public void move(String DIRECTION){
//        String possibleMove[] = DIRECTION.split(",");
//        if("GESERKIRI".equals(DIRECTION)){
//            INITIAL_STATE = moveLeft(INITIAL_STATE).clone();
//        }else if("GESERBAWAH".equals(DIRECTION)){
//            INITIAL_STATE = moveDown(INITIAL_STATE).clone();
//        }else if("GESERATAS".equals(DIRECTION)){
//            INITIAL_STATE = moveUp(INITIAL_STATE).clone();
//        }else{
//            INITIAL_STATE = moveRight(INITIAL_STATE).clone();
//        }
//    }
//       public void printDirection(String DIRECTION){
//        String possibleMove[] = DIRECTION.split(",");
//        if("GESERKIRI".equals(DIRECTION)){
//            System.out.println("0 digeser ke kanan");
//        }else if("GESERBAWAH".equals(DIRECTION)){
//            System.out.println("0 digeser ke atas");
//        }else if("GESERATAS".equals(DIRECTION)){
//            System.out.println("0 digeser ke bawah");
//        }else{
//            System.out.println("0 digeser ke kiri");
//        }
//    }
////       public void execute(){
////        System.out.println("INITIAL STATE");
////        printPuzzle();
////        System.out.println("");
////        System.out.println("GOAL STATE");
////        printPuzzle(getGoalState());
////        System.out.println("");
////        System.out.println("nilai heuristik awal = "+getHamming());
////        System.out.println("nilai SUCC = "+getHamming());
////        SUCC = getHamming();
////        System.out.println("");
////        System.out.println("<------------ Pemecahan ------------>");
////        String DIRECTION;
////        int i =1;
////        while(SUCC<8){
////            System.out.println("Iterasi ke "+(++i));
////            DIRECTION = getBestMove(getInitialState());
////            move(DIRECTION);
////            if(SUCC == getHamming()){
////                System.out.println("Stopped");
////                break;
////            }
////            SUCC = getHamming();
////            printDirection(DIRECTION);
////            printPuzzle();
////            System.out.println("SUCC sekarang "+SUCC);
////            System.out.println("");
////        }
////        if(SUCC >= 8)
////            System.out.println("SELAMAT PUZZLE MAMPU KE GLOBAL OPTIMUM");
////        else
////            System.out.println("PUZZLE BERHENTI DI LOKAL OPTIMUM");
////    }
//        private byte[] moveUp(byte[] PA){
//        byte zeroPos = getZeroPosition(PA);
//        PA[zeroPos] = PA[zeroPos+3];
//        PA[zeroPos+3] = 0;
//        return PA;
//    }
//        private byte[] moveDown(byte[] PA){
//        byte zeroPos = getZeroPosition(PA);
//        PA[zeroPos] = PA[zeroPos-3];
//        PA[zeroPos-3] = 0;
//        return PA;
//    }
//        private byte[] moveLeft(byte[] PA){
//        byte zeroPos = getZeroPosition(PA);
//        PA[zeroPos] = PA[zeroPos+1];
//        PA[zeroPos+1] = 0;
//        return PA;
//    }
//         private byte[] moveRight(byte[] PA){
//        byte zeroPos = getZeroPosition(PA);
//        PA[zeroPos] = PA[zeroPos-1];
//        PA[zeroPos-1] = 0;
//        return PA;
//    }
//         public static void main(String[] args) {
//        OOPversion SE = new OOPversion(5);
////        SE.execute();
////        Timer t = new Timer();
////        t.schedule(new TimerTask() {
////            @Override
////            public void run() {
////                System.out.println("");
////            }
////        }, 1000, 1000);
//    }
}
