// $ANTLR 3.0 ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g 2008-10-03 17:22:34

package com.piece_framework.piece_ide.piece_orm.mapper.parser;

import org.openarchitectureware.xtext.parser.ErrorMsg;
import org.openarchitectureware.xtext.parser.impl.AntlrUtil;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class Piece_ORM_MapperLexer extends Lexer {
    public static final int T21=21;
    public static final int RULE_ML_COMMENT=8;
    public static final int T14=14;
    public static final int RULE_ID=4;
    public static final int T22=22;
    public static final int T11=11;
    public static final int RULE_STRING=5;
    public static final int T12=12;
    public static final int T13=13;
    public static final int T20=20;
    public static final int T10=10;
    public static final int T18=18;
    public static final int RULE_WS=7;
    public static final int T15=15;
    public static final int RULE_INT=6;
    public static final int EOF=-1;
    public static final int T17=17;
    public static final int Tokens=23;
    public static final int T16=16;
    public static final int RULE_SL_COMMENT=9;
    public static final int T19=19;

    	 private List<ErrorMsg> errors = new ArrayList<ErrorMsg>();
    	public List<ErrorMsg> getErrors() {
    		return errors;
    	}

    	public String getErrorMessage(RecognitionException e, String[] tokenNames) {
    		String msg = super.getErrorMessage(e,tokenNames);
    		errors.add(AntlrUtil.create(msg,e,tokenNames));
    		return msg;
    	}

    public Piece_ORM_MapperLexer() {;} 
    public Piece_ORM_MapperLexer(CharStream input) {
        super(input);
    }
    public String getGrammarFileName() { return "..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g"; }

    // $ANTLR start T10
    public void mT10() throws RecognitionException {
        try {
            int _type = T10;
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:22:7: ( 'query' )
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:22:7: 'query'
            {
            match("query"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T10

    // $ANTLR start T11
    public void mT11() throws RecognitionException {
        try {
            int _type = T11;
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:23:7: ( 'orderBy' )
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:23:7: 'orderBy'
            {
            match("orderBy"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T11

    // $ANTLR start T12
    public void mT12() throws RecognitionException {
        try {
            int _type = T12;
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:24:7: ( 'associations' )
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:24:7: 'associations'
            {
            match("associations"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T12

    // $ANTLR start T13
    public void mT13() throws RecognitionException {
        try {
            int _type = T13;
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:25:7: ( 'table' )
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:25:7: 'table'
            {
            match("table"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T13

    // $ANTLR start T14
    public void mT14() throws RecognitionException {
        try {
            int _type = T14;
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:26:7: ( 'type' )
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:26:7: 'type'
            {
            match("type"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T14

    // $ANTLR start T15
    public void mT15() throws RecognitionException {
        try {
            int _type = T15;
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:27:7: ( 'ManyToMany' )
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:27:7: 'ManyToMany'
            {
            match("ManyToMany"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T15

    // $ANTLR start T16
    public void mT16() throws RecognitionException {
        try {
            int _type = T16;
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:28:7: ( 'OneToMany' )
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:28:7: 'OneToMany'
            {
            match("OneToMany"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T16

    // $ANTLR start T17
    public void mT17() throws RecognitionException {
        try {
            int _type = T17;
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:29:7: ( 'ManyToOne' )
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:29:7: 'ManyToOne'
            {
            match("ManyToOne"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T17

    // $ANTLR start T18
    public void mT18() throws RecognitionException {
        try {
            int _type = T18;
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:30:7: ( 'OneToOne' )
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:30:7: 'OneToOne'
            {
            match("OneToOne"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T18

    // $ANTLR start T19
    public void mT19() throws RecognitionException {
        try {
            int _type = T19;
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:31:7: ( 'column' )
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:31:7: 'column'
            {
            match("column"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T19

    // $ANTLR start T20
    public void mT20() throws RecognitionException {
        try {
            int _type = T20;
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:32:7: ( 'referencedColumn' )
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:32:7: 'referencedColumn'
            {
            match("referencedColumn"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T20

    // $ANTLR start T21
    public void mT21() throws RecognitionException {
        try {
            int _type = T21;
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:33:7: ( 'through' )
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:33:7: 'through'
            {
            match("through"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T21

    // $ANTLR start T22
    public void mT22() throws RecognitionException {
        try {
            int _type = T22;
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:34:7: ( 'inverseColumn' )
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:34:7: 'inverseColumn'
            {
            match("inverseColumn"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T22

    // $ANTLR start RULE_ID
    public void mRULE_ID() throws RecognitionException {
        try {
            int _type = RULE_ID;
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:363:3: ( ( '^' )? ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )* )
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:363:3: ( '^' )? ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            {
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:363:3: ( '^' )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0=='^') ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:363:4: '^'
                    {
                    match('^'); 

                    }
                    break;

            }

            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recover(mse);    throw mse;
            }

            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:363:33: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>='0' && LA2_0<='9')||(LA2_0>='A' && LA2_0<='Z')||LA2_0=='_'||(LA2_0>='a' && LA2_0<='z')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:
            	    {
            	    if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse =
            	            new MismatchedSetException(null,input);
            	        recover(mse);    throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RULE_ID

    // $ANTLR start RULE_STRING
    public void mRULE_STRING() throws RecognitionException {
        try {
            int _type = RULE_STRING;
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:369:3: ( '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' ) | ~ ( '\\\\' | '\"' ) )* '\"' | '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' ) | ~ ( '\\\\' | '\\'' ) )* '\\'' )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0=='\"') ) {
                alt5=1;
            }
            else if ( (LA5_0=='\'') ) {
                alt5=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("367:1: RULE_STRING : ( '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' ) | ~ ( '\\\\' | '\"' ) )* '\"' | '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' ) | ~ ( '\\\\' | '\\'' ) )* '\\'' );", 5, 0, input);

                throw nvae;
            }
            switch (alt5) {
                case 1 :
                    // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:369:3: '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' ) | ~ ( '\\\\' | '\"' ) )* '\"'
                    {
                    match('\"'); 
                    // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:369:7: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' ) | ~ ( '\\\\' | '\"' ) )*
                    loop3:
                    do {
                        int alt3=3;
                        int LA3_0 = input.LA(1);

                        if ( (LA3_0=='\\') ) {
                            alt3=1;
                        }
                        else if ( ((LA3_0>='\u0000' && LA3_0<='!')||(LA3_0>='#' && LA3_0<='[')||(LA3_0>=']' && LA3_0<='\uFFFE')) ) {
                            alt3=2;
                        }


                        switch (alt3) {
                    	case 1 :
                    	    // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:369:9: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' )
                    	    {
                    	    match('\\'); 
                    	    if ( input.LA(1)=='\"'||input.LA(1)=='\''||input.LA(1)=='\\'||input.LA(1)=='b'||input.LA(1)=='f'||input.LA(1)=='n'||input.LA(1)=='r'||input.LA(1)=='t' ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse =
                    	            new MismatchedSetException(null,input);
                    	        recover(mse);    throw mse;
                    	    }


                    	    }
                    	    break;
                    	case 2 :
                    	    // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:369:53: ~ ( '\\\\' | '\"' )
                    	    {
                    	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='!')||(input.LA(1)>='#' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFE') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse =
                    	            new MismatchedSetException(null,input);
                    	        recover(mse);    throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop3;
                        }
                    } while (true);

                    match('\"'); 

                    }
                    break;
                case 2 :
                    // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:370:3: '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' ) | ~ ( '\\\\' | '\\'' ) )* '\\''
                    {
                    match('\''); 
                    // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:370:8: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' ) | ~ ( '\\\\' | '\\'' ) )*
                    loop4:
                    do {
                        int alt4=3;
                        int LA4_0 = input.LA(1);

                        if ( (LA4_0=='\\') ) {
                            alt4=1;
                        }
                        else if ( ((LA4_0>='\u0000' && LA4_0<='&')||(LA4_0>='(' && LA4_0<='[')||(LA4_0>=']' && LA4_0<='\uFFFE')) ) {
                            alt4=2;
                        }


                        switch (alt4) {
                    	case 1 :
                    	    // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:370:10: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' )
                    	    {
                    	    match('\\'); 
                    	    if ( input.LA(1)=='\"'||input.LA(1)=='\''||input.LA(1)=='\\'||input.LA(1)=='b'||input.LA(1)=='f'||input.LA(1)=='n'||input.LA(1)=='r'||input.LA(1)=='t' ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse =
                    	            new MismatchedSetException(null,input);
                    	        recover(mse);    throw mse;
                    	    }


                    	    }
                    	    break;
                    	case 2 :
                    	    // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:370:54: ~ ( '\\\\' | '\\'' )
                    	    {
                    	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='&')||(input.LA(1)>='(' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFE') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse =
                    	            new MismatchedSetException(null,input);
                    	        recover(mse);    throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop4;
                        }
                    } while (true);

                    match('\''); 

                    }
                    break;

            }
            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RULE_STRING

    // $ANTLR start RULE_INT
    public void mRULE_INT() throws RecognitionException {
        try {
            int _type = RULE_INT;
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:376:3: ( ( '-' )? ( '0' .. '9' )+ )
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:376:3: ( '-' )? ( '0' .. '9' )+
            {
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:376:3: ( '-' )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0=='-') ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:376:4: '-'
                    {
                    match('-'); 

                    }
                    break;

            }

            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:376:9: ( '0' .. '9' )+
            int cnt7=0;
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( ((LA7_0>='0' && LA7_0<='9')) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:376:10: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt7 >= 1 ) break loop7;
                        EarlyExitException eee =
                            new EarlyExitException(7, input);
                        throw eee;
                }
                cnt7++;
            } while (true);


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RULE_INT

    // $ANTLR start RULE_WS
    public void mRULE_WS() throws RecognitionException {
        try {
            int _type = RULE_WS;
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:382:3: ( ( ' ' | '\\t' | '\\r' | '\\n' )+ )
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:382:3: ( ' ' | '\\t' | '\\r' | '\\n' )+
            {
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:382:3: ( ' ' | '\\t' | '\\r' | '\\n' )+
            int cnt8=0;
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( ((LA8_0>='\t' && LA8_0<='\n')||LA8_0=='\r'||LA8_0==' ') ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:
            	    {
            	    if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse =
            	            new MismatchedSetException(null,input);
            	        recover(mse);    throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt8 >= 1 ) break loop8;
                        EarlyExitException eee =
                            new EarlyExitException(8, input);
                        throw eee;
                }
                cnt8++;
            } while (true);

            channel=HIDDEN;

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RULE_WS

    // $ANTLR start RULE_ML_COMMENT
    public void mRULE_ML_COMMENT() throws RecognitionException {
        try {
            int _type = RULE_ML_COMMENT;
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:388:3: ( '/*' ( options {greedy=false; } : . )* '*/' )
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:388:3: '/*' ( options {greedy=false; } : . )* '*/'
            {
            match("/*"); 

            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:388:8: ( options {greedy=false; } : . )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0=='*') ) {
                    int LA9_1 = input.LA(2);

                    if ( (LA9_1=='/') ) {
                        alt9=2;
                    }
                    else if ( ((LA9_1>='\u0000' && LA9_1<='.')||(LA9_1>='0' && LA9_1<='\uFFFE')) ) {
                        alt9=1;
                    }


                }
                else if ( ((LA9_0>='\u0000' && LA9_0<=')')||(LA9_0>='+' && LA9_0<='\uFFFE')) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:388:36: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);

            match("*/"); 

            channel=HIDDEN;

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RULE_ML_COMMENT

    // $ANTLR start RULE_SL_COMMENT
    public void mRULE_SL_COMMENT() throws RecognitionException {
        try {
            int _type = RULE_SL_COMMENT;
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:394:3: ( '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n' )
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:394:3: '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n'
            {
            match("//"); 

            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:394:8: (~ ( '\\n' | '\\r' ) )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( ((LA10_0>='\u0000' && LA10_0<='\t')||(LA10_0>='\u000B' && LA10_0<='\f')||(LA10_0>='\u000E' && LA10_0<='\uFFFE')) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:394:8: ~ ( '\\n' | '\\r' )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='\uFFFE') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse =
            	            new MismatchedSetException(null,input);
            	        recover(mse);    throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);

            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:394:22: ( '\\r' )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0=='\r') ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:394:22: '\\r'
                    {
                    match('\r'); 

                    }
                    break;

            }

            match('\n'); 
            channel=HIDDEN;

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RULE_SL_COMMENT

    public void mTokens() throws RecognitionException {
        // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:1:10: ( T10 | T11 | T12 | T13 | T14 | T15 | T16 | T17 | T18 | T19 | T20 | T21 | T22 | RULE_ID | RULE_STRING | RULE_INT | RULE_WS | RULE_ML_COMMENT | RULE_SL_COMMENT )
        int alt12=19;
        switch ( input.LA(1) ) {
        case 'q':
            {
            int LA12_1 = input.LA(2);

            if ( (LA12_1=='u') ) {
                int LA12_15 = input.LA(3);

                if ( (LA12_15=='e') ) {
                    int LA12_28 = input.LA(4);

                    if ( (LA12_28=='r') ) {
                        int LA12_39 = input.LA(5);

                        if ( (LA12_39=='y') ) {
                            int LA12_50 = input.LA(6);

                            if ( ((LA12_50>='0' && LA12_50<='9')||(LA12_50>='A' && LA12_50<='Z')||LA12_50=='_'||(LA12_50>='a' && LA12_50<='z')) ) {
                                alt12=14;
                            }
                            else {
                                alt12=1;}
                        }
                        else {
                            alt12=14;}
                    }
                    else {
                        alt12=14;}
                }
                else {
                    alt12=14;}
            }
            else {
                alt12=14;}
            }
            break;
        case 'o':
            {
            int LA12_2 = input.LA(2);

            if ( (LA12_2=='r') ) {
                int LA12_16 = input.LA(3);

                if ( (LA12_16=='d') ) {
                    int LA12_29 = input.LA(4);

                    if ( (LA12_29=='e') ) {
                        int LA12_40 = input.LA(5);

                        if ( (LA12_40=='r') ) {
                            int LA12_51 = input.LA(6);

                            if ( (LA12_51=='B') ) {
                                int LA12_62 = input.LA(7);

                                if ( (LA12_62=='y') ) {
                                    int LA12_72 = input.LA(8);

                                    if ( ((LA12_72>='0' && LA12_72<='9')||(LA12_72>='A' && LA12_72<='Z')||LA12_72=='_'||(LA12_72>='a' && LA12_72<='z')) ) {
                                        alt12=14;
                                    }
                                    else {
                                        alt12=2;}
                                }
                                else {
                                    alt12=14;}
                            }
                            else {
                                alt12=14;}
                        }
                        else {
                            alt12=14;}
                    }
                    else {
                        alt12=14;}
                }
                else {
                    alt12=14;}
            }
            else {
                alt12=14;}
            }
            break;
        case 'a':
            {
            int LA12_3 = input.LA(2);

            if ( (LA12_3=='s') ) {
                int LA12_17 = input.LA(3);

                if ( (LA12_17=='s') ) {
                    int LA12_30 = input.LA(4);

                    if ( (LA12_30=='o') ) {
                        int LA12_41 = input.LA(5);

                        if ( (LA12_41=='c') ) {
                            int LA12_52 = input.LA(6);

                            if ( (LA12_52=='i') ) {
                                int LA12_63 = input.LA(7);

                                if ( (LA12_63=='a') ) {
                                    int LA12_73 = input.LA(8);

                                    if ( (LA12_73=='t') ) {
                                        int LA12_83 = input.LA(9);

                                        if ( (LA12_83=='i') ) {
                                            int LA12_91 = input.LA(10);

                                            if ( (LA12_91=='o') ) {
                                                int LA12_98 = input.LA(11);

                                                if ( (LA12_98=='n') ) {
                                                    int LA12_104 = input.LA(12);

                                                    if ( (LA12_104=='s') ) {
                                                        int LA12_108 = input.LA(13);

                                                        if ( ((LA12_108>='0' && LA12_108<='9')||(LA12_108>='A' && LA12_108<='Z')||LA12_108=='_'||(LA12_108>='a' && LA12_108<='z')) ) {
                                                            alt12=14;
                                                        }
                                                        else {
                                                            alt12=3;}
                                                    }
                                                    else {
                                                        alt12=14;}
                                                }
                                                else {
                                                    alt12=14;}
                                            }
                                            else {
                                                alt12=14;}
                                        }
                                        else {
                                            alt12=14;}
                                    }
                                    else {
                                        alt12=14;}
                                }
                                else {
                                    alt12=14;}
                            }
                            else {
                                alt12=14;}
                        }
                        else {
                            alt12=14;}
                    }
                    else {
                        alt12=14;}
                }
                else {
                    alt12=14;}
            }
            else {
                alt12=14;}
            }
            break;
        case 't':
            {
            switch ( input.LA(2) ) {
            case 'y':
                {
                int LA12_18 = input.LA(3);

                if ( (LA12_18=='p') ) {
                    int LA12_31 = input.LA(4);

                    if ( (LA12_31=='e') ) {
                        int LA12_42 = input.LA(5);

                        if ( ((LA12_42>='0' && LA12_42<='9')||(LA12_42>='A' && LA12_42<='Z')||LA12_42=='_'||(LA12_42>='a' && LA12_42<='z')) ) {
                            alt12=14;
                        }
                        else {
                            alt12=5;}
                    }
                    else {
                        alt12=14;}
                }
                else {
                    alt12=14;}
                }
                break;
            case 'a':
                {
                int LA12_19 = input.LA(3);

                if ( (LA12_19=='b') ) {
                    int LA12_32 = input.LA(4);

                    if ( (LA12_32=='l') ) {
                        int LA12_43 = input.LA(5);

                        if ( (LA12_43=='e') ) {
                            int LA12_54 = input.LA(6);

                            if ( ((LA12_54>='0' && LA12_54<='9')||(LA12_54>='A' && LA12_54<='Z')||LA12_54=='_'||(LA12_54>='a' && LA12_54<='z')) ) {
                                alt12=14;
                            }
                            else {
                                alt12=4;}
                        }
                        else {
                            alt12=14;}
                    }
                    else {
                        alt12=14;}
                }
                else {
                    alt12=14;}
                }
                break;
            case 'h':
                {
                int LA12_20 = input.LA(3);

                if ( (LA12_20=='r') ) {
                    int LA12_33 = input.LA(4);

                    if ( (LA12_33=='o') ) {
                        int LA12_44 = input.LA(5);

                        if ( (LA12_44=='u') ) {
                            int LA12_55 = input.LA(6);

                            if ( (LA12_55=='g') ) {
                                int LA12_65 = input.LA(7);

                                if ( (LA12_65=='h') ) {
                                    int LA12_74 = input.LA(8);

                                    if ( ((LA12_74>='0' && LA12_74<='9')||(LA12_74>='A' && LA12_74<='Z')||LA12_74=='_'||(LA12_74>='a' && LA12_74<='z')) ) {
                                        alt12=14;
                                    }
                                    else {
                                        alt12=12;}
                                }
                                else {
                                    alt12=14;}
                            }
                            else {
                                alt12=14;}
                        }
                        else {
                            alt12=14;}
                    }
                    else {
                        alt12=14;}
                }
                else {
                    alt12=14;}
                }
                break;
            default:
                alt12=14;}

            }
            break;
        case 'M':
            {
            int LA12_5 = input.LA(2);

            if ( (LA12_5=='a') ) {
                int LA12_21 = input.LA(3);

                if ( (LA12_21=='n') ) {
                    int LA12_34 = input.LA(4);

                    if ( (LA12_34=='y') ) {
                        int LA12_45 = input.LA(5);

                        if ( (LA12_45=='T') ) {
                            int LA12_56 = input.LA(6);

                            if ( (LA12_56=='o') ) {
                                switch ( input.LA(7) ) {
                                case 'O':
                                    {
                                    int LA12_75 = input.LA(8);

                                    if ( (LA12_75=='n') ) {
                                        int LA12_85 = input.LA(9);

                                        if ( (LA12_85=='e') ) {
                                            int LA12_92 = input.LA(10);

                                            if ( ((LA12_92>='0' && LA12_92<='9')||(LA12_92>='A' && LA12_92<='Z')||LA12_92=='_'||(LA12_92>='a' && LA12_92<='z')) ) {
                                                alt12=14;
                                            }
                                            else {
                                                alt12=8;}
                                        }
                                        else {
                                            alt12=14;}
                                    }
                                    else {
                                        alt12=14;}
                                    }
                                    break;
                                case 'M':
                                    {
                                    int LA12_76 = input.LA(8);

                                    if ( (LA12_76=='a') ) {
                                        int LA12_86 = input.LA(9);

                                        if ( (LA12_86=='n') ) {
                                            int LA12_93 = input.LA(10);

                                            if ( (LA12_93=='y') ) {
                                                int LA12_100 = input.LA(11);

                                                if ( ((LA12_100>='0' && LA12_100<='9')||(LA12_100>='A' && LA12_100<='Z')||LA12_100=='_'||(LA12_100>='a' && LA12_100<='z')) ) {
                                                    alt12=14;
                                                }
                                                else {
                                                    alt12=6;}
                                            }
                                            else {
                                                alt12=14;}
                                        }
                                        else {
                                            alt12=14;}
                                    }
                                    else {
                                        alt12=14;}
                                    }
                                    break;
                                default:
                                    alt12=14;}

                            }
                            else {
                                alt12=14;}
                        }
                        else {
                            alt12=14;}
                    }
                    else {
                        alt12=14;}
                }
                else {
                    alt12=14;}
            }
            else {
                alt12=14;}
            }
            break;
        case 'O':
            {
            int LA12_6 = input.LA(2);

            if ( (LA12_6=='n') ) {
                int LA12_22 = input.LA(3);

                if ( (LA12_22=='e') ) {
                    int LA12_35 = input.LA(4);

                    if ( (LA12_35=='T') ) {
                        int LA12_46 = input.LA(5);

                        if ( (LA12_46=='o') ) {
                            switch ( input.LA(6) ) {
                            case 'O':
                                {
                                int LA12_67 = input.LA(7);

                                if ( (LA12_67=='n') ) {
                                    int LA12_77 = input.LA(8);

                                    if ( (LA12_77=='e') ) {
                                        int LA12_87 = input.LA(9);

                                        if ( ((LA12_87>='0' && LA12_87<='9')||(LA12_87>='A' && LA12_87<='Z')||LA12_87=='_'||(LA12_87>='a' && LA12_87<='z')) ) {
                                            alt12=14;
                                        }
                                        else {
                                            alt12=9;}
                                    }
                                    else {
                                        alt12=14;}
                                }
                                else {
                                    alt12=14;}
                                }
                                break;
                            case 'M':
                                {
                                int LA12_68 = input.LA(7);

                                if ( (LA12_68=='a') ) {
                                    int LA12_78 = input.LA(8);

                                    if ( (LA12_78=='n') ) {
                                        int LA12_88 = input.LA(9);

                                        if ( (LA12_88=='y') ) {
                                            int LA12_95 = input.LA(10);

                                            if ( ((LA12_95>='0' && LA12_95<='9')||(LA12_95>='A' && LA12_95<='Z')||LA12_95=='_'||(LA12_95>='a' && LA12_95<='z')) ) {
                                                alt12=14;
                                            }
                                            else {
                                                alt12=7;}
                                        }
                                        else {
                                            alt12=14;}
                                    }
                                    else {
                                        alt12=14;}
                                }
                                else {
                                    alt12=14;}
                                }
                                break;
                            default:
                                alt12=14;}

                        }
                        else {
                            alt12=14;}
                    }
                    else {
                        alt12=14;}
                }
                else {
                    alt12=14;}
            }
            else {
                alt12=14;}
            }
            break;
        case 'c':
            {
            int LA12_7 = input.LA(2);

            if ( (LA12_7=='o') ) {
                int LA12_23 = input.LA(3);

                if ( (LA12_23=='l') ) {
                    int LA12_36 = input.LA(4);

                    if ( (LA12_36=='u') ) {
                        int LA12_47 = input.LA(5);

                        if ( (LA12_47=='m') ) {
                            int LA12_58 = input.LA(6);

                            if ( (LA12_58=='n') ) {
                                int LA12_69 = input.LA(7);

                                if ( ((LA12_69>='0' && LA12_69<='9')||(LA12_69>='A' && LA12_69<='Z')||LA12_69=='_'||(LA12_69>='a' && LA12_69<='z')) ) {
                                    alt12=14;
                                }
                                else {
                                    alt12=10;}
                            }
                            else {
                                alt12=14;}
                        }
                        else {
                            alt12=14;}
                    }
                    else {
                        alt12=14;}
                }
                else {
                    alt12=14;}
            }
            else {
                alt12=14;}
            }
            break;
        case 'r':
            {
            int LA12_8 = input.LA(2);

            if ( (LA12_8=='e') ) {
                int LA12_24 = input.LA(3);

                if ( (LA12_24=='f') ) {
                    int LA12_37 = input.LA(4);

                    if ( (LA12_37=='e') ) {
                        int LA12_48 = input.LA(5);

                        if ( (LA12_48=='r') ) {
                            int LA12_59 = input.LA(6);

                            if ( (LA12_59=='e') ) {
                                int LA12_70 = input.LA(7);

                                if ( (LA12_70=='n') ) {
                                    int LA12_80 = input.LA(8);

                                    if ( (LA12_80=='c') ) {
                                        int LA12_89 = input.LA(9);

                                        if ( (LA12_89=='e') ) {
                                            int LA12_96 = input.LA(10);

                                            if ( (LA12_96=='d') ) {
                                                int LA12_102 = input.LA(11);

                                                if ( (LA12_102=='C') ) {
                                                    int LA12_106 = input.LA(12);

                                                    if ( (LA12_106=='o') ) {
                                                        int LA12_109 = input.LA(13);

                                                        if ( (LA12_109=='l') ) {
                                                            int LA12_112 = input.LA(14);

                                                            if ( (LA12_112=='u') ) {
                                                                int LA12_114 = input.LA(15);

                                                                if ( (LA12_114=='m') ) {
                                                                    int LA12_116 = input.LA(16);

                                                                    if ( (LA12_116=='n') ) {
                                                                        int LA12_117 = input.LA(17);

                                                                        if ( ((LA12_117>='0' && LA12_117<='9')||(LA12_117>='A' && LA12_117<='Z')||LA12_117=='_'||(LA12_117>='a' && LA12_117<='z')) ) {
                                                                            alt12=14;
                                                                        }
                                                                        else {
                                                                            alt12=11;}
                                                                    }
                                                                    else {
                                                                        alt12=14;}
                                                                }
                                                                else {
                                                                    alt12=14;}
                                                            }
                                                            else {
                                                                alt12=14;}
                                                        }
                                                        else {
                                                            alt12=14;}
                                                    }
                                                    else {
                                                        alt12=14;}
                                                }
                                                else {
                                                    alt12=14;}
                                            }
                                            else {
                                                alt12=14;}
                                        }
                                        else {
                                            alt12=14;}
                                    }
                                    else {
                                        alt12=14;}
                                }
                                else {
                                    alt12=14;}
                            }
                            else {
                                alt12=14;}
                        }
                        else {
                            alt12=14;}
                    }
                    else {
                        alt12=14;}
                }
                else {
                    alt12=14;}
            }
            else {
                alt12=14;}
            }
            break;
        case 'i':
            {
            int LA12_9 = input.LA(2);

            if ( (LA12_9=='n') ) {
                int LA12_25 = input.LA(3);

                if ( (LA12_25=='v') ) {
                    int LA12_38 = input.LA(4);

                    if ( (LA12_38=='e') ) {
                        int LA12_49 = input.LA(5);

                        if ( (LA12_49=='r') ) {
                            int LA12_60 = input.LA(6);

                            if ( (LA12_60=='s') ) {
                                int LA12_71 = input.LA(7);

                                if ( (LA12_71=='e') ) {
                                    int LA12_81 = input.LA(8);

                                    if ( (LA12_81=='C') ) {
                                        int LA12_90 = input.LA(9);

                                        if ( (LA12_90=='o') ) {
                                            int LA12_97 = input.LA(10);

                                            if ( (LA12_97=='l') ) {
                                                int LA12_103 = input.LA(11);

                                                if ( (LA12_103=='u') ) {
                                                    int LA12_107 = input.LA(12);

                                                    if ( (LA12_107=='m') ) {
                                                        int LA12_110 = input.LA(13);

                                                        if ( (LA12_110=='n') ) {
                                                            int LA12_113 = input.LA(14);

                                                            if ( ((LA12_113>='0' && LA12_113<='9')||(LA12_113>='A' && LA12_113<='Z')||LA12_113=='_'||(LA12_113>='a' && LA12_113<='z')) ) {
                                                                alt12=14;
                                                            }
                                                            else {
                                                                alt12=13;}
                                                        }
                                                        else {
                                                            alt12=14;}
                                                    }
                                                    else {
                                                        alt12=14;}
                                                }
                                                else {
                                                    alt12=14;}
                                            }
                                            else {
                                                alt12=14;}
                                        }
                                        else {
                                            alt12=14;}
                                    }
                                    else {
                                        alt12=14;}
                                }
                                else {
                                    alt12=14;}
                            }
                            else {
                                alt12=14;}
                        }
                        else {
                            alt12=14;}
                    }
                    else {
                        alt12=14;}
                }
                else {
                    alt12=14;}
            }
            else {
                alt12=14;}
            }
            break;
        case 'A':
        case 'B':
        case 'C':
        case 'D':
        case 'E':
        case 'F':
        case 'G':
        case 'H':
        case 'I':
        case 'J':
        case 'K':
        case 'L':
        case 'N':
        case 'P':
        case 'Q':
        case 'R':
        case 'S':
        case 'T':
        case 'U':
        case 'V':
        case 'W':
        case 'X':
        case 'Y':
        case 'Z':
        case '^':
        case '_':
        case 'b':
        case 'd':
        case 'e':
        case 'f':
        case 'g':
        case 'h':
        case 'j':
        case 'k':
        case 'l':
        case 'm':
        case 'n':
        case 'p':
        case 's':
        case 'u':
        case 'v':
        case 'w':
        case 'x':
        case 'y':
        case 'z':
            {
            alt12=14;
            }
            break;
        case '\"':
        case '\'':
            {
            alt12=15;
            }
            break;
        case '-':
        case '0':
        case '1':
        case '2':
        case '3':
        case '4':
        case '5':
        case '6':
        case '7':
        case '8':
        case '9':
            {
            alt12=16;
            }
            break;
        case '\t':
        case '\n':
        case '\r':
        case ' ':
            {
            alt12=17;
            }
            break;
        case '/':
            {
            int LA12_14 = input.LA(2);

            if ( (LA12_14=='/') ) {
                alt12=19;
            }
            else if ( (LA12_14=='*') ) {
                alt12=18;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("1:1: Tokens : ( T10 | T11 | T12 | T13 | T14 | T15 | T16 | T17 | T18 | T19 | T20 | T21 | T22 | RULE_ID | RULE_STRING | RULE_INT | RULE_WS | RULE_ML_COMMENT | RULE_SL_COMMENT );", 12, 14, input);

                throw nvae;
            }
            }
            break;
        default:
            NoViableAltException nvae =
                new NoViableAltException("1:1: Tokens : ( T10 | T11 | T12 | T13 | T14 | T15 | T16 | T17 | T18 | T19 | T20 | T21 | T22 | RULE_ID | RULE_STRING | RULE_INT | RULE_WS | RULE_ML_COMMENT | RULE_SL_COMMENT );", 12, 0, input);

            throw nvae;
        }

        switch (alt12) {
            case 1 :
                // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:1:10: T10
                {
                mT10(); 

                }
                break;
            case 2 :
                // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:1:14: T11
                {
                mT11(); 

                }
                break;
            case 3 :
                // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:1:18: T12
                {
                mT12(); 

                }
                break;
            case 4 :
                // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:1:22: T13
                {
                mT13(); 

                }
                break;
            case 5 :
                // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:1:26: T14
                {
                mT14(); 

                }
                break;
            case 6 :
                // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:1:30: T15
                {
                mT15(); 

                }
                break;
            case 7 :
                // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:1:34: T16
                {
                mT16(); 

                }
                break;
            case 8 :
                // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:1:38: T17
                {
                mT17(); 

                }
                break;
            case 9 :
                // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:1:42: T18
                {
                mT18(); 

                }
                break;
            case 10 :
                // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:1:46: T19
                {
                mT19(); 

                }
                break;
            case 11 :
                // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:1:50: T20
                {
                mT20(); 

                }
                break;
            case 12 :
                // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:1:54: T21
                {
                mT21(); 

                }
                break;
            case 13 :
                // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:1:58: T22
                {
                mT22(); 

                }
                break;
            case 14 :
                // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:1:62: RULE_ID
                {
                mRULE_ID(); 

                }
                break;
            case 15 :
                // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:1:70: RULE_STRING
                {
                mRULE_STRING(); 

                }
                break;
            case 16 :
                // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:1:82: RULE_INT
                {
                mRULE_INT(); 

                }
                break;
            case 17 :
                // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:1:91: RULE_WS
                {
                mRULE_WS(); 

                }
                break;
            case 18 :
                // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:1:99: RULE_ML_COMMENT
                {
                mRULE_ML_COMMENT(); 

                }
                break;
            case 19 :
                // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:1:115: RULE_SL_COMMENT
                {
                mRULE_SL_COMMENT(); 

                }
                break;

        }

    }


 

}