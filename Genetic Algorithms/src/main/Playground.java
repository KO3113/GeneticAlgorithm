package main;

/**
 * @author Kimberly O'Neill
 * CSC 462
 * Homework 3
 */

public class Playground {

  private int startX, startY;
  private int endX, endY;
  
  public Playground(int sX, int sY, int eX, int eY) {
    this.startX = sX;
    this.startY = sY;
    this.endX = eX;
    this.endY = eY;
  }

  public int getStartX() {
    return startX;
  }

  public void setStartX(int startX) {
    this.startX = startX;
  }

  public int getStartY() {
    return startY;
  }

  public void setStartY(int startY) {
    this.startY = startY;
  }

  public int getEndX() {
    return endX;
  }

  public void setEndX(int endX) {
    this.endX = endX;
  }

  public int getEndY() {
    return endY;
  }

  public void setEndY(int endY) {
    this.endY = endY;
  }
  
  
}
