package LAB_4;

public class ComprehensivePolicy extends InsurancePolicy {
    int driverAge;
    int level;


    public ComprehensivePolicy(String policyHolderName, int id, Car car, int numberOfClaims, MyDate expiryDate, int driverAge, int level) {
        super(policyHolderName, id, car, numberOfClaims, expiryDate);
        this.driverAge = driverAge;
        this.level = level;
    }
    //copy constructor
    public ComprehensivePolicy(InsurancePolicy insurancePolicy, ComprehensivePolicy comprehensivePolicy) {
        super(insurancePolicy);
        this.driverAge = comprehensivePolicy.driverAge;
        this.level = comprehensivePolicy.level;
    }

    public void print(){
        super.print();
        System.out.println("Driver Age : "+this.driverAge);
        System.out.println("Level : "+this.level);
    }

    public String toString() {
        return super.toString() + " |Driver Age: " + this.driverAge + " |Level: " + level;
    }

    public double calcPremium(int flatRate){
        double cost = car.price/50+numberOfClaims*200+flatRate;
        if (driverAge < 30){
            cost = cost + (30 - driverAge)*50;
        }
        return cost;
    }
}


