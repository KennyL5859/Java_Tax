package CalculateAGI;

public class CalcAGI extends GrossIncome
{
    protected final double IRA_LIMIT = 6000;
    protected final double STU_LOAN = 2500;
    protected final double HSA_SINGLE = 3500;
    protected double HSA;
    protected double stuLoan;
    protected double IRA;
    protected double SeTax;


    public CalcAGI(String name, String status, double W2, double capital, double schE, double schC, double retirement,
                   double HSA, double stuLoan, double IRA, double SeTax)
    {
        super(name, status, W2, capital, schE, schC, retirement);
        this.HSA = HSA;
        this.stuLoan = stuLoan;
        this.IRA = IRA;
        this.SeTax = SeTax;
    }

    public double getSE_Dedcut()
    {
        double seTaxDeduct = this.SeTax / 2;
        return seTaxDeduct;
    }

    public double getIRA_Dedcut()
    {
        double iraDeduct;

        if (this.IRA > IRA_LIMIT)
        {
            iraDeduct = IRA_LIMIT;
        }
        else
        {
            iraDeduct = this.IRA;
        }
        return iraDeduct;
    }

    public double getStu_Deduct()
    {
        double stuDeduct;

        if (this.stuLoan > STU_LOAN)
        {
            stuDeduct = STU_LOAN;
        }
        else
        {
            stuDeduct = this.stuLoan;
        }
        return stuDeduct;
    }

    public double getHSA_Deduct()
    {
        double hsaDeduct;

        if (this.fStatus.equals("MFJ") || this.fStatus.equals("HOH"))
        {
            if (this.HSA > HSA_SINGLE * 2)
            {
                hsaDeduct = HSA_SINGLE * 2;
            }
            else
            {
                hsaDeduct = this.HSA;
            }
        }
        else
        {
            if (this.HSA > HSA_SINGLE)
            {
                hsaDeduct = HSA_SINGLE;
            }
            else
            {
                hsaDeduct = this.HSA;
            }
        }
        return hsaDeduct;
    }

    public double getAGI()
    {
        double hsaDeduct = getHSA_Deduct();
        double stuDeduct = getStu_Deduct();
        double iraDeduct = getIRA_Dedcut();
        double seTaxDeduct = getSE_Dedcut();
        double agi = (this.w2 + this.capital + this.schC + this.schE +
                this.retirement - hsaDeduct - stuDeduct - iraDeduct -
                seTaxDeduct);
        return agi;
    }
}
