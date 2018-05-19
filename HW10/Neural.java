import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class data {
	public double hwGrade;
	public double midGrade;
	public double letterGrade;

	public data(double a, double b, double y) {
		hwGrade = a;
		midGrade = b;
		letterGrade = y;
	}
}

public class Neural {
	private static double[] weights = new double[9];
	static ArrayList<data> train, test, eval;

	private static ArrayList<data> read(String filename) {
		File file = new File(filename);
		ArrayList<data> result = new ArrayList();
		try {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] token = line.split(" ");
				double a = Double.valueOf(token[0].toString());
				double b = Double.valueOf(token[1].toString());
				double y = Double.valueOf(token[2].toString());
				result.add(new data(a, b, y));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("file not found: " + filename);
		}
		return result;
	}

	private static double[] getUnit(double x1, double x2) {
		double[] result = new double[6];
		result[0] = weights[0] + weights[1] * x1 + weights[2] * x2; // uA
		result[1] = Math.max(result[0], 0); // vA
		result[2] = weights[3] + weights[4] * x1 + weights[5] * x2; // uB
		result[3] = Math.max(result[2], 0); // vB
		result[4] = weights[6] + weights[7] * result[1] + weights[8] * result[3]; // uC
		result[5] = (double) 1 / (1 + Math.exp(-result[4])); // vc

		return result;
	}

	private static double[] getError(double vc, double y) {
		double[] result = new double[3];
		double error = Math.pow(vc - y, 2) * 0.5;
		double dvc = vc - y;
		double duc = dvc * vc * (1 - vc);
		result[0] = error;
		result[1] = dvc;
		result[2] = duc;
		return result;
	}

	private static double[] hiddenLayer(double[] lists, double y) {
		double vc = lists[5];
		double dvc = vc - y;
		double duc = dvc * vc * (1 - vc);
		double dva = weights[7] * duc;
		double dvb = weights[8] * duc;
		double dua = 0.0;
		double dub = 0.0;
		if (lists[0] >= 0) {
			dua = 1 * dva;
		} else {
			dua = 0;
		}
		if (lists[2] >= 0) {
			dub = 1 * dvb;
		} else {
			dub = 0;
		}
		return new double[] { dva, dua, dvb, dub };
	}

	private static double[] weights(double[] lists, double x1, double x2, double y) {
		double[] results = new double[9];
		double vc = lists[5];
		double dvc = vc - y;
		double duc = dvc * vc * (1 - vc);
		double[] hidden = hiddenLayer(lists, y);
		double dua = hidden[1];
		double dub = hidden[3];
		results[0] = dua;
		results[1] = x1 * dua;
		results[2] = x2 * dua;
		results[3] = dub;
		results[4] = x1 * dub;
		results[5] = x2 * dub;
		results[6] = duc;
		results[7] = lists[1] * duc;
		results[8] = lists[3] * duc;
		return results;
	}

	private static void updateE(double[] list, double stepsize) {
		for (int i = 0; i < 9; i++) {
			weights[i] = weights[i] - stepsize * list[i];
		}
	}

	private static double setError(ArrayList<data> input) {
		double result = 0.0;
		for (data d : input) {
			double vc = getUnit(d.hwGrade, d.midGrade)[5];
			result = result + Math.pow(vc - d.letterGrade, 2);
		}
		result = 0.5 * result;
		return result;
	}
	
    private static double accuracy(ArrayList<data> input) {
        int i = 0;
        for (data d : input) {
        	double vc = getUnit(d.hwGrade, d.midGrade)[5];
            int x;
			if (vc >= 0.5) {
				x = 1;
			} else {
				x = 0;
			}
            if (x == d.letterGrade) {
                i++;
            }
        }
        double accuracy = (double) i / input.size();
        return accuracy;
    }


	private static void print(double[] list) {
		for (double k : list) {
			System.out.print(String.format("%.5f ", k));
		}
	}

	public static void main(String[] args) {
		int flag = Integer.valueOf(args[0]);
		for (int i = 0; i < 9; i++) {
			weights[i] = Double.valueOf(args[i + 1]);
		}
		if (flag < 600) {
			double x1 = Double.valueOf(args[10]);
			double x2 = Double.valueOf(args[11]);
			if (flag == 100) {
				print(getUnit(x1, x2));
				return;
			}

			double y = Double.valueOf(args[12]);
			double[] units = getUnit(x1, x2);
			if (flag == 200) {
				double vc = units[5];
				print(getError(vc, y));
				return;
			}
			if (flag == 300) {
				print(hiddenLayer(units, y));
				return;
			}
			if (flag == 400) {
				print(weights(units, x1, x2, y));
				return;
			}

			double stepSize = Double.valueOf((args[13]));
			if (flag == 500) {
				double[] weightDev = weights(units, x1, x2, y);
				print(weights);
				double vc = units[5];
				System.out.println(String.format("\n%.5f", Math.pow(vc - y, 2) * 0.5));
				updateE(weightDev, stepSize);
				print(weights);
				vc = getUnit(x1, x2)[5];
				System.out.println(String.format("\n%.5f", Math.pow(vc - y, 2) * 0.5));
				return;
			}
		} else {
			double[] units;
			double y = Double.valueOf(args[10]);
			train = read("hw2_midterm_A_train.txt");
			test = read("hw2_midterm_A_test.txt");
			eval = read("hw2_midterm_A_eval.txt");

			if (flag == 600) {
				for (data d : train) {
					System.out.println(String.format("%.5f %.5f %.5f", d.hwGrade, d.midGrade, d.letterGrade));
					units = getUnit(d.hwGrade, d.midGrade);
					double[] weightDev = weights(units, d.hwGrade, d.midGrade, d.letterGrade);
					updateE(weightDev, y);
					print(weights);
					System.out.println(String.format("\n%.5f", setError(eval)));
				}
				return;
			}
			int T = Integer.valueOf(args[11]);
			if (flag == 700) {
				int i;
				for(i = 0; i < T; i++) {
					for (data d : train) {
						units = getUnit(d.hwGrade, d.midGrade);
						double[] weightDev = weights(units, d.hwGrade, d.midGrade, d.letterGrade);
						updateE(weightDev, y);
					}
					print(weights);
					System.out.println(String.format("\n%.5f", setError(eval)));
				}
				return;
			}
			if (flag == 800) {
				int i;
				double value = 0;
				double prev = Double.POSITIVE_INFINITY;
				for(i = 0; i < T; i++) {
					for (data d : train) {
						units = getUnit(d.hwGrade, d.midGrade);
						double[] weightDev = weights(units, d.hwGrade, d.midGrade, d.letterGrade);
						updateE(weightDev, y);
					}
					value = setError(eval);
					if(value <= prev) {
						prev = value;
					} else {
						break;
					}
				}
				System.out.println(i+1);
				print(weights);
				System.out.println(String.format("\n%.5f", setError(eval)));
				System.out.println(String.format("%.5f", accuracy(test)));
			}
		}
	}
}
