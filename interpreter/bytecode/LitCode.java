package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class LitCode extends ByteCode {
    private ArrayList<String> arguments;

    public void init(ArrayList<String> args) {
        arguments = args;
    }

    public void execute(VirtualMachine vm) {
        vm.pushAllStack(Integer.parseInt(arguments.get(0)));
    }

    public ArrayList<String> getArguments() {
        return arguments;
    }

    public String toString() {
        var reStr = String.format("LIT %s", arguments.get(0));
        if (arguments.size() == 2)
            reStr = reStr + String.format(" %s   int %s", arguments.get(1), arguments.get(1));
        return reStr;
    }

}