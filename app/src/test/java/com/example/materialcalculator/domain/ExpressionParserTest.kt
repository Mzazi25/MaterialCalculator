package com.example.materialcalculator.domain

import com.google.common.truth.Truth
import org.junit.Test


class ExpressionParserTest{

    private lateinit var parser: ExpressionParser

    @Test
    fun `Simple expression is properly parsed`(){
        //GIVEN
        parser = ExpressionParser("3+5-3x4/3")

        //DO SOMETHING WITH WHAT'S GIVEN
        val actual = parser.parse()
        //ASSERT EXPECTED ==ACTUAL
        val expected = listOf(
            ExpressionPart.Number(3.0),
            ExpressionPart.Op(Operation.ADD),
            ExpressionPart.Number(5.0),
            ExpressionPart.Op(Operation.SUBTRACT),
            ExpressionPart.Number(3.0),
            ExpressionPart.Op(Operation.MULTIPLY),
            ExpressionPart.Number(4.0),
            ExpressionPart.Op(Operation.DIVIDE),
            ExpressionPart.Number(3.0),
        )
        Truth.assertThat(expected).isEqualTo(actual)
    }

    @Test
    fun `Expression with parenthesis is properly parsed`(){
        parser = ExpressionParser("4-(4x5)")
        val actual = parser.parse()
        val expected = listOf(
            ExpressionPart.Number(4.0),
            ExpressionPart.Op(Operation.SUBTRACT),
            ExpressionPart.Parenthesis(ParenthesisType.Opening),
            ExpressionPart.Number(4.0),
            ExpressionPart.Op(Operation.MULTIPLY),
            ExpressionPart.Number(5.0),
            ExpressionPart.Parenthesis(ParenthesisType.Closing),
        )
        Truth.assertThat(expected).isEqualTo(actual)

    }
}