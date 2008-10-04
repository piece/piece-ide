// $ANTLR 3.0 ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g 2008-10-03 17:22:34

package com.piece_framework.piece_ide.piece_orm.mapper.parser;

import org.eclipse.emf.ecore.EObject;

import org.openarchitectureware.xtext.parser.impl.AntlrUtil;
import org.openarchitectureware.xtext.XtextFile;
import org.openarchitectureware.xtext.parser.impl.EcoreModelFactory;
import org.openarchitectureware.xtext.parser.ErrorMsg;
import org.openarchitectureware.xtext.parser.model.ParseTreeManager;
import org.openarchitectureware.xtext.parser.parsetree.Node;

import com.piece_framework.piece_ide.piece_orm.mapper.MetaModelRegistration;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
public class Piece_ORM_MapperParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_STRING", "RULE_INT", "RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "'query'", "'orderBy'", "'associations'", "'table'", "'type'", "'ManyToMany'", "'OneToMany'", "'ManyToOne'", "'OneToOne'", "'column'", "'referencedColumn'", "'through'", "'inverseColumn'"
    };
    public static final int RULE_ML_COMMENT=8;
    public static final int RULE_ID=4;
    public static final int RULE_WS=7;
    public static final int EOF=-1;
    public static final int RULE_INT=6;
    public static final int RULE_STRING=5;
    public static final int RULE_SL_COMMENT=9;

        public Piece_ORM_MapperParser(TokenStream input) {
            super(input);
            ruleMemo = new HashMap[32+1];
         }
        

    public String[] getTokenNames() { return tokenNames; }
    public String getGrammarFileName() { return "..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g"; }



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




    // $ANTLR start parse
    // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:121:1: parse returns [Node r] : result= ruleMapper EOF ;
    public Node parse() throws RecognitionException {
        Node r = null;
        int parse_StartIndex = input.index();
        EObject result = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 1) ) { return r; }
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:122:3: (result= ruleMapper EOF )
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:122:3: result= ruleMapper EOF
            {
            pushFollow(FOLLOW_ruleMapper_in_parse67);
            result=ruleMapper();
            _fsp--;
            if (failed) return r;
            match(input,EOF,FOLLOW_EOF_in_parse69); if (failed) return r;
            if ( backtracking==0 ) {
              ptm.ruleFinished(result,end());r = ptm.getCurrent();
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 1, parse_StartIndex); }
        }
        return r;
    }
    // $ANTLR end parse


    // $ANTLR start ruleMapper
    // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:125:1: ruleMapper returns [EObject result] : (temp_methods= ruleMethod )* ;
    public EObject ruleMapper() throws RecognitionException {
        EObject result = null;
        int ruleMapper_StartIndex = input.index();
        EObject temp_methods = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 2) ) { return result; }
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:126:4: ( (temp_methods= ruleMethod )* )
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:126:4: (temp_methods= ruleMethod )*
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "Mapper");
              				ptm.setModelElement(result);
              			 
            }
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:130:1: (temp_methods= ruleMethod )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==RULE_ID) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:130:2: temp_methods= ruleMethod
            	    {
            	    if ( backtracking==0 ) {
            	      ptm.invokeRule(((EObject)((EObject)xtextfile.eContents().get(0)).eContents().get(1)),line(),start());
            	    }
            	    pushFollow(FOLLOW_ruleMethod_in_ruleMapper92);
            	    temp_methods=ruleMethod();
            	    _fsp--;
            	    if (failed) return result;
            	    if ( backtracking==0 ) {
            	      factory.add(result,"methods",convert(temp_methods),false); ptm.ruleFinished(temp_methods,end()); 
            	    }

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 2, ruleMapper_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleMapper


    // $ANTLR start ruleMethod
    // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:134:1: ruleMethod returns [EObject result] : ( (temp_name= RULE_ID ) (temp_methodFeatures= ruleQuery )? (temp_methodFeatures= ruleOrderBy )? (temp_methodFeatures= ruleAssociations )? ) ;
    public EObject ruleMethod() throws RecognitionException {
        EObject result = null;
        int ruleMethod_StartIndex = input.index();
        Token temp_name=null;
        EObject temp_methodFeatures = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 3) ) { return result; }
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:135:4: ( ( (temp_name= RULE_ID ) (temp_methodFeatures= ruleQuery )? (temp_methodFeatures= ruleOrderBy )? (temp_methodFeatures= ruleAssociations )? ) )
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:135:4: ( (temp_name= RULE_ID ) (temp_methodFeatures= ruleQuery )? (temp_methodFeatures= ruleOrderBy )? (temp_methodFeatures= ruleAssociations )? )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "Method");
              				ptm.setModelElement(result);
              			 
            }
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:139:1: ( (temp_name= RULE_ID ) (temp_methodFeatures= ruleQuery )? (temp_methodFeatures= ruleOrderBy )? (temp_methodFeatures= ruleAssociations )? )
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:139:2: (temp_name= RULE_ID ) (temp_methodFeatures= ruleQuery )? (temp_methodFeatures= ruleOrderBy )? (temp_methodFeatures= ruleAssociations )?
            {
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:139:2: (temp_name= RULE_ID )
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:139:3: temp_name= RULE_ID
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(1)).eContents().get(1)).eContents().get(0)),line(),start());
            }
            temp_name=(Token)input.LT(1);
            match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleMethod120); if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"name",convert(temp_name),false); ptm.ruleFinished(temp_name,end()); 
            }

            }

            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:142:1: (temp_methodFeatures= ruleQuery )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==10) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:142:2: temp_methodFeatures= ruleQuery
                    {
                    if ( backtracking==0 ) {
                      ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(1)).eContents().get(1)).eContents().get(1)),line(),start());
                    }
                    pushFollow(FOLLOW_ruleQuery_in_ruleMethod131);
                    temp_methodFeatures=ruleQuery();
                    _fsp--;
                    if (failed) return result;
                    if ( backtracking==0 ) {
                      factory.add(result,"methodFeatures",convert(temp_methodFeatures),false); ptm.ruleFinished(temp_methodFeatures,end()); 
                    }

                    }
                    break;

            }

            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:145:1: (temp_methodFeatures= ruleOrderBy )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==11) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:145:2: temp_methodFeatures= ruleOrderBy
                    {
                    if ( backtracking==0 ) {
                      ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(1)).eContents().get(1)).eContents().get(2)),line(),start());
                    }
                    pushFollow(FOLLOW_ruleOrderBy_in_ruleMethod143);
                    temp_methodFeatures=ruleOrderBy();
                    _fsp--;
                    if (failed) return result;
                    if ( backtracking==0 ) {
                      factory.add(result,"methodFeatures",convert(temp_methodFeatures),false); ptm.ruleFinished(temp_methodFeatures,end()); 
                    }

                    }
                    break;

            }

            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:148:1: (temp_methodFeatures= ruleAssociations )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==12) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:148:2: temp_methodFeatures= ruleAssociations
                    {
                    if ( backtracking==0 ) {
                      ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(1)).eContents().get(1)).eContents().get(3)),line(),start());
                    }
                    pushFollow(FOLLOW_ruleAssociations_in_ruleMethod155);
                    temp_methodFeatures=ruleAssociations();
                    _fsp--;
                    if (failed) return result;
                    if ( backtracking==0 ) {
                      factory.add(result,"methodFeatures",convert(temp_methodFeatures),false); ptm.ruleFinished(temp_methodFeatures,end()); 
                    }

                    }
                    break;

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 3, ruleMethod_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleMethod


    // $ANTLR start ruleQuery
    // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:153:1: ruleQuery returns [EObject result] : ( (temp_name= 'query' ) (temp_value= RULE_STRING ) ) ;
    public EObject ruleQuery() throws RecognitionException {
        EObject result = null;
        int ruleQuery_StartIndex = input.index();
        Token temp_name=null;
        Token temp_value=null;

        try {
            if ( backtracking>0 && alreadyParsedRule(input, 4) ) { return result; }
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:154:4: ( ( (temp_name= 'query' ) (temp_value= RULE_STRING ) ) )
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:154:4: ( (temp_name= 'query' ) (temp_value= RULE_STRING ) )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "Query");
              				ptm.setModelElement(result);
              			 
            }
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:158:1: ( (temp_name= 'query' ) (temp_value= RULE_STRING ) )
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:158:2: (temp_name= 'query' ) (temp_value= RULE_STRING )
            {
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:158:2: (temp_name= 'query' )
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:158:3: temp_name= 'query'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(2)).eContents().get(1)).eContents().get(0)),line(),start());
            }
            temp_name=(Token)input.LT(1);
            match(input,10,FOLLOW_10_in_ruleQuery185); if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"name",convert(temp_name),false); ptm.ruleFinished(temp_name,end()); 
            }

            }

            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:161:1: (temp_value= RULE_STRING )
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:161:2: temp_value= RULE_STRING
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(2)).eContents().get(1)).eContents().get(1)),line(),start());
            }
            temp_value=(Token)input.LT(1);
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleQuery196); if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"value",convert(temp_value),false); ptm.ruleFinished(temp_value,end()); 
            }

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 4, ruleQuery_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleQuery


    // $ANTLR start ruleOrderBy
    // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:166:1: ruleOrderBy returns [EObject result] : ( (temp_name= 'orderBy' ) (temp_value= RULE_STRING ) ) ;
    public EObject ruleOrderBy() throws RecognitionException {
        EObject result = null;
        int ruleOrderBy_StartIndex = input.index();
        Token temp_name=null;
        Token temp_value=null;

        try {
            if ( backtracking>0 && alreadyParsedRule(input, 5) ) { return result; }
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:167:4: ( ( (temp_name= 'orderBy' ) (temp_value= RULE_STRING ) ) )
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:167:4: ( (temp_name= 'orderBy' ) (temp_value= RULE_STRING ) )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "OrderBy");
              				ptm.setModelElement(result);
              			 
            }
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:171:1: ( (temp_name= 'orderBy' ) (temp_value= RULE_STRING ) )
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:171:2: (temp_name= 'orderBy' ) (temp_value= RULE_STRING )
            {
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:171:2: (temp_name= 'orderBy' )
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:171:3: temp_name= 'orderBy'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(3)).eContents().get(1)).eContents().get(0)),line(),start());
            }
            temp_name=(Token)input.LT(1);
            match(input,11,FOLLOW_11_in_ruleOrderBy225); if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"name",convert(temp_name),false); ptm.ruleFinished(temp_name,end()); 
            }

            }

            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:174:1: (temp_value= RULE_STRING )
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:174:2: temp_value= RULE_STRING
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(3)).eContents().get(1)).eContents().get(1)),line(),start());
            }
            temp_value=(Token)input.LT(1);
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleOrderBy236); if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"value",convert(temp_value),false); ptm.ruleFinished(temp_value,end()); 
            }

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 5, ruleOrderBy_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleOrderBy


    // $ANTLR start ruleAssociations
    // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:179:1: ruleAssociations returns [EObject result] : ( (temp_name= 'associations' ) (temp_associations= ruleAssociation )* ) ;
    public EObject ruleAssociations() throws RecognitionException {
        EObject result = null;
        int ruleAssociations_StartIndex = input.index();
        Token temp_name=null;
        EObject temp_associations = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 6) ) { return result; }
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:180:4: ( ( (temp_name= 'associations' ) (temp_associations= ruleAssociation )* ) )
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:180:4: ( (temp_name= 'associations' ) (temp_associations= ruleAssociation )* )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "Associations");
              				ptm.setModelElement(result);
              			 
            }
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:184:1: ( (temp_name= 'associations' ) (temp_associations= ruleAssociation )* )
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:184:2: (temp_name= 'associations' ) (temp_associations= ruleAssociation )*
            {
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:184:2: (temp_name= 'associations' )
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:184:3: temp_name= 'associations'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(4)).eContents().get(1)).eContents().get(0)),line(),start());
            }
            temp_name=(Token)input.LT(1);
            match(input,12,FOLLOW_12_in_ruleAssociations265); if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"name",convert(temp_name),false); ptm.ruleFinished(temp_name,end()); 
            }

            }

            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:187:1: (temp_associations= ruleAssociation )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==RULE_ID) ) {
                    int LA5_2 = input.LA(2);

                    if ( (LA5_2==13) ) {
                        alt5=1;
                    }


                }


                switch (alt5) {
            	case 1 :
            	    // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:187:2: temp_associations= ruleAssociation
            	    {
            	    if ( backtracking==0 ) {
            	      ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(4)).eContents().get(1)).eContents().get(1)),line(),start());
            	    }
            	    pushFollow(FOLLOW_ruleAssociation_in_ruleAssociations276);
            	    temp_associations=ruleAssociation();
            	    _fsp--;
            	    if (failed) return result;
            	    if ( backtracking==0 ) {
            	      factory.add(result,"associations",convert(temp_associations),false); ptm.ruleFinished(temp_associations,end()); 
            	    }

            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 6, ruleAssociations_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleAssociations


    // $ANTLR start ruleAssociation
    // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:192:1: ruleAssociation returns [EObject result] : ( (temp_name= RULE_ID ) (temp_associationFeatures= ruleTable ) (temp_associationFeatures= ruleType ) (temp_associationFeatures= ruleColumn )? (temp_associationFeatures= ruleReferencedColumn )? (temp_associationFeatures= ruleThrough )? (temp_associationFeatures= ruleOrderBy )? ) ;
    public EObject ruleAssociation() throws RecognitionException {
        EObject result = null;
        int ruleAssociation_StartIndex = input.index();
        Token temp_name=null;
        EObject temp_associationFeatures = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 7) ) { return result; }
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:193:4: ( ( (temp_name= RULE_ID ) (temp_associationFeatures= ruleTable ) (temp_associationFeatures= ruleType ) (temp_associationFeatures= ruleColumn )? (temp_associationFeatures= ruleReferencedColumn )? (temp_associationFeatures= ruleThrough )? (temp_associationFeatures= ruleOrderBy )? ) )
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:193:4: ( (temp_name= RULE_ID ) (temp_associationFeatures= ruleTable ) (temp_associationFeatures= ruleType ) (temp_associationFeatures= ruleColumn )? (temp_associationFeatures= ruleReferencedColumn )? (temp_associationFeatures= ruleThrough )? (temp_associationFeatures= ruleOrderBy )? )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "Association");
              				ptm.setModelElement(result);
              			 
            }
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:197:1: ( (temp_name= RULE_ID ) (temp_associationFeatures= ruleTable ) (temp_associationFeatures= ruleType ) (temp_associationFeatures= ruleColumn )? (temp_associationFeatures= ruleReferencedColumn )? (temp_associationFeatures= ruleThrough )? (temp_associationFeatures= ruleOrderBy )? )
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:197:2: (temp_name= RULE_ID ) (temp_associationFeatures= ruleTable ) (temp_associationFeatures= ruleType ) (temp_associationFeatures= ruleColumn )? (temp_associationFeatures= ruleReferencedColumn )? (temp_associationFeatures= ruleThrough )? (temp_associationFeatures= ruleOrderBy )?
            {
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:197:2: (temp_name= RULE_ID )
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:197:3: temp_name= RULE_ID
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(5)).eContents().get(1)).eContents().get(0)),line(),start());
            }
            temp_name=(Token)input.LT(1);
            match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleAssociation306); if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"name",convert(temp_name),false); ptm.ruleFinished(temp_name,end()); 
            }

            }

            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:200:1: (temp_associationFeatures= ruleTable )
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:200:2: temp_associationFeatures= ruleTable
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(5)).eContents().get(1)).eContents().get(1)),line(),start());
            }
            pushFollow(FOLLOW_ruleTable_in_ruleAssociation317);
            temp_associationFeatures=ruleTable();
            _fsp--;
            if (failed) return result;
            if ( backtracking==0 ) {
              factory.add(result,"associationFeatures",convert(temp_associationFeatures),false); ptm.ruleFinished(temp_associationFeatures,end()); 
            }

            }

            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:203:1: (temp_associationFeatures= ruleType )
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:203:2: temp_associationFeatures= ruleType
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(5)).eContents().get(1)).eContents().get(2)),line(),start());
            }
            pushFollow(FOLLOW_ruleType_in_ruleAssociation328);
            temp_associationFeatures=ruleType();
            _fsp--;
            if (failed) return result;
            if ( backtracking==0 ) {
              factory.add(result,"associationFeatures",convert(temp_associationFeatures),false); ptm.ruleFinished(temp_associationFeatures,end()); 
            }

            }

            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:206:1: (temp_associationFeatures= ruleColumn )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==19) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:206:2: temp_associationFeatures= ruleColumn
                    {
                    if ( backtracking==0 ) {
                      ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(5)).eContents().get(1)).eContents().get(3)),line(),start());
                    }
                    pushFollow(FOLLOW_ruleColumn_in_ruleAssociation339);
                    temp_associationFeatures=ruleColumn();
                    _fsp--;
                    if (failed) return result;
                    if ( backtracking==0 ) {
                      factory.add(result,"associationFeatures",convert(temp_associationFeatures),false); ptm.ruleFinished(temp_associationFeatures,end()); 
                    }

                    }
                    break;

            }

            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:209:1: (temp_associationFeatures= ruleReferencedColumn )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==20) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:209:2: temp_associationFeatures= ruleReferencedColumn
                    {
                    if ( backtracking==0 ) {
                      ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(5)).eContents().get(1)).eContents().get(4)),line(),start());
                    }
                    pushFollow(FOLLOW_ruleReferencedColumn_in_ruleAssociation351);
                    temp_associationFeatures=ruleReferencedColumn();
                    _fsp--;
                    if (failed) return result;
                    if ( backtracking==0 ) {
                      factory.add(result,"associationFeatures",convert(temp_associationFeatures),false); ptm.ruleFinished(temp_associationFeatures,end()); 
                    }

                    }
                    break;

            }

            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:212:1: (temp_associationFeatures= ruleThrough )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==21) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:212:2: temp_associationFeatures= ruleThrough
                    {
                    if ( backtracking==0 ) {
                      ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(5)).eContents().get(1)).eContents().get(5)),line(),start());
                    }
                    pushFollow(FOLLOW_ruleThrough_in_ruleAssociation363);
                    temp_associationFeatures=ruleThrough();
                    _fsp--;
                    if (failed) return result;
                    if ( backtracking==0 ) {
                      factory.add(result,"associationFeatures",convert(temp_associationFeatures),false); ptm.ruleFinished(temp_associationFeatures,end()); 
                    }

                    }
                    break;

            }

            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:215:1: (temp_associationFeatures= ruleOrderBy )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==11) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:215:2: temp_associationFeatures= ruleOrderBy
                    {
                    if ( backtracking==0 ) {
                      ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(5)).eContents().get(1)).eContents().get(6)),line(),start());
                    }
                    pushFollow(FOLLOW_ruleOrderBy_in_ruleAssociation375);
                    temp_associationFeatures=ruleOrderBy();
                    _fsp--;
                    if (failed) return result;
                    if ( backtracking==0 ) {
                      factory.add(result,"associationFeatures",convert(temp_associationFeatures),false); ptm.ruleFinished(temp_associationFeatures,end()); 
                    }

                    }
                    break;

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 7, ruleAssociation_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleAssociation


    // $ANTLR start ruleTable
    // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:220:1: ruleTable returns [EObject result] : ( (temp_name= 'table' ) (temp_value= RULE_STRING ) ) ;
    public EObject ruleTable() throws RecognitionException {
        EObject result = null;
        int ruleTable_StartIndex = input.index();
        Token temp_name=null;
        Token temp_value=null;

        try {
            if ( backtracking>0 && alreadyParsedRule(input, 8) ) { return result; }
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:221:4: ( ( (temp_name= 'table' ) (temp_value= RULE_STRING ) ) )
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:221:4: ( (temp_name= 'table' ) (temp_value= RULE_STRING ) )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "Table");
              				ptm.setModelElement(result);
              			 
            }
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:225:1: ( (temp_name= 'table' ) (temp_value= RULE_STRING ) )
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:225:2: (temp_name= 'table' ) (temp_value= RULE_STRING )
            {
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:225:2: (temp_name= 'table' )
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:225:3: temp_name= 'table'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(6)).eContents().get(1)).eContents().get(0)),line(),start());
            }
            temp_name=(Token)input.LT(1);
            match(input,13,FOLLOW_13_in_ruleTable405); if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"name",convert(temp_name),false); ptm.ruleFinished(temp_name,end()); 
            }

            }

            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:228:1: (temp_value= RULE_STRING )
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:228:2: temp_value= RULE_STRING
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(6)).eContents().get(1)).eContents().get(1)),line(),start());
            }
            temp_value=(Token)input.LT(1);
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleTable416); if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"value",convert(temp_value),false); ptm.ruleFinished(temp_value,end()); 
            }

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 8, ruleTable_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleTable


    // $ANTLR start ruleType
    // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:233:1: ruleType returns [EObject result] : ( (temp_name= 'type' ) (temp_value= ruleTypeRule ) ) ;
    public EObject ruleType() throws RecognitionException {
        EObject result = null;
        int ruleType_StartIndex = input.index();
        Token temp_name=null;
        EObject temp_value = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 9) ) { return result; }
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:234:4: ( ( (temp_name= 'type' ) (temp_value= ruleTypeRule ) ) )
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:234:4: ( (temp_name= 'type' ) (temp_value= ruleTypeRule ) )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "Type");
              				ptm.setModelElement(result);
              			 
            }
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:238:1: ( (temp_name= 'type' ) (temp_value= ruleTypeRule ) )
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:238:2: (temp_name= 'type' ) (temp_value= ruleTypeRule )
            {
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:238:2: (temp_name= 'type' )
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:238:3: temp_name= 'type'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(7)).eContents().get(1)).eContents().get(0)),line(),start());
            }
            temp_name=(Token)input.LT(1);
            match(input,14,FOLLOW_14_in_ruleType445); if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"name",convert(temp_name),false); ptm.ruleFinished(temp_name,end()); 
            }

            }

            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:241:1: (temp_value= ruleTypeRule )
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:241:2: temp_value= ruleTypeRule
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(7)).eContents().get(1)).eContents().get(1)),line(),start());
            }
            pushFollow(FOLLOW_ruleTypeRule_in_ruleType456);
            temp_value=ruleTypeRule();
            _fsp--;
            if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"value",convert(temp_value),false); ptm.ruleFinished(temp_value,end()); 
            }

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 9, ruleType_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleType


    // $ANTLR start ruleTypeRule
    // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:246:1: ruleTypeRule returns [EObject result] : ( ( 'ManyToMany' ) | ( 'OneToMany' ) | ( 'ManyToOne' ) | ( 'OneToOne' ) ) ;
    public EObject ruleTypeRule() throws RecognitionException {
        EObject result = null;
        int ruleTypeRule_StartIndex = input.index();
        try {
            if ( backtracking>0 && alreadyParsedRule(input, 10) ) { return result; }
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:247:4: ( ( ( 'ManyToMany' ) | ( 'OneToMany' ) | ( 'ManyToOne' ) | ( 'OneToOne' ) ) )
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:247:4: ( ( 'ManyToMany' ) | ( 'OneToMany' ) | ( 'ManyToOne' ) | ( 'OneToOne' ) )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "TypeRule");
              				ptm.setModelElement(result);
              			 
            }
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:251:1: ( ( 'ManyToMany' ) | ( 'OneToMany' ) | ( 'ManyToOne' ) | ( 'OneToOne' ) )
            int alt10=4;
            switch ( input.LA(1) ) {
            case 15:
                {
                alt10=1;
                }
                break;
            case 16:
                {
                alt10=2;
                }
                break;
            case 17:
                {
                alt10=3;
                }
                break;
            case 18:
                {
                alt10=4;
                }
                break;
            default:
                if (backtracking>0) {failed=true; return result;}
                NoViableAltException nvae =
                    new NoViableAltException("251:1: ( ( 'ManyToMany' ) | ( 'OneToMany' ) | ( 'ManyToOne' ) | ( 'OneToOne' ) )", 10, 0, input);

                throw nvae;
            }

            switch (alt10) {
                case 1 :
                    // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:251:2: ( 'ManyToMany' )
                    {
                    // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:251:2: ( 'ManyToMany' )
                    // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:251:3: 'ManyToMany'
                    {
                    if ( backtracking==0 ) {
                      ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(8)).eContents().get(1)).eContents().get(0)),line(),start());
                    }
                    match(input,15,FOLLOW_15_in_ruleTypeRule483); if (failed) return result;
                    if ( backtracking==0 ) {
                      ptm.ruleFinished(getLastToken(),end());
                    }

                    }


                    }
                    break;
                case 2 :
                    // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:253:1: ( 'OneToMany' )
                    {
                    // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:253:1: ( 'OneToMany' )
                    // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:253:2: 'OneToMany'
                    {
                    if ( backtracking==0 ) {
                      ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(8)).eContents().get(1)).eContents().get(1)),line(),start());
                    }
                    match(input,16,FOLLOW_16_in_ruleTypeRule492); if (failed) return result;
                    if ( backtracking==0 ) {
                      ptm.ruleFinished(getLastToken(),end());
                    }

                    }


                    }
                    break;
                case 3 :
                    // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:255:1: ( 'ManyToOne' )
                    {
                    // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:255:1: ( 'ManyToOne' )
                    // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:255:2: 'ManyToOne'
                    {
                    if ( backtracking==0 ) {
                      ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(8)).eContents().get(1)).eContents().get(2)),line(),start());
                    }
                    match(input,17,FOLLOW_17_in_ruleTypeRule501); if (failed) return result;
                    if ( backtracking==0 ) {
                      ptm.ruleFinished(getLastToken(),end());
                    }

                    }


                    }
                    break;
                case 4 :
                    // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:257:1: ( 'OneToOne' )
                    {
                    // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:257:1: ( 'OneToOne' )
                    // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:257:2: 'OneToOne'
                    {
                    if ( backtracking==0 ) {
                      ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(8)).eContents().get(1)).eContents().get(3)),line(),start());
                    }
                    match(input,18,FOLLOW_18_in_ruleTypeRule510); if (failed) return result;
                    if ( backtracking==0 ) {
                      ptm.ruleFinished(getLastToken(),end());
                    }

                    }


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 10, ruleTypeRule_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleTypeRule


    // $ANTLR start ruleColumn
    // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:261:1: ruleColumn returns [EObject result] : ( (temp_name= 'column' ) (temp_value= RULE_STRING ) ) ;
    public EObject ruleColumn() throws RecognitionException {
        EObject result = null;
        int ruleColumn_StartIndex = input.index();
        Token temp_name=null;
        Token temp_value=null;

        try {
            if ( backtracking>0 && alreadyParsedRule(input, 11) ) { return result; }
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:262:4: ( ( (temp_name= 'column' ) (temp_value= RULE_STRING ) ) )
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:262:4: ( (temp_name= 'column' ) (temp_value= RULE_STRING ) )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "Column");
              				ptm.setModelElement(result);
              			 
            }
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:266:1: ( (temp_name= 'column' ) (temp_value= RULE_STRING ) )
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:266:2: (temp_name= 'column' ) (temp_value= RULE_STRING )
            {
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:266:2: (temp_name= 'column' )
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:266:3: temp_name= 'column'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(9)).eContents().get(1)).eContents().get(0)),line(),start());
            }
            temp_name=(Token)input.LT(1);
            match(input,19,FOLLOW_19_in_ruleColumn537); if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"name",convert(temp_name),false); ptm.ruleFinished(temp_name,end()); 
            }

            }

            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:269:1: (temp_value= RULE_STRING )
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:269:2: temp_value= RULE_STRING
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(9)).eContents().get(1)).eContents().get(1)),line(),start());
            }
            temp_value=(Token)input.LT(1);
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleColumn548); if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"value",convert(temp_value),false); ptm.ruleFinished(temp_value,end()); 
            }

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 11, ruleColumn_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleColumn


    // $ANTLR start ruleReferencedColumn
    // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:274:1: ruleReferencedColumn returns [EObject result] : ( (temp_name= 'referencedColumn' ) (temp_value= RULE_STRING ) ) ;
    public EObject ruleReferencedColumn() throws RecognitionException {
        EObject result = null;
        int ruleReferencedColumn_StartIndex = input.index();
        Token temp_name=null;
        Token temp_value=null;

        try {
            if ( backtracking>0 && alreadyParsedRule(input, 12) ) { return result; }
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:275:4: ( ( (temp_name= 'referencedColumn' ) (temp_value= RULE_STRING ) ) )
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:275:4: ( (temp_name= 'referencedColumn' ) (temp_value= RULE_STRING ) )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "ReferencedColumn");
              				ptm.setModelElement(result);
              			 
            }
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:279:1: ( (temp_name= 'referencedColumn' ) (temp_value= RULE_STRING ) )
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:279:2: (temp_name= 'referencedColumn' ) (temp_value= RULE_STRING )
            {
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:279:2: (temp_name= 'referencedColumn' )
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:279:3: temp_name= 'referencedColumn'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(10)).eContents().get(1)).eContents().get(0)),line(),start());
            }
            temp_name=(Token)input.LT(1);
            match(input,20,FOLLOW_20_in_ruleReferencedColumn577); if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"name",convert(temp_name),false); ptm.ruleFinished(temp_name,end()); 
            }

            }

            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:282:1: (temp_value= RULE_STRING )
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:282:2: temp_value= RULE_STRING
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(10)).eContents().get(1)).eContents().get(1)),line(),start());
            }
            temp_value=(Token)input.LT(1);
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleReferencedColumn588); if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"value",convert(temp_value),false); ptm.ruleFinished(temp_value,end()); 
            }

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 12, ruleReferencedColumn_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleReferencedColumn


    // $ANTLR start ruleThrough
    // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:287:1: ruleThrough returns [EObject result] : ( (temp_name= 'through' ) (temp_throuthFeatures= ruleThroughTable ) (temp_throuthFeatures= ruleThroughColumn )? (temp_throuthFeatures= ruleThroughReferencedColumn )? (temp_throuthFeatures= ruleThroughInverseColumn )? ) ;
    public EObject ruleThrough() throws RecognitionException {
        EObject result = null;
        int ruleThrough_StartIndex = input.index();
        Token temp_name=null;
        EObject temp_throuthFeatures = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 13) ) { return result; }
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:288:4: ( ( (temp_name= 'through' ) (temp_throuthFeatures= ruleThroughTable ) (temp_throuthFeatures= ruleThroughColumn )? (temp_throuthFeatures= ruleThroughReferencedColumn )? (temp_throuthFeatures= ruleThroughInverseColumn )? ) )
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:288:4: ( (temp_name= 'through' ) (temp_throuthFeatures= ruleThroughTable ) (temp_throuthFeatures= ruleThroughColumn )? (temp_throuthFeatures= ruleThroughReferencedColumn )? (temp_throuthFeatures= ruleThroughInverseColumn )? )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "Through");
              				ptm.setModelElement(result);
              			 
            }
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:292:1: ( (temp_name= 'through' ) (temp_throuthFeatures= ruleThroughTable ) (temp_throuthFeatures= ruleThroughColumn )? (temp_throuthFeatures= ruleThroughReferencedColumn )? (temp_throuthFeatures= ruleThroughInverseColumn )? )
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:292:2: (temp_name= 'through' ) (temp_throuthFeatures= ruleThroughTable ) (temp_throuthFeatures= ruleThroughColumn )? (temp_throuthFeatures= ruleThroughReferencedColumn )? (temp_throuthFeatures= ruleThroughInverseColumn )?
            {
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:292:2: (temp_name= 'through' )
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:292:3: temp_name= 'through'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(11)).eContents().get(1)).eContents().get(0)),line(),start());
            }
            temp_name=(Token)input.LT(1);
            match(input,21,FOLLOW_21_in_ruleThrough617); if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"name",convert(temp_name),false); ptm.ruleFinished(temp_name,end()); 
            }

            }

            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:295:1: (temp_throuthFeatures= ruleThroughTable )
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:295:2: temp_throuthFeatures= ruleThroughTable
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(11)).eContents().get(1)).eContents().get(1)),line(),start());
            }
            pushFollow(FOLLOW_ruleThroughTable_in_ruleThrough628);
            temp_throuthFeatures=ruleThroughTable();
            _fsp--;
            if (failed) return result;
            if ( backtracking==0 ) {
              factory.add(result,"throuthFeatures",convert(temp_throuthFeatures),false); ptm.ruleFinished(temp_throuthFeatures,end()); 
            }

            }

            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:298:1: (temp_throuthFeatures= ruleThroughColumn )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==19) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:298:2: temp_throuthFeatures= ruleThroughColumn
                    {
                    if ( backtracking==0 ) {
                      ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(11)).eContents().get(1)).eContents().get(2)),line(),start());
                    }
                    pushFollow(FOLLOW_ruleThroughColumn_in_ruleThrough639);
                    temp_throuthFeatures=ruleThroughColumn();
                    _fsp--;
                    if (failed) return result;
                    if ( backtracking==0 ) {
                      factory.add(result,"throuthFeatures",convert(temp_throuthFeatures),false); ptm.ruleFinished(temp_throuthFeatures,end()); 
                    }

                    }
                    break;

            }

            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:301:1: (temp_throuthFeatures= ruleThroughReferencedColumn )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==20) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:301:2: temp_throuthFeatures= ruleThroughReferencedColumn
                    {
                    if ( backtracking==0 ) {
                      ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(11)).eContents().get(1)).eContents().get(3)),line(),start());
                    }
                    pushFollow(FOLLOW_ruleThroughReferencedColumn_in_ruleThrough651);
                    temp_throuthFeatures=ruleThroughReferencedColumn();
                    _fsp--;
                    if (failed) return result;
                    if ( backtracking==0 ) {
                      factory.add(result,"throuthFeatures",convert(temp_throuthFeatures),false); ptm.ruleFinished(temp_throuthFeatures,end()); 
                    }

                    }
                    break;

            }

            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:304:1: (temp_throuthFeatures= ruleThroughInverseColumn )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==22) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:304:2: temp_throuthFeatures= ruleThroughInverseColumn
                    {
                    if ( backtracking==0 ) {
                      ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(11)).eContents().get(1)).eContents().get(4)),line(),start());
                    }
                    pushFollow(FOLLOW_ruleThroughInverseColumn_in_ruleThrough663);
                    temp_throuthFeatures=ruleThroughInverseColumn();
                    _fsp--;
                    if (failed) return result;
                    if ( backtracking==0 ) {
                      factory.add(result,"throuthFeatures",convert(temp_throuthFeatures),false); ptm.ruleFinished(temp_throuthFeatures,end()); 
                    }

                    }
                    break;

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 13, ruleThrough_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleThrough


    // $ANTLR start ruleThroughTable
    // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:309:1: ruleThroughTable returns [EObject result] : ( (temp_name= 'table' ) (temp_value= RULE_STRING ) ) ;
    public EObject ruleThroughTable() throws RecognitionException {
        EObject result = null;
        int ruleThroughTable_StartIndex = input.index();
        Token temp_name=null;
        Token temp_value=null;

        try {
            if ( backtracking>0 && alreadyParsedRule(input, 14) ) { return result; }
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:310:4: ( ( (temp_name= 'table' ) (temp_value= RULE_STRING ) ) )
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:310:4: ( (temp_name= 'table' ) (temp_value= RULE_STRING ) )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "ThroughTable");
              				ptm.setModelElement(result);
              			 
            }
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:314:1: ( (temp_name= 'table' ) (temp_value= RULE_STRING ) )
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:314:2: (temp_name= 'table' ) (temp_value= RULE_STRING )
            {
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:314:2: (temp_name= 'table' )
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:314:3: temp_name= 'table'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(12)).eContents().get(1)).eContents().get(0)),line(),start());
            }
            temp_name=(Token)input.LT(1);
            match(input,13,FOLLOW_13_in_ruleThroughTable693); if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"name",convert(temp_name),false); ptm.ruleFinished(temp_name,end()); 
            }

            }

            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:317:1: (temp_value= RULE_STRING )
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:317:2: temp_value= RULE_STRING
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(12)).eContents().get(1)).eContents().get(1)),line(),start());
            }
            temp_value=(Token)input.LT(1);
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleThroughTable704); if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"value",convert(temp_value),false); ptm.ruleFinished(temp_value,end()); 
            }

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 14, ruleThroughTable_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleThroughTable


    // $ANTLR start ruleThroughColumn
    // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:322:1: ruleThroughColumn returns [EObject result] : ( (temp_name= 'column' ) (temp_value= RULE_STRING ) ) ;
    public EObject ruleThroughColumn() throws RecognitionException {
        EObject result = null;
        int ruleThroughColumn_StartIndex = input.index();
        Token temp_name=null;
        Token temp_value=null;

        try {
            if ( backtracking>0 && alreadyParsedRule(input, 15) ) { return result; }
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:323:4: ( ( (temp_name= 'column' ) (temp_value= RULE_STRING ) ) )
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:323:4: ( (temp_name= 'column' ) (temp_value= RULE_STRING ) )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "ThroughColumn");
              				ptm.setModelElement(result);
              			 
            }
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:327:1: ( (temp_name= 'column' ) (temp_value= RULE_STRING ) )
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:327:2: (temp_name= 'column' ) (temp_value= RULE_STRING )
            {
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:327:2: (temp_name= 'column' )
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:327:3: temp_name= 'column'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(13)).eContents().get(1)).eContents().get(0)),line(),start());
            }
            temp_name=(Token)input.LT(1);
            match(input,19,FOLLOW_19_in_ruleThroughColumn733); if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"name",convert(temp_name),false); ptm.ruleFinished(temp_name,end()); 
            }

            }

            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:330:1: (temp_value= RULE_STRING )
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:330:2: temp_value= RULE_STRING
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(13)).eContents().get(1)).eContents().get(1)),line(),start());
            }
            temp_value=(Token)input.LT(1);
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleThroughColumn744); if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"value",convert(temp_value),false); ptm.ruleFinished(temp_value,end()); 
            }

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 15, ruleThroughColumn_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleThroughColumn


    // $ANTLR start ruleThroughReferencedColumn
    // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:335:1: ruleThroughReferencedColumn returns [EObject result] : ( (temp_name= 'referencedColumn' ) (temp_value= RULE_STRING ) ) ;
    public EObject ruleThroughReferencedColumn() throws RecognitionException {
        EObject result = null;
        int ruleThroughReferencedColumn_StartIndex = input.index();
        Token temp_name=null;
        Token temp_value=null;

        try {
            if ( backtracking>0 && alreadyParsedRule(input, 16) ) { return result; }
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:336:4: ( ( (temp_name= 'referencedColumn' ) (temp_value= RULE_STRING ) ) )
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:336:4: ( (temp_name= 'referencedColumn' ) (temp_value= RULE_STRING ) )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "ThroughReferencedColumn");
              				ptm.setModelElement(result);
              			 
            }
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:340:1: ( (temp_name= 'referencedColumn' ) (temp_value= RULE_STRING ) )
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:340:2: (temp_name= 'referencedColumn' ) (temp_value= RULE_STRING )
            {
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:340:2: (temp_name= 'referencedColumn' )
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:340:3: temp_name= 'referencedColumn'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(14)).eContents().get(1)).eContents().get(0)),line(),start());
            }
            temp_name=(Token)input.LT(1);
            match(input,20,FOLLOW_20_in_ruleThroughReferencedColumn773); if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"name",convert(temp_name),false); ptm.ruleFinished(temp_name,end()); 
            }

            }

            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:343:1: (temp_value= RULE_STRING )
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:343:2: temp_value= RULE_STRING
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(14)).eContents().get(1)).eContents().get(1)),line(),start());
            }
            temp_value=(Token)input.LT(1);
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleThroughReferencedColumn784); if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"value",convert(temp_value),false); ptm.ruleFinished(temp_value,end()); 
            }

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 16, ruleThroughReferencedColumn_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleThroughReferencedColumn


    // $ANTLR start ruleThroughInverseColumn
    // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:348:1: ruleThroughInverseColumn returns [EObject result] : ( (temp_name= 'inverseColumn' ) (temp_value= RULE_STRING ) ) ;
    public EObject ruleThroughInverseColumn() throws RecognitionException {
        EObject result = null;
        int ruleThroughInverseColumn_StartIndex = input.index();
        Token temp_name=null;
        Token temp_value=null;

        try {
            if ( backtracking>0 && alreadyParsedRule(input, 17) ) { return result; }
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:349:4: ( ( (temp_name= 'inverseColumn' ) (temp_value= RULE_STRING ) ) )
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:349:4: ( (temp_name= 'inverseColumn' ) (temp_value= RULE_STRING ) )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "ThroughInverseColumn");
              				ptm.setModelElement(result);
              			 
            }
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:353:1: ( (temp_name= 'inverseColumn' ) (temp_value= RULE_STRING ) )
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:353:2: (temp_name= 'inverseColumn' ) (temp_value= RULE_STRING )
            {
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:353:2: (temp_name= 'inverseColumn' )
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:353:3: temp_name= 'inverseColumn'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(15)).eContents().get(1)).eContents().get(0)),line(),start());
            }
            temp_name=(Token)input.LT(1);
            match(input,22,FOLLOW_22_in_ruleThroughInverseColumn813); if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"name",convert(temp_name),false); ptm.ruleFinished(temp_name,end()); 
            }

            }

            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:356:1: (temp_value= RULE_STRING )
            // ..//com.piece_framework.piece_ide.piece_orm.mapper/src-gen//com/piece_framework/piece_ide/piece_orm/mapper/parser/Piece_ORM_Mapper.g:356:2: temp_value= RULE_STRING
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(15)).eContents().get(1)).eContents().get(1)),line(),start());
            }
            temp_value=(Token)input.LT(1);
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleThroughInverseColumn824); if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"value",convert(temp_value),false); ptm.ruleFinished(temp_value,end()); 
            }

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 17, ruleThroughInverseColumn_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleThroughInverseColumn


 

    public static final BitSet FOLLOW_ruleMapper_in_parse67 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_parse69 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleMethod_in_ruleMapper92 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleMethod120 = new BitSet(new long[]{0x0000000000001C02L});
    public static final BitSet FOLLOW_ruleQuery_in_ruleMethod131 = new BitSet(new long[]{0x0000000000001802L});
    public static final BitSet FOLLOW_ruleOrderBy_in_ruleMethod143 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_ruleAssociations_in_ruleMethod155 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_10_in_ruleQuery185 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleQuery196 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_11_in_ruleOrderBy225 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleOrderBy236 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_ruleAssociations265 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_ruleAssociation_in_ruleAssociations276 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleAssociation306 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_ruleTable_in_ruleAssociation317 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_ruleType_in_ruleAssociation328 = new BitSet(new long[]{0x0000000000380802L});
    public static final BitSet FOLLOW_ruleColumn_in_ruleAssociation339 = new BitSet(new long[]{0x0000000000300802L});
    public static final BitSet FOLLOW_ruleReferencedColumn_in_ruleAssociation351 = new BitSet(new long[]{0x0000000000200802L});
    public static final BitSet FOLLOW_ruleThrough_in_ruleAssociation363 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_ruleOrderBy_in_ruleAssociation375 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_ruleTable405 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleTable416 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_ruleType445 = new BitSet(new long[]{0x0000000000078000L});
    public static final BitSet FOLLOW_ruleTypeRule_in_ruleType456 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_ruleTypeRule483 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_ruleTypeRule492 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_ruleTypeRule501 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_ruleTypeRule510 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_ruleColumn537 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleColumn548 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_ruleReferencedColumn577 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleReferencedColumn588 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_ruleThrough617 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_ruleThroughTable_in_ruleThrough628 = new BitSet(new long[]{0x0000000000580002L});
    public static final BitSet FOLLOW_ruleThroughColumn_in_ruleThrough639 = new BitSet(new long[]{0x0000000000500002L});
    public static final BitSet FOLLOW_ruleThroughReferencedColumn_in_ruleThrough651 = new BitSet(new long[]{0x0000000000400002L});
    public static final BitSet FOLLOW_ruleThroughInverseColumn_in_ruleThrough663 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_ruleThroughTable693 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleThroughTable704 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_ruleThroughColumn733 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleThroughColumn744 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_ruleThroughReferencedColumn773 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleThroughReferencedColumn784 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_ruleThroughInverseColumn813 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleThroughInverseColumn824 = new BitSet(new long[]{0x0000000000000002L});

}