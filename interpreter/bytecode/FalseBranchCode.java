package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class FalseBranchCode extends ByteCode {
    private ArrayList<String> arguments;
    private int targetPointer;

    public void init(ArrayList<String> args) {
        arguments = args;
    }

    public void execute(VirtualMachine vm) {
        var i = vm.popRunStack();
        if (i == 0) vm.setPC(targetPointer - 1);
    }

    public ArrayList<String> getArguments() {
        return arguments;
    }


    public void setTargetPointer(int dest) {
        targetPointer = dest;
    }

    public String toString() {
        return ("FALSEBRANCH" + " " + arguments.get(0));
    }


}