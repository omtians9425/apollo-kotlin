package com.apollographql.apollo3.compiler.codegen.java.helpers

import com.apollographql.apollo3.compiler.codegen.java.JavaClassNames
import com.apollographql.apollo3.compiler.codegen.java.JavaContext
import com.apollographql.apollo3.compiler.codegen.java.L
import com.apollographql.apollo3.compiler.codegen.java.T
import com.apollographql.apollo3.compiler.ir.IrListTypeRef
import com.apollographql.apollo3.compiler.ir.IrNamedTypeRef
import com.apollographql.apollo3.compiler.ir.IrNonNullTypeRef
import com.apollographql.apollo3.compiler.ir.IrTypeRef
import com.squareup.javapoet.CodeBlock

internal fun IrTypeRef.codeBlock(context: JavaContext): CodeBlock {
  return when (this) {
    is IrNonNullTypeRef -> {
      CodeBlock.of("new $T($L)", JavaClassNames.CompiledNotNullType, ofType.codeBlock(context))
    }
    is IrListTypeRef -> {
      CodeBlock.of("new $T($L)", JavaClassNames.CompiledListType, ofType.codeBlock(context))
    }
    is IrNamedTypeRef -> {
      context.resolver.resolveCompiledType(name)
    }
  }
}