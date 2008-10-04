grammar Piece_ORM_Mapper;
 options{backtrack=true; memoize=true;} 

@lexer::header {
package com.piece_framework.piece_ide.piece_orm.mapper.parser;

import org.openarchitectureware.xtext.parser.ErrorMsg;
import org.openarchitectureware.xtext.parser.impl.AntlrUtil;

}

@parser::header {
package com.piece_framework.piece_ide.piece_orm.mapper.parser;

import org.eclipse.emf.ecore.EObject;

import org.openarchitectureware.xtext.parser.impl.AntlrUtil;
import org.openarchitectureware.xtext.XtextFile;
import org.openarchitectureware.xtext.parser.impl.EcoreModelFactory;
import org.openarchitectureware.xtext.parser.ErrorMsg;
import org.openarchitectureware.xtext.parser.model.ParseTreeManager;
import org.openarchitectureware.xtext.parser.parsetree.Node;

import com.piece_framework.piece_ide.piece_orm.mapper.MetaModelRegistration;

}
@lexer::members {
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

@parser::members {

	private Token getLastToken() {
		return input.LT(-1);
	}
	private Token getNextToken() {
		return input.LT(1);
	}

	private int line() {
		Token t = getNextToken();
		if (t==null)
			return 1;
		return t.getLine();
	}

	private int start() {
		Token t = getNextToken();
		if (t==null)
			return 0;
		if (t instanceof CommonToken) {
			return ((CommonToken)t).getStartIndex();
		}
		return t.getTokenIndex();
	}

	private int end() {
		Token t = getLastToken();
		if (t==null)
			return 1;
		if (t instanceof CommonToken) {
			return ((CommonToken)t).getStopIndex()+1;
		}
		return t.getTokenIndex();
	}

	protected Object convert(Object arg) {
		if (arg instanceof org.antlr.runtime.Token) {
			Token t = (Token) arg;
			String s = t.getText();
			if (t.getType() == Piece_ORM_MapperLexer.RULE_ID && s.startsWith("^")) {
				return s.substring(1);
			} else if (t.getType()==Piece_ORM_MapperLexer.RULE_STRING) {
				return s.substring(1,s.length()-1);
			} else if (t.getType()==Piece_ORM_MapperLexer.RULE_INT) {
				return Integer.valueOf(s);
			}
			return s;
		}
		return arg;
	}


	private EcoreModelFactory factory = new EcoreModelFactory(MetaModelRegistration.getEPackage());
    private ParseTreeManager ptm = new ParseTreeManager();
	private XtextFile xtextfile = MetaModelRegistration.getXtextFile();
	
	{
		
	}

	public ParseTreeManager getResult() {
		return ptm;
	}

	private List<ErrorMsg> errors = new ArrayList<ErrorMsg>();
	public List<ErrorMsg> getErrors() {
		return errors;
	}

	@Override
		public void reportError(RecognitionException e) {
		String msg = super.getErrorMessage(e,tokenNames);
		errors.add(AntlrUtil.create(msg,e,tokenNames));
			ptm.addError(msg, e);
			ptm.ruleFinished(null, end());
		}

}


parse returns [Node r]:
	 result=ruleMapper EOF
{ptm.ruleFinished(result,end());$r = ptm.getCurrent();};

ruleMapper returns [EObject result] :
			{
				$result = factory.create("", "Mapper");
				ptm.setModelElement($result);
			 }
({ptm.invokeRule(((EObject)((EObject)xtextfile.eContents().get(0)).eContents().get(1)),line(),start());}temp_methods=ruleMethod {factory.add($result,"methods",convert(temp_methods),false); ptm.ruleFinished(temp_methods,end()); }
)*
;

ruleMethod returns [EObject result] :
			{
				$result = factory.create("", "Method");
				ptm.setModelElement($result);
			 }
(({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(1)).eContents().get(1)).eContents().get(0)),line(),start());}temp_name=RULE_ID {factory.set($result,"name",convert(temp_name),false); ptm.ruleFinished(temp_name,end()); }
)

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(1)).eContents().get(1)).eContents().get(1)),line(),start());}temp_methodFeatures=ruleQuery {factory.add($result,"methodFeatures",convert(temp_methodFeatures),false); ptm.ruleFinished(temp_methodFeatures,end()); }
)?

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(1)).eContents().get(1)).eContents().get(2)),line(),start());}temp_methodFeatures=ruleOrderBy {factory.add($result,"methodFeatures",convert(temp_methodFeatures),false); ptm.ruleFinished(temp_methodFeatures,end()); }
)?

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(1)).eContents().get(1)).eContents().get(3)),line(),start());}temp_methodFeatures=ruleAssociations {factory.add($result,"methodFeatures",convert(temp_methodFeatures),false); ptm.ruleFinished(temp_methodFeatures,end()); }
)?
)
;

ruleQuery returns [EObject result] :
			{
				$result = factory.create("", "Query");
				ptm.setModelElement($result);
			 }
(({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(2)).eContents().get(1)).eContents().get(0)),line(),start());}temp_name='query' {factory.set($result,"name",convert(temp_name),false); ptm.ruleFinished(temp_name,end()); }
)

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(2)).eContents().get(1)).eContents().get(1)),line(),start());}temp_value=RULE_STRING {factory.set($result,"value",convert(temp_value),false); ptm.ruleFinished(temp_value,end()); }
)
)
;

ruleOrderBy returns [EObject result] :
			{
				$result = factory.create("", "OrderBy");
				ptm.setModelElement($result);
			 }
(({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(3)).eContents().get(1)).eContents().get(0)),line(),start());}temp_name='orderBy' {factory.set($result,"name",convert(temp_name),false); ptm.ruleFinished(temp_name,end()); }
)

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(3)).eContents().get(1)).eContents().get(1)),line(),start());}temp_value=RULE_STRING {factory.set($result,"value",convert(temp_value),false); ptm.ruleFinished(temp_value,end()); }
)
)
;

ruleAssociations returns [EObject result] :
			{
				$result = factory.create("", "Associations");
				ptm.setModelElement($result);
			 }
(({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(4)).eContents().get(1)).eContents().get(0)),line(),start());}temp_name='associations' {factory.set($result,"name",convert(temp_name),false); ptm.ruleFinished(temp_name,end()); }
)

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(4)).eContents().get(1)).eContents().get(1)),line(),start());}temp_associations=ruleAssociation {factory.add($result,"associations",convert(temp_associations),false); ptm.ruleFinished(temp_associations,end()); }
)*
)
;

ruleAssociation returns [EObject result] :
			{
				$result = factory.create("", "Association");
				ptm.setModelElement($result);
			 }
(({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(5)).eContents().get(1)).eContents().get(0)),line(),start());}temp_name=RULE_ID {factory.set($result,"name",convert(temp_name),false); ptm.ruleFinished(temp_name,end()); }
)

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(5)).eContents().get(1)).eContents().get(1)),line(),start());}temp_associationFeatures=ruleTable {factory.add($result,"associationFeatures",convert(temp_associationFeatures),false); ptm.ruleFinished(temp_associationFeatures,end()); }
)

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(5)).eContents().get(1)).eContents().get(2)),line(),start());}temp_associationFeatures=ruleType {factory.add($result,"associationFeatures",convert(temp_associationFeatures),false); ptm.ruleFinished(temp_associationFeatures,end()); }
)

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(5)).eContents().get(1)).eContents().get(3)),line(),start());}temp_associationFeatures=ruleColumn {factory.add($result,"associationFeatures",convert(temp_associationFeatures),false); ptm.ruleFinished(temp_associationFeatures,end()); }
)?

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(5)).eContents().get(1)).eContents().get(4)),line(),start());}temp_associationFeatures=ruleReferencedColumn {factory.add($result,"associationFeatures",convert(temp_associationFeatures),false); ptm.ruleFinished(temp_associationFeatures,end()); }
)?

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(5)).eContents().get(1)).eContents().get(5)),line(),start());}temp_associationFeatures=ruleThrough {factory.add($result,"associationFeatures",convert(temp_associationFeatures),false); ptm.ruleFinished(temp_associationFeatures,end()); }
)?

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(5)).eContents().get(1)).eContents().get(6)),line(),start());}temp_associationFeatures=ruleOrderBy {factory.add($result,"associationFeatures",convert(temp_associationFeatures),false); ptm.ruleFinished(temp_associationFeatures,end()); }
)?
)
;

ruleTable returns [EObject result] :
			{
				$result = factory.create("", "Table");
				ptm.setModelElement($result);
			 }
(({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(6)).eContents().get(1)).eContents().get(0)),line(),start());}temp_name='table' {factory.set($result,"name",convert(temp_name),false); ptm.ruleFinished(temp_name,end()); }
)

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(6)).eContents().get(1)).eContents().get(1)),line(),start());}temp_value=RULE_STRING {factory.set($result,"value",convert(temp_value),false); ptm.ruleFinished(temp_value,end()); }
)
)
;

ruleType returns [EObject result] :
			{
				$result = factory.create("", "Type");
				ptm.setModelElement($result);
			 }
(({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(7)).eContents().get(1)).eContents().get(0)),line(),start());}temp_name='type' {factory.set($result,"name",convert(temp_name),false); ptm.ruleFinished(temp_name,end()); }
)

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(7)).eContents().get(1)).eContents().get(1)),line(),start());}temp_value=ruleTypeRule {factory.set($result,"value",convert(temp_value),false); ptm.ruleFinished(temp_value,end()); }
)
)
;

ruleTypeRule returns [EObject result] :
			{
				$result = factory.create("", "TypeRule");
				ptm.setModelElement($result);
			 }
(({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(8)).eContents().get(1)).eContents().get(0)),line(),start());}'ManyToMany'{ptm.ruleFinished(getLastToken(),end());})
	|
({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(8)).eContents().get(1)).eContents().get(1)),line(),start());}'OneToMany'{ptm.ruleFinished(getLastToken(),end());})
	|
({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(8)).eContents().get(1)).eContents().get(2)),line(),start());}'ManyToOne'{ptm.ruleFinished(getLastToken(),end());})
	|
({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(8)).eContents().get(1)).eContents().get(3)),line(),start());}'OneToOne'{ptm.ruleFinished(getLastToken(),end());})
)
;

ruleColumn returns [EObject result] :
			{
				$result = factory.create("", "Column");
				ptm.setModelElement($result);
			 }
(({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(9)).eContents().get(1)).eContents().get(0)),line(),start());}temp_name='column' {factory.set($result,"name",convert(temp_name),false); ptm.ruleFinished(temp_name,end()); }
)

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(9)).eContents().get(1)).eContents().get(1)),line(),start());}temp_value=RULE_STRING {factory.set($result,"value",convert(temp_value),false); ptm.ruleFinished(temp_value,end()); }
)
)
;

ruleReferencedColumn returns [EObject result] :
			{
				$result = factory.create("", "ReferencedColumn");
				ptm.setModelElement($result);
			 }
(({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(10)).eContents().get(1)).eContents().get(0)),line(),start());}temp_name='referencedColumn' {factory.set($result,"name",convert(temp_name),false); ptm.ruleFinished(temp_name,end()); }
)

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(10)).eContents().get(1)).eContents().get(1)),line(),start());}temp_value=RULE_STRING {factory.set($result,"value",convert(temp_value),false); ptm.ruleFinished(temp_value,end()); }
)
)
;

ruleThrough returns [EObject result] :
			{
				$result = factory.create("", "Through");
				ptm.setModelElement($result);
			 }
(({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(11)).eContents().get(1)).eContents().get(0)),line(),start());}temp_name='through' {factory.set($result,"name",convert(temp_name),false); ptm.ruleFinished(temp_name,end()); }
)

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(11)).eContents().get(1)).eContents().get(1)),line(),start());}temp_throuthFeatures=ruleThroughTable {factory.add($result,"throuthFeatures",convert(temp_throuthFeatures),false); ptm.ruleFinished(temp_throuthFeatures,end()); }
)

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(11)).eContents().get(1)).eContents().get(2)),line(),start());}temp_throuthFeatures=ruleThroughColumn {factory.add($result,"throuthFeatures",convert(temp_throuthFeatures),false); ptm.ruleFinished(temp_throuthFeatures,end()); }
)?

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(11)).eContents().get(1)).eContents().get(3)),line(),start());}temp_throuthFeatures=ruleThroughReferencedColumn {factory.add($result,"throuthFeatures",convert(temp_throuthFeatures),false); ptm.ruleFinished(temp_throuthFeatures,end()); }
)?

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(11)).eContents().get(1)).eContents().get(4)),line(),start());}temp_throuthFeatures=ruleThroughInverseColumn {factory.add($result,"throuthFeatures",convert(temp_throuthFeatures),false); ptm.ruleFinished(temp_throuthFeatures,end()); }
)?
)
;

ruleThroughTable returns [EObject result] :
			{
				$result = factory.create("", "ThroughTable");
				ptm.setModelElement($result);
			 }
(({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(12)).eContents().get(1)).eContents().get(0)),line(),start());}temp_name='table' {factory.set($result,"name",convert(temp_name),false); ptm.ruleFinished(temp_name,end()); }
)

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(12)).eContents().get(1)).eContents().get(1)),line(),start());}temp_value=RULE_STRING {factory.set($result,"value",convert(temp_value),false); ptm.ruleFinished(temp_value,end()); }
)
)
;

ruleThroughColumn returns [EObject result] :
			{
				$result = factory.create("", "ThroughColumn");
				ptm.setModelElement($result);
			 }
(({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(13)).eContents().get(1)).eContents().get(0)),line(),start());}temp_name='column' {factory.set($result,"name",convert(temp_name),false); ptm.ruleFinished(temp_name,end()); }
)

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(13)).eContents().get(1)).eContents().get(1)),line(),start());}temp_value=RULE_STRING {factory.set($result,"value",convert(temp_value),false); ptm.ruleFinished(temp_value,end()); }
)
)
;

ruleThroughReferencedColumn returns [EObject result] :
			{
				$result = factory.create("", "ThroughReferencedColumn");
				ptm.setModelElement($result);
			 }
(({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(14)).eContents().get(1)).eContents().get(0)),line(),start());}temp_name='referencedColumn' {factory.set($result,"name",convert(temp_name),false); ptm.ruleFinished(temp_name,end()); }
)

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(14)).eContents().get(1)).eContents().get(1)),line(),start());}temp_value=RULE_STRING {factory.set($result,"value",convert(temp_value),false); ptm.ruleFinished(temp_value,end()); }
)
)
;

ruleThroughInverseColumn returns [EObject result] :
			{
				$result = factory.create("", "ThroughInverseColumn");
				ptm.setModelElement($result);
			 }
(({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(15)).eContents().get(1)).eContents().get(0)),line(),start());}temp_name='inverseColumn' {factory.set($result,"name",convert(temp_name),false); ptm.ruleFinished(temp_name,end()); }
)

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(15)).eContents().get(1)).eContents().get(1)),line(),start());}temp_value=RULE_STRING {factory.set($result,"value",convert(temp_value),false); ptm.ruleFinished(temp_value,end()); }
)
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

