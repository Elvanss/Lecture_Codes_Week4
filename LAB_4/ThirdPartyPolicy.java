package LAB_4;

public class ThirdPartyPolicy extends InsurancePolicy {
    protected String comment;

    public ThirdPartyPolicy(String policyHolderName, int id, Car car, int numberOfClaim, MyDate expiryDate, String comment) {
        super(policyHolderName, id, car, numberOfClaim, expiryDate);
        this.comment = comment;
    }

    public ThirdPartyPolicy(InsurancePolicy insurancePolicy, ThirdPartyPolicy thirdPartyPolicy) {
        super(insurancePolicy);
        this.comment = thirdPartyPolicy.comment;
    }

    public void print() {
        super.print();
        System.out.println("Comments: "+this.comment);
    }
    public String toString() {
        return super.toString() + " |Comment: " + this.comment;
    }

    public double calcPremium (int flatRate) {
        return (car.price/100 + numberOfClaims * 200 + flatRate);
        }
    }



