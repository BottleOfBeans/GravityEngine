package App;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;


public class Particle extends GameWindow {
    //Init all the required variables for future use
    public double G = 6.674 * Math.pow(10, -11);
    public double mass;
    public Point central;
    public Point origin;
    public double radius;
    public double velocity;
    public Vector currentVector;
    public String name;
    public boolean crashed = false;
    public boolean isplanet;
    double distanceOffset = Math.pow(10,7);

    //Creating the instance once called
    public Particle(Point gCentral, double gmass, double gradius, double gvelocity, Vector gVector, String gname, boolean gisplanet) {
        central = gCentral;
        mass = gmass;
        radius = gradius;
        origin = new Point((int) (central.getX() - radius), (int) (central.getY() - radius));
        velocity = gvelocity;
        double xChange = gvelocity * Math.sin(gVector.getAngle());
        double yChange = gvelocity * Math.cos(gVector.getAngle());
        currentVector = new Vector(xChange, yChange);
        name = gname;
        isplanet = gisplanet;
    }

    //Creating a function that returns an ellipse to be drawn
    public Ellipse2D getParticle(){
        return new Ellipse2D.Double(origin.getX(), origin.getY(), radius*2, radius*2);
    }

    public void updateLocation(){
        origin = new Point((int) (origin.x+currentVector.x), (int) (origin.y + currentVector.y));
        central = new Point((int) (central.x+currentVector.x), (int) (central.y + currentVector.y));
    }

    //Creating a function that calculates the location of the particle every call
    public Ellipse2D computeLocation(Particle[] particles){

        for(Particle p : particles){

            /*
                Geometric Values
             */
            double xDist = (p.origin.getX() - origin.getX());
            double yDist = (p.origin.getY() - origin.getY());
            double dist = Math.sqrt( xDist*xDist + yDist*yDist) * distanceOffset;
            double angle = Math.atan2(xDist, yDist);
            double xChange = 0;
            double yChange = 0;

            //Checking for super close collision (Prevents slingshot due to clipping)
            if(dist < p.radius){

                //Fun goofy science stuff using the equations and stuff :)
                double acceleration = (p.mass * G / (dist*dist)) / mass;

                //Updates the changes on each axis
                xChange = (acceleration * Math.sin(angle)) / FPS; //Updates happen [FPS] times a second
                yChange = (acceleration * Math.cos(angle)) / FPS; //Updates happen [FPS] times a second

            }
            //Updates the movement vector of the object
            currentVector = currentVector.add(new Vector(xChange, yChange));
        }
        //Updates the location of the particle
        updateLocation();

        //Returns the new calculated particle
        return getParticle();
    }



   }
