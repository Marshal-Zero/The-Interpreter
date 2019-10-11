package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class ReturnCode extends ByteCode {
    private ArrayList<String> arguments;

    public void init(ArrayList<String> args) {
        arguments = args;
    }

    public void execute(VirtualMachine vm) {
        vm.popFrame();
        int reAddr = vm.popAddress();
        vm.setPC(reAddr);
    }


    public ArrayList<String> getArguments() {
        return arguments;
    }

    public String toString() {
        var reStr = new StringBuilder("RETURN");

        if (arguments.size() != 1) return reStr.toString();
        reStr.append(String.format(" %s    exit ", arguments.get(0)));
        var first = arguments.get(0);
        var i1 = first.indexOf("<<");
        var i = 0;
        while (i < i1) {
            reStr.append(first.charAt(i));
            i++;
        }
        if (i1 == -1) reStr.append(arguments.get(0));
        reStr.append(":");


        return reStr.toString();
    }
}