package interpreter;


import java.util.Stack;

import interpreter.bytecode.*;


public class VirtualMachine {

    private RunTimeStack runStack;
    private Stack<Integer> returnAddrs;
    private Program program;
    private int pc;
    private Boolean isRunning;
    private boolean dumping;

    protected VirtualMachine(Program program) {
        this.program = program;
    }

    public void off() {
        isRunning = false;
    }

    void executeProgram() {

        pc = 0;
        int first = 0;
        runStack = new RunTimeStack();
        returnAddrs = new Stack<>();
        isRunning = true;
        try {

            while (isRunning) {
                ByteCode code = program.getCode(pc);
                if (code instanceof StoreCode && dumping && ((StoreCode) code).getArguments().size() == 2)
                    first = runStack.peek();
                code.execute(this);

                if (dumping) {
                    StringBuilder toString = new StringBuilder(code.toString());


                    if (code instanceof ReturnCode && ((ReturnCode) code).getArguments().size() == 1) {
                        if (((StoreCode) code).getArguments().size() == 2)
                            System.out.println(code instanceof StoreCode ? toString.toString() + first : code.toString() + runStack.peek());
                        else if (code instanceof ReturnCode) System.out.println(code.toString() + runStack.peek());
                        else if (code instanceof WriteCode) System.out.println(toString);
                        else if (code instanceof CallCode) {
                            var argsArr = getArgs();
                            toString.append("(");
                            var i = 0;
                            while (i < argsArr.length) {
                                toString.append(argsArr[i]);
                                toString.append(",");
                                i++;
                            }
                            if (toString.charAt(toString.length() - 1) == ',')
                                toString = new StringBuilder(toString.substring(0, toString.length() - 1));
                            toString.append(")");
                            System.out.println(toString);
                        }
                    } else if (code instanceof StoreCode || code instanceof WriteCode || code instanceof CallCode || code instanceof DumpCode) {
                        if (((StoreCode) code).getArguments().size() == 2) {
                            if (code instanceof StoreCode) System.out.println(toString.toString() + first);
                            else if (code instanceof ReturnCode) System.out.println(code.toString() + runStack.peek());
                            else if (code instanceof WriteCode) {
                                System.out.println(toString);
                                var argsArr = getArgs();
                                toString.append("(");
                                for (int argsArr1 : argsArr) {
                                    toString.append(argsArr1).append(",");
                                }

                                if (toString.charAt(toString.length() - 1) == ',')
                                    toString = new StringBuilder(toString.substring(0, toString.length() - 1));
                                toString.append(")");
                                System.out.println(toString);
                            }
                        } else if (code instanceof ReturnCode) {
                            System.out.println(code.toString() + runStack.peek());
                        } else if (code instanceof WriteCode) {
                            System.out.println(toString);
                        } else if (code instanceof CallCode) {
                            var argsArr = getArgs();
                            toString.append("(");
                            var i = 0;
                            while (argsArr.length > i) toString.append(argsArr[i++]).append(",");
                            if (toString.charAt(toString.length() - 1) == ',')
                                toString = new StringBuilder(toString.substring(0, toString.length() - 1));
                            toString.append(")");
                            System.out.println(toString);
                        }
                    } else System.out.println(toString);
                    runStack.dump();
                }
                pc++;
            }
        } catch (Exception x) {
            System.out.println("Exception! @ vm.executeProgram");
        }
    }


    public void dOff() {          //turns dumping off
        dumping = false;
    }

    public void dOn() {           //turns dumping on
        dumping = true;
    }

    public void setPC(int setPc) {   //setter for PC
        pc = setPc;
    }

    public int getPC() {                  //getter for PC
        return pc;
    }

    public int popRunStack() {
        return runStack.pop();
    }

    public void helperPop(int pops) {
        runStack.helperPop(pops);
    }

    public int popAddress() {
        return returnAddrs.pop();
    }

    public void updateAddress(int updated) {
        returnAddrs.push(updated);
    }

    public void pushAllStack(int newStack) {
        runStack.push(newStack);
    }

    public int peekRunStack() {
        return runStack.peek();
    }

    public void newFrameAt(int offset_val) {
        runStack.newFrameAt(offset_val);
    }

    public void popFrame() {
        runStack.popFrame();
    }

    public int Store(int offset_val) {
        return runStack.store(offset_val);
    }

    public int Load(int offset) {
        return runStack.load(offset);
    }

    public int[] getArgs() {
        return runStack.helperCall();
    }


}