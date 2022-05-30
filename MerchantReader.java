import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class MerchantReader {

    public ArrayList<String> groupBy(String content){
        ArrayList<Merchant> merchantList = new ArrayList<>();
//            8,11,13,16
        content.lines().filter( line -> !line.split(",")[0].equals("Merchant")).forEach( line -> {
            String[] splitArray = line.split(",");
            merchantList.add(new Merchant(splitArray[8],splitArray[11], splitArray[17], splitArray[13]));
        });

//            for( Merchant m: merchantList){
//                System.out.println(m.actionDate);
//            }

//            Scanner in = new Scanner(System.in);
//            while(in.hasNext()){
//                String grouping = in.next();
//
//            }
        String grouping = "AccountCode";

        Set<String> uniqueAccountCodes = merchantList.stream().map(merchant -> merchant.accountCode).collect(Collectors.toSet());
        ArrayList<String> outputString = new ArrayList<>();
        for( String uniqueAC: uniqueAccountCodes){
//                TODO: Assuming 8 for decimal places
            double amount = merchantList.stream().filter(merchant ->
                            merchant.accountCode.equals(uniqueAC))
                    .map(merchant -> Double.parseDouble(merchant.amount) / (10^8))
                    .reduce(0.0, (a,b) -> a + b);
            outputString.add(uniqueAC + " " + amount);
        }
        return outputString;
    }

    public static void main(String[] args) {
        try {
            Path csvFilePath = Path.of("simple-data.csv");
            String content = Files.readString(csvFilePath);
            MerchantReader one = new MerchantReader();
            System.out.println(one.groupBy(content));

        } catch (Exception e){
            e.printStackTrace();
        }
    }

}

class Merchant {

    public String accountCode;
    public String accountType;
    public String actionDate;
    public String amount;

    public Merchant(String accountCode, String accountType, String actionDate, String amount) {
        this.accountCode = accountCode;
        this.accountType = accountType;
        this.actionDate = actionDate;
        this.amount = amount;
    }
}