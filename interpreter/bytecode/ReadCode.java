package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;
import java.util.Scanner;

public class ReadCode extends ByteCode {
    private ArrayList<String> arguments;

    public void init(ArrayList<String> args) {
        arguments = args;
    }

    public void execute(VirtualMachine vm) {
        int input;
        try (Scanner in = new Scanner(System.in)) {
            System.out.print("Please enter an integer number: ");
            while (!in.hasNextInt()) {
                System.out.print("Please enter an integer number: ");
                in.next();
            }
            input = in.nextInt();
        }
        vm.pushAllStack(input);
    }

    public ArrayList<String> getArguments() {
        return arguments;
    }

    public String toString() {
        return "READ";
    }


}