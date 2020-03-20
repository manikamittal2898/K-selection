
import java.util.*;
import java.io.*;
class randomized
{
    static ArrayList<Integer> counter = new ArrayList<Integer>();
    static int c=0;
    public static void main(String args[]) throws IOException {
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
                
            }
           
            op[i] =  krank(arr, k[i]);
         }
        for(i=0;i<op.length;i++){
            System.out.println(op[i]);
        }
        for(i=0;i<counter.size();i++){
            System.out.println("Number of comparisons are "+counter.get(i));
        }

}

public static int krank(int arr[], int k)
{   
    ArrayList<Integer> high = new ArrayList<Integer>(); 
    ArrayList<Integer> low = new ArrayList<Integer>();
    Random r= new Random();
    int index= r.nextInt(arr.length);
    int pivot= arr[index];
    int m,i;
   
    for(i=0;i<arr.length;i++)
    {   c++;
        if(arr[i]<pivot)
        low.add(arr[i]);
        else if(arr[i]>pivot)
        high.add(arr[i]);
        c++;
    }  
    int repeats=arr.length-low.size()-high.size()-1;
    if(repeats!=0){
    for(i=1;i<=repeats;i++)
    low.add(pivot);
    c++;
    }
     if(low.size()>=k)
            {    c++;
                int arr2[]= new int[low.size()];
                for(m=0;m<low.size();m++){
                    arr2[m]=low.get(m);

                }
                return krank(arr2,k);
    }
    else if (low.size()<(k-1)){
        c++;
        int arr2[]= new int[high.size()];
                for(m=0;m<high.size();m++){
                    arr2[m]=high.get(m);
                }
                   return krank(arr2,k-1-low.size());

    }
    counter.add(c);
    c=0;
    return pivot;
}
}