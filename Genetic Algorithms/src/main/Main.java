package main;

/**
 * @author Kimberly O'Neill
 * CSC 462
 * Homework 3
 */

public class Main {

  public static final int HEIGHT = 18;
  public static final int WIDTH = 32;
  public static final int MAX_GEN = 1000;

  public static void main(String[] args) {
    Playground p = new Playground(2, 3, 10, 12);
    GeneticAlgorithm ga = new GeneticAlgorithm(25, 0.95, p);

    // Initialize population
    Population population = ga.init();
    int generation = 1;
    for (Individual i : population.getIndividuals()) {
      i.draw();
      i.calcFitness(p);
    }
    
    ga.evaluatePop(population);

    // Start evolution loop
    while (!ga.terminationCheck(generation, MAX_GEN, population)) {
      //so i can see it's working
      System.out.println("-----------------------------------------");
      System.out.println("Generation " + generation);
      System.out.println("-----------------------------------------");
      
      population = ga.crossoverPopulation(population);
      ga.evaluatePop(population);
      for(int i = 0; i < population.getPopSize(); i++) {
        System.out.println(i + " " + population.getIndividual(i).toString());
      }
      generation++;
    }

    System.out.println("-----------------------------------------");
    //prints generations and best fitness
    System.out.println("Stopped after " + (generation-1) + " generations.");
    Individual indiv = population.getFittest(0);
    System.out.println("Best Fitness: " + indiv.getFitness());
    System.out.println("-----------------------------------------");
    
    for(int j = 0; j < 25; j++) {
      System.out.print("Angle " + j + ": " + indiv.getGene(j) + "   ");
      if(j % 4 == 0)
        System.out.println();
    }
  }
}
