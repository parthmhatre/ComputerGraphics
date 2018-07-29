import java.io.*;
import java.util.*;
public class superelipsoid {
	public static void main(String args[]) throws FileNotFoundException{
		double s1_val=0.0,s2_val=0.0,Aval=0.0,Bval=0.0,Cval=0.0,u=0.0,v=0.0,i,j;
		double[] valuefinal;
		int u_num=19,v_num=9,m=0,n=0,s=0,count=0,count1=0,count2=0;
		if(args.length != 0){
			for(m=0;m<args.length;m++) {
				if(args[m].equals("-r")){
					s1_val=Double.parseDouble(args[m+1]);
				}
				if(args[m].equals("-t")){
					s2_val=Double.parseDouble(args[m+1]);
				}
				if(args[m].equals("-A")){
					Aval=Double.parseDouble(args[m+1]);
				}
				if(args[m].equals("-B")){
					Bval=Double.parseDouble(args[m+1]);
				}
				if(args[m].equals("-C")){
					Cval=Double.parseDouble(args[m+1]);
				}
				if(args[m].equals("-u")){
					u_num=Integer.parseInt(args[m+1]);
				}
				if(args[m].equals("-v")){
					v_num=Integer.parseInt(args[m+1]);
				}
				if(args[m].equals("-S")){
					s=1;
				}
			}
		}
		
		System.out.println("#Inventor V2.0 ascii");
		System.out.println();
		System.out.println("ShapeHints {");
		System.out.println("	vertexOrdering        COUNTERCLOCKWISE");
		System.out.println("}");
		System.out.println();
		System.out.println("Separator {");
		System.out.println("Coordinate3 {");
		System.out.println("	point [");
		u=(2.0*Math.PI)/(u_num-1);
		v=(Math.PI)/(v_num-1);
		System.out.println("		0.0 0.0 "+(-1.0*Cval*sauxiliary(-Math.PI/2, s1_val)));
		n=0;
		for(j=(-Math.PI/2+v);j<(Math.PI/2);j+=v){
			m=0;
			for(i=(-Math.PI);i<Math.PI;i+=u){
				valuefinal = point(Aval,Bval,Cval,i,j,s1_val,s2_val);
				System.out.println("		"+(-decimal(valuefinal[0]))+" "+(-decimal(valuefinal[1]))+" "+(-decimal(valuefinal[2])));
				m++;
			}
			n++;
		}
		System.out.println("		0.0 0.0 "+(-Cval*sauxiliary(Math.PI/2, s1_val)));
		System.out.println("		]");
		System.out.println("	}");
		System.out.println("NormalBinding {");
		System.out.println("	value        PER_VERTEX_INDEXED");
		System.out.println("}");
		System.out.println();
		if(s==1){
			System.out.println("Normal {");
			System.out.println("	vector [");
			System.out.println("		0.0 0.0 "+(-(1/Cval)*sauxiliary(-Math.PI/2, 2-s1_val)));
			for(j=(-Math.PI/2+v);j<(Math.PI/2);j+=v){
				for(i=(-Math.PI);i<Math.PI;i+=u){
					valuefinal = normal(Aval,Bval,Cval,i,j,s1_val,s2_val);
					System.out.println("		"+(-decimal(valuefinal[0]))+" "+(-decimal(valuefinal[1]))+" "+(-decimal(valuefinal[2])));
				}
			}
			System.out.println("		0.0 0.0 "+(-(1/Cval)*sauxiliary(Math.PI/2, 2-s1_val)));
			System.out.println("	]");
			System.out.println("}");
		}
		
		
		System.out.println("IndexedFaceSet {");
		System.out.println("	coordIndex [");
		count1=n+2;count2=m;
		count=1;
		int[][] index= new int[count1][count2];
		for(m=0;m<count1;m++){
			for(n=0;n<count2;n++){
				if(m==0){
					index[m][n]=0;
				}
				else{
					index[m][n]=count;
					count++;
				}
			}
		}
		int x=0;
		for(m=1;m<count1;m++){
			for(n=0;n<count2-1;n++){
				if(m==1){
					System.out.println("		"+index[m][n]+", "+index[m][n+1]+", "+index[0][0]+", -1,");
					if(n==count2-2){
						System.out.println("		"+index[m][n+1]+", "+index[m][0]+", "+index[m-1][0]+", -1,");
					}
				}				
			}
		}
		for(m=1;m<count1;m++){
			for(n=0;n<count2-1;n++){
				if(m==count1-1){
					System.out.println("		"+index[m][0]+", "+index[m-1][n+1]+", "+index[m-1][n]+", -1,");
					if(n==count2-2){
						System.out.println("		"+index[m][0]+", "+index[m-1][0]+", "+index[m-1][n+1]+", -1,");
					}
				}
			}
		}
		for(m=1;m<count1;m++){
			for(n=0;n<count2-1;n++){
				if(m==1){	
				}
				else {
					if(m!=count1-1){
						System.out.println("		"+index[m][n]+", "+index[m][n+1]+", "+index[m-1][n+1]+", -1,");
						System.out.println("		"+index[m][n]+", "+index[m-1][n+1]+", "+index[m-1][n]+", -1,");
						if(n==count2-2){
							System.out.println("		"+index[m][n+1]+", "+index[m][0]+", "+index[m-1][0]+", -1,");
							System.out.println("		"+index[m][n+1]+", "+index[m-1][0]+", "+index[m-1][n+1]+", -1,");
						}
					}
				}
			}
		}
		System.out.println("		]");
		System.out.println("	}");
		System.out.println("}");
//		for(m=0;m<count1;m++){
//			for(n=0;n<count2;n++){
//				System.out.print(index[m][n]+" ");
//			}
//			System.out.println();
//		}
	}

	public static double cauxiliary(double w, double m){
		double value=0.0;
		value = (SignFunction(Math.cos(w)))*(Math.pow(Math.abs(Math.cos(w)),m));
		return value;
	}
	
	public static double sauxiliary(double w, double m){
		double value=0.0;
		value = (SignFunction(Math.sin(w)))*(Math.pow(Math.abs(Math.sin(w)),m));
		return value;
	}
	
	public static double[] point(double Aval, double Bval, double Cval, double u, double v, double s1_val, double s2_val){
		double xval,yval,zval;
		xval=Aval*cauxiliary(v, s1_val)*cauxiliary(u, s2_val);
		yval=Bval*cauxiliary(v, s1_val)*sauxiliary(u, s2_val);
		zval=Cval*sauxiliary(v, s1_val);
		double[] value = {xval,yval,zval};
		return value;
	}
	
	public static double[] normal(double Aval, double Bval, double Cval, double u, double v, double s1_val, double s2_val){
		double xval,yval,zval;
		xval=(1.0/Aval)*cauxiliary(v, 2.0-s1_val)*cauxiliary(u, 2.0-s2_val);
		yval=(1.0/Bval)*cauxiliary(v, 2.0-s1_val)*sauxiliary(u, 2.0-s2_val);
		zval=(1.0/Cval)*sauxiliary(v, 2.0-s1_val);
		double[] value = {xval,yval,zval};
		return value;
	}
	
	static double decimal(double x){
		double value=0.0;
		value=Math.round(x*1000000.0)/1000000.0;
		return value;
	}
	
	static double SignFunction (double x){
		if(x<0){
			return -1.0;
		}
		else if(x==0.0){
			return 0.0;
		}
		else {
			return 1.0;
		}
	}
}

