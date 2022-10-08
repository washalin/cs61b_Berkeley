/**
 * implement Body functions
 */
public class Body {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    
    public Body(double xP,double yP,double xV,double yV,double m,String img){
        xxPos=xP;
        yyPos=yP;
        xxVel=xV;
        yyVel=yV;
        mass=m;
        imgFileName=img;
    }
    public Body(Body b){
        xxPos=b.xxPos;
        yyPos=b.yyPos;
        xxVel=b.xxVel;
        yyVel=b.yyVel;
        mass=b.mass;
        imgFileName=b.imgFileName;   
    }
    public double calcDistance(Body b){
        return Math.sqrt((b.xxPos-xxPos)*(b.xxPos-xxPos)+(b.yyPos-yyPos)*(b.yyPos-yyPos));
    }
    public double calcForceExertedBy(Body b){
        double G=6.67e-11;
        return G*mass*b.mass/(calcDistance(b)*calcDistance(b));
    }
    public double calcForceExertedByX(Body b){
        double F=calcForceExertedBy(b);
        double r=calcDistance(b);
        double x=b.xxPos-xxPos;
        double Fx=F*x/r;
        return Fx;
    }
    public double calcForceExertedByY(Body b){
        double F=calcForceExertedBy(b);
        double r=calcDistance(b);
        double y=b.yyPos-yyPos;
        double Fy=F*y/r;
        return Fy;
    }
    public double calcNetForceExertedByX(Body[] b){
        if(b==null) return 0;
        double netF=0;
        for(int i=0;i<b.length;i++){
            if(b[i].equals(this))   continue;
            
            netF+=calcForceExertedByX(b[i]);
        }
        return netF;
    }
    public double calcNetForceExertedByY(Body[] b){
        if(b==null) return 0;
        double netF=0;
        for(int i=0;i<b.length;i++){
            if(b[i].equals(this))   continue;
            netF+=calcForceExertedByY(b[i]);
        }
        return netF;
    }
    public void update(double dt,double fx,double fy){
        double ax=fx/mass;
        xxVel=xxVel+dt*ax;
        xxPos=xxPos+dt*xxVel;
        double ay=fy/mass;
        yyVel=yyVel+dt*ay;
        yyPos=yyPos+dt*yyVel;
    }
    public void draw(){
        StdDraw.picture(xxPos, yyPos, imgFileName);
    }

}
