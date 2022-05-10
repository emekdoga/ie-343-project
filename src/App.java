import java.lang.Math;
import java.util.ArrayList;

import medianProblem.*;
public class App {
    public static void main(String[] args){
        Operators operator = new Operators();
        double[][] facs_coordinates = operator.createDistanceMatrix(500,2);
        double[][] points_coordinates = operator.createDistanceMatrix(500,2);
        Facility[] facs = new Facility[500];
        Point[] points = new Point [500];
        for (int i=0; i<facs.length;i++){
            Facility f1 = new Facility(i, facs_coordinates[i][0], facs_coordinates[i][1], 20*Math.random()+50);
            facs[i] = f1;
        }
        for (int i=0; i<points.length;i++){
            Point p1 = new Point (i, points_coordinates[i][0], points_coordinates[i][1], 2*Math.random()+1);
            points[i] = p1;
        }
        double[][] distanceMatrix = operator.distanceMatrix(facs, points);
        ArrayList<Facility> openedFacilities = new ArrayList<>();
        int pValue = 5;
        double currentTime = System.currentTimeMillis();
        for(int i = 0 ; i < pValue ; i++){
            Facility[] candidates = FindCandidateFacilities(openedFacilities,facs);
            Facility bestCandidate = FindBestCandidate(openedFacilities,candidates,distanceMatrix);
            openedFacilities.add(bestCandidate);
        }
        System.out.println("Cpu Time:" + (System.currentTimeMillis() - currentTime));
    }
    public static Facility[] FindCandidateFacilities(ArrayList<Facility> opened,Facility[] total){
        Facility[] result = new Facility[total.length - opened.size()];
        int index = 0;
        for(int i = 0 ; i < total.length ;i++){
            Facility candidateFacility = total[i];
            if(!opened.contains(candidateFacility)){
                result[index] = candidateFacility;
                index = index + 1;
            }
        }
        return result;
    }
    public static Facility FindBestCandidate(ArrayList<Facility> opened,Facility[] candidates,double[][] distMatrix){
        Facility result = candidates[0];
        double cost = -77;
        for(int i = 0 ; i < candidates.length ; i++){
            ArrayList<Facility> cloneFacilities = new ArrayList<Facility>(opened);
            cloneFacilities.add(candidates[i]);
            double tempCost = CalculateGreedyCost(cloneFacilities,distMatrix);
            if(cost == -77) {
                cost = tempCost;
            }
            else if(tempCost < cost){
                result = candidates[i];
                cost = tempCost;
            }
        }
        return result;
    }
    public static double CalculateGreedyCost(ArrayList<Facility> target,double[][] distMatrix){
        double result =  0;
        int[] indexes = new int[target.size()];

        for(int i = 0 ; i < indexes.length ; i++){
            indexes[i] = target.get(i).getId();
        }

        for(int j = 0 ; j < distMatrix[1].length ; j++){
            double min = distMatrix[0][j];
            for(int i = 0 ; i < indexes.length ; i++){
                double tempMin = distMatrix[i][j];
                if(tempMin < min){
                    min = tempMin;
                }
            }
            result = result + min;
        }
        return result;
    }
    public static ArrayList<ArrayList<Point>> GetAssignedPoints(ArrayList<Facility> openedFacilities,double[][] distMatrix,Point[] points){
        ArrayList<ArrayList<Point>> result = new ArrayList<>();
        for(int i =0  ; i < openedFacilities.size() ; i++){
            result.add(new ArrayList<Point>());
        }
        for(int i = 0 ; i < distMatrix[1].length ; i++){
            double minFacilityDistance = distMatrix[openedFacilities.get(0).getId()][i];
            int index = 0;
            for(int j = 0 ; j < openedFacilities.size() ; j++ ){
                double tempDistance = distMatrix[openedFacilities.get(j).getId()][i];
                if(tempDistance < minFacilityDistance){
                    minFacilityDistance = tempDistance;
                    index = openedFacilities.get(j).getId();
                }
            }
            result.get(index).add(points[i]);
        }
        return result;
    }
}



