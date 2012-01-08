package com.webofthings.emf.experiments;


/**
 * Bean class containing the data of an experiment: i.e., instructions and conclusions.
 * @author <a href="http://www.guinard.org">Dominique Guinard</a>
 */
public class ExperimentInfo
{
  private String instructions;
  private String conclusion;

  public ExperimentInfo(String instructions, String conclusion)
  {
    this.instructions = instructions;
    this.conclusion = conclusion;
  }

  public ExperimentInfo()
  {
  }

  public String getInstructions()
  {
    return instructions;
  }

  public void setInstructions(String instructions)
  {
    this.instructions = instructions;
  }

  public String getConclusion()
  {
    return conclusion;
  }

  public void setConclusion(String conclusion)
  {
    this.conclusion = conclusion;
  }
}
