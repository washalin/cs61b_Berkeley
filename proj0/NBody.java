public class NBody {
    /**
     * read radius of the universe in the file
     */
    public static double readRadius(String file){
        In in=new In(file);
        // the numbers of plantes
        int N=in.readInt();
        // the radius of plantes
        double R=in.readDouble();
        return R;
    }
   
   /**
    * read Bodys data in file ,initial Body based on data
    * @param file filename
    * @return initialization Bodys 
    */
    public static Body[] readBodies(String file){
        // read data in file
        In in = new In(file);
        int N=in.readInt();
        double R=in.readDouble();
        // initial Body
        Body[] b=new Body[N];
        for(int i=0;i<N;i++){
            b[i]=new Body(in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),"./images/" + in.readString());
        }
        return b;
    }
    public static void main(String[] args) {

        if(args.length<3){
            System.out.println("The number of parameters must more than three ");
            System.exit(1);
        }
        double T = Double.parseDouble(args[0]);
        double dt= Double.parseDouble(args[1]);


        // String filename = "./data/planets";
        String filename = args[2];
        
        double radius = readRadius(filename);
        Body[] bodies = readBodies(filename);

        String imageToDraw = "./images/starfield.jpg";

        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-radius,radius);
        StdDraw.clear();
        double time=0;
        double xForces[] = new double[bodies.length];
        double yForces[] = new double[bodies.length];
        while(time<T){
            
            for(int i=0;i<bodies.length;i++){
                xForces[i]=bodies[i].calcNetForceExertedByX(bodies);
                yForces[i]=bodies[i].calcNetForceExertedByY(bodies);
                //bodies[i].update(dt,1000,10000);
            }
            // for(int i=0;i<bodies.length;i++){
            // }
            StdDraw.picture(0, 0, imageToDraw);
            for(int i=0;i<bodies.length;i++){
                System.out.println(bodies[i].xxPos);
                bodies[i].draw();
                
                bodies[i].update(dt,xForces[i],yForces[i]);
            }
            StdDraw.show();
            StdDraw.pause(0);
            System.out.println("this xxpo");
            time+=dt;
        }
        StdOut.printf("%d\n",bodies.length);
        StdOut.printf("%.2e\n",radius);
        for(int i=0;i<bodies.length;i++){
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                            bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
                            bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);
        }
    }    

}
