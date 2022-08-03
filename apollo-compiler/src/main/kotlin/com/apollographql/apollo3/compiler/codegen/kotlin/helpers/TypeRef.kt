package com.apollographql.apollo3.compiler.codegen.kotlin.helpers

import com.apollographql.apollo3.compiler.codegen.kotlin.KotlinContext
import com.apollographql.apollo3.compiler.ir.IrListTypeRef
import com.apollographql.apollo3.compiler.ir.IrNamedTypeRef
import com.apollographql.apollo3.compiler.ir.IrNonNullTypeRef
import com.apollographql.apollo3.compiler.ir.IrTypeRef
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.MemberName

internal fun IrTypeRef.codeBlock(context: KotlinContext): CodeBlock {
  return when (this) {
    is IrNonNullTypeRef -> {
      val notNullFun = MemberName("com.apollographql.apollo3.api", "notNull")
      CodeBlock.of("%L.%M()", ofType.codeBlock(context), notNullFun)
    }
    is IrListTypeRef -> {
      val listFun = MemberName("com.apollographql.apollo3.api", "list")
      CodeBlock.of("%L.%M()", ofType.codeBlock(context), listFun)
    }
    is IrNamedTypeRef -> {
      context.resolver.resolveCompiledType(name)
    }
  }
}
