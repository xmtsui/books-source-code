
import java.util.Scanner;  

public class Square {  
      
    public static void main(String[] args){   
        Scanner sc=new Scanner(System.in);  
        int T=sc.nextInt();  
        int[] result=new int[T];  
        for(int i=0;i<T;i++){  
            int M=sc.nextInt();  
            int N=sc.nextInt();  
            int K=sc.nextInt();  
            result[i]=getNum(M,N,K);  
        }  
        printResult(result);  
    }  
  
    private static int getNum(int m, int n, int k) {  
        int result=0;  
        int max=n;  
        int min=m;  
        if(m>n){  
            min=n;  
            max=m;  
        }  
        int start=new Double(Math.sqrt(k)).intValue();  
        if(start>min)  
            start=min;  
        for(int i=start;i>1;i--){  
            int x=i;  
            int y=k/x;  
            if(y>max)  
                break;  
            int z=k%x;  
            int s=getResult(x,y,z,max,min);  
            if(s>result){  
                result=s;  
            }  
        }  
        return result;  
    }  
  
    private static int getResult(int x, int y, int z, int max, int min) {  
        int result=(x*(x-1)*y*(y-1))/4;  
        if(z>1){  
            if(y<max)  
                result=result+(y*z*(z-1))/2;  
            else{  
                result=result+(x*z*(z-1))/2;  
            }  
        }  
        return result;  
    }  
  
    private static void printResult(int[] result) {  
        for(int i=0;i<result.length;i++){  
            System.out.println("Case #"+(i+1)+": "+result[i]);  
        }  
    }  
} 
