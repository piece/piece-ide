grammar debugMeWithAntlrWorks;
 options{backtrack=true; memoize=true;} 



parse :
	 result=ruleMapper EOF
;

ruleMapper  :
(temp_methods=ruleMethod )*
;

ruleMethod  :
((temp_name=RULE_ID )

(temp_methodFeatures=ruleQuery )?

(temp_methodFeatures=ruleOrderBy )?

(temp_methodFeatures=ruleAssociations )?
)
;

ruleQuery  :
((temp_name='query' )

(temp_value=RULE_STRING )
)
;

ruleOrderBy  :
((temp_name='orderBy' )

(temp_value=RULE_STRING )
)
;

ruleAssociations  :
((temp_name='associations' )

(temp_associations=ruleAssociation )*
)
;

ruleAssociation  :
((temp_name=RULE_ID )

(temp_associationFeatures=ruleTable )

(temp_associationFeatures=ruleType )

(temp_associationFeatures=ruleColumn )?

(temp_associationFeatures=ruleReferencedColumn )?

(temp_associationFeatures=ruleThrough )?

(temp_associationFeatures=ruleOrderBy )?
)
;

ruleTable  :
((temp_name='table' )

(temp_value=RULE_STRING )
)
;

ruleType  :
((temp_name='type' )

(temp_value=ruleTypeRule )
)
;

ruleTypeRule  :
(('ManyToMany')
	|
('OneToMany')
	|
('ManyToOne')
	|
('OneToOne')
)
;

ruleColumn  :
((temp_name='column' )

(temp_value=RULE_STRING )
)
;

ruleReferencedColumn  :
((temp_name='referencedColumn' )

(temp_value=RULE_STRING )
)
;

ruleThrough  :
((temp_name='through' )

(temp_throuthFeatures=ruleThroughTable )

(temp_throuthFeatures=ruleThroughColumn )?

(temp_throuthFeatures=ruleThroughReferencedColumn )?

(temp_throuthFeatures=ruleThroughInverseColumn )?
)
;

ruleThroughTable  :
((temp_name='table' )

(temp_value=RULE_STRING )
)
;

ruleThroughColumn  :
((temp_name='column' )

(temp_value=RULE_STRING )
)
;

ruleThroughReferencedColumn  :
((temp_name='referencedColumn' )

(temp_value=RULE_STRING )
)
;

ruleThroughInverseColumn  :
((temp_name='inverseColumn' )

(temp_value=RULE_STRING )
)
;

RULE_ID :

	 ('^')?('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*
	 
;

RULE_STRING :

	 '"' ( '\\' ('b'|'t'|'n'|'f'|'r'|'\"'|'\''|'\\') | ~('\\'|'"') )* '"' |
	 '\'' ( '\\' ('b'|'t'|'n'|'f'|'r'|'\"'|'\''|'\\') | ~('\\'|'\'') )* '\''
	 
;

RULE_INT :

	 ('-')?('0'..'9')+
	 
;

RULE_WS :

	 (' '|'\t'|'\r'|'\n')+ {$channel=HIDDEN;}
	 
;

RULE_ML_COMMENT :

	 '/*' ( options {greedy=false;} : . )* '*/' {$channel=HIDDEN;}
	 
;

RULE_SL_COMMENT :

	 '//' ~('\n'|'\r')* '\r'? '\n' {$channel=HIDDEN;}
	 
;

