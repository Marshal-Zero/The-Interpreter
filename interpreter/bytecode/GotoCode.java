package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class GotoCode extends ByteCode {
    private ArrayList<String> arguments;
    private int targetPointer;


    public void init(ArrayList<String> args) {
        arguments = args;
    }

    public void execute(VirtualMachine vm) {
        vm.setPC(targetPointer - 1);
    }

    public ArrayList<String> getArguments() {
        return arguments;
    }

    public void setTargetPointer(int addr) {
        targetPointer = addr;
    }

    public String toString() {
        return String.format("GOTO %s", arguments.get(0));
    }
}