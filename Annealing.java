//Simulated Annealing em Java
package simulatedv7;

import java.util.Random;

public class Annealing {
        Random rand = new Random();

        int pa=0, sd=0;
        
        public void annealing( int v[][], int t[][], int b[][], int maq, int tar, int tempo[][], int setup[][]){
            System.out.println("Iniciando o processo do Annealing");
            float T;
            int  i;
            for (T=2500; T>=0.1; T*=0.908){
		for(i=0; i<100; i++){
		//geraAleatorio(v, b, tar);
		mutaSolucao(v, t, b,maq,tar, tempo, setup);
		compara(v, t, b, maq, tar, tempo, setup, T);
		}	
	}
        }
        
        
        public int temp(int v[][], int m, int maq, int tar, int tempo[][], int setup[][]){
            int j=0, t=0;
            
            while(v[m][j]!=0){
                if (j==0 || t==0){
			t+= tempo[m][v[m][j]-1];
                }
		else{
			t+= tempo [m] [ v[m][j] -1 ];
			t+= setup [(v[m][j-1]-1 )+ m*tar] [ v[m][j]-1 ];
                    }
                    j++;
                    if(j==7) break;
                                }
            System.out.println("Tempo total da maquina " +m+" : "+ t);
            return t;
        
        }
        
        
        public int tempMax(int v[][], int maq, int tarefas, int tempo[][], int setup[][]){   
        int t, tmax=0;   
        for(int m=0;m<maq;m++){
            t = temp(v, m, maq, tarefas, tempo, setup);
            if(t>tmax) tmax=t;
        }
        return tmax;
        }
        
        
               
        public void compara(int v[][], int t[][], int b[][], int maq, int tarefas, int tempo[][], int setup[][], float T){
	int i, del, u;
	float x, P;
	
	del= tempMax(t,maq, tarefas, tempo, setup)-tempMax(v, maq, tarefas, tempo, setup);
	
	if(del<=0) {
            for(int m=0; m<maq; m++)
                for(i=0; i<tarefas; i++) 
                     v[m][i]=t[m][i];
            sd++;
            System.out.println("SD");}	
	
	else if (del>0){
		x = rand.nextFloat();
		P = (float) Math.pow(Math.E, -(del / T));
		if (x < P) {
		for(int m=0; m<maq; m++)
                    for(i=0; i<tarefas; i++) 
                        v[m][i]=t[m][i];
			pa++;
                        System.out.println("PA");}
	              } } 

        public void geraAleatorio(int v[][],int b[][], int maq, int tar){
            System.out.println("Iniciando a primeira solução"); 
            int i, u, l=0, c=0;
            boolean ex=false;
            //Iniciar a solução com zeros
            for(int m=0; m<maq; m++)
                for(i=0; i<tar; i++) v[m][i]=0;
                //Alocar as tarefas nas máquinas
                for(i=1; i<=tar; i++){
                        if(l==maq){
                            l=0;
                            c++;
                        }
			v[l][c] = i;
			l++;
	}
           
           for(int m=0; m<maq; m++)
                for(i=0; i<tar; i++) b[m][i]=v[m][i];
           
           System.out.println("\nSolução aleatória:\n");
           for(int li=0; li<maq; li++){
                    System.out.println("");
                    for(int ci=0; ci<tar; ci++){
                        System.out.print(" "+v[li][ci]); } }
            
           }

        public void mutaSolucao(int v[][], int t[][], int b[][],int maq, int tarefas, int tempo[][], int setup[][]){
               int t1=0, t2=0,m1=0, m2=0, a, i, u;
        System.out.println("\nSolução antes de mutação:\n");
           for(int li=0; li<maq; li++){
                    System.out.println("");
                    for(int ci=0; ci<tarefas; ci++){
                        System.out.print(" "+v[li][ci]); } }
               //Checar melhor solução
                if(tempMax(b,maq, tarefas,tempo, setup)>tempMax(v,maq, tarefas, tempo, setup))
                    for(int m=0; m<maq; m++)
                     for(i=0; i<tarefas; i++) 
                      b[m][i]=v[m][i];
           int x, m;
           x=1;
           System.out.println("Metodo de mutação "+ x);
           if(x==1){
           int max=0, cont=0;
           //Codigo para saber qual sera a ultima posicao de tarefa
           for(m=0; m<maq; m++){
               cont=0;
           for(i=0; i<tarefas; i++) {
               if(v[m][i]==0) break;
               cont++;
           }
           max=(cont>max)?cont:max;
           }
            
           t1 = rand.nextInt(max);
           t2 = rand.nextInt(max);
           m1 = rand.nextInt(maq);
           m2 = rand.nextInt(maq);
           
           //System.out.println("Máquina 1: "+ m1 + "Máquina 2: " + m2 + "V1>"+ v[m1][t1]+ "V2>"+v[m2][t2]);
           System.out.println("\nTarefa maxima: "+ max);
           for(m=0; m<maq; m++)
                for(i=0; i<tarefas; i++) 
                    t[m][i]=v[m][i];
            x=2;
           //if(!((t[m1][t1] == 0 && t[m2][t2]!=0 && t2!=max) || (t[m2][t2] == 0 && t[m1][t1] !=0 && t1!=max)) ){
            a=t[m1][t1];
            t[m1][t1]=t[m2][t2];
            t[m2][t2]=a; } 
           
          
           else if(x==2){
           int max=0, cont=0;
           //Codigo para saber qual sera a ultima posicao de tarefa
           for(m=0; m<maq; m++){
               cont=0;
           for(i=0; i<tarefas; i++) {
               if(v[m][i]==0) break;
               cont++;
           }
           max=(cont>max)?cont:max;
           }
           m1 = rand.nextInt(maq);
           m2 = rand.nextInt(maq);
           a = t[m1][max-2];
           t[m1][max-2] = t[m2][max-2];
           t[m2][max-2] = a;
           }
        
        System.out.println("\nSolução apos a mutação:\n");
           for(int li=0; li<maq; li++){
                    System.out.println("");
                    for(int ci=0; ci<tarefas; ci++){
                        System.out.print(" "+t[li][ci]); } }
           }
        
}
        

