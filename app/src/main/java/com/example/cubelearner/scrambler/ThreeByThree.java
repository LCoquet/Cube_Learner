package com.example.cubelearner.scrambler;

public class ThreeByThree {

    public static String scrambler() {

        String result= "";
        int val;
        int last = 6;
        for(int i = 0; i < 25; i ++) {
            while((val = (int) (Math.random()*6)) == last) {}
            last = val;
            switch(val) {
                case 0 : result += "F"; break;
                case 1 : result += "R"; break;
                case 2 : result += "L"; break;
                case 3 : result += "U"; break;
                case 4 : result += "D"; break;
                case 5 : result += "B"; break;
            }
            val = (int) (Math.random()*5);
            switch(val) {
                case 2 :
                case 3 : result += "'"; break;
                case 4 : result += "2"; break;
            }
            result += " ";
        }
        return result;

    }

}