import java.io.*;
public class heirarchicalmodels {
	public static void main(String args[]) throws FileNotFoundException{
		int i,flag=0;
		double a1_val=-51.0,a2_val=39.0,a3_val=65.0,l1_val=4.0,l2_val=3.0,l3_val=2.5;
		double[][] base = new double[8][3];
		double[][] first = new double[8][3];
		double[][] second = new double[8][3];
		double[][] third = new double[8][3];
		double[] midpoint=new double[3];
		if(args.length != 0){
			for(i=0;i<args.length;i++) {
				if(args[i].equals("-t")){
					a1_val = Double.parseDouble(args[i+1]);
				}
				if(args[i].equals("-u")){
					a2_val = Double.parseDouble(args[i+1]);
				}
				if(args[i].equals("-v")){
					a3_val = Double.parseDouble(args[i+1]);
				}
				if(args[i].equals("-l")){
					l1_val = Double.parseDouble(args[i+1]);
				}
				if(args[i].equals("-m")){
					l2_val = Double.parseDouble(args[i+1]);
				}
				if(args[i].equals("-n")){
					l3_val = Double.parseDouble(args[i+1]);
				}
			}
		}
		base = compute_matrix(base, 2.0, 2.0, 1.0, -2.0, -2.0, 0.0);
		first = compute_matrix(first, 0.5, 0.5, l1_val, -0.5, -0.5, 0.0);
		second = compute_matrix(second, 0.5, 0.5, l2_val, -0.5, -0.5, 0.0);
		third = compute_matrix(third, 0.5, 0.5, l3_val, -0.5, -0.5, 0.0);
		
		first = rotation(a1_val, first, 1);
		midpoint=center(base);
		first = translation(midpoint[0], midpoint[1], midpoint[2], first);
		
		second = rotation(a2_val, second, 0);
		second = rotation(a1_val, second, 1);
		midpoint=center(first);
		second = translation(midpoint[0], midpoint[1], midpoint[2], second);
		
		third = rotation(a2_val+a3_val, third, 0);
		third = rotation(a1_val, third, 1);
		midpoint=center(second);
		third = translation(midpoint[0], midpoint[1], midpoint[2], third);
		
		midpoint=center(third);
		
		System.out.println("#Inventor V2.0 ascii");
		System.out.println();
		System.out.println("Separator {");
		System.out.println("  Coordinate3 {");
		System.out.println("    point [");
		System.out.println("	"+base[0][0]+" "+base[0][1]+" "+base[0][2]+",");
		System.out.println("	"+base[1][0]+" "+base[1][1]+" "+base[1][2]+",");
		System.out.println("	"+base[2][0]+" "+base[2][1]+" "+base[2][2]+",");
		System.out.println("	"+base[3][0]+" "+base[3][1]+" "+base[3][2]+",");
		System.out.println("	"+base[4][0]+" "+base[4][1]+" "+base[4][2]+",");
		System.out.println("	"+base[5][0]+" "+base[5][1]+" "+base[5][2]+",");
		System.out.println("	"+base[6][0]+" "+base[6][1]+" "+base[6][2]+",");
		System.out.println("	"+base[7][0]+" "+base[7][1]+" "+base[7][2]);
		System.out.println("    ]");
		System.out.println("  }");
		System.out.println("  IndexedLineSet {");
		System.out.println("    coordIndex [");
		System.out.println("	 0, 1, 2, 0, -1,");
		System.out.println("	 0, 2, 3, 0, -1,");
		System.out.println("	 7, 6, 5, 7, -1,");
		System.out.println("	 7, 5, 4, 7, -1,");
		System.out.println("	 0, 3, 7, 0, -1,");
		System.out.println("	 0, 7, 4, 0, -1,");
		System.out.println("	 1, 5, 6, 1, -1,");
		System.out.println("	 1, 6, 2, 1, -1,");
		System.out.println("	 0, 4, 5, 0, -1,");
		System.out.println("	 0, 5, 1, 0, -1,");
		System.out.println("	 3, 2, 6, 3, -1,");
		System.out.println("	 3, 6, 7, 3, -1");
		System.out.println("    ]");
		System.out.println("  }");
		System.out.println("}");
		System.out.println("");
		System.out.println("Separator {");
		System.out.println("  Coordinate3 {");
		System.out.println("    point [");
		System.out.println("	"+first[0][0]+" "+first[0][1]+" "+first[0][2]+",");
		System.out.println("	"+first[1][0]+" "+first[1][1]+" "+first[1][2]+",");
		System.out.println("	"+first[2][0]+" "+first[2][1]+" "+first[2][2]+",");
		System.out.println("	"+first[3][0]+" "+first[3][1]+" "+first[3][2]+",");
		System.out.println("	"+first[4][0]+" "+first[4][1]+" "+first[4][2]+",");
		System.out.println("	"+first[5][0]+" "+first[5][1]+" "+first[5][2]+",");
		System.out.println("	"+first[6][0]+" "+first[6][1]+" "+first[6][2]+",");
		System.out.println("	"+first[7][0]+" "+first[7][1]+" "+first[7][2]);
		System.out.println("    ]");
		System.out.println("  }");
		System.out.println("  IndexedLineSet {");
		System.out.println("    coordIndex [");
		System.out.println("	 0, 1, 2, 0, -1,");
		System.out.println("	 0, 2, 3, 0, -1,");
		System.out.println("	 7, 6, 5, 7, -1,");
		System.out.println("	 7, 5, 4, 7, -1,");
		System.out.println("	 0, 3, 7, 0, -1,");
		System.out.println("	 0, 7, 4, 0, -1,");
		System.out.println("	 1, 5, 6, 1, -1,");
		System.out.println("	 1, 6, 2, 1, -1,");
		System.out.println("	 0, 4, 5, 0, -1,");
		System.out.println("	 0, 5, 1, 0, -1,");
		System.out.println("	 3, 2, 6, 3, -1,");
		System.out.println("	 3, 6, 7, 3, -1");
		System.out.println("    ]");
		System.out.println("  }");
		System.out.println("}");
		System.out.println("");
		System.out.println("Separator {");
		System.out.println("  Coordinate3 {");
		System.out.println("    point [");
		System.out.println("	"+second[0][0]+" "+second[0][1]+" "+second[0][2]+",");
		System.out.println("	"+second[1][0]+" "+second[1][1]+" "+second[1][2]+",");
		System.out.println("	"+second[2][0]+" "+second[2][1]+" "+second[2][2]+",");
		System.out.println("	"+second[3][0]+" "+second[3][1]+" "+second[3][2]+",");
		System.out.println("	"+second[4][0]+" "+second[4][1]+" "+second[4][2]+",");
		System.out.println("	"+second[5][0]+" "+second[5][1]+" "+second[5][2]+",");
		System.out.println("	"+second[6][0]+" "+second[6][1]+" "+second[6][2]+",");
		System.out.println("	"+second[7][0]+" "+second[7][1]+" "+second[7][2]);
		System.out.println("    ]");
		System.out.println("  }");
		System.out.println("  IndexedLineSet {");
		System.out.println("    coordIndex [");
		System.out.println("	 0, 1, 2, 0, -1,");
		System.out.println("	 0, 2, 3, 0, -1,");
		System.out.println("	 7, 6, 5, 7, -1,");
		System.out.println("	 7, 5, 4, 7, -1,");
		System.out.println("	 0, 3, 7, 0, -1,");
		System.out.println("	 0, 7, 4, 0, -1,");
		System.out.println("	 1, 5, 6, 1, -1,");
		System.out.println("	 1, 6, 2, 1, -1,");
		System.out.println("	 0, 4, 5, 0, -1,");
		System.out.println("	 0, 5, 1, 0, -1,");
		System.out.println("	 3, 2, 6, 3, -1,");
		System.out.println("	 3, 6, 7, 3, -1");
		System.out.println("    ]");
		System.out.println("  }");
		System.out.println("}");
		System.out.println("");
		System.out.println("Separator {");
		System.out.println("  Coordinate3 {");
		System.out.println("    point [");
		System.out.println("	"+third[0][0]+" "+third[0][1]+" "+third[0][2]+",");
		System.out.println("	"+third[1][0]+" "+third[1][1]+" "+third[1][2]+",");
		System.out.println("	"+third[2][0]+" "+third[2][1]+" "+third[2][2]+",");
		System.out.println("	"+third[3][0]+" "+third[3][1]+" "+third[3][2]+",");
		System.out.println("	"+third[4][0]+" "+third[4][1]+" "+third[4][2]+",");
		System.out.println("	"+third[5][0]+" "+third[5][1]+" "+third[5][2]+",");
		System.out.println("	"+third[6][0]+" "+third[6][1]+" "+third[6][2]+",");
		System.out.println("	"+third[7][0]+" "+third[7][1]+" "+third[7][2]);
		System.out.println("    ]");
		System.out.println("  }");
		System.out.println("  IndexedLineSet {");
		System.out.println("    coordIndex [");
		System.out.println("	 0, 1, 2, 0, -1,");
		System.out.println("	 0, 2, 3, 0, -1,");
		System.out.println("	 7, 6, 5, 7, -1,");
		System.out.println("	 7, 5, 4, 7, -1,");
		System.out.println("	 0, 3, 7, 0, -1,");
		System.out.println("	 0, 7, 4, 0, -1,");
		System.out.println("	 1, 5, 6, 1, -1,");
		System.out.println("	 1, 6, 2, 1, -1,");
		System.out.println("	 0, 4, 5, 0, -1,");
		System.out.println("	 0, 5, 1, 0, -1,");
		System.out.println("	 3, 2, 6, 3, -1,");
		System.out.println("	 3, 6, 7, 3, -1");
		System.out.println("    ]");
		System.out.println("  }");
		System.out.println("}");
		System.out.println("Separator {");
		System.out.println("LightModel {");
		System.out.println("model PHONG");
		System.out.println("}");
		System.out.println("Material {");
		System.out.println("        diffuseColor 1.0 1.0 1.0"); 
		System.out.println("}");
		System.out.println("Sphere {");
		System.out.println("        radius  0.20"); 
		System.out.println("}");
		System.out.println("}");
		System.out.println("Separator {");
		System.out.println("LightModel {");
		System.out.println("model PHONG");
		System.out.println("}");
		System.out.println("Material {");
		System.out.println("        diffuseColor 1.0 1.0 1.0"); 
		System.out.println("}");
		System.out.println("Transform {");
		System.out.println("	translation "+midpoint[0]+" "+midpoint[1]+" "+midpoint[2]);
		System.out.println("}");
		System.out.println("Sphere {");
		System.out.println("        radius  0.20"); 
		System.out.println("}");
		System.out.println("}");		
	}
	
	public static double[][] rotation(double theta, double matrix[][], int flag){
		double[][] yrot = {{Math.cos(Math.toRadians(theta)),0.0,Math.sin(Math.toRadians(theta)),0.0},
							{0.0,1.0,0.0,0.0},
							{-Math.sin(Math.toRadians(theta)),0.0,Math.cos(Math.toRadians(theta)),0.0},
							{0.0,0.0,0.0,1.0}};
		double [][] zrot = {{Math.cos(Math.toRadians(theta)),-Math.sin(Math.toRadians(theta)),0.0,0.0},
				{Math.sin(Math.toRadians(theta)),Math.cos(Math.toRadians(theta)),0.0,0.0},
				{0.0,0.0,1.0,0.0},
				{0.0,0.0,0.0,1.0}};
		double[][] vertex = new double[4][1];
		vertex[3][0]=1.0;
		for(int i=0;i<8;i++){
			for(int j=0;j<3;j++){
				vertex[j][0]=matrix[i][j];
			}
			if(flag==0){
				vertex=multiply(yrot,vertex);
			}
			else if(flag==1){
				vertex=multiply(zrot,vertex);
			}
			for(int j=0;j<3;j++){
				matrix[i][j]=vertex[j][0];
			}
		}					
		return matrix;
	}
	
	public static double[][] translation(double x,double y,double z, double matrix[][]){
		double [][] translate = {{1.0,0.0,0.0,x},
				{0,1.0,0.0,y},
				{0.0,0.0,1.0,z},
				{0.0,0.0,0.0,1.0}};
		double[][] vertex = new double[4][1];
		vertex[3][0]=1.0;
		for(int i=0;i<8;i++){
			for(int j=0;j<3;j++){
				vertex[j][0]=matrix[i][j];
			}
			vertex=multiply(translate,vertex);
			for(int j=0;j<3;j++){
				matrix[i][j]=vertex[j][0];
			}
		}
		return matrix;
	}
	
	public static double[][] compute_matrix(double matrix[][],double x1,double y1,double z1,double x2,double y2,double z2){
		matrix[0]=new double [] {x1,y1,z1};
		matrix[1]=new double [] {-x1,y1,z1};
		matrix[2]=new double [] {-x1,-y1,z1};
		matrix[3]=new double [] {x1,-y1,z1};
		matrix[4]=new double [] {-x2,-y2,z2};
		matrix[5]=new double [] {x2,-y2,z2};
		matrix[6]=new double [] {x2,y2,z2};
		matrix[7]=new double [] {-x2,y2,z2};
		return matrix;
	}
	public static double[][] multiply(double a[][], double b[][]) {
       int rowsInA = a.length;
       int columnsInA = a[0].length;
       int columnsInB = b[0].length;
       double[][] c = new double[rowsInA][columnsInB];
       for (int i = 0; i < rowsInA; i++) {
           for (int j = 0; j < columnsInB; j++) {
               for (int k = 0; k < columnsInA; k++) {
                   c[i][j] = c[i][j] + a[i][k] * b[k][j];
               }
           }
       }
       return c;
	}
	public static double[] center(double matrix[][]){
		double x1=matrix[1][0];
		double y1=matrix[1][1];
		double z1=matrix[1][2];
		double x2=matrix[3][0];
		double y2=matrix[3][1];
		double z2=matrix[3][2];
		
		return new double[] {(x1+x2)/2.0,(y1+y2)/2.0,(z1+z2)/2.0};
	}
	static double decimal(double x){
		double value=0.0;
		value=Math.round(x*1000000.0)/1000000.0;
		return value;
	}
}
