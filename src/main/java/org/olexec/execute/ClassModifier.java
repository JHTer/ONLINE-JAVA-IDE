package org.olexec.execute;

import org.olexec.util.IntCodec;
import org.olexec.util.StringCodec;
import org.olexec.util.ArrayUtils;

/**
 * Minimalistic constant-pool editor used to replace UTF-8 strings in a compiled
 * class file. The implementation manually navigates the binary structure of a
 * `.class` file (see JVMS §4). It only supports `CONSTANT_Utf8_info` entries
 * and is therefore <strong>not</strong> a general-purpose byte-code tool.
 * <p>
 * For production-grade usage consider using ASM or ByteBuddy.
 */
public class ClassModifier {
    /** Byte offset where the constant-pool count starts (0x0008). */
    private static final int CONSTANT_POOL_COUNT_INDEX = 8;

    /** Tag value for CONSTANT_Utf8_info entries. */
    private static final int CONSTANT_UTF8_INFO = 1;

    /**
     * Length table for the 11 constant-pool entry types. Index by tag to get
     * the size (in bytes) of the corresponding entry.
     */
    private static final int[] CONSTANT_ITEM_LENGTH = {-1, -1, -1, 5, 5, 9, 9, 3, 3, 5, 5, 5, 5};

    /** Helper constants for 1-byte (u1) and 2-byte (u2) values inside the class file. */
    private static final int u1 = 1;
    private static final int u2 = 2;

    /** The raw class-file bytes to be modified. */
    private byte[] classByte;

    public ClassModifier(byte[] classByte) {
        this.classByte = classByte;
    }

    /**
     * Reads the constant-pool count (2 bytes at offset 8).
     *
     * @return number of entries in the constant pool
     */
    public int getConstantPoolCount() {
        return IntCodec.bytesToInt(classByte, CONSTANT_POOL_COUNT_INDEX, u2);
    }

    /**
     * Replaces occurrences of {@code oldStr} with {@code newStr} in the
     * constant-pool's Utf8 entries.
     *
     * @return the modified class-file bytes
     */
    public byte[] modifyUTF8Constant(String oldStr, String newStr) {
        int cpc = getConstantPoolCount();
        int offset = CONSTANT_POOL_COUNT_INDEX + u2;  // Start of the first constant-pool entry
        for (int i = 1; i < cpc; i++) {
            int tag = IntCodec.bytesToInt(classByte, offset, u1);
            if (tag == CONSTANT_UTF8_INFO) {
                int len = IntCodec.bytesToInt(classByte, offset + u1, u2);
                offset += u1 + u2;
                String str = StringCodec.bytesToString(classByte, offset, len);
                if (str.equals(oldStr)) {
                    byte[] strReplaceBytes = StringCodec.stringToBytes(newStr);
                    byte[] intReplaceBytes = IntCodec.intToBytes(strReplaceBytes.length, u2);
                    // Replace the length field
                    classByte = ArrayUtils.replace(classByte, offset - u2, u2, intReplaceBytes);
                    // Replace the actual bytes of the string
                    classByte = ArrayUtils.replace(classByte, offset, len, strReplaceBytes);
                    return classByte;  // Only one occurrence expected; safe to return early.
                } else {
                    offset += len;
                }
            } else {
                offset += CONSTANT_ITEM_LENGTH[tag];
            }
        }
        return classByte;
    }

}
