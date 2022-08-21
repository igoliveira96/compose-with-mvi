package goulart.composemvi.domain.exceptions

open class DomainException(message: String, title: String? = null) :
    RuntimeException(message, RuntimeException(title))

sealed class ParamException(message: String, title: String? = null) :
    DomainException(message, title)

class MissingParamsException : ParamException("Params must not be null.")