package com.webofthings.emf.experiments;

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
