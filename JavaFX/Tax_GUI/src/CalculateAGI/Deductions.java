package CalculateAGI;

public class Deductions
{
    private final double STATE_LIMIT = 10000;
    private final double MED_LIMIT = 0.1;
    private final double CHARITABLE_LIMIT = 0.6;
    private final double MFJ_SD = 24400;
    private final double HOH_SD = 18350;
    private final double SINGLE_SD = 12200;
    protected double mortInt;
    protected double mortIns;
    protected double charitable;
    protected double medExp;
    protected double slTax;

    public Deductions(double mortInt, double mortIns, double charitable, double medExp,
                      double slTax)
    {
        this.mortInt = mortInt;
        this.mortIns = mortIns;
        this.charitable = charitable;
        this.medExp = medExp;
        this.slTax = slTax;
    }

    public double getMortInt()
    {
        return mortInt;
    }

    public void setMortInt(double mortInt)
    {
        this.mortInt = mortInt;
    }

    public double getMortIns()
    {
        return mortIns;
    }

    public void setMortIns(double mortIns)
    {
        this.mortIns = mortIns;
    }

    public double getCharitable()
    {
        return charitable;
    }

    public void setCharitable(double charitable)
    {
        this.charitable = charitable;
    }

    public double getMedExp()
    {
        return medExp;
    }

    public void setMedExp(double medExp)
    {
        this.medExp = medExp;
    }

    public double getSlTax()
    {
        return slTax;
    }

    public void setSlTax(double slTax)
    {
        this.slTax = slTax;
    }

    public double getSLTaxDeduct()
    {
        double slDeduct;

        if (this.slTax > this.STATE_LIMIT)
        {
            slDeduct = this.STATE_LIMIT;
        }
        else
        {
            slDeduct = this.slTax;
        }

        return slDeduct;
    }

    public double getMedDeduct(double agi)
    {
        double medDeduct;

        if (this.medExp > agi * this.MED_LIMIT)
        {
            medDeduct = this.medExp - (agi * this.MED_LIMIT);
        }
        else
        {
            medDeduct = 0;
        }

        return medDeduct;
    }

    public double getCharDeduct(double agi)
    {
        double charDeduct;

        if (this.charitable > agi * CHARITABLE_LIMIT)
        {
            charDeduct = agi * CHARITABLE_LIMIT;
        }
        else
        {
            charDeduct = this.charitable;
        }

        return charDeduct;
    }

    public double getItemize(double agi)
    {
        double charDeduct = getCharDeduct(agi);
        double medDeduct = getMedDeduct(agi);
        double slDedcut = getSLTaxDeduct();
        double itemize = this.mortInt + this.mortIns + charDeduct + medDeduct + slDedcut;
        return itemize;
    }

    public String itemOrStandard(String status, double itemize)
    {
        String result = "";

        if (status.equals("MFJ"))
        {
            if (itemize > MFJ_SD)
            {
                result = "Take Itemized Deduction of " + itemize;
            }
            else
            {
                result = "Take Standard Deduction of " + MFJ_SD;
            }
        }
        else if (status.equals("HOH"))
        {
            if (itemize > HOH_SD)
            {
                result = "Take Itemized Deduction of" + itemize;
            }
            else
            {
                result = "Take Standard Deduction of " + HOH_SD;
            }
        }
        else
        {
            if (itemize > MFJ_SD)
            {
                result = "Take Itemized Deduction of " + itemize;
            }
            else
            {
                result = "Take Standard Deduction of " + SINGLE_SD;
            }
        }

        return result;
    }



}
