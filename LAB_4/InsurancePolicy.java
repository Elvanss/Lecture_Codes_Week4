package LAB_4;
import java.util.ArrayList;


public abstract class InsurancePolicy implements Cloneable, Comparable<InsurancePolicy>  {
    protected String policyHolderName;
    protected int id;
    protected Car car;
    protected int numberOfClaims;
    private MyDate expiryDate;

    //constructor
    public InsurancePolicy(String policyHolderName, int id, Car car, int numberOfClaims, MyDate expiryDate) {
        this.policyHolderName = policyHolderName;
        this.id = id;
        this.car = car;
        this.numberOfClaims = numberOfClaims;
        this.expiryDate = expiryDate;
    }

    //copy constructor
    public InsurancePolicy(InsurancePolicy insurancePolicy) {
        this.policyHolderName = insurancePolicy.policyHolderName;
        this.id = insurancePolicy.id;
        this.car = insurancePolicy.car;
        this.numberOfClaims = insurancePolicy.numberOfClaims;
        this.expiryDate = insurancePolicy.expiryDate;
    }
    protected InsurancePolicy clone() throws CloneNotSupportedException {
        return (InsurancePolicy) super.clone();
    }



    public int compareTo(InsurancePolicy dateCheck) {
        return expiryDate.compareTo(dateCheck.expiryDate);
    }

    public int compareTo1(InsurancePolicy priceCheck) {
        return 0;

    }

    //getter and setter
    public String getPolicyHolderName() {
        return policyHolderName;
    }
    
    public void setPolicyHolderName(String policyHolderName) {
        this.policyHolderName = policyHolderName;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public Car getCar() {
        return car;
    }
    
    public String getCarModel() { 
    	return car.getModel(); 
    	}
    
    public void setCarModel(String model) { 
    	car.setModel(model); 
    	}
    
    public void setCar(Car car) {
        this.car = car;
    }
  //optional method I created to get the same manufacturing year with the changed car name
    public void setCarManufacturingYear(int manufacturingYear) {
        car.setManufacturingYear(manufacturingYear);
    }
    
    public int getNumberOfClaims() {
        return numberOfClaims;
    }
    
    public void setNumberOfClaims (int numberOfClaims) {
        this.numberOfClaims = numberOfClaims;
    }
    
    public MyDate getExpiryDate() {
        return expiryDate;
    }
    
    public void setExpiryDate(MyDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    //print() method
    public void print() {
        System.out.println("Policy Holder name: " + policyHolderName);
        System.out.println("Id: " + id);
        System.out.println("Car Model: " + car.model);
        System.out.println("Car Manufacturing Year: " + car.manufacturingYear);
        System.out.printf("Car Price: $%.0f\n", car.price);
        System.out.println("Number of Claims: " + numberOfClaims);
        System.out.println("The Expiry Date: " + expiryDate);
    }

    //toString() method
    public String toString() {
        return " |Policy Holder Name: " + policyHolderName + " |Id: " + id + car.toString() + " |Number Of Claims: " + numberOfClaims + " |The Expiry Date: " + expiryDate;
    }

    public abstract double calcPremium(int flatRate);

    static void printPolicies(ArrayList<InsurancePolicy> policies) {
        for (InsurancePolicy member : policies) {
            member.print();
            System.out.println("<----------------------------------->");
        }
    }

    static double calcTotalPayments(ArrayList <InsurancePolicy> policies, int flatRate) {
        double totalPremium = 0;
        for (InsurancePolicy member : policies) {
            totalPremium =totalPremium + member.calcPremium(flatRate);
        }
        return totalPremium;
    }

    public void carPriceRise(double risePercent) {
        car.priceRise(risePercent);
    }
    public static void carPriceRiseAll(ArrayList<InsurancePolicy> policies, double risePercent) {
        for (InsurancePolicy AllPolicy : policies) {
            AllPolicy.carPriceRise(risePercent);
        }
    }

    public static ArrayList<InsurancePolicy>filterByCarModel (ArrayList<InsurancePolicy>policies, String carModel) {
        ArrayList<InsurancePolicy> filteredList = new ArrayList<>();
        for (InsurancePolicy filterPolicy : policies) {
            if (filterPolicy.getCar().getModel().equals(carModel)) {
                filteredList.add(filterPolicy);
            }
        }
        return filteredList;
    }
    
    public static ArrayList<InsurancePolicy> filterByExpiryDate (ArrayList<InsurancePolicy> policies, MyDate date) {
        ArrayList<InsurancePolicy> filteredExpiry = new ArrayList<> ();
        for(InsurancePolicy expiry: policies) {
            if(expiry.getExpiryDate().isExpired(date)) {
                filteredExpiry.add(expiry);
            }
        }
        return filteredExpiry;
    }

    public static ArrayList< InsurancePolicy> shallowCopy (ArrayList< InsurancePolicy> policies) {
        ArrayList<InsurancePolicy> shallowPolicy = new ArrayList<InsurancePolicy>();
        for(InsurancePolicy shallowCopy: policies) {
            shallowPolicy.add(shallowCopy);
        }
        return shallowPolicy;
    }

    public static ArrayList< InsurancePolicy> deepCopy (ArrayList< InsurancePolicy> policies) throws CloneNotSupportedException {
        ArrayList<InsurancePolicy> deepPolicy = new ArrayList<InsurancePolicy>();
            for (InsurancePolicy deepCopy: policies){
                InsurancePolicy clonePolicy = deepCopy.clone();
                clonePolicy.policyHolderName = deepCopy.getPolicyHolderName();
                clonePolicy.id = deepCopy.getId();
                clonePolicy.car = deepCopy.getCar().clone();
                clonePolicy.numberOfClaims = deepCopy.getNumberOfClaims();
                clonePolicy.expiryDate = deepCopy.getExpiryDate().clone();
            }
            return deepPolicy;
        }


}
















