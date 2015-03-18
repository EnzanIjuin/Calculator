/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evaluator;

import java.util.*;

public class Evaluator {
    
    private Stack<Operand> opdStack;
    private Stack<Operator> oprStack;
    private HashMap<String, Operator> hashMap;
    
    public Evaluator() {
        opdStack = new Stack<Operand>();
        oprStack = new Stack<Operator>();
        hashMap = new HashMap<String, Operator>();
        hashMap.put("+", new AddOperator());
        hashMap.put("-", new SubOperator());
        hashMap.put("*", new MultOperator());
        hashMap.put("/", new DivOperator());
        hashMap.put("%", new ModOperator());
    }
    
    public double eval(String expr) {
        String tok;
        oprStack.push(new BogusOperator("#"));
        // init stack - necessary with operator priority schema;
        // the priority of any operator in the operator stack other then
        // the usual operators - "+-*/" - should be less than the priority
        // of the usual operators oprStack.push(new Operator("#"));
        String delimiters = "+-*/%#! ";
        StringTokenizer st = new StringTokenizer(expr,delimiters,true);
        // the 3rd arg is true to indicate to use the delimiters as tokens, too
        // but we'll filter out spaces
        
        boolean negFlag = false;
        String prev = " ";

        while (st.hasMoreTokens()) {
            if ( !(tok = st.nextToken()).equals(" ")) { // filter out spaces if (Operand.check(tok)) { // check if tok is an operand opdStack.push(new Operand(tok)); } else { if (!Operator.check(tok)) { System.out.println("*****invalid token******");
                if(tok.equals("-") && !Character.isDigit(prev.charAt(0))) {
                    negFlag = true;
                }
                if(Operand.isInt(tok)){
                    if(negFlag) {
                        opdStack.push(new Operand("-" + tok));
                        negFlag = false;
                    } else opdStack.push(new Operand(tok));
                }else if(!negFlag) {
                    if(!(hashMap.get(tok)).check(tok)){
                        System.out.println("Invalid Token!");
                        System.exit(1);
                    }
                    Operator newOpr = hashMap.get(tok); //POINT 1
                    while ( (oprStack.peek().priority() >= newOpr.priority())) {
                        // note that when we eval the expression 1 - 2 we will
                        // push the 1 then the 2 and then do the subtraction operation
                        // This means that the first number to be popped is the
                        // second operand, not the first operand - see the following code
                        Operator oldOpr = oprStack.pop();
                        Operand op2 = opdStack.pop();
                        Operand op1 = opdStack.pop();
                        opdStack.push(oldOpr.execute(op1,op2));
                    }
                    if(newOpr.priority() != 1) oprStack.push(newOpr);
                    else oprStack.pop();
                }
                prev = tok;
            }
        }
        while(oprStack.peek().priority() != 0){
            Operator oldOpr = oprStack.pop();
            Operand op2 = opdStack.pop();
            Operand op1 = opdStack.pop();
            opdStack.push(oldOpr.execute(op1, op2));
        }
        return opdStack.pop().getValue();
    }
}

class Operand{
    private double value;
    
    Operand(){}
    Operand(String tok){ value = Double.parseDouble(tok); }
    Operand(double n){ value = n; }
    
    public static boolean isInt(String tok){
        char[] cstr = tok.toCharArray();
        for (char aCstr : cstr) if (!Character.isDigit(aCstr) && aCstr != '.') return false;
        
        return true;
    } 
    
    public double getValue(){ return value; }
    
    @Override
    public String toString() {
        return "" + value;
        
    }
}

abstract class Operator{
    private char symbol;
    private int priority;
    
    Operator(char symbol, int priority){
        this.symbol = symbol;
        this.priority = priority;
    }
    
    public String toString() {
        return "" + symbol;
    }
    
    public int priority() {
        return priority;
    }
    
    public abstract boolean check(String tok);
    public abstract Operand execute(Operand opd1, Operand opd2);

}

class AddOperator extends Operator{
    
    AddOperator(){ 
        super('+', 2);
    }
    
    @Override
    public boolean check(String tok){
        return tok.equals("+");
    }
    
    @Override
    public Operand execute(Operand opd1, Operand opd2){
        return new Operand(opd1.getValue() + opd2.getValue());
    }
}

class SubOperator extends Operator{
    
    SubOperator(){
        super('-', 2);
    }
    @Override
    public boolean check(String tok){
        return tok.equals("-");
    }
    
    @Override
    public Operand execute(Operand opd1, Operand opd2){
        return new Operand(opd1.getValue() - opd2.getValue());
    }
    
}

class MultOperator extends Operator{
    
    MultOperator(){
        super('*', 3);
    }
    @Override
    public boolean check(String tok){
        return tok.equals("*");
    }
    
    @Override
    public Operand execute(Operand opd1, Operand opd2){
        return new Operand(opd1.getValue() * opd2.getValue());
    }
    
}

class DivOperator extends Operator{
    DivOperator(){ 
        super('/', 3);
    }
    @Override
    public boolean check(String tok){
        return tok.equals("/");
    }
    
    @Override
    public Operand execute(Operand opd1, Operand opd2){
        return new Operand(opd1.getValue() / opd2.getValue());
    }
    
    
}

class BogusOperator extends Operator{
    
    private int priority;
    
    BogusOperator(String opr){
        super(' ', 0);
        switch (opr) {
            case "#":
                priority = 0;
                break;
            case "!":
                priority = 1;
                break;
        }
    }
    
    @Override
    public boolean check(String tok){
        return tok.equals("!") || tok.equals("#");
    }
    
    @Override
    public Operand execute(Operand opd1, Operand opd2){
        return new Operand();
    }
    
    
    @Override
    public int priority(){
        return priority;
    }
    
}

class ModOperator extends Operator {
    
    ModOperator(){
        super('*', 3);
    }
    
    @Override
    public boolean check(String tok){
        return tok.equals("%");
    }
    
    @Override
    public Operand execute(Operand opd1, Operand opd2){
        return new Operand(opd1.getValue() % opd2.getValue());
    }
    
}
