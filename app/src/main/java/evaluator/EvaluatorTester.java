package evaluator;

public class EvaluatorTester {

    public static void main(String[] args) {
        String expr = "-20.0 * 5.5 + 20 + -50";
        
        Evaluator anEvaluator = new Evaluator();
        System.out.println("Expression:\t" + expr);
        System.out.println("Solution:\t" + anEvaluator.eval(expr));

    }
}
