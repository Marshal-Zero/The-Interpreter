package interpreter;

import java.util.ArrayList;
import java.util.Stack;
import java.util.stream.IntStream;

public class RunTimeStack {

    private ArrayList<Integer> runTimeStack;
    private Stack<Integer> framePointer;

    public RunTimeStack() {
        runTimeStack = new ArrayList<>();
        framePointer = new Stack<>();
        // Add initial Frame Pointer, main is the entry
        // point of our language, so its frame pointer is 0.
        framePointer.add(0);
    }


    public void dump() {

        RunTimeStack rts = new RunTimeStack(); //test don't forget to add framesize
        System.out.println(rts.getClass());

        StringBuilder str = new StringBuilder();
        int i = 0;
        while (framePointer.size() > i) {
            str.append("[");
            if (framePointer.size() - 1 == i) {
                int n = framePointer.peek();
                while ((runTimeStack.size()) > n) {
                    str.append(runTimeStack.get(n++)).append(",");
                }
            } else {
                var difference = framePointer.get(i + 1) - framePointer.get(i);
                var j = framePointer.get(i);
                while (framePointer.get(i) + difference > j) {
                    str.append(runTimeStack.get(j++)).append(",");
                }
            }

            if (str.charAt(str.length() - 1) == ',')
                str = new StringBuilder(str.substring(0, str.length() - 1));
            str.append("] ");
            i++;
        }
        System.out.println(str);
    }

    public int peek() {
        if (runTimeStack.isEmpty()){
            System.out.println("Empty PEEK");
            return 0;
        }
        return runTimeStack.get(runTimeStack.size() - 1);
    }

    public int push(int i) {
        runTimeStack.add(i);
        return i;
    }



    public int pop() {
        var first = 0;

        if (runTimeStack.size() == 0) {
            System.out.println("All popped .pop()");
            System.exit(-1);
        }

        var marker = runTimeStack.size() - framePointer.peek();

        if (marker < 1) return first;
        first = runTimeStack.get(runTimeStack.size() - 1);
        runTimeStack.remove(runTimeStack.size() - 1);
        return first;
    }

    public int store(int offset) {
        if(runTimeStack.isEmpty()){
            System.out.println("Empty STACK .store()");
            System.exit(-2);
        }
        var store = runTimeStack.get(runTimeStack.size() - 1);
        runTimeStack.remove(runTimeStack.size() - 1);
        runTimeStack.set(framePointer.peek() + offset, store);

        return store;
    }

    public int load(int offset) {
        if(runTimeStack.isEmpty()){
            System.out.println("Empty STACK .load()");
            System.exit(-3);
        }

        var load = runTimeStack.get(framePointer.peek()) + offset;
        runTimeStack.add(load);
        return load;
    }



    public void newFrameAt(int offset) {
        framePointer.push(runTimeStack.size() - offset);
    }

    public void popFrame() {
        var pop = runTimeStack.size() - framePointer.peek();
        var reVal = runTimeStack.get(runTimeStack.size() - 1);
        helperPop(pop);
        framePointer.pop();
        runTimeStack.add(reVal);
    }

    public int[] helperCall(){

        if(runTimeStack.isEmpty()){
            System.out.println("Empty STACK (helperCall)");
            System.exit(-4);
        }

        var pointer= framePointer.get(0) -1;
        pointer += framePointer.get(pointer ) == null ? 0 : runTimeStack.get(pointer );
        var args = runTimeStack.size() - framePointer.peek();
        var arr = new int[args];
        IntStream.range(framePointer.peek(), runTimeStack.size()).forEach(i -> {
            arr[i - framePointer.peek()] = runTimeStack.get(i);
    } );
        return arr;
    }

    public void helperPop(int pops) {
        if(runTimeStack.isEmpty()){
            System.out.println("Empty STACK (helperPop)");
            System.exit(-5);
        }
        IntStream.range(0, pops).forEachOrdered(i -> this.pop());
    }


    Integer push(Integer i) {
        runTimeStack.add(i);
        return i;
    }
}

