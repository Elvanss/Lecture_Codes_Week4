package LAB_4;


import java.util.ArrayList;
import java.util.List;

public class TestCase {
    static final int flatRate = 50;

    public static void testCode() throws CloneNotSupportedException {
        // create the car objects
        Car brand1 = new Car("Porsche Panamera", carType.HATCH, 2021, 353000);
        Car brand2 = new Car("Tesla Model 3", carType.LUX, 2022, 77000);
        Car brand3 = new Car("Toyota Supra", carType.SED, 2019, 125000);
        Car brand4 = new Car("Aston Martin DBX", carType.SUV, 2010, 456999);

        // create the address objects
        Address address1 = new Address(12, "Killian St", "Alexander Heights", "Perth");
        Address address2 = new Address(24, "Willam St", "RingWood", "Melbourne");
        Address address3 = new Address(48, "Brightmore St", "Cremorne", "Sydney");


        // create MyDate objects
        MyDate ExpiryDate1 = new MyDate(2024, 2, 12);
        MyDate ExpiryDate2 = new MyDate(2024, 3, 13);
        MyDate ExpiryDate3 = new MyDate(2024, 4, 14);
        MyDate ExpiryDate4 = new MyDate(2024, 2, 12);

        // create 1 user that was based on lab2 requirement
        User user1 = new User("Justin", 1101, address1, "justin01");
        User user2 = new User("Henry", 1102, address2, "henry01");
        User user3 = new User("Shawn", 1103, address3, "shawn01");


        // create ThirdPartyPolicy & ComprehensivePolicy objects
        ThirdPartyPolicy TPP1 = new ThirdPartyPolicy("Evan", 1, brand1, 6, ExpiryDate1, "Be careful with this car!");
        ThirdPartyPolicy TPP2 = new ThirdPartyPolicy("Andrew", 3, brand3, 4, ExpiryDate3, "Drive or die");
        ThirdPartyPolicy TPP3 = new ThirdPartyPolicy("William", 137, brand2, 1, ExpiryDate2, "Good");
        ComprehensivePolicy CP1 = new ComprehensivePolicy("Peter", 2, brand2, 1, ExpiryDate2, 29, 7);
        ComprehensivePolicy CP2 = new ComprehensivePolicy("David", 4, brand4, 2, ExpiryDate3, 30, 6);
        ComprehensivePolicy CP3 = new ComprehensivePolicy("Zoe", 110, brand3, 5, ExpiryDate1, 26, 12);


/**************----------------------------------(lab1)-----------------------------------------**************/
        //print the list of information, including customer's information and policies
        ArrayList<InsurancePolicy> policies = new ArrayList<>();
        policies.add(TPP1);
        policies.add(TPP2);
        policies.add(TPP3);
        policies.add(CP1);
        policies.add(CP2);
        policies.add(CP3);
        System.out.println("[=====================================]");
        System.out.println("THE DETAIL OF CUSTOMER'S CAR INSURANCE: ");
        System.out.println("[=====================================] \n");
        for (InsurancePolicy member : policies) {
            member.print();
            System.out.println("<----------------------------------->");
        }
        System.out.println();
        System.out.println("<------------------------------------------------------------------------------------------------------------------------------------------------------------------------------->");


        for (InsurancePolicy member : policies) {

            System.out.println(member.toString());
            System.out.println("<------------------------------------------------------------------------------------------------------------------------------------------------------------------------------->");
        }
//calculate the price
        double totalPremium = 0;
        for (InsurancePolicy member : policies) {
            double premium = member.calcPremium(flatRate);
            totalPremium = totalPremium + premium;

        }
        System.out.printf("The Total: $%.0f \n", totalPremium);
        System.out.println("<|>-----------------------------------<|> \n");

/**************----------------------------------(lab1)-(end)----------------------------------------**************/


/**************----------------------------------(lab2)-----------------------------------------**************/
// Add the policies to the user by using addPolicy()
        user1.addPolicy(TPP1);
        user1.addPolicy(CP1);
        user1.addPolicy(TPP2);
        user1.addPolicy(CP2);
        user1.addPolicy(TPP3);
        user1.addPolicy(CP3);


// call the print method for the user
        user1.print();

// print the user by using toString()
        System.out.println(user1.toString());

// Find a policy by using findPolicy() for a given policy ID when the ID is not valid and show an error message: “Policy has not been found”
        InsurancePolicy foundPolicy = user1.findPolicy(5);
        if (foundPolicy == null) {
            System.out.println(">-----------------------------------<");
            System.out.println("Policy has not been found");
            System.out.println(">-----------------------------------< \n");
        }

// Find a policy by using findPolicy() with a given policy ID (valid) and save it in a policy object to be used for following steps.
        foundPolicy = user1.findPolicy(4);
        if (foundPolicy != null) {
            System.out.println("\n>-----------------------------------<");
            System.out.println("List of founded policies: ");
            System.out.println(">-----------------------------------<");
            System.out.println("\n|---Policy Information before rising---|");
            foundPolicy.print();
            System.out.println(" \n|---Policy Information after rising---|");
            foundPolicy.carPriceRise(0.1);
            foundPolicy.print();

            // change the policyHolderName of this policy to “Robert” by using setPolicyHolderName (String newName)
            foundPolicy.setPolicyHolderName("Robert");

            // change the car model of this policy to “Toyota Camry 2018” by using Policy.SetCarModel(String model) which calls Car.setModel(String model)
            foundPolicy.setCarModel("Toyota Camry 2018");
            /* Note: I create one more method call and call it to change the manufacturing year to 2018
            (I create the setCarManuFacturingYear() in InsurancePolicies class, it may be suitable to the name
            of Toyota Camry 2018, it just an optional, sir */
            foundPolicy.setCarManufacturingYear(2018);
        }
        // change the city of the user to “Wollongong” by using User.setCity(String city) which calls Address.setCity(String city)
        user1.setCity("Wollongong");

// ask the customer to enter the information for a new address (by using Scanner) and change the address of the user by using setAddress(address) and print the user after change.

        /* LOCK the scanner for ASSIGNMENT 1:
        Scanner info = new Scanner(System.in);
        System.out.println(" \n>-------------------------------------------<");
        System.out.println("Enter the information for the new address:");
        System.out.println(">-------------------------------------------<");
        System.out.print("Enter street number: ");
        //Integer.parseInt(info.nextLine());
        int streetNumber = info.nextInt();
        info.nextLine();
        System.out.print("Enter street name: ");
        String streetName = info.nextLine();
        System.out.print("Enter suburb: ");
        String suburb = info.nextLine();
        System.out.print("Enter city: ");
        String city = info.nextLine(); */

        System.out.println(">-------------------------------------------<");
        Address newAddress = new Address(22, "Bulli", "CBD", "Wollongong");
        user1.setAddress(newAddress);
        user1.print();

// print the total premium payments for all policies this user owns
        System.out.printf("The total premium payment: $%.0f\n", user1.calcTotalPremium(flatRate));

// add 10% to the price of cars for all the policies this user owns
        user1.carPriceRiseAll(0.1);
        System.out.printf("The total premium payment: $%.0f\n", user1.calcTotalPremium( flatRate));

// ask the customer to enter a carModel then call filterByCarModel method for the user and store the filtered list.
        System.out.println("\n>-----------------------------------<");
        /* Lock the Scanner for ASSIGNMENT1
        System.out.print("Enter the car model: ");
        String model = info.nextLine(); */
        ArrayList<InsurancePolicy> policyList = user1.filterByCarModel("Toyota Supra");
// print the filtered list by calling the static method inside InsurancePolicy
        InsurancePolicy.printPolicies(policyList);
        System.out.println("<|>-----------------------------------<|> \n");

/**************----------------------------------(lab2)-(end)----------------------------------------**************/


/**************----------------------------------(lab3)-----------------------------------------**************/

        //create one insuranceCompany in my main
        InsuranceCompany insuranceCompany = new InsuranceCompany("InsuranceCarCompany", "admin", "dpit121", 30);
        //not successful
        insuranceCompany.validateAdmin("evan", "2022");
        if (!insuranceCompany.validateAdmin("admin", "dpit121")) {
            System.out.println("Login unsuccessfully!");
        } else {
            System.out.println("Login successfully!");
            //addUser(User user) method, at least 1 fail
            insuranceCompany.addUser(user1);
            insuranceCompany.addUser(user2);
            insuranceCompany.addUser(user3);
            insuranceCompany.addUser(user3);

            //addUser(String name, int userID, Address address) method
            insuranceCompany.addUser("Kevin", 1105, new Address(88, "Ravenclaud", "Howart", "Brisbane"), "kevin1");

            //Add several polices to the users by calling addPolicy (int userID, InsurancePolicy policy), at least 1 fail
            insuranceCompany.addPolicy(1101, TPP1);
            insuranceCompany.addPolicy(1101, TPP2);
            insuranceCompany.addPolicy(1101, TPP3);
            insuranceCompany.addPolicy(1102, CP2);
            insuranceCompany.addPolicy(1102, CP1);
            insuranceCompany.addPolicy(1102, CP1);
            insuranceCompany.addPolicy(1102, CP3);
            insuranceCompany.addPolicy(1103, TPP1);
            insuranceCompany.addPolicy(1103, TPP2);
            insuranceCompany.addPolicy(1103, TPP3);
            insuranceCompany.addPolicy(1103, CP1);


            // Add several policies to some users by calling createComprehensivePolicy() and createThirdPartyPolicy()
            insuranceCompany.createThirdPartyPolicy(1101, "Evan", 1, brand1, 6, ExpiryDate1, "Be careful with this car!");
            insuranceCompany.createThirdPartyPolicy(1102, "Andrew", 3, brand3, 4, ExpiryDate3, "Drive or die");
            insuranceCompany.createThirdPartyPolicy(1103, "William", 137, brand2, 1, ExpiryDate2, "Good");
            insuranceCompany.createComprehensivePolicy(1101, "Peter", 2, brand2, 1, ExpiryDate2, 29, 7);
            insuranceCompany.createComprehensivePolicy(1102, "David", 4, brand4, 2, ExpiryDate4, 30, 6);
            insuranceCompany.createComprehensivePolicy(1103, "Zoe", 110, brand3, 5, ExpiryDate1, 26, 12);

            System.out.println(">-------------------------------------------------< \n");

            // Ask customer to enter a userID and print the user and all of his policies by using methods inside insuranceCompany

            /* LOCK the scanner for ASSIGNMENT 1
            System.out.print("Enter the user ID: ");
            int userID = info.nextInt();
            insuranceCompany.printPolicies(userID);
            System.out.println(">-------------------------------------------------< \n");

            //Ask customer to enter a userID and policyID and find a policy with the given policyID for that userID by calling findPolicy(int userID, int policyID) and then print the policy
            System.out.print("Enter a userID: ");
            userID = info.nextInt();
            System.out.print("Enter a policyID: ");
            int policyID = info.nextInt(); */

            foundPolicy = insuranceCompany.findPolicy(1101, 4);
            if (foundPolicy == null) {
                System.out.println("Policy has not been found");
            } else {
                foundPolicy.print();
            }
            System.out.println(">-------------------------------------------------< \n");

            //Print all the user inside the insuranceCompany
            System.out.println("Print the users's Company");
            insuranceCompany.print();
            System.out.println();

            System.out.println("|-------------------------------------------------| \n");
            System.out.println(insuranceCompany.toString());
            System.out.println(">-------------------------------------------------< \n");

            // Raise the price of cars for all users and policies by 10% and print the user again
            insuranceCompany.carPriceRise(0.1);
            System.out.printf("The total payment of users after rise: $%.0f\n", insuranceCompany.calcTotalPayments());
            System.out.println("All the car after price rise: ");
            insuranceCompany.print();

            //	Print the total premium payments for a given userID by calling calcTotalPayments (int userID)

            /* LOCK the Scanner for ASSIGNMENT 1:
            System.out.print("Enter the userID: ");
            userID = info.nextInt(); */
            System.out.printf("The total payments for this user: $%.0f\n", insuranceCompany.calcTotalPayments(1102));
            System.out.println(">-------------------------------------------------< \n");


            // Print the total premium payments for all users in the company
            System.out.printf("The total payments for all user: $%.0f\n", insuranceCompany.calcTotalPayments());
            System.out.println(">-------------------------------------------------< \n");

            // Call allPolicies() for the insuranceCompany and store it in an ArrayList and print the list by using InsurancePolicy.printPolicies()
            ArrayList<InsurancePolicy> PoliciesCompanyList = insuranceCompany.allPolicies();
            InsurancePolicy.printPolicies(PoliciesCompanyList);

            // For a given userID and expiry date call filterByExpiryDate (int userID, MyDate date), store the filtered list and print the list by using InsurancePolicy.printPolicies()
            ArrayList<InsurancePolicy> CompanyDateList = insuranceCompany.filterByExpiryDate(1102, new MyDate(2022, 9, 18));
            InsurancePolicy.printPolicies(CompanyDateList);
            System.out.println(">-------------------------------------------------< \n");

            // For a given car model call insuranceCompany.filterByCarModel (String carModel) and print the filtered list
            ArrayList<InsurancePolicy> CompanyCarList = insuranceCompany.filteredByCarModel("Toyota Supra");
            InsurancePolicy.printPolicies(CompanyCarList);
            System.out.println(">-------------------------------------------------< \n");

            // Ask user to enter a date (year, month, and day) and call filterByExpiryDate (MyDate date) and print the filtered list

            /* LOCK the Scanner for ASSIGNMENT 1:
            System.out.print("Enter year: ");
            int userYear = info.nextInt();
            System.out.print("Enter month: ");
            int userMonth = info.nextInt();
            System.out.print("Enter day: ");
            int userDay = info.nextInt(); */
            MyDate userDate = new MyDate(2024, 2, 12);
            ArrayList<InsurancePolicy> filteredDate = insuranceCompany.filterByExpiryDate(userDate);
            InsurancePolicy.printPolicies(filteredDate);
            System.out.println(">-------------------------------------------------< \n");

            // Find a user with the given ID (valid) and save it in a user object. Ask user to provide a new address and change the current address for the given user
            System.out.println("--------------------------------------");
            User userInfo = insuranceCompany.findUser(1102);
            Address address = new Address(33, "Wollie St", "Hurstville", "Darwin");
            if (userInfo != null) {
                System.out.println("Found User:" + userInfo.getUserID());
                userInfo.setAddress(address);
                System.out.println("New Address:" + userInfo.getAddress());
                System.out.println();
            } else {
                System.out.println("User has not been found");
            }
        }
        /**************----------------------------------(lab3)-(end)----------------------------------------**************/

/**************----------------------------------(ASSIGNMENT1)---------------------------------------**************/

//Populate Distinct City Names
    System.out.print("The Populate Distinct City Names: ");
        ArrayList<String> cityList = insuranceCompany.populateDistinctCityNames();
            for(String cityL : cityList) {
                System.out.print(cityL + " ");
            }
            System.out.println();
            double gongTotal = insuranceCompany.getTotalPaymentForCity("Wollongong");
            double sydneyTotal = insuranceCompany.getTotalPaymentForCity("Sydney");
            double melbourneTotal = insuranceCompany.getTotalPaymentForCity("Melbourne");
        System.out.printf("Total Payment for Wollongong: $%.0f\n", gongTotal);
        System.out.printf("Total Payment for Sydney: $%.0f\n", sydneyTotal);
        System.out.printf("Total Payment for Melbourne: $%.0f\n", melbourneTotal);
        System.out.println();

        System.out.println("The total payment for per city: ");
        ArrayList<Double> PaymentCity = insuranceCompany.getTotalPaymentPerCity(cityList);
        System.out.println(PaymentCity + " ");

        System.out.println("---Report Total Payments for all Cities---");
        insuranceCompany.reportPaymentPerCity(cityList,PaymentCity);

        //Populate Distinct Car Models
        System.out.println("---Populate Distinct Car Models---");
        ArrayList<String> modelDistinct = insuranceCompany.populateDistinctCarModels();
        for(String model : modelDistinct) {
            System.out.print(model+" , ");
        }
        System.out.println();
        System.out.println("|Count for all models|");
        ArrayList<Integer> modelCount = insuranceCompany.getTotalCountPerCarModel(modelDistinct);
        System.out.println(modelCount);
        System.out.println("|The total Payment for all models|");
        ArrayList<Double> modelTotalPayment = insuranceCompany.getTotalPaymentPerCarModel(modelDistinct);
        System.out.println(modelTotalPayment);

        System.out.println("---Report Total and average payments for all models");
        insuranceCompany.reportPaymentsPerCarModel(modelDistinct, modelCount, modelTotalPayment);

        System.out.println("The Total Payment user1's Car Model: ");
        double totalPaymentForAModel = user1.getTotalPaymentForCarModel("Porsche Panamera",flatRate);
        System.out.println("Total payment for user1: " + totalPaymentForAModel);
        System.out.println();

        System.out.println("The number of user1's Car Model: ");
        modelCount = user1.getTotalCountPerCarModel(modelDistinct);
        System.out.println("Count for user1: " + modelCount);
        System.out.println();

        System.out.println("Report Total and average Payments for all car models user 1: ");
        user1.reportPaymentsPerCarModel(modelDistinct, modelCount, modelTotalPayment);

/**************----------------------------------(ASSIGNMENT1)-(end)--------------------------------------**************/


/**************----------------------------------(Lab4)---------------------------------------**************/

//for a User
    /* For a user call deepCopyPolicies() and shallowCopyPolicies() to make
        deep and shallow copies of user’s policies and store these lists in the test code */

       // change the user’s city to “New York” and add a new policy to his polices

       // sort the user’s polices by calling sortPoliciesByDate()

       // print the shallow and deep copy lists as well as user’s policies and see how these three lists are compared


//for a InsuranceCompany
        // For an insuranceCompany call deepCopyUsers() and shallowCopUsers() to make deep and shallow copies of company’s users and store these lists in the test code

        // add a new user to the company
        User user = new User("Hazard",1109,new Address(321,"fasdda","FASDF","fasdfsa"),"hazard01");

        // sort the company’s users by calling sortUsers()
        insuranceCompany.sortUsers();

        // print the shallow and deep copy lists as well as company’s users and see how these three lists are compared

        // make a clone of the insuranceCompany in your test code and test to show it is a deep copy
    }
}
