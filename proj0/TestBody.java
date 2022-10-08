/**
 * Test calcForceExertedBy
 */
public class TestBody {
    public static void main(String[] args){
        checkCalcForce();
    }
/**
 * 检查是否通过测试
 * @param actual 实际计算值
 * @param expected 期望值
 * @param label 测试的方法
 * @param eps 容忍的误差
 */
    public static void checkEquals(double actual,double expected,String label,double eps){
        if(Double.isNaN(actual)||Double.isInfinite(actual)){
            System.out.println("FAIL:"+label
            +":Expected " + expected + "and you gave " + actual);
        }
        else if(Math.abs(expected-actual)<=eps*Math.max(expected, actual)){
            System.out.println("PASS:"+label
            +": Expected " +expected+" and you gave "+actual);
        }
        else{
            System.out.println("FAIL"+label
            +": Expected "+ expected + " and you gave " + actual);
        }
    }
    /**
     * check the Body class make sure calcForceExertedBy works
    */
    public static void checkCalcForce(){

        System.out.println("checking force from b1");
        Body b1 = new Body(1.0,1.0,3.0,4.0,5.0,"jupiter.gif");
        Body b2 = new Body(2.0,1.0,3.0,4.0,4e11,"jupiter.gif");
        checkEquals(b2.calcForceExertedBy(b1), 133.4, "calcForceExertedBy()", 0.01);
        
        System.out.println("checking force from b2");
        checkEquals(b1.calcForceExertedBy(b2), 133.4, "calcForceExertedBy()", 0.01);

    }
}
