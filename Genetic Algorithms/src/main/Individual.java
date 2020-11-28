package main;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.DoubleStream;

/**
 * @author Kimberly O'Neill
 * CSC 462
 * Homework 3
 */
public class Individual {

  private double[] chromosome;
  private double[] xValues;
  private double[] yValues;
  private double endX, endY;
  private double fitness = -1;
  private int chromoLength = 25;
  Random rand = new Random();

  /**
   * 
   * @param chromoLength
   */
  public Individual(int chromoLength) {
    DoubleStream individual = rand.doubles(chromoLength, 0, (Math.PI));
    this.chromosome = individual.toArray();
    xValues = new double[25];
    Arrays.fill(xValues, -1);
    yValues = new double[25];
    Arrays.fill(yValues, -1);
    xValues[0] = 0;
    yValues[0] = 0;
  }

  /**
   * 
   * @param chromosome
   */
  public Individual(double[] chromosome) {
    this.chromosome = chromosome;
    xValues = new double[25];
    Arrays.fill(xValues, -1);
    yValues = new double[25];
    Arrays.fill(yValues, -1);
    xValues[0] = 0;
    yValues[0] = 0;
  }

  /**
   * generates the snake for the given chromosome
   */
  public void draw() {

    for (int i = 0; i < xValues.length - 1; i++) {
      xValues[i + 1] = xValues[i] + Math.cos(chromosome[i]);
      yValues[i + 1] = yValues[i] + Math.sin(chromosome[i] / 2);
      if (xValues[i] < 0 || yValues[i] < 0) {
        break;
      }
      if(xValues[i] > 32 || yValues[i] > 18) {    //checks if the snakes go out of bounds
        break;
      }
    }

    this.endX = xValues[xValues.length - 1];
    this.endY = yValues[yValues.length - 1];
  }

  /**
   * Calculates fitness which is the distance between the end of the snake
   * and the goal point
   * @param pg
   */
  public void calcFitness(Playground pg) {
    this.draw();
    if(this.endX == -1 || this.endY == -1)
      this.fitness = 101;
    else {
    double x = this.endX - pg.getEndX();
    double y = this.endY - pg.getEndY();
    double xSq = Math.pow(x, 2);
    double ySq = Math.pow(y, 2);
    double add = xSq + ySq;
    double ans = Math.sqrt(add);
    
    this.fitness = ans;
    }
  }

  public double[] getChromo() {
    return this.chromosome;
  }

  public void setGene(int index, double gene) {
    this.chromosome[index] = gene;
  }

  public double getGene(int index) {
    return this.chromosome[index];
  }

  public double getFitness() {
    return this.fitness;
  }

  public void setChromoLength(int l) {
    this.chromoLength = l;
  }

  public int getChromoLength() {
    return this.chromoLength;
  }

  @Override
  public String toString() {
    return "Individual " + this.fitness;
  }
  
  

}
