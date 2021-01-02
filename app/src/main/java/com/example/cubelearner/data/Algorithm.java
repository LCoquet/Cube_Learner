package com.example.cubelearner.data;

/*
 * Data class containing an algorithm and its ID, to be shown on the PLL or OLL wiki ie AlgorithmActivity.
 */

public class Algorithm {

    private String id;
    private String algorithm;

    public Algorithm(String id, String algorithm){
        this.id = id;
        this.algorithm = algorithm;
    }

    public String getId(){
        return id;
    }

    public String getAlgorithm(){
        return algorithm;
    }

}
