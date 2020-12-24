package models;

import kg2019examples_task4threedimensions.math.Vector3;
import kg2019examples_task4threedimensions.third.IModel;
import kg2019examples_task4threedimensions.third.PolyLine3D;

import java.awt.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static java.lang.Math.*;

public class Icosahedron implements IModel {
    private Vector3 center;
    private float radius;
    private float angle;
    private Color color;
    private double alfa;

    public Icosahedron(Vector3 center, float radius, Color color) {

        this.center = center;
        this.radius = radius;
        this.color = color;
        this.angle = 0;
        this.alfa = (2 * PI / 10);
    }




    @Override
    public List<PolyLine3D> getLines() {
        List<PolyLine3D> triangles = new LinkedList<>();
        double alpha = (2 * PI / 5);

        for (int j = 0; j < 4; j++) {
            Vector3 p1, p2, p3;
            int level = getLvl(j, false);
            int nextLevel = getLvl(j, true);

            for (int i = 0; i < 5; i++) {
                float x = getX(nextLevel, i, alpha);
                float y = getY(nextLevel, i, alpha);


                float x1 = getX(nextLevel, i + 1, alpha);
                float y1 = getY(nextLevel, i + 1, alpha);

                p1 = new Vector3(x, y, getZ(nextLevel));
                p2 = new Vector3(x1, y1, getZ(nextLevel));

                x = getX(level, i, alpha);
                y = getY(level, i, alpha);

                p3 = (new Vector3(x, y, getZ(level)));
                triangles.add(new PolyLine3D(Arrays.asList(p1, p2, p3), true, color));

            }
        }

        return triangles;
    }


    @Override
    public void rotate(float angle) {

    }

    private float getZ(int level) {

        if (level == 0) {
            return (center.getZ() + radius);
        } else if (level == 1) {
            return (center.getZ() + radius/2);
        } else if (level == 2) {
            return (center.getZ() -radius/2);
        } else {
            return (center.getZ() - radius);
        }
    }

    private float getX(int level, int i, double alpha) {
        if (level == 1) {
            return (float) (radius * Math.cos(alpha * i + angle) + center.getX());
        } else if (level == 2) {
            return (float) (radius * Math.cos(alpha * i + alfa+ angle) + center.getX());
        } else {
            return center.getX();
        }
    }

    private float getY(int level, int i, double alpha) {

        if (level == 1) {
            return (float) (radius * Math.sin(alpha * i + angle) + center.getY());
        } else if (level == 2) {
            return (float) (radius * Math.sin(alpha * i + alfa + angle) + center.getY());
        } else {
            return center.getY();
        }
    }
    private int getLvl(int j, boolean next){
        if (j == 0){
            if (next){
                return 1;
            } else {
                return 0;
            }
        } else if (j == 1){
            if (next){
                return 1;
            } else {
                return 2;
            }
        } else if (j == 2){
            if (next){
                return 1;
            } else {
                return 2;
            }
        } else {
            if (next) {
                return 2;
            } else {
                return 3;
            }
        }
    }
}
