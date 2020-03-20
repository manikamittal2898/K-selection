import java.util.*;
import java.io.*;
import java.util.concurrent.TimeUnit;
class version2 {
    static int grp[][];
    static ArrayList<Integer> copy = new ArrayList<Integer>();
    static ArrayList<Integer> counter = new ArrayList<Integer>();
    static int c=0;
    
    public static void main(String args[]) throws IOException {
        long startTime= System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t, i,j;
        t = Integer.parseInt(br.readLine());
        int n[] = new int[t];
        int k[] = new int[t];
        String s[]= new String[t];
        
        int op[] = new int[t];
        for (i = 0; i < t; i++) {
            StringTokenizer s1 = new StringTokenizer(br.readLine());
            
            n[i] = Integer.parseInt(s1.nextToken());
            k[i] = Integer.parseInt(s1.nextToken());
            s[i]= br.readLine();
         }
         for(i=0;i<t;i++){
            int arr[] = new int[n[i]];
            StringTokenizer s2 = new StringTokenizer(s[i]);
            for (j = 0; j < n[i]; ++j) {
                arr[j] = Integer.parseInt(s2.nextToken());
                copy.add(arr[j]);
            }
           
            op[i] =  findk(arr, k[i]);//
         }
        for(i=0;i<op.length;i++){
            System.out.println(op[i]);
        }
        for(i=0;i<counter.size();i++){
            System.out.println("Number of comparisons are "+counter.get(i));
        }
        long endTime=System.currentTimeMillis();
        long timeElapsed=endTime-startTime;
        System.out.println("Execution time in milliseconds: "+timeElapsed);

}

    public static int findk(int arr[], int k) {
        int row = (int) Math.ceil(arr.length / 5.0);
        // System.out.println(row);//
        grp = new int[row][5];
        int m = 0;
        int i, j;
        boolean flag = false;
        for (i = 0; i < row; i++) {
                for (j = 0; j < 5; j++) {
                    grp[i][j] = arr[m];
                    m++;
                    if (m >= arr.length) {
                        flag = true;
                        break;
                    }
                }
                if (flag == true)
                    break;
            }
      
        // System.out.println("The sorted list of elements divided into groups of 5 is: ");
        for (i = 0; i < row-1; i++) {
            sort(grp[i]);
            // for (j = 0; j < 5; j++) {
            //     System.out.print(grp[i][j] + " ");
            // }
            // System.out.println();
        }
       if(arr.length%5==0){
           sort(grp[row-1]);
        //    for (j = 0; j < 5; j++) {
        //     System.out.print(grp[i][j] + " ");
       // }
       // System.out.println();
       }
       else{
        int subarr[]= new int[arr.length%5];
        for (i=0;i<(arr.length%5);i++)
        subarr[i]= grp[row-1][i];
        sort(subarr);
        for (i=0;i<(arr.length%5);i++)
        grp[row-1][i]=  subarr[i];
    //     for (i=0;i<(arr.length%5);i++)
    //    // System.out.println( grp[row-1][i]);
        }
        int medians[] = new int [row];
        int pivot;
        for (i = 0; i < row-1; i++)
            medians[i] = grp[i][2];
            if(arr.length%5==0)
            medians[row-1]=grp[row-1][2];
            else
        medians[row - 1] = grp[row - 1][(int)Math.ceil((arr.length%5) / 2.0)-1];
        //sort(medians, 1);
        // for(i=0;i<medians.length;i++)
        // System.out.println(medians[i]);
        if (medians.length <=5){
            sort(medians);
            pivot= medians[(int)Math.ceil(medians.length/2.0)-1];
        }
           else 
           pivot=  findk(medians, (int)Math.ceil(medians.length/2.0));
           
           ArrayList<Integer> high = new ArrayList<Integer>(); 
           ArrayList<Integer> low = new ArrayList<Integer>();
           
          for(i=0;i<arr.length;i++)
          {   c++;
              if(arr[i]<pivot)
              low.add(arr[i]);
              else if(arr[i]>pivot)
              high.add(arr[i]);
             
          }  
          int repeats=arr.length-low.size()-high.size()-1;
              if(repeats!=0){
                c++;
              for(j=1;j<=repeats;j++)
              low.add(pivot);
              }
      
           if(low.size()>=k)
                  {  c++;
                  // copy.clear();
                      int arr2[]= new int[low.size()];
                      for(m=0;m<low.size();m++){
                         // copy.add(low.get(m));
                          arr2[m]=low.get(m);
                      }
                      return findk(arr2,k);
          }
          else if (low.size()<(k-1)){
            c++;
            //  copy.clear();
              int arr2[]= new int[high.size()];
                      for(m=0;m<high.size();m++){
                          //copy.add(high.get(m));
                          arr2[m]=high.get(m);
                      }
                         return findk(arr2,k-1-low.size());
      
          }
         
          

          counter.add(c);
          c=0;
    return pivot;
}

    public static void sort(int arr[]) {
        
        int n = arr.length;
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (arr[j] > arr[j + 1]) {
                        c++;
                        int temp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = temp;
                }

    }

}
