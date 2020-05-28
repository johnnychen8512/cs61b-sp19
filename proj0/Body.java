public class Body{
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public static final double g = 6.67e-11;

    public Body(double xP, double yP, double xV, double yV, double m, String img ){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Body(Body b){
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    public double calcDistance(Body b){
        double dx = this.xxPos - b.xxPos;
        double dy = this.yyPos - b.yyPos;
        double r = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
        return r;
    }

    public double calcForceExertedBy(Body b){
        double r = this.calcDistance(b);
        return this.mass * b.mass * Body.g / Math.pow(r,2); 
    }

    public double calcForceExertedByX(Body b){
        double r = this.calcDistance(b);
        double f = this.calcForceExertedBy(b);
        double dx = this.xxPos - b.xxPos;
        return f * dx /r;
    }

    public double calcForceExertedByY(Body b){
        double r = this.calcDistance(b);
        double f = this.calcForceExertedBy(b);
        double dy = b.yyPos - this.yyPos;
        return f * dy /r;
    }

    public double calcNetForceExertedByX(Body[] bodyarray){
        double NetforceX = 0;
        for ( Body b : bodyarray){
            if(!this.equals(b)){
                NetforceX += this.calcForceExertedByX(b);
            }
        }
        return NetforceX;
    }

    public double calcNetForceExertedByY(Body[] bodyarray){
        double NetforceY = 0;
        for ( Body b : bodyarray){
            if(!this.equals(b)){
                NetforceY += this.calcForceExertedByY(b);
            }
        }
        return NetforceY;
    }

    public void update(double s, double xf, double yf){
        double m = this.mass;
        this.xxVel += xf/m * s;
        this.yyVel += yf/m * s;
        this.xxPos += this.xxVel * s;
        this.yyPos += this.yyVel * s;
    }

    public void draw() {
		StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
	}
    
}