package org.xmath.stats;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Data {

    private double[][] content;

    private String[] titles;
    public String[] getTitles() { return titles; }
    public void setTitles(String[] titles) { this.titles = titles; }

    public Data(double[][] content) {
        this.content = content;
    }


    public static Data fromCSV(File path){
        // todo
        return null;
    }

    public static Data fromCSV(File path, Config config){
        // todo
        return null;
    }

    public void addContent(Sample sample){
        double[][] arr = new double[content.length + 1][content[0].length];
        for (int i = 0; i < content.length; i++) {
            arr[i] = content[i];
        }
        arr[content.length + 1] = sample.toDoubleArray();
        content = arr;
    }

    public void toCSV(File path){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < content[0].length; i++) {
            for (int j = 0; j < content.length; j++) {
                sb.append(content[j][i]);
                if (!(j == content.length - 1)){
                    sb.append(",");
                }
            }
            sb.append("\n");
        }

        try {
            FileWriter fw = new FileWriter(path);
            fw.write(sb.toString());
            fw.close();
        } catch (IOException iow){
            System.out.println("Could not save file "+path);

        }

    }

    public List<Sample> samples(){
        List<Sample> samples = new ArrayList<>();
        for (double[] obs : content){
            samples.add(new Sample(obs));
        }
        return samples;
    }


    public static class Config {

        public boolean first_line_title = false;
        public boolean column_orientation = true;
        public int start_index = 0;
        public int[] skip_index = new int[0];

    }

    public static Sample c(double[] data){
        return new Sample(data);
    }

}
