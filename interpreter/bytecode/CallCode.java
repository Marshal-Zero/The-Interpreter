package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class CallCode extends ByteCode {
    private ArrayList<String> arguments;
    private int targetPointer;


    public void init(ArrayList<String> args) {

        arguments = args;
    }

    public ArrayList<String> getArguments() {

        return arguments;
    }

    public void execute(VirtualMachine vm) {
        vm.updateAddress(vm.getPC());
        vm.setPC(targetPointer - 1);
    }

    public void setTargetPointer(int addr) {
        targetPointer = addr;
    }

    public String toString() {
        StringBuilder returnStr = new StringBuilder("CALL" + " " + this.getArguments().get(0) + "    ");
        var first = arguments.get(0);
        var i1 = first.indexOf("<<");
        var i = 0;
        while (i < i1) {
            returnStr.append(first.charAt(i++));
        }
        if (i1 == -1) returnStr.append(arguments.get(0));

        return returnStr.toString();
    }
}