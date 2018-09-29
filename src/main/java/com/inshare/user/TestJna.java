package com.inshare.user;

import com.sun.jna.*;

import java.util.Arrays;
import java.util.List;

/**
 * @author Guichao
 * @since 2018/9/29
 */
public class TestJna {

    public interface Clibrary extends Library{

        Clibrary INSTANTCE = (Clibrary) Native.loadLibrary("test_call", Clibrary.class);

        public static class Stru_Base extends Structure {

            public byte[] ba = new byte[6];
            public double[] bb = new double[2];

            public static class ByReference extends Stru_Base implements Structure.ByReference{}
            public static class ByValue extends Stru_Base implements Structure.ByValue{}

            @Override
            protected List<String> getFieldOrder() {
                return Arrays.asList(new String[]{"ba", "bb"});
            }
        }

        public static class Stru_InfoIn extends Structure {

            public int a;
            public float b;
            public double c;
            public byte[] d = new byte[10];
            public int[] e = new int[2];
            public double[] f = new double[2];
            public Stru_Base.ByValue[] g = new Stru_Base.ByValue[2];

            public static class ByReference extends Stru_InfoIn implements Structure.ByReference{}
            public static class ByValue extends Stru_InfoIn implements Structure.ByValue{}

            @Override
            protected List<String> getFieldOrder() {
                return Arrays.asList(new String[]{"a", "b", "c", "d", "e", "f", "g"});
            }
        }

        public static class Stru_InfoOut extends Structure {

            public int a;
            public float b;
            public double c;
            public byte[] d = new byte[10];
            public int[] e = new int[2];
            public double[] f = new double[2];
            public Stru_Base.ByValue[] g = new Stru_Base.ByValue[2];

            public static class ByReference extends Stru_InfoOut implements Structure.ByReference{}
            public static class ByValue extends Stru_InfoOut implements Structure.ByValue{}

            @Override
            protected List<String> getFieldOrder() {
                return Arrays.asList(new String[]{"a", "b", "c", "d", "e", "f", "g"});
            }
        }

        public int callTest(Stru_InfoIn.ByValue[] data_in, Stru_InfoOut.ByReference data_out);

    }

    public static void main(String[] args) {
        Clibrary.Stru_InfoIn.ByValue In = new Clibrary.Stru_InfoIn.ByValue();
        Clibrary.Stru_InfoIn.ByValue[] dataIn = (Clibrary.Stru_InfoIn.ByValue[])In.toArray(2);

        Clibrary.Stru_Base.ByValue baseD = new Clibrary.Stru_Base.ByValue();
        Clibrary.Stru_Base.ByValue[] dataBase = (Clibrary.Stru_Base.ByValue[])baseD.toArray(2);

        System.arraycopy("firstB0", 0, dataBase[0].ba, 0, "firstB0".length());
        System.arraycopy("SecondB1", 0, dataBase[1].ba, 0, "SecondB1".length());
        dataBase[0].bb[0] = 12.32;
        dataBase[0].bb[1] = 22.32;
        dataBase[1].bb[0] = 118.3432;
        dataBase[1].bb[1] = 124.32;

        Clibrary.Stru_InfoIn.ByValue first = dataIn[0];
        first.a = 10;
        System.arraycopy("firstVal", 0, first.d, 0, "firstVal".length());
        first.e[0] = 1;
        first.e[1] = 2;
        first.g[0] = dataBase[0];
        first.g[1] = dataBase[1];

        Clibrary.Stru_InfoIn.ByValue senond = dataIn[1];
        senond.b = 343.453F;
        senond.c = 56567.4534;
        senond.f[0] = 234.453;
        senond.f[1] = 3464.453;
        senond.g[0] = dataBase[0];
        senond.g[1] = dataBase[1];

        Clibrary.Stru_InfoOut.ByReference dataOut = new Clibrary.Stru_InfoOut.ByReference();
        int result = Clibrary.INSTANTCE.callTest(dataIn, dataOut);
    }
}
