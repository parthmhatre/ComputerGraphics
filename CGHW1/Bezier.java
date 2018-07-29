import java.util.*;
import java.io.*;
public class Bezier {
	public static void main(String args[]) throws FileNotFoundException{
		String filename="cpts_in.txt";
		double t=0.09;
		String r="0.1";
		int i;
		if(args.length != 0){
			for(i=0;i<args.length;i++) {
				if(args[i].equals("-f")){
					filename=args[i+1];	
				}
				if(args[i].equals("-u")){
					t=Double.parseDouble(args[i+1]);
				}
				if(args[i].equals("-r")){
					r=args[i+1];
				}
			}
		}
		String path=System.getProperty("user.dir")+"/"+filename;
		Scanner sc = new Scanner(new File(path));
		String line;
		String[] ElementsInLine;
		i=0;
		while (sc.hasNextLine()) {
			sc.nextLine();
			i++;
		}
		sc.close();
		double[][] matrix=new double[i][3];
		Scanner scanner = new Scanner(new File(path));
		i=0;
		while(scanner.hasNextLine()) {
			line = scanner.nextLine();
			ElementsInLine = line.split(" ");
			for(int l=0;l<3;l++){
				matrix[i][l]=Double.parseDouble(ElementsInLine[l]);
			}
			i++;
		}
		scanner.close();
//		for(int j=0;j<i;j++){
//			for(int k=0;k<3;k++){
//				System.out.print(matrix[j][k]+" ");
//			}
//			System.out.println();
//		}
		int length=i;
		double[] x=new double[length];
		double[] y=new double[length];
		double[] z=new double[length];
		double u = 0.000;
		double NumberOfPoints=0;
		NumberOfPoints=(1/t)+1;
		
		double[] xFinal=new double[(int)NumberOfPoints];
		double[] yFinal=new double[(int)NumberOfPoints];
		double[] zFinal=new double[(int)NumberOfPoints];
		for (i=0;i<length;i++){
			x[i]=matrix[i][0];
			y[i]=matrix[i][1];
			z[i]=matrix[i][2];
		}
		i=0;
		while(u<=1){
			xFinal[i]=Math.round(point(length-1,x,u)*1000.0000)/1000.0000;
			yFinal[i]=Math.round(point(length-1,y,u)*1000.0000)/1000.0000;
			zFinal[i]=Math.round(point(length-1,z,u)*1000.0000)/1000.0000;
			u=u+t;
			u=Math.round(u*1000.0000)/1000.0000;
			i++;
		}
//		for(i=0;i<NumberOfPoints;i++){
//			System.out.print(xFinal[i]+" ");
//			System.out.print(yFinal[i]+" ");
//			System.out.print(zFinal[i]+" ");
//			System.out.println();
//		}
		
		System.out.println("#Inventor V2.0 ascii");
		System.out.println("# control points");
		for(i=0;i<length;i++){
			System.out.print("# "+matrix[i][0]+" "+matrix[i][1]+" "+matrix[i][2]);
			System.out.println();
		}
		System.out.println("Separator {");
		System.out.println("LightModel {");
		System.out.println("model BASE_COLOR");
		System.out.println("}");
		System.out.println("Material {");
		System.out.println("	diffuseColor 1.0 1.0 0.2");
		System.out.println("}");
		System.out.println("Coordinate3 {");
		System.out.println("point [");
		for(i=0;i<(int)NumberOfPoints;i++){
			System.out.print("		"+xFinal[i]+" "+yFinal[i]+" "+zFinal[i]);
			System.out.println();
		}
		System.out.println("	]");
		System.out.println("}");
		System.out.println("IndexedLineSet {");
		System.out.println("coordIndex [");
		for(i=0;i<NumberOfPoints;i++){
			System.out.print(i+", ");
		}
		System.out.println("-1,");
		System.out.println("	]");
		System.out.println("}");
		for(i=0;i<length;i++){
			System.out.println("Separator {");
			System.out.println("LightModel {");
			System.out.println("model PHONG");
			System.out.println("}");
			System.out.println("Material {");
			System.out.println("	diffuseColor 1.0 1.0 1.0");
			System.out.println("}");
			System.out.println("Transform {");
			System.out.println("	translation "+matrix[i][0]+" "+matrix[i][1]+" "+matrix[i][2]);
			System.out.println("}");
			System.out.println("Sphere {");
			System.out.println("	radius  "+r);
			System.out.println("}");
			System.out.println("}");
		}
		System.out.println("}");
	}
	
	static long factorial (int n) {
		long fact=1,i=1;
		while(i<=n){
			fact=fact*i;
			i++;
		}
		return fact;
	}
	
	static double power (double x, int y){
		double pow=1.00;
		int i=1;
		while(i<=y){
			pow=pow*x;
			i++;
		}
		return pow;
	}
	
	static double point(int k, double x[],double u){
		long ki=0;
		int i=0;
		double q=0.0;
		while(i<=k){
			ki=(factorial(k)/(factorial(i)*factorial(k-i)));			
			q=q+(x[i]*ki*power((1-u),(k-i))*power(u,i));
			i++;
		}
		return q;
	}
}
