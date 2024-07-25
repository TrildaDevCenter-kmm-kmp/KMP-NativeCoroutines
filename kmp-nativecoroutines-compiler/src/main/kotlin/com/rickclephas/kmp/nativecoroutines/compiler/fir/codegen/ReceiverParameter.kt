package com.rickclephas.kmp.nativecoroutines.compiler.fir.codegen

import org.jetbrains.kotlin.KtFakeSourceElementKind
import org.jetbrains.kotlin.fakeElement
import org.jetbrains.kotlin.fir.declarations.FirReceiverParameter
import org.jetbrains.kotlin.fir.declarations.builder.buildReceiverParameter
import org.jetbrains.kotlin.fir.extensions.FirExtension
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor
import org.jetbrains.kotlin.fir.types.coneType
import org.jetbrains.kotlin.fir.types.toFirResolvedTypeRef

internal fun FirExtension.buildReceiverParameterCopy(
    originalParameter: FirReceiverParameter?,
    substitutor: ConeSubstitutor
): FirReceiverParameter? {
    if (originalParameter == null) return null
    return buildReceiverParameter {
        source = originalParameter.source?.fakeElement(KtFakeSourceElementKind.PluginGenerated)

        // TODO: copy type ref annotations?
        typeRef = originalParameter.typeRef.coneType
            .let(substitutor::substituteOrSelf)
            .toFirResolvedTypeRef()

        annotations.addAll(buildAnnotationsCopy(originalParameter.annotations))
    }
}
