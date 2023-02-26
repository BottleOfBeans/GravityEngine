package App;

public class Vector {
    public  double x;
    public  double y;

    public Vector(double gx, double gy){
        x = gx;
        y = gy;
    }


    public Vector add(Vector givenVector){return new Vector (x+ givenVector.getX(), y +  givenVector.getY());}
    public Vector sub(Vector givenVector){return new Vector (x+ givenVector.x, y -  givenVector.y);}
    public double distance(){return Math.sqrt(x*x + y*y);}
    public double getX(){return x;}
    public double getY(){return y;}
    public void printVector(){System.out.println("X: "+x+"    Y: "+y);}
    public double getAngle(){return Math.atan2(x, y);
    }
}
