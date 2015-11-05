package com.xiaomi.nlp.util.regular;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

/**
 * Created by dy on 15-11-5.
 */
public class RegexListenerImpl extends RegexBaseListener{
    StringBuffer res = new StringBuffer();

    @Override
    public void visitErrorNode(@NotNull ErrorNode node) {
        super.visitErrorNode(node);
    }

    @Override
    public void enterWildcard(@NotNull RegexParser.WildcardContext ctx) {
        super.enterWildcard(ctx);
    }

    @Override
    public void exitWildcard(@NotNull RegexParser.WildcardContext ctx) {
        super.exitWildcard(ctx);
    }

    @Override
    public void enterS(@NotNull RegexParser.SContext ctx) {
        super.enterS(ctx);
    }

    @Override
    public void exitS(@NotNull RegexParser.SContext ctx) {
        super.exitS(ctx);
    }

    @Override
    public void enterS_tag(@NotNull RegexParser.S_tagContext ctx) {
        super.enterS_tag(ctx);
    }

    @Override
    public void exitS_tag(@NotNull RegexParser.S_tagContext ctx) {
        super.exitS_tag(ctx);
    }

    @Override
    public void enterS_tag_name(@NotNull RegexParser.S_tag_nameContext ctx) {
        super.enterS_tag_name(ctx);
    }

    @Override
    public void exitS_tag_name(@NotNull RegexParser.S_tag_nameContext ctx) {
        super.exitS_tag_name(ctx);
    }

    @Override
    public void enterRe_choice(@NotNull RegexParser.Re_choiceContext ctx) {
        super.enterRe_choice(ctx);
    }

    @Override
    public void exitRe_choice(@NotNull RegexParser.Re_choiceContext ctx) {
        super.exitRe_choice(ctx);
    }

    @Override
    public void enterRe_seq_elem(@NotNull RegexParser.Re_seq_elemContext ctx) {
        super.enterRe_seq_elem(ctx);
    }

    @Override
    public void exitRe_seq_elem(@NotNull RegexParser.Re_seq_elemContext ctx) {
        super.exitRe_seq_elem(ctx);
    }

    @Override
    public void enterRe_seq(@NotNull RegexParser.Re_seqContext ctx) {
        super.enterRe_seq(ctx);
    }

    @Override
    public void exitRe_seq(@NotNull RegexParser.Re_seqContext ctx) {
        super.exitRe_seq(ctx);
    }

    @Override
    public void enterRe_factor(@NotNull RegexParser.Re_factorContext ctx) {
        super.enterRe_factor(ctx);
    }

    @Override
    public void exitRe_factor(@NotNull RegexParser.Re_factorContext ctx) {
        super.exitRe_factor(ctx);
    }

    @Override
    public void enterRe_char(@NotNull RegexParser.Re_charContext ctx) {
        super.enterRe_char(ctx);
    }

    @Override
    public void exitRe_char(@NotNull RegexParser.Re_charContext ctx) {
        super.exitRe_char(ctx);
    }

    @Override
    public void enterRe_class(@NotNull RegexParser.Re_classContext ctx) {
        super.enterRe_class(ctx);
    }

    @Override
    public void exitRe_class(@NotNull RegexParser.Re_classContext ctx) {
        super.exitRe_class(ctx);
    }

    @Override
    public void enterRe_class_char(@NotNull RegexParser.Re_class_charContext ctx) {
        super.enterRe_class_char(ctx);
    }

    @Override
    public void exitRe_class_char(@NotNull RegexParser.Re_class_charContext ctx) {
        super.exitRe_class_char(ctx);
    }

    @Override
    public void enterRe_group(@NotNull RegexParser.Re_groupContext ctx) {
        super.enterRe_group(ctx);
    }

    @Override
    public void exitRe_group(@NotNull RegexParser.Re_groupContext ctx) {
        super.exitRe_group(ctx);
    }

    @Override
    public void enterRe_quant(@NotNull RegexParser.Re_quantContext ctx) {
        super.enterRe_quant(ctx);
    }

    @Override
    public void exitRe_quant(@NotNull RegexParser.Re_quantContext ctx) {
        super.exitRe_quant(ctx);
    }

    @Override
    public void enterInt_seq(@NotNull RegexParser.Int_seqContext ctx) {
        super.enterInt_seq(ctx);
    }

    @Override
    public void exitInt_seq(@NotNull RegexParser.Int_seqContext ctx) {
        super.exitInt_seq(ctx);
    }

    @Override
    public void enterEsc(@NotNull RegexParser.EscContext ctx) {
        super.enterEsc(ctx);
    }

    @Override
    public void exitEsc(@NotNull RegexParser.EscContext ctx) {
        super.exitEsc(ctx);
    }

    @Override
    public void enterGroup_ref(@NotNull RegexParser.Group_refContext ctx) {
        super.enterGroup_ref(ctx);
    }

    @Override
    public void exitGroup_ref(@NotNull RegexParser.Group_refContext ctx) {
        super.exitGroup_ref(ctx);
    }

    @Override
    public void enterRe_esc_char(@NotNull RegexParser.Re_esc_charContext ctx) {
        super.enterRe_esc_char(ctx);
    }

    @Override
    public void exitRe_esc_char(@NotNull RegexParser.Re_esc_charContext ctx) {
        super.exitRe_esc_char(ctx);
    }

    @Override
    public void enterEveryRule(@NotNull ParserRuleContext ctx) {
        super.enterEveryRule(ctx);
    }

    @Override
    public void exitEveryRule(@NotNull ParserRuleContext ctx) {
        super.exitEveryRule(ctx);
    }

    @Override
    public void visitTerminal(@NotNull TerminalNode node) {
        super.visitTerminal(node);
    }
}
