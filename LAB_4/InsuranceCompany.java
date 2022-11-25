package LAB_4;
import java.util.ArrayList;
import java.util.Collections;

public class InsuranceCompany implements Cloneable {
    private String name;
    private ArrayList <User> users;
    private String adminUsername;
    private String adminPassword;
    private int flatRate;

    public InsuranceCompany (String name, String adminUsername, String adminPassword, int flatRate ) {
        this.name = name;
        this.users = new ArrayList <>();
        this.adminUsername = adminUsername;
        this.adminPassword = adminPassword;
        this.flatRate = flatRate;
    }
	//copy constructor
	public InsuranceCompany (InsuranceCompany insuranceCompany) {
		this.name = insuranceCompany.name;
		ArrayList <User> users = insuranceCompany.users;
		this.adminUsername = insuranceCompany.adminUsername;
		this.adminPassword = insuranceCompany.adminPassword;
		this.flatRate = insuranceCompany.flatRate;
	}

	protected InsurancePolicy clone() throws CloneNotSupportedException {
		return (InsurancePolicy) super.clone();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<User> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}

	public String getAdminUsername() {
		return adminUsername;
	}

	public void setAdminUsername(String adminUsername) {
		this.adminUsername = adminUsername;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	public int getFlatRate() {
		return flatRate;
	}

	public void setFlatRate(int flatRate) {
		this.flatRate = flatRate;
	}

	public boolean validateAdmin(String username, String password) {
		if (adminUsername.equals(username) && adminPassword.equals(password)) {
			return true;
		} else {
		return false;
		}
	}

	public User validateUser (int userID, String password) {
		User user=findUser(userID);
		if ((user!= null) && user.validateUser(userID, password)) {
			return user;
		} else {
			return null;
		}
	}

    public User findUser(int userID) {
        for(User user : users) {
            if(user.getUserID() == userID) {
                return user;
            }
        }
		return null;
}
    public boolean addUser(User user) {
    	if(findUser(user.getUserID()) == null) {
            users.add(user);
            return true;
        } else {
            return false;
        }
    }

    public boolean addUser(String name, int userID, Address address, String password) {
    	User user = new User (name, userID, address, password);
    		return addUser(user);
    }



    public boolean addPolicy (int userID, InsurancePolicy policy) {
    	User user = findUser(userID);
    		if (user == null) {
    			return false;
    		}
    		return user.addPolicy(policy);
    }

    public InsurancePolicy findPolicy(int userID, int policyID) {
    	User user = findUser(userID);
        	if(user!= null) {
        		return user.findPolicy(policyID);
        	}
        return null;
    }

	public void printPolicies(int userID) {
		User user = findUser(userID);
		if(user != null) {
			user.printPolicies(flatRate);
		}
	}
	//create for 5th function (Interface)
	public void userInfo(int userID) {
		User user = findUser(userID);
		if(user != null) {
			user.print();
		} else {
			System.out.println("The user is not found! Try again...");
		}
	}

    public void print() {
    	System.out.println("Company name: "+name+" Username: " + adminUsername+ " Password: "+ adminPassword + " Flat Rate: "+flatRate);
    	for (User user: users) {
    		user.printPolicies(flatRate);
    	}
    }

    public String toString() {
    	String printUser = "Company name: "+name+" Username: " + adminUsername+ " Password: "+ adminPassword + " Flat Rate: "+flatRate;
    		for (User StringUser: users) {
    			printUser = printUser + StringUser.toString();
    		}
    		return printUser;
    	}

    public boolean createThirdPartyPolicy(int userID, String policyHolderName, int id, Car car, int numberOfClaims, MyDate expiryDate, String comments) {
    	User user = findUser(userID);
    		if (user != null) {
    			return user.createThirdPartyPolicy(policyHolderName, id, car, numberOfClaims, expiryDate, comments);
    		}
    		return false;
    }

    public boolean createComprehensivePolicy(int userID, String policyHolderName, int id, Car car, int numberOfClaims, MyDate expiryDate, int driverAge, int level) {
    	User user = findUser(userID);
    		if(user != null) {
    			return user.createComprehensivePolicy(policyHolderName, id, car, numberOfClaims, expiryDate, driverAge, level);
    		}
    		return false;
    }

	public static void userPrint(User user) {
		user.print();
	}

    public double calcTotalPayments(int userID) {
    	User user = findUser(userID);
    	if (user != null) {
    		return user.calcTotalPremium(userID);
    	}
    	return 0;
    }

   public double calcTotalPayments() {
	   double totalPayment = 0;
	   for (User user: users) {
		   totalPayment = totalPayment + user.calcTotalPremium(flatRate);
	   }
	   return totalPayment;
   }

   public boolean carPriceRise(int userID, double risePercent) {
	   User user = findUser(userID);
	   if (user != null) {
		   user.carPriceRiseAll(risePercent);
		   return true;
	   }
	   return false;
   }

   public void carPriceRise (double risePercent) {
	   for (User user: users) {
		   user.carPriceRiseAll(risePercent);
	   }
   }

   public ArrayList<InsurancePolicy> allPolicies() {
	   ArrayList<InsurancePolicy> policiesAll = new ArrayList();
	   for(User user: users) {
           for(InsurancePolicy policy: user.getPolicies()) {
               policiesAll.add(policy);
           }
       }
       return policiesAll;
   }

   public ArrayList<InsurancePolicy> filteredByCarModel(int userID, String carModel) {
	   User user = findUser(userID);
	   if (user !=null) {
		   return user.filterByCarModel(carModel);

	   }
	   return new ArrayList<InsurancePolicy>();
   }

   public ArrayList<InsurancePolicy> filterByExpiryDate(int userID, MyDate date) {
	   User user = findUser(userID);
	   if (user != null) {
		   return user.filterByExpiryDate(date);
	   }
	   return new ArrayList<InsurancePolicy>();
   }

   public ArrayList<InsurancePolicy> filteredByCarModel (String carModel) {
	   ArrayList<InsurancePolicy> filteredModelPolicies = new ArrayList();
		   for (User user: users) {
		   for (InsurancePolicy filteredModel: user.filterByCarModel(carModel)) {
			   filteredModelPolicies.add(filteredModel);
		   }
	   }
	   return filteredModelPolicies;
   }


	public ArrayList<InsurancePolicy> filterByExpiryDate (MyDate date) {
	   ArrayList<InsurancePolicy> filteredByDate = new ArrayList();
	   	for (User user: users) {
	   		for(InsurancePolicy ByDate: user.filterByExpiryDate(date)) {
	   			filteredByDate.add(ByDate);
	   		}
	   	}
	   return filteredByDate;
   }

   //-----Standard_Level-------
	public ArrayList<String> populateDistinctCityNames() {
		ArrayList<String> cities =new ArrayList<String>();
		for (User user:users) {
			boolean cityCheck= false;
			for (String city:cities) {
				if (user.getCity().equals(city)) {
					cityCheck = true;
					break;
				}
			}
			if (!cityCheck) {
				cities.add(user.getCity());
			}
		}
		return cities;
	}

	public double getTotalPaymentForCity(String city) {
		double totalForCity =0;
			for (User user: users) {
				if (user.getCity().equals(city)) {
					totalForCity = totalForCity + user.calcTotalPremium(flatRate);
				}
			}
		return totalForCity;
	}

	public ArrayList<Double> getTotalPaymentPerCity(ArrayList<String> cities) {
		ArrayList <Double> cityPayment = new ArrayList<Double>();
				for (String city: cities) {
					cityPayment.add(getTotalPaymentForCity(city));
				}
		return cityPayment;
	}

	public void reportPaymentPerCity( ArrayList<String> cities, ArrayList<Double> payments) {
		System.out.println("City Name     " + "     Total Premium Payment");
		for (int i =0; i<cities.size(); i++){
			System.out.printf("%10s %30s \n",cities.get(i), payments.get(i));
		}
	}

	public ArrayList<String> populateDistinctCarModels() {
		ArrayList<String> Models = new ArrayList<String>();
		for (User user : users) {
			ArrayList<String> carModel = user.populateDistinctCarModels();
			for (String carCategory : carModel) {
				boolean modelCheck = false;
				for (String model : Models) {
					if (model.equals(carCategory)) {
						modelCheck = true;
						break;
					}
				}
				if (!modelCheck) {
					Models.add(carCategory);
				}
			}
		}
			return Models;
		}

	public ArrayList<Integer> getTotalCountPerCarModel (ArrayList<String> carModels) {
        ArrayList<Integer> totalCounts=new ArrayList<Integer>();
        int amount=0; //set the amount = 0;
        for (String model:carModels) {
            for (User user:users) {
                amount = amount + user.getTotalCountForCarModel(model);
            }
            totalCounts.add(amount);
        }
        return totalCounts;
    }

	public ArrayList<Double> getTotalPaymentPerCarModel (ArrayList<String> carModels) {
		ArrayList<Double> total=new ArrayList<Double>();
		double countPay = 0;

		for (String model:carModels) {
			total.add((double)0);
		}
		for (User user:users) {
			ArrayList<Double> TotalPayment=user.getTotalPaymentPerCarModel(carModels,flatRate);
			for(int i=0; i<TotalPayment.size(); i++) {
				total.set(i, total.get(i) + TotalPayment.get(i));
			}
		}
		return total;
	}

	public void reportPaymentsPerCarModel (ArrayList<String> carModels, ArrayList<Integer>counts, ArrayList<Double> payments) {
		System.out.println("Model" +"                    "+ "Payments" +"                  "+ "Payment");
		for (int i=0;i<counts.size();i++)
			System.out.println(carModels.get(i) +"     "+ payments.get(i) +"      "+ (double)(payments.get(i)/counts.get(i)));
	}

	public static ArrayList<User> deepCopyUser() throws CloneNotSupportedException {
		ArrayList<User> deepUser = new ArrayList<User>();
		for (User list: deepUser) {
			deepUser.add(list.clone());
			}
		return deepUser;
}

	public static ArrayList<User> shallowCopyUser() {
		ArrayList <User> shallowUser = new ArrayList<User> ();
		for (User list: shallowUser) {
			shallowUser.add(list);
		}
		return shallowUser;
}

	public static ArrayList<User> sortUsers() {
		ArrayList<User> userSorted = new ArrayList<User>();
			Collections.sort(userSorted);
		return userSorted;
	}
}


