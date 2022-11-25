package LAB_4;

import java.util.ArrayList;
import java.util.Collections;
import static LAB_4.TestCase.flatRate;
public class User implements Cloneable, Comparable<User> {
    private String name;
    private int userID;
    private Address address;
    private String password;
    private ArrayList<InsurancePolicy> policies;

    public User(String name, int userID, Address address, String password) {
        this.name = name;
        this.userID = userID;
        this.address = address;
        this.password = password;
        this.policies = new ArrayList<>();
    }
    //copy constructor
    public User(User user) {
        this.name = user.name;
        this.userID = user.userID;
        this.address = user.address;
        this.password = user.password;
        ArrayList<InsurancePolicy> policies = user.policies;
    }

    protected User clone() throws CloneNotSupportedException {
        return (User) super.clone();
    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getUserID() {
        return userID;
    }
    
    public void setUserID(int userID) {
        this.userID = userID;
    }
    
    public Address getAddress() {
        return address;
    }
    
    public void setAddress(Address address) {
        this.address = address;
    }
    
    public void setCity(String city) { 
    	address.setCity(city); 
    	}
    
    public String getCity() { 
    	return address.getCity(); 
    	}

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public ArrayList<InsurancePolicy> getPolicies() {
        return policies;
    }
    
    public void setPolicies(ArrayList<InsurancePolicy> policies) {
        this.policies = policies;
    }

        boolean addPolicy(InsurancePolicy policy) {
            for (InsurancePolicy AddPolicy : policies) {
                if (AddPolicy.getId() == policy.getId()) {
                    return false;
                }
            }
            policies.add(policy);
            return true;
        }

        public InsurancePolicy findPolicy(int policyID) {
        for (InsurancePolicy FindPolicy: policies) {
            if (FindPolicy.getId() == policyID)
                return FindPolicy;
        }
        return null;
    }

    public void print() {
            System.out.println("Name: " + name);
            System.out.println("User ID: " + userID);
            System.out.println("Address: ");
            System.out.println("- Street Nụmber: " + address.streetNum);
            System.out.println("- Street Name: " + address.street);
            System.out.println("- Suburb: " + address.suburb);
            System.out.println("- City: " + address.city);
            System.out.println("<----------------------------------->");
            InsurancePolicy.printPolicies(policies);
            System.out.println();
        }

    public String toString() {
         String PolicyUser = " |User ID: " + userID + " |Name:" + name + " |Address:" + address + "\n";
        for(InsurancePolicy user : policies) {
            PolicyUser = PolicyUser + user.toString()+ "\n";
            }
        return PolicyUser;
        }


    public void printPolicies ( int flatRate) {
        InsurancePolicy.printPolicies(policies);
    }

    public double calcTotalPremium(int flatRate) {
        return InsurancePolicy.calcTotalPayments(policies, flatRate);
    }

    public void carPriceRiseAll (double risePercent) {
        InsurancePolicy.carPriceRiseAll(policies, risePercent);
    }

    public ArrayList <InsurancePolicy> filterByCarModel (String carModel) {
        return InsurancePolicy.filterByCarModel(policies, carModel);
    }

    boolean createThirdPartyPolicy(String policyHolderName, int id, Car car, int numberOfClaims, MyDate expiryDate, String comments) {
    	return addPolicy(new ThirdPartyPolicy(policyHolderName, id, car, numberOfClaims, expiryDate,comments));
    	
    }
    
    public boolean createComprehensivePolicy( String policyHolderName, int id, Car car, int numberOfClaims, MyDate expiryDate, int driverAge, int level)
    {
    	return addPolicy(new ComprehensivePolicy(policyHolderName,  id,  car,  numberOfClaims,  expiryDate,  driverAge,  level));        
    }
    
    public ArrayList<InsurancePolicy> filterByExpiryDate(MyDate date) {
    	return InsurancePolicy.filterByExpiryDate(policies,date);
    }


    public boolean validateUser(int userID, String password) {
        if ((this.userID==userID) && this.password.equals(password)) {
            return true;
        }
        return false;
    }

    public ArrayList<String> populateDistinctCarModels() {
        ArrayList<String> models =new ArrayList<String>();
        for (InsurancePolicy policy: policies) {
            boolean userCarCheck=false;
            for (String disModel: models) {
                if (policy.getCarModel().equals(disModel)) {
                    userCarCheck=true;
                    break;
                }
            }
            if (!userCarCheck) {
                models.add(policy.getCarModel());
            }
        }
        return models;
    }

    public int getTotalCountForCarModel(String carModel) {
        int totalCount=0;
        for(InsurancePolicy policy:policies) {
            if(policy.getCarModel().equals(carModel))
                totalCount = totalCount +1;
        }
        return totalCount;
    }

    public double getTotalPaymentForCarModel(String carModel, int flatRate) {
        double total=0;
        for( InsurancePolicy policy:policies) {
            if(policy.getCarModel().equals(carModel)) {
                total += policy.calcPremium(flatRate);
            }
        }
        return total;
    }

    public ArrayList<Integer> getTotalCountPerCarModel (ArrayList<String> carModels) {
        ArrayList<Integer> totalCounts=new ArrayList<Integer>();
        for (String model:carModels) {
            totalCounts.add(getTotalCountForCarModel(model));
        }
        return totalCounts;
    }

    public ArrayList<Double> getTotalPaymentPerCarModel (ArrayList<String> carModels,int flatRate) {
        ArrayList<Double> totalPayments=new ArrayList<Double>();
        for (String model:carModels) {
            totalPayments.add(getTotalPaymentForCarModel(model,flatRate));
        }
        return totalPayments;
    }

    public void reportPaymentsPerCarModel (ArrayList<String> carModels, ArrayList<Integer>counts, ArrayList<Double> premiums) {
        System.out.println("Model" +"      "+ "Total" +"       "+ "Average");
        for (int i=0;i<counts.size();i++)
            System.out.println(carModels.get(i)+ "    "+ premiums.get(i) +"    "+ (double)(premiums.get(i)/counts.get(i)));
        System.out.println();
    }


    public static ArrayList<User> shallowCopy (ArrayList< User> users) throws CloneNotSupportedException  {
            ArrayList<User> shallowUser = new ArrayList<User>();
                for (User shallowCopy: users) {
                    shallowUser.add(shallowCopy.clone());
                }
        return shallowUser;
    }
    public static ArrayList<User> deepCopy (ArrayList< User> users) throws CloneNotSupportedException {
        ArrayList<User> deepUser = new ArrayList<User>();
            for (User deepCopy: users) {
                User CloneUser = deepCopy.clone();
                CloneUser.name = deepCopy.getName();
                CloneUser.userID = deepCopy.getUserID();
                CloneUser.address = deepCopy.getAddress().clone();
                CloneUser.password = deepCopy.getPassword();
                CloneUser.policies = deepCopy.getPolicies();
            }
        return deepUser;
    }
    public static ArrayList< InsurancePolicy> deepCopyPolicies() throws CloneNotSupportedException  {
        ArrayList<InsurancePolicy> deepList = new ArrayList<InsurancePolicy>();
            for(InsurancePolicy list: deepList) {
                deepList.add(list.clone());
            }
        return deepList;
    }

    public static ArrayList< InsurancePolicy>  shallowCopyPolicies() {
        ArrayList<InsurancePolicy> shallowList = new ArrayList<InsurancePolicy>();
        for (InsurancePolicy list : shallowList) {
            shallowList.add(list);
        }
        return shallowList;
    }

    public int compareTo(User userAddress) {
        return address.compareTo(userAddress.getAddress());
    }

    public int compareTo1(User calculate) {
        if (this.calcTotalPremium(flatRate) == calculate.calcTotalPremium(flatRate)) {
            return 0;
        }
        if (this.calcTotalPremium(flatRate) > calculate.calcTotalPremium(flatRate)) {
            return 1;
        } else {
            return -1;
        }
    }

    public ArrayList<InsurancePolicy> sortPoliciesByDate() {
        ArrayList<InsurancePolicy> policySorted = new ArrayList<InsurancePolicy>();
                Collections.sort(policySorted);
        return policySorted;
    }
}








