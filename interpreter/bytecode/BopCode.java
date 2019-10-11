package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;
import java.util.List;

public class BopCode extends ByteCode {
    private ArrayList<String> arguments;

    public void init(ArrayList<String> args) {
        arguments = args;
    }

    public void execute(VirtualMachine vm) {
        String operation;
        operation = arguments.get(0);
        var operandOne = 0;
        var operandTwo = 0;
        if (List.of("+", "-", "/", "*", "==", "!=", "<=", ">", ">=", "<", "|", "&").contains(operation)) {
    operandTwo = vm.popRunStack();
    operandOne = vm.popRunStack();
}

        if ("+".equals(operation)) vm.pushAllStack(operandOne + operandTwo);
        else if ("-".equals(operation)) vm.pushAllStack(operandOne - operandTwo);
        else if ("/".equals(operation)) vm.pushAllStack(operandOne / operandTwo);
        else if ("*".equals(operation)) vm.pushAllStack(operandOne * operandTwo);
        else if ("!=".equals(operation)) {
            int is_not_equal = operandOne != operandTwo ? 1 : 0;
            vm.pushAllStack(is_not_equal);
        } else {
            if ("==".equals(operation)) {
                var equal = operandOne == operandTwo ? 1 : 0;
                vm.pushAllStack(equal);
            } else {
                if ("<=".equals(operation)) {
                    var lessOrEqual = operandOne <= operandTwo ? 1 : 0;
                    vm.pushAllStack(lessOrEqual);
                } else {
                    if (">=".equals(operation)) {
                        int greaterOrEqual = operandOne >= operandTwo ? 1 : 0;
                        vm.pushAllStack(greaterOrEqual);
                    } else {
                        if ("<".equals(operation)) {
                            int is_less_than = operandOne < operandTwo ? 1 : 0;
                            vm.pushAllStack(is_less_than);
                        } else {
                            if (">".equals(operation)) {
                                int greater = operandOne > operandTwo ? 1 : 0;
                                vm.pushAllStack(greater);
                            } else {
                                if ("&".equals(operation)) {
                                    int inclusive;
                                    if (operandOne == 1) {
                                        inclusive = operandTwo == 1 ? 1 : 0;
                                    } else {
                                        inclusive = 0;
                                    }
                                    vm.pushAllStack(inclusive);
                                } else {
                                    if ("|".equals(operation)) {
                                        int exclusive;
                                        exclusive = 0;
                                        vm.pushAllStack(exclusive);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public ArrayList<String> getArguments() {
        return arguments;
    }

    public String toString() {
        return String.format("BOP %s", arguments.get(0));
    }


}