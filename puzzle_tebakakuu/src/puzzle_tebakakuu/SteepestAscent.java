///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package puzzle_tebakakuu;
//import java.util.ArrayList;
//import java.util.Iterator;
///**
// *
// * @author persada
// */
//public class SteepestAscent {
//    
//    private final ArrayList<node> NODE_ANAK;
//    private final ArrayList INDEX_NODE_ANAK = new ArrayList();
//    private final ArrayList ARAH_GESER_TERSEDIA = new ArrayList();
//    private int POSISI_NODE_ANAK_TERBAIK;
//    private boolean SUCC_BERUBAH = false;
//    private String ARAH_GESER, HASIL;
//    private byte LEVEL_NODE;
//    
//    /*
//        1     2     3
//      
//        8     0     4
//    
//        7     6     5
//    */
//    private final byte KEADAAN_TUJUAN[] = {1, 2, 3, 8, 0, 4, 7, 6, 5};
//    private byte KEADAAN_SEKARANG[];
//    private final byte AVAILABLE_KEADAAN_AWAL[][] = {
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
//        //no solve state
//        /*
//            1     2     3
//        
//            8     0     4
//        
//            7     6     5
//        */
//        { 1,2,3,8,0,4,7,6,5 },
//    };
//    
//    private byte KEADAAN_AWAL[], SUCC = 0;
//    
//    public SteepestAscent(int shuffle){
//        if(shuffle>AVAILABLE_KEADAAN_AWAL.length+1 || shuffle<1)
//            throw new ArithmeticException();
//        else
//            KEADAAN_AWAL = AVAILABLE_KEADAAN_AWAL[shuffle-1];
//        NODE_ANAK = new ArrayList();
//    }
//    public byte[] getKeadaanAwal(){
//        return KEADAAN_AWAL;
//    }
//    public boolean isSuccBerubah(){
//        return SUCC_BERUBAH;
//    }
//    public void setKeadaanSekarang(byte puz[]){
//        KEADAAN_SEKARANG = puz;
//    }
//    public void setSUCC(byte succ){
//        SUCC = succ;
//    }
//    public int getPosisiNodeAnakTerbaik(){
//        return POSISI_NODE_ANAK_TERBAIK;
//    }
//    public String getArahGeser(){
//        return ARAH_GESER;
//    }
//     public ArrayList<node> getNodeAnak(){
//        return NODE_ANAK;
//    }
//     public byte[] getKeadaanTujuan(){
//        return KEADAAN_TUJUAN;
//    }
//     byte getLevelTerakhirKali(){
//        byte lvl = 0;
//        if(NODE_ANAK.size()>0){
//            lvl = (byte) (((node) NODE_ANAK.get(NODE_ANAK.size()-1)).getLevel()+1);
//        } 
//        return lvl;
//    }
//      void expandNodeAnak(){
//        INDEX_NODE_ANAK.clear();
//        ARAH_GESER_TERSEDIA.clear();
//        String possibleMove[] = getKemungkinanGeser(getPosisiNol(KEADAAN_SEKARANG)).split(",");
//        LEVEL_NODE = (byte) (getLevelTerakhirKali()+1);
//        for(String p : possibleMove){
//            ARAH_GESER_TERSEDIA.add(p);
//            byte puzzleSementara[];
//            byte pz[] = KEADAAN_SEKARANG.clone();
//            if("GESERKIRI".equals(p)){
//                puzzleSementara = geserKeKiri(pz).clone();
//            }else if("GESERBAWAH".equals(p)){
//                puzzleSementara = geserKeBawah(pz).clone();
//            }else if("GESERATAS".equals(p)){
//                puzzleSementara = geserKeAtas(pz).clone();
//            }else{
//                puzzleSementara = geserKeKanan(pz).clone();
//            }
//            NODE_ANAK.add(new node(puzzleSementara, LEVEL_NODE));
//            INDEX_NODE_ANAK.add(NODE_ANAK.size()-1);
//        }
//    }
//      void filterNodeAnak(){
//        Iterator i = NODE_ANAK.iterator();
//        SUCC_BERUBAH = false;
//        while(i.hasNext()){
//            node node = ((node) i.next());
//            if(node.getLevel() == LEVEL_NODE){
//                if(SUCC < getHeuristik(node.getState())){
//                    SUCC = getHeuristik(node.getState());
//                    SUCC_BERUBAH = true;
//                    POSISI_NODE_ANAK_TERBAIK = NODE_ANAK.indexOf(node);
////                    System.out.println(ARAH_GESER_TERSEDIA);
////                    ARAH_GESER = ARAH_GESER_TERSEDIA.get(POSISI_NODE_ANAK_TERBAIK).toString();
//                }
//            }
//        }
//    }
//      public void cetakPuzzle(){
//        System.out.println("----------------------------");
//        for (int i = 1; i <= KEADAAN_AWAL.length; i++) {
//            System.out.print(KEADAAN_AWAL[i-1]+"\t");
//            if(i%3==0)
//                System.out.println("");
//        }
//        System.out.println("----------------------------");
//    }
//       public void cetakPuzzle(byte p[]){
//        System.out.println("----------------------------");
//        for (int i = 1; i <= p.length; i++) {
//            System.out.print(p[i-1]+"\t");
//            if(i%3==0)
//                System.out.println("");
//        }
//        System.out.println("----------------------------");
//    }
//         private byte getPosisiNol(byte[] PUZ){
//        for (byte i = 0; i < PUZ.length; i++) {
//            if(PUZ[i] == 0)
//                return i;
//        }
//        return 0;
//    }
//         private String getKemungkinanGeser(int pn){
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
//         public byte getHeuristik(){
//        byte benar = 0;
//        for (int i = 1; i <= KEADAAN_TUJUAN.length; i++) {
//            if(KEADAAN_AWAL[i-1] == 0)
//                continue;
//            else if(KEADAAN_AWAL[i-1] == KEADAAN_TUJUAN[i-1])
//                benar++;
//        }
//        return benar;
//    }
//           public byte getHeuristik(byte puz[]){
//        byte benar = 0;
//        for (int i = 1; i <= KEADAAN_TUJUAN.length; i++) {
//            if(puz[i-1] == 0)
//                continue;
//            else if(puz[i-1] == KEADAAN_TUJUAN[i-1])
//                benar++;
//        }
//        return benar;
//    }
//           public byte[] getKeadaanSekarang(){
//        return KEADAAN_SEKARANG;
//    }
//           public void printarahGeser(String arahGeser){
//        String possibleMove[] = arahGeser.split(",");
//        if("GESERKIRI".equals(arahGeser)){
//            System.out.println("0 digeser ke kanan");
//        }else if("GESERBAWAH".equals(arahGeser)){
//            System.out.println("0 digeser ke atas");
//        }else if("GESERATAS".equals(arahGeser)){
//            System.out.println("0 digeser ke bawah");
//        }else{
//            System.out.println("0 digeser ke kiri");
//        }
//    }
//           public boolean isTujuan(byte[] p){
//        return getHeuristik(p) == 8;
//    }
//           public byte getSUCC(){
//        return SUCC;
//    }
//           private void execute(){
//        System.out.println("KEADAAN AWAL");
//        cetakPuzzle(getKeadaanAwal());
//        System.out.println("");
//        System.out.println("KEADAAN TUJUAN");
//        cetakPuzzle(getKeadaanTujuan());
//        System.out.println("");
//        System.out.println("menggunakan heuristik puzzle menempati posisi yang benar");
//        if(isTujuan(KEADAAN_AWAL)){
//            System.out.println("KEADAAN AWAL == KEADAAN TUJUAN");
//            System.exit(0);
//        }
//        KEADAAN_SEKARANG = KEADAAN_AWAL;
//        System.out.println("nilai heuristik awal = "+getHeuristik(KEADAAN_SEKARANG));
//        System.out.println("nilai SUCC = "+getHeuristik());
//        SUCC = getHeuristik();
//        System.out.println("");
//        System.out.println("<------------ Pemecahan ------------>");
//        int i = 1;
//        while(SUCC<8){
//            expandNodeAnak();
//            filterNodeAnak();
//            if(SUCC_BERUBAH){
//                KEADAAN_SEKARANG = NODE_ANAK.get(POSISI_NODE_ANAK_TERBAIK).getState();
//                System.out.println("Iterasi ke "+(i++));
//                printarahGeser(ARAH_GESER);
//                cetakPuzzle(KEADAAN_SEKARANG);
//                System.out.println("SUCC sekarang "+SUCC);
//            }else{
//                break;
//            }            
//            System.out.println("");
//        }
//        if(SUCC >= 8)
//            System.out.println("SELAMAT PUZZLE MAMPU KE GLOBAL OPTIMUM");
//        else
//            System.out.println("PUZZLE BERHENTI DI LOKAL OPTIMUM");
//    }
//            private byte[] geserKeAtas(byte[] PA){
//        byte posisiNol = getPosisiNol(PA);
//        PA[posisiNol] = PA[posisiNol+3];
//        PA[posisiNol+3] = 0;
//        return PA;
//    }
//             private byte[] geserKeBawah(byte[] PA){
//        byte posisiNol = getPosisiNol(PA);
//        PA[posisiNol] = PA[posisiNol-3];
//        PA[posisiNol-3] = 0;
//        return PA;
//    }
//             private byte[] geserKeKiri(byte[] PA){
//        byte posisiNol = getPosisiNol(PA);
//        PA[posisiNol] = PA[posisiNol+1];
//        PA[posisiNol+1] = 0;
//        return PA;
//    }
//              private byte[] geserKeKanan(byte[] PA){
//        byte posisiNol = getPosisiNol(PA);
//        PA[posisiNol] = PA[posisiNol-1];
//        PA[posisiNol-1] = 0;
//        return PA;
//    }
//               public static void main(String[] args) {
//        SteepestAscent SE = new SteepestAscent(4);
//        SE.execute();
//        Iterator se = SE.NODE_ANAK.iterator();
//        while(se.hasNext()){
//            System.out.println(((node) se.next()).getLevel());
//        }
//    }
//}
//
//
//
