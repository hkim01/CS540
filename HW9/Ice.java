import java.util.Random;

public class Ice {
	private static final int[] year = new int[162]; // 2016 - 1855 + 1
    private static final int[] iceDay = new int[] {118, 151, 121, 96, 110, 117, 132, 
    		104, 125, 118, 125,123, 110, 127, 131, 99, 126, 144, 136, 126, 91, 130, 
    		62, 112, 99, 161, 78, 124, 119, 124,128, 131, 113, 88, 75, 111, 97, 112, 
    		101, 101, 91, 110, 100, 130, 111, 107, 105, 89, 126,108, 97, 94, 83, 106, 
    		98, 101, 108, 99, 88, 115, 102, 116, 115, 82, 110, 81, 96, 125, 104,105, 
    		124, 103, 106, 96, 107, 98, 65, 115, 91, 94, 101, 121, 105, 97, 105, 96, 82, 
    		116, 114,92, 98, 101, 104, 96, 109, 122, 114, 81, 85, 92, 114, 111, 95, 
    		126, 105, 108, 117, 112, 113,120, 65, 98, 91, 108, 113, 110, 105, 97, 105, 
    		107, 88, 115, 123, 118, 99, 93, 96, 54, 111,85, 107, 89, 87, 97, 93, 88, 
    		99, 108, 94, 74, 119, 102, 47, 82, 53, 115, 21, 89, 80, 101,95, 66, 106, 97, 
    		87, 109, 57, 87, 117, 91, 62, 65};
    
    private static void Initialize() {
        for (int i = 1855; i <= 2016; i++) {
            year[i - 1855] = i;
        }
    }
    
    private static double getMean(int[] array) {
    	double mean = 0.0;
    	int sum = 0;
        for (int k : array) {
            sum += k;
        }
    	mean = (double) sum / array.length;
    	return mean;
    }
    
    private static double getDev(int[] array) {
    	double dev = 0.0;
    	double sum = 0.0;
    	double mean = getMean(array);
    	for (int k : array) {
    		sum += Math.pow(k - mean, 2);
    	}
    	dev = Math.sqrt(sum/(array.length - 1));
    	return dev;
    }
    
    private static double getMSE(double arg1, double arg2){
    	double MSE = 0.0;
    	double sum = 0.0;
    	for (int i = 1855; i <= 2016; i++) {
    		sum += Math.pow((arg1 + arg2 * i - iceDay[i - 1855]), 2);
    	}
    	MSE = sum / iceDay.length;
    	return MSE;
    }
    
    private static double getMSE2(double[] array, double arg1, double arg2) {
    	double MSE = 0.0;
    	double sum = 0.0;
    	for (int i = 0; i < array.length; i++) {
    		sum += Math.pow((arg1 + arg2 * array[i] - iceDay[i]), 2);
    	}
    	MSE = sum / iceDay.length;
    	return MSE;
    }
    
    private static double gradientArg1(double arg1, double arg2) {
      	double MSE = 0.0;
    	double sum = 0.0;
    	for (int i = 1855; i <= 2016; i++) {
    		sum += arg1 + arg2 * i - iceDay[i - 1855];
    	}
    	MSE = 2 * sum / iceDay.length;
    	return MSE;
    }
    
    private static double gradientArg2(double arg1, double arg2) {
      	double MSE = 0.0;
    	double sum = 0.0;
    	for (int i = 1855; i <= 2016; i++) {
    		sum += i * (arg1 + arg2 * i - iceDay[i - 1855]);
    	}
    	MSE = 2 * sum / iceDay.length;
    	return MSE;
    }
    
    
    private static double gradientArg1(double[]array, double arg1, double arg2) {
      	double MSE = 0.0;
    	double sum = 0.0;
    	for (int i = 0; i < array.length; i++) {
    		sum += arg1 + arg2 * array[i] - iceDay[i];
    	}
    	MSE = 2 * sum / iceDay.length;
    	return MSE;
    }
    
    private static double gradientArg2(double[]array, double arg1, double arg2) {
      	double MSE = 0.0;
    	double sum = 0.0;
    	for (int i = 0; i < array.length; i++) {
    		sum += array[i] * (arg1 + arg2 * array[i] - iceDay[i]);
    	}
    	MSE = 2 * sum / iceDay.length;
    	return MSE;
    }
    
    private static double getSGD1(double[] array, double arg1, double arg2, int seed) {
    	double sgd1 = 2* (arg1 + arg2 * array[seed] - iceDay[seed]);
    	return sgd1;
    }
    
    private static double getSGD2(double[] array, double arg1, double arg2, int seed) {
    	double sgd2 = 2* (arg1 + arg2 * array[seed] - iceDay[seed]) * array[seed];
    	return sgd2;
    }
    
    private static double getHat1(double hat1) {
    	double hat2 = 0.0;
    	double xmean = getMean(year);
    	double ymean = getMean(iceDay);
    	hat2 = ymean - hat1 * xmean;
    	return hat2;
    }
    
    private static double getHat2() {
    	double hat = 0.0;
    	double xmean = getMean(year);
    	double ymean = getMean(iceDay);
    	double m = 0.0;
    	double n = 0.0;
    	for (int i = 1855; i <= 2016; i++) {
    		m += (i - xmean) * (iceDay[i - 1855] - ymean);
    		n += Math.pow((i - xmean), 2);
    	}
    	hat = m / n;
    	return hat;
    }
    
    private static double[] getStdx() {
    	
    	double[] std = new double[year.length];
    	double mean = getMean(year);
    	double dev = getDev(year);
    	for(int i = 0; i < year.length; i++) {
    		std[i] = (year[i] - mean) / dev;
    	}
    	return std;
    }
    
	 public static void main(String[] args){
		 int flag = Integer.valueOf(args[0]);
		 Initialize();
		 
		 if (flag == 100) {
			 for (int i = 1855; i <= 2016; i++) {
				 System.out.println(i + " " + iceDay[i-1855]);
			 }
		 } 
		 else if(flag == 200) {
			 double mean = getMean(iceDay);
			 System.out.println(iceDay.length);
			 System.out.println(String.format("%.2f",mean));
			 System.out.println(String.format("%.2f",getDev(iceDay)));
		 }
		 else if(flag == 300) {
			 double arg1 = Double.valueOf(args[1]);
			 double arg2 = Double.valueOf(args[2]);
			 System.out.println(String.format("%.2f", getMSE(arg1, arg2)));
		 }
		 else if(flag == 400) {
			 double arg1 = Double.valueOf(args[1]);
			 double arg2 = Double.valueOf(args[2]);
			 System.out.println(String.format("%.2f", gradientArg1(arg1, arg2)));
			 System.out.println(String.format("%.2f", gradientArg2(arg1, arg2)));
		 }
		 else if(flag == 500) {
			 double arg1 = Double.valueOf(args[1]);
			 int arg2 = Integer.valueOf(args[2]);
			 double m = 0.0;
			 double n = 0.0;
			 for(int i = 1; i <= arg2; i++) {
                 double gra1 = gradientArg1(m, n);
                 double gra2 = gradientArg2(m, n);
				 m = m - arg1 * gra1;
				 n = n - arg1 * gra2;
				 System.out.print(i + " ");
				 System.out.println(String.format("%.2f %.2f %.2f", m, n, getMSE(m, n)));
			 }
		 }
		 else if(flag == 600) {
			 double hat2 = getHat2();
			 double hat1 = getHat1(hat2);
			 System.out.print(String.format("%.2f %.2f %.2f", hat1, hat2, getMSE(hat1, hat2)));
		 }
		 else if(flag == 700) {
			 double arg1 = Double.valueOf(args[1]);
			 double hat2 = getHat2();
			 double hat1 = getHat1(hat2);
			 System.out.print(String.format("%.2f", hat1 + hat2 * arg1));
		 }
		 else if (flag == 800) {
			 double arg1 = Double.valueOf(args[1]);
			 int arg2 = Integer.valueOf(args[2]);
			 double[] stdx = getStdx();
			 double m = 0.0;
			 double n = 0.0;
			 
			 for(int i = 1; i <= arg2; i++) {
                 double gra1 = gradientArg1(stdx, m, n);
                 double gra2 = gradientArg2(stdx, m, n);
				 m = m - arg1 * gra1;
				 n = n - arg1 * gra2;
				 System.out.print(i + " ");
				 System.out.println(String.format("%.2f %.2f %.2f", m, n, getMSE2(stdx, m, n)));
			 }
		 }
		 else if(flag == 900) {
			 Random random = new Random();
			 double arg1 = Double.valueOf(args[1]);
			 int arg2 = Integer.valueOf(args[2]);
			 double[] stdx = getStdx();
			 double m = 0.0;
			 double n = 0.0;
			 
			 for(int i = 1; i <= arg2; i++) {
				 int seed = random.nextInt(year.length);
                 double gra1 = getSGD1(stdx, m, n, seed);
                 double gra2 = getSGD2(stdx, m, n, seed);
				 m = m - arg1 * gra1;
				 n = n - arg1 * gra2;
				 System.out.print(i + " ");
				 System.out.println(String.format("%.2f %.2f %.2f", m, n, getMSE2(stdx, m, n)));
			 }
		 }
	 }
}
