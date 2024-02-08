package com.bagadesh.featureflags.domain

/**
 * Created by bagadesh on 15/04/23.
 */
sealed interface ValidateChange {

    object Failed : ValidateChange

    object NoDifference : ValidateChange

    class Success(val data: Any) : ValidateChange

}