package interpreter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.StringTokenizer;
import java.util.ArrayList;

import interpreter.bytecode.ByteCode;


public class ByteCodeLoader extends Object {

    private BufferedReader byteSource;
    private Program prg = new Program();
    private String mark;


    public ByteCodeLoader(String file) throws IOException {
        this.byteSource = new BufferedReader(new FileReader(file));
    }

    /**
     * This function reads one line of source code at a time.
     * For each line it :
     * Tokenizes the string to break it into parts.
     * Grabs THE correct class name for the given ByteCode from CodeTable
     * Creates an instance of the ByteCode class name returned from code table.
     * Parses any additional arguments for the given ByteCode and sends them to
     * the newly created ByteCode instance via the init function.
     */
    public Program loadCodes()  {
        try {
            while ((mark = byteSource.readLine()) != null) {

                var tok = new StringTokenizer(mark);
                var type = tok.nextToken(" ");
                var args = new ArrayList<String>();
                var classType = CodeTable.getClassName(type);

                Class<?> c = Class.forName(MessageFormat.format("interpreter.bytecode.{0}", classType));
                var bc = (ByteCode) c.getDeclaredConstructor().newInstance();

                while (tok.hasMoreTokens()) args.add(tok.nextToken());
               { bc.init(args);
                prg.fillup(bc);}
            }

        } catch (Exception ignored) {

        }

        prg.resolveAddress();

        return prg;
    }


}