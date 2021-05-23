package CalculateAGI;

public class GrossIncome
{
    protected String name;
    protected String fStatus;
    protected double w2;
    protected double schC;
    protected double schE;
    protected double retirement;
    protected double capital;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getfStatus()
    {
        return fStatus;
    }

    public void setfStatus(String fStatus)
    {
        this.fStatus = fStatus;
    }

    public double getW2()
    {
        return w2;
    }

    public void setW2(double w2)
    {
        this.w2 = w2;
    }

    public double getSchC()
    {
        return schC;
    }

    public void setSchC(double schC)
    {
        this.schC = schC;
    }

    public double getSchE()
    {
        return schE;
    }

    public void setSchE(double schE)
    {
        this.schE = schE;
    }

    public double getRetirement()
    {
        return retirement;
    }

    public void setRetirement(double retirement)
    {
        this.retirement = retirement;
    }

    public double getCapital()
    {
        return capital;
    }

    public void setCapital(double capital)
    {
        this.capital = capital;
    }

    public GrossIncome(String name, String status, double W2, double capital, double schE, double schC,
                       double retirement)
    {
        this.name = name;
        this.fStatus = status;
        this.w2 = W2;
        this.capital = capital;
        this.schC = schC;
        this.schE = schE;
        this.retirement = retirement;
    }

    public double getGI()
    {
        double grossIncome = w2 + capital + schC + schE + retirement;
        return grossIncome;
    }


    
}
