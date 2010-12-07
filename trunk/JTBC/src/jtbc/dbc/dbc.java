package jtbc.dbc;

public abstract interface dbc
{
  public abstract void Execute(String paramString);

  public abstract void setConnStr(String paramString);

  public abstract int Executes(String paramString);

  public abstract int getRState();

  public abstract Object[] getDataAry(String paramString);

  public abstract Object getValue(Object[][] paramArrayOfObject, int paramInt);

  public abstract Object getValue(Object[][] paramArrayOfObject, String paramString);

  public abstract String getEMessage();
}