package main;

import java.util.Arrays;
import java.util.Random;

/**
 * @author Kimberly O'Neill
 * CSC 462
 * Homework 3
 */

public class GeneticAlgorithm {

  private int popSize;
  private double crossoverRate;
  public Playground pg;

  public GeneticAlgorithm(int populationSize, double crossoverRate, Playground pg) {

    this.popSize = populationSize;
    this.crossoverRate = crossoverRate;
    this.pg = pg;
  }

  public Population init() {
    return new Population(this.popSize, this.pg);
  }

  /**
   * Calculate the distance from the last segment of the individual to the end
   * point
   * 
   * @param i
   * @return
   */
  public double calcFitness(Individual i) {
    i.draw();
    i.calcFitness(pg);
    return i.getFitness();
  }

  /**
   * gets an average fitness for the whole population
   * @param population
   */
  public void evaluatePop(Population population) {
    double populationFitness = 0;

    // Loop over population evaluating individuals and summing population fitness
    for (Individual individual : population.getIndividuals()) {
      individual.calcFitness(pg);
      populationFitness += individual.getFitness();
    }

    double avgFitness = populationFitness / population.getPopSize();
    population.setPopFitness(avgFitness);
  }
  
  /**
   * Checks if there is a hit or max generations is reached
   * @param generationsCount
   * @param maxGenerations
   * @param p
   * @return
   */

  public boolean terminationCheck(int generationsCount, int maxGenerations, Population p) {
    boolean flag = false;
    if (generationsCount > maxGenerations) {
      flag = true;
    }
    for (int i = 0; i < popSize; i++) {
      p.getIndividual(i).calcFitness(pg);
      if (p.getIndividual(i).getFitness() <= 0.5) {
        flag = true;
        System.out.println("Hit!");
      }
    }
    return flag;
  }

  /**
   *  selects a random individual from the population
   * @param population
   * @return a random individual to be the parent
   */
  public Individual chooseParent(Population population) {
    Random rand = new Random();
    int index = rand.nextInt(population.getPopSize());
    return population.getIndividual(index);
  }

  /**
   * Applies crossover to the population
   * @param population
   * @return new population of offspring
   */
  public Population crossoverPopulation(Population population) {
    Random rand = new Random();
    // Create new population
    Population newPopulation = new Population(population.getPopSize(), this.pg);
    int k = 0;
    /*
     * CUT POINT 2 random for each generation int cutPoint = rand.nextInt(24);
     */

    /*
     * CUT POINT 3 fixed int cutPoint = 12
     */
    
    /*
     * CUT POINT 4 fixed int cutPoint = 20
     */
    while (newPopulation.getIndex() < newPopulation.getPopSize() && k < newPopulation.getPopSize() / 2) {
      // Get parent1
      Individual parent1 = population.getFittest(k);

      // Apply crossover to this individual
      if (this.crossoverRate > Math.random()) {
        Individual parent2 = this.chooseParent(population);

        // Create blank offspring chromosomes
        double[] offspringChromosome = new double[25];
        Arrays.fill(offspringChromosome, -1);
        Individual offspring = new Individual(offspringChromosome);

        double[] offspringChromosome2 = new double[25];
        Arrays.fill(offspringChromosome2, -1);
        Individual offspring2 = new Individual(offspringChromosome2);

        /*
         * CUT POINT 1 random for each pairing int cutPoint = rand.nextInt(24);
         */
        int cutPoint = rand.nextInt(24);
        // Loop and add the subset from parent1 to our child
        for (int i = 0; i < cutPoint; i++) {
          offspring.setGene(i, parent1.getGene(i));
          offspring2.setGene(i, parent2.getGene(i));
        }

        for (int j = cutPoint; j < parent1.getChromoLength(); j++) {
          offspring.setGene(j, parent2.getGene(j));
          offspring2.setGene(j, parent1.getGene(j));
        }
        
        // Add children
        newPopulation.setIndividual(offspring);
        newPopulation.setIndividual(offspring2);

      } else {
        // don't apply crossover
        newPopulation.setIndividual(parent1);
      }
      k++;
    }
    return newPopulation;
  }

}
