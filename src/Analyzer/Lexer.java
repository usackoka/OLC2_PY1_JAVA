/* The following code was generated by JFlex 1.6.1 */

package Analyzer;
import java_cup.runtime.Symbol;
import AST.Principal;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.6.1
 * from the specification file <tt>lexer.jflex</tt>
 */
public class Lexer implements java_cup.runtime.Scanner {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;
  public static final int COM = 2;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0,  0,  1, 1
  };

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = {
     0,  0,  0,  0,  0,  0,  0,  0,  0, 52,  5, 53, 52, 52,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
    52, 48,  9,  4,  0, 43, 49,  0, 35, 36,  6, 40, 37, 41,  3, 44, 
     2,  2,  2,  2,  2,  2,  2,  2,  2,  2, 51, 38, 47, 39, 46, 45, 
     0, 15, 28, 21, 27, 18, 14,  7, 26, 23,  7, 30, 13,  7, 11, 24, 
     7,  7, 20, 17, 19, 12,  7, 25,  7,  7,  7, 31, 10, 32, 42,  8, 
     0, 15, 28, 21, 27, 18, 14,  7, 26, 23,  7, 30, 13,  7, 11, 24, 
     7,  7, 20, 17, 19, 12,  7, 25,  7,  7,  7, 33, 50, 34,  0,  0, 
     0,  0,  0,  0,  0, 53,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0
  };

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\2\0\1\1\2\2\1\3\1\1\1\4\1\5\1\1"+
    "\2\3\1\1\5\3\1\1\4\3\1\6\1\7\1\10"+
    "\1\11\1\12\1\13\1\14\1\15\1\16\1\17\1\20"+
    "\1\21\1\1\1\22\1\23\1\24\1\25\1\26\1\27"+
    "\1\30\1\31\2\0\1\32\2\0\1\33\1\0\4\3"+
    "\1\0\6\3\1\34\1\35\1\34\1\35\2\3\1\36"+
    "\1\3\1\37\1\40\1\41\1\42\1\43\1\44\1\32"+
    "\1\0\1\33\3\3\1\45\1\0\1\3\1\0\3\3"+
    "\1\0\2\3\1\0\3\3\2\0\1\32\1\46\1\3"+
    "\1\0\1\3\1\0\1\3\2\47\1\50\1\3\2\51"+
    "\1\3\1\0\4\3\2\52\1\0\2\3\1\0\1\3"+
    "\2\53\1\3\2\54\1\0\1\3\2\55\1\56\1\0"+
    "\2\3\1\0\1\3\1\0\1\3\1\57\2\60\2\61";

  private static int [] zzUnpackAction() {
    int [] result = new int[146];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\66\0\154\0\242\0\330\0\u010e\0\u0144\0\u017a"+
    "\0\154\0\u01b0\0\u01e6\0\u021c\0\u0252\0\u0288\0\u02be\0\u02f4"+
    "\0\u032a\0\u0360\0\u0396\0\u03cc\0\u0402\0\u0438\0\u046e\0\154"+
    "\0\154\0\154\0\154\0\154\0\154\0\154\0\154\0\u04a4"+
    "\0\154\0\154\0\154\0\u04da\0\154\0\154\0\u0510\0\u0546"+
    "\0\u057c\0\154\0\154\0\154\0\u05b2\0\u05e8\0\154\0\u061e"+
    "\0\u01b0\0\154\0\u0654\0\u068a\0\u06c0\0\u06f6\0\u072c\0\u0762"+
    "\0\u0798\0\u07ce\0\u0804\0\u083a\0\u0870\0\u08a6\0\154\0\154"+
    "\0\u010e\0\u010e\0\u08dc\0\u0912\0\u010e\0\u0948\0\154\0\154"+
    "\0\154\0\154\0\154\0\u05b2\0\u097e\0\u09b4\0\u01b0\0\u09ea"+
    "\0\u0a20\0\u0a56\0\u010e\0\u0a8c\0\u0ac2\0\u0af8\0\u0b2e\0\u0b64"+
    "\0\u0b9a\0\u0bd0\0\u0c06\0\u0c3c\0\u0c72\0\u0ca8\0\u0cde\0\u0d14"+
    "\0\u097e\0\u0d4a\0\u05e8\0\u010e\0\u0d80\0\u0db6\0\u0dec\0\u0e22"+
    "\0\u0e58\0\154\0\u010e\0\u010e\0\u0e8e\0\154\0\u010e\0\u0ec4"+
    "\0\u0efa\0\u0f30\0\u0f66\0\u0f9c\0\u0fd2\0\154\0\u010e\0\u1008"+
    "\0\u103e\0\u1074\0\u10aa\0\u10e0\0\154\0\u010e\0\u1116\0\154"+
    "\0\u010e\0\u114c\0\u1182\0\154\0\u010e\0\u010e\0\u11b8\0\u11ee"+
    "\0\u1224\0\u125a\0\u1290\0\u12c6\0\u12fc\0\u010e\0\154\0\u010e"+
    "\0\154\0\u010e";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[146];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\6"+
    "\1\3\1\12\1\3\1\13\2\6\1\14\1\6\1\15"+
    "\1\16\1\17\1\20\1\21\1\22\1\23\1\24\1\6"+
    "\1\25\1\6\1\26\1\27\1\3\1\6\1\30\1\31"+
    "\1\32\1\33\1\34\1\35\1\36\1\37\1\40\1\41"+
    "\1\42\1\43\1\44\1\45\1\46\1\47\1\50\1\51"+
    "\1\52\1\53\1\54\1\10\1\0\4\3\1\7\1\10"+
    "\56\3\1\10\70\0\2\4\64\0\1\4\1\5\1\55"+
    "\64\0\2\6\3\0\2\6\2\0\5\6\1\0\5\6"+
    "\1\0\6\6\1\0\1\6\27\0\5\56\1\57\1\60"+
    "\57\56\5\0\1\10\56\0\1\10\1\0\5\61\1\0"+
    "\3\61\1\62\1\63\53\61\2\0\2\6\3\0\2\6"+
    "\2\0\1\6\1\64\3\6\1\0\5\6\1\0\6\6"+
    "\1\0\1\6\31\0\2\6\3\0\2\6\2\0\1\6"+
    "\1\65\2\6\1\66\1\0\5\6\1\0\1\6\1\67"+
    "\4\6\1\0\1\6\60\0\1\70\36\0\2\6\3\0"+
    "\2\6\2\0\5\6\1\0\5\6\1\0\2\6\1\71"+
    "\3\6\1\0\1\6\31\0\2\6\3\0\2\6\2\0"+
    "\2\6\1\72\2\6\1\0\5\6\1\0\6\6\1\0"+
    "\1\6\31\0\2\6\3\0\2\6\2\0\5\6\1\0"+
    "\3\6\1\73\1\6\1\0\6\6\1\0\1\6\31\0"+
    "\2\6\3\0\2\6\2\0\5\6\1\0\1\6\1\74"+
    "\3\6\1\0\6\6\1\0\1\6\31\0\2\6\3\0"+
    "\2\6\2\0\4\6\1\75\1\0\5\6\1\0\1\6"+
    "\1\76\4\6\1\0\1\6\42\0\1\77\2\0\1\100"+
    "\51\0\2\6\3\0\2\6\2\0\1\101\2\6\1\102"+
    "\1\6\1\0\5\6\1\0\6\6\1\0\1\6\31\0"+
    "\2\6\3\0\2\6\2\0\5\6\1\0\5\6\1\0"+
    "\3\6\1\103\2\6\1\0\1\6\31\0\2\6\3\0"+
    "\2\6\2\0\5\6\1\0\1\6\1\104\3\6\1\0"+
    "\1\6\1\105\4\6\1\0\1\6\31\0\2\6\3\0"+
    "\2\6\2\0\5\6\1\0\3\6\1\106\1\6\1\0"+
    "\6\6\1\0\1\6\76\0\1\107\71\0\1\110\61\0"+
    "\1\111\65\0\1\112\65\0\1\113\20\0\1\114\63\0"+
    "\5\56\1\57\60\56\5\60\1\115\1\116\57\60\5\61"+
    "\1\0\3\61\1\117\1\63\53\61\2\0\2\6\3\0"+
    "\2\6\2\0\2\6\1\120\2\6\1\0\5\6\1\0"+
    "\6\6\1\0\1\6\31\0\2\6\3\0\2\6\2\0"+
    "\1\121\4\6\1\0\5\6\1\0\6\6\1\0\1\6"+
    "\31\0\2\6\3\0\2\6\2\0\2\6\1\122\2\6"+
    "\1\0\5\6\1\0\6\6\1\0\1\6\31\0\2\6"+
    "\3\0\2\6\2\0\5\6\1\0\3\6\1\123\1\6"+
    "\1\0\6\6\1\0\1\6\55\0\2\124\40\0\2\6"+
    "\3\0\2\6\2\0\5\6\1\0\5\6\1\124\1\125"+
    "\5\6\1\0\1\6\31\0\2\6\3\0\2\6\2\0"+
    "\5\6\1\126\1\127\4\6\1\0\6\6\1\0\1\6"+
    "\31\0\2\6\3\0\2\6\2\0\1\6\1\130\3\6"+
    "\1\0\5\6\1\0\6\6\1\0\1\6\31\0\2\6"+
    "\3\0\2\6\2\0\5\6\1\0\2\6\1\131\2\6"+
    "\1\0\6\6\1\0\1\6\31\0\2\6\3\0\2\6"+
    "\2\0\5\6\1\132\1\133\4\6\1\0\6\6\1\0"+
    "\1\6\31\0\2\6\3\0\2\6\2\0\1\134\4\6"+
    "\1\0\5\6\1\0\6\6\1\0\1\6\31\0\2\6"+
    "\3\0\2\6\2\0\5\6\1\0\5\6\1\135\1\136"+
    "\5\6\1\0\1\6\31\0\2\6\3\0\2\6\2\0"+
    "\3\6\1\137\1\6\1\0\5\6\1\0\6\6\1\0"+
    "\1\6\31\0\2\6\3\0\2\6\2\0\5\6\1\0"+
    "\1\6\1\140\3\6\1\0\6\6\1\0\1\6\27\0"+
    "\6\141\1\142\57\141\4\60\1\143\1\115\1\116\57\60"+
    "\2\0\2\6\3\0\2\6\2\0\2\6\1\144\2\6"+
    "\1\0\5\6\1\0\6\6\1\0\1\6\31\0\2\6"+
    "\3\0\2\6\2\0\5\6\1\0\4\6\1\145\1\0"+
    "\6\6\1\0\1\6\31\0\2\6\3\0\2\6\2\0"+
    "\5\6\1\146\1\147\4\6\1\0\6\6\1\0\1\6"+
    "\52\0\1\150\44\0\2\6\3\0\2\6\2\0\5\6"+
    "\1\0\2\6\1\151\2\6\1\0\6\6\1\0\1\6"+
    "\51\0\1\152\45\0\2\6\3\0\2\6\2\0\5\6"+
    "\1\0\1\6\1\153\3\6\1\0\6\6\1\0\1\6"+
    "\31\0\2\6\3\0\2\6\2\0\5\6\1\0\1\6"+
    "\1\154\3\6\1\0\6\6\1\0\1\6\31\0\2\6"+
    "\3\0\2\6\2\0\1\6\1\155\3\6\1\0\5\6"+
    "\1\0\6\6\1\0\1\6\51\0\1\156\45\0\2\6"+
    "\3\0\2\6\2\0\5\6\1\0\1\6\1\157\3\6"+
    "\1\0\6\6\1\0\1\6\31\0\2\6\3\0\2\6"+
    "\2\0\5\6\1\0\2\6\1\160\2\6\1\0\6\6"+
    "\1\0\1\6\44\0\1\161\52\0\2\6\3\0\2\6"+
    "\2\0\2\6\1\162\2\6\1\0\5\6\1\0\6\6"+
    "\1\0\1\6\31\0\2\6\3\0\2\6\2\0\4\6"+
    "\1\163\1\0\5\6\1\0\6\6\1\0\1\6\31\0"+
    "\2\6\3\0\2\6\2\0\4\6\1\164\1\0\5\6"+
    "\1\0\6\6\1\0\1\6\27\0\4\141\1\57\1\141"+
    "\1\142\57\141\2\0\2\6\3\0\2\6\2\0\5\6"+
    "\1\0\2\6\1\165\2\6\1\0\6\6\1\0\1\6"+
    "\51\0\1\166\45\0\2\6\3\0\2\6\2\0\5\6"+
    "\1\0\1\6\1\167\3\6\1\0\6\6\1\0\1\6"+
    "\54\0\1\170\42\0\2\6\3\0\2\6\2\0\5\6"+
    "\1\0\4\6\1\171\1\0\6\6\1\0\1\6\31\0"+
    "\2\6\3\0\2\6\2\0\5\6\1\0\3\6\1\172"+
    "\1\6\1\0\6\6\1\0\1\6\31\0\2\6\3\0"+
    "\2\6\2\0\5\6\1\0\5\6\1\173\1\174\5\6"+
    "\1\0\1\6\51\0\1\175\45\0\2\6\3\0\2\6"+
    "\2\0\5\6\1\0\1\6\1\176\3\6\1\0\6\6"+
    "\1\0\1\6\31\0\2\6\3\0\2\6\2\0\1\6"+
    "\1\177\3\6\1\0\5\6\1\0\6\6\1\0\1\6"+
    "\31\0\2\6\3\0\2\6\2\0\5\6\1\0\5\6"+
    "\1\0\6\6\1\200\1\201\31\0\2\6\3\0\2\6"+
    "\2\0\5\6\1\0\5\6\1\202\1\203\5\6\1\0"+
    "\1\6\61\0\1\204\35\0\2\6\3\0\2\6\2\0"+
    "\5\6\1\0\5\6\1\0\3\6\1\205\2\6\1\0"+
    "\1\6\31\0\2\6\3\0\2\6\2\0\1\206\4\6"+
    "\1\0\5\6\1\0\6\6\1\0\1\6\42\0\1\207"+
    "\54\0\2\6\3\0\2\6\2\0\1\210\4\6\1\0"+
    "\5\6\1\0\6\6\1\0\1\6\31\0\2\6\3\0"+
    "\2\6\2\0\2\6\1\211\2\6\1\0\5\6\1\0"+
    "\6\6\1\0\1\6\57\0\1\212\37\0\2\6\3\0"+
    "\2\6\2\0\5\6\1\0\5\6\1\0\1\6\1\213"+
    "\4\6\1\0\1\6\43\0\1\214\53\0\2\6\3\0"+
    "\2\6\2\0\1\6\1\215\3\6\1\0\5\6\1\0"+
    "\6\6\1\0\1\6\31\0\2\6\3\0\2\6\2\0"+
    "\5\6\1\0\2\6\1\216\2\6\1\0\6\6\1\0"+
    "\1\6\42\0\1\217\54\0\2\6\3\0\2\6\2\0"+
    "\1\220\4\6\1\0\5\6\1\0\6\6\1\0\1\6"+
    "\51\0\1\221\45\0\2\6\3\0\2\6\2\0\5\6"+
    "\1\0\1\6\1\222\3\6\1\0\6\6\1\0\1\6"+
    "\27\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[4914];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unknown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\2\0\1\11\5\1\1\11\16\1\10\11\1\1\3\11"+
    "\1\1\2\11\3\1\3\11\2\0\1\11\2\0\1\11"+
    "\1\0\4\1\1\0\6\1\2\11\6\1\5\11\2\1"+
    "\1\0\5\1\1\0\1\1\1\0\3\1\1\0\2\1"+
    "\1\0\3\1\2\0\3\1\1\0\1\1\1\0\1\1"+
    "\1\11\3\1\1\11\2\1\1\0\4\1\1\11\1\1"+
    "\1\0\2\1\1\0\1\1\1\11\2\1\1\11\1\1"+
    "\1\0\1\1\1\11\2\1\1\0\2\1\1\0\1\1"+
    "\1\0\2\1\1\11\1\1\1\11\1\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[146];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;
  
  /** 
   * The number of occupied positions in zzBuffer beyond zzEndRead.
   * When a lead/high surrogate has been read from the input stream
   * into the final zzBuffer position, this will have a value of 1;
   * otherwise, it will have a value of 0.
   */
  private int zzFinalHighSurrogate = 0;

  /* user code: */
    Principal principal;

    public void setPrincipal(Principal principal){
        this.principal = principal;
    }

    public Principal getPrincipal(){
        return this.principal;
    }


  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public Lexer(java.io.Reader in) {
    this.zzReader = in;
  }



  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length - zzFinalHighSurrogate) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzBuffer.length*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
    }

    /* fill the buffer with new input */
    int requested = zzBuffer.length - zzEndRead;
    int numRead = zzReader.read(zzBuffer, zzEndRead, requested);

    /* not supposed to occur according to specification of java.io.Reader */
    if (numRead == 0) {
      throw new java.io.IOException("Reader returned 0 characters. See JFlex examples for workaround.");
    }
    if (numRead > 0) {
      zzEndRead += numRead;
      /* If numRead == requested, we might have requested to few chars to
         encode a full Unicode character. We assume that a Reader would
         otherwise never return half characters. */
      if (numRead == requested) {
        if (Character.isHighSurrogate(zzBuffer[zzEndRead - 1])) {
          --zzEndRead;
          zzFinalHighSurrogate = 1;
        }
      }
      /* potentially more input available */
      return false;
    }

    /* numRead < 0 ==> end of stream */
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * Internal scan buffer is resized down to its initial length, if it has grown.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    zzFinalHighSurrogate = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
    if (zzBuffer.length > ZZ_BUFFERSIZE)
      zzBuffer = new char[ZZ_BUFFERSIZE];
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
      yyclose();
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public java_cup.runtime.Symbol next_token() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      yychar+= zzMarkedPosL-zzStartRead;

      boolean zzR = false;
      int zzCh;
      int zzCharCount;
      for (zzCurrentPosL = zzStartRead  ;
           zzCurrentPosL < zzMarkedPosL ;
           zzCurrentPosL += zzCharCount ) {
        zzCh = Character.codePointAt(zzBufferL, zzCurrentPosL, zzMarkedPosL);
        zzCharCount = Character.charCount(zzCh);
        switch (zzCh) {
        case '\u000B':
        case '\u000C':
        case '\u0085':
        case '\u2028':
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn += zzCharCount;
        }
      }

      if (zzR) {
        // peek one character ahead if it is \n (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof) 
            zzPeek = false;
          else 
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
            zzCurrentPosL += Character.charCount(zzInput);
          }
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
        zzAtEOF = true;
            zzDoEOF();
          { return new java_cup.runtime.Symbol(sym.EOF); }
      }
      else {
        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
          case 1: 
            { Token token = new Token();
        token.lexema = yytext();
        token.componenteLexico = "Léxico";
        token.descripcion = "El caracter no pertenece al lenguaje";
        token.columna = yycolumn+1+"";
        token.fila = yyline+1+"";
        this.principal.addError(token);
            }
          case 50: break;
          case 2: 
            { return new Symbol(sym.numero,yyline,yychar,yytext());
            }
          case 51: break;
          case 3: 
            { return new Symbol(sym.id,yyline,yychar,yytext());
            }
          case 52: break;
          case 4: 
            { /* Espacios en blanco se ingnoran */
            }
          case 53: break;
          case 5: 
            { return new Symbol(sym.por,yyline,yychar, yytext());
            }
          case 54: break;
          case 6: 
            { return new Symbol(sym.l_corchete,yyline,yychar, yytext());
            }
          case 55: break;
          case 7: 
            { return new Symbol(sym.r_corchete,yyline,yychar, yytext());
            }
          case 56: break;
          case 8: 
            { return new Symbol(sym.l_llave,yyline,yychar, yytext());
            }
          case 57: break;
          case 9: 
            { return new Symbol(sym.r_llave,yyline,yychar, yytext());
            }
          case 58: break;
          case 10: 
            { return new Symbol(sym.l_parent,yyline,yychar, yytext());
            }
          case 59: break;
          case 11: 
            { return new Symbol(sym.r_parent,yyline,yychar, yytext());
            }
          case 60: break;
          case 12: 
            { return new Symbol(sym.coma,yyline,yychar, yytext());
            }
          case 61: break;
          case 13: 
            { return new Symbol(sym.puntocoma,yyline,yychar, yytext());
            }
          case 62: break;
          case 14: 
            { return new Symbol(sym.igual,yyline,yychar, yytext());
            }
          case 63: break;
          case 15: 
            { return new Symbol(sym.mas,yyline,yychar, yytext());
            }
          case 64: break;
          case 16: 
            { return new Symbol(sym.menos,yyline,yychar, yytext());
            }
          case 65: break;
          case 17: 
            { return new Symbol(sym.potencia,yyline,yychar, yytext());
            }
          case 66: break;
          case 18: 
            { return new Symbol(sym.div,yyline,yychar, yytext());
            }
          case 67: break;
          case 19: 
            { return new Symbol(sym.ternario,yyline,yychar, yytext());
            }
          case 68: break;
          case 20: 
            { return new Symbol(sym.mayor,yyline,yychar, yytext());
            }
          case 69: break;
          case 21: 
            { return new Symbol(sym.menor,yyline,yychar, yytext());
            }
          case 70: break;
          case 22: 
            { return new Symbol(sym.not,yyline,yychar, yytext());
            }
          case 71: break;
          case 23: 
            { return new Symbol(sym.and,yyline,yychar, yytext());
            }
          case 72: break;
          case 24: 
            { return new Symbol(sym.or,yyline,yychar, yytext());
            }
          case 73: break;
          case 25: 
            { return new Symbol(sym.dospuntos,yyline,yychar, yytext());
            }
          case 74: break;
          case 26: 
            { /*se ignoran*/
            }
          case 75: break;
          case 27: 
            { return new Symbol(sym.cadena,yyline,yychar,yytext());
            }
          case 76: break;
          case 28: 
            { return new Symbol(sym.res_in,yyline,yychar,yytext());
            }
          case 77: break;
          case 29: 
            { return new Symbol(sym.res_if,yyline,yychar,yytext());
            }
          case 78: break;
          case 30: 
            { return new Symbol(sym.res_do,yyline,yychar,yytext());
            }
          case 79: break;
          case 31: 
            { return new Symbol(sym.igualigual,yyline,yychar, yytext());
            }
          case 80: break;
          case 32: 
            { return new Symbol(sym.modular,yyline,yychar, yytext());
            }
          case 81: break;
          case 33: 
            { return new Symbol(sym.mayorigual,yyline,yychar, yytext());
            }
          case 82: break;
          case 34: 
            { return new Symbol(sym.menorigual,yyline,yychar, yytext());
            }
          case 83: break;
          case 35: 
            { return new Symbol(sym.diferente,yyline,yychar, yytext());
            }
          case 84: break;
          case 36: 
            { return new Symbol(sym.doble,yyline,yychar,yytext());
            }
          case 85: break;
          case 37: 
            { return new Symbol(sym.res_for,yyline,yychar,yytext());
            }
          case 86: break;
          case 38: 
            { return new Symbol(sym.res_null,yyline,yychar,yytext());
            }
          case 87: break;
          case 39: 
            { return new Symbol(sym.res_else,yyline,yychar,yytext());
            }
          case 88: break;
          case 40: 
            { return new Symbol(sym.res_true,yyline,yychar,yytext());
            }
          case 89: break;
          case 41: 
            { return new Symbol(sym.res_case,yyline,yychar,yytext());
            }
          case 90: break;
          case 42: 
            { return new Symbol(sym.res_false,yyline,yychar,yytext());
            }
          case 91: break;
          case 43: 
            { return new Symbol(sym.res_while,yyline,yychar,yytext());
            }
          case 92: break;
          case 44: 
            { return new Symbol(sym.res_break,yyline,yychar,yytext());
            }
          case 93: break;
          case 45: 
            { return new Symbol(sym.res_switch,yyline,yychar,yytext());
            }
          case 94: break;
          case 46: 
            { return new Symbol(sym.res_return,yyline,yychar,yytext());
            }
          case 95: break;
          case 47: 
            { return new Symbol(sym.res_default,yyline,yychar,yytext());
            }
          case 96: break;
          case 48: 
            { return new Symbol(sym.res_function,yyline,yychar,yytext());
            }
          case 97: break;
          case 49: 
            { return new Symbol(sym.res_continue,yyline,yychar,yytext());
            }
          case 98: break;
          default:
            zzScanError(ZZ_NO_MATCH);
        }
      }
    }
  }


}
