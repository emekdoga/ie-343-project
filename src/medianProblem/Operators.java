package medianProblem;

public class Operators {
    public double[][] createDistanceMatrix(int rowLength, int columnLength){
        double[][] result = new double[rowLength][columnLength];
        for (int i=0;i<rowLength;i++){
            for (int j=0;j<columnLength;j++){
                if (j == 0){
                    result[i][j] = (Math.random()/10)+41.02;
                }                
                else{
                    result[i][j] = Math.random()+28.2;
                }
            }
        }
        return result;
    }
    public void print2d (double[][] array){
        for(int i=0;i<array.length;i++){
            for (int j=0;j<array[0].length;j++){
                if (j != array[0].length-1){
                    System.out.print(array[i][j]+",");
                }
                else{
                    System.out.println(array[i][j]);
                }
            }
        }
    }
    public double distFrom(double lat1, double lng1, double lat2, double lng2) {
        double earthRadius = 3958.75; // miles (or 6371.0 kilometers)
        double dLat = Math.toRadians(lat2-lat1);
        double dLng = Math.toRadians(lng2-lng1);
        double sindLat = Math.sin(dLat / 2);
        double sindLng = Math.sin(dLng / 2);
        double a = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
                * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2));
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double dist = earthRadius * c;
        return dist;
    }
    public double[][] distanceMatrix (Facility[] facilities, Point[] points){
        double[][] distMat = new double[facilities.length][points.length];
        for (int i=0;i<facilities.length;i++){
            for (int j=0;j<points.length;j++){
                distMat[i][j] = distFrom(facilities[i].x, facilities[i].y, points[j].x, points[j].y);
            }
        }
        return distMat;
    }
    public int[][] findMinIndex (double[][] distMat){
        int[][] result = new int[1][2];
        double min = distMat[0][0];
        for (int i=0;i<distMat.length;i++){
            for (int j=0;j<distMat[0].length;i++){
                if (distMat[i][j] < min){
                    min = distMat[i][j];
                    result[0][0] = i;
                    result [0][1] = j;
                }
            }
        }
        return result;
    }
}
