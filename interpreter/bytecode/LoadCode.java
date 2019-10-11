package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class LoadCode extends ByteCode {
    private ArrayList<String> arguments;

    public void init(ArrayList<String> args) {
        arguments = args;
    }

    public void execute(VirtualMachine vm) {
        vm.Load(Integer.parseInt(arguments.get(0)));
    }

    public ArrayList<String> getArguments() {
        return arguments;
    }

    public String toString() {
        AtomicReference<String> reStr;
        reStr = new AtomicReference<>(String.format("LOAD %s", arguments.get(0)));
        if (arguments.size() == 2) reStr.set(reStr + String.format(" %s   <load %s>", arguments.get(1), arguments.get(1)));
        return reStr.get();
    }


}