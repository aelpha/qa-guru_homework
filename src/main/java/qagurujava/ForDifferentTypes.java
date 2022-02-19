package qagurujava;

public class ForDifferentTypes {
    static byte aByte = 125;
    static short aShort = 32767;
    static int anInt;
    long aLong;
    float aFloat;
    double aDouble;
    char aChar;
    static boolean aBoolean;

    public static void main (String[] args) {
        for (int i = 0; i < 15; i++ ) {
            aByte = (byte) (aByte + 1);
            anInt = aByte + aShort + i;
            aBoolean = aByte < -120;
            System.out.println("aBoolean = " + aBoolean + "   aByte = " + aByte + "    anInt = " + anInt);
        }
    }

}
