import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Main {
    public static String read(String path)throws Exception {
        String everything;
        List<String> items1,items2;
        BufferedReader br = new BufferedReader(new FileReader(path));
        try {
            StringBuilder sb = new StringBuilder();
            String line ;

            while((line = br.readLine()) != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                if (line.indexOf("%")!=-1){
                    System.out.println("\n"+line.replace('%', '$'));
                    items1 = Arrays.asList(line.split("      "));
                }
            }
            everything = sb.toString();

        } finally {
            br.close();
        }
        return everything;
    }

    public static HashMap readtoHashmap(String path)throws Exception {
        HashMap hm = new HashMap();
        BufferedReader br = new BufferedReader(new FileReader(path));
        try {
            StringBuilder sb = new StringBuilder();
            String line ;
            while((line = br.readLine()) != null) {
                sb.append(line);
                sb.append(System.lineSeparator());

                if (line.indexOf("Correctly Classified Instances")!=-1)
                {
                    List<String> items = Arrays.asList(line.substring(30).split("           "));
                    hm.put("Correctly Classified Instances",items.get(0).trim());
                    hm.put("Correctly Classified Instances Percent",items.get(1).trim());
                }

                if (line.indexOf("Incorrectly Classified Instances")!=-1)
                {
                    List<String> items = Arrays.asList(line.substring(33).split("           "));
                    hm.put("Incorrectly Classified Instances",items.get(0).trim());
                    hm.put("Incorrectly Classified Instances Percent",items.get(1).trim());
                }

                if (line.indexOf("Kappa statistic")!=-1)
                {
                    hm.put("Kappa statistic",Arrays.asList(line.substring(20).trim()));
                }

                if (line.indexOf("Mean absolute error")!=-1)
                {
                    hm.put("Mean absolute error",Arrays.asList(line.substring(22).trim()));
                }

                if (line.indexOf("Root mean squared error")!=-1)
                {
                    hm.put("Root mean squared error",Arrays.asList(line.substring(25).trim()));
                }

                if (line.indexOf("Relative absolute error")!=-1)
                {
                    List<String> items = Arrays.asList(line.substring(30).split("           "));
                    hm.put("Relative absolute error",items.get(0).trim());
                }

                if (line.indexOf("Root relative squared error")!=-1)
                {
                    hm.put("Root relative squared error",Arrays.asList(line.substring(29).trim()));
                }

                if (line.indexOf("Total Number of Instances")!=-1)
                {
                    List<String> items = Arrays.asList(line.substring(30).split("           "));
                    hm.put("Total Number of Instances",items.get(0).trim());
                }

                if (line.indexOf("Weighted Avg")!=-1)
                {
                    List<String> items = Arrays.asList(line.substring(15).split("    "));
                    hm.put("TP Rate",items.get(0).trim());
                    hm.put("FP Rate",items.get(1).trim());
                    hm.put("Precision",items.get(2).trim());
                    hm.put("Recall",items.get(3).trim());
                    hm.put("F-Measure",items.get(4).trim());
                    hm.put("MCC",items.get(5).trim());
                    hm.put("ROC Area",items.get(6).trim());
                    hm.put("PRC Area",items.get(7).trim());
                }


            }

        } finally {
            br.close();
        }

        return hm;
    }
    public static void main(String[] args) throws Exception{
        Map hm1 =readtoHashmap("src/ADA-RT.txt");
        Map hm2 =readtoHashmap("src/RT.txt");

        // Get a set of the entries
        Set set1 = hm1.entrySet();
        Set set2 = hm2.entrySet();

        // Get an iterator
        Iterator i1 = set1.iterator();
        Iterator i2 = set2.iterator();

        // Display elements
        while(i1.hasNext()) {
            Map.Entry me1 = (Map.Entry) i1.next();
            Map.Entry me2 = (Map.Entry) i2.next();
            System.out.print(me1.getKey() + " ADA-RT : ");
            System.out.print(me1.getValue()+"   | ");
            System.out.print(me2.getKey() + " RT: ");
            System.out.println(me2.getValue());
        }


    }
}
