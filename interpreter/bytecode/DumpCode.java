package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;
import java.util.Objects;

public class DumpCode extends ByteCode {
    private ArrayList<String> arguments;

    public void init(ArrayList<String> args) {
        arguments = args;
    }



    public void execute(VirtualMachine vm) {
        if (Objects.equals(arguments.get(0), "ON")) vm.dOn();
        else vm.dOff();
    }

    public ArrayList<String> getArguments() {
        return arguments;
    }

    public String toString() {
        return String.format("DUMP %s", arguments.get(0));
    }
}