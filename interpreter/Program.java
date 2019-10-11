package interpreter;

import java.util.ArrayList;

import interpreter.bytecode.*;

import java.util.HashMap;
import java.util.stream.IntStream;

public class Program {

    private ArrayList<ByteCode> program;

    public Program() {

        program = new ArrayList<ByteCode>();

    }

    public ByteCode getCode(int pc) {

        return this.program.get(pc);

    }

    protected void fillup(ByteCode ini) {    //this helper function allows us to add fully INITILIAZED bytecodes to the Array List
        program.add(ini);
    }

    public int getSize() {

        return this.program.size();
    }

    /**
     * This function  goes through the program and resolves all addresses.
     * Previously all labels look like LABEL <<num>>>, but here they are converted into
     * correct addresses so the VirtualMachine knows what to set the Program Counter(PC)
     */
    public void resolveAddress() {

        var tags = new HashMap<String, Integer>();

        IntStream.range(0, this.program.size()).forEachOrdered(i -> {
            var bc = program.get(i);
            if (bc instanceof LabelCode) {
                LabelCode bc1 = (LabelCode) bc;
                tags.put(((LabelCode) bc).getArguments().get(0), i);
            }
        });

        int range = program.size();
        IntStream.range(0, range).forEachOrdered(i -> {
            if ((program.get(i) instanceof FalseBranchCode)) {
                int val = tags.get(((FalseBranchCode) program.get(i)).getArguments().get(0));
                ((FalseBranchCode) program.get(i)).setTargetPointer(val);
            } else {
                if (program.get(i) instanceof GotoCode) {
                    int i1 = tags.get(((GotoCode) program.get(i)).getArguments().get(0));
                    ((GotoCode) program.get(i)).setTargetPointer(i1);
                } else if (program.get(i) instanceof CallCode) {
                    int val = tags.get(((CallCode) program.get(i)).getArguments().get(0));
                    ((CallCode) program.get(i)).setTargetPointer(val);
                }
            }
        });


    }


}