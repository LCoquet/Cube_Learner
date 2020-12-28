package com.example.cubelearner.data;

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
