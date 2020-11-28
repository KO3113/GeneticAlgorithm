package main;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Kimberly O'Neill 
 * CSC 462 
 * Homework 3
 */

public class Population {

  private Individual[] population;
  private double popFitness = -1;
  private int popSize;
  private int index = 0;
  Playground p;

  public Population(int popSize, Playground pg) {
    this.popSize = popSize;
    this.p = pg;
    this.population = new Individual[popSize];
    for (int j = 0; j < popSize; j++) {
      Individual i = new Individual(25);
      population[j] = i;
    }
  }

  /**
   * returns the fittest individual after sorting pop by fitness
   * @param index
   * @return
   */
  public Individual getFittest(int index) {
    // Order population by fitness
    Arrays.sort(this.population, new Comparator<Individual>() {
      @Override
      public int compare(Individual o1, Individual o2) {
        o1.calcFitness(p);
        o2.calcFitness(p);
        if (o1.getFitness() < o2.getFitness()) {
          return -1;
        } else if (o1.getFitness() > o2.getFitness()) {
          return 1;
        }
        return 0;
      }
    });

    // Return the fittest individual
    return this.population[index];
  }

  public void setPopFitness(double fitness) {
    this.popFitness = fitness;
  }

  public double getPopFitness() {
    return this.popFitness;
  }

  public void setIndividual(Individual individual) {
    population[this.index] = individual;
    this.index++;
    if (this.index >= this.popSize)
      this.index = this.popSize - 1;
  }

  public Individual getIndividual(int index) {
    return population[index];
  }

  public Individual[] getIndividuals() {
    return this.population;
  }

  public int getPopSize() {
    return this.popSize;
  }

  public int getIndex() {
    return index;
  }

  public void setIndex(int index) {
    this.index = index;
  }
}
