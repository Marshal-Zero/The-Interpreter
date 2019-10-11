package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class StoreCode extends ByteCode {
    private ArrayList<String> arguments;

    public void init(ArrayList<String> args) {
        arguments = args;
    }

    public void execute(VirtualMachine vm) {
        vm.Store(Integer.parseInt(arguments.get(0)));
    }

    public ArrayList<String> getArguments() {
        return arguments;
    }

    public String toString() {
        String reStr = String.format("STORE %s", arguments.get(0));

        if (arguments.size() != 2) {
            return reStr;
        }
        reStr = reStr + String.format(" %s    %s = ", arguments.get(1), arguments.get(1));
        return reStr;
    }

}