package medianProblem;
public class Point{
    public double id = 0;
    public double x = 0;
    public double y = 0;
    public double demand = 0;

    public Point (int id, double x, double y, double demand){
        this.id = id;
        this.x = x;
        this.y = y;
        this.demand = demand;
    }

    public double getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getDemand() {
        return demand;
    }

    public void setDemand(double demand) {
        this.demand = demand;
    }
}