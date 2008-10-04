lexer grammar Piece_ORM_Mapper;
@members {
	 private List<ErrorMsg> errors = new ArrayList<ErrorMsg>();
	public List<ErrorMsg> getErrors() {
		return errors;
	}

	public String getErrorMessage(RecognitionException e, String[] tokenNames) {
		String msg = super.getErrorMessage(e,tokenNames);
		errors.add(AntlrUtil.create(msg,e,tokenNames));
		return msg;
	}
}
@header {
package com.piece_framework.piece_ide.piece_orm.mapper.parser;

import org.openarchitectureware.xtext.parser.ErrorMsg;
import org.openarchitectureware.xtext.parser.impl.AntlrUtil;

}

T10 : 'query' ;
T11 : 'orderBy' ;
T12 : 'associations' ;
T13 : 'table' ;
T14 : 'type' ;
T15 : 'ManyToMany' ;
T16 : 'OneToMany' ;
T17 : 'ManyToOne' ;
T18 : 'OneToOne' ;
T19 : 'column' ;
T20 : 'referencedColumn' ;
T21 : 'through' ;
T22 : 'inverseColumn' ;

// $ANTLR src "..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g" 361
RULE_ID :

	 ('^')?('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*
	 
;

// $ANTLR src "..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g" 367
RULE_STRING :

	 '"' ( '\\' ('b'|'t'|'n'|'f'|'r'|'\"'|'\''|'\\') | ~('\\'|'"') )* '"' |
	 '\'' ( '\\' ('b'|'t'|'n'|'f'|'r'|'\"'|'\''|'\\') | ~('\\'|'\'') )* '\''
	 
;

// $ANTLR src "..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g" 374
RULE_INT :

	 ('-')?('0'..'9')+
	 
;

// $ANTLR src "..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g" 380
RULE_WS :

	 (' '|'\t'|'\r'|'\n')+ {$channel=HIDDEN;}
	 
;

// $ANTLR src "..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g" 386
RULE_ML_COMMENT :

	 '/*' ( options {greedy=false;} : . )* '*/' {$channel=HIDDEN;}
	 
;

// $ANTLR src "..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g" 392
RULE_SL_COMMENT :

	 '//' ~('\n'|'\r')* '\r'? '\n' {$channel=HIDDEN;}
	 
;

